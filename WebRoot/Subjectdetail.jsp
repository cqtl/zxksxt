<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Subjectdetail.jsp' starting page</title>
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
	
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	
  </head>
  
  <body>
    <div class="col-sm-offset-2 col-sm-8">
		<table class="table table-bordered">
			<h4>题目详情</h4>
			
			<tbody>
				<tr>
					<td class="active col-sm-2">题目</td>
					<td class="col-sm-10">${sublist[0] }</td>
				</tr>
				<tr>
					<td class="active col-sm-2">答案选项</td>
					<td class="col-sm-10">${sublist[3] }</td>
				</tr>
				<tr>
					<td class="active col-sm-2">正确答案</td>
					<td class="col-sm-10">${sublist[4] }</td>
				</tr>
				<tr>
					<td class="active col-sm-2">所属学科</td>
					<td class="col-sm-10">${sublist[1] }</td>
				</tr>
				<tr>
					<td class="active col-sm-2">类型</td>
					<td class="col-sm-10">${sublist[2] }</td>
				</tr>
				<tr>
					<td class="active col-sm-2">提交人</td>
					<td class="col-sm-10">${sublist[5] }</td>
				</tr>
			</tbody>
		</table>
		
	</div>
  </body>
</html>
