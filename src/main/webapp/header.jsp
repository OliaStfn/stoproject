<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/css/header.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css"
      rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css" rel="stylesheet">
<script src="/js/header.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>

<nav id="header" class="navbar navbar-fixed-top">
    <div id="header-container" class="container navbar-container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a id="brand" class="navbar-brand" href="">${text.getProperty("label")}</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/home">${text.getProperty("home")}</a></li>
                <c:if test="${!sessionScope.containsKey('user') || sessionScope.userType!='admin'}">
                    <li><a href="/services">${text.getProperty("services")}</a></li>
                </c:if>
                <li><a href="/vacancies">${text.getProperty("vacancies")}</a></li>
                <c:if test="${sessionScope.containsKey('user')}">
                    <c:if test="${sessionScope.userType=='customer'}">
                        <li><a href="/orders">${text.getProperty("orders")}</a></li>
                    </c:if>
                    <c:if test="${sessionScope.userType=='admin'}">
                        <li><a href="/orders">${text.getProperty("orders")}</a></li>
                        <li><a href="/services">${text.getProperty("header.edit_services")}</a></li>
                        <li><a href="/settings/customers">${text.getProperty("customers")}</a></li>
                        <li><a href="/settings/masters">${text.getProperty("masters")}</a></li>
                    </c:if>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="right" style="position: relative; left: 80px;">
                    <span onclick="window.location.href= window.location.href.split('?')[0]+'?language=en'"
                          class="flag-icon flag-icon-us"></span >
                    <span onclick="window.location.href= window.location.href.split('?')[0]+'?language=ua'"
                          class="flag-icon flag-icon-ua"></span>
                    <span onclick="window.location.href= window.location.href.split('?')[0]+'?language=ru'"
                          class="flag-icon flag-icon-ru"></span>
                </li><br>
                <c:if test="${sessionScope.containsKey('user')}">
                    <li class="right"><a
                            href="/authorization?type=logout">${text.getProperty("logout")}</a></li>
                </c:if>
                <c:if test="${!sessionScope.containsKey('user')}">
                    <li class="right"><a href="/authorization?type=login">${text.getProperty("login")}</a>
                    </li>
                    <li class="right"><a
                            href="/authorization?type=register">${text.getProperty("register")}</a></li>
                </c:if>
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav>
<!-- /.navbar -->