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
			<p>���̵� : <input name="id" type="text" value="${user.id}"/> </p>
			
			<p>��й�ȣ : <input name="password" type="password" /> </p>
			
			<p>�̸� : <input name="name" type="text" value="${user.userInfo.name}"/></p>
		
			<p>�̸��� : <input name="email" type="email" value="${user.userInfo.email}"/></p>
			
			<p>�г��� : <input name="subName" type="text" value="${user.userInfo.subName}"/></p>
			
			<p>��ȭ��ȣ : <input name="phoneNumber" type="text" value="${user.userInfo.phoneNumber}"/></p>
			
			<input type="submit"/>
		</form>
	</div>
</body>
</html>