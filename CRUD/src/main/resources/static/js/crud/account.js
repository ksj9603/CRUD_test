const addUser = () => {
	  let id = $("#id").val();
	  let pwd = $("#pwd").val();
	  let name = $("#name").val();
	  let info = {"id":id,"pwd":pwd,"name":name};
	  console.log(JSON.stringify(info))
	  $.ajax({
		  url:"/addUser",
		  type:"post",
		  data:JSON.stringify(info),
		  contentType: "application/json; charset=UTF-8", 
		  success:function(data) {
			if(data === true ){
				location.href="/";
			}else {
				alert("다시 입력해주세요");
			}	  
		  }
		  
	  });
  }