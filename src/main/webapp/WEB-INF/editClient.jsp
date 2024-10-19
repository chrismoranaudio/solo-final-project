<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Edit Client</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>Edit <c:out value="${client.firstName} ${client.lastName}"/></h1>
		<div>
			<a href="/home" class="btn btn-secondary">Dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="Log out" class="btn btn-danger ms-2"/>
			</form>
		</div>
	</div>
	<h2 class="mb-3">Client Info</h2>
	<form:form action="/clients/${client.id}/edit" method="POST" modelAttribute="client">
	<input type="hidden" name="_method" value="put"/>
        <div class="mb-3">
            <form:label path="firstName" class="form-label">First Name:</form:label>
            <form:input path="firstName" class="form-control"/>
            <form:errors path="firstName" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label path="lastName" class="form-label">Last Name:</form:label>
            <form:input path="lastName" class="form-control"/>
            <form:errors path="lastName" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label path="address" class="form-label">Address:</form:label>
            <form:textarea path="address" class="form-control"/>
            <form:errors path="address" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label path="phoneNumber" class="form-label">Phone Number:</form:label>
            <form:input path="phoneNumber" class="form-control"/>
            <form:errors path="phoneNumber" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label path="priceQuoted" class="form-label">Price Quoted:</form:label>
            <form:input path="priceQuoted" class="form-control"/>
            <form:errors path="priceQuoted" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label path="dailyVisits" class="form-label">Daily Visits:</form:label>
            <form:input path="dailyVisits" type="number" class="form-control"/>
            <form:errors path="dailyVisits" class="text-danger"/>
        </div>
        <button type="submit" class="btn btn-primary">Update Client</button>
    </form:form>
    <form action="/clients/${client.id}/delete" method="POST" style="display: inline;">
        <input type="hidden" name="_method" value="delete"/>
        <button type="submit" class="btn btn-danger mt-2"
            onclick="return confirm('Are you sure you want to delete this client?');">Delete Client
        </button>
    </form>
</div>
</body>
</html>
