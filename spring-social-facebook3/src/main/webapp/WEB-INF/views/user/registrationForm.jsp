<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<h6><mark>registrationForm.jsp</mark></h6>

	<div>
		<h2>
			Registration Form
		</h2>
	</div>

	<c:if test="${empty loginUser }">
		<c:url var="link" value="/user/register"/>
		<form action="${link }" method="post">
			<table>
				<tr>
					<td>Email</td>
					<td>
						<input type="text" name="email" />
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Password Verification</td>
					<td><input type="password" name="passwordVerification" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button type="submit">Submit</button>
					</td>
				</tr>
			</table>
		</form>
	</c:if>

	<c:if test="${not empty loginUser }">
		<h3>You cannot register because you are already logged in.</h3>
		<div>
			Login User Name : ${loginUser.email}
		</div>
	</c:if>

</body>
</html>