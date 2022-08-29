<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<%--<div class="col-md-4 col-sm-4 col-xs-4 ps-3" style="text-align: center; display: inline-block; margin-top: 10%">--%>

<%--class="card-img-top"--%>
<%--</div>--%>


<%
    //　　1.request.getSchema();可以返回当前页面所使用的协议，就是"http"
//　　2.request.getServerName();返回当前页面所在服务器的名字，就是上面例子中的"localhost"
//　　3.request.getServerPort();返回当前页面所在服务器的端口号，就是上面例子中的"8080"
//　　4.request.getContextPath();返回当前页面所在的应用的名字
    String bathGoodsDetailPage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>



<div id="container" style="margin-top: 20px">
    <div class="col-md-4 col-sm-4 col-xs-4" style="float: left; margin-left: 15%">
        <%--header里面已经定义了basePath，不允许重复定义--%>
        <img src="<%=bathGoodsDetailPage%>${goods.wsGoodsImage}" height="450px" alt="..." width="100%">
    </div>
    <%--<div class="col-md-5 col-sm-5 col-xs-5 ps-3 pt-3" style="float: right ; border: 8px solid; border-color: red; height: 450px; margin-right: 10%; text-align: center">--%>
    <div class="col-md-5 col-sm-5 col-xs-5 ps-3 pt-3" style="float: right ; height: 450px; margin-right: 10%; text-align: center">
        <h3 style="color: #1a1e21">Name: ${goods.wsGoodsName}</h3>
        <p style="color: #d63384; margin-top: 10px">Prize: ${goods.wsGoodsPrice} GBP</p>
        <a href="${pageContext.request.contextPath}/fore/user/user-detail?id=${requestScope.seller.wsUserId}" style="text-decoration: none"><p style="margin-top: 10px">seller: ${requestScope.seller.wsUserNickname}</p></a>
        <p style="margin-top: 10px">Stock: ${goods.wsGoodsAmount}</p>
        <h4 style="color: darkblue">Description</h4>
        <%--vertical-align: middle--%>
        <div style="word-wrap:break-word; word-break:break-all; height: 200px; margin-right: 5%; border: 2px solid; border-color: black; text-align: left">${goods.wsGoodsDescription}</div>
        <div>
            <span><a href="${pageContext.request.contextPath}/fore/buyer/confirm-pay?id=${goods.wsGoodsId}" class="btn btn-primary mx-4 my-3">buy it</a></span>
            <%--<span><a href="#" class="btn m-2">      </a></span>--%>
            <span><a href="${pageContext.request.contextPath}/fore/user/tracker/add?id=${goods.wsGoodsId}" class="btn btn-danger mx-4 my-3">favourite</a></span>
        </div>
    </div>

    <c:if test="${not empty requestScope.ErrMsg && !(requestScope.ErrMsg eq null) }">
        <div style="text-align: center; color: red">
                ${requestScope.ErrMsg}
        </div>
        <%--<script> alert(${requestScope.ErrMsg});</script>--%>
    </c:if>

</div>





