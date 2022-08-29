<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<div style="text-align: center; margin-top: 5%">
    <div style="margin-top: 3%">
        <a href="${pageContext.request.contextPath}/fore/buyer/order/deal-with-score?id=${requestScope.id}&scoreLevel=1">
            <button class="btn-outline-danger" style="width: 200px; height: 50px">Perfect</button>
        </a>
    </div>

    <div style="margin-top: 3%">
        <a href="${pageContext.request.contextPath}/fore/buyer/order/deal-with-score?id=${requestScope.id}&scoreLevel=2">
            <button class="btn-outline-danger" style="width: 200px; height: 50px">Good</button>
        </a>
    </div>

    <div style="margin-top: 3%">
        <a href="${pageContext.request.contextPath}/fore/buyer/order/deal-with-score?id=${requestScope.id}&scoreLevel=3">
            <button class="btn-outline-danger" style="width: 200px; height: 50px">Not Bad</button>
        </a>
    </div>

    <div style="margin-top: 3%">
        <a href="${pageContext.request.contextPath}/fore/buyer/order/deal-with-score?id=${requestScope.id}&scoreLevel=4">
            <button class="btn-outline-danger" style="width: 200px; height: 50px">Bad</button>
        </a>
    </div>


</div>