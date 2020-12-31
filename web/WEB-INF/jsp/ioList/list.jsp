<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>收支项目管理</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="32" align="left" valign="top" class="text006">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr valign="top">
                    <td>【 收支项目管理 】</td>
                    <td align="right">
                        <a href="edit" target="mainframe"
                           onMouseOver="MM_swapImage('Image1','','${pageContext.request.contextPath}/images/index_10_1.gif',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image1','','${pageContext.request.contextPath}/images/index_10_2.gif',1)"
                           onMouseUp="MM_swapImage('Image1','','${pageContext.request.contextPath}/images/index_10_1.gif',1)">
                            <img src="${pageContext.request.contextPath}/images/index_10_0.gif" width="75"
                                 height="24"
                                 border="0" align="top" id="Image1">
                        </a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="215" valign="top">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr align="center" class="bg03">
                    <td width="5%" height="35"> 序号</td>
                    <td width="10%" class="text007"> 账户序号</td>
                    <td width="10%" class="text007"> 家庭序号</td>
                    <td width="10%" class="text007"> 收入/支出</td>
                    <td width="10%" class="text007"> 收支来源</td>
                    <td width="10%" class="text007"> 收支类型</td>
                    <td width="10%" class="text007"> 收支金额</td>
                    <td width="10%" class="text007"> 收支日期</td>
                    <td width="10%" class="text007"> 描述</td>
                    <td width="15%" class="text007"> 操作</td>
                </tr>

                <c:forEach var="ioList" items="${ioLists}" varStatus="vs">
                    <tr align="center" <c:if test="${vs.index%2==1}"> class="bg04"</c:if>  >
                        <td height="35">${vs.index+1}</td>
                        <td height="35">${ioList.accountId}</td>
                        <td height="35">${ioList.familyId}</td>
                        <td>
                            <c:if test="${ioList.isOutput}">支出</c:if>
                            <c:if test="${!ioList.isOutput}">收入</c:if>
                        </td>
                        <td height="35">${ioList.source}</td>
                        <td height="35">${ioList.convertType()}</td>
                        <td height="35">${ioList.money}</td>
                        <td height="35">${ioList.date}</td>
                        <td height="35">${ioList.comment}</td>
                        <td>
                            <a href="edit?id=${ioList.id}">
                                <img src="${pageContext.request.contextPath}/images/icon_set.gif" alt="编 辑" width="14" height="15" border="0" />
                            </a>
                            <a href="delete?id=${ioList.id}">
                                <img src="${pageContext.request.contextPath}/images/delete.gif" alt="删 除" width="13" height="15" hspace="10" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td align=left valign=top>
            <hr class="bor005" size="1"/>
        </td>
    </tr>
</table>
</body>
</html>
