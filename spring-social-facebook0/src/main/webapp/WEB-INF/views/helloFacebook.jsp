<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::</title>
</head>
<body>

	<h2>My Site Home</h2>

	<c:if test="${not empty facebookProfile }">
		<h3>Hello, ${facebookProfile.name }</h3>
		
		<c:if test="${not empty feed }">
			<h3>Here is your feed:</h3>
			<table>
				<tr>
					<td>feed.from.name</td>
					<td>${feed.from.name }</td>
				</tr>
				<tr>
					<td>feed.message</td>
					<td>${feed.message }</td>
				</tr>
				<c:if test="${not empty feed.picture }">
					<tr>
						<td>feed.picture</td>
						<td>${feed.picture }</td>
					</tr>
				</c:if>
			</table>
		</c:if>
	</c:if>

</body>
</html>