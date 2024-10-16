<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Edit Pet(s) Info!</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center mt-5 mb-3">
	<h1 class="my-4">Edit Pets for ${client.firstName} ${client.lastName}</h1>
		<div>
			<a href="/home" class="btn btn-link">dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="log out" class="btn btn-link"/>
			</form>
		</div>
	</div>
    <form:form action="/clients/${client.id}/pets/edit" method="POST" modelAttribute="petDetailsForm">
    <input type="hidden" name="_method" value="put" />
        <h2>Dogs</h2>
        <c:forEach var="pet" items="${client.pets}" varStatus="status">
            <c:if test="${pet.petType == 'dog'}">
                <div class="mb-3">
                    <form:label path="dogNames[${status.index}]" class="form-label">Dog Name:</form:label>
                    <form:input path="dogNames[${status.index}]" class="form-control" value="${pet.name}"/>
                </div>
                <div class="mb-3">
                    <form:label path="dogNotes[${status.index}]" class="form-label">Dog Notes:</form:label>
                    <form:textarea path="dogNotes[${status.index}]" class="form-control" value="${pet.notes}"/>
                </div>
            </c:if>
        </c:forEach>
        <div class="mb-3">
            <form:label path="dogNames[${client.numberOfDogs}]" class="form-label">New Dog Name:</form:label>
            <form:input path="dogNames[${client.numberOfDogs}]" class="form-control"/>
        </div>
        <div class="mb-3">
            <form:label path="dogNotes[${client.numberOfDogs}]" class="form-label">New Dog Notes:</form:label>
            <form:textarea path="dogNotes[${client.numberOfDogs}]" class="form-control"/>
        </div>

        <h2>Cats</h2>
        <c:forEach var="pet" items="${client.pets}" varStatus="status">
            <c:if test="${pet.petType == 'cat'}">
                <div class="mb-3">
                    <form:label path="catNames[${status.index}]" class="form-label">Cat Name:</form:label>
                    <form:input path="catNames[${status.index}]" class="form-control" value="${pet.name}"/>
                </div>
                <div class="mb-3">
                    <form:label path="catNotes[${status.index}]" class="form-label">Cat Notes:</form:label>
                    <form:textarea path="catNotes[${status.index}]" class="form-control" value="${pet.notes}"/>
                </div>
            </c:if>
        </c:forEach>
        <div class="mb-3">
            <form:label path="catNames[${client.numberOfCats}]" class="form-label">New Cat Name:</form:label>
            <form:input path="catNames[${client.numberOfCats}]" class="form-control"/>
        </div>
        <div class="mb-3">
            <form:label path="catNotes[${client.numberOfCats}]" class="form-label">New Cat Notes:</form:label>
            <form:textarea path="catNotes[${client.numberOfCats}]" class="form-control"/>
        </div>

        <button type="submit" class="btn btn-primary">Update Pets</button>
    </form:form>
</div>
</body>
</html>
