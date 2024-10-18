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
        <!-- Client Information -->
        <h1 class="my-5">Client Information</h1>
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="card-title">${client.firstName} ${client.lastName}</h5>
                <p class="card-text"><strong>Address:</strong> ${client.address}</p>
                <p class="card-text"><strong>Phone Number:</strong> ${client.phoneNumber}</p>
                <p class="card-text"><strong>Price Quoted:</strong> $${client.priceQuoted}</p>
                <p class="card-text"><strong>Daily Visits Required:</strong> ${client.dailyVisits}</p>
            </div>
        </div>

        <!-- Pets Information -->
        <h2>Pets</h2>
        <c:choose>
            <c:when test="${not empty client.pets}">
                <table class="table table-striped table-bordered">
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

        <!-- Actions -->
        <div class="mt-4">
            <a href="/clients/${client.id}/edit" class="btn btn-primary">Edit Client Info</a>
            <a href="/clients/${client.id}/pets/edit" class="btn btn-secondary">Edit Pets</a>
            <a href="/sits/new?clientId=${client.id}" class="btn btn-success">Schedule a New Sit</a>
        </div>

        <!-- Back to all clients -->
        <div class="mt-5">
            <a href="/clients/all" class="btn btn-link">Back to Clients List</a>
        </div>
    </div>
</body>
</html>