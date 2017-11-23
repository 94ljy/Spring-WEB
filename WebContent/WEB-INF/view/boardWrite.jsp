<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet"/>
<script src="/assert/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<title>글 작성</title>
<script>
	$(function() {
		$("#editor").summernote({
			height : 500,
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					uploadFile(files, editor, welEditable)
				}
			}
		});
	});
	
  	function uploadFile(files, editor, welEditable) {
  		
  		var form = new FormData();
  		form.append("file", files[0]);
  		
		$.ajax({
			url : "/file",
			type : "post",
			data : form,
			enctype : "multipart/form-data", 
			processData : false,
			contentType: false,
			success : function(response) {
				console.log(response);
				$("#editor").summernote('insertImage', response.url);
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
  	
  	function writeBoard() {
  		var editor = $("#editor");
		$("#content").val(editor.summernote("code"));
		$("#form").submit();
	}
	
</script>
</head>
<body class="container" style="height: 100vh;" >
	<div style="display: table; width: 100%; height: 100%">
		<div style="display: table-cell; vertical-align: middle;">
			<h2>글 작성</h2>
			<hr>
			<form id="form" action="/board/write" method="post">
				<div class="input-group"> 
					<span class="input-group-addon">제목</span> 
					<input type="text" class="form-control" name="boardTitle"  /> 
				</div>
			 	<div id="editor">
			 	</div>
			 	<input id="content" type="hidden" name="boardContent"/>
				 <div class="text-center" style="margin-top:20px;">
					<button class="btn btn-default" onclick="writeBoard()">작성완료</button>
				 </div>
			</form>
		</div>
	</div>
</body>
</html>