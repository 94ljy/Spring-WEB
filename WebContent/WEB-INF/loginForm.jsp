<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	
</script>
</head>
<body>

	<div
		style="display: table; width: 100%; height: 100%; text-align: center;">
		<div style="display: table-cell; vertical-align: middle;">
			<form action="/auth/login" method="post">
				<p>
					���̵� : <input name="id" type="text" />
				</p>
				<p>
					��й�ȣ : <input name="password" type="password" />
				</p>
				<input type="submit" value="�α���" />
			</form>
			<div style="text-align: center">
				<a href="/auth/join">ȸ������</a>
			</div>
		</div>

	</div>
</body>
</html>