<%@page import="model.Answers"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Kaoshi.jsp' starting page</title>
    
      <!-- 浏览器上的小图标 -->
	<link rel="icon" href="pic/myfavicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="pic/myfavicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="pic/myfavicon.ico" type="image/x-icon" />
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<link rel="stylesheet" href="css/bootstrap.min.css"> 
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
	<link href="css/footer.css" rel="stylesheet" type="text/css"/>
	
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
		if($("a[id='user']").text().trim()!=""){
			$("#lg_re").hide();
		}
		else{
			$("#duser").hide();
		}
		});
	</script>
	
	<script type="text/javascript">

		var h;
		var m;
		var s;
		var th=0;
		var tm=0;
		var ts=0;
		var examinationtime;
		var startexaminationtime;
		$("document").ready(function(){
			examinationtime =$("#timetxt").text().split("&")[0];
			startexaminationtime=$("#timetxt").text().split("&")[1];
			startexaminationtime = startexaminationtime.replace(/-/g,"/");
			var date=new Date(startexaminationtime);
			h=date.getHours();
			m=date.getMinutes();
			s=date.getSeconds();
			m=m+parseInt(examinationtime);
		});
		function startTime() {
			var today=new Date();
			var h1=today.getHours();
			var m1=today.getMinutes();
			var s1=today.getSeconds();
			var t1=(h1*60*60+m1*60+s1)-(h*60*60+m*60+s);
			if(t1<0||t1==0){
			var t=(h*60*60+m*60+s)-(h1*60*60+m1*60+s1);
				th=parseInt(t/60/60);
				tm=parseInt((t-th*60*60)/60);
				ts=parseInt(t-(th*60*60)-(tm*60));
			}
			else{
				return false;
			}
			//document.getElementById('timetxt').innerHTML="剩余考试时间:"+th+"小时"+tm+"分"+ts+"秒"; 
			$("#timetxt").html("<font color='red' style='float:right;margin-right:20px;'>离考试结束还有:"+th+"小时"+tm+"分"+ts+"秒</font>");
		setTimeout( //setTimeout()只执行一次代码，所以要循环调用
					"startTime()",1000
			); //1000代表一秒
		} 
		
		$(document).ready(function(){		
			startTime();
			
			//选择题中10个选项在初始化时都有，所以判断是否选项有值，没有值则删除。
		$("tbody tr td div").each(function(i,n){
			if(($(n).text().length)<6){
				$(n).remove();
			}
		});
		
		//将没有值的tbody，即没有这种类型的题删掉
		$("tbody").each(function(i,n){
			if(($(n).text().trim().length)<1){
				$(n).remove();
			}
		});
		
		var num=["一、","二、","三、","四、"];
		var a=0;
		$("thead").each(function(i,n){
			//将没有值的thead，即没有这种类型的题删掉
			if(($(n).text().trim().length)<1){
				$(n).remove();
			}
			else{
				$(n).find("h3").prepend(num[a]);
				a=a+1;
			}
		});
		
		$("#tiankongb label").each(function(i,n){
			var data=$(n).text().split("&");
			var s=data[0];
			var datavalue=data[1];
			for(var ii=0;ii<s;ii++){
				$(n).parent("td").prepend("<tr><td><label class='control-label col-sm-2'>"+eval(s-ii)+".</label><div class='col-sm-10'><input name='"+datavalue+"' type='text' class='form-control' value=''/></div></td></tr>");
			}
		});
		
		//提交答案
		$("#result").click(function(){
		
			var answer=$("form").serialize();
			var dataid="";
			$("input.sr-only").each(function(){
				dataid=dataid+$(this).val()+",";
			});
			dataid=dataid.substring(0,dataid.length-1);
			$.ajax({
				type:"post",
				url:"kaoshi/tijiao",
				data:answer+"&subid="+dataid+"&shijuanname="+$("#shijuanname").text()+"",
				success:function(msg){
					var jmsg=JSON.parse(msg);
					alert(jmsg.message+",将为你跳转到主页");
					window.location.href="cxqx?backurl="+window.location.href;
				}
			});
		});
		
		 });
	</script>
	
  </head>
  
  <body>
  <nav class="navbar navbar-default navbar-static-top" role="navigation" > 
  <div class="form-horizontal col-sm-12 header" id="">
  	<div class="navbar-header col-sm-offset-1 col-sm-5 headerimg">
  		<a href="index.jsp"><img alt="title" src="pic/logo.png" width="200" height="60"></a>
  	</div>
  	<div class="col-sm-6 headeruser">
  	<div id="duser">
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a id="user" href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <span class="glyphicon glyphicon-user"></span> ${sessionScope.successusername } <b class="caret"></b>
                  <div id="userid" style="display:none;">${sessionScope.successuserid }</div>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">个人中心</a></li>
                    <li><a href="#">账号设置</a></li>
                    <li><a href="loginout">退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
  	<div id="lg_re">
        <ul class="nav navbar-nav navbar-right"> 
            <li><a href="regist.jsp"><span class="glyphicon glyphicon-user"></span> 注册</a></li> 
            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li> 
        </ul> 
     </div>
     </div>
  </div>
  </nav>
  
  <div class="xiangdao">
	<ol class="breadcrumb">
		<li><a href="cxqx">首页</a></li>
		<li class="active">考试答题</li>
	</ol>
  </div>
  
  <div class="bodystyle">
  <div id="timetxt" class="examinationtime_style">${examinationtime }&${startexaminationtime }</div>
  
   
    <!-- 通过jstl标签+el表达式遍历后台传来的list -->
    <div class="col-sm-offset-2 col-sm-8">
    <form>
    	<table class="table table-striped">
		<caption><center><h1 id="shijuanname">${examinationname }</h1></center></caption>
		<div>
			<thead>
				<tr>
					<th><h3>${danxuantimu }</h3></th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${danxuan }" var="item" varStatus="status">
					
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">(分值：${item[2] }分)</span></td></tr>
						<tr><td><div><input name="${item[0] }" type="radio" value="1"/>&nbsp;&nbsp;A. ${danxuanans[status.count-1].xuanxiang1 }</br></div>
								<div><input name="${item[0] }" type="radio" value="2"/>&nbsp;&nbsp;B. ${danxuanans[status.count-1].xuanxiang2 }</br></div>
								<div><input name="${item[0] }" type="radio" value="3"/>&nbsp;&nbsp;C. ${danxuanans[status.count-1].xuanxiang3 }</br></div>
								<div><input name="${item[0] }" type="radio" value="4"/>&nbsp;&nbsp;D. ${danxuanans[status.count-1].xuanxiang4 }</br></div>
								<div><input name="${item[0] }" type="radio" value="5"/>&nbsp;&nbsp;E. ${danxuanans[status.count-1].xuanxiang5 }</br></div>
								<div><input name="${item[0] }" type="radio" value="6"/>&nbsp;&nbsp;F. ${danxuanans[status.count-1].xuanxiang6 }</br></div>
								<div><input name="${item[0] }" type="radio" value="7"/>&nbsp;&nbsp;G. ${danxuanans[status.count-1].xuanxiang7 }</br></div>
								<div><input name="${item[0] }" type="radio" value="8"/>&nbsp;&nbsp;H. ${danxuanans[status.count-1].xuanxiang8 }</br></div>
								<div><input name="${item[0] }" type="radio" value="9"/>&nbsp;&nbsp;I. ${danxuanans[status.count-1].xuanxiang9 }</br></div>
								<div><input name="${item[0] }" type="radio" value="10"/>&nbsp;&nbsp;J. ${danxuanans[status.count-1].xuanxiang10 }</br></div>
						</td></tr>
				</c:forEach>
		
			</tbody>
		</div>

		<div>
			<thead>
				<tr>
					<th><h3>${duoxuantimu }</h3></th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${duoxuan }" var="item" varStatus="status">
					
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">(分值：${item[2] }分)</span></td></tr>
						<tr><td><div><input name="${item[0] }" type="checkbox" value="1"/>&nbsp;&nbsp;A. ${duoxuanans[status.count-1].xuanxiang1 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="2"/>&nbsp;&nbsp;B. ${duoxuanans[status.count-1].xuanxiang2 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="3"/>&nbsp;&nbsp;C. ${duoxuanans[status.count-1].xuanxiang3 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="4"/>&nbsp;&nbsp;D. ${duoxuanans[status.count-1].xuanxiang4 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="5"/>&nbsp;&nbsp;E. ${duoxuanans[status.count-1].xuanxiang5 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="6"/>&nbsp;&nbsp;F. ${duoxuanans[status.count-1].xuanxiang6 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="7"/>&nbsp;&nbsp;G. ${duoxuanans[status.count-1].xuanxiang7 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="8"/>&nbsp;&nbsp;H. ${duoxuanans[status.count-1].xuanxiang8 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="9"/>&nbsp;&nbsp;I. ${duoxuanans[status.count-1].xuanxiang9 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value="10"/>&nbsp;&nbsp;J. ${duoxuanans[status.count-1].xuanxiang10 }</br></div>
						</td></tr>
				</c:forEach>
			</tbody>
		</div>
	
		<div id="tiankong">
			<thead>
			<tr>
				<th><h3>${tiankongtimu }</h3></th>
				</tr>
			</thead>
			<tbody id="tiankongb">
				<c:forEach items="${tiankong }" var="item" varStatus="status">
					
					<!-- input的值是在提交时用的，是题目的id -->
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">(分值：${item[2] }分)</span></td></tr>
						
						<tr><td>
							<label class="sr-only" for="name">${tiankongans[status.count-1].xuanxiang }&${item[0] }</label>  <!--填空题选项的个数 和填空题目的id,后台是以id去查找的答案 class="sr-only" label隐藏 -->
						</td></tr>
						
				</c:forEach>
			</tbody>
		</div>
		
		<div>
			<thead>
				<tr>
					<th><h3>${jiandatimu }</h3></th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${jianda }" var="item" varStatus="status">
					
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">(分值：${item[2] }分)</span></td></tr>
						<tr><td>
						<textarea name="${item[0] }" class="form-control" rows="3"></textarea>
						</td></tr>
				</c:forEach>
			</tbody>
		</div>
	</table>
	</form>
	<center><button type="button" class="btn btn-primary btn-lg" id="result">提交试卷</button></center>
   </div>
   </div>
   
</body>
</html>
