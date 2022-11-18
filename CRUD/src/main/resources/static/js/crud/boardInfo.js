
$(document).ready(function() {
	console.log(board_no1);
	console.log(account_id);
	$.ajax({
		url:"/board/boardInfoData",
		data:{"board_no1":board_no1},
		type:"post",
		success:function(data) {
			console.log(data);
			// 상세내용 구성
			for(var i=0; i<data.board.length; i++ ){
				if(data.board[i].imagefile == null) {
					$("#tdImg").html("이미지 없음");
				}else {
					var tdimg = $("<img>").attr({"src":"/imagePath/"+data.board[i].imagefile, "width":"150","height":"150"})
					$("#tdImg").html(tdimg);
				}
				$("#td1").append(data.board[i].title);
				$("#td2").append(data.board[i].text);
				$("#td3").append(data.board[i].account_id);
				$("#likeboard").html(data.board[i].likeboard);
				$("#hateboard").html(data.board[i].hateboard);
			}
			// 댓글 구성
			for(var j=0; data.comm.length; j++) {
				if(data.comm[j].parentno != 0) {
					var tr = $("<tr>").attr("id",data.comm[j].id);
					var comm_id = $("<td>").text(data.comm[j].comment_id).attr("width","20%");
					var comm_text = $("<td>").text("\u00a0\u00a0"+"RE : "+data.comm[j].text).attr("width","50%");
					var comm_time = $("<td>").text(data.comm[j].comment_time);
					var comm_bt = $("<td>");
	
					tr.append(comm_id, comm_text, comm_time,comm_bt);
					$("#commTR").append(tr);
				} else {
					var tr = $("<tr>").attr("id",data.comm[j].id);
					var comm_id = $("<td>").text(data.comm[j].comment_id).attr("width","20%");
					var comm_text = $("<td>").text(data.comm[j].text).attr("width","50%");
					var comm_time = $("<td>").text(data.comm[j].comment_time);
					var comm_bt = $("<td>");
					var bt = $("<input>").attr({"value":"답글","type":"button","onclick":"recommentButton("+data.comm[j].id+");"});
					comm_bt.append(bt);
					tr.append(comm_id, comm_text, comm_time,comm_bt);
					$("#commTR").append(tr);
				}
			}
		}
	});
});

/**
* 좋아요 / 싫어요
* @param{int} i : 1-like / 2-hate
*/
function likehate(i) {
	var bt = i;
	$.ajax({
		url:"/board/boardLikeHate",
		data:{"bt":bt, "board_no1":board_no1,"account_id":account_id},
		type:"post",
		success:function(data) {
			console.log(data);
			if(data == "") {
				alert("하루에 총 3번만 누를 수 있습니다. 24시간 뒤에 시도해주세요.");
			}
			$.each(data, function(i) {
				$("#likeboard").html(data[i].likeboard);
				$("#hateboard").html(data[i].hateboard);
			});
		}
	});
}

/**
 * 댓글달기 버튼 함수
 */
function comment(i) {
	var comment_val = $("#comment_board").val();
	$("#comment_board").val("");
	
	
	$.ajax({
		url:"/board/comment",
		data:{"comment_val":comment_val, "board_no1":board_no1,"account_id":account_id,"parentno":i},
		type:"post",
		success:function(data) {
				var tr = $("<tr>").attr("id",data[data.length-1].id);
				var comm_id = $("<td>").text(data[data.length -1].comment_id).attr("width","20%");
				var comm_text = $("<td>").text(data[data.length -1].text).attr("width","60%");
				var comm_time = $("<td>").text(data[data.length -1].comment_time);
				var comm_bt = $("<td>");
				var bt = $("<input>").attr({value:"답글",type:"button",onclick:"recommentButton("+data[data.length -1].id+");"});
				comm_bt.append(bt);
				tr.append(comm_id, comm_text, comm_time,comm_bt);
				$("#commTR").append(tr);
				console.log(data[data.length -1].id);
			
		}
	});
}

/**
* 메인페이지로 가는 함수
*/
function mainPage() {
	location.href="/board/boardList";
}

/**
* 대댓글 처리 함수
 */
function recommentButton(i) {
	console.log(i);
	var id = i;
	var trId = "tr"+i;
	
	var tr1 = $("<tr>").attr("id",trId);
	var td1 = $("<td>").attr({"colspan":4});
	var td1_in=$("<input>").attr({"type":"text","id":"commText","class":"inputstyle"});
	var td1_bt = $("<input>").attr({type:"button",value:"답글",onclick:"recommentView("+id+");"});
	
	td1.append(td1_in,td1_bt);
	tr1.append(td1);
	$('#'+i).after(tr1)	
}
/**
* 대댓글 버튼 누르면 적용되는 함수
* @param{int} id - 댓글 고유 id
 */
function recommentView(id) {
	var id = id ;
	var text = $("#commText").val();
	var trId = "tr"+id;
	
	$.ajax({
		url:"/board/recommentView",
		data:{"id":id,"text":text,"board_no1":board_no1, "account_id":account_id},
		type:"post",
		success:function(){
			$('#'+trId).remove();
			location.href="/board/boardInfo/?board_no="+board_no1; // 야매로 
		} 
	});
}