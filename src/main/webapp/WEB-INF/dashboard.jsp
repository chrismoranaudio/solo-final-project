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
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>Welcome, <c:out value="${loggedInUser.firstName}"/>!</h1>
		<form action="/logout" method="POST" class="d-inline">
			<input type="submit" value="Log out" class="btn btn-danger ml-2"/>
		</form>
	</div>
	<div class="row ms-5">
		<div class="col-md-6 d-grid gap-3">
			<a href="/clients/all" class="btn btn-primary btn-lg">Client List</a>
			<a href="/clients/new" class="btn btn-success btn-lg">New Client</a>
			<a href="sits/all" class="btn btn-primary btn-lg">Booked Sits</a>
			<a href="/sits/new" class="btn btn-success btn-lg">New Sit</a>
		</div>
		<div class="col-md-6">
		
		</div>
	</div>
</div>
</body>
</html>