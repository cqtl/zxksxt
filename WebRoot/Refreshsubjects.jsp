<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Refreshsubjects.jsp' starting page</title>
    
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
		
		//填空题答案个数增减
			$("#taddanswer").click(function(){
				if(eval($("#tanswer div div").size()>9)){
					alert("不能再增加了！");
				}
				else{
					$("#tanswer").append("<div class='col-sm-6'><label for='lastname' class='control-label col-sm-3'>答案"+eval($("#tanswer div div").size()+1)+":</label><div class='col-sm-9'><input type='text' name='answer' class='form-control' id='' placeholder=''></div></div>");
				}
			});
			
			$("#tminusanswer").click(function(){
				if(eval($("#tanswer div div").size()==1)){
					alert("不能再减少了，至少要有一个答案！");
				}
				else{
					$("div").remove("#tanswer div:last");
					$("div").remove("#tanswer div:last");
				}
			});
			//单项选择题答案个数增减
			$("#saddanswer").click(function(){
				if(eval($("#sanswer div div").size()>9)){
					alert("不能再增加了！");
				}
				else{
					$("#sanswer").append("<div class='col-sm-6'><label for='lastname' class='control-label col-sm-3'>选项"+eval($("#sanswer div div").size()+1)+":</label><div class='col-sm-9'><input type='text' name='xuanxiang"+eval($("#sanswer div div").size()+1)+"' class='form-control' id='' placeholder=''></div></div>");
					$("#sselect").append("<option value='"+$("#sanswer div div").size()+"'>选项"+$("#sanswer div div").size()+"</option>");
				}
			});
			
			$("#sminusanswer").click(function(){
				if(eval($("#sanswer div div").size()==1)){
					alert("不能再减少了，至少要有一个选项！");
				}
				else{
					$("div").remove("#sanswer div:last");
					$("div").remove("#sanswer div:last");
					$("option").remove("#sselect option:last");
				}
			});
			
			//多项选择题选项个数增减
			$("#maddxuanxiang").click(function(){
				if(eval($("#mxuanxiang div div").size()>9)){
					alert("不能再增加了");
				}
				else{
					$("#mxuanxiang").append("<div class='col-sm-6'><label for='lastname' class='control-label col-sm-3'>选项"+eval($("#mxuanxiang div div").size()+1)+":</label><div class='col-sm-9'><input type='text' name='xuanxiang"+eval($("#mxuanxiang div div").size()+1)+"' class='form-control' id='' placeholder=''></div></div>");
					$("#identifier .modal-body").append("<div><input type='checkbox' class='col-sm-1' value='"+$("#mxuanxiang div div").size()+"'><label class='col-sm-11'>选项"+$("#mxuanxiang div div").size()+"</label></br></div>");
				}
			});
			
			$("#mminusxuanxiang").click(function(){
				if(eval($("#mxuanxiang div div").size()==1)){
					alert("不能再减少了，至少要有一个选项！");
				}
				else{
					$("div").remove("#mxuanxiang div:last");
					$("div").remove("#mxuanxiang div:last");
					$("div").remove("#identifier .modal-body div:last");
				}
			});
			//多项选择题通模态框答案显示
			$("#addmodel").click(function(){
				$("#identifier").modal("show");
			});
			$("#modeladdanswer").click(function(){
				var answer="";
				$("#identifier input:checked").each(function(){
					answer=answer+$(this).val()+",";
				});
				answer=answer.substring(0,answer.length-1);
				$("#addmodel").val(answer);
			});
			
			//简答题提交
			$("#jbutton").click(function(){
				var jform=$("#jform").serialize();
				jform=jform+"&leixing=jdt"+"&xuanxiang=1";
				var url="refreshsubjects/addsubjects";
				$.post(url,jform,function callback(data){
					var msg = eval("("+data+")");  //将string转换成json
					if(msg.status){
						alert(msg.message);
						$("#jform textarea").val("");
						$("#jform select").val("");
					}
				});
			});
			
			//填空题提交
			$("#tbutton").click(function(){
				var tform=$("#tform").serialize();
				tform=tform+"&leixing=tkt"+"&xuanxiang="+$('#tanswer div div').size();
				var url="refreshsubjects/addsubjects";
				$.post(url,tform,function callback(data){
					var msg = eval("("+data+")");  //将string转换成json
					if(msg.status){
						alert(msg.message);
						$("#tform textarea").val("");
						$("#tform select").val("");
						$("#tform input").val("");
					}
				});
			});
			//单项选择题提交
			$("#sbutton").click(function(){
				var sform=$("#sform").serialize();
				sform=sform+"&leixing=single"+"&xuanxiang="+$('#sanswer div div').size();
				var url="refreshsubjects/addsubjects";
				$.post(url,sform,function callback(data){
					var msg = eval("("+data+")");  //将string转换成json
					if(msg.status){
						alert(msg.message);
						$("#sform textarea").val("");
						$("#sform select").val("");
						$("#sform input").val("");
					}
				});
			});
			//多项选择题提交
			$("#mbutton").click(function(){
				var mform=$("#mform").serialize();
				mform=mform+"&leixing=many"+"&xuanxiang="+$('#mxuanxiang div div').size();
				var url="refreshsubjects/addsubjects";
				$.post(url,mform,function callback(data){
					var msg = eval("("+data+")");  //将string转换成json
					if(msg.status){
						alert(msg.message);
						$("#mform textarea").val("");
						$("#mform select").val("");
						$("#mform input").val("");
					}
				});
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
		<li class="active">更新题库</li>
	</ol>
  </div>
  
  <div class="bodystyle">
    <ul id="myTab" class="nav nav-tabs">
	<li class="active">
		<a href="#tkt" data-toggle="tab">
			 填空题
		</a>
	</li>
	
	<li class="dropdown">
		<a href="" id="myTabDrop1" class="dropdown-toggle" 
		   data-toggle="dropdown">选择题
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
			<li><a href="#single" tabindex="-1" data-toggle="tab">单选题</a></li>
			<li><a href="#many" tabindex="-1" data-toggle="tab">多选题</a></li>
		</ul>
	</li>
	
	<li><a href="#jdt" data-toggle="tab">简答题</a></li>
</ul>
<div id="myTabContent" class="tab-content">
<!-- 填空题 -->
	<div class="tab-pane fade in active col-sm-offset-2  col-sm-8" id="tkt">
		<form role="form" id="tform">
			<div class="form-group col-sm-12">
				<label for="name" class="col-sm-offset-5 col-sm-7"><h2>题目</h2></label>
				<textarea name="subject" class="form-control" rows="3"></textarea>
			</div>
			
			<div id="tanswer" class="form-group col-sm-11">
				<label for="name" class="col-sm-offset-4 col-sm-7">
					<button id="taddanswer" type="button" style="top:20px;" class="btn btn-warning col-sm-2">
         		 		&nbsp;<span class="glyphicon glyphicon-plus"></span>&nbsp;
        			</button>
					<h2 class="col-sm-3">答案</h2>
					<button id="tminusanswer" type="button" style="top:20px;" class="btn btn-warning col-sm-2">
						&nbsp;<span class="glyphicon glyphicon-minus"></span>&nbsp;
					</button>
				</label>
				<div class="col-sm-6">
					<label for="lastname" class="control-label col-sm-3">答案1:</label>
					<div class="col-sm-9">
						<input name="answer" type="text" class="form-control" id="" placeholder="">
					</div>
				</div>
				
			</div>
			
			<div class="form-group" style="padding-top:20px;">
				<label for="name" class="col-sm-offset-3 col-sm-2">题目类型:</label>
				<select name="attribute" class="col-sm-3" style="height:30px;">
					<option value="">--请选择题目类型--</option>
				    <option value="操作系统">操作系统</option>
				    <option value="数学">数学</option>
				    <option value="英语">英语</option>
				    <option value="计算机网络">计算机网络</option>
				</select>
			</div>
			
			<div class="col-sm-offset-5 col-sm-4" style="top:30px;">
				<button id="tbutton" type="button" class="btn btn-info">提交</button>
			</div>
		</form>
	</div>
	<!-- 单项选择题 -->
	<div class="tab-pane fade col-sm-offset-2  col-sm-8" id="single">
		<form role="form" id="sform">
			<div class="form-group col-sm-12">
				<label for="name" class="col-sm-offset-5 col-sm-7"><h2>题目</h2></label>
				<textarea name="subject" class="form-control" rows="3"></textarea>
			</div>
			
			<div id="sanswer" class="form-group col-sm-11">
				<label for="name" class="col-sm-offset-4 col-sm-7">
					<button id="saddanswer" type="button" style="top:20px;" class="btn btn-warning col-sm-2">
         		 		&nbsp;<span class="glyphicon glyphicon-plus"></span>&nbsp;
        			</button>
					<h2 class="col-sm-3">答案</h2>
					<button id="sminusanswer" type="button" style="top:20px;" class="btn btn-warning col-sm-2">
						&nbsp;<span class="glyphicon glyphicon-minus"></span>&nbsp;
					</button>
				</label>
				<div class="col-sm-6">
					<label for="lastname" class="control-label col-sm-3">选项1:</label>
					<div class="col-sm-9">
						<input name="xuanxiang1" type="text" class="form-control" id="" placeholder="">
					</div>
				</div>
				
			</div>
			
			<div class="form-group col-sm-11" style="padding-top:20px;">
				<label for="name" class="col-sm-offset-3 col-sm-2">正确答案:</label>
				<select id="sselect" name="answer" class="col-sm-3" style="height:30px;">
					<option value="">--请添加正确答案--</option>
				    <option value="1">选项1</option>
				</select>
			</div>
			
			<div class="form-group col-sm-11" style="padding-top:20px;">
				<label for="name" class="col-sm-offset-3 col-sm-2">题目类型:</label>
				<select id="cc" name="attribute" class="col-sm-3" style="height:30px;">
					<option value="">--请选择题目类型--</option>
				    <option value="操作系统">操作系统</option>
				    <option value="数学">数学</option>
				    <option value="英语">英语</option>
				    <option value="计算机网络">计算机网络</option>
				</select>
			</div>
			
			<div class="col-sm-offset-5 col-sm-4" style="top:30px;">
				<button id="sbutton" type="button" class="btn btn-info">提交</button>
			</div>
		</form>
	</div>
	<!-- 多项选择题 -->
	<div class="tab-pane fade col-sm-offset-2  col-sm-8" id="many">
		<form role="form" id="mform">
			<div class="form-group col-sm-12">
				<label for="name" class="col-sm-offset-5 col-sm-7"><h2>题目</h2></label>
				<textarea name="subject" class="form-control" rows="3"></textarea>
			</div>
			
			<div id="mxuanxiang" class="form-group col-sm-11">
				<label for="name" class="col-sm-offset-4 col-sm-7">
					<button id="maddxuanxiang" type="button" style="top:20px;" class="btn btn-warning col-sm-2">
         		 		&nbsp;<span class="glyphicon glyphicon-plus"></span>&nbsp;
        			</button>
					<h2 class="col-sm-3">选项</h2>
					<button id="mminusxuanxiang" type="button" style="top:20px;" class="btn btn-warning col-sm-2">
						&nbsp;<span class="glyphicon glyphicon-minus"></span>&nbsp;
					</button>
				</label>
				<div class="col-sm-6">
					<label for="lastname" class="control-label col-sm-3">选项1:</label>
					<div class="col-sm-9">
						<input name="xuanxiang1" type="text" class="form-control" id="" placeholder="">
					</div>
				</div>
			</div>
			
			<div id="manswer" class="form-group col-sm-11" style="padding-top:20px;">
				<label for="name" class="col-sm-offset-5 col-sm-6">
					
					<h2 >答案</h2>
				</label>	
				<div class="col-sm-offset-4 col-sm-4">
					<input name="answer" type="text" class="form-control" id="addmodel" value="">
				</div>
				
			</div>
			
			<div class="form-group col-sm-11" style="padding-top:20px;">
				<label for="name" class="col-sm-offset-3 col-sm-2">题目类型:</label>
				<select name="attribute" class="col-sm-3" style="height:30px;">
					<option value="">--请选择题目类型--</option>
				    <option value="操作系统">操作系统</option>
				    <option value="数学">数学</option>
				    <option value="英语">英语</option>
				    <option value="计算机网络">计算机网络</option>
				</select>
			</div>
			
			<div class="col-sm-offset-5 col-sm-4" style="top:30px;">
				<button id="mbutton" type="button" class="btn btn-info">提交</button>
			</div>
		</form>
	</div>
	
	<!--简答题-->
	<div class="tab-pane fade col-sm-offset-2  col-sm-8" id="jdt">
		<form role="form" id="jform">
			<div class="form-group">
				<label for="name" class="col-sm-offset-5 col-sm-4"><h2>&nbsp;&nbsp;题目</h2></label>
				<textarea name="subject" class="form-control" rows="3"></textarea>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-offset-5 col-sm-4"><h2>参考答案</h2></label>
				<textarea name="answer" class="form-control" rows="3"></textarea>
			</div>
			<div class="form-group" style="padding-top:20px;">
				<label for="name" class="col-sm-offset-3 col-sm-2">题目类型:</label>
				<select name="attribute" class="col-sm-3" style="height:30px;">
					<option value="">--请选择题目类型--</option>
				    <option value="操作系统">操作系统</option>
				    <option value="数学">数学</option>
				    <option value="英语">英语</option>
				    <option value="计算机网络">计算机网络</option>
				</select>
			</div>
			<div class="col-sm-offset-5 col-sm-4" style="top:40px;">
				<button id="jbutton" type="button" class="btn btn-info">提交</button>
			</div>
		</form>
		
	</div>
</div>

<div id="identifier" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">请选择正确答案</h4>
            </div>
            <div class="modal-body">
            	<div>
            		<input type='checkbox' class="col-sm-1" value="1"><label class="col-sm-11">选项1</label></br>
            	</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="modeladdanswer" type="button" class="btn btn-primary" data-dismiss="modal" data-dismiss="modal">确定</button>
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