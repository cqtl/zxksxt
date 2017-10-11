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
	<title>网络教育学院在线考试</title>
	
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
	
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script language="javascript" type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
	
			function reloadCode(){
				var time=new Date().getTime();
				document.getElementById("pyzm").src="yzm?t="+time;  //让缓存内容发生变化
				
			}
			
		$(document).ready(function(){
			$("#login").click(function(){
			if(($("#name").val()=="")&&($("#password").val()=="")){
				$("#warn").show();
				$("#warn").html("<span class='glyphicon glyphicon-minus-sign' style='color:#FF0000'></span>&nbsp;&nbsp;请输入用户名和密码");
				return false;
			}
			else if(($("#name").val()=="")&&($("#password").val()!="")){
				$("#warn").show();
				$("#warn").html("<span class='glyphicon glyphicon-minus-sign' style='color:#FF0000'></span>&nbsp;&nbsp;请输入用户名");
				return false;
			}
			else if(($("#name").val()!="")&&($("#password").val()=="")){
				$("#warn").show();
				$("#warn").html("<span class='glyphicon glyphicon-minus-sign' style='color:#FF0000'></span>&nbsp;&nbsp;请输入密码");
				return false;
			}
			else{
				var form=$("#inform").serialize();
				var url = "yibujiazai";
				$.post(url,form,function callback(data){  
					var resp = eval("("+data+")"); 
					if(resp.success){
					//	window.location.href="cxqx/"+resp.userid;
						window.location.href="cxqx";
					}
					else{ 
						$("#warn").show();
						$("#warn").html("<span class='glyphicon glyphicon-minus-sign' style='color:#FF0000'></span>&nbsp;&nbsp;"+resp.msg+"");
						reloadCode();
						$("#yzm").val("");
					}	
					});
				return false;
				}
			});
		});
	</script>

	<link href="css/style.css" rel="stylesheet" type="text/css"/>

  </head>
  
  <body>
  <div class="form-horizontal col-sm-12" id="header">
  	<div class="col-sm-offset-1">
  		<a href="index.jsp"><img alt="title" src="pic/title.png" width="200" height="60"></a>
  	</div>
  </div>
  <div class="col-sm-12" id="container">
    <form id="inform" class="form-horizontal" role="form" action="" method="post">  <!-- role属性给读屏软件用 -->
    <div class="col-sm-offset-7 col-sm-5" style="background-color:#FFECEC">
    <div class="form-group">
    	<div class="col-sm-offset-4">
    		<h2>登录</h2>
    		<div class="alert-danger col-sm-7" style="display:none" id="warn"></div>
    	</div>
    	
    </div>
    
	<div class="form-group">
		<!--  <label for="name" class="col-sm-4 control-label">名字</label>   -->   <!-- for属性表示规定label标签与哪个表单元素绑定 -->
		<div class="col-sm-offset-3 col-sm-6 input-group">
		<span class="input-group-addon">
			<span class="glyphicon glyphicon-user"></span>
		</span>
			<input type="text" class="form-control" id="name" name="name" placeholder="请输入名字">
		</div>
	</div>
	<div class="form-group">
	<!-- 
		<label for="password" class="col-sm-4 control-label">密码</label>
	-->
		<div class="col-sm-offset-3 col-sm-6 input-group">
		<span class="input-group-addon">
		    <span class="glyphicon glyphicon glyphicon-lock"></span>
		</span>
			<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
		</div>
		
		
	</div>
	<!--  带头像的输入框的例子
	<div class="form-group">
		<div class="col-sm-offset-2 input-group">
						
			<span class="input-group-addon">
				<span class="glyphicon glyphicon-user"></span>
			</span>
			<input type="text" class="form-control">
		</div>
	</div>
	-->
	
	<div class="form-group">
	<div class="col-sm-offset-3 col-sm-4">
	<input type="text" class="form-control" id="yzm" name="yzm" placeholder="请输入验证码">
	</div>
	<div class="col-sm-4">
	<a href="javascript:reloadCode();"><img alt="验证码" id="pyzm" src="yzm" width="70" height="40"/></a>
    <a href="javascript:reloadCode();">看不清</a>
    </div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<div class="checkbox">
				<label>
					<input type="checkbox"> 请记住我
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-4">
		<div class="form-group">
		<div class="col-sm-3">
			<button type="submit" id="login" class="btn btn-info btn-lg">登录</button>
			</div>
			<div class=" col-sm-3">
			<button type="button" class="btn btn-default btn-lg">取消</button>
			</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-8">
			<a href="regist.jsp?from=login" class="" target="_blank" tabindex="3">免费注册</a>
		</div>
	</div>
	</div>
</form>

</div>

  </body>
</html>
