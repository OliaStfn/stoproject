<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${text.getProperty("authorization")}</title>
    <meta content="text/html" charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/libs/bootstrap.css">
    <script src="/libs/jquery-3.3.1.min.js"></script>
    <script src="/libs/bootstrap.js"></script>
    <link href="/css/authorization.css" rel="stylesheet">
    <script src="/js/authorization.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div id="fullscreen_bg" class="fullscreen_bg"></div>
<div id="regContainer" class="container content">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="authorization?type=login"
                                    <c:if test="${!paramValues.containsKey('type') || param.type == 'login'}">
                                        <c:set var="login" value="display: block;"/>
                                        <c:set var="register" value="display: none;"/>
                                        <c:set var="forgot" value="display: none;"/>
                                        class="active"
                                    </c:if> id="login-form-link">
                                ${text.getProperty("login")}</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="authorization?type=register"
                                    <c:if test="${param.type == 'register'}">
                                        <c:set var="login" value="display: none;"/>
                                        <c:set var="register" value="display: block;"/>
                                        <c:set var="forgot" value="display: none;"/>
                                        class="active"
                                    </c:if>
                               id="register-form-link">
                                ${text.getProperty("register")}</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form accept-charset="UTF-8" id="login-form" action="/login" method="post" role="form"
                                  style="${login}">
                                <div class="form-group">
                                    <label for="username-login">${text.getProperty("username")}</label>
                                    <input type="text" name="username" id="username-login" tabindex="1"
                                           class="form-control username"
                                           placeholder="${text.getProperty("username")}" value="">
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="password-login">${text.getProperty("password")}</label>
                                    <input type="password" name="password" id="password-login" tabindex="2"
                                           class="form-control password"
                                           placeholder="${text.getProperty("password")}">
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group col-xs-6">
                                    <a href="" id="forgot-form-link">
                                        ${text.getProperty("login.forgot_password")}</a>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login"
                                                   value="${text.getProperty("login.submit")}">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form accept-charset="UTF-8" id="register-form" action="/register" method="post" role="form"
                                  style="${register}">
                                <div class="form-group">
                                    <span class="text-center" style="color: red">
                                        ${requestScope.message}</span><br>
                                    <label for="name">${text.getProperty("name")}</label>
                                    <input type="text" name="name" id="name" tabindex="1"
                                           class="form-control name"
                                           placeholder="${text.getProperty("name")}" value="" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="surname">${text.getProperty("surname")}</label>
                                    <input type="text" name="surname" id="surname" tabindex="2"
                                           class="form-control surname"
                                           placeholder="${text.getProperty("surname")}" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="birthDay">${text.getProperty("born_date")}</label>
                                    <input type="text" onfocus="this.type='date'"
                                           name="birthDay" id="birthDay" tabindex="2"
                                           class="form-control birthday"
                                           placeholder="${text.getProperty("born_date")}" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="username">${text.getProperty("username")}</label>
                                    <input type="text" name="username" id="username" tabindex="3"
                                           class="form-control username"
                                           placeholder="${text.getProperty("username")}" value="" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="password">${text.getProperty("password")}</label>
                                    <input type="password" name="password" id="password" tabindex="4"
                                           class="form-control password"
                                           placeholder="${text.getProperty("password")}" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="email">${text.getProperty("email")}</label>
                                    <input type="email" name="email" id="email"
                                           tabindex="5" class="form-control email"
                                           placeholder="${text.getProperty("email")}" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <label for="phone">${text.getProperty("phone")}</label>
                                    <input type="text" name="phone" id="phone"
                                           tabindex="6" class="form-control phone"
                                           placeholder="+38(___)-___-__-__" required>
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="7" class="form-control btn btn-register"
                                                   value="${text.getProperty("register.submit")}">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form accept-charset="UTF-8" id="forgot-form" action="/authorization" method="post"
                                  role="form"
                                  style="display: none">
                                <div class="form-group">
                                    <label for="email-forgot">${text.getProperty("email")}</label>
                                    <input type="email" name="email" id="email-forgot" tabindex="1"
                                           class="form-control email"
                                           placeholder="${text.getProperty("email")}" value="">
                                    <img src="img/ok.png" class="status ok">
                                    <img src="img/error.png" class="status error">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="forgot-submit" id="forgot-submit" tabindex="2"
                                                   class="form-control btn btn-login"
                                                   value="${text.getProperty("forgot.submit")}">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
