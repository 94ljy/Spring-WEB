<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet"/>
<script src="/assert/js/bootstrap.min.js"></script>
<script src="/assert/js/jquery.splendid.textchange.js"></script>
<style>

hr{
	border-color: #454545;
}

.input-group-addon{
	min-width: 115px;
}

.input-group{
	margin-top: 10px;
}

.true{
	color: green;
}

.false{
	color: red;
}

</style>
<title>회원가입</title>
<script>
	var idCheck = false;
	var subNameCheck = false;
	var passwordCheck = false;
	
	function pwCheck() {
		var pw = $('input[name=password]').val();
		var pwCheck = $('#pwCheck').val();
		
		if(pw == pwCheck){
			passwordCheck = true;
			$('#pwCheckAlert').removeClass('false');
			$('#pwCheckAlert').addClass('true');
		}else{
			passwordCheck = false;
			$('#pwCheckAlert').removeClass('true');
			$('#pwCheckAlert').addClass('false');
		}
	}

	$(function() {
		$('#join').click(function() {
			$('#joinform').submit();
		});
		
		$('#idCheck').click(function() {
			const id = $('input[name=id]').val();
			$.ajax({
				type: 'get',
				url: '/auth/join/idCheck/'+id,
				success : function(response) {
					if(response.able){
						idCheck = true;
						alert("사용 가능한 아이디 입니다.");
						$('input[name=id]').attr('readonly', true);
					}
					else{
						idCheck = false;
						alert("사용 불가능한 아이디 입니다.");
					}
				}
			});
		});
		
		$('#subNameCheck').click(function() {
			const subName = $('input[name=subName]').val();
			$.ajax({
				type: 'get',
				url: '/auth/join/subNameCheck/'+ subName,
				success : function(response) {
					if(response.able){
						subNameCheck = true;
						alert("사용 가능한 닉네임 입니다.");
						$('input[name=subName]').attr('readonly', true);
					}
					else{
						subNameCheck = false;
						alert("사용 불가능한 닉네임 입니다.");
					}
				}
			});
		});
		
		$('#pwCheck').on('textchange', pwCheck);
		$('input[name=password]').on('textchange', pwCheck);
		
	});

</script>
</head>
<body class="container">

	<div style="display: table; width:500px; height: 100%; margin: 0 auto;">
		<form id="joinform" action="/auth/join" method="post" style="display:table-cell; vertical-align: middle;">
			<h2>회원가입</h2>
			<hr>
			<div class="input-group">
				<span class="input-group-addon">아이디</span> 
				<input class="form-control" name="id" type="text" value="${user.id}"/>
				<span id="idCheck" class="input-group-addon btn" style="min-width: auto;">중복체크</span>
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">비밀번호</span> 
				<input class="form-control" name="password" type="password" />
			</div>
			
			<div class="input-group">
				<span id="pwCheckAlert" class="input-group-addon">비밀번호 확인</span> 
				<input id="pwCheck" class="form-control" type="password"/>
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">이름</span> 
				<input class="form-control" name="name" type="text" value="${user.name}"/>
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">이메일</span> 
				<input class="form-control" name="email" type="email" value="${user.email}"/>
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">닉네임</span> 
				<input class="form-control" name="subName" type="text" value="${user.subName}"/>
				<span id="subNameCheck" class="input-group-addon btn" style="min-width: auto;">중복체크</span>
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">전화번호</span> 
				<input class="form-control" name="phoneNumber" type="text" value="${user.phoneNumber}"/>
			</div>
			
			<button id="join" class="btn btn-default" type="button" style="margin-top:10px; width: 100%;">작성 완료</button>
		</form>
	</div>
</body>
</html>