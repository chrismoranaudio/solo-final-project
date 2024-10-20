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
<title>Add New Client</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>New Client</h1>
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
	<h2 class="mb-3">Client Info</h2>
	<form:form action="/clients/new" method="POST" modelAttribute="newClient">
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
                <div class="col-md-8"></div>
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
                <div class="col-md-8">
                	<input type="submit" class="btn btn-primary btn-lg" value="How Many Fur-Babies!?!"/>   
                </div>
            </div>
        </div>
    </div>
	</form:form>
</div>
</body>
</html>
