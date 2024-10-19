<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Select Number of Pets</title>
</head>
<body>
<div class="container">
	<h1 class="my-5">Please Select Number of Pets!</h1>
	<form action="/pets/numberSelection" method="POST">
	<input type="hidden" name="clientId" value="${clientId}" />
	    <div class="mb-3">
	        <label>They have 
		        <select name="numberOfDogs" class="form-select d-inline w-auto">
		            <c:forEach var="option" items="${dogOptions}">
		                <option value="${option}">${option}</option>
		            </c:forEach>
		        </select> dog(s)
	        </label>
	    </div>
	    <div class="mb-3">
	        <label>They have 
	        <select name="numberOfCats" class="form-select d-inline w-auto">
	            <c:forEach var="option" items="${catOptions}">
	                <option value="${option}">${option}</option>
	            </c:forEach>
	        </select> cat(s)
	        </label> 
	    </div>
	    <input type="submit" value="Next" class="btn btn-primary"/>
	</form>
</div>
</body>
</html>