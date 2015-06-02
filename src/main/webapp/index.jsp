<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>

	<h1>welcome</h1>
	这是首页欢迎
	<sec:authentication property="name" />
	!
	<br>
	<sec:authorize ifAllGranted="ROLE_ADMIN">
		<a href="book/add">book/add</a>
	</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_USER">
		<a href="book/update">book/update</a>
	</sec:authorize>
	<!-- ifAllGranted	ifAnyGranted	ifNotGranted -->

	<a href="../j_spring_security_logout">退出系统</a>
</body>
</html>
