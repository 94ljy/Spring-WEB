<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet" />
<script src="/assert/js/bootstrap.min.js"></script>
<title>${board.boardTitle }</title>
<style>
hr {
	border-color: #454545
}
</style>
<script>
	var pageBoardNo = ${board.boardNo};
	
	function getReply(page) {
		const divReply = $('#reply');
		$.ajax({
			type : 'post',
			url : '/board/reply/'+ pageBoardNo + '/page/'+ page,
			success : function(response) {
				divReply.html("");
				divReply.html(response);
			}
		});
	}
	
	function boardDel(boardNo) {
		if(confirm("게시글을 삭제합니다.")){
			location.href="/board/del/"+boardNo;	
		}
	}
	
	
	$(function() {
		$('#writeReply').click(function() {
			const reply = $('#replyText').val();
			$.ajax({
				type : 'post',
				url : '/board/reply/write',
				data : {
					boardNo : pageBoardNo,
					replyContent : reply
				},
				success : function(response) {
					getReply(1);
					$('#replyText').val("");
				},error : function() {
					alert("다시 시도해주세요");
				}
			});
		});
		
	});
</script>
</head>
<body class="container">

	<div class="board-view container" style="margin: 0 auto;">
		<div class="board-title " style="margin-bottom: 15px;">
			<h2>제목 : ${board.boardTitle }</h2>
		</div>
		<hr>
		<div class="board-info" style="padding-top: 4px; padding-bottom: 4px;">
			<b> <span>닉네임 : ${board.user.subName }님</span> <span
				style="float: right;">작성일 : ${board.boardTime }</span> <span
				style="float: right; margin-right: 10px;">조회수:${board.boardCount }</span>
			</b>
		</div>
		<hr>
		<div class="board-content" style="height: auto; min-height: 300px;">
			<p>${board.boardContent }</p>
		</div>
		<div style="text-align: right;">
			<c:if test="${user.id == board.user.id}">
				<button class="btn btn-default" onclick="location.href='/board/modify/${board.boardNo}'">수정</button>
				<button class="btn btn-default" onclick="boardDel(${board.boardNo})">삭제</button>
			</c:if>
		</div>
		<hr>
		<div class="board-reply">
			<div id="reply">
				<%@ include file="/WEB-INF/boardReply.jsp"  %>
			</div>
			<c:if test="${user != null}">
				<div class="input-group">
					<textarea id="replyText" class="form-control" rows="3"></textarea>
					<span id="writeReply" class="input-group-addon btn">댓글 달기</span>
				</div>
			</c:if>
		</div>
		<div>
			<button class="btn btn-default" onclick="location.href='/board/${page}'">목록으로</button>
		</div>
	</div>

</body>
</html>