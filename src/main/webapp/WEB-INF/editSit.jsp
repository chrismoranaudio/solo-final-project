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
<title>Edit Sit</title>
</head>
<body>
	<div class="container">
		<h1 class="my-5">Edit Sit</h1>
	    <form:form action="/sits/${editSit.id}/update" method="POST" modelAttribute="editSit">
	    <input type="hidden" name="_method" value="put"/>
  	        <div class="mb-3">
	            <form:label path="client.id">Client:</form:label>
	            <form:select path="client.id" class="form-select">
	                <c:forEach var="client" items="${clients}">
	                    <option value="${client.id}" 
	                    	<c:if test="${client.id == editSit.client.id}">selected</c:if>>
	                    		${client.firstName} ${client.lastName}
                    	</option>
	                </c:forEach>
	            </form:select>
	        </div>
	        <div class="mb-3">
	            <form:label path="startDate">Start Date:</form:label>
	            <form:input path="startDate" type="date" value="${editSit.startDate}" class="form-control" />
	            <form:errors path="startDate" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="endDate">End Date:</form:label>
	            <form:input path="endDate" type="date" value="${editSit.endDate}" class="form-control" />
	            <form:errors path="endDate" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="dailyVisits">Daily Visits:</form:label>
	            <form:input path="dailyVisits" type="number" min="1" max="4" value="${editSit.dailyVisits}" class="form-control" />
	            <form:errors path="dailyVisits" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="finalVisit">Final Visit:</form:label>
	            <form:select path="finalVisit" class="form-select">
	                <option value="MORNING" <c:if test="${editSit.finalVisit == 'MORNING'}">selected</c:if>>Morning</option>
	                <option value="MID_DAY" <c:if test="${editSit.finalVisit == 'MID_DAY'}">selected</c:if>>Mid-Day</option>
	                <option value="DINNER" <c:if test="${editSit.finalVisit == 'DINNER'}">selected</c:if>>Dinner</option>
	                <option value="BEDTIME" <c:if test="${editSit.finalVisit == 'BEDTIME'}">selected</c:if>>Bedtime</option>
	            </form:select>
	            <form:errors path="finalVisit" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="notes">Additional Notes:</form:label>
	            <form:textarea path="notes" value="${editSit.notes}" class="form-control" />
	        </div>
	        <button type="submit" class="btn btn-primary">Update Sit</button>
	    </form:form>	
	</div>
</body>
</html>