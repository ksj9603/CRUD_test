

let category = 0;
let number = 0;
/*
 ** 동일한 html 구조**
 				*/
 				
/**
	html 구성하는 공통적인 태그
	* @param {int} number - start number 
 */
const html_write = (number, data,i) => {
	let tr = $("<tr>",{});
			
	if(data[i].imagefile == null) {
		let td1 = $("<td>",{text:number*5 +(i+1), width:"13%"});
		let td2 = $("<td>",{});
		let td3 = $("<td>",{});
		var a1 = $("<a>").text(data[i].title).attr("href","javascript:boardInfo(" + data[i].board_no + ");");
		td3.append(a1);
		
		let td5 = $("<td>",{text:data[i].account_id});
		let td6 = $("<td>",{});
		let bt1 = $("<input>",{type:'button', class:'btn btn-primary', value:'삭제', onclick:"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"})
		td6.append(bt1);
		
		let td7 = $("<td>",{});
		let bt2 = $("<input>",{type:'button', class:'btn btn-primary', value:'수정', onclick:"location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"})
		td7.append(bt2);
		
		let td8 = $("<td>");
		let bt3 = $("<input>").attr({"type":"button","value":"답글","class":"btn btn-primary","onclick":"reboard("+data[i].board_no+");"});
		td8.append(bt3);
		
		tr.append(td1,td2,td3,td5,td6,td7,td8);
		$("#go").append(tr);
	} else {
		let td1 = $("<td>",{text:number*5 +(i+1),width:"13%"});
		let td2 = $("<td>",{});
		let img1 = $("<img>",{src:"/imagePath/"+data[i].imagefile, width:'30', height:'30'})
		td2.append(img1);
		
		let td3 = $("<td>",{});
		var a1 = $("<a>").text(data[i].title).attr("href","javascript:boardInfo(" + data[i].board_no + ");");
		td3.append(a1);
		
		let td5 = $("<td>",{text:data[i].account_id});
		let td6 = $("<td>",{});
		let bt1 = $("<input>",{type:'button', class:'btn btn-primary', value:'삭제', onclick:"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"})
		td6.append(bt1);
		
		let td7 = $("<td>",{});
		let bt2 = $("<input>",{type:'button', class:'btn btn-primary', value:'수정', onclick:"location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"})
		td7.append(bt2);
		
		let td8 = $("<td>");
		let bt3 = $("<input>").attr({"type":"button","value":"답글","class":"btn btn-primary","onclick":"reboard("+data[i].board_no+");"});
		td8.append(bt3);
		
		tr.append(td1,td2,td3,td5,td6,td7,td8);
		$("#go").append(tr);
		}
}

/**
* @param{int} i - board_no값
 */
function reboard(i) {
	location.href="/board/reboard/?board_no=" + i
}

/**
게시글 누르면 상세 페이지 가는 링크
 */
function boardInfo(i) {
	location.href="/board/boardInfo/?board_no=" + i; 
}

/**
카테고리별로 구분해서 리스트 뽑아내는 함수
 */
const fn_category = (i) => {
	number = 0;
	category = i;
	$("#search").val("");
	  
	$.ajax({
		url:"/board/getBoardList",
		type:"post",
		data:{"category" : category},
		success:function(data) {
			$("#go").html("");		
			$.each(data, function(i) {
				html_write(number, data,i);
			});
		}
	});  
	$.ajax({
		url:"/board/getAllBoardList",
		type:"post",
		data:{"category" : category},
		success:function(data1) {
			let num = data1.length / 5;
			let pag = "";
			for(let i = 0 ;i < num; i++ ) {
				  
				pag += "<a href='javascript:chagePage("+(i+1)+");'>"+ (i+1) +"</a> &nbsp;&nbsp;&nbsp;&nbsp;";
			}
			$("#page").html(pag);
		}
	});
}

/**
검색 함수
 */
const search = () => {
	number = 0;
	let dropdown = document.getElementById("drop");
	let droper = dropdown.options[dropdown.selectedIndex].value;
	let search = $("#search").val();
	  
	console.log(search);
	console.log(droper);
	let data12 = {"search":search,"droper":droper,"category":category};
	$.ajax({
		url:"/board/searchBoard",
		type:"post",
		data:data12,
		success:function(data) {
			$("#go").html("");		
			$.each(data, function(i) {
				html_write(number, data,i);
			});
		}
	});
	$.ajax({
		url:"/board/searchAllBoard",
		data:data12,
		type:"post",
		success:function(data1) {
			let num = data1.length / 5;
			let pag = "";
			for(let i = 0 ;i < num; i++ ) {
				pag += "<a href='javascript:searchPage("+(i+1)+");'>"+ (i+1) +"</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			$("#page").html(pag);
		}
	});
}

/**
검색하고 나온 리스트들 페이징 함수
 */
const searchPage = (j)=> {
	number = 0;
	let dropdown = document.getElementById("drop");
	let droper = dropdown.options[dropdown.selectedIndex].value;
	let search = $("#search").val();
	number = j-1;
	let page = j;
	let data12 = {"page":page,"search":search,"droper":droper,"category":category};
	$.ajax({
		url:"/board/searchBoardpage",
		type:"post",
		data:data12,
		success:function(data) {
			$("#go").html("");		
			$.each(data, function(i) {
				html_write(number, data,i);
			});
		}
	});  
}

/**
리스트 페이징 함수
 */
const chagePage = (j)=> {
	number = j-1;
	let page = j;
	let data1 = {"page":page,"category":category};
	$.ajax({
		url:"/board/changePage",
		type:"post",
		data:data1,
		success:function(data) {
			$("#go").html("");		
			$.each(data, function(i) {
				html_write(number, data,i);
			});
		}
	});
}

/**
* 첫 화면 구성
 */
$(document).ready(function() {
	$.ajax({
		url:"/board/getBoardList",
		type:"post",
		data:{"category":category},
		success:function(data) {
			$("#go").html("");		
			$.each(data, function(i) {
				html_write(number, data,i);
			});
		}
	}); 
	$.ajax({
		url:"/board/getAllBoardList",
		type:"post",
		data:{"category":category},
		success:function(data1) {
			let num = data1.length / 5;
			let pag = "";
			for(let i = 0 ;i < num; i++ ) {
				  pag += "<a href='javascript:chagePage("+(i+1)+");'>"+ (i+1) +"</a> &nbsp;&nbsp;&nbsp;&nbsp;";
			}
			$("#page").html(pag);
		}
	});
});
  
  
  