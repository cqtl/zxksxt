<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stusituation.jsp' starting page</title>
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
	<script src="js/Highcharts-5.0.6/code/highcharts.js"></script>
	<script src="js/Highcharts-5.0.6/code/highcharts-3d.js"></script>
	
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
	var xingzhuang="column";   //分布图的形状
	var scores=new Array(0,0,0,0,0);
	$(document).ready(function(){
		/**     查询成绩          */
		$("#search").click(function(){
			var kemu="";
			var banji="";
			kemu=$("#kemu").val();
			banji=$("#banji").val();
			if(kemu==""){
				alert("请输入科目!");
				return false;
			}
			else if(banji==""){
				alert("请输入班级!");
				return false;
			}
			else{
				$("tbody tr").remove();
				for(var i=0;i<scores.length;i++){
					scores[i]=0;
				}
				$.ajax({
					type:"post",
					url:"teachersearchstuscore/searchstuscore",
					data:"kemu="+kemu+"&banji="+banji+"",
					success:function(msg){
						var message=JSON.parse(msg);
						$.each(message,function(i,n){
							$("tbody").append("<tr><td>"+eval(i+1)+"</td><td>"+n[0]+"</td><td>"+n[1]+"</td><td>"+n[2]+"</td><td>"+n[3]+"</td></tr>");
							if(n[3]<60){
								scores[0]=scores[0]+1;
							}
							else if(60<=n[3]&&n[3]<70){
								scores[1]=scores[1]+1;
							}
							else if(70<=n[3]&&n[3]<80){
								scores[2]=scores[2]+1;
							}
							else if(80<=scores[3]&&n[3]<90){
								scores[3]=scores[3]+1;
							}
							else{
								scores[4]=scores[4]+1;
							}
						});
						showcharts();
						$("#xingzhuang").show();
					}
				});
			}
		});
		
		$("#xingzhuang").click(function(){
			var xz=$("#xingzhuang").text();
			if(xz=="折线"){
				xingzhuang="line";
				$("#xingzhuang").text("柱状");
			}
			else{
				xingzhuang="column";
				$("#xingzhuang").text("折线");
			}
			showcharts();
		});
		function showcharts(){
		$(function () {
							$('#container').highcharts({                  //图表展示容器，与 div 的 id 保持一致
						        chart: {
						            type: xingzhuang                           //指定图表的类型，默认是折线图（line）,column是柱状图
						        },
						        title: {
						            text: '分数段人数分布图'                 //指定图表标题
						        },
						        xAxis: {
						            categories: ['60分以下','60-70分','70-80分','80-90分','90分以上']   //指定x轴分组
						        },
						        yAxis: {
						            title: {
						                text: '数量'                 //指定y轴的标题
						            }
						        },
						        series: [{                                 //指定数据列
						            name: '人数',                          //数据列名
						            data: [scores[0], scores[1], scores[2],scores[3],scores[4]]                        //数据
						        }],
						        plotOptions: {
						                series: {
						                    cursor: 'pointer',
						                    point: {
						                        events: {
						                            click: function (e) {
						                                window.location.href="searchsingle";
						                            }
						                        }
						                    },
						                 
						                }
						            },
						    });
						});
						}
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
		<li class="active">学生成绩查询</li>
	</ol>
  </div>
  
  <div class="bodystyle">
	  <button id="xingzhuang" type="button" class="btn btn-default" style="display:none">折线</button>
	  <div class="col-sm-offset-1 col-sm-10">
		   <div class="col-sm-5 form-group">
			   <label for="" class="col-sm-2 control-label">科目</label>
				<div class="col-sm-6">
					<select name="" class="form-control" id="kemu">
						<option value="">--请选择科目--</option>
						 <option value="操作系统">操作系统</option>
				    <option value="数学">数学</option>
				    <option value="英语">英语</option>
				    <option value="计算机网络">计算机网络</option>
					</select>
				</div>
			</div>
			<div class="col-sm-5 form-group">
				<label for="" class="col-sm-2 control-label">班级</label>
				 <div class="col-sm-6">
				    <input name="" type="text" class="form-control" id="banji" placeholder="请输入班级">
				 </div>
			</div>
		    <div class="col-sm-2">
				<button id="search" type="button" class="btn btn-info col-sm-6">查询</button>
			</div>
		</div>
		
		<div class="col-sm-12">
			<div class="col-sm-8">
			  <table class="table table-bordered">
				<caption>各门学科的成绩</caption>
				<thead>
					<tr>
						<th class="active" width="10%">序号</th>
						<th class="active" width="20%">学号</th>
						<th class="active" width="20%">姓名</th>
						<th class="active" width="35%">学科</th>
						<th class="active" width="15%">成绩</th>
					</tr>
				</thead>
				<tbody>
				
					
				
				</tbody>
			</table>
			</div>
			<div class="col-sm-4">
				<div id="container"></div>
				
			</div>
		</div>
	</div>
<div class="footer">
		       <p>在线考试系统</p>
		       <p>Copyright 2017 All Rights Reserved</p>
		</div>
</body>
</html>
