<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${text.getProperty("home")}</title>
    <meta content="text/html" charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/libs/bootstrap.css">
    <script src="/libs/jquery-3.3.1.min.js"></script>
    <script src="/libs/bootstrap.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="col-xs-12 col-sm-12 content">
    <div class="jumbotron" style="padding-left: 15px;">
        <h1>${text.getProperty("home.jumbtron.slogan")}</h1>
        <p>${text.getProperty("home.jumbtron.description")}
        </p>
    </div>
    <div class="row">
        <c:forEach var="category" items="${sessionScope.categories}">
            <div class="col-xs-6 col-lg-4">
                <h2>${category}</h2>
                <p
                        ${text.getProperty("home.services")}
                <ul>
                    <c:forEach var="service" items="${sessionScope.services}">
                        <c:if test="${service.category == category}">
                            <li>${service.name} - ${service.price} ${text.getProperty("money")}.</li>
                        </c:if>
                    </c:forEach>
                </ul>
                </p>
                <p><a class="btn btn-default" href="/services?category=${category}"
                      role="button">${text.getProperty("home.more_information")} »</a></p>
            </div>
            <!--/.col-xs-6s.col-lg-4-->
        </c:forEach>
    </div><!--/row-->
</div>
<c:import url="footer.jsp"/>
</body>
</html>
