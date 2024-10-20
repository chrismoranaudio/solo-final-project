<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>Dashboard</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-5">
        <h1>Welcome, <c:out value="${loggedInUser.firstName}"/>!</h1>
        <form action="/logout" method="POST" class="d-inline">
    		<button type="submit" class="btn btn-danger ml-2">
        		<i class="fas fa-sign-out-alt"></i> Log Out
    		</button>
		</form>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="card shadow-sm mb-4">
                <div class="card-body text-center">
                    <h5 class="card-title">Client List</h5>
                    <p class="card-text">View and manage all your clients.</p>
                    <a href="/clients/all" class="btn btn-primary">
                        <i class="fas fa-users"></i> Go to Clients
                    </a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card shadow-sm mb-4">
                <div class="card-body text-center">
                    <h5 class="card-title">New Client</h5>
                    <p class="card-text">Add a new client to your list.</p>
                    <a href="/clients/new" class="btn btn-success">
                        <i class="fas fa-user-plus"></i> Add Client
                    </a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card shadow-sm mb-4">
                <div class="card-body text-center">
                    <h5 class="card-title">Booked Sits</h5>
                    <p class="card-text">View and manage all booked sits.</p>
                    <a href="/sits/all" class="btn btn-primary">
                        <i class="fas fa-calendar-alt"></i> Go to Sits
                    </a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card shadow-sm mb-4">
                <div class="card-body text-center">
                    <h5 class="card-title">New Sit</h5>
                    <p class="card-text">Schedule a new sit.</p>
                    <a href="/sits/new" class="btn btn-success">
                        <i class="fas fa-plus-circle"></i> Schedule Sit
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>