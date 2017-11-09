<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body style="display: flex; width:100%; height:100vh; margin: 0; padding:0; align-items: center;">
	<table style="margin: 0 auto;">
		<tr>
			<td>글 번호</td>
			<td>제목</td>
			<td>작성시간</td>
			<td>조회수</td>
			<td>작성자</td>
		</tr>
		<c:choose>
			<c:when test="${f:length(boardTable.boardList) != 0}">
				<c:forEach var="board" items="${boardTable.boardList}">
					<tr>
						<td>${board.boardNo }</td>
						<td>${board.boardTitle }</td>
						<td>${board.boardTime }</td>
						<td>${board.boardCount }</td>
						<td>${board.user.userInfo.subName }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="5" style="text-align: center;"> 글이 없습니다. </td></tr>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>