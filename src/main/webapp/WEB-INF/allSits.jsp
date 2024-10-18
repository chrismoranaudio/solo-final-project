<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>View All Sits</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-5">
        <h1>All Sits</h1>
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
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Final Visit</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sit" items="${sits}">
                    <tr>
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
                        <td>${dateFormatter.format(sit.endDate)}</td>
                        <td>${sit.finalVisit}</td>
                        <td>
                            <a href="/sits/${sit.id}/edit">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/sits/new" class="btn btn-secondary btn-lg w-100">New Sit</a>
        </div>
    </div>
</div>
</body>
</html>