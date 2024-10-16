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
	<h1>Please Select Number of Pets!</h1>
	<form action="/pets/numberSelection" method="POST">
	<input type="hidden" name="clientId" value="${clientId}" />
	    <div>
	        <label>I have </label>
	        <select name="numberOfDogs">
	            <c:forEach var="option" items="${dogOptions}">
	                <option value="${option}">${option}</option>
	            </c:forEach>
	        </select>
	        <label> dog(s).</label>
	    </div>
	    <div>
	        <label>I have </label>
	        <select name="numberOfCats">
	            <c:forEach var="option" items="${catOptions}">
	                <option value="${option}">${option}</option>
	            </c:forEach>
	        </select>
	        <label> cat(s).</label>
	    </div>
	    <input type="submit" value="Next" class="btn btn-primary"/>
	</form>
</div>
</body>
</html>