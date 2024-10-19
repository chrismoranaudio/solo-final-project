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
<title>Schedule a New Sit</title>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center my-5">
			<h1>Schedule a New Sit</h1>
			<div>
				<a href="/home" class="btn btn-secondary">Dashboard</a>
				<form action="/logout" method="POST" class="d-inline">
					<input type="submit" value="Log out" class="btn btn-danger ms-2"/>
				</form>
			</div>
		</div>
		<form:form action="/sits/new" method="POST" modelAttribute="newSit">
		    <!-- Client Field - Full Width -->
		    <div class="mb-3">
		        <c:choose>
		            <c:when test="${selectedClient != null}">
		                <div class="mb-3">
		                    <form:label path="client.id">Client:</form:label>
		                    <input type="hidden" name="client.id" value="${selectedClient.id}" />
		                    <p class="fs-4 ms-2">${selectedClient.firstName} ${selectedClient.lastName}</p>
		                </div>
		            </c:when>
		            <c:otherwise>
		                <div class="mb-3">
		                    <form:label path="client.id">Client:</form:label>
		                    <form:select path="client.id" class="form-select">
		                        <option value="" disabled>
		                            <c:choose>
		                                <c:when test="${selectedClient == null}">
		                                    Choose Client
		                                </c:when>
		                                <c:otherwise>
		                                    Select Client
		                                </c:otherwise>
		                            </c:choose>
		                        </option>
		                        <c:forEach var="client" items="${clients}">
		                            <option value="${client.id}" <c:if test="${selectedClient != null && selectedClient.id == client.id}">selected</c:if>>
		                                ${client.firstName} ${client.lastName}
		                            </option>
		                        </c:forEach>
		                    </form:select>
		                </div>
		            </c:otherwise>
		        </c:choose>
		    </div>
		
		    <!-- Start Date and First Visit side by side -->
		    <div class="row">
		        <div class="col-md-6 mb-3">
		            <form:label path="startDate">Start Date:</form:label>
		            <form:input path="startDate" type="date" class="form-control" />
		            <form:errors path="startDate" class="text-danger"/>
		        </div>
		        <div class="col-md-6 mb-3">
		            <form:label path="firstVisit">First Visit:</form:label>
		            <form:select path="firstVisit" class="form-select">
		                <option value="">Select First Visit</option>
		                <option value="Morning" ${newSit.firstVisit == 'Morning' ? 'selected' : ''}>Morning</option>
		                <option value="Midday" ${newSit.firstVisit == 'Midday' ? 'selected' : ''}>Mid-Day</option>
		                <option value="Dinner" ${newSit.firstVisit == 'Dinner' ? 'selected' : ''}>Dinner</option>
		                <option value="Bedtime" ${newSit.firstVisit == 'Bedtime' ? 'selected' : ''}>Bedtime</option>
		            </form:select>
		            <form:errors path="firstVisit" class="text-danger"/>
		        </div>
		    </div>
		
		    <!-- End Date and Final Visit side by side -->
		    <div class="row">
		        <div class="col-md-6 mb-3">
		            <form:label path="endDate">End Date:</form:label>
		            <form:input path="endDate" type="date" class="form-control" />
		            <form:errors path="endDate" class="text-danger"/>
		        </div>
		        <div class="col-md-6 mb-3">
		            <form:label path="finalVisit">Final Visit:</form:label>
		            <form:select path="finalVisit" class="form-select">
		                <option value="">Select Final Visit</option>
		                <option value="Morning" ${newSit.finalVisit == 'Morning' ? 'selected' : ''}>Morning</option>
		                <option value="Midday" ${newSit.finalVisit == 'Midday' ? 'selected' : ''}>Mid-Day</option>
		                <option value="Dinner" ${newSit.finalVisit == 'Dinner' ? 'selected' : ''}>Dinner</option>
		                <option value="Bedtime" ${newSit.finalVisit == 'Bedtime' ? 'selected' : ''}>Bedtime</option>
		            </form:select>
		            <form:errors path="finalVisit" class="text-danger"/>
		        </div>
		    </div>
		
		    <!-- Daily Visits and Additional Notes side by side -->
		    <div class="row">
		        <div class="col-md-6 mb-3">
		            <form:label path="dailyVisits">Daily Visits:</form:label>
		            <form:input path="dailyVisits" type="number" min="1" max="4" class="form-control" />
		            <form:errors path="dailyVisits" class="text-danger"/>
		        </div>
		        <div class="col-md-6 mb-3">
		            <form:label path="notes">Additional Notes:</form:label>
		            <form:textarea path="notes" class="form-control" />
		        </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Schedule Sit</button>
		</form:form>
	    <%-- <form:form action="/sits/new" method="POST" modelAttribute="newSit">
  	        <div class="mb-3">
				<c:choose>
				    <c:when test="${selectedClient != null}">
				        <div class="mb-3">
				            <form:label path="client.id">Client:</form:label>
				            <input type="hidden" name="client.id" value="${selectedClient.id}" />
				            <p class="fs-4 ms-2">${selectedClient.firstName} ${selectedClient.lastName}</p>
				        </div>
				    </c:when>
				
				    <c:otherwise>
				        <div class="mb-3">
				            <form:label path="client.id">Client:</form:label>
				            <form:select path="client.id" class="form-select">
				                <option value="" disabled>
				                    <c:choose>
				                        <c:when test="${selectedClient == null}">
				                            Choose Client
				                        </c:when>
				                        <c:otherwise>
				                            Select Client
				                        </c:otherwise>
				                    </c:choose>
				                </option>
				                <c:forEach var="client" items="${clients}">
				                    <option value="${client.id}" <c:if test="${selectedClient != null && selectedClient.id == client.id}">selected</c:if>>
				                        ${client.firstName} ${client.lastName}
				                    </option>
				                </c:forEach>
				            </form:select>
				        </div>
				    </c:otherwise>
				</c:choose>
	        </div>
	        <div class="mb-3">
	            <form:label path="startDate">Start Date:</form:label>
	            <form:input path="startDate" type="date" class="form-control" />
	            <form:errors path="startDate" class="text-danger"/>
	        </div>
     	    <div class="mb-3">
	            <form:label path="firstVisit">First Visit:</form:label>
			    <form:select path="firstVisit" class="form-select">
			        <option value="">Select First Visit</option> 
			        <option value="Morning" ${newSit.firstVisit == 'Morning' ? 'selected' : ''}>Morning</option>
			        <option value="Midday" ${newSit.firstVisit == 'Midday' ? 'selected' : ''}>Mid-Day</option>
			        <option value="Dinner" ${newSit.firstVisit == 'Dinner' ? 'selected' : ''}>Dinner</option>
			        <option value="Bedtime" ${newSit.firstVisit == 'Bedtime' ? 'selected' : ''}>Bedtime</option>
			    </form:select>
	            <form:errors path="firstVisit" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="endDate">End Date:</form:label>
	            <form:input path="endDate" type="date" class="form-control" />
	            <form:errors path="endDate" class="text-danger"/>
	        </div>
	        <div class="mb-3">
				<form:label path="finalVisit">Final Visit:</form:label>
			    <form:select path="finalVisit" class="form-select">
			        <option value="">Select Final Visit</option> 
			        <option value="Morning" ${newSit.finalVisit == 'Morning' ? 'selected' : ''}>Morning</option>
			        <option value="Midday" ${newSit.finalVisit == 'Midday' ? 'selected' : ''}>Mid-Day</option>
			        <option value="Dinner" ${newSit.finalVisit == 'Dinner' ? 'selected' : ''}>Dinner</option>
			        <option value="Bedtime" ${newSit.finalVisit == 'Bedtime' ? 'selected' : ''}>Bedtime</option>
			    </form:select>
	            <form:errors path="finalVisit" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="dailyVisits">Daily Visits:</form:label>
	            <form:input path="dailyVisits" type="number" min="1" max="4" class="form-control" />
	            <form:errors path="dailyVisits" class="text-danger"/>
	        </div>
	        <div class="mb-3">
	            <form:label path="notes">Additional Notes:</form:label>
	            <form:textarea path="notes" class="form-control" />
	        </div>
	        <button type="submit" class="btn btn-primary">Schedule Sit</button>
	    </form:form> --%>	
	</div>
</body>
</html>