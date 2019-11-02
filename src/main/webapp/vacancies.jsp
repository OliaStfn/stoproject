<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${text.getProperty("vacancies")}</title>
    <meta content="text/html" charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/libs/bootstrap.css">
    <script src="/libs/jquery-3.3.1.min.js"></script>
    <script src="/libs/bootstrap.js"></script>
</head>
<body>
<c:import url="/header.jsp"/>
<div class="col-xs-12 col-sm-12 content">
    <div class="row">
        <div class="col-xs-6 col-lg-6">
            <h1>${text.getProperty("vacancies.name1")}</h1>
            <div style="margin-top: 58px;">
                ${text.getProperty("vacancies.conditions1")}
            </div>
            <div>
                ${text.getProperty("vacancies.requirements1")}
            </div>
        </div>
        <div class="col-xs-6 col-lg-6">
            <h1>${text.getProperty("vacancies.name2")}</h1>
            <div>
                ${text.getProperty("vacancies.conditions2")}
            </div>
            <div>
                ${text.getProperty("vacancies.requirements2")}
            </div>
        </div>
    </div>
    <div align="center">
        <h3>${text.getProperty("vacancies.contacts")}</h3>
        <h4>+38 (066) 222 33 79</h4>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
