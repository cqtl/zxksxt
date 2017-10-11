<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectcourse.jsp' starting page</title>
    
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
	
	<script type="text/javascript">
		$(document).ready(function(){
			var url="selectcourse/searchcourse";
			$.post(url,function callback(data){
				var msg= JSON.parse(data);
				$.each(msg,function(i,n){
					$("tbody").append("<tr><td><input name='course' type='checkbox' value='"+n+"'></td><td>"+eval(i+1)+"</td><td>"+n+"</td></tr>");
				});
			});
			
			$("#tijiao").click(function(){
				var course=$("form").serialize();
				var url="selectcourse/tijiaocourse";
				$.post(url,course,function callback(data){
					var msg=JSON.parse(data);
					alert(msg.message);
				});
			});
		});
	</script>
	<style type="text/css">
		td,th{
			text-align:center;
		}
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
  
  <div class="xiangdao">
	<ol class="breadcrumb">
		<li><a href="cxqx">首页</a></li>
		<li class="active">选课</li>
	</ol>
  </div>
  
  <div class="bodystyle">
  <div class="col-sm-offset-2 col-sm-8">
  <form>
    	<table class="table table-hover table-bordered table-striped table-responsive">
			<thead>
				<tr class="alert-info">
					<th width="10%">选择</th>
					<th width="10%">序号</th>
					<th width="25%">学科</th>
				</tr>
			</thead>
			<tbody>
					
			</tbody>
		</table>
	</form>
		<button id="tijiao" type="button" class="btn btn-info col-sm-1">提交</button>
	</div>
	</div>
<div class="footer">
		       <p>在线考试系统</p>
		       <p>Copyright 2017 All Rights Reserved</p>
		</div>
</body>
</html>
