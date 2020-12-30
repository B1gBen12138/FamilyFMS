<%--
  Created by IntelliJ IDEA.
  User: xielian
  Date: 2020/12/26
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <title>Title</title>
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
<div class="div_top_1">


</div>
<div class="main">
    <div class="login">
        <div class="div_top_2">

        </div>
        <div class="div_login_head">
            密码登录
        </div>

        <div class="div_empty">
        </div>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="div_input_account">
                <input class="input_account" type="text" name="name" placeholder="用户名" value="${name}"/>
            </div>
            <div class="div_empty">
            </div>

            <div class="div_input_pwd">
                <input class="input_pwd" type="password" name="pwd" placeholder="密码" value="${pwd}"/>
            </div>
            <div class="div_empty">
            </div>
            <div class="div_error">
                <span class="span_error"> ${errorMsg }</span>
            </div>
            <div class="div_button_login">
                <input class="button_login" type="submit" value="登录"/>
            </div>
        </form>
    </div>


</div>

</div>
</body>
</html>
