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
<title>Add New Pets</title>
</head>
<body>
<div class="container">
    <h1 class="my-5">Add New Pets</h1>
    <form action="/clients/${clientId}/pets/add" method="POST">
    <input type="hidden" name="clientIt" value="${clientId}" />
        <div class="mb-3">
            <label>I'm adding 
                <select name="numberOfDogs" class="form-select d-inline w-auto">
                    <c:forEach items="${petOptions}" var="option">
                        <option value="${option}">${option}</option>
                    </c:forEach>
                </select> dog(s)
            </label>
        </div>
        <div class="mb-3">
            <label>I'm adding 
                <select name="numberOfCats" class="form-select d-inline w-auto">
                    <c:forEach items="${petOptions}" var="option">
                        <option value="${option}">${option}</option>
                    </c:forEach>
                </select> cat(s)
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Next</button>
    </form>
</div>
</body>
</html>