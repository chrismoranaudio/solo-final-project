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
		<form action="/logout" method="POST">
			<input type="submit" value="Log out" class="btn btn-secondary"/>
		</form>
	</div>
	<div class="row justify-content-center">
		<div class="col-10">
		<table class="table table-striped table-bordered mb-5">
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
                    <tr>
                        <td>${client.firstName} ${client.lastName}</td>
                        <td>
                            <c:set var="dogs" value="0" />
                            <c:set var="cats" value="0" />
                            <c:forEach var="pet" items="${client.pets}">
                                <c:choose>
                                    <c:when test="${pet.petType == 'DOG'}">
                                        <c:set var="dogs" value="${dogs + 1}" />
                                    </c:when>
                                    <c:when test="${pet.petType == 'CAT'}">
                                        <c:set var="cats" value="${cats + 1}" />
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${dogs > 0}">
                                ${dogs} dog<c:if test="${dogs > 1}">s</c:if>
                            </c:if>
                            <c:if test="${dogs > 0 && cats > 0}">, </c:if>
                            <c:if test="${cats > 0}">
                                ${cats} cat<c:if test="${cats > 1}">s</c:if>
                            </c:if>
                        </td>
                        <td>${client.dailyVisits}</td>
                        <td>
                        	<a href="/clients/${client.id}/edit">Edit Client</a> | 
                        	<a href="/clients/${client.id}/pets/edit">Edit Pets</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
		<a href="/clients/new" class="btn btn-secondary btn-lg w-100">New Client</a>
		</div>
	</div>
</div>
</body>
</html>