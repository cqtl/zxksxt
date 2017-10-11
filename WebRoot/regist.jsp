<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="butongyi">&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					注册协议
				</h4>
			</div>
			<div class="modal-body">
				这是协议
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info col-sm-offset-4 col-sm-4" data-dismiss="modal">同意协议
				</button>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    
<div class="xiangdao">
	<ol class="breadcrumb">
		<li><a href="cxqx">首页</a></li>
		<li class="active">注册</li>
	</ol>
</div>

<div class="main">
  <div class="col-sm-12" id="from_top">


    <form class="form-horizontal" role="form" action="regist" method="post">
  <div class="form-group">
    <label for="name" class="col-sm-offset-2 col-sm-2 control-label">名字</label>
    <div class="col-sm-4">
     <!-- 因为用到hibernate的自动注入将user的信息添加进数据库中，因为其是靠name属性来匹配值的，所以表格中的name的值要和数据库中的字段的值一样 -->
      <input type="text" class="form-control" id="name" name="name" placeholder="请输入名字">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
    </div>
  </div>
   <div class="form-group">
    <label for="qpassword" class="col-sm-offset-2 col-sm-2 control-label">确认密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="qpassword" name="qpassword" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-5 col-sm-7">
      <button type="submit" class="btn btn-info col-sm-3" id="zc">注册</button>
    </div>
  </div>
</form>
</div>
</div>

<%@ include file="foot.jsp" %>
 <script language="javascript" type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$("#butongyi").click(function(){
    			window.location.href="cxqx";
    		});
    		$("#zc").click(function(){
    			if($("#name").val()==""){
    				alert("用户名不能为空");
    				return false;
    			}
    			if($("#password").val()!=$("#qpassword").val()){
    				alert("密码与确认密码不一致");
    				return false;
    			}
    		});
    	});
    </script>
    
    <!-- 这是使用的bootstrap里面的js，所以必须在这个<script>上面加上下面的连接，注意：不是jQuery里面的js -->
    <script src="js/bootstrap.min.js"></script>   
		<script>
		$(document).ready(function () { $("#myModal").modal({
			keyboard:false      //设置为false则按下esc键也无法退出模态框
		});});
		</script>