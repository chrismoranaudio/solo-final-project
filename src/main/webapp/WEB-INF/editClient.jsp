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
<title>Edit Client</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center mt-5 mb-3">
		<h1>Edit <c:out value="${editClient.firstName} ${editClient.lastName}"/></h1>
		<div>
			<a href="/home" class="btn btn-link">dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="log out" class="btn btn-link"/>
			</form>
		</div>
	</div>
	<h2 class="mb-3">Client Info</h2>
	<form:form action="/clients/${editClient.id}/update" method="POST" modelAttribute="editClient">
		<input type="hidden" name="_method" value="put"/>
	    <div class="row mb-3">
	        <div class="col-md-6">
	            <div class="row">
	                <div class="col-md-6">
	                    <div class="form-floating mb-3">
	                        <form:input path="firstName" class="form-control" placeholder="First Name"/>
	                        <form:label path="firstName">First Name</form:label>
	                        <form:errors path="firstName" class="text-danger"/>
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-floating mb-3">
	                        <form:input path="lastName" class="form-control" placeholder="Last Name"/>
	                        <form:label path="lastName">Last Name</form:label>
	                        <form:errors path="lastName" class="text-danger"/>
	                    </div>
	                </div>
	            </div>
	        </div>
	
	        <div class="col-md-1"></div> 
	
	        <div class="col-md-5">
	            <div class="mb-3 row">
	                <label for="priceQuoted" class="col-md-4 col-form-label">Price Quoted:</label>
	                <div class="col-md-8">
	                    <div class="input-group">
	                        <span class="input-group-text">$</span>
	                        <form:input path="priceQuoted" class="form-control"/>
	                    </div>
	                    <form:errors path="priceQuoted" class="text-danger"/>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="row mb-3">
	        <div class="col-md-6">
	            <div class="form-floating mb-3">
	                <form:textarea path="address" class="form-control" placeholder="Address" style="height: 100px;"></form:textarea>
	                <form:label path="address">Address</form:label>
	                <form:errors path="address" class="text-danger"/>
	            </div>
	        </div>
	
	        <div class="col-md-1"></div> 
	
	        <div class="col-md-5">
	            <div class="mb-3 row">
	                <label for="dailyVisits" class="col-md-4 col-form-label">Daily Visits:</label>
	                <div class="col-md-8">
	                    <form:input path="dailyVisits" type="number" class="form-control"/>
	                    <form:errors path="dailyVisits" class="text-danger"/>
	                </div>
	            </div>
	
	            <div class="mb-3 row">
	                <label for="numberOfDogs" class="col-md-4 col-form-label">Number of Dogs:</label>
	                <div class="col-md-8">
	                    <form:input path="numberOfDogs" id="numberOfDogs" type="number" class="form-control"/>
	                    <form:errors path="numberOfDogs" class="text-danger"/>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="row mb-3">
	        <div class="col-md-6">
	            <div class="form-floating mb-3">
	                <form:input path="phoneNumber" type="tel" class="form-control" placeholder="Phone Number"/>
	                <form:label path="phoneNumber">Phone Number</form:label>
	                <form:errors path="phoneNumber" class="text-danger"/>
	            </div>
	        </div>
	
	        <div class="col-md-1"></div> 
	
	        <div class="col-md-5">
	            <div class="mb-3 row">
	                <label for="numberOfCats" class="col-md-4 col-form-label">Number of Cats:</label>
	                <div class="col-md-8">
	                    <form:input path="numberOfCats" id="numberOfCats" type="number" class="form-control"/>
	                    <form:errors path="numberOfCats" class="text-danger"/>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="row">
	        <div class="col-md-12 text-center">
	            <input type="submit" class="btn btn-primary btn-lg w-50" value="Submit"/>
	        </div>
	    </div>
	</form:form>
</div>
</body>
</html>
