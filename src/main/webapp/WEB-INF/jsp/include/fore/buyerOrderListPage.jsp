<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%--<div style="visibility: hidden">--%>
    <%--<div>x</div>--%>
    <%--<div>x</div>--%>
    <%--<div>x</div>--%>
<%--</div>--%>

<c:forEach items="${requestScope.orderVoList}" var="orderVo" varStatus="status">
    <div style="width: 60%; margin-left: 20%; margin-right: 20%; height: 306px; border: 2px solid black; margin-top: 5%; background-color: honeydew; position: relative">

        <img src="<%=basePath%>${orderVo.wsGoodsImage}" height="300px" width="300px" alt="..." style="margin-top: 1px; margin-bottom: 2px">

        <span style="position: absolute; z-index: 2; left: 36%; top: 10%; color: seagreen;">Order ID:</span>
        <span style="position: absolute; z-index: 2; left: 58%; top: 10%; color: black">${orderVo.wsOrderId}</span>

        <h4 style="position: absolute; z-index: 2; left: 36%; top: 22%; color: seagreen;">Goods:</h4>
        <h4 style="position: absolute; z-index: 2; left: 58%; top: 22%; color: black;">${orderVo.wsGoodsName}</h4>

        <span style="position: absolute; z-index: 2; left: 36%; top: 34%; color: seagreen;">Prize:</span>
        <span style="position: absolute; z-index: 2; left: 58%; top: 34%; color: black;">${orderVo.wsOrderPrice}  GBP</span>

        <span style="position: absolute; z-index: 2; left: 36%; top: 46%; color: seagreen;">Seller:</span>
        <span style="position: absolute; z-index: 2; left: 58%; top: 46%; color: black;">${orderVo.wsOrderSellerName}</span>

        <span style="position: absolute; z-index: 2; left: 36%; top: 58%; color: seagreen;">Buyer:</span>
        <span style="position: absolute; z-index: 2; left: 58%; top: 58%; color: black;">${orderVo.wsOrderBuyerName}</span>

        <span style="position: absolute; z-index: 2; left: 36%; top: 70%; color: seagreen;">Address:</span>
        <span style="position: absolute; z-index: 2; left: 58%; top: 70%; color: black;">${orderVo.wsOrderAddress}</span>

        <span style="position: absolute; z-index: 2; left: 36%; top: 82%; color: seagreen;">Date:</span>
        <span style="position: absolute; z-index: 2; left: 58%; top: 82%; color: black;">${orderVo.wsOrderCreateDate}</span>

        <c:if test="${orderVo.wsOrderStatus == 0}">
            <%--等待卖方发货--%>
            <span style="position: absolute; z-index: 2; right: 2%; top: 5%;color: red">wait for shipment</span>
        </c:if>

        <c:if test="${orderVo.wsOrderStatus == 1}">
            <%--卖方已发货，等待买方确定收货--%>
            <span style="position: absolute; z-index: 2; right: 2%; top: 5%;color: red">shipped</span>
            <a href="${pageContext.request.contextPath}/fore/buyer/order/confirm-receive?id=${orderVo.wsOrderId}" style="text-decoration: none"><button class="btn-danger" style="position: absolute; z-index: 2; right: 2%; bottom: 5%">confirm to receive</button></a>
        </c:if>

        <%--<button class="btn-danger" style="position: absolute; z-index: 2; right: 2%; top: 5%;">Date:</button>--%>

        <c:if test="${orderVo.wsOrderStatus == 2 || orderVo.wsOrderStatus == 4}">
            <%--==2 意味着双方都还没有评价，但买方已经收到货了， ==4 意味着卖方已经论了，但买方还没有评论--%>
            <span style="position: absolute; z-index: 2; right: 2%; top: 5%;color: blue">wait for scoring</span>
            <a href="${pageContext.request.contextPath}/fore/buyer/order/score-for-trade?id=${orderVo.wsOrderId}" style="text-decoration: none"><button class="btn-danger" style="position: absolute; z-index: 2; right: 2%; bottom: 5%">rate for trade</button></a>
            <%--</c:if><a href="" style="text-decoration: none">--%>
            <%--<button class="btn-danger" style="position: absolute; z-index: 2; right: 2%; top: 5%;">Date:</button>--%>
        </c:if>

        <c:if test="${orderVo.wsOrderStatus == 3}">
            <%--==3 意味着buyer已经评价，在等待seller评价 --%>
            <span style="position: absolute; z-index: 2; right: 2%; top: 5%;color: blue">wait for seller</span>
            <%--</c:if><a href="" style="text-decoration: none">--%>
            <%--<button class="btn-danger" style="position: absolute; z-index: 2; right: 2%; top: 5%;">Date:</button>--%>
        </c:if>


        <c:if test="${orderVo.wsOrderStatus == 5}">
            <%--==5 意味着都评论完了 --%>
            <span style="position: absolute; z-index: 2; right: 2%; top: 5%;color: blue">finished</span>
            <%--</c:if><a href="" style="text-decoration: none">--%>
            <%--<button class="btn-danger" style="position: absolute; z-index: 2; right: 2%; top: 5%;">Date:</button>--%>
        </c:if>

    </div>
    <div>

    </div>
</c:forEach>