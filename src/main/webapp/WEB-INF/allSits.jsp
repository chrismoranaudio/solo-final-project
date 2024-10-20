<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>View All Sits</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-5">
		<h1>All Sits</h1>
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
    <div class="row justify-content-center">
        <div>
        <table class="table table-striped table-bordered mb-5 text-center">
            <thead>
                <tr class="align-middle">
                    <th>Client Name</th>
                    <th>Pets</th>
                    <th>Daily Visits</th>
                    <th>Start Date</th>
                    <th>First Visit</th>
                    <th>End Date</th>
                    <th>Final Visit</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sit" items="${sits}">
                    <tr class="align-middle">
                        <td>
                            <a href="/clients/${sit.client.id}/view">${sit.client.firstName} ${sit.client.lastName}</a>
                        </td>
                        <td>
                            <c:if test="${sit.client.numberOfDogs > 0}">
                                ${sit.client.numberOfDogs} dog<c:if test="${sit.client.numberOfDogs > 1}">s</c:if>
                            </c:if>
                            <c:if test="${sit.client.numberOfDogs > 0 && sit.client.numberOfCats > 0}">, </c:if>
                            <c:if test="${sit.client.numberOfCats > 0}">
                                ${sit.client.numberOfCats} cat<c:if test="${sit.client.numberOfCats > 1}">s</c:if>
                            </c:if>
                            <c:if test="${sit.client.numberOfDogs == 0 && sit.client.numberOfCats == 0}">
                                No pets
                            </c:if>
                        </td>
                        <td>${sit.dailyVisits}</td>
                        <td>${dateFormatter.format(sit.startDate)}</td>
                        <td>${sit.firstVisit}</td>
                        <td>${dateFormatter.format(sit.endDate)}</td>
                        <td>${sit.finalVisit}</td>
                        <td>
                            <a href="/sits/${sit.id}/edit" class="btn btn-primary">Edit Sit</a>  
                            <form action="/sits/${sit.id}/delete" method="POST" style="display: inline;">
				            	<input type="hidden" name="_method" value="delete"/>
				            	<button type="submit" class="btn btn-danger"
				            		onclick="return confirm('Are you sure you want to delete this sit?');">Delete Sit
				           		</button>
				            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/sits/new" class="btn btn-success btn-lg">New Sit</a>
        </div>
    </div>
</div>
</body>
</html>