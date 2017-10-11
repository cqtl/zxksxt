package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Answers;
import model.Shijuan;
import model.Subjects;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ChutiDao;
import dao.SearchsubjectDao;

@Scope(value="prototype")
@Controller("ChutiAction")
@RequestMapping(value="chuti")
public class ChutiAction {
	
	@Autowired
	SearchsubjectDao searchsubjectDao;
	@Autowired
	ChutiDao chutiDao;
	/**
	 * ��ѯ����������Ŀ���������ʦѡ��
	 */
	@RequestMapping
	public void searchsubjects(String pagenow , Subjects subject, HttpServletResponse response){
		int pagenow1=Integer.parseInt(pagenow);   //��ǰҳ
		int pagesize=5;   //��ҳ�Ĵ�С��һҳ��ʾ����������
		int count=0;     //��Ŀ������
		count=searchsubjectDao.searchsubjectscount(subject);
		int pagenumber=count/pagesize;
		if(count%pagesize>0){
			pagenumber=pagenumber+1;
		}
		ArrayList<Object> list=new ArrayList<Object>();
		list=searchsubjectDao.searchsubjectsDao(pagenow1,pagesize,subject);
		
		JSONArray jsonArray=JSONArray.fromObject(list);
		jsonArray.add("{\"pagenumber\":\""+pagenumber+"\"}");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �������ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="kemu")
	public String kemu(Model model){
		
		return "Chuti";
	}
	
	/**
	 * ��ѯ��Ŀ����ϸ���
	 * @param subid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="subjectdetail")
	public String subjectdetail(String subid,Model model){
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		ArrayList<Object> list1=new ArrayList<Object>();
		list=searchsubjectDao.searchsubdetail(subid);
		String xuanxiangString="";
		String answerString="";
		String anString[];
		String abc[]={"A","B","C","D","E","F","G","H","I","J"};
		if (list.get(0)[2].equals("��ѡ��")) {
			for (int i = 0; i < 10; i++) {
				if (((Answers) list.get(0)[3]).getAnswer().trim().equals(String.valueOf(i+1))) {
					answerString=abc[i];
				}
			}
		}
		else if (list.get(0)[2].equals("��ѡ��")) {
			anString=((Answers) list.get(0)[3]).getAnswer().trim().split(",");
			for (int i = 0; i < anString.length; i++) {
				for (int j = 0; j < 10; j++) {
					if (anString[i].trim().equals(String.valueOf(j+1))) {
						answerString=answerString+abc[j]+" , ";
					}
				}
			}
		}
		else {
			answerString=((Answers) list.get(0)[3]).getAnswer();
		}
		if (((Answers) list.get(0)[3]).getXuanxiang1()!=null) {
			xuanxiangString=xuanxiangString+"A. "+((Answers) list.get(0)[3]).getXuanxiang1()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang2()!=null) {
			xuanxiangString=xuanxiangString+"B. "+((Answers) list.get(0)[3]).getXuanxiang2()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang3()!=null) {
			xuanxiangString=xuanxiangString+"C. "+((Answers) list.get(0)[3]).getXuanxiang3()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang4()!=null) {
			xuanxiangString=xuanxiangString+"D. "+((Answers) list.get(0)[3]).getXuanxiang4()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang5()!=null) {
			xuanxiangString=xuanxiangString+"E. "+((Answers) list.get(0)[3]).getXuanxiang5()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang6()!=null) {
			xuanxiangString=xuanxiangString+"F. "+((Answers) list.get(0)[3]).getXuanxiang6()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang7()!=null) {
			xuanxiangString=xuanxiangString+"G. "+((Answers) list.get(0)[3]).getXuanxiang7()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang8()!=null) {
			xuanxiangString=xuanxiangString+"H. "+((Answers) list.get(0)[3]).getXuanxiang8()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang9()!=null) {
			xuanxiangString=xuanxiangString+"I. "+((Answers) list.get(0)[3]).getXuanxiang9()+" ";
		}
		if (((Answers) list.get(0)[3]).getXuanxiang10()!=null) {
			xuanxiangString=xuanxiangString+"J. "+((Answers) list.get(0)[3]).getXuanxiang10()+" ";
		}
		list1.add(list.get(0)[0]);
		list1.add(list.get(0)[1]);
		list1.add(list.get(0)[2]);
		list1.add(xuanxiangString);
		list1.add(answerString);
		list1.add(list.get(0)[4]);
		model.addAttribute("sublist", list1);
		System.err.println(list.get(0)[0]);
		return "Subjectdetail";
	}
	
	/**
	 * ��ѡ�����Ŀ��ѯ����
	 * @param subid
	 */
	@RequestMapping(value="saveselecttimu")
	public void saveselecttimu(String subid,HttpServletResponse response){
		ArrayList<Subjects> list=new ArrayList<Subjects>();
		if(subid!=null){
			String subid1[]=subid.split(",");
			for (int i = 0; i < subid1.length; i++) {
				list.add(chutiDao.searchselecttimu(subid1[i]).get(0));
			}
		}
		JSONArray jsonArray=JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �������ʦѡ�е���洢Ϊ�Ծ�
	 * @param subid
	 */
	@RequestMapping(value="makeshijuan")
	public String makeshijuan(String subid,Model model){
		ArrayList<Object[]> tiankongList=new ArrayList<Object[]>();
		String tiankongtimu = null;
		ArrayList<Object[]> danxuanList=new ArrayList<Object[]>();
		String danxuantimu = null;
		ArrayList<Object[]> duoxuanList=new ArrayList<Object[]>();
		String duoxuantimu = null;
		ArrayList<Object[]> jiandaList=new ArrayList<Object[]>();
		String jiandatimu = null;
		ArrayList<Answers> danxuanansList=new ArrayList<Answers>();
		ArrayList<Answers> duoxuanansList=new ArrayList<Answers>();
		ArrayList<Answers> tiankongansList=new ArrayList<Answers>();
		if(subid!=null){
			String subid1[]=subid.split(",");
			for (int i = 0; i < subid1.length; i++) {
				if (chutiDao.makesub(subid1[i], "�����").size()>0) {
					tiankongList.add(chutiDao.makesub(subid1[i], "�����").get(0));
					tiankongansList.add(chutiDao.makeans(subid1[i]).get(0));
					tiankongtimu="�����";
				}
				if (chutiDao.makesub(subid1[i], "��ѡ��").size()>0) {
					danxuanList.add(chutiDao.makesub(subid1[i], "��ѡ��").get(0));
					danxuanansList.add(chutiDao.makeans(subid1[i]).get(0));
					danxuantimu="��ѡ��";
				}
				if (chutiDao.makesub(subid1[i], "��ѡ��").size()>0) {
					duoxuanList.add(chutiDao.makesub(subid1[i], "��ѡ��").get(0));
					duoxuanansList.add(chutiDao.makeans(subid1[i]).get(0));
					duoxuantimu="��ѡ��";
				}
				if (chutiDao.makesub(subid1[i], "�����").size()>0) {
					jiandaList.add(chutiDao.makesub(subid1[i], "�����").get(0));
					jiandatimu="�����";
				}
			}
		}
		System.err.println("ff");
		model.addAttribute("tiankong", tiankongList);
		model.addAttribute("tiankongans", tiankongansList);
		model.addAttribute("danxuan", danxuanList);
		model.addAttribute("duoxuan", duoxuanList);
		model.addAttribute("jianda", jiandaList);
		model.addAttribute("danxuanans", danxuanansList);
		model.addAttribute("duoxuanans", duoxuanansList);
		model.addAttribute("danxuantimu", danxuantimu);
		model.addAttribute("duoxuantimu", duoxuantimu);
		model.addAttribute("jiandatimu", jiandatimu);
		model.addAttribute("tiankongtimu", tiankongtimu);
		return "Shijuan";
	}
	
	/**
	 * �����ɵ��Ծ��浽���ݿ���
	 * @param subid
	 * @param response
	 */
	@RequestMapping(value="savetoshijuan")
	public void savetoshijuan(String subid,String shijuanname, String score, HttpServletResponse response,HttpSession session){
		String jsondata="";
		boolean issuccess=false;
		//�õ���ǰ��¼�˵�����,��������
		String username=(String) session.getAttribute("successusername");
		Shijuan shijuan=new Shijuan();
		shijuan.setCreatedby(username);
		shijuan.setName(shijuanname);
		shijuan.setSubjectIds(subid);
		shijuan.setScore(score);
		issuccess=chutiDao.savetoshijuan(shijuan);
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"����ɹ�\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"����ʧ��\"}";
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsondata);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ������Ӧ�ó�������������
	 * @param response
	 * @param session
	 */
	@RequestMapping(value="searchshijuanname")
	public void searchshijuanname(HttpServletResponse response,HttpSession session){
		//�õ���ǰ��¼�˵�����,��������
		String username=(String) session.getAttribute("successusername");
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		list=chutiDao.searchshijuanname(username);
		JSONArray jsonArray=JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
