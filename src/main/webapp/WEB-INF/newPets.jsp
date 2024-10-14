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
<title>New Pet(s) Info!</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center mt-5 mb-3">
		<h1>New Client</h1>
		<div>
			<a href="/home" class="btn btn-link">dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="log out" class="btn btn-link"/>
			</form>
		</div>
	</div>
	<h2 class="mb-3">Pet(s) Info</h2>
	<form:form action="/clients/${client.id}/pets/new" method="POST" modelAttribute="client">
    
    <h2>Doggos</h2>
    <c:choose>
        <c:when test="${client.numberOfDogs > 0}">
            <c:set var="dogIndex" value="0" />
            <c:forEach var="pet" items="${client.pets}" varStatus="status">
                <c:if test="${pet.petType == 'DOG'}">
                    <c:set var="dogIndex" value="${dogIndex + 1}" />
                    <div class="mb-3">
                        <label for="pets[${status.index}].name">Dog #${dogIndex} Name:</label>
                        <form:input path="pets[${status.index}].name" class="form-control" placeholder="Dog #${dogIndex} Name" />
                    </div>
                    <div class="mb-3">
                        <label for="pets[${status.index}].notes">Dog #${dogIndex} Notes:</label>
                        <form:textarea path="pets[${status.index}].notes" class="form-control" placeholder="Dog #${dogIndex} Notes"></form:textarea>
                    </div>
                    <!-- Add a hidden field to bind the petType -->
                    <form:hidden path="pets[${status.index}].petType" />
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No Puppers currently...</p>
        </c:otherwise>
    </c:choose>

    <!-- Section for Cats -->
    <h2>Kitties</h2>
    <c:choose>
        <c:when test="${client.numberOfCats > 0}">
            <c:set var="catIndex" value="0" />
            <c:forEach var="pet" items="${client.pets}" varStatus="status">
                <c:if test="${pet.petType == 'CAT'}">
                    <c:set var="catIndex" value="${catIndex + 1}" />
                    <div class="mb-3">
                        <label for="pets[${status.index}].name">Cat #${catIndex} Name:</label>
                        <form:input path="pets[${status.index}].name" class="form-control" placeholder="Cat #${catIndex} Name" />
                    </div>
                    <div class="mb-3">
                        <label for="pets[${status.index}].notes">Cat #${catIndex} Notes:</label>
                        <form:textarea path="pets[${status.index}].notes" class="form-control" placeholder="Cat #${catIndex} Notes"></form:textarea>
                    </div>
                    <!-- Add a hidden field to bind the petType -->
                    <form:hidden path="pets[${status.index}].petType" />
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No Kitties... yet!</p>
        </c:otherwise>
    </c:choose>

    <input type="submit" class="btn btn-primary" value="Submit">
</form:form>
</div>
</body>
</html>
