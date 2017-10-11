<%@page import="model.Singlesubject"%>
<%@page import="model.Answers"%>
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
    	<!-- 浏览器上的小图标 -->
	<link rel="icon" href="pic/myfavicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="pic/myfavicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="pic/myfavicon.ico" type="image/x-icon" />
    
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
	
	
	<script type="text/javascript">
	
		$(document).ready(function(){
		
		//选择题中10个选项在初始化时都有，所以判断是否选项有值，没有值则删除。
		$("tbody tr td div").each(function(i,n){
			if(($(n).text().length)<6){
				$(n).remove();
			}
		});
		
		//将没有值的tbody，即没有这种类型的题删掉
		$("tbody").each(function(i,n){
			if(($(n).text().trim().length)<1){
				$(n).remove();
			}
		});
		
		var num=["一、","二、","三、","四、"];
		var a=0;
		$("thead").each(function(i,n){
			//将没有值的thead，即没有这种类型的题删掉
			if(($(n).text().trim().length)<1){
				$(n).remove();
			}
			else{
				$(n).find("h3").prepend(num[a]);
				a=a+1;
			}
		});
		
		$("#tiankongb label").each(function(i,n){
			var s=$(n).text();
			for(var ii=0;ii<s;ii++){
				$(n).parent("td").prepend("<tr><td><label class='control-label col-sm-2'>"+eval(s-ii)+".</label><div class='col-sm-10'><input name='' type='text' class='form-control' value=''/></div></td></tr>");
			}
		});
		
		var score=[];  //试卷的每道题的分数值
		//点击保存试卷之后查询该试卷的名称
		$("#result").click(function(){
			var isfull=false;  //判断是否每道题都设置了分值的
			$("input[id='score']").each(function(i){
				score.push($(this).val());
				if($(this).val()==""||$(this).val()==null){
					isfull=true;
				}
			});
			if(isfull){
				alert("请输入分值");
				return false;
			}
			$("#identifier").modal("show");
			$.ajax({
				type:"post",
				url:"chuti/searchshijuanname",
				success:function(msg){
					var message=JSON.parse(msg);
					$.each(message,function(i,n){
						$("#shijuanname").append("<option value='"+n+"'>"+n+"</option>");
					});
				}
			});
			
		});
		
		//将试卷保存
		$(".btn-default").click(function(){
			var shijuanname=$("#shijuanname").val();
			if(shijuanname==""){
				alert("请输入试卷名称");
				return false;
			}
			else{
				var data=[];
				//得到题目id
				$("input.sr-only").each(function(){
					data.push($(this).val());
				});
				alert($(".sr-only").val()+"success"+shijuanname);
				$.ajax({
					type:"post",
					url:"chuti/savetoshijuan",
					data:"subid="+data+"&shijuanname="+shijuanname+"&score="+score,
					success:function(info){
						var msg = eval("("+info+")");  //将string转换成json
						alert(msg.message);
					}
				});
			}
		});
	
	});
	</script>

  </head>
  
  <body>

  
   
    <!-- 通过jstl标签+el表达式遍历后台传来的list -->
    <div class="col-sm-offset-2 col-sm-8">
    	<table class="table table-striped">
		<caption><center><h1>将会生成的试卷样式</h1></center></caption>
		<div>
			<thead>
				<tr>
					<th><h3>${danxuantimu }</h3></th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${danxuan }" var="item" varStatus="status">
					
						
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">分值</span><input type="text" id="score" name="score" style="width:6%;"/></td></tr>
						<tr><td><div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;A. ${danxuanans[status.count-1].xuanxiang1 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;B. ${danxuanans[status.count-1].xuanxiang2 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;C. ${danxuanans[status.count-1].xuanxiang3 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;D. ${danxuanans[status.count-1].xuanxiang4 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;E. ${danxuanans[status.count-1].xuanxiang5 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;F. ${danxuanans[status.count-1].xuanxiang6 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;G. ${danxuanans[status.count-1].xuanxiang7 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;H. ${danxuanans[status.count-1].xuanxiang8 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;I. ${danxuanans[status.count-1].xuanxiang9 }</br></div>
								<div><input name="${item[0] }" type="radio" value=""/>&nbsp;&nbsp;J. ${danxuanans[status.count-1].xuanxiang10 }</br></div>
						</td></tr>
				</c:forEach>
		
			</tbody>
		</div>

		<div>
			<thead>
				<tr>
					<th><h3>${duoxuantimu }</h3></th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${duoxuan }" var="item" varStatus="status">
					
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">分值</span><input type="text" id="score" name="score" style="width:6%;"/></td></tr>
						<tr><td><div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;A. ${duoxuanans[status.count-1].xuanxiang1 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;B. ${duoxuanans[status.count-1].xuanxiang2 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;C. ${duoxuanans[status.count-1].xuanxiang3 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;D. ${duoxuanans[status.count-1].xuanxiang4 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;E. ${duoxuanans[status.count-1].xuanxiang5 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;F. ${duoxuanans[status.count-1].xuanxiang6 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;G. ${duoxuanans[status.count-1].xuanxiang7 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;H. ${duoxuanans[status.count-1].xuanxiang8 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;I. ${duoxuanans[status.count-1].xuanxiang9 }</br></div>
								<div><input name="${item[0] }" type="checkbox" value=""/>&nbsp;&nbsp;J. ${duoxuanans[status.count-1].xuanxiang10 }</br></div>
						</td></tr>
				</c:forEach>
			</tbody>
		</div>
	
		<div id="tiankong">
			<thead>
			<tr>
				<th><h3>${tiankongtimu }</h3></th>
				</tr>
			</thead>
			<tbody id="tiankongb">
				<c:forEach items="${tiankong }" var="item" varStatus="status">
					
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">分值</span><input type="text" id="score" name="score" style="width:6%;"/></td></tr>
						
						<tr><td>
							<label class="sr-only" for="name">${tiankongans[status.count-1].xuanxiang }</label>  <!-- class="sr-only" label隐藏 -->
						</td></tr>
						
				</c:forEach>
			</tbody>
		</div>
		
		<div>
			<thead>
				<tr>
					<th><h3>${jiandatimu }</h3></th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${jianda }" var="item" varStatus="status">
					
						<tr><td><input type="text" value="${item[0] }" class="sr-only"/>${status.count }. ${item[1] } &emsp;&emsp;<span style="font-family:楷体; color:red;">分值</span><input type="text" id="score" name="score" style="width:6%;"/></td></tr>
						<tr><td>
						<textarea name="answer" class="form-control" rows="3"></textarea>
						</td></tr>
				</c:forEach>
			</tbody>
		</div>
	</table>
	<center><button type="button" class="btn btn-primary btn-lg" id="result">保存试卷</button></center>
   </div>
    
   <div id="identifier" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	       <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">请为试卷命名</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="col-sm-12">
	            		<label for="lastname" class="control-label col-sm-3">试卷名称:</label>
						<div class="col-sm-9">
							
							<select name="shijuanname" class="form-control" id="shijuanname" style="height:35px;">
								<option value="">--请输入试卷名称--</option>
							</select>
						</div>
	            	</div>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div> 
  </body>
</html>
