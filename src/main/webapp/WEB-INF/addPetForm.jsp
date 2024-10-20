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
<title>Add New Pets</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center my-5">
		<h1>Add New Pets</h1>
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
    <form action="/clients/${clientId}/pets/add" method="POST">
    <input type="hidden" name="clientId" value="${clientId}" />
        <div class="mb-3">
            <label>I'm adding 
                <select name="numberOfDogs" class="form-select d-inline w-auto">
                    <c:forEach var="option" items="${petOptions}">
                        <option value="${option}">${option}</option>
                    </c:forEach>
                </select> dog(s)
            </label>
        </div>
        <div class="mb-3">
            <label>I'm adding 
                <select name="numberOfCats" class="form-select d-inline w-auto">
                    <c:forEach var="option" items="${petOptions}">
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