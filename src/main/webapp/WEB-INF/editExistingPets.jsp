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
    <form:form action="/clients/${client.id}/pets/update" method="POST" modelAttribute="petDetailsForm">
    <input type="hidden" name="_method" value="put" />
	    <c:forEach items="${client.pets}" var="pet" varStatus="status">
	        <div class="card mb-3">
	            <div class="card-body">
	                <form:hidden path="petIds[${status.index}]" value="${pet.id}" />
	                <form:hidden path="petTypes[${status.index}]" value="${pet.petType}" />
	                
	                <div class="mb-3">
	                    <form:label path="petNames[${status.index}]">Name:</form:label>
	                    <form:input path="petNames[${status.index}]" value="${pet.name}" class="form-control" />
	                </div>
	                
	                <div class="mb-3">
	                    <form:label path="petNotes[${status.index}]">Notes:</form:label>
	                    <form:textarea path="petNotes[${status.index}]" value="${pet.notes}" class="form-control" />
	                </div>
	                
	                <div class="form-check">
	                    <form:checkbox path="petsToDelete" value="${pet.id}" class="form-check-input" />
	                    <label class="form-check-label">Delete ${pet.name}</label>
	                </div>
	            </div>
	        </div>
	    </c:forEach>

	    <button type="submit" class="btn btn-primary">Update Pets</button>
	</form:form>
</div>
</body>
</html>
