<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet"/>
<script src="/assert/js/bootstrap.min.js"></script>
<title>로그인</title>

<script>
	$()

</script>

</head>
<body class="container">

	<div style="display: table; height: 100%; margin: 0 auto;">
		<div class="text-center" style="display: table-cell; vertical-align: middle; width: 500px;">
			<form action="/auth/login" method="post">
				
				<div class="input-group">
					<span class="input-group-addon">아이디 : </span> 
					<input class="form-control" name="id" type="text" />
				</div>
				
				<div class="input-group" style="margin-top: 10px;">
					<span class="input-group-addon">비밀번호 : </span> 
					<input class="form-control" name="password" type="password" />
				</div>
				<input class="btn btn-default"  style="margin-top: 10px;" type="submit" value="로그인" />
			</form>
			<button class="btn btn-default" onclick="location.href='/auth/join'">회원가입</button>
		</div>

	</div>
</body>
</html>