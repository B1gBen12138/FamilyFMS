<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>FamilyFMS-Login</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
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

    .span_error {

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
            <c:if test="${flag==2}">
                <script>
                    alert("用户名或密码错误，请重试！");
                </script>
            </c:if>
        </div>
        <form id="subform" action="${pageContext.request.contextPath}/login" method="post">
            <div class="div_input_account">
                <input id="name" class="input_account" type="text" name="name" placeholder="用户名" value="${name}"/>
            </div>
            <div class="div_empty">
            </div>

            <div class="div_input_pwd">
                <input id="password" class="input_pwd" type="password" name="pwd" placeholder="密码" value="${pwd}"/>
            </div>
            <div class="div_empty">
            </div>
            <div class="div_button_login">
                <input id="login" class="button_login" type="submit" value="登录"/>
            </div>
            <div class="div_button_login">
                <input id="register" class="button_login" type="button" value="注册" onclick="ajaxAccount()"/>
            </div>
        </form>
    </div>
</div>

</body>
<script>
    function ajaxAccount() {
        let name = $('#name');
        let password = $('#password');
        if (name !== '' && password !== '') {
            $.ajax(
                {
                    type: "post",
                    url: "check",
                    data: "name=" + name.val() + "&password=" + password.val(),
                    async: false,
                    success: function (msg) {
                        if (!msg) {
                            alert("该用户名已存在！");
                        }else{
                            register(name,  password);
                        }
                    },
                    dataType: "json"
                })
        }
    }

    function register(name, password) {
        $.ajax({
            type:"post",
            url:"register",
            data:"name=" + name.val() + "&password=" + password.val(),
            async: false,
            success: function (msg) {
                if (!msg){
                    alert("注册失败！请重试");
                } else{
                    alert("注册成功！");
                    sub();
                }
            },
            dataType: "json"
        })
    }
</script>
</html>
