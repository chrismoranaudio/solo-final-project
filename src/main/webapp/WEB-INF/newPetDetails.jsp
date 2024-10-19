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
<title>New Pet Details</title>
</head>
<body>
<div class="container">
    <h1 class="my-5">New Pet Details</h1>
    <form:form action="/clients/${clientId}/pets/add-details" method="POST" modelAttribute="petDetailsForm">
        <h2>Dogs</h2>
        <c:set var="numDogs" value="${numberOfDogs != null ? numberOfDogs : 0}" />
        <c:if test="${numDogs > 0}">
            <c:forEach begin="0" end="${numDogs - 1}" var="index">
                <div class="mb-3">
                    <form:label path="dogNames[${index}]">Dog #${index + 1}'s Name:</form:label>
                    <form:input path="dogNames[${index}]" class="form-control"/>
                    <form:errors path="dogNames[${index}]" class="text-danger"/>
                </div>
                <div class="mb-3">
                    <form:label path="dogNotes[${index}]">Dog #${index + 1}'s Notes:</form:label>
                    <form:textarea path="dogNotes[${index}]" class="form-control"/>
                </div>
            </c:forEach>
        </c:if>
        <h2>Cats</h2>
        <c:set var="numCats" value="${numberOfCats != null ? numberOfCats : 0}" />
        <c:if test="${numCats > 0}">
            <c:forEach begin="0" end="${numCats - 1}" var="index">
                <div class="mb-3">
                    <form:label path="catNames[${index}]">Cat #${index + 1}'s Name:</form:label>
                    <form:input path="catNames[${index}]" class="form-control"/>
                    <form:errors path="catNames[${index}]" class="text-danger"/>
                </div>
                <div class="mb-3">
                    <form:label path="catNotes[${index}]">Cat #${index + 1}'s Notes:</form:label>
                    <form:textarea path="catNotes[${index}]" class="form-control"/>
                </div>
            </c:forEach>
        </c:if>
        <button type="submit" class="btn btn-primary">Add Pets</button>
    </form:form>
</div>
</body>
</html>