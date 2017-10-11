<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<div class="xiangdao">
	<ol class="breadcrumb">
		<li><a href="cxqx">考试系统</a></li>
		<li class="active">发布考试</li>
	</ol>
</div>
<div class="main">
  	<div class="col-sm-offset-2 col-sm-10">
    	<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="" class="col-sm-2 control-label">科目</label>
		    <div class="col-sm-4">
		    	<select name="examcourse" class="form-control" id="kemu">
					<option value="">--请选择科目--</option>
				    <option value="操作系统">操作系统</option>
				    <option value="数学">数学</option>
				    <option value="英语">英语</option>
				    <option value="计算机网络">计算机网络</option>
				</select>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="" class="col-sm-2 control-label">开考时间</label>
		    <div class="col-sm-4 input-append date form_datetime">
	     	  	<input name="examtime" class="form-control" type="datetime" value="" readonly>
	          	<span class="add-on"><i class="icon-time"></i></span>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="" class="col-sm-2 control-label">考试时间（分钟）</label>
		    <div class="col-sm-4">
		      <input name="howlong" type="text" class="form-control" id="" placeholder="请输入考试时间">
		    </div>
		  </div>
		  	  <div class="form-group">
		    <label for="" class="col-sm-2 control-label">出题人</label>
		    <div class="col-sm-4">
		      <input name="ctrName" type="text" class="form-control" id="ct_teacher" placeholder="请输入出题人名">
		    </div>
		  </div>
		  	  <div class="form-group">
		    <label for="" class="col-sm-2 control-label">改卷人</label>
		    <div class="col-sm-4">
		      <input name="gjrName" type="text" class="form-control" id="pg_teacher" placeholder="请输入改卷人名">
		    </div>
		  </div>
		  	  <div class="form-group">
		    <label for="" class="col-sm-2 control-label">试卷名</label>
		    <div class="col-sm-4">
		      <input name="shijuanname" type="text" class="form-control" id="" placeholder="请输入试卷名">
		    </div>
		  </div>
     
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="button" id="save" class="btn btn-default">保存</button>
		    </div>
		  </div>
		</form>
	</div>
	
	
	<div id="identifier" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">请选择教师</h4>
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
            	<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
<%@ include file="foot.jsp" %>
<script type="text/javascript" src="js/my/examination.js"></script>
