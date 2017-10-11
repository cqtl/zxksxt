<%@page import="model.Singlesubject"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>My JSP 'xx.jsp' starting page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
	
	var countdown=2; 
	function settime(val) { 
		if (countdown == 0) { 
		//	val.removeAttribute("disabled");    
			val.value="免费获取验证码"; 
			return false; 
		} else { 
	//	val.setAttribute("disabled", true); 
		val.value="重新发送(" + countdown + ")"; 
		countdown--; 
	} 
	setTimeout(function() { //setTimeout()只执行一次代码，所以要循环调用
				settime(val); 
			},1000
		); //1000代表一秒
	} 
	
	var today=new Date();
	var h=today.getHours();
	var m=today.getMinutes()+20;
	var s=today.getSeconds();
			var th=0;
		var tm=0;
		var ts=0;
	function startTime() { 
		var today=new Date();
		var h1=today.getHours();
		var m1=today.getMinutes();
		var s1=today.getSeconds();
		var t1=(h1*60*60+m1*60+s1)-(h*60*60+m*60+s);
		if(t1<0||t1==0){
		t=(h*60*60+m*60+s)-(h1*60*60+m1*60+s1);
			th=parseInt(t/60/60);
			tm=parseInt((t-th*60*60)/60);
			ts=parseInt(t-(th*60*60)-(tm*60));
		
			
		}
		else{
			return false;
		}
		//document.getElementById('txt').innerHTML="剩余考试时间:"+th+"小时"+tm+"分"+ts+"秒"; 
		$("#txt").html("<font color='red' style='float:right;margin-right:20px;'>离考试结束还有:"+th+"小时"+tm+"分"+ts+"秒</font>");
	setTimeout( //setTimeout()只执行一次代码，所以要循环调用
				"startTime()",1000
		); //1000代表一秒
	} 
	
		$(document).ready(function(){
		$("#result").click(function(){
			//alert($("input[name='fff']").val());
			alert("value"+$("input:checked").val());
			alert($("input:checked").serialize());
			var data=decodeURIComponent($("input:checked").serialize(),true);   //防止中文乱码
			alert(data);
			$.ajax({
				type:"post",
				url:"checkanswer",
				data:{"name":data},
				success:function(msg){
					alert(msg);
				}
			});
		});
		

		/*
		//$("#bt1").click(function(){
			$.ajax({
				type:"post",
				url:"searchsingle",
				success:function(msg){
				alert(msg);
			
				var msg = eval("("+msg+")"); 
				alert(msg);
				$.each(msg,function(i,n){
					//alert("name:"+n.name+",password:"+n.password);
					$("#table").append("<tr><td>"+n.name+"<td><button>编辑</button></td><td><button>删除</button></td></td></tr>");
					//$("table").append("<td width='180' height='60'>sfsf</td>");
				});
				
				}
			});
		//});*/
		
		
		//$.ajax({
		//	type:"post",
		//	url:"searchanswer",
		//	data:"answerid=1",
		//	success:function(msg){
		//	alert(msg);
		//	}
		//});
		
	//	$("#table2").load("searchanswer","answerid=1");
		});
	</script>

  </head>
  
  <body onload="startTime()">
  <ol class="breadcrumb">
	<li><a href="#">考试系统</a></li>
	<li><a href="#"></a></li>
	<li class="active">考试</li>
</ol>
  <div id="txt"></div>
  <div class="form-horizontal">
    <br/>
    <button id="bt1" name="bt1">查询所以注册用户</button>
</div>
   
    <!-- 通过jstl标签+el表达式遍历后台传来的list -->
    <div class="col-sm-offset-2 col-sm-8">
    <table class="table table-striped">
	<caption><center><h1>考试卷</h1></center></caption>
	<thead>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			</tr>
	</thead>
	<tbody id="table">
		<c:forEach items="${single }" var="item" varStatus="status">
			
				<td>${item.subject }</td>
	
						<tr>
							<td>
								<input name="${item.s_id }" type="radio" value="${item.answera }"/>&nbsp;<label>A.&nbsp;${item.answera }</label><br>
								<input name="${item.s_id }" type="radio" value="${item.answerb }"/>&nbsp;<label>B.&nbsp;${item.answerb }</label><br>
								<input name="${item.s_id }" type="radio" value="${item.answerc }"/>&nbsp;<label>C.&nbsp;${item.answerc }</label><br>
								<input name="${item.s_id }" type="radio" value="${item.answerd }"/>&nbsp;<label>D.&nbsp;${item.answerd }</label>
							</td>
						</tr>

		</c:forEach>
		
	</tbody>
	
</table>
<center><button type="button" class="btn btn-primary btn-lg" id="result">提交答案</button></center>
<input name="fff" type="radio" value="ffdd"/>
    </div>
    
 <div id="dsd"></div>
  </body>
</html>
