<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<c:if test="${not empty requestScope.ErrMsg && !(requestScope.ErrMsg eq null) }">
    <div style="text-align: center; color: red; display: none">
            ${requestScope.ErrMsg}
    </div>
    <div style="text-align: center; color: red">
            ${requestScope.ErrMsg}
    </div>
    <%--<script> alert(${requestScope.ErrMsg});</script>--%>
</c:if>

<div style="width: 40%; margin-left: 30%; margin-right: 30%; height: 500px; border: 2px solid black; margin-top: 5%; background-color: honeydew; position: relative">
    <h4 style="position: absolute; z-index: 2; left: 10%; top: 5%; color: seagreen;">User Id:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 5%; color: black">${requestScope.userVo.wsUserId}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 14%; color: seagreen;">Nick Name:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 14%; color: black">${requestScope.userVo.wsUserNickname}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 24%; color: seagreen;">Account:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 24%; color: black;">${requestScope.userVo.wsUserAccount}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 34%; color: seagreen;">Email:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 34%; color: black;">${requestScope.userVo.wsUserEmail}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 44%; color: seagreen;">Address:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 44%; color: black;">${requestScope.userVo.wsUserAddress}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 54%; color: seagreen;">Credit:</h4>
    <h4 style="position: absolute; z-index: 2; left: 40%; top: 54%; color: black;">${requestScope.userVo.wsUserCredit}</h4>

    <h4 style="position: absolute; z-index: 2; left: 10%; top: 64%; color: seagreen;">Authority</h4>
    <%--<h4 style="position: absolute; z-index: 2; left: 40%; top: 88%; color: black;">${requestScope.userVo.authorityName}</h4>--%>

    <form action="${pageContext.request.contextPath}/admin/user/edit-authority" style="position: absolute; z-index: 2; left: 40%; top: 64%; color: black; height: 20%">
        <input name="userId" type="number" style="display: none" value="${requestScope.userVo.wsUserId}"/>
        <select name="selectedAuthority" class="form-select" aria-label="Default select example">
            <option value=${requestScope.userVo.authority} selected>${requestScope.userVo.authorityName}</option>
            <c:forEach items="${requestScope.authorities}" var="authority" varStatus="status">
                <option value=${authority.wsUserAuthority}>${authority.wsAuthorityDescription}</option>
            </c:forEach>
        </select>
        <button class="btn-primary" type="submit" style="position: absolute; z-index: 2; left: 20%; top: 90%; color: black">submit</button>
    </form>
</div>