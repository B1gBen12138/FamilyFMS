<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>证券管理</title>
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
                    <td>【 证券管理 】</td>
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
                        <span class="text001">&nbsp;&nbsp;新增证券信息</span></td>
                </tr>
            </table>
            <form id="subform" action="${pageContext.request.contextPath}/bondList/add" method="POST" name="subform">
                <table width="98%" border="0" cellpadding="0" cellspacing="0" class="text008">
                    <tr align="center">
                        <td height="35" align="right">用户</td>
                        <td width="88%" align="left">
                            <input id="user" name="user" type="text" class="inp001"
                                   value="${account.loginName}" readonly="readonly">
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">证券账户</td>
                        <td width="88%" align="left" height="50">
                            <span class="text010">
                                <label for="bondAccount">
                                    <select id="bondAccount" type="" class="inp005" name="bondAccount">
                                    <c:forEach var="bondAccount" items="${mapBondAccount}" varStatus="vs">
                                        <c:set var="bondName" value="${bondAccount.key}"/>
                                        <option class="text001" value="${bondName}">${bondName}</option>
                                    </c:forEach>
                                </select>
                                </label>
                            </span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">证券名</td>
                        <td align="left">
                            <input id="bondName" name="bondName" class="inp001"
                                   onblur=ajaxName()>
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
                        <a href="list" target="mainframe"
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
        let bondName = $('#bondName');
        if (bondName===""){
            alert("请输入证券名");
        }
        $.ajax({
            type:"post",
            url:"check",
            data: "bondAccount=" + $('#bondAccount').val() +"&bondName=" + bondName.val(),
            async: false,
            success: function (msg) {
                if (!msg){
                    bondName.val();
                    alert("证券名重复，请重新输入");
                } else{
                    sub();
                }
            },

            dataType: "json"
        })
    }

    function ajaxName(id) {
        let bondName = $('#bondName');
        if (bondName===""){
            alert("请输入证券名");
        }
        $.ajax({
            type:"post",
            url:"check",
            data: "bondAccount=" + $('#bondAccount').val() +"&bondName=" + bondName.val(),
            async: false,
            success: function (msg) {
                if (!msg){
                    bondName.val();
                    alert("证券名重复，请重新输入");
                }
            },
            dataType: "json"
        })

    }
</script>
</body>
</html>
