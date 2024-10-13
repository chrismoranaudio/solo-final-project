<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Pet Sitter's Application</title>
</head>
<body>
<div class="container">
	<h1 class="display-4 my-5 text-primary fw-bold text-center">Pet Sitter's United Against Jeff Bezos</h1>
	<div class="row">
		<div class="col-sm-12 col-md-6">
			<h2 class="display-5 mb-3">Register</h2>
			<div class="card shadow mb-3">
				<div class="card-body">
					<form:form action="/register" method="POST" modelAttribute="newUser">
						<div class="mb-3">
							<form:label path="firstName" class="form-label">First Name:</form:label>
							<form:errors path="firstName" class="text-danger" />
							<form:input path="firstName" class="form-control" />
						</div>
						<div class="mb-3">
							<form:label path="lastName" class="form-label">Last Name:</form:label>
							<form:errors path="lastName" class="text-danger" />
							<form:input path="lastName" class="form-control" />
						</div>
						<div class="mb-3">
							<form:label path="email" class="form-label">Email:</form:label>
							<form:errors path="email" class="text-danger" />
							<form:input path="email" class="form-control" />
						</div>
						<div class="mb-3">
							<form:label path="password" class="form-label">Password:</form:label>
							<form:errors path="password" class="text-danger" />
							<form:input type="password" path="password" class="form-control" />
						</div>
						<div class="mb-3">
							<form:label path="confirm" class="form-label">Confirm Password:</form:label>
							<form:errors path="confirm" class="text-danger" />
							<form:input type="password" path="confirm" class="form-control" />
						</div>
						<div class="text-end">
							<button class="btn btn-primary" type="submit">Submit</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-12 col-md-6">
			<h2 class="display-5 mb-3">Log in</h2>
			<div class="card shadow mb-3">
				<div class="card-body">
					<form:form action="/login" method="POST" modelAttribute="newLogin">
						<div class="mb-3">
							<form:label path="email" class="form-label">Email:</form:label>
							<form:errors path="email" class="text-danger" />
							<form:input path="email" class="form-control" />
						</div>
						<div class="mb-3">
							<form:label path="password" class="form-label">Password:</form:label>
							<form:errors path="password" class="text-danger" />
							<form:input type="password" path="password" class="form-control" />
						</div>
						<div class="text-end">
							<button class="btn btn-primary" type="submit">Submit</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>