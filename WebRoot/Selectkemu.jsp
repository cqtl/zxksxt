<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Selectkemu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  	<div class="container col-md-6" style="margin-top:50px">
  	
		<div class="row" >
			<div class="col-md-12 col-md-offset-1">
				<c:forEach items="${ca }" var="item" varStatus="status" begin="0" end="2">
					<div class="col-md-2">
						<a href="${item.link }" class="${item.classA }" style="font-size: 30px; height:100px; width:100px;">
							<p><span class="${item.classB }"></span></p> <p style="font-size: 18px;">${item.modulename }</p>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
  </body>
</html>
