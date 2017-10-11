<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'answerforrefrence.jsp' starting page</title>
    
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
	<link href="css/table.css" rel="stylesheet" type="text/css"/>
	
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
				type:"post",
				url:"gaijuan/refrence",
				data:"shijuanname="+$("#shijuanname").val(),
				success: function(msg){
     				var jmsg=JSON.parse(msg);
     				$.each(jmsg,function(i,n){
     					$("tbody").append("<tr><td>"+n[0]+"</td><td>"+n[1]+"</td><td>"+n[2]+"</td></tr>");
     				});
   				}
			});
		});
	</script>
	
  </head>
  
  <body>
  <input type="text" value="${shijuanname }" name="shijuanname" id="shijuanname" class="sr-only"/>
    <table class="table table-bordered table-hover table-striped">
  <thead>
    <tr class="success">
      <th width="50%">题目</th>
      <th width="40%">参考答案</th>
      <th width="10%">分值</th>
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table>
  </body>
</html>
