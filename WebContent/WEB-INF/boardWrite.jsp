<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet"/>
<script src="/assert/js/bootstrap.min.js"></script>
<title>글 작성</title>
</head>
<body style="width:100%; height:100vh; margin: 0; padding:0; align-items: center;">
	<div class="board-write container" style="">
		<form action="/board/write" method="post">
			<div class="input-group"> 
				<span class="input-group-addon">제목</span> 
				<input type="text" class="form-control" name="boardTitle"  /> 
			</div>
			<div class="input-group" style="margin-top:20px;">
				<h3 class="input-group-addon">본문</h3> 
				<textarea class="form-control" name="boardContent" rows="20" style="width:100%;"></textarea>
			 </div>
			 <div class="text-center" style="margin-top:20px;">
				<input class="btn btn-default" type="submit" value="작성완료"/>
			 </div>
		</form>
	</div>
</body>
</html>