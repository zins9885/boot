<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>풀스택반 게시판😎😎</h2>
  <div class="panel panel-default">
    <div class="panel-heading">게시판</div>
    <div class="panel-body" id="list">
    	  <table class="table table-bordered">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>제목</th>
		        <th>작성자</th>
		        <th>작성일</th>
		        <th>삭제</th>
		      </tr>
		    </thead>
		    
		    <tbody>
			           <!-- 전체 게시판 출력 c:forEach를 쓰려면 pom.xml에 jstl을 넣어주고 >>  maven 업데이트를 해주세요! var는 변수라고함 -->
			<c:forEach items="${boardList}" var="b">
						    <tr>
						       <td>${b.idx}</td>
						       <td><a href="board/content/${b.idx}">${b.title}</a></td>
						       <td>${b.writer}</td>
						       <td>${b.indate}</td>
						       <td><button class="btn btn-sm btn-success" onClick="goDelete(${b.idx})">삭제</button></td>
						    </tr>
		    </c:forEach>
			<!-- 비동기방식 삭제요청 >> 테이블 부분만 수정할수있게 만듬 , view에서 일정 부분만 수정하도록 만듬. -->
		      <tr>
		      	<td colspan="4">
		      		<button onClick="location.href='board/writeform'" class="btn btn-sm btn-success">글작성</button>
		      	</td>
		      </tr>
		    </tbody>
		  </table>
    
    </div>
    <div class="panel-footer">FullStack SW융합 실무부트캠프 (담임 : 강예진)</div>
  </div>
</div>
<script>
 function goDelete(idx){
	 $.ajax({
	  url : "board/delete/"+idx,
	  type : "get",
	  success : loadList,
	   error : function(){
		   alert("실패")
	   }
	 })
 }
 
   // DB(board)전체 정보(data 만!) 가져오기 //비동기통신
   function  loadList(){
	   $.ajax({ 
		   url : "board/ajax",
		   type : "get",
		   dataType : "json", //서버에서 반환해주는 데이터 타입
		   success : updateTable,
		   error : function(){
			   alert("loadList 실패")
		   }
	   })
   }
   
   function updateTable(data){
	   var result = "<table class='table table-bordered table-hover'>";
	   result += "<thead>"
	   result += "<tr>";
	   result += "<th>번호</th>";
	   result += "<th>제목</th>";
	   result += "<th>작성자</th>";
	   result += "<th>작성일</th>";
	   result += "<th>삭제</th>";
	   result += "</tr>";
	   result += "</thead>"
	   //반복문
	   result += "<tbody>";
	   $.each(data, (index,vo)=> { //vo -> Board 하나하나 , index (0~)
	      result += "<tr>";
	      result += "<td>"+vo.idx+"</td>";
	      result += "<td>"+vo.title+"</td>";
	      result += "<td>"+vo.writer+"</td>";
	      result += "<td>"+vo.indate+"</td>";
	      result += "<td><button class='btn btn-sm btn-success' onClick='goDelete("+vo.idx+")'>삭제</button></td>"
	      result += "</tr>";
	   })
	   result += "</tbody>";
	   result += "<tr>";
	   result += "<td colspan='6'>";
	   result += "<button onClick=\"location.href='board/writeform'\"class='btn btn-primary btn-sm'>글작성</button>";
	   result += "</td>";
	   result += "</tr>";
	   result += "</table>";
	   
	   //html로 변환
	   $("#list").html(result)
   }
</script>
</body>
</html>
