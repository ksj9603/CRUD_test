let category = 0;
  let number = 0;
  
  const fn_category = (i) => {
	  number = 0;
	  category = i;
	  $("#search").val("");
	  
	  $.ajax({
		  url:"/board/getBoardList",
		  type:"post",
		  data:{"category" : category},
		  success:function(data) {
			let str ='<tr>' ;
			$.each(data, function(i) {
				if(data[i].imagefile == null) {
					str +="<td>" +((number*5)+(i+1)) +"</td><td>&nbsp;"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}else {
				str +="<td>" + ((number*5)+(i+1)) +"</td><td>"+"<img src="+"/imagePath/"+data[i].imagefile+" width='30' height='30'/>"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}
			});
			$("#go").html(str);
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
			  
			let str ='<tr>' ;
			if(data.length === 0) {
				str+= "<td colspan='7' style='text-align:center'>검색 결과가 없습니다.</td></tr>"
			}
			$.each(data, function(i) {
				console.log(data.length);
				
				if(data[i].imagefile === null) {
					str +="<td>" +((number*5)+(i+1)) +"</td><td>&nbsp;"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}else {
				str +="<td>" + ((number*5)+(i+1)) +"</td><td>"+"<img src="+"/imagePath/"+data[i].imagefile+" width='30' height='30'/>"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
					}
				
			});
			$("#go").html(str);
			  
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
			let str ='<tr>' ;
			$.each(data, function(i) {
				if(data[i].imagefile == null) {
					str +="<td>" + ((number*5)+(i+1)) +"</td><td>&nbsp;"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}else {
				str +="<td>" + ((number*5)+(i+1)) +"</td><td>"+"<img src="+"/imagePath/"+data[i].imagefile+" width='30' height='30'/>"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}
				
			});
			$("#go").html(str);
		  }
	  });
	  
  }
  const chagePage = (j)=> {
	  number = j-1;
	  let page = j;
	  let data1 = {"page":page,"category":category};
	  $.ajax({
		  url:"/board/changePage",
		  type:"post",
		  data:data1,
		  success:function(data) {
			let str ='<tr>' ;
			$.each(data, function(i) {
				if(data[i].imagefile == null) {
					str +="<td>" + ((number*5)+(i+1)) +"</td><td>&nbsp;"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}else {
				str +="<td>" + ((number*5)+(i+1)) +"</td><td>"+"<img src="+"/imagePath/"+data[i].imagefile+" width='30' height='30'/>"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}
				
			});
			$("#go").html(str);
		  }
	  });
	  
  }
  $(document).ready(function() {
	  $.ajax({
		  url:"/board/getBoardList",
		  type:"post",
		  data:{"category":category},
		  success:function(data) {
			let str ='<tr>' ;
			$.each(data, function(i) {
				if(data[i].imagefile == null) {
					str +="<td>" +((number*5)+(i+1)) +"</td><td>&nbsp;"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}else {
				str +="<td>" + ((number*5)+(i+1)) +"</td><td>"+"<img src="+"/imagePath/"+data[i].imagefile+" width='30' height='30'/>"
				+"</td><td>"+data[i].title+"</td><td>"+data[i].text+"</td><td>"+data[i].account_id+ "</td>" +
				"<td><input type='button' class='btn btn-primary' value='삭제' onclick="+"location.href='/board/deleteBoard/?board_no="+data[i].board_no+"'"+"></input></td>"
				+ "<td><input type='button' class='btn btn-primary' value='수정' onclick"+"=location.href='/board/alterBoard/?board_no="+data[i].board_no+"'"+"></input></td>";
				str += '</tr>';
				}
			});
			$("#go").html(str);
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
  
  
  