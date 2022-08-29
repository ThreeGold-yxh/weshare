<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%
    String currentUrl = (String) request.getAttribute("currentUrl");
    //如果有问号，说明是先选了页码，要把原来的页码去掉
    if (currentUrl != null && currentUrl.contains("?")) {
        currentUrl = currentUrl.substring(0, currentUrl.indexOf("?"));
    }
%>

<%-- 使用 pageHelper-bootstrap 实现分页功能  --%>

<%
    Integer curPage = (Integer) request.getAttribute("currentpage");
    Integer tolPage = (Integer) request.getAttribute("totalpages");
    Integer stratPage = 1;
    Integer endPage = tolPage;
    if (curPage - 2 > 1) {
        stratPage = curPage - 2;
    }
    if (curPage + 2 < endPage) {
        endPage = curPage + 2;
    }
%>

<nav style="text-align: center; padding-left: 35%; margin-top: 5%">
    <ul class="pagination">
        <c:if test="${requestScope.currentpage> 1}">
            <li class="page-item">
                <a class="page-link" href="<%=currentUrl%>?page=${requestScope.currentpage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="<%=stratPage%>" end="<%=endPage%>" var="page">
            <li class="page-item">
                <a class="page-link" href="<%=currentUrl%>?page=${page}">${page}</a>
            </li>
        </c:forEach>

        <c:if test="${requestScope.currentpage * 1 < requestScope.totalpages}">
            <li class="page-item">
                <a class="page-link" href="<%=currentUrl%>?page=${requestScope.currentpage+1}">Next</a>
            </li>
        </c:if>

        <%--<li class="page-item dropdown">--%>
        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
        <%--select page--%>
        <%--<b class="caret"></b>--%>
        <%--</a>--%>
        <%--<ul class="dropdown-menu">--%>
        <%--<c:forEach begin="<%=stratPage%>" end="<%=tolPage%>" var="page">--%>
        <%--<li>--%>
        <%--<a class="page-link" href="<%=currentUrl%>?page=${page}">${page}</a>--%>
        <%--</li>--%>
        <%--</c:forEach>--%>
        <%--</ul>--%>
        <%--</li>--%>

        <c:if test="${requestScope.totalpages > 0}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLinkB" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    select page
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <c:forEach begin="1" end="<%=tolPage%>" var="page">
                        <li>
                            <a class="page-link" href="<%=currentUrl%>?page=${page}">${page}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:if>

        <c:if test="${requestScope.totalpages > 0}">
            <li class="page-item" style="color: #1a1e21">
                <a class="page-link" href=""
                   style="color: #1a1e21">${requestScope.currentpage}/${requestScope.totalpages}</a>
            </li>
        </c:if>
    </ul>
</nav>


<%--<!--/分页-->--%>
<%--<div class="container">--%>
<%--<div class="row myCenter">--%>
<%--<div class="col-xs-6 col-md-12 col-center-block">--%>
<%--<ul class="pagination col-md-5 col-center-block">--%>

<%--<c:if test="${requestScope.currentpage> 1}">--%>
<%--<li class="page-item">--%>
<%--<a class="page-link" href="<%=currentUrl%>?page=${requestScope.currentpage-1}">Previous</a>--%>
<%--</li>--%>
<%--</c:if>--%>

<%--<c:forEach begin="<%=stratPage%>" end="<%=endPage%>" var="page">--%>
<%--<li class="page-item">--%>
<%--<a class="page-link" href="<%=currentUrl%>?page=${page}">${page}</a>--%>
<%--</li>--%>
<%--</c:forEach>--%>


<%--<li>--%>
<%--<label><select id="pageSize" aria-controls="DataTables_Table_0"--%>
<%--size="1" name="DataTables_Table_0_length" class="g-pages">--%>
<%--<option selected="selected" value="5">5条/页</option>--%>
<%--<option value="10">10条/页</option>--%>
<%--<option value="20">20条/页</option>--%>
<%--<option value="30">30条/页</option>--%>
<%--</select></label>--%>
<%--</li>--%>

<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<!--分页/-->--%>


