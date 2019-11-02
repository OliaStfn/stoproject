<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${text.getProperty("orders")}</title>
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
                <h3 class="panel-title">${text.getProperty("orders")}</h3>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                        ${text.getProperty("filter")}
                    </button>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr class="filters">
                    <th style="width: 5%"><input type="text" class="form-control" placeholder="#" disabled></th>
                    <th style="width: 9%"><input type="text" class="form-control"
                               placeholder="${text.getProperty("car_brand")}" disabled></th>
                    <th style="width: 9%"><input type="text" class="form-control"
                               placeholder="${text.getProperty("car_model")}" disabled></th>
                    <th style="width: 11%"><input type="text" class="form-control"
                               placeholder="${text.getProperty("license_plate")}" disabled></th>
                    <th style="width: 9%"><input type="text" class="form-control"
                               placeholder="${text.getProperty("orders.services")}" disabled></th>
                    <th style="width:8%;"><input type="text" class="form-control"
                               placeholder="${text.getProperty("orders.price")}" disabled></th>
                    <th style="width: 15%"><input type="text" class="form-control"
                               placeholder="${text.getProperty("orders.reception_point")}" disabled></th>
                    <th style="width: 10%"><input type="text" class="form-control"
                               placeholder="${text.getProperty("orders.master")}" disabled></th>
                    <th><input type="text" onfocus="(this.type='date')" onblur="(this.type='text')"
                                           class="form-control"
                                           placeholder="${text.getProperty("orders.created_date")}" disabled></th>
                    <th style="width: 7%">${text.getProperty("orders.action")}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${sessionScope.orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.carBrand}</td>
                        <td>${order.carModel}</td>
                        <td>${order.licensePlate}</td>
                        <td>
                            <c:forEach var="service" items="${order.services}">
                                ${service.name}<br>
                            </c:forEach>
                        </td>
                        <td>${order.allPrice}</td>
                        <td><a href="/orders?reception_point=${order.receptionPoint}">${order.receptionPoint}</a></td>
                        <td>${order.masterId}</td>
                        <td>${order.orderDate}</td>
                        <td>
                            <c:if test="${sessionScope.userType == 'customer'}">
                                <form action="/orders/delete" method="post">
                                    <input type="hidden" style="display: none" name="id" value="${order.id}">
                                    <input class="btn btn-danger" type="submit" value="${text.getProperty("orders.delete")}">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input id="add" class="btn col-md-2 pull-right" type="button"
                   value="${text.getProperty("add_order")}"
                   onclick="location.href='/add-new-order.jsp'">
        </div>
    </div>
    <c:import url="footer.jsp"/>
</body>
</html>
