<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
	function replyModify(tag) {
		var trTag= $(tag).closest("tr");
		var contentTdTag = $(trTag.children()[2]);
		var contentDivTag = $(contentTdTag.children()[0]);
		
		contentDivTag.css("display","none");
		contentTdTag.append("<div><textarea style='width:100%;'>" + contentDivTag.html() + "</textarea>" +
		 "<button class='btn btn-default' onclick='successModify(this)'>완료</button><button class='btn btn-default' onclick='cancleModify(this)'>취소</button></div>");
	}
	
	function cancleModify(tag){
		var tdTag = $(tag).closest("td");
		var childrens = $(tdTag).children();
		$(childrens[0]).css('display','block');
		$(childrens[1]).remove();
	}
	
	function successModify(tag){
		var modifyContent = $($(tag).prev()).val();
		
		var replyNo = $($($(tag).closest("tr")).children()[0]).html();
		
		$.ajax({
			url : "/board/reply/modify",
			type : "post",
			data : {
				replyNo : replyNo,
				replyContent : modifyContent
			},
			success : function() {
				getReply(1);
			}
		});	
	}
	
	function replyDel(replyNo) {
		$.ajax({
			url : "/board/reply/del/"+replyNo,
			type : "post",
			success : function() {
				getReply(1);
			}
		});	
	}
	
	
</script>
<style>

</style>
<c:choose>
	<c:when test="${f:length(replyTable.replys) != 0}">
		<table class="table">
			<thead>
				<tr>
					<th style="width: 10%;">닉네임</th>
					<th>내용</th>
					<th style="width: 15%;">시간</th>
					<th style="width: 12%;"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reply" items="${replyTable.replys}">
					<tr>
						<td style="display: none">${reply.replyNo }</td>
						<td>${reply.user.subName }</td>
						<td>
							<div>${reply.replyContent }</div>
						</td>
						<td>${reply.replyDate }</td>
						<c:choose>
							<c:when test="${user.id == reply.user.id }">
								<td>
									<button class="btn btn-default" onclick="replyModify(this)">수정</button>
									<button class="btn btn-default" onclick="replyDel(${reply.replyNo})">삭제</button>
								</td>
							</c:when >
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
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
