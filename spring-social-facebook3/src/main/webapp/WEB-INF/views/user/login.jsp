<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<h6><mark>login.jsp</mark></h6>

	<div>
		<h2>Login Form</h2>
	</div>

	<c:if test="${empty loginUser }">
		<h3>Email/Password Authentication</h3>

		<c:if test="${not empty error}">
			<h4>${error }</h4>
		</c:if>

		<form action="login" method="post">
			<table>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button type="submit">Login</button>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><a href="user/register">Create account</a></td>
				</tr>
			</table>
		</form>

		<h3>Sign in by using social provider</h3>

		<form action="/connect/facebook" method="post">
			<input type="hidden" name="scope" value="public_profile, email"/>
			<button type="submit">Facebook</button>
		</form>
	</c:if>

	<c:if test="${not empty loginUser }">
		<h3>You cannot login because you are already logged in.</h3>
		<div>
			Login User Name : ${loginUser.email}
		</div>
	</c:if>

</body>
</html>