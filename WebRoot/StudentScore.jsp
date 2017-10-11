<%@ page import="model.StudentCourse" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'StudentScore.jsp' starting page</title>
    
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
	<link href="css/table.css" rel="stylesheet" type="text/css"/>
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
		$(document).ready(function(){
			
			var score=0;
			$("tbody tr td:last-child").each(function(i,n){
				score=$(n).text();
				if(score<60){
					$(n).parents("tr").attr("class","danger");
				}
			});
			
			$("#downloadscore").click(function(){
			var url="searchscore/download";
				window.open(url);
				
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
		<li class="active">查询成绩</li>
	</ol>
  </div>
  
  <div class="bodystyle">
  
  <div class="studetscore_main">

		<div class="col-sm-4">
			<h3 class="panel-title">${stuclass } &nbsp; ${stunumber } &nbsp;${stuname } &nbsp;成绩如下：<br/>(红底代表不及格)</h3>
		</div>
		<div class="col-sm-offset-6 col-sm-2">
			<button type="button" class="btn btn-success" id="downloadscore">下载成绩单</button>
		</div>

  
    <table class="table table-bordered">
		<caption>各门学科的成绩</caption>
		<thead>
			<tr>
				<th class="active" width="20%">序号</th>
				<th class="active" width="50%">学科</th>
				<th class="active" width="30%">成绩</th>
			
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${stuscore }" var="item" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${item.course }</td>
					<td>${item.score }</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
</div>
</div>
<div class="footer">
		       <p>在线考试系统</p>
		       <p>Copyright 2017 All Rights Reserved</p>
		</div>
</body>
</html>
