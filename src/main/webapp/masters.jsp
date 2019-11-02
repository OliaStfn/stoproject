<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${text.getProperty("masters")}</title>
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
                <h3 class="panel-title">${text.getProperty("masters")}</h3>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                        ${text.getProperty("filter")}
                    </button>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr class="filters">
                    <th style="width: 50px"><input style="width: 4%" type="text" class="form-control" placeholder="#" disabled></th>
                    <th style="width: 80px"><input type="text" class="form-control"
                                                 placeholder="${text.getProperty("name")}" disabled></th>
                    <th style="width: 80px"><input type="text" class="form-control"
                                                  placeholder="${text.getProperty("surname")}" disabled></th>
                    <th><input type="text" onfocus="(this.type='date')" onblur="(this.type='text')"
                               class="form-control"
                               placeholder="${text.getProperty("born_date")}" disabled></th>
                    <th><input type="text" class="form-control"
                               placeholder="${text.getProperty("address")}" disabled></th>
                    <th><input type="text" class="form-control"
                               placeholder="${text.getProperty("masters.passport")}" disabled></th>
                    <th><input type="tel" class="form-control"
                               placeholder="${text.getProperty("phone")}" disabled></th>
                    <th style="width: 50px; height: auto;"><input type="text" class="form-control"
                               placeholder="${text.getProperty("post")}" disabled></th>
                    <th style="width: 50px; height: auto;"><input type="text" class="form-control"
                               placeholder="${text.getProperty("work_place")}" disabled></th>
                    <th style="width: 70px"><input type="text" class="form-control"
                                                 placeholder="${text.getProperty("masters.status")}" disabled></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="master" items="${sessionScope.masters}">
                    <tr>
                        <td>${master.id}</td>
                        <td>${master.name}</td>
                        <td>${master.surname}</td>
                        <td>${master.dateOfBirth}</td>
                        <td>${master.address}</td>
                        <td>${master.passport}</td>
                        <td>${master.phoneNumber}</td>
                        <td>${master.post}</td>
                        <td>${master.workPlace}</td>
                        <td>${master.status.toString().toLowerCase()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input id="add" class="btn col-md-2 pull-right" type="button"
                   value="${text.getProperty("masters.add_master")}"
                   onclick="location.href='/add-new-master.jsp'">
        </div>
    </div>
</div>
<hr>
<c:import url="footer.jsp"/>
</body>
</html>
