
$(document).ready(function() {
	console.log(board_no1);
	
	$.ajax({
		url:"/board/boardInfoData",
		data:{"board_no1":board_no1},
		type:"post",
		success:function(data) {
			$.each(data, function(i) {
				if(data[i].imagefile == null){
					$("#tdImg").html("이미지 없음");
				} else {
					var tdimg = $("<img>").attr({"src":"/imagePath/"+data[i].imagefile, "width":"150","height":"150"})
					$("#tdImg").html(tdimg);
				}
				$("#td1").append(data[i].title);
				$("#td2").append(data[i].text);
				$("#td3").append(data[i].account_id);
				$("#likeboard").html(data[i].likeboard);
				$("#hateboard").html(data[i].hateboard);
			});
		}
	});
});

/**
* @param{int} i - like or hate
*/
	function likehate(i) {
		var bt = i;
		$.ajax({
			url:"/board/boardLikeHate",
			data:{"bt":bt, "board_no1":board_no1},
			type:"post",
			success:function(data) {
				$.each(data, function(i) {
					$("#likeboard").html(data[i].likeboard);
					$("#hateboard").html(data[i].hateboard);
				});
			}
		});
	}