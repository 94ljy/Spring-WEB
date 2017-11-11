<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:choose>
	<c:when test="${f:length(replyTable.replys) != 0}">
		<table class="table">
			<thead>
				<tr>
					<th style="width: 10%;">닉네임</th>
					<th>내용</th>
					<th style="width: 15%;">시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reply" items="${replyTable.replys}">
					<tr>
						<td>${reply.user.userInfo.subName}</td>
						<td>${reply.replyContent }</td>
						<td>${reply.replyDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class=" text-center">
			<ul class="pagination">
				<c:forEach var="i" begin="${replyTable.firstPage}"
					end="${replyTable.lastPage}">
					<c:choose>
						<c:when test="${replyTable.nowPage == i }">
							<li><span style="color: red;"><b>${ i}</b></span></li>
						</c:when>
						<c:otherwise>
							<li><span onclick="getReply(${i})">${ i}</span></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<h3 class="text-center">댓글이 없습니다.</h3>
	</c:otherwise>
</c:choose>
