<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Reports</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<style>
    body {
        background-color: #ffffff;
        color: #333333;
        font-family: 'Roboto', sans-serif;
    }

    .container {
        background: #f9f9f9;
        border-radius: 16px;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
        padding: 2rem;
        margin-top: 50px;
    }
</style>
<div class="container mt-5">
    <div class="row">
        <!-- Polja s statistikami -->
        <div class="container mt-5">
            <h1 class="text-center" style="color: black; font-weight: bold;">Reports</h1><br><br>


            <!-- Statistics Boxes -->
            <div class="row text-center mb-4">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 14px; color: black; text-align: left;">Number of ToDo Tasks</h5>
                            <p class="card-text"  style="font-size: 30px; color: #28a745; text-align: center;">${todoCount}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 14px; color: black; text-align: left;">Number of Doing Tasks</h5>
                            <p class="card-text"  style="font-size: 30px; color: #28a745; text-align: center;">${doingCount}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mt-3">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 14px; color: black; text-align: left;">Average Time on Doing Tasks</h5>
                            <p class="card-text"  style="font-size: 30px; color: #28a745; text-align: center;">${averageDoingTime} mins</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mt-3">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 14px; color: black; text-align: left;">Percentage of Done Tasks</h5>
                            <p class="card-text"  style="font-size: 28px; color: #28a745; text-align: center;">${completionPercentage} %</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>