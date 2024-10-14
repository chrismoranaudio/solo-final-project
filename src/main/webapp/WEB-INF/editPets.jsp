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
		<h1>Edit <c:out value="${client.firstName}"/>'s Pet(s)</h1>
		<div>
			<a href="/home" class="btn btn-link">dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="log out" class="btn btn-link"/>
			</form>
		</div>
	</div>
	<h2 class="mb-3">Pet(s) Info</h2>
	<form:form action="/clients/${client.id}/pets/update" method="POST" modelAttribute="clientUpdate">
	    <input type="hidden" name="_method" value="put"/>
	    
	    <h2>Existing Pets</h2>
	    <c:forEach var="pet" items="${clientUpdate.existingPets}" varStatus="status">
	        <div class="mb-3">
	            <label for="existingPets[${status.index}].name">${pet.petType} Name:</label>
	            <form:input path="existingPets[${status.index}].name" class="form-control"/>
	        </div>
	        <div class="mb-3">
	            <label for="existingPets[${status.index}].notes">${pet.petType} Notes:</label>
	            <form:textarea path="existingPets[${status.index}].notes" class="form-control"/>
	        </div>
	        <form:hidden path="existingPets[${status.index}].id"/>
	        <form:hidden path="existingPets[${status.index}].petType"/>
	    </c:forEach>
	
	    <h2>New Dogs</h2>
	    <c:forEach begin="1" end="${newDogs}" varStatus="status">
	        <div class="mb-3">
	            <label for="newDogs[${status.index-1}].name">New Dog #${status.index} Name:</label>
	            <input type="text" name="newDogs[${status.index-1}].name" class="form-control"/>
	        </div>
	        <div class="mb-3">
	            <label for="newDogs[${status.index-1}].notes">New Dog #${status.index} Notes:</label>
	            <textarea name="newDogs[${status.index-1}].notes" class="form-control"></textarea>
	        </div>
	    </c:forEach>
	
	    <h2>New Cats</h2>
	    <c:forEach begin="1" end="${newCats}" varStatus="status">
	        <div class="mb-3">
	            <label for="newCats[${status.index-1}].name">New Cat #${status.index} Name:</label>
	            <input type="text" name="newCats[${status.index-1}].name" class="form-control"/>
	        </div>
	        <div class="mb-3">
	            <label for="newCats[${status.index-1}].notes">New Cat #${status.index} Notes:</label>
	            <textarea name="newCats[${status.index-1}].notes" class="form-control"></textarea>
	        </div>
	    </c:forEach>
	
	    <input type="submit" class="btn btn-primary" value="Submit">
	</form:form>
</div>
</body>
</html>
