const gogo = () => {
	  let id = $("#id").val();
	  let pwd = $("#pwd").val();
	  let info = {"id":id,"pwd":pwd};
	  
	  $.ajax({
		  url:"/accountCheck",
		  type:"post",
		  data:info,
		  //data:JSON.stringify(info),
		  //contentType: "application/json; charset=UTF-8", 
		  success:function(data) {
				if(data===0){
					$("#errorMessage").text("아이디 및 비밀번호가 틀렸습니다.");
					$("#id").val("");
					$("#pwd").val("");
				} else if(data === 2) {
					location.href="/board/boardList";
				} else if (data === 1) {
					alert("20초간 로그인 시도 불가능");
					
				}
		  }
	  });
  }
 
  const addUser = () => {
	  location.href="/account";
  }