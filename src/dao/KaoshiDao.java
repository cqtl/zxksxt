package dao;

import java.util.ArrayList;

import model.Shijuan;
import model.StudentShijuan;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class KaoshiDao {
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> SearchsubjectDao(String course){
		Session session=sessionFactory.openSession();
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		String queryString="select s,e.howlong, e.examtime from Examination e, Shijuan s where e.shijuanname=s.name and e.state=1 ";
		Query query=session.createQuery(queryString);
		list=(ArrayList<Object[]>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean tijiao(String subids[],String answers[],int userid,String shijuanname){
		Session session=sessionFactory.openSession();
		StudentShijuan studentsj;
		String querystring="";
		Query query;
		querystring="select s.score from Shijuan s where s.name='"+shijuanname+"'";
		query=session.createQuery(querystring);
		ArrayList<String> sList=(ArrayList<String>) query.list();
		String score=sList.get(0);
		String scores[]=score.split(",");
		for (int i = 0; i < subids.length; i++) {
			studentsj=new StudentShijuan();
			querystring="select s.leixing from Subjects s where s.id='"+subids[i]+"'";
			query=session.createQuery(querystring);
			if("单选题".equals(query.list().get(0)) || "多选题".equals(query.list().get(0))){
				querystring="select a.answer from Answers a where a.subjectId='"+subids[i]+"'";
				query=session.createQuery(querystring);
				if (answers[i].equals(query.list().get(0))) {
					studentsj.setScore(Float.parseFloat(scores[i]));
				}
				else {
					studentsj.setScore((float) 0);
				}
				studentsj.setIschecked(1);
			}
			else {
				studentsj.setIschecked(0);
			}
			studentsj.setAnswer(answers[i]);
			studentsj.setSubjectId(Integer.parseInt(subids[i]));
			studentsj.setUserId(userid);
			studentsj.setShijuanname(shijuanname);
			session.save(studentsj);
		}
		session.flush();
		session.clear();
		session.close();
		return true;
	}
}
