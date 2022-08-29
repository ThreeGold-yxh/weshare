<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<div style="margin-top: 5%">
    <c:forEach items="${requestScope.goodsList}" var="goods" varStatus="status">
        <a href="<%=request.getContextPath()%>/fore/goods/goods-detail?id=${goods.wsGoodsId}"
           style="text-decoration: none">
            <div class="col-md-2 col-sm-4 col-xs-6 ms-4 my-3" style="text-align: center; display: inline-block">

                    <%--<div class="card" style="border: 2px royalblue solid">--%>
                    <%--&lt;%&ndash;header里面已经定义了basePath，不允许重复定义&ndash;%&gt;--%>
                    <%--<img src="<%=basePath%>${goods.wsGoodsImage}" height="200px" class="card-img-top" alt="...">--%>
                    <%--<div class="card-body">--%>
                    <%--<h5 class="card-title" style="color: #1a1e21">${goods.wsGoodsName}</h5>--%>
                    <%--<p class="card-text" style="color: #d63384">${goods.wsGoodsPrice} GBP</p>--%>
                    <%--<p></p>--%>
                    <%--<p class="card-text">${goods.wsHistoryCreateDate}</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                <c:if test="${goods.wsGoodsAmount > 0 && goods.wsGoodsStatus == 1}">
                    <div class="card" style="border: 2px royalblue solid">
                            <%--header里面已经定义了basePath，不允许重复定义--%>
                        <div>
                            <img src="<%=basePath%>${goods.wsGoodsImage}" height="200px" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title" style="color: #1a1e21">${goods.wsGoodsName}</h5>
                            <p class="card-text" style="color: #d63384">${goods.wsGoodsPrice} GBP</p>
                            <p></p>
                            <p class="card-text">${goods.wsHistoryCreateDate}</p>
                        </div>
                    </div>
                </c:if>

                <c:if test="${goods.wsGoodsAmount <= 0 || goods.wsGoodsStatus == 0}">
                    <div class="card" style="border: 2px royalblue solid; background-color: #636464">
                            <%--header里面已经定义了basePath，不允许重复定义--%>
                        <c:if test="${goods.wsGoodsStatus == 0}">
                            <div>
                                <img src="<%=basePath%>${goods.wsGoodsImage}" height="200px" class="card-img-top"
                                     alt="..."
                                     style="filter: grayscale(100%)">
                                <span style="position: absolute; z-index: 2; left: 10px; top: 10px; color: red">taken down</span>
                            </div>
                        </c:if>
                        <c:if test="${goods.wsGoodsAmount <= 0}">
                            <div>
                                <img src="<%=basePath%>${goods.wsGoodsImage}" height="200px" class="card-img-top"
                                     alt="..."
                                     style="filter: grayscale(100%)">
                                <span style="position: absolute; z-index: 2; left: 10px; top: 10px; color: red">sold out</span>
                            </div>
                        </c:if>
                        <div class="card-body">
                            <h5 class="card-title" style="color: #1a1e21">${goods.wsGoodsName}</h5>
                            <p class="card-text" style="color: #d63384">${goods.wsGoodsPrice} GBP</p>
                            <p></p>
                            <p class="card-text">${goods.wsHistoryCreateDate}</p>
                        </div>
                    </div>
                </c:if>
            </div>
        </a>

    </c:forEach>
</div>