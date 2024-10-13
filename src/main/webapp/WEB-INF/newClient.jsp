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
<title>Add New Client</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center mt-5 mb-3">
		<h1>New Client</h1>
		<div>
			<a href="/home" class="me-2">dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="Log out"/>
			</form>
		</div>
	</div>
	<h2>Client Info</h2>
	<form:form action="/clients/new" method="POST" modelAttribute="newClient">
		<div class="row mb-1">
			<div class="col-md-6 d-grid gap-3">
				<div class="col-md-3">
					<div class="form-floating mb-3">
						<form:input path="firstName" class="form-control" placeholder="First Name" />
						<form:label path="firstName">First Name</form:label>
						<form:errors path="firstName" class="text-danger"/>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-floating mb-3">
						<form:input path="lastName" class="form-control" placeholder="Last Name" />
						<form:label path="lastName">Last Name</form:label>
						<form:errors path="lastName" class="text-danger"/>						
					</div>
				</div>
				<div class="form-floating mb-3">
					<form:textarea path="address" class="form-control" placeholder="Address" />
					<form:label path="address">Address</form:label>
					<form:errors path="address" class="text-danger"/>
				</div>
				<div class="form-floating mb-3">
					<form:input path="phoneNumber" type="tel" class="form-control" placeholder="Phone Number" />
					<form:label path="phoneNumber">Phone Number</form:label>
					<form:errors path="phoneNumber" class="text-danger"/>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-floating mb-3">
				   <form:input path="priceQuoted" class="form-control" placeholder="Price Quoted" />
				   <form:label path="priceQuoted">Price Quoted</form:label>
				   <form:errors path="priceQuoted" class="text-danger"/>
				</div>
				<div class="form-floating mb-3">
				   <form:input path="dailyVisits" class="form-control" placeholder="Daily Visits" />
				   <form:label path="dailyVisits">Daily Visits</form:label>
				   <form:errors path="dailyVisits" class="text-danger"/>
				</div>
				<div>
					<form:label path="numberOfDogs" class="col-form-label">Number of Dogs:</form:label>
					<form:input path="numberOfDogs" id="numberOfDogs" type="number"/>
				</div>
				<div>
					<form:label path="numberOfCats" class="col-form-label">Number of Cats:</form:label>
					<form:input path="numberOfCats" id="numberOfCats" type="number"/>
				</div>
			</div>
		</div>
		<div class="row mb-1">
			<h2>Tell Us About Their Fur-Babies!</h2>
		</div>
		<div class="row mb-1">
			<div id="pet-info" class="col-md-12"></div>
		</div>
		<div class="row">
			<input type="submit" class="btn btn-secondary" value="Submit"/>
		</div>
	</form:form>
	<script>
	    document.getElementById('numberOfDogs').addEventListener('input', generatePetInputs);
	    document.getElementById('numberOfCats').addEventListener('input', generatePetInputs);
	
	    function generatePetInputs() {
	        // Use parseInt to ensure the values are numbers
	        const numberOfDogs = parseInt(document.getElementById('numberOfDogs').value) || 0;
	        const numberOfCats = parseInt(document.getElementById('numberOfCats').value) || 0;
	        const petInfoDiv = document.getElementById('pet-info');
	
	        let petInputs = '';
	
	        // Generate inputs for dogs
	        for (let i = 1; i <= numberOfDogs; i++) {
	            petInputs += `
	                <div class="form-floating mb-3">
	                    <input type="text" class="form-control" name="dog${i}Name" placeholder="Dog #${i} Name">
	                    <label for="dog${i}Name">Dog #${i}'s Name</label>
	                </div>
	                <div class="form-floating mb-3">
	                    <textarea class="form-control" name="dog${i}Notes" placeholder="Additional Notes for Dog #${i}"></textarea>
	                    <label for="dog${i}Notes">Additional Notes for Dog #${i}</label>
	                </div>`;
	        }
	
	        // Generate inputs for cats
	        for (let i = 1; i <= numberOfCats; i++) {
	            petInputs += `
	                <div class="form-floating mb-3">
	                    <input type="text" class="form-control" name="cat${i}Name" placeholder="Cat #${i} Name">
	                    <label for="cat${i}Name">Cat #${i}'s Name</label>
	                </div>
	                <div class="form-floating mb-3">
	                    <textarea class="form-control" name="cat${i}Notes" placeholder="Additional Notes for Cat #${i}"></textarea>
	                    <label for="cat${i}Notes">Additional Notes for Cat #${i}</label>
	                </div>`;
	        }
	
	        petInfoDiv.innerHTML = petInputs;
	    }
	</script>
</div>
</body>
</html>
