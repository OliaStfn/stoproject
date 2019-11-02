<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${text.getProperty("services")}</title>
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
                <h3 class="panel-title">${text.getProperty("services")}</h3>
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
                    <th><input type="text" class="form-control"
                               placeholder="${text.getProperty("services.name")}" disabled></th>
                    <c:if test="${requestScope.containsKey('category')}">
                        <th><input type="text" class="form-control" value="${category}"
                                   placeholder="${text.getProperty("services.category")}"></th>
                    </c:if>
                    <c:if test="${!requestScope.containsKey('category')}">
                        <th><input type="text" class="form-control"
                                   placeholder="${text.getProperty("services.category")}" disabled></th>
                    </c:if>
                    <th><input type="text" class="form-control"
                               placeholder="${text.getProperty("services.price")}" disabled></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="service" items="${sessionScope.services}">
                    <tr>
                        <td>${service.id}</td>
                        <td>${service.name}</td>
                        <td>${service.category}</td>
                        <td>
                            <c:if test="${sessionScope.userType!='admin'}">
                                ${service.price}
                            </c:if>
                            <c:if test="${sessionScope.userType=='admin'}">
                                <form action="/services" method="post">
                                    <input type="hidden" style="display: none"
                                           value="${service.id}" name="id">
                                    <input type="number" name="price" value="${service.price}">
                                    <input class="navbar-right" type="submit"
                                           value="${text.getProperty("services.save")}">
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
</div>
<c:import url="footer.jsp"/>
</body>
</html>
