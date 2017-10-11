<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8"> 
	<title>Bootstrap 实例 - 基本表单</title>
	
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
		<link rel="stylesheet" type="text/css" href="css/default.css">
<link rel="stylesheet" type="text/css" href="css/fsbanner.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<link rel="stylesheet" href="css/bootstrap-datetimepicker.css">  
	<link href="css/footer.css" rel="stylesheet" type="text/css"/>
	
	
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-datetimepicker.js"></script>
	<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
	
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