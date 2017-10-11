$(document).ready(function(){
	
		$("#ct_teacher").on("click",function(){
			$(".modal-body div table tbody tr").remove();
			if($("#kemu").val()==""){
				alert("请先填写科目");
				return false;
			}
			var url="examination";
			var data="kemu="+$('#kemu').val()+"&name=ctjiaoshi";
			$.post(url,data,function callback(message){
					var resp = eval("("+message+")"); 
					if(resp.length==0){
						$(".modal-body div table tbody").append("<tr><td><label class='col-sm-11'>暂无合适的教师</label></td></tr>");
					}
					else{
						$.each(resp,function(i,n){
							$(".modal-body div table tbody").append("<tr><td><input type='checkbox' class='col-sm-1' value='"+n.name+"'/><label class='col-sm-11'>"+n.name+"</label></td></tr>");	
						});
					}
					$("#identifier").modal("show");
					$(".modal-footer .btn-default").one("click",function(){
						var teacher="";
						$("#identifier input:checked").each(function(){
							teacher=teacher+$(this).val()+",";
						});
						teacher=teacher.substring(0,teacher.length-1);
						$("#ct_teacher").val(teacher);
					});
			});
			return true;
		});
		$("#pg_teacher").on("click",function(){
			$(".modal-body div table tbody tr").remove();
			if($("#kemu").val()==""){
				alert("请先填写科目");
				return false;
			}
			var url="examination";
			var data="kemu="+$('#kemu').val()+"&name=gjjiaoshi";
			$.post(url,data,function callback(message){  
					var resp = eval("("+message+")");
					if(resp.length==0){
						$(".modal-body div table tbody").append("<tr><td><label class='col-sm-11'>暂无合适的教师</label></td></tr>");
					}
					else{
						$.each(resp,function(i,n){
							$(".modal-body div table tbody").append("<tr><td><input type='checkbox' class='col-sm-1' value='"+n.name+"'/><label class='col-sm-11'>"+n.name+"</label></td></tr>");
						});
					}
					$("#identifier").modal("show");
					$(".modal-footer .btn-default").one("click",function(){
						var pgteacher="";
						$("#identifier input:checked").each(function(){
							pgteacher=pgteacher+$(this).val()+",";
						});
						pgteacher=pgteacher.substring(0,pgteacher.length-1);
						$("#pg_teacher").val(pgteacher);
					});
				});
				return true;
		});
		
		$(".modal-footer .btn-danger").click(function(){
			$(".modal-body div table tbody tr").remove();
		});
		
		$(".form_datetime").datetimepicker({
      		format: "yyyy-mm-dd hh:ii:ss",
      		autoclose: true,
     		todayBtn: true,
     		language:'zh-CN',
     		pickerPosition:"left"
   		 });
   		 
   		 $("#save").click(function(){
   		 	var data=$("form").serialize();
   		 	var url="examination/save";
   		 	$.post(url,data,function callback(message){
   		 		var msg = JSON.parse(message);  //将string转换成json
   		 		alert(msg.message);
   		 	});
   		 });
			
	});