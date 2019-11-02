<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${text.getProperty("customers")}</title>
    <meta content="text/html" charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/libs/bootstrap.css">
    <script src="/libs/jquery-3.3.1.min.js"></script>
    <script src="/libs/bootstrap.js"></script>
    <link href="/css/table.css" rel="stylesheet">
    <script src="/js/table.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container content">
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">${text.getProperty("customers")}</h3>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th style="width: 5%">#</th>
                    <th style="width: 8%">${text.getProperty("name")}</th>
                    <th style="width: 8%">${text.getProperty("surname")}</th>
                    <th>${text.getProperty("born_date")}</th>
                    <th>${text.getProperty("email")}</th>
                    <th style="width: 8%">${text.getProperty("phone")}</th>
                    <th>${text.getProperty("customers.register_date")}</th>
                    <th>${text.getProperty("orders.reception_point")}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="customer" items="${sessionScope.customers}">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.surname}</td>
                        <td>${customer.dateOfBirth}</td>
                        <td>${customer.email}</td>
                        <td>${customer.phoneNumber}</td>
                        <td>${customer.createdDate.dayOfMonth}.${customer.createdDate.monthValue}.${customer.createdDate.year}<br>
                                ${customer.createdDate.hour}:${customer.createdDate.minute}:${customer.createdDate.second}</td>
                        <td>${requestScope.reception_point}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
