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
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 14px; color: black; text-align: left;">Number of Done Tasks</h5>
                            <p class="card-text"  style="font-size: 30px; color: #28a745; text-align: center;">${doneCount}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mt-3">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 14px; color: black; text-align: left;">Total Number of Tasks</h5>
                            <p class="card-text" style="font-size: 30px; color: #28a745; text-align: center;">${totalCount}</p>
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
    </div> <!-- Pie Chart Section -->
    <br>  <br>  <br>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <h5 class="text-center">Task Progress Overview</h5><br>
            <canvas id="taskPieChart"></canvas>
        </div>
    </div>
    <br><br>
    <!-- Back to Task List Button -->
    <div class="text-center mt-4">
        <a href="/viewToDoList" class="btn btn-primary">Back to Task List</a>
    </div>
</div>

<script>
    // Data for the Pie Chart
    var pieData = {
        labels: ['ToDo', 'Doing', 'Done'],
        datasets: [{
            data: [${todoCount}, ${doingCount}, ${doneCount}],
            backgroundColor: ['#dcdada', '#ffca28', '#28a745'],
            hoverBackgroundColor: ['#c7c5c5', '#9B9B19FF', '#006400FF']
        }]
    };

    // Config and Render the Pie Chart
    var ctx = document.getElementById('taskPieChart').getContext('2d');
    new Chart(ctx, {
        type: 'pie',
        data: pieData,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    enabled: true
                }
            }
        }
    });
</script>
</body>
</html>
