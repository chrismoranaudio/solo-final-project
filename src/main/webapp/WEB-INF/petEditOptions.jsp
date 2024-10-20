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
<title>Pet Edit Options</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-5">
			<h1>Pet Edit Options</h1>
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
    <div class="mt-4">
        <a href="/clients/${clientId}/pets/edit-existing" class="btn btn-primary btn-lg mb-3">Edit or Delete Existing Pet</a>
    </div>
    <div>
        <a href="/clients/${clientId}/pets/add" class="btn btn-success btn-lg">Add a New Pet</a>
    </div>
</div>
</body>
</html>