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
<title>Pet Edit Options</title>
</head>
<body>
<div class="container">
    <h1 class="my-5">Pet Edit Options</h1>
    <div class="mt-4">
        <a href="/clients/${clientId}/pets/edit-existing" class="btn btn-primary btn-lg mb-3">Edit or Delete Existing Pet</a>
    </div>
    <div>
        <a href="/clients/${clientId}/pets/add" class="btn btn-success btn-lg">Add a New Pet</a>
    </div>
</div>
</body>
</html>