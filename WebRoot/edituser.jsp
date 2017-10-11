<%@ page import="model.Role" %>
<%@ page import="model.User" %>
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
    
    <title>My JSP 'editpermission.jsp' starting page</title>
    
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
	<link href="css/table.css" rel="stylesheet" type="text/css"/>
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
		//查询该用户所拥有的角色
			var rname=[];    //该用户拥有的角色
			$.ajax({
				type:"post",
				url:"manageuser/searchuserrole",
				data:"yhid="+$("#yhid").val(),
				success:function(msg){
					var jmsg=JSON.parse(msg);
					var userrole="";
					$.each(jmsg,function(i,n){
						userrole=userrole+n.chinesename+",";
						rname.push(n.chinesename);
					});
					userrole=userrole.substring(0,userrole.length-1);
					$("#jsmc").val(userrole);
					$(".modal-body input").each(function(i,n){
						for(var j=0;j<rname.length;j++){
							if(rname[j]==$(n).val()){
								$(n).attr({checked:"checked"});        //将已有权限设为已选
							}
						}
					});
				}
			});
			$("#jsmc").click(function(){
				$("#identifier").modal("show");
			});
			//点击模态框的确定按钮
			$("#modeledituser").click(function(){
				var userrole="";
				$("#identifier input:checked").each(function(){
					userrole=userrole+$(this).val()+",";
				});
				userrole=userrole.substring(0,userrole.length-1);
				$("#jsmc").val(userrole);
			});
			
			//点击取消按钮
			$("#quxiao").click(function(){
				window.location.href="manageuser/searchuser";
			});
		//点击确定按钮
			$("#queding").click(function(){
				$.ajax({
					type:"post",
					url:"manageuser/edituser",
					data:$("form").serialize(),
					success:function(msg){
						var jmsg=JSON.parse(msg);
						alert(jmsg.message);
						window.location.href="manageuser/searchuser";
					}
				});
			});
		});
	</script>

  </head>
  
  <body>
    <nav class="navbar navbar-default navbar-static-top" role="navigation" > 
  <div class="form-horizontal col-sm-12 header" id="">
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
		<li><a href="manageuser/searchuser">用户管理</a></li>
		<li class="active">编辑用户</li>
	</ol>
  </div>
  
  <div class="bodystyle">
	  <div class="col-sm-offset-2 col-sm-8">
	    <form class="form-horizontal" role="form">
	    
			  <div class="form-group">
			    <label for="yhmc" class="col-sm-offset-1 col-sm-2 control-label">用户名称</label>
			    <div class="col-sm-6">
			    	<c:forEach items="${ulist }" var="item" varStatus="status">
			    		<input type="text" value="${item.id }" name="yhid" id="yhid" class="sr-only"/>
						<input type="text" class="form-control" value="${item.name }" name="yhmc" id="yhmc" placeholder="请输入用户名称">
					</c:forEach>
			      
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="yhbs" class="col-sm-offset-1 col-sm-2 control-label">用户标识</label>
			    <div class="col-sm-6">
			    	<c:forEach items="${ulist }" var="item" varStatus="status">
						<input type="text" class="form-control" value="${item.account }" name="yhbs" id="yhbs" placeholder="请输入用户标识">
					</c:forEach>
	
			    </div>
			  </div>
			  
			   <div class="form-group">
			    <label for="jsmc" class="col-sm-offset-1 col-sm-2 control-label">赋予角色</label>
			    <div class="col-sm-6">
			    	<input type="text" class="form-control" value="" name="jsmc" id="jsmc" placeholder="请输入角色名称">
	
			    </div>
			  </div>
			  
			  </br>
			  <div class="form-group">
			    <div class="col-sm-offset-3 col-sm-1">
			      <button id="quxiao" type="button" class="btn btn-default">取消</button>
			    </div>
			    <div class="col-sm-1">
			      <button id="queding" type="button" class="btn btn-warning">确定</button>
			    </div>
			  </div>
			  
			</form>
		</div>
		
		<div id="identifier" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">请选择角色</h4>
		            </div>
		            <div class="modal-body">
		            
		            <c:forEach items="${rlist }" var="item" varStatus="status">
						<input type='checkbox' class="col-sm-1" id="${item.id }" value="${item.chinesename }"><label class="col-sm-11">${item.chinesename }</label></br>
					</c:forEach>
		            	
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button id="modeledituser" type="button" class="btn btn-primary" data-dismiss="modal" data-dismiss="modal">确定</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
	</div>
	<div class="footer">
	 <p>在线考试系统</p>
	 <p>Copyright 2017 All Rights Reserved</p>
	</div>
	
  </body>
</html>
