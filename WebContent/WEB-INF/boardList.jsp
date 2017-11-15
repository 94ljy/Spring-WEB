<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet"/>
<script src="/assert/js/bootstrap.min.js"></script>
<title>게시판</title>
</head>
<body class="container" style="height: 100vh;">


	
	<div style="display: table; width: 100%; height: 100%;">
		<div style="display:table-cell;  height: 75%; vertical-align: middle;">
			<h2 style="">게시판</h2>
			<hr>
			<h2 style="text-align: right;">${ user.subName }님 반갑습니다.</h2>
			<button class="btn btn-default" style="float: right;" onclick="location.href='/auth/logout'">로그아웃</button>
			<div class="board-list" style="min-height: 500px;">
				<table class="table table-hover" style="margin: 0 auto; text-align: center;">
					<thead>
						<tr>
							<th class="text-center">글 번호</th>
							<th class="text-center">제목</th>
							<th class="text-center">작성시간</th>
							<th class="text-center">조회수</th>
							<th class="text-center">작성자</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${f:length(boardTable.boardList) != 0}">
							<c:forEach var="board" items="${boardTable.boardList}">
								<tr>
									<td>${board.boardNo }</td>
									<td><a href="/board/view/${board.boardNo}?page=${boardTable.nowPage}">${board.boardTitle }</a></td>
									<td>${board.boardTime }</td>
									<td>${board.boardCount }</td>
									<td>${board.user.subName }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="5" style="text-align: center;"> 글이 없습니다. </td></tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div class="boardfoot" style="">
				<div class="board-navi text-center">
					<ul class="pagination">
						<c:forEach var="i" begin="${boardTable.firstPage}" end="${boardTable.lastPage}">
							<c:choose>
								<c:when test="${boardTable.nowPage == i}">
									<li> <a style="color: red; ">${i}</a> </li>
								</c:when>
								<c:otherwise>
									<li> <a href="/board/${i}">${i}</a> </li>
								</c:otherwise>	
							</c:choose>	
						</c:forEach>			
					</ul>
				</div>
				<div class="btn btn-default"  style="float: right;"><a href="/board/write">글쓰기</a></div>
			</div>
		</div>
	</div>
</body>
</html>