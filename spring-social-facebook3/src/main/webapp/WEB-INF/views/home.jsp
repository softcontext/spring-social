<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<h6><mark>home.jsp</mark></h6>

	<h2>Welcome Home</h2>

	<c:if test="${empty loginUser }">
		<a href="login">Login</a>
	</c:if>
	
	<c:if test="${not empty loginUser }">
		<div>
			Login User Name : ${loginUser.email}
		</div>
		<div>
			<a href="login?logout">Logout</a>
		</div>
	</c:if>

</body>
</html>