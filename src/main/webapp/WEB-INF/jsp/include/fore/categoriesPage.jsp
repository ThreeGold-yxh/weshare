<%@ page import="com.yxh.weshare.bean.bo.FilterBo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%
//    Object filterBo = request.getSession().getAttribute("filterBo");
//    FilterBo filterBoBean = null;
//    if (filterBo != null) {
//        filterBo = (FilterBo) filterBoBean;
//    }
%>

<div>
    <nav class="navbar navbar-light" style="color: blue">
        <div class="container-fluid">
            <a class="navbar-brand" href="#" style="font-size: 20px"></a>
            <%--<a class="navbar-brand" href="#" style="font-size: 20px">Offcanvas navbar</a>--%>
            <%--<a class="navbar-brand" href="#" style="font-size: 20px">Offcanvas navbar</a>--%>
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                    aria-controls="offcanvasNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
                 aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>

                <c:if test="${not empty requestScope.filterErrMsg && !(requestScope.filterErrMsg eq null) }">
                    <div style="text-align: center; color: red">
                            ${requestScope.filterErrMsg}
                    </div>
                    <%--<script> alert(${requestScope.ErrMsg});</script>--%>
                </c:if>

                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

                        <%--<li class="nav-item">--%>
                        <%--<a class="nav-link active" aria-current="page" href="#">Home</a>--%>
                        <%--</li>--%>

                        <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">Link</a>--%>
                        <%--</li>--%>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="prizeFilterNavbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                filter
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="offcanvasNavbarDropdown">
                                <form method="post" action="${pageContext.request.contextPath}/fore/home/edit-filter">
                                    <li>
                                        <div class="form-check form-switch" style="margin-left: 5%">
                                            <c:if test="${requestScope.filterBo == null || requestScope.filterBo.orderByPrize == null}">
                                                <input class="form-check-input" type="checkbox"
                                                       id="flexSwitchCheckPrize" name="orderByPrize" value="selected">
                                            </c:if>

                                            <c:if test="${requestScope.filterBo != null && requestScope.filterBo.orderByPrize != null}">
                                                <input class="form-check-input" type="checkbox"
                                                       id="flexSwitchCheckPrize" name="orderByPrize" value="selected"
                                                       checked>
                                            </c:if>

                                            <label class="form-check-label" for="flexSwitchCheckPrize">order by
                                                prize</label>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-check form-switch" style="margin-left: 5%">
                                            <c:if test="${requestScope.filterBo == null || requestScope.filterBo.orderByPrizeDESC == null}">
                                                <input class="form-check-input" type="checkbox"
                                                       id="flexSwitchCheckPrizeDESC" name="orderByPrizeDESC"
                                                       value="selected">
                                            </c:if>

                                            <c:if test="${requestScope.filterBo != null && requestScope.filterBo.orderByPrizeDESC != null}">
                                                <input class="form-check-input" type="checkbox"
                                                       id="flexSwitchCheckPrizeDESC" name="orderByPrizeDESC"
                                                       value="selected" checked>
                                            </c:if>
                                            <label class="form-check-label" for="flexSwitchCheckPrizeDESC">order by
                                                prize desc</label>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-check form-switch" style="margin-left: 5%">
                                            <c:if test="${requestScope.filterBo == null || requestScope.filterBo.orderByTime == null}">
                                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckTime"
                                                       name="orderByTime" value="selected">
                                            </c:if>

                                            <c:if test="${requestScope.filterBo != null && requestScope.filterBo.orderByTime != null}">
                                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckTime"
                                                       name="orderByTime" value="selected" checked>
                                            </c:if>
                                            <label class="form-check-label" for="flexSwitchCheckTime">order by
                                                time</label>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-check form-switch" style="margin-left: 5%">
                                            <c:if test="${requestScope.filterBo == null || requestScope.filterBo.orderByTimeDESC == null}">
                                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckTimeDESC"
                                                       name="orderByTimeDESC" value="selected">
                                            </c:if>

                                            <c:if test="${requestScope.filterBo != null && requestScope.filterBo.orderByTimeDESC != null}">
                                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckTimeDESC"
                                                       name="orderByTimeDESC" value="selected" checked>
                                            </c:if>
                                            <label class="form-check-label" for="flexSwitchCheckTimeDESC">order by time
                                                desc</label>
                                        </div>
                                    </li>

                                    <button type="submit" class="btn-danger" style="margin-left: 40%; margin-top: 5%">
                                        submit
                                    </button>
                                </form>

                                <%--<li>--%>
                                <%--<div class="form-check form-switch" style="margin-left: 5%">--%>
                                <%--<input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">--%>
                                <%--<label class="form-check-label" for="flexSwitchCheckDefault">Default switch--%>
                                <%--checkbox input</label>--%>
                                <%--</div>--%>
                                <%--</li>--%>
                            </ul>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="offcanvasNavbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Categories
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="offcanvasNavbarDropdown">
                                <%--<li>--%>
                                <%--<hr class="dropdown-divider">--%>
                                <%--</li>--%>
                                <c:forEach items="${requestScope.categories}" var="category" varStatus="status">
                                    <li><a class="dropdown-item"
                                           href=<%=request.getContextPath()%>/fore/home/show/select-category/${category.wsCategoryId}>${category.wsCategoryName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>

                    </ul>
                    <%--<form class="d-flex" role="search">--%>
                    <%--<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">--%>
                    <%--<button class="btn btn-outline-success" type="submit">Search</button>--%>
                    <%--</form>--%>
                </div>
            </div>
        </div>
    </nav>
</div>


