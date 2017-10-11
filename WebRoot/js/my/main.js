function checkexam(){
	$(document).ready(function(){
		var userid=$("#userid").text().trim();
		var url="checkexam";
		$.post(url,{"userid":userid},function callback(data){
			var resp = eval("("+data+")");
			if(resp.status){
				alert(resp.message);
				window.location.href="kaoshi";
			}
			else{
				alert(resp.message);
			}
		});
	});
			
}

function checkgaijuan(){
	$(document).ready(function(){
		var userid=$("#userid").text().trim();
		var url="gaijuan/checkgaijuan";
		$.post(url,{"userid":userid},function callback(data){
			var resp = eval("("+data+")");
			if(resp.status){
				window.location.href="gaijuan";
			}
			else{
				alert(resp.message);
				return false;
			}
		});
	});
}
