<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::</title>
</head>
<body>

	<h2>Connect to Facebook</h2>

	<form action="/connect/facebook" method="POST">
		<input type="hidden" name="scope" value="user_posts" />
		<div>
			<p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
		</div>
		<div>
			<button type="submit">Connect to Facebook</button>
		</div>
	</form>

</body>
</html>