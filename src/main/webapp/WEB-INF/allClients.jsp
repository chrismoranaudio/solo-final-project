<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Client List</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>My Clients</h1>
		<div>
			<a href="/home" class="btn btn-secondary">Dashboard</a>
			<form action="/logout" method="POST" class="d-inline">
				<input type="submit" value="Log out" class="btn btn-danger ms-2"/>
			</form>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-10">
		<table class="table table-striped table-bordered mb-5 text-center">
            <thead>
                <tr>
                    <th>Client Name</th>
                    <th>Pets</th>
                    <th>Daily Visits</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${clients}">
                    <tr class="align-middle">
                        <td>
                        	<a href="/clients/${client.id}/view">${client.firstName} ${client.lastName}</a>
                       	</td>
                        <td>
						    <c:if test="${client.numberOfDogs > 0}">
						        ${client.numberOfDogs} dog<c:if test="${client.numberOfDogs > 1}">s</c:if>
						    </c:if>
						    <c:if test="${client.numberOfDogs > 0 && client.numberOfCats > 0}">, </c:if>
						    <c:if test="${client.numberOfCats > 0}">
						        ${client.numberOfCats} cat<c:if test="${client.numberOfCats > 1}">s</c:if>
						    </c:if>
						    <c:if test="${client.numberOfDogs == 0 && client.numberOfCats == 0}">
						        No pets
						    </c:if>
						</td>
                        <td>${client.dailyVisits}</td>
                        <td>
                        	<a href="/sits/new?clientId=${client.id}" class="btn btn-primary">Schedule New Sit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
		<a href="/clients/new" class="btn btn-success btn-lg w-100">New Client</a>
		</div>
	</div>
</div>
</body>
</html>