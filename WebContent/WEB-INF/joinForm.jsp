<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<div style="display: table; text-align: center; height: 100%; width:100%;">
		<form action="/auth/join" method="post" style="display:table-cell; vertical-align: middle;">
			<p>아이디 : <input name="id" type="text" value="${user.id}"/> </p>
			
			<p>비밀번호 : <input name="password" type="password" /> </p>
			
			<p>이름 : <input name="name" type="text" value="${user.password}"/></p>
		
			<p>이메일 : <input name="email" type="email" value="${user.email}"/></p>
			
			<p>닉네임 : <input name="subName" type="text" value="${user.subName}"/></p>
			
			<p>전화번호 : <input name="phoneNumber" type="text" value="${user.phoneNumber}"/></p>
			
			<input type="submit"/>
		</form>
	</div>
</body>
</html>