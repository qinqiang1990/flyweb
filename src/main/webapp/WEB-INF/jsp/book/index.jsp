<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEIZU MX3</title>
</head>
<body>
	${message}

	<sec:authentication property="name" />
	<br />
	<sec:authorize ifAllGranted="ROLE_ADD">
		<a href="/book/add">book/add</a>
		<br />
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_UPD">
		<a href="/book/update">book/update</a>
		<br />
	</sec:authorize>





	<a href="/auth/logout">推出系统</a>
</body>
</html>
