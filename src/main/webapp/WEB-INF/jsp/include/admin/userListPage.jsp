<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%--<div style="visibility: hidden">--%>
<%--<div>x</div>--%>
<%--<div>x</div>--%>
<%--<div>x</div>--%>
<%--</div>--%>


<c:forEach items="${requestScope.userVoList}" var="userVo" varStatus="status">
    <a href="${pageContext.request.contextPath}/admin/user/detail?id=${userVo.wsUserId}" style="text-decoration: none">
        <div style="width: 80%; margin-left: 10%; margin-right: 10%; height: 50px; border: 2px solid black; margin-top: 1%; background-color: honeydew; position: relative">

        <span style="position: absolute; z-index: 2; left: 5%; top: 30%">
            <span style="color: seagreen">
                User ID:
            </span>
            <span style="color: black">
                    ${userVo.wsUserId}
            </span>
        </span>

            <span style="position: absolute; z-index: 2; left: 20%; top: 30%">
            <span style="color: seagreen">
                Nick Name:
            </span>
            <span style="color: black">
                    ${userVo.wsUserNickname}
            </span>
        </span>

            <span style="position: absolute; z-index: 2; left: 47%; top: 30%">
            <span style="color: seagreen">
                Account:
            </span>
            <span style="color: black">
                    ${userVo.wsUserAccount}
            </span>
        </span>

            <span style="position: absolute; z-index: 2; left: 80%; top: 30%">
            <span style="color: seagreen">
                Authority:
            </span>
            <span style="color: black">
                    ${userVo.authorityName}
            </span>
        </span>
        </div>
    </a>

</c:forEach>