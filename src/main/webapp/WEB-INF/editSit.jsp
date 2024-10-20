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
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>Edit Sit</title>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center my-5">
			<h1>Edit <c:out value="${editSit.client.firstName}"/>'s Sit</h1>
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
	            <form:label path="firstVisit">Final Visit:</form:label>
	            <form:select path="firstVisit" class="form-select">
	                <option value="Morning" <c:if test="${editSit.firstVisit == 'Morning'}">selected</c:if>>Morning</option>
	                <option value="Midday" <c:if test="${editSit.firstVisit == 'Midday'}">selected</c:if>>Mid-Day</option>
	                <option value="Dinner" <c:if test="${editSit.firstVisit == 'Dinner'}">selected</c:if>>Dinner</option>
	                <option value="Bedtime" <c:if test="${editSit.firstVisit == 'Bedtime'}">selected</c:if>>Bedtime</option>
	            </form:select>
	            <form:errors path="firstVisit" class="text-danger"/>
	        </div>	        
	        <div class="mb-3">
	            <form:label path="endDate">End Date:</form:label>
	            <form:input path="endDate" type="date" value="${editSit.endDate}" class="form-control" />
	            <form:errors path="endDate" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="finalVisit">Final Visit:</form:label>
	            <form:select path="finalVisit" class="form-select">
	                <option value="Morning" <c:if test="${editSit.finalVisit == 'Morning'}">selected</c:if>>Morning</option>
	                <option value="Midday" <c:if test="${editSit.finalVisit == 'Midday'}">selected</c:if>>Mid-Day</option>
	                <option value="Dinner" <c:if test="${editSit.finalVisit == 'Dinner'}">selected</c:if>>Dinner</option>
	                <option value="Bedtime" <c:if test="${editSit.finalVisit == 'Bedtime'}">selected</c:if>>Bedtime</option>
	            </form:select>
	            <form:errors path="finalVisit" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="dailyVisits">Daily Visits:</form:label>
	            <form:input path="dailyVisits" type="number" min="1" max="4" value="${editSit.dailyVisits}" class="form-control" />
	            <form:errors path="dailyVisits" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="notes">Additional Notes:</form:label>
	            <form:textarea path="notes" value="${editSit.notes}" class="form-control" />
	        </div>
	        <button type="submit" class="btn btn-primary">Update Sit</button>
	    </form:form>	
	    <form action="/sits/${editSit.id}/delete" method="POST" style="display: inline;">
        <input type="hidden" name="_method" value="delete"/>
           	<button type="submit" class="btn btn-danger mt-2"
           		onclick="return confirm('Are you sure you want to delete this sit?');">Delete Sit
          	</button>
         </form>
	</div>
</body>
</html>