<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Chuti.jsp' starting page</title>
    
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
		var pagenow=1;
		var pagenumber=0;
		$(document).ready(function(){
		
			$.ajax({
				type:"post",
				url:"chuti",
				data:"pagenow="+pagenow+"",
				success:function(msg){
					var message=JSON.parse(msg);
					$.each(message,function(i,n){
						if(n.subject!=null){
							$("tbody:first").append("<tr><td><input type='checkbox' value="+n.id+"></td><td>"+n.attribute+"</td><td >"+n.subject+"</td><td>"+n.leixing+"</td><td><button type='button' class='btn btn-info' value="+n.id+">查看</button></td></tr>");
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
						//截取tbody下的所有的td的内容，通过遍历
						var text1="";
						$("tbody td").each(function(i,n){
							text1=$(n).text();
							if(text1.length>5){
								text1=text1.substring(0,5)+"...";
								$($(n).text(text1));
							}
							//n为td标签等于this，所以有以下alert的情况
						//	alert("td"+i+$(n).text()+"xxx"+$(this).text());
						});
					});
				}
			});
			
			//点击搜索按钮
			$(".btn-success").click(function(){
				$("tbody:first").html("");
				$(".bodystyle ul").html("");
				$.ajax({
				type:"post",
				url:"chuti",
				data:$("form").serialize()+"&pagenow=1",
				success:function(msg){
					var message=JSON.parse(msg);
					$.each(message,function(i,n){
						if(n.subject!=null){
							$("tbody:first").append("<tr><td><input type='checkbox' value="+n.id+"></td><td>"+n.attribute+"</td><td >"+n.subject+"</td><td>"+n.leixing+"</td><td><button type='button' class='btn btn-info' value="+n.id+">查看</button></td></tr>");
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
						//截取tbody下的所有的td的内容，通过遍历
						var text1="";
						$("tbody td").each(function(i,n){
							text1=$(n).text();
							if(text1.length>5){
								text1=text1.substring(0,5)+"...";
								$($(n).text(text1));
							}
							//n为td标签等于this，所以有以下alert的情况
						//	alert("td"+i+$(n).text()+"xxx"+$(this).text());
						});
					});
				}
			});
		});
			//点击查看按钮
			$("tbody").on("click","tr td button",function(){
			
			//设置cookie存储当前的值，返回此页面时，还能够到查询的同一个位置
		//		$.cookie("searchattribute",$("#searchattribute").val());
				alert($("#searchattribute").val());
				alert($(this).val());
				window.open("chuti/subjectdetail?subid="+$(this).val());
				//window.location.href="chuti/subjectdetail ?subid="+$(this).val();
			});
			
			//点击分页栏
			$(".bodystyle div").on("click","ul li a",function(){
				$("tbody:first").html("");
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
				alert("page"+pagenow);
				
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
					url:"chuti",
					data:$("form").serialize()+"&pagenow="+pagenow+"",
					success:function(msg){
						var message=JSON.parse(msg);
						$.each(message,function(i,n){
							if(n.subject!=null){
								$("tbody:first").append("<tr><td><input type='checkbox' value="+n.id+"></td><td>"+n.attribute+"</td><td >"+n.subject+"</td><td>"+n.leixing+"</td><td><button type='button' class='btn btn-info' value="+n.id+">查看</button></td></tr>");
							}
							else{
								pagenumber=n.pagenumber;
							}
							//截取tbody下的所有的td的内容，通过遍历
							var text1="";
							$("tbody td").each(function(i,n){
								text1=$(n).text();
								if(text1.length>5){
									text1=text1.substring(0,5)+"...";
									$($(n).text(text1));
								}
								//n为td标签等于this，所以有以下alert的情况
							//	alert("td"+i+$(n).text()+"xxx"+$(this).text());
							});
						});
					}
				});
			});
	
	//保存按钮点击事件
			$("center .btn-warning").click(function(){
				$("#identifier").modal("show");
				alert($("input:checked").val());
				var data=[];
				$("input:checked").each(function(){
					data.push($(this).val());
				});
				$.ajax({
					traditional: true,    //数组序列化时
					type:"post",
					url:"chuti/saveselecttimu",
					data:{"subid":data},
					success:function(msg){
						var message=JSON.parse(msg);
						$.each(message,function(i,n){
							if(n.subject!=null){
								$(".modal-body div table tbody").append("<tr><td><input type='checkbox' value="+n.id+"></td><td>"+n.attribute+"</td><td >"+n.subject+"</td><td>"+n.leixing+"</td><td><button type='button' class='btn btn-info' value="+n.id+">查看</button></td></tr>");
							}
						
						});
						
						//截取tbody下的所有的td的内容，通过遍历
						var text1="";
						$("tbody td").each(function(i,n){
							text1=$(n).text();
							if(text1.length>5){
								text1=text1.substring(0,5)+"...";
								$($(n).text(text1));
							}
							//n为td标签等于this，所以有以下alert的情况
						//	alert("td"+i+$(n).text()+"xxx"+$(this).text());
						});
					}
				});
			});
			
			//模态框中的移出按钮点击事件
			$(".modal-footer .btn-danger").click(function(){
				$(".modal-body tr:has(input:checked)").remove();
			});
			
			//模态框中的生成试卷按钮点击事件
			$(".modal-footer .btn-primary").click(function(){
				var data=[];
				$(".modal-body tbody input[type='checkbox']").each(function(){
					data.push($(this).val());
				});
				/*
				$.ajax({
					traditional: true,
					type:"post",
					url:"chuti/makeshijuan",
					data:{"subid":data},
					success:function(msg){
						alert("success!");
					}
				});*/
			//	window.open("chuti/subjectdetail?subid="+$(this).val());
				window.open("chuti/makeshijuan?subid="+data)
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
		<li class="active">出题</li>
	</ol>
  </div>
  
  <div class="bodystyle">
    <div class=" col-sm-offset-2 col-sm-8">
	    <form class="form-horizontal">
	    	<div class="form-group">
	    		<div class="col-sm-5">
		    		<label class="col-sm-4 control-label">学科:</label>
				    <div class="col-sm-8">
				      <input name="attribute" type="text" class="form-control" id="searchattribute" placeholder="请输入学科">
				    </div>
	    		</div>
	    		<div class="col-sm-5">
		    		<label class="col-sm-4 control-label">类型:</label>
				    <div class="col-sm-8">
				      <input name="leixing" type="text" class="form-control" id="searchleixing" placeholder="请输入类型">
				    </div>
	    		</div>
		  	</div>
	    	<div class="form-group">
	    		<div class="col-sm-5">
		    		<label class="col-sm-4 control-label">题目:</label>
				    <div class="col-sm-8">
				      <input name="subject" type="text" class="form-control" id="searchsubject" placeholder="请输入题目">
				    </div>
	    		</div>
	    		<div class="col-sm-offset-2 col-sm-2">
				     <button type="button" class="btn btn-success">搜索</button>
	    		</div>
		  	</div>
		</form>
		<table class="table table-hover table-bordered table-striped table-responsive">
			
			<thead>
				<tr class="alert-info">
					<th width="10%">选择</th>
					<th width="25%">学科</th>
					<th width="45%">题目</th>
					<th width="10%">类型</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
					
			</tbody>
		</table>
		
	<ul class="pagination">
		
    </ul>
    <center><button type="button" class="btn btn-warning" >保存</button></center>
</div>

<div id="identifier" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">你所选择的题目</h4>
            </div>
            <div class="modal-body">
            	<div>
            		<table class="table table-hover table-bordered table-striped table-responsive">
			
						<tbody>
								
						</tbody>
					</table>
            	</div>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-danger">移出</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                <button id="modeladdanswer" type="button" class="btn btn-primary">生成试卷</button>
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
