<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'answerforkaosheng.jsp' starting page</title>
    
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
		var allkaoshengnum=0;
		var restkaoshengnum=0;
		var kaoshengids=0;
		var ksid=0;
		var location=0;
		
		//查询参加该考试的考生总人数
		$.ajax({
			type:"post",
			url:"gaijuan/kaoshengnum",
			data:"shijuanname="+$("#shijuanname").val(),
			success:function(msg){
				var jmsg=JSON.parse(msg);
				allkaoshengnum=jmsg.message;
			}
		});
		
		//查询该试卷对应的考试考生的id
			$.ajax({
				type:"post",
				url:"gaijuan/kaoshengids",
				data:"shijuanname="+$("#shijuanname").val(),
				success:function(msg){
					var jmsg=JSON.parse(msg);
					kaoshengids=jmsg;
					ksid=kaoshengids[location];
					restkaoshengnum=kaoshengids.length;
					$("#xinxi").text("考生试题数剩余量："+restkaoshengnum+"/"+allkaoshengnum+"");
					if(restkaoshengnum<1){
						$("tbody").append("<h1>当前没有未改试卷!</h1>");
						$("#tijiao").hide();
						return false;
					}
					//查询对应id考生的作答答案
					$.ajax({
						type:"post",
						url:"gaijuan/kaosheng",
						data:"userid="+ksid+"&shijuanname="+$("#shijuanname").val(),
						success: function(msg){
		     				var jmsg=JSON.parse(msg);
		     				$.each(jmsg,function(i,n){
		     					$("tbody").append("<tr><td><input name='score' type='text' class='form-control'/></td><td>"+n[0]+"</td><td>"+n[1]+"</td><td><textarea name='pingyu' class='form-control' rows='3'></textarea></td></tr>");
		     				});
		   				}
					});
				}
			});
			
					
			//点击上一个考生，显示上一个考生的答案
			$("#up").click(function(){
				location=location-1;
				if(location<0){
					location=0;
					alert("已经是第一个了");
					return false;
				}
				$("tbody tr").remove();
				ksid=kaoshengids[location];
				$.ajax({
				type:"post",
				url:"gaijuan/kaosheng",
				data:"userid="+ksid+"&shijuanname="+$("#shijuanname").val(),
				success: function(msg){
     				var jmsg=JSON.parse(msg);
     				$.each(jmsg,function(i,n){
     					$("tbody").append("<tr><td><input name='score' type='text' class='form-control'/></td><td>"+n[0]+"</td><td>"+n[1]+"</td><td><textarea name='pingyu' class='form-control' rows='3'></textarea></td></tr>");
     				});
   				}
			});
			});
			//点击下一个考生，显示下一个考生的答案
			$("#down").click(function(){
				location=location+1;
				if(location>kaoshengids.length-1){
					location=kaoshengids.length-1;
					alert("已经是最后一个了");
					return false;
				}
				$("tbody tr").remove();
				ksid=kaoshengids[location];
				$.ajax({
				type:"post",
				url:"gaijuan/kaosheng",
				data:"userid="+ksid+"&shijuanname="+$("#shijuanname").val(),
				success: function(msg){
     				var jmsg=JSON.parse(msg);
     				$.each(jmsg,function(i,n){
     					$("tbody").append("<tr><td><input name='score' type='text' class='form-control'/></td><td>"+n[0]+"</td><td>"+n[1]+"</td><td><textarea name='pingyu' class='form-control' rows='3'></textarea></td></tr>");
     				});
   				}
			});
			});
			//提交改卷结果
			$("#tijiao").click(function(){
				var dataform=$("form").serialize();
				$.ajax({
					type:"post",
					url:"gaijuan/tijiaogaijuan",
					data:dataform+"&kaoshengid="+ksid+"&shijuanname="+$("#shijuanname").val(),
					success:function(msg){
						var jmsg=JSON.parse(msg);
						alert(jmsg.message);
						if(jmsg.status){
							restkaoshengnum--;
							$("#xinxi").text("考生试题数剩余量："+restkaoshengnum+"/"+allkaoshengnum+"");
							$("tbody tr").remove();
							if(restkaoshengnum<1){
								$("tbody").append("<h1>当前没有未改试卷!</h1>");
								$("#tijiao").hide();
								return false;
							}
							else{
							$.ajax({
								type:"post",
								url:"gaijuan/kaoshengids",
								data:"shijuanname="+$("#shijuanname").val(),
								success:function(msg){
									var jmsg=JSON.parse(msg);
									location=0;
									kaoshengids=jmsg;
									ksid=kaoshengids[location];
									restkaoshengnum=kaoshengids.length;
									$("#xinxi").text("考生试题数剩余量："+restkaoshengnum+"/"+allkaoshengnum+"");
									//查询对应id考生的作答答案
									$.ajax({
										type:"post",
										url:"gaijuan/kaosheng",
										data:"userid="+ksid+"&shijuanname="+$("#shijuanname").val(),
										success: function(msg){
							     			var jmsg=JSON.parse(msg);
							     			$.each(jmsg,function(i,n){
							     				$("tbody").append("<tr><td><input name='score' type='text' class='form-control'/></td><td>"+n[0]+"</td><td>"+n[1]+"</td><td><textarea name='pingyu' class='form-control' rows='3'></textarea></td></tr>");
							     			});
							   			}
									});
								}
							});
						}
					}
				}
			});
		});
	});
	</script>
  </head>
  
  <body>
  <input type="text" value="${shijuanname }" name="shijuanname" id="shijuanname" class="sr-only"/>
  	<form class="form-horizontal col-sm-12" role="form">
		<div class="form-group col-sm-12">
        <button id="up" type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-chevron-left"></span> 上一个考生
        </button>
			
         <button id="down" type="button" class="btn btn-default btn-sm">
        	 下一个考生 <span class="glyphicon glyphicon-chevron-right"></span> 
        </button>
        
        <span style="float:right" id="xinxi">考生试题数剩余量：0/0</span>
		</div>
       <table class="table table-bordered table-hover table-striped">
		  <thead>
		    <tr class="success">
		   	  <th width="10%">得分</th>
		      <th width="35%">题目</th>
		      <th width="35%">参考答案</th>
		      <th width="20%">评语</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  </tbody>
	</table>
	<button id="tijiao" type="button" class="btn btn-info">提交</button>
	</form>
  </body>
</html>
