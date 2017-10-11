package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import util.ViewExcel;

import dao.Logindao;
import dao.SearchAllUserDao;

@Scope(value="prototype")
@SessionAttributes({"successusername","yy","successuserid"})
@Controller("login")
public class LoginAction {
	@Autowired
	Logindao ld;
	@Autowired
	SearchAllUserDao searchAllUserDao;
	
	@RequestMapping("loginaction")
	public String login(User user,Model model,HttpServletResponse response){    //方法一
		ArrayList<User> list=ld.logindao();
		String sname="";
		String spassword="";
		User user1=null;
		for(int i=0;i<list.size();i++){
			user1=(User) list.get(i);
			sname=user1.getName();
			spassword=user1.getPassword();
			if(sname.equals(user.getName())&&spassword.equals(user.getPassword())){
				model.addAttribute("name", sname);
				model.addAttribute("password", spassword);
				model.addAttribute("successusername", sname);
				return "sucess";
			}
			else {
				continue;
			}
		}
			return "fail";
	}
	/*
	 * 方法二
	public String login(@RequestParam("username") String name,@RequestParam("password") String password, Model model,@RequestParam("qqq") String a ){
//		for(User user:list){
//			name=user.getName();
//			password=user.getPassword();
//		}
		
//		String queryString="select from model User";
//		ArrayList list= (ArrayList) getHibernateTemplate().find(queryString);
		System.out.println(a);
		ArrayList list=ld.logindao();
		String sname="";
		String spassword="";
		User user=null;
		for(int i=0;i<list.size();i++){
			user=(User) list.get(i);
			sname=user.getName();
			spassword=user.getPassword();
			if(sname.equals(name)&&spassword.equals(password)){
				model.addAttribute("name", name);
				model.addAttribute("password", password);
				return "sucess";
			}
			else {
				continue;
			}
		}
			return "fail";
	}
	*/

	
	@RequestMapping("testcookie")
	public String testcookie(HttpServletResponse response,@CookieValue(value="name1",required=false)String name1){
		System.out.println("cookie为:"+name1);
		Cookie coo=new Cookie("coo","this is the cookdddddie");
		coo.setMaxAge(1000);
//		HttpServletResponse response = null;
		response.addCookie(coo);
		return "fail";
	}
	
	@RequestMapping("out")
	public String outexcle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String fileName="excel文件";
        //填充projects数据
        ArrayList<User> projects=createData();
        ArrayList<Map<String,Object>> list=createExcelRecord(projects);
        String columnNames[]={"ID","用户名","密码"};//列名
        String keys[]    =     {"id","name","password"};//map中的key
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
    private ArrayList<User> createData() {
        // TODO Auto-generated method stub
        //自己实现
    	/*
    	 *自己创建的
    	User user1=new User();
    	user1.setId(1);
    	user1.setName("liming");
    	user1.setPassword("123");
    	User user2=new User();
    	user2.setId(1);
    	user2.setName("liming");
    	user2.setPassword("123");
    	ArrayList<User>list=new ArrayList<User>();
    	list.add(user1);
    	list.add(user2);
    	*/
    	/*
    	 * 查询数据库得到的
    	 */
    	ArrayList<User>list=new ArrayList<User>();
    	list=searchAllUserDao.searchalluserdao();
        return list;
    }
    private ArrayList<Map<String, Object>> createExcelRecord(ArrayList<User> projects) {
        ArrayList<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetname", "sheet1");
        listmap.add(map);
        User project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("id", project.getId());
            mapValue.put("name", project.getName());
            mapValue.put("password", project.getPassword());
            listmap.add(mapValue);
        }
        return listmap;
	}
    
    @SuppressWarnings({ "unchecked", "null" })
	@RequestMapping(value="yibujiazai")
    public void ybjz(User user,Model model,HttpServletResponse response,HttpServletRequest request,@RequestParam("yzm")String yzm){
    	String jsondata="";
    	int userid = -1;
    	ArrayList<User>list=new ArrayList<User>();
//    	System.out.println(user.getName());
//    	System.out.println(user.getPassword());
    	list=ld.logindao();
    	boolean isyzm=false;
    	boolean isuser=false;
    	String yzmsession=(String) request.getSession().getAttribute("yzmsession");
    //	String yzm=(String) request.getAttribute("yzm");
    	for (int i = 0; i < list.size(); i++) {
			if ((list.get(i).getName().equals(user.getName())&&list.get(i).getPassword().equals(user.getPassword()))) {
				isuser=true;
				userid=list.get(i).getId();
			}
			
		}
    	if (yzmsession.equalsIgnoreCase(yzm)) {
			isyzm=true;
		}
		
    	if (isyzm&&isuser) {
    		model.addAttribute("successusername", user.getName());
    		model.addAttribute("successuserid", userid);
    		model.addAttribute("yy", "hhh");
    		jsondata="{success:true,msg:\"哈哈\",userid:"+userid+"}";
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().write(jsondata);
				response.getWriter().close();
				response.getWriter().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("fffff");
				e.printStackTrace();
			}
		}
    	else {
    		if (!isyzm) {
    			jsondata="{success:false,msg:\"验证码不正确\"}";
			}
    		else if (!isuser) {
    			jsondata="{success:false,msg:\"用户名和密码不匹配\"}";
			}
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().write(jsondata);
				response.getWriter().close();
				response.getWriter().flush();
				//response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("fffff");
				e.printStackTrace();
				
			}
			
		}
 	
    }
    
    @RequestMapping(value="cxqx/{userid}")
    public String cxqx(Model model,@PathVariable int userid){
    	ArrayList<Category>list2=ld.searchCategories(userid);
		model.addAttribute("ca", list2);
		System.out.println(list2.get(0).getClassA());
		return "main";
    }
    
    @RequestMapping(value="cxqx")
    public String cxqx(Model model,HttpSession session){
    	if(session.getAttribute("successuserid")!=null){	
	    	int userid=(Integer) session.getAttribute("successuserid");
	    	ArrayList<Category>list2=ld.searchCategories(userid);
			model.addAttribute("ca", list2);
    	}
		return "main";
    	
    }
    
    @RequestMapping(value="yzm")
	public void yzm(HttpServletResponse response,HttpServletRequest request){
		BufferedImage bi=new BufferedImage(100,60,BufferedImage.TYPE_INT_RGB);
		Graphics g=bi.getGraphics();
		
		Random r=new Random();
	        Color c=new Color(200,150,255);
			g.setColor(c);   //设置背景颜色
			g.fill3DRect(0, 0, 100,60,true); //设置背景
			char[] ch="QWERTYUIPLKJHGFDSAZXCVBNM123456789".toCharArray();
		Font mfont=new Font("宋体",Font.BOLD,40); //定义字体样式  
		g.setFont(mfont);                   //设置字体  
		int len=ch.length,index;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<4;i++){
			index=r.nextInt(len);
			g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			//旋转文字
			Graphics2D g2d_word=(Graphics2D)g;  
	        AffineTransform trans=new AffineTransform();  
	        trans.rotate((45)*3.14/200,15*i+8,7); 
	        /*缩放文字*/  
            float scaleSize=r.nextFloat()+0.8f;  
            if(scaleSize>1f) scaleSize=1f;  
            trans.scale(scaleSize, scaleSize); 
	        g2d_word.setTransform(trans);
			g.drawString(ch[index]+"", 20+(i*20), 30);
			sb.append(ch[index]);
		}
		 // 添加噪点
        float yawpRate = 0.02f;// 噪声率
        int area = (int) (yawpRate * 100* 60);  //100代表图片的长,60代表宽
        int cr =  r.nextInt(255);
        int cg =  r.nextInt(255);
        int cb =  r.nextInt(255);
        for (int i = 0; i < area; i++) {
        
            int x = r.nextInt(100);
            int y = r.nextInt(60);        
            bi.setRGB(x, y, cr);
        }
        /*
      //绘制干扰线
        Random random = new Random();
        Graphics2D g2=bi.createGraphics();
        g2.setColor(new Color(200));
      //  g2.setColor(getRandColor(160, 200));// 设置线条的颜色
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(100 - 1);  
            int y = random.nextInt(60 - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
        */
		request.getSession().setAttribute("yzmsession", sb.toString());
		try {
			ImageIO.write(bi, "JPG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @RequestMapping(value="loginout")
    public String loginout(SessionStatus sessionStatus){
    	sessionStatus.setComplete();
    	
    	return "main";
    	
    }
}
