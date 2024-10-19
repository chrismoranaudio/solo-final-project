<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>View Client</title>
</head>
<body>
<div class="container">
<%-- 	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>${client.firstName} ${client.lastName}</h1>
		<div>
			<a href="/home" class="btn btn-secondary">Dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="Log out" class="btn btn-danger ms-2"/>
			</form>
		</div>
	</div>
	<div class="row">
	    <!-- Left side: Client Information -->
	    <div class="col-md-6">
	        <div class="card mb-4">
	            <div class="card-body">
	                <h4 class="card-title mb-4">Client Information</h4>
	                <p class="card-text ms-4"><strong>Address:</strong> ${client.address}</p>
	                <p class="card-text ms-4"><strong>Phone Number:</strong> ${client.phoneNumber}</p>
	                <p class="card-text ms-4"><strong>Price Quoted:</strong> $${client.priceQuoted}</p>
	                <p class="card-text ms-4"><strong>Daily Visits Required:</strong> ${client.dailyVisits}</p>
	            </div>
	        </div>
	        <div class="mt-5">
	            <a href="/clients/${client.id}/edit" class="btn btn-primary">Edit Client Info</a>
	            <form action="/clients/${client.id}/delete" method="POST" style="display: inline;">
	                <input type="hidden" name="_method" value="delete"/>
	                <button type="submit" class="btn btn-danger"
	                    onclick="return confirm('Are you sure you want to delete this client?');">Delete Client
	                </button>
	            </form>
	        </div>
	        <a href="/clients/all" class="btn btn-warning mt-2">Back to Clients List</a>
	    </div>
	
	    <!-- Right side: Pets Information -->
	    <div class="col-md-6">
	        <h2 class="mb-5">Pets</h2>
	        <c:choose>
	            <c:when test="${not empty client.pets}">
	                <table class="table table-striped table-bordered text-center">
	                    <thead>
	                        <tr>
	                            <th>Name</th>
	                            <th>Type</th>
	                            <th>Notes</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <c:forEach var="pet" items="${client.pets}">
	                            <tr>
	                                <td>${pet.name}</td>
	                                <td>${pet.petType}</td>
	                                <td>${pet.notes}</td>
	                            </tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
	            </c:when>
	            <c:otherwise>
	                <p>No pets found for this client.</p>
	            </c:otherwise>
	        </c:choose>
	
	        <div class="mt-4">
	            <a href="/clients/${client.id}/pets/edit" class="btn btn-primary">Edit Pets</a>
	            <a href="/sits/new?clientId=${client.id}" class="btn btn-success">Schedule a New Sit</a>
	        </div>
	    </div>
	</div> --%>
	<div class="card mb-4">
	    <div class="card-body">
	        <h4 class="card-title mb-4">Client Information</h4>
	        <p class="card-text ms-4"><strong>Address:</strong> ${client.address}</p>
	        <p class="card-text ms-4"><strong>Phone Number:</strong> ${client.phoneNumber}</p>
	        <p class="card-text ms-4"><strong>Price Quoted:</strong> $${client.priceQuoted}</p>
	        <p class="card-text ms-4"><strong>Daily Visits Required:</strong> ${client.dailyVisits}</p>
	    </div>
	</div>
	<div class="mt-5">
		<a href="/clients/${client.id}/edit" class="btn btn-primary">Edit Client Info</a>
		<form action="/clients/${client.id}/delete" method="POST" style="display: inline;">
   		<input type="hidden" name="_method" value="delete"/>
		   	<button type="submit" class="btn btn-danger"
		   		onclick="return confirm('Are you sure you want to delete this client?');">Delete Client
	   		</button>
	    </form>
	</div>
	<a href="/clients/all" class="btn btn-warning mt-2">Back to Clients List</a>
	<h2 class="mb-5">Pets</h2>
	<c:choose>
	    <c:when test="${not empty client.pets}">
	        <table class="table table-striped table-bordered text-center">
	            <thead>
	                <tr>
	                    <th>Name</th>
	                    <th>Type</th>
	                    <th>Notes</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="pet" items="${client.pets}">
	                    <tr>
	                        <td>${pet.name}</td>
	                        <td>${pet.petType}</td>
	                        <td>${pet.notes}</td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </c:when>
	    <c:otherwise>
	        <p>No pets found for this client.</p>
	    </c:otherwise>
	</c:choose>
	<div class="mt-4">
	   	<a href="/clients/${client.id}/pets/edit" class="btn btn-primary">Edit Pets</a>
	   	<a href="/sits/new?clientId=${client.id}" class="btn btn-success">Schedule a New Sit</a>
	</div>
</div>
</body>
</html>