<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>New Pet Details</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-5">
		<h1>New Pet Details</h1>
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
	<form:form action="/clients/${clientId}/pets/add-details" method="POST" modelAttribute="petDetailsForm">
    <input type="hidden" name="numberOfDogs" value="${numberOfDogs}"/>
    <input type="hidden" name="numberOfCats" value="${numberOfCats}"/>
    <div class="row">
        <div class="col-md-6">
            <h2 class="mb-4">Doggos</h2>
            <c:set var="numDogs" value="${numberOfDogs != null ? numberOfDogs : 0}" />
            <c:if test="${numDogs > 0}">
                <c:forEach var="i" begin="0" end="${numDogs - 1}">
                    <div class="mb-3">
                        <form:label path="dogNames[${i}]">Dog #${i + 1}'s Name:</form:label>
                        <form:input path="dogNames[${i}]" class="form-control" placeholder="Dog #${i + 1}'s Name"/>
                        <form:errors path="dogNames[${i}]" class="text-danger"/>
                    </div>
                    <div class="mb-3">
                        <form:label path="dogNotes[${i}]">Dog #${i + 1}'s Notes:</form:label>
                        <form:textarea path="dogNotes[${i}]" class="form-control" placeholder="Dog #${i + 1}'s Notes"/>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <div class="col-md-6">
            <h2 class="mb-4">Kitties</h2>
            <c:set var="numCats" value="${numberOfCats != null ? numberOfCats : 0}" />
            <c:if test="${numCats > 0}">
                <c:forEach var="i" begin="0" end="${numCats - 1}" >
                    <div class="mb-3">
                        <form:label path="catNames[${i}]">Cat #${i + 1}'s Name:</form:label>
                        <form:input path="catNames[${i}]" class="form-control" placeholder="Cat #${i + 1}'s Name"/>
                        <form:errors path="catNames[${i}]" class="text-danger"/>
                    </div>
                    <div class="mb-3">
                        <form:label path="catNotes[${i}]">Cat #${i + 1}'s Notes:</form:label>
                        <form:textarea path="catNotes[${i}]" class="form-control" placeholder="Cat #${i + 1}'s Notes"/>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="text-center mt-4">
        <button type="submit" class="btn btn-primary">Add Pets</button>
    </div>
</form:form>
</div>
</body>
</html>