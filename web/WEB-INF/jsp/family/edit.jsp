<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>家庭信息管理</title>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="<c:url value='/js/common.js' />"></script>
    <script src="<c:url value='/js/jquery-3.0.0.min.js' />"></script>
</head>

<body leftmargin="0" topmargin="0"
      onLoad="MM_preloadImages('<c:url value='/images/login_10.gif' />','<c:url value='/images/login_12.gif' />','<c:url value='/images/login_09.gif' />','<c:url value='/images/login_11.gif' />')">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="32" align="left" valign="top" class="text006">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr valign="top">
                    <td>【 家庭信息管理 】</td>
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
                        <span class="text001">&nbsp;&nbsp;${editType==0?'修改家庭信息':editType==1?'修改家庭成员':'添加家庭信息'}</span></td>
                </tr>
            </table>
            <c:if test="${editType==0}">
            <form:form action="editFamily" method="POST" modelAttribute="familyAccount" name="frmAction">
                <table width="98%" border="0" cellpadding="0" cellspacing="0" class="text008">
                    <tr align="center">
                        <td height="35" align="right">家庭序号</td>
                        <td align="left">
                            <form:input path="id" class="inp001" readonly="true"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td height="35" align="right">家庭名称</td>
                        <td align="left">
                            <form:input path="name" class="inp001" />
                        </td>
                    </tr>
                </table>
            </form:form>
            </c:if>
            <c:if test="${editType==2}">
            <form:form action="editFamily" method="POST" modelAttribute="familyAccount" name="frmAction">
                <table width="98%" border="0" cellpadding="0" cellspacing="0" class="text008">
                    <tr align="center">
                        <td height="35" align="right">家庭名称</td>
                        <td align="left">
                            <form:input path="name" class="inp001" />
                        </td>
                    </tr>
                </table>
            </form:form>
            </c:if>
            <c:if test="${editType==1}">
            <form:form action="editMember" method="POST" modelAttribute="account" name="frmAction">
                <form:hidden path="id" />
                <table width="98%" border="0" cellpadding="0" cellspacing="0" class="text008">
                    <tr align="center">
                        <td height="35" align="right">用户Id</td>
                        <td align="left">
                            <form:input path="id" class="inp001" readonly="true"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">家庭<br/>(Id/名称)</td>
                        <td width="88%" align="left">
                            <span class="text010">
                                <form:select path="familyId"  class="inp005">
                                    <c:forEach items="${familyAccounts}" var="family">
                                    <form:option value="${family.getId()}">${family.getId()}/${family.getName()}</form:option>
                                    </c:forEach>
                                </form:select>
                            </span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="12%" height="35" align="right">家庭管理员</td>
                        <td width="88%" align="left">
                            <span class="text010">
                                <form:select path="isSuperAccount"  class="inp005">
                                    <form:option value="true">是</form:option>
                                    <form:option value="false">否</form:option>
                                </form:select>
                            </span>
                        </td>
                    </tr>
                </table>
            </form:form>
            </c:if>
            <table width="98%" border="0" cellpadding="0" cellspacing="0" class="bor001">
                <tr>
                    <td width="11%" height="40" align="center">
                        <img onclick="summit()"
                           onMouseOver="MM_swapImage('Image1','',
                                   '<c:url value="/images/index_12_1.gif" />',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image1','',
                                   '<c:url value="/images/index_12_2.gif" />',1)"
                           onMouseUp="MM_swapImage('Image1','',
                                   '<c:url value="/images/index_12_1.gif" />',1)"
                           src="<c:url value='/images/index_12_0.gif' />"
                           name="Image1" width="75" height="24" border="0" id="Image1">
                    </td>
                    <td width="11%" height="40" align="center">
                        <a href="<c:url value='/family/list' />"
                           onMouseOver="MM_swapImage('Image2','',
                                   '<c:url value="/images/login_09.gif" />',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image2','',
                                   '<c:url value="/images/login_11.gif" />',1)"
                           onMouseUp="MM_swapImage('Image2','',
                                   '<c:url value="/images/login_09.gif" />',1)">
                            <img src="<c:url value='/images/login_07.gif' />"
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