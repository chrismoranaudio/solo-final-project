<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Dashboard</title>
</head>
<body>
<div class="container">
	<h1 class="my-5">Welcome, <c:out value="${loggedInUser.firstName}"/>!</h1>
	<form action="/logout" method="POST">
		<input type="submit" value="log out" class="btn btn-link mb-5"/>
	</form>
	<div class="row">
		<div class="col-md-6 d-grid gap-3">
			<a href="/clients/all" class="btn btn-secondary btn-lg">Client List</a>
			<a href="/clients/new" class="btn btn-secondary btn-lg">New Client</a>
			<a href="sits/all" class="btn btn-secondary btn-lg">Booked Sits</a>
			<a href="/sits/new" class="btn btn-secondary btn-lg">New Sit</a>
		</div>
		<div class="col-md-6">
		
		</div>
	</div>
</div>
</body>
</html>