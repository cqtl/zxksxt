<%@page import="model.Examination"%>
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
    
    <title>My JSP 'Examinationdetail.jsp' starting page</title>
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
	  
	  <div class="xiangdao">
		<ol class="breadcrumb">
			<li><a href="cxqx">首页</a></li>
			<li class="active">考试信息</li>
		</ol>
	  </div>
  
    <div class="bodystyle">
    <div class="col-sm-offset-2 col-sm-8">
	  <c:forEach items="${exam }" var="item" varStatus="status">
	  <h2 class="text-center">${item.shijuanname }</h2>
	  <!-- 作为分隔线 -->
	  <table class="table">
		  <thead>
		    <tr>
		      <th></th>
		    </tr>
		  </thead>
	  </table>
	  	<h4>&emsp;&emsp;${item.examtime }，网络教育学院将进行${item.examcourse }的考试。考试出题人：${item.ctrName }，
	  	改卷人：${item.gjrName }，考试时间：${item.howlong }分钟。
	  	请各位选了该课的同学，认真复习，做好考前准备。</h4></br>
	  	&emsp;&emsp;<h4>考试规则：</h4>
	  	&emsp;&emsp;一、自觉服从监考员等考试工作人员管理，不得以任何理由妨碍监考员等考试工作人员履行职责，不得扰乱考场及其他考试工作地点的秩序。</br>
	  	&emsp;&emsp;二、凭《准考证》和省级教育考试机构规定的其他证件，按规定时间和地点参加考试。应主动接受监考员按规定进行的身份验证和对随身物品等进行的必要检查。</br>
	  	&emsp;&emsp;三、在考场内须保持安静，不得吸烟，不得喧哗，不得交头接耳、左顾右盼、打手势、做暗号，不得夹带、旁窥、抄袭或有意让他人抄袭，不得传抄答案或交换试卷、答卷、草稿纸，不得传递文具、物品等，不得将试卷、答卷或草稿纸带出考场。</br>
	  	&emsp;&emsp;四、考试结束信号发出后，立即停笔，在监考员依序收齐答卷、试卷、草稿纸后，根据监考员指令依次退出考场。
	  </c:forEach>
	  </div>
  </div>
   <div class="footer">
	<p>在线考试系统</p>
	<p>Copyright 2017 All Rights Reserved</p>
   </div>
  </body>
</html>
