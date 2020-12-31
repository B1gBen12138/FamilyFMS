<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>收支项目管理</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
</head>

<body leftmargin="0" topmargin="0"
      onLoad="MM_preloadImages('${pageContext.request.contextPath}/images/login_10.gif','${pageContext.request.contextPath}/images/login_12.gif','${pageContext.request.contextPath}/images/login_09.gif','${pageContext.request.contextPath}/images/login_11.gif')">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="32" align="left" valign="top" class="text006">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr valign="top">
                    <td>【 收支项目管理 】</td>
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
                        <span class="text001">&nbsp;&nbsp;修改收支项目</span></td>
                </tr>
            </table>
            <form:form action="edit" method="POST" modelAttribute="ioList" name="frmAction">
                <form:hidden path="id" />
                <table width="98%" border="0" cellpadding="0" cellspacing="0" class="text008">
                    <c:if test="${isAdmin}">
                    <tr align="center">
                        <td height="35" align="right">用户ID</td>
                        <td align="left">
                            <form:input path="accountId" class="inp001" />
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">家庭ID</td>
                        <td align="left">
                            <form:input path="familyId" class="inp001" />
                        </td>
                    </tr>
                    </c:if>
                    <tr align="center">
                        <td width="12%" height="35" align="right">支出/收入</td>
                        <td width="88%" align="left">
                            <span class="text010">
                                <form:select path="isOutput"  class="inp005">
                                    <form:option value="true">支出</form:option>
                                    <form:option value="false">收入</form:option>
                                </form:select>
                            </span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">收支来源</td>
                        <td align="left">
                            <form:input path="source" class="inp001" />
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">收支类型<br/>(支出/收入)</td>
                        <td width="88%" align="left">
                            <span class="text010">
                                <form:select path="type"  class="inp005">
                                    <c:forEach items="${typeMap}" var="item">
                                    <form:option value="${item.key}">${item.value.get(0)}/${item.value.get(1)}</form:option>
                                    </c:forEach>
                                </form:select>
                            </span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">收支金额</td>
                        <td align="left">
                            <form:input path="money" class="inp001" />
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">收支日期</td>
                        <td align="left">
                            <input class="inp001" type="date" name="date"  value="<fmt:formatDate value='${ioList.date}' pattern='yyyy-MM-dd'/>" />
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">描述</td>
                        <td align="left">
                            <form:textarea path="comment" class="inp001" rows="5" cols="10"/>
                        </td>
                    </tr>
                </table>
            </form:form>
            <table width="98%" border="0" cellpadding="0" cellspacing="0" class="bor001">
                <tr>
                    <td width="11%" height="40" align="center">
                        <img onclick="summit()"
                           onMouseOver="MM_swapImage('Image1','',
                                   '${pageContext.request.contextPath}/images/index_12_1.gif',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image1','',
                                   '${pageContext.request.contextPath}/images/index_12_2.gif',1)"
                           onMouseUp="MM_swapImage('Image1','',
                                   '${pageContext.request.contextPath}/images/index_12_1.gif',1)"
                           src="${pageContext.request.contextPath}/images/index_12_0.gif"
                           name="Image1" width="75" height="24" border="0" id="Image1">
                    </td>
                    <td width="11%" height="40" align="center">
                        <a href="${pageContext.request.contextPath}/ioList/list"
                           onMouseOver="MM_swapImage('Image2','',
                                   '${pageContext.request.contextPath}/images/login_09.gif',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image2','',
                                   '${pageContext.request.contextPath}/images/login_11.gif',1)"
                           onMouseUp="MM_swapImage('Image2','',
                                   '${pageContext.request.contextPath}/images/login_09.gif',1)">
                            <img src="${pageContext.request.contextPath}/images/login_07.gif"
                                 name="Image2" width="75" height="24" border="0" id="Image2">
                        </a>
                    </td>
                    <td width="78%" align="right">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script>
    function summit() {
        document.getElementsByName("frmAction")[0].submit();
    }
</script>
</body>
</html>