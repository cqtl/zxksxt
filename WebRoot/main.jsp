<%@page import="model.Category"%>
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
    
    <meta charset="utf-8"> 
	<title>考试系统首页</title>
	
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

	<link rel="stylesheet" type="text/css" href="css/fsbanner.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">   
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
	
<script type="text/javascript" src="js/fsbanner.js"></script>
<script type="text/javascript">
	$(function(){
		$('.ex[name=4] .fsbanner').fsBanner({
			trigger: 'mouse',
		});
	});
	
	$(document).ready(function(){
		$.ajax({
			type:"post",
			url:"searchexamination/search",
			success:function(msg){
				var examlist=JSON.parse(msg);
				$.each(examlist,function(i,n){
					$("#unexam").append("<li><a href='searchexamination/examinationdetail?exam="+n.id+"'>"+n.shijuanname+"</a></li>");

				});
			}
		});
	});
</script>
	<script type="text/javascript" src="js/my/main.js"></script>
	<style type="text/css">
	.ex{
		width: 87%;
		margin:0 auto;
	}
	.fsbanner{margin-top: 0px;}
</style>
	
	</head>
  
  <body>
	  <nav class="navbar navbar-default navbar-static-top" role="navigation" > 
	  <div class="form-horizontal col-sm-12 header" id="header">
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
	  
<div class="bodystyle">

<!--  可以直接得到session的值
    欢迎你:${sessionScope.successusername }
   欢迎你:${sessionScope.yy }
   -->
   
<article class="htmleaf-container">

	<div class='ex' name='4'>
		
		<div class='fsbanner' style="height:330px;">
			<div style='background-image:url(pic/img/bj1.jpg)'>
				<span class='name'>新生报到</span>
			</div>
			<div style='background-image:url(pic/img/bj2.jpg)'>
				<span class='name'>新生班会</span>
			</div>
			<div style='background-image:url(pic/img/bj3.jpg)'>
				<span class='name'>全神贯注</span>
			</div>
			<div style='background-image:url(pic/img/bj4.jpg)'>
				<span class='name'></span>
			</div>
			<div style='background-image:url(pic/img/bj5.jpg)'>
				<span class='name'>授课教师返校活动</span>
			</div>
		</div>

	</div>
</article>



<!--         //////////////           -->
  <div class="container col-sm-6" style="margin-top:50px">
		<div class="row" >
			<div class="col-sm-9 col-sm-offset-1">
				<c:forEach items="${ca }" var="item" varStatus="status">
					<div class="col-sm-4">
						<a href="${item.link }" class="${item.classA }" style="font-size: 30px; height:100px; width:130px;">
							<p><span class="${item.classB }"></span></p> <p style="font-size: 18px;">${item.modulename }</p>
						</a>
						</br></br>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="col-sm-6" style="margin-top:50px">
		<div class="col-sm-11">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">
						近期考试信息
					</h3>
				</div>
				<div class="panel-body" id="unexam">

				</div>
			</div>
		</div>
	</div>
	
</div>	

		<div class="footer">
		       <p>在线考试系统</p>
		       <p>Copyright 2017 All Rights Reserved</p>
		</div>
</body>
</html>