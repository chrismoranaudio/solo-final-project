<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<meta charset="UTF-8">
<title>New Pet(s) Info!</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>Pet Details!</h1>
		<div>
			<a href="/home" class="btn btn-secondary">
    			<i class="fas fa-home"></i> Dashboard
			</a>
			<form action="/logout" method="POST" class="d-inline">
	    		<button type="submit" class="btn btn-danger ml-2">
	        		<i class="fas fa-sign-out-alt"></i> Log Out
	    		</button>
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
	                            <label for="dogNames[${i-1}]">Dog #${i}'s Name:</label>
	                            <form:input path="dogNames[${i-1}]" class="form-control" placeholder="Dog #${i}'s Name" />
	                            <form:errors path="dogNames[${i-1}]" class="text-danger"/>
	                        </div>
	                        <div class="mb-3">
	                            <label for="dogNotes[${i-1}]">Dog #${i}'s Notes:</label>
	                            <form:textarea path="dogNotes[${i-1}]" class="form-control" placeholder="Dog #${i}'s Notes"></form:textarea>
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
	                            <label for="catNames[${i-1}]">Cat #${i}'s Name:</label>
	                            <form:input path="catNames[${i-1}]" class="form-control" placeholder="Cat #${i}'s Name" />
	                            <form:errors path="catNames[${i-1}]" class="text-danger"/>
	                        </div>
	                        <div class="mb-3">
	                            <label for="catNotes[${i-1}]">Cat #${i}'s Notes:</label>
	                            <form:textarea path="catNotes[${i-1}]" class="form-control" placeholder="Cat #${i}'s Notes"></form:textarea>
	                        </div>
	                    </c:forEach>
	                </c:when>
	                <c:otherwise>
	                    <p class="fs-4 ms-4">No Kitties... yet!</p>
	                </c:otherwise>
	            </c:choose>
	        </div>
	    </div>
	    <div class="text-center mt-4">
	        <button type="submit" class="btn btn-primary">Add Pets</button>
	    </div>
	</form:form>
</div>
</body>
</html>
