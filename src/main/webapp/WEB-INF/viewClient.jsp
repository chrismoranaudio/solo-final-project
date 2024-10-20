<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>View Client</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-5">
        <h1>${client.firstName} ${client.lastName}</h1>
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
    <div class="row">
        <div class="col-md-6">
            <div class="card mb-4 bg-light">
                <div class="card-body">
                    <h4 class="card-title mb-4">Client Information</h4>
                    <p class="card-text"><strong>Address:</strong> ${client.address}</p>
                    <p class="card-text"><strong>Phone Number:</strong> ${formattedPhoneNumber}</p>
                    <p class="card-text"><strong>Price Quoted:</strong> $<fmt:formatNumber value="${client.priceQuoted}" maxFractionDigits="0"/></p>
                    <p class="card-text"><strong>Daily Visits Required:</strong> ${client.dailyVisits}</p>
                </div>
            </div>
            <div class="mt-2">
                <a href="/clients/${client.id}/edit" class="btn btn-primary">Edit Client Info</a>
                <form action="/clients/${client.id}/delete" method="POST" style="display: inline;">
                    <input type="hidden" name="_method" value="delete"/>
                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this client?');">Delete Client</button>
                </form>
            </div>
            <a href="/clients/all" class="btn btn-warning mt-2 mb-5">Back to Client List</a>
        </div>
        <div class="col-md-6">
            <div class="card mb-4 bg-light">
                <div class="card-body">
                    <h4 class="card-title mb-4">Pet Information</h4>
                    <c:choose>
                        <c:when test="${not empty client.pets}">
                            <c:forEach var="pet" items="${client.pets}">
                                <p><strong>${pet.name} (${pet.petType}):</strong> ${pet.notes}</p>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No pets found for this client.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="mt-4">
                <a href="/clients/${client.id}/pets/edit" class="btn btn-primary">Edit Pets</a>
                <a href="/sits/new?clientId=${client.id}" class="btn btn-success">Schedule a New Sit</a>
            </div>
        </div>
    </div>
    <c:if test="${not empty currentSits}">
        <h2 class="my-5">Current Sits</h2>
        <table class="table table-striped table-bordered text-center">
            <thead>
                <tr>
                    <th>Start Date</th>
                    <th>First Visit</th>
                    <th>End Date</th>
                    <th>Final Visit</th>
                    <th>Daily Visits</th>
                    <th>Notes</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sit" items="${currentSits}">
                    <tr class="align-middle">
                        <td>${dateFormatter.format(sit.startDate)}</td>
                        <td>${sit.firstVisit}</td>
                        <td>${dateFormatter.format(sit.endDate)}</td>
                        <td>${sit.finalVisit}</td>
                        <td>${sit.dailyVisits}</td>
                        <td>${sit.notes}</td>
                        <td>
                            <a href="/sits/${sit.id}/edit" class="btn btn-primary btn-sm">Edit Sit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/sits/all" class="btn btn-warning mt-2 mb-5">View All Sits</a>
    </c:if>
</div>
</body>
</html>