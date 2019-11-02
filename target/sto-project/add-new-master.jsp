<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${text.getProperty("add_new_master.title")}</title>
    <meta content="text/html" charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/libs/bootstrap.css">
    <script src="/libs/jquery-3.3.1.min.js"></script>
    <script src="/libs/bootstrap.js"></script>
    <link href="/css/form.css" rel="stylesheet">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container content">
    <div class="col-md-6 col-md-offset-3">
        <div class="form-area">
            <form accept-charset="UTF-8" action="/settings/masters" method="post" role="form">
                <br style="clear:both">
                <h3 style="margin-bottom: 25px; text-align: center;">${text.getProperty("add_new_master.form_title")}</h3>
                <div class="form-group">
                    <input type="text" class="form-control" name="name"
                           placeholder="${text.getProperty("name")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="surname"
                           placeholder="${text.getProperty("surname")}" required>
                </div>
                <div class="form-group">
                    <input type="text" onfocus="this.type='date'" class="form-control" name="bornDate"
                           placeholder="${text.getProperty("born_date")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="address"
                           placeholder="${text.getProperty("address")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="passport"
                           placeholder="${text.getProperty("add_new_master.passport")}"
                           required>
                </div>
                <div class="form-group">
                    <input type="tel" class="form-control" name="mobile"
                           placeholder="${text.getProperty("phone")}"
                           required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="post"
                           placeholder="${text.getProperty("post")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="work-place"
                           placeholder="${text.getProperty("work_place")}" required>
                </div>

                <button type="submit" id="submit" class="btn btn-primary pull-right">
                    ${text.getProperty("add_new_master.add")}</button>
            </form>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
