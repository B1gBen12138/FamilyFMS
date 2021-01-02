<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>家庭成员管理</title>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="<c:url value='/js/common.js' />"></script>
    <script src="<c:url value='/js/jquery-3.0.0.min.js' />"></script>
</head>

<body topmargin="0" leftmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="32" align="left" valign="top" class="text006">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr valign="top">
                    <td>【 家庭成员管理 】</td>
                    <c:if test="${isAdmin || !isSuperAccount}">
                    <td align="right">
                        <a href="edit" target="mainframe"
                           onMouseOver="MM_swapImage('Image1','','<c:url value="/images/index_10_1.gif" />',1)"
                           onMouseOut="MM_swapImgRestore()"
                           onMouseDown="MM_swapImage('Image1','','<c:url value="/images/index_10_2.gif" />',1)"
                           onMouseUp="MM_swapImage('Image1','','<c:url value="/images/index_10_1.gif" />',1)">
                            <img src="<c:url value='/images/index_10_0.gif' />" width="75"
                                 height="24" border="0" align="top" id="Image1">
                        </a>
                    </td>
                    </c:if>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="215" valign="top">
            <table width="98%" border="0" cellspacing="0" cellpadding="0">
                <tr align="center" class="bg03">
                    <td width="5%" height="40"> 序号</td>
                <c:choose>
                    <c:when test="${isAdmin || isSuperAccount}">
                    <td width="10%" class="text007"> 家庭Id</td>
                    <td width="25%" class="text007"> 家庭名称</td>
                    <td width="10%" class="text007"> 账户Id</td>
                    <td width="25%" class="text007"> 账户名称</td>
                    <td width="10%" class="text007"> 家庭管理员</td>
                    <td width="15%" class="text007"> 操作</td>
                    </c:when>
                    <c:otherwise>
                    <td width="12.5%" class="text007"> 家庭Id</td>
                    <td width="35%" class="text007"> 家庭名称</td>
                    <td width="12.5%" class="text007"> 账户Id</td>
                    <td width="35%" class="text007"> 账户名称</td>
                    </c:otherwise>
                </c:choose>
                </tr>

                <c:forEach var="familyMember" items="${familyMemberList}" varStatus="vs">
                    <tr align="center" <c:if test="${vs.index%2==1}"> class="bg04"</c:if>  >
                        <td height="35">${vs.index+1}</td>
                        <td height="35">${familyMember.get("familyId")}</td>
                        <td height="35">${familyMember.get("familyName")}</td>
                        <td height="35">${familyMember.get("accountId")}</td>
                        <td height="35">${familyMember.get("accountName")}</td>
                        <c:if test="${isAdmin || isSuperAccount}">
                        <td height="35">${familyMember.get("isAdmin")==true?'是':'否'}</td>
                        <c:set var='arg_familyId' value='familyId=${familyMember.get("familyId")}'/>
                        <c:set var='arg_accountId' value='accountId=${familyMember.get("accountId")}'/>
                        <td>
                        <c:if test='${familyMember.get("familyId")!=""}'>
                            <a href="edit?${arg_familyId}">
                                <img src="<c:url value='/images/icon_set.gif' />" alt="编 辑" title="编辑家庭" width="14" height="15" border="0" />
                            </a>
                            <a href="delete?${arg_familyId}">
                                <img src="<c:url value='/images/delete.gif' />" alt="删 除" title="删除家庭" width="14" height="15" hspace="5" />
                            </a>
                            <a href="delete?${arg_accountId}">
                                <img src="<c:url value='/images/delete_2.gif' />" alt="删 除" title="删除家庭成员" width="14" height="15" hspace="5" />
                            </a>
                        </c:if>
                        <c:if test='${familyMember.get("accountId")!=""}'>
                            <a href="edit?${arg_accountId}">
                                <img src="<c:url value='/images/icon_set_2.gif' />" alt="编 辑" title="编辑家庭成员" width="14" height="15" border="0" hspace="5" />
                            </a>
                        </c:if>
                        </td>
                        </c:if>
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
