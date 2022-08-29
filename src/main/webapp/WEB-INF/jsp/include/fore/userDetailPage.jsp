<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<div style="width: 40%; margin-left: 30%; margin-right: 30%; height: 320px; border: 2px solid black; margin-top: 5%; background-color: honeydew; position: relative">


    <h4 style="position: absolute; z-index: 2; left: 10%; top: 10%; color: seagreen;">Nick Name:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 10%; color: black">${requestScope.user.wsUserNickname}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 28%; color: seagreen;">Account:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 28%; color: black;">${requestScope.user.wsUserAccount}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 46%; color: seagreen;">Email:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 46%; color: black;">${requestScope.user.wsUserEmail}</h4>

    <%--<h4 style="position: absolute; z-index: 2; left: 10%; top: 64%; color: seagreen;">Address:</h4>--%>
    <%--<h4 style="position: absolute; z-index: 2; left: 40%; top: 64%; color: black;">${requestScope.user.wsUserAddress}</h4>--%>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 64%; color: seagreen;">Credit:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 64%; color: black;">${requestScope.user.wsUserCredit}</h4>

    <c:if test="${requestScope.previouspath!=null}">
        <a href="${requestScope.previouspath}" style="text-decoration: none; position: absolute; z-index: 2; left: 45%; top: 82%;"><button class="btn-primary">back</button></a>
    </c:if>

</div>