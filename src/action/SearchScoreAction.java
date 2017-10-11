package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import model.StudentCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import util.ViewExcel;

import dao.SearchScoreDao;

@Scope(value="prototype")
@Controller("SearchScore")
@RequestMapping(value="searchscore")
public class SearchScoreAction {
	@Autowired SearchScoreDao searchScoreDao;

	/**
	 * 查询登录学生的基本信息
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="showstudentinfo")
	public String showstudentinfo(Model model,HttpSession session){
		int userid=(Integer) session.getAttribute("successuserid");
		ArrayList<Student> list = searchScoreDao.searchstudentinfo(userid);
		ArrayList<StudentCourse> list2 = searchScoreDao.searchstuscore(list.get(0).getNumber());
		model.addAttribute("stuname", list.get(0).getName());
		model.addAttribute("stuclass", list.get(0).getClass_());
		model.addAttribute("stunumber", list.get(0).getNumber());
		model.addAttribute("stuscore", list2);
		return "StudentScore";
	}
	
	/**
	 * 学生成绩信息-进行导出
	 * @param response
	 * @param req
	 */
	@RequestMapping(value="download")
	public String outexcle(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
        //填充projects数据
		int userid=(Integer) session.getAttribute("successuserid");
		String stuname=(String) session.getAttribute("successusername");
		String fileName=stuname+"-成绩单";
        ArrayList<StudentCourse> scorelist=createData(userid);
        ArrayList<Map<String,Object>> list=createExcelRecord(scorelist);
        String columnNames[]={"序号","学科","成绩"};//列名
        String keys[]    =     {"xuhao","course","score"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ViewExcel.createWorkbook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }
    private ArrayList<StudentCourse> createData(int userid) {
		ArrayList<Student> list = searchScoreDao.searchstudentinfo(userid);
		ArrayList<StudentCourse> list2 = searchScoreDao.searchstuscore(list.get(0).getNumber());
        return list2;
    }
    private ArrayList<Map<String, Object>> createExcelRecord(ArrayList<StudentCourse> scorelist) {
        ArrayList<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetname", "cjd");
        listmap.add(map);
        StudentCourse stuscore=null;
        for (int j = 0; j < scorelist.size(); j++) {
        	stuscore=scorelist.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("xuhao", j+1);
            mapValue.put("course", stuscore.getCourse());
            mapValue.put("score", stuscore.getScore());
            listmap.add(mapValue);
        }
        return listmap;
	}
	
}