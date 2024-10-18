<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1 class="my-5">Schedule a New Sit</h1>
	    <form:form action="/sits/new" method="POST" modelAttribute="newSit">
  	        <div class="mb-3">
	            <form:label path="client.id">Client:</form:label>
	            <form:select path="client.id" class="form-select">
	                <c:forEach var="client" items="${clients}">
	                    <option value="${client.id}">${client.firstName} ${client.lastName}</option>
	                </c:forEach>
	            </form:select>
	        </div>
	        <div class="mb-3">
	            <form:label path="startDate">Start Date:</form:label>
	            <form:input path="startDate" type="date" class="form-control" />
	        </div>
	        <div class="mb-3">
	            <form:label path="endDate">End Date:</form:label>
	            <form:input path="endDate" type="date" class="form-control" />
	        </div>
	        <div class="mb-3">
	            <form:label path="dailyVisits">Daily Visits:</form:label>
	            <form:input path="dailyVisits" type="number" min="1" max="4" class="form-control" />
	        </div>
	        <div class="mb-3">
	            <form:label path="finalVisit">Final Visit:</form:label>
	            <form:select path="finalVisit" class="form-select">
	                <option value="MORNING">Morning</option>
	                <option value="MID_DAY">Mid-Day</option>
	                <option value="DINNER">Dinner</option>
	                <option value="BEDTIME">Bedtime</option>
	            </form:select>
	        </div>
	        <div class="mb-3">
	            <form:label path="notes">Additional Notes:</form:label>
	            <form:textarea path="notes" class="form-control" />
	        </div>
	        <button type="submit" class="btn btn-primary">Schedule Sit</button>
	    </form:form>	
	</div>
</body>
</html>