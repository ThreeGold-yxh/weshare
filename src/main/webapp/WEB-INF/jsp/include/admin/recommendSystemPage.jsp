<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<div style="text-align: center; margin-top: 5%">
    <div style="margin-top: 3%">
        <a href="${pageContext.request.contextPath}/admin/recommend-system/execute">
            <button class="btn-outline-danger" style="width: 200px; height: 50px">Execute</button>
        </a>
    </div>
    <div style="text-align: center; margin-top: 5%">
        <c:if test="${not empty requestScope.fileName}">
            <img src="${pageContext.request.contextPath}/static/img/recommend/${requestScope.fileName}" width=600 height=400 border=0 alt="">
        </c:if>
    </div>
</div>