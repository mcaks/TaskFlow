<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<head>
    <meta charset="ISO-8859-1">
    <title>View ToDo Item List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

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

        h1 {
            text-align: center;
            color: #333333;
            font-weight: bold;
        }

        .form-label {
            color: #555555;
        }

        .form-control {
            background-color: #ffffff;
            border: 2px solid #d2d1d1;
            color: #2a2a2a;
        }

        .btn {
            border: none;
            border-radius: 30px;
            transition: 0.3s;
            padding: 0.5rem 1.5rem;
        }

        .btncomplete {
            background: #28a745;
        }

        .btnedit {
            background: rgba(243, 243, 8, 0.94);
        }

        .btndelete {
            background: #dc3545;
        }

        .btncomplete:hover {
            background: darkgreen;
        }

        .btnedit:hover {
            background: rgb(155, 155, 25);
        }

        .btndelete:hover {
            background: darkred;
        }

        .status {
            padding: 0.4rem 1rem;
            border-radius: 20px;
            color: #000000;
            display: inline-block;
            text-align: center;
            width: 100px;
        }

        .status-todo {
            background-color: #dcdada;
        }

        .status-doing {
            background-color: #ffca28;
        }

        .status-done {
            background-color: #28a745;
        }
    </style>

</head>

<body>

<div class="container">
    <h1 class="p-3">ToDo Board</h1>

    <!-- Filter by Status Form -->
    <form method="get" action="viewToDoList" class="form-inline mb-3">
        <label for="status" class="mr-2">Filter by Status:</label>
        <select class="form-control mr-2" name="status" id="status">
            <option value="All" ${selectedStatus == 'All' ? 'selected' : ''}>All</option>
            <option value="Todo" ${selectedStatus == 'Todo' ? 'selected' : ''}>Todo</option>
            <option value="Doing" ${selectedStatus == 'Doing' ? 'selected' : ''}>Doing</option>
            <option value="Done" ${selectedStatus == 'Done' ? 'selected' : ''}>Done</option>
        </select>
        <button type="submit" class="btn btn-primary">Filter</button>
    </form>

    <form:form>
        <table class="table table-bordered text-center">
            <tr>
                <th class="text-center">Task Name</th>
                <th class="text-center">Date</th>
                <th class="text-center">Time</th> <!-- Add Time Column Header -->
                <th class="text-center">Status</th>
                <th class="text-center">Mark Complete</th>
                <th class="text-center">Edit</th>
                <th class="text-center">Delete</th>
            </tr>
            <c:forEach var="todo" items="${list}">
                <tr>
                    <td class="text-center">${todo.title}</td>
                    <td class="text-center">
                        <fmt:formatDate value="${todo.date}" pattern="dd.MM.yyyy" />
                    </td>
                    <td class="text-center">${todo.time}</td> <!-- Display Time -->

                    <td class="text-center">
                        <span class="status
                            <c:choose>
                                <c:when test="${todo.status == 'ToDo'}">status-todo</c:when>
                                <c:when test="${todo.status == 'Doing'}">status-doing</c:when>
                                <c:when test="${todo.status == 'Done'}">status-done</c:when>
                            </c:choose>">
                                ${todo.status}
                        </span>
                    </td>

                    <td class="text-center">
                        <button type="button" class="btn btncomplete">
                            <a href="/updateToDoStatus/${todo.id}" style="color: black;">&#x2714;</a>
                        </button>
                    </td>
                    <td class="text-center">
                        <button type="button" class="btn btnedit">
                            <a href="/editToDoItem/${todo.id}" style="color: black;">E</a>
                        </button>
                    </td>
                    <td class="text-center">
                        <button type="button" class="btn btndelete">
                            <a href="/deleteToDoItem/${todo.id}" style="color: black;">X</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form:form>

    <button type="button" class="btn btn-primary btn-block">
        <a href="/addToDoItem" class="text-white">Add</a>
    </button>

</div>

<script th:inline="javascript">
    window.onload = function() {
        var msg = "${message}";

        if (msg == "Save Success") {
            toastr.success("Item added successfully!!");
        } else if (msg == "Delete Success") {
            toastr.success("Item deleted successfully!!");
        } else if (msg == "Delete Failure") {
            toastr.error("Some error occurred, couldn't delete item");
        } else if (msg == "Edit Success") {
            toastr.success("Item updated successfully!!");
        }

        toastr.options = {
            "closeButton": true,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "timeOut": "5000"
        }
    }
</script>

</body>

</html>
