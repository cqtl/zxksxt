<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showuser.jsp' starting page</title>
    
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
	<link href="css/table.css" rel="stylesheet" type="text/css"/>
	
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
		var pagenow=1;
		var pagenumber=0;
		$(document).ready(function(){
			$.ajax({
				type:"post",
				url:"manageuser/searchfenyeuser",
				data:"pagenow="+pagenow+"",
				success:function(msg){
					var message=JSON.parse(msg);
					$.each(message,function(i,n){
						if(n.name!=null){
						
							$("tbody").append("<tr><td>"+eval(i+1)+"</td><td>"+n.name+"</td><td><button name='delete' id='"+n.id+"' type='button' class='btn btn-danger'>删除</button></td><td><button name='edit' id='"+n.id+"' type='button' class='btn btn-warning'>编辑</button></td><td><button name='chakan' id='"+n.id+"' type='button' class='btn btn-warning'>查看</button></td></tr>");
						}
						//最后一条数据不是user而是pagenumber的值
						else{
							pagenumber=n.pagenumber;
							if(pagenumber>4){
								for(var i=1;i<5;i++){
									$(".bodystyle ul").append("<li><a href='javascript:void(0)'>"+i+"</a></li>");
								}
								$(".bodystyle ul").append("<li><a href='javascript:void(0)'>下一页&raquo;</a></li>");
							}
							else{
								for(var i=1;i<=pagenumber;i++){
									$(".bodystyle ul").append("<li><a  href='javascript:void(0)'>"+i+"</a></li>");
								}
								$(".bodystyle ul").append("<li><a href='javascript:void(0)'>下一页&raquo;</a></li>");
							}
						}
					
					});
				}
			});
			
			//点击搜索按钮
			$(".btn-success").click(function(){
				$("tbody").html("");
				$(".bodystyle ul").html("");
				$.ajax({
				type:"post",
				url:"manageuser/searchfenyeuser",
				data:$("form").serialize()+"&pagenow=1",
				success:function(msg){
					var message=JSON.parse(msg);
					$.each(message,function(i,n){
						if(n.name!=null){
							$("tbody").append("<tr><td>"+eval(i+1)+"</td><td>"+n.name+"</td><td><button name='delete' id='"+n.id+"' type='button' class='btn btn-danger'>删除</button></td><td><button name='edit' id='"+n.id+"' type='button' class='btn btn-warning'>编辑</button></td></tr>");
						}
						else{
							pagenumber=n.pagenumber;
							if(pagenumber>4){
								for(var i=1;i<5;i++){
									$(".bodystyle ul").append("<li><a href='javascript:void(0)'>"+i+"</a></li>");
								}
								$(".bodystyle ul").append("<li><a href='javascript:void(0)'>下一页&raquo;</a></li>");
							}
							else{
								for(var i=1;i<=pagenumber;i++){
									$(".bodystyle ul").append("<li><a  href='javascript:void(0)'>"+i+"</a></li>");
								}
								$(".bodystyle ul").append("<li><a href='javascript:void(0)'>下一页&raquo;</a></li>");
							}
						}
						
					});
				}
			});
		});
		
		//点击分页栏
			$("div").on("click","ul li a",function(){
				$("tbody").html("");
				$(".bodystyle ul").html("");
				if($(this).text().substring(1,4)=="上一页"){
					pagenow=Number(pagenow)-1;
				}
				else if($(this).text().substring(0,3)=="下一页"){
					pagenow=Number(pagenow)+1;
				}
				else{
					pagenow=$(this).text();
				}
				
				if(pagenow>1){
					$(".bodystyle ul").append("<li><a href='javascript:void(0)'>&laquo;上一页</a></li>");
				}

				if(pagenumber>4){
					var location=pagenow-2;
					if(location>0){
						if((location+4)>pagenumber){
							for(var i=pagenumber-3;i<=pagenumber;i++){
								$(".bodystyle ul").append("<li><a href='javascript:void(0)'>"+i+"</a></li>");
							}
						}
						else{
							for(var i=location;i<location+4;i++){
								$(".bodystyle ul").append("<li><a href='javascript:void(0)'>"+i+"</a></li>");
							}
						}
					}
					else{
						for(var i=1;i<1+4;i++){
							$(".bodystyle ul").append("<li><a href='javascript:void(0)'>"+i+"</a></li>");
						}
					}
				}
				else{
					for(var i=1;i<=pagenumber;i++){
						$(".bodystyle ul").append("<li><a  href='javascript:void(0)'>"+i+"</a></li>");
					}
				}
				if(pagenow!=pagenumber){
					$(".bodystyle ul").append("<li><a href='javascript:void(0)'>下一页&raquo;</a></li>");
				}
				
				$.ajax({
					type:"post",
					url:"manageuser/searchfenyeuser",
					data:$("form").serialize()+"&pagenow="+pagenow+"",
					success:function(msg){
						var message=JSON.parse(msg);
						$.each(message,function(i,n){
							if(n.name!=null){
								$("tbody").append("<tr><td>"+eval(i+1)+"</td><td>"+n.name+"</td><td><button name='delete' id='"+n.id+"' type='button' class='btn btn-danger'>删除</button></td><td><button name='edit' id='"+n.id+"' type='button' class='btn btn-warning'>编辑</button></td></tr>");
							}
							else{
								pagenumber=n.pagenumber;
							}
							
						});
					}
				});
			});
		
			//点击删除按钮
			$("tbody").on("click","tr td button[name='delete']",function(){
				var userid=0;
				userid=$(this).attr("id");
				$.ajax({
					type:"post",
					url:"manageuser/deleteuser",
					data:"userid="+userid,
					success:function(msg){
						var jmsg=JSON.parse(msg);
						alert(jmsg.message);
						window.location.href="manageuser/searchuser";
					}
				});
			});
			
			//点击编辑按钮
			$("tbody").on("click","tr td button[name='edit']",function(){
				var userid=0;
				userid=$(this).attr("id");
				window.location.href="manageuser/edituserpage ?userid="+userid;
			});
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
			<li class="active">用户管理</li>
		</ol>
	  </div>
	 
	 <div class="bodystyle">
	    <div class=" col-sm-offset-2 col-sm-8">
		    <form class="form-horizontal">
		    	
		    	<div class="form-group">
		    		<div class="col-sm-5">
			    		<label class="col-sm-4 control-label">用户名:</label>
					    <div class="col-sm-8">
					      <input name="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
					    </div>
		    		</div>
		    		<div class="col-sm-offset-2 col-sm-2">
					     <button type="button" class="btn btn-success">搜索</button>
		    		</div>
			  	</div>
			  	
			</form>

			<table class="table table-hover table-bordered table-striped table-responsive">
				<caption>用户信息表</caption>
				<thead>
					<tr>
						<th class="active" width="10%">序号</th>
						<th class="active" width="50%">用户名</th>
						<th class="active" width="20%">删除</th>
						<th class="active" width="10%">编辑</th>
						<th class="active" width="10%">查看</th>
					</tr>
				</thead>
				<tbody>
						
				</tbody>
			</table>
			
		<ul class="pagination">
			
	    </ul>
	    
	</div>
   </div>
	
	<div class="footer">
	 <p>在线考试系统</p>
	 <p>Copyright 2017 All Rights Reserved</p>
	</div>
  </body>
</html>
