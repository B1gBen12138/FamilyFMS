<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>用户管理</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.i18n.min.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>

<body leftmargin="0" topmargin="0"
      onLoad="MM_preloadImages('../images/login_10.gif','../images/login_12.gif','../images/login_09.gif','../images/login_11.gif')">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="32" align="left" valign="top" class="text006">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr valign="top">
                    <td>【 用户管理 】</td>
                    <td align="right">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="215" valign="top">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr align="left" class="bg03">
                    <td width="100%" height="29">
                        <span class="text001">&nbsp;&nbsp;新增用户信息</span></td>
                </tr>
            </table>
            <form id="subform" action="${pageContext.request.contextPath}/account/add/addAccount" method="post">
                <table width="98%" border="0" cellpadding="0" cellspacing="0" class="text008">
                    <tr align="center">
                        <td width="12%" height="35px" align="right">登录名</td>
                        <td width="12%" align="left" style="width: 20%">
                            <label for="login">
                                <input id="login" name="login" type="text" class="inp001" value=""
                                        onblur="check(name)" >
                            </label>
                        </td>
<%--                        <td id="loginInfo" align="left" style="display: block">--%>
<%--                            登录名已被使用，请重新输入--%>
<%--                        </td>--%>
                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">用户名</td>
                        <td width="88%" align="left">
                            <label for="name">
                                <input id="name" name="name" type="text" class="inp001" value=""
                                        onblur="check(name)">
                            </label>
                        </td>

                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">密码</td>
                        <td width="88%" align="left">
                            <label for="password">
                                <input id="password" name="password" type="password" class="inp001" value=""
                                       onblur="check(password)">
                            </label>
                        </td>

                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">管理员权限</td>
                        <td width="88%" align="left">
                            <label for="yes"><input id="yes" type="radio" name="isAdmin" value="1" align="left" style="margin-left: 20px">是</label>

                            <label for="no"><input id="no" type="radio" name="isAdmin" value="0" checked="checked" align="left" style="margin-left: 50px">否</label>
                        </td>
                    </tr>
                </table>
            </form>
            <table width="98%" border="0" cellpadding="0" cellspacing="0" class="bor001">
                <tr>
                    <td width="12%" height="40" align="center">
                        <a id="tolist" href="#" target="mainframe"
                           onMouseOver="MM_swapImage('Image1','','../images/index_12_1.gif',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image1','','../images/index_12_2.gif',1)"
                           onMouseUp="MM_swapImage('Image1','','../images/index_12_1.gif',1)">
                            <img src="../images/index_12_0.gif" name="Image1" width="75" height="24" border="0"
                                 id="Image1" onclick="doSub()">
                        </a>
                    </td>
                    <td width="12%" height="40" align="center">
                        <a href="${pageContext.request.contextPath}/account/list" target="mainframe"
                           onMouseOver="MM_swapImage('Image2','','../images/login_09.gif',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image2','','../images/login_11.gif',1)"
                           onMouseUp="MM_swapImage('Image2','','../images/login_09.gif',1)">
                            <img src="../images/login_07.gif" name="Image2" width="75" height="24" border="0"
                                 id="Image2">
                        </a>
                    </td>
                    <td width="76%" align="right">&nbsp;</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script>

    function doSub() {
        var loginName = document.getElementById("login");
        var name = document.getElementById("name");
        var password = document.getElementById("password");
        var isAdmin = $('input:radio[name="isAdmin"]:checked');
        console.log(parseInt(isAdmin.val()));

        $.ajax({
            type: "post",
            url: "add/addAccount",
            data: "loginName=" + $('#login').val() + "&name=" + $('#name').val()
                + "&password=" + $('#password').val() + "&isAdmin=" + parseInt(isAdmin.val()),
            async: false,
            success: function (msg) {
                if (!msg) {
                    alert("添加失败");
                }else{

                    alert("添加成功");
                    goto("${pageContext.request.contextPath}/account/list");
                }
            },
            dataType: "json"
        })

    }

    function check(id) {
        var param = document.getElementById(id);
        if (param.value.length > 255){

        }
        if (id === "login"){
            $.ajax({
                type:"post",
                url:"check",
                data: "loginName=" + $('#login').val(),
                async: false,
                success: function (msg) {
                    if (!msg){
                        alert("登录名已被使用，请重新输入");
                        // document.getElementById("loginInfo").style.display("block");
                        document.getElementById("login").value = "";
                        document.getElementById("login").focus();

                    }
                },
                dataType: "json"
            })
        }
    }
</script>
</body>
</html>
