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
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>Pet Details!</h1>
		<div>
			<a href="/home" class="btn btn-secondary">Dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="Log out" class="btn btn-danger ms-2"/>
			</form>
		</div>
	</div>
	<form:form action="/pets/detailsSubmission" method="POST" modelAttribute="petDetailsForm">
    <input type="hidden" name="clientId" value="${clientId}" />
	    <div class="row">
	        <div class="col-md-6">
	            <h2 class="mb-4">Doggos</h2>
	            <c:choose>
	                <c:when test="${numberOfDogs > 0}">
	                    <c:forEach var="i" begin="1" end="${numberOfDogs}">
	                        <div class="mb-3">
	                            <label for="dogNames[${i-1}]">Dog #${i} Name:</label>
	                            <form:input path="dogNames[${i-1}]" class="form-control" placeholder="Dog #${i} Name" />
	                        </div>
	                        <div class="mb-3">
	                            <label for="dogNotes[${i-1}]">Dog #${i} Notes:</label>
	                            <form:textarea path="dogNotes[${i-1}]" class="form-control" placeholder="Dog #${i} Notes"></form:textarea>
	                        </div>
	                    </c:forEach>
	                </c:when>
	                <c:otherwise>
	                    <p class="fs-4 ms-4">No Puppers currently...</p>
	                </c:otherwise>
	            </c:choose>
	        </div>
	        <div class="col-md-6">
	            <h2 class="mb-4">Kitties</h2>
	            <c:choose>
	                <c:when test="${numberOfCats > 0}">
	                    <c:forEach var="i" begin="1" end="${numberOfCats}">
	                        <div class="mb-3">
	                            <label for="catNames[${i-1}]">Cat #${i} Name:</label>
	                            <form:input path="catNames[${i-1}]" class="form-control" placeholder="Cat #${i} Name" />
	                        </div>
	                        <div class="mb-3">
	                            <label for="catNotes[${i-1}]">Cat #${i} Notes:</label>
	                            <form:textarea path="catNotes[${i-1}]" class="form-control" placeholder="Cat #${i} Notes"></form:textarea>
	                        </div>
	                    </c:forEach>
	                </c:when>
	                <c:otherwise>
	                    <p class="fs-4 ms-4">No Kitties... yet!</p>
	                </c:otherwise>
	            </c:choose>
	        </div>
	    </div>
	    <input type="submit" value="Submit" class="btn btn-primary" />
	</form:form>
</div>
</body>
</html>
