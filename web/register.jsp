<%--
  Created by IntelliJ IDEA.
  User: xielian
  Date: 2020/12/28
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path=request.getContextPath();%>
<html>

<head>
    <title>Register</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    body {
        background-color: gainsboro;
        font-size: 16px;
    }

    .div_top_1 {
        height: 140px;
        width: 100%;
    }

    .div_top_2 {
        height: 50px;
        width: 100%;
    }

    .main {
        width: 417.683px;
        height: 440px;
        background-color: #FFFFFF;
        margin: 0 auto;

    }

    .login {

        width: 360px;
        height: 360px;
        background-color: #FFFFFF;
        margin: 0 auto
    }

    .div_login_head {
        height: 36px;
        background-color: #FFFFFF;
        margin: 0 auto;
        line-height: 36px;
        text-align: center;
        color: #666;
        border-bottom: 3px solid #21b351;
        font-size: 18px;
        line-height: 24px;
        margin-bottom: -1px;
        font-family: "PingFang SC", "Microsoft yahei", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    }

    .div_input_account {
        width: 360px;
        height: 40px;
        border-radius: 10px;
    }

    .div_input_pwd {
        width: 360px;
        height: 24px;
        border-radius: 10px
    }

    .input_account, .input_pwd {
        width: 360px;
        height: 40px;
        border: none;
        border-bottom: #ddd 1px solid;
        border-radius: 0;
        outline: 0;
        font: inherit;
        font-size: .875rem;
    }

    .div_button_login {
        width: 360px;
        height: 40px;
        margin-top: 36px;
        text-align: center;

    }

    .button_login {
        width: 180px;
        height: 40px;
        background: #1fa54a;
        font-size: 16px;
        cursor: pointer;
        color: white;
        border: none;
        border-radius: 2px;
        outline: 0;

    }

    .div_empty {
        width: 360px;
        height: 24px;

    }
    .div_error {
        width: 360px;
        height: 24px;
        text-align: center;
    }
    .span_error{

        color: #e35b5a;
        font-size: 13px;
    }
</style>
<body>

<!--使用模态框的方式模拟一个登陆框-->
<div class="modal show" id="loginModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close">×</button>
                <h1 class="text-center text-primary">登录</h1>
            </div>
            <div class="modal-body">
                <form class="form col-md-12 center-block" id="loginForm" action="main/successLogin.do" method="post">
                    <div class="form-group-lg"  id="accountDiv">
                        <label class="sr-only" for="inputAccount">账号</label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
                            <input class="form-control" id="inputAccount" name="accountNo" type="text" placeholder="账号" required autofocus>
                        </div>
                        <div class="hidden text-center" id="accountMsg"><span class="glyphicon glyphicon-exclamation-sign"></span>用户名不存在</div>
                    </div>
                    <br>
                    <div class="form-group-lg" id="pwdDiv">
                        <label class="sr-only" for="inputPassword">密码</label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                            <input class="form-control" id="inputPassword" name="pwd" type="password" placeholder="密码" required>
                            <div class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></div>
                        </div>
                        <div class="hidden text-center" id="pwdMsg"><span class="glyphicon glyphicon-exclamation-sign"></span>用户名密码错误</div>
                    </div>
                    <div class="checkbox">
                        <label> <input type="checkbox" value="remember-me">记住我</label>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-default btn-lg col-md-6" id="btn_register" type="submit">注册</button>
                        <button class="btn btn-primary btn-lg col-md-6" id="btn_login" type="button" >登录</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

</body>
</html>
