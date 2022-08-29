<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/7/24
  Time: 0:00
  To change this template use File | Settings | File Templates.
  use bootstrap5
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    //　　1.request.getSchema();可以返回当前页面所使用的协议，就是"http"
//　　2.request.getServerName();返回当前页面所在服务器的名字，就是上面例子中的"localhost"
//　　3.request.getServerPort();返回当前页面所在服务器的端口号，就是上面例子中的"8080"
//　　4.request.getContextPath();返回当前页面所在的应用的名字
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<html lang="en">
<head>
    <title>WeShare</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 的 CSS 文件 -->
    <%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-V06iGeOYiHqaJG18vU/udsyVfgcm8Pgax9HmoZh65R0FrT9X2GZZ8w2ZQcZkzZGV" crossorigin="anonymous">--%>
    <%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rTTiRUKnSWaDu2FjhzWFl8/JuUZMlplyWE/djenb2LoKqkgLGfEGfSrL7XDLoB1M" crossorigin="anonymous">--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-Nj1D6pu2WnJojj+67GiU9ZFNwbl7bUWX5Kj5MS22C8bGjllemM9pvQyvj14zJb58" crossorigin="anonymous"></script>--%>
    <%--<link href="../../../css/bootstrap/5.1.3/bootstrap.min.css" rel="stylesheet">--%>
    <%--<script type="text/javascript" src="../../../js/bootstrap/5.1.3/bootstrap.bundle.min.js"></script>--%>
    <%--<%=basePath%>--%>
    <link href="<%=basePath%>css/bootstrap/5.1.3/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/fore/register.css" rel="stylesheet">
    <link href="<%=basePath%>css/fore/login.css" rel="stylesheet">
    <link href="<%=basePath%>css/fore/sellerGoodsAddPage.css" rel="stylesheet">
    <link href="<%=basePath%>css/fore/commentPage.css" rel="stylesheet">

    <script type="text/javascript" src="<%=basePath%>js/bootstrap/5.1.3/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/3.3.1/jquery-3.3.1.min.js"></script>


    <%--<script type="text/javascript" src="<%=basePath%>js/jquery/3.6.0/jquery-3.6.0.min.js"></script>--%>
    <%--<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>--%>

</head>


<body style="background-color: silver">
<%--导航栏--%>
<nav class="navbar navbar-expand-lg navbar-light " style="background-color: black">
    <%--<a class="navbar-brand" href="${pageContext.request.contextPath}/home">--%>
    <a class="navbar-brand" href="<%=path%>/fore/home/show">
        <%--<img src="../../../static/img/logo/weshare.png" alt="" width="144" height="32" class="d-inline-block align-text-top">--%>
        <img src="<%=basePath%>static/img/logo/weshare.png" alt="" width="144" height="32"
             class="d-inline-block align-text-top">
    </a>
    <%--使用toggler classes以建立适当的响应样式--%>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <%--<ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">--%>
            <li class="nav-item">
                <a class="nav-link" style="color: white" href="<%=path%>/fore/home/show">Back To Fore</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: white" href="<%=path%>/admin/home/show">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: white" href="<%=path%>/admin/user/list">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: white" href="<%=path%>/admin/order/list">Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: white" href="<%=path%>/admin/recommend-system/show">Recommend-System</a>
            </li>
        </ul>
    </div>

    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="${pageContext.request.contextPath}/admin/search">
        <input name="keyword" type="search" class="form-control form-control-dark" placeholder="Search..."
               aria-label="Search">
    </form>

    <%--<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 input-group">--%>
    <%--<input type="text" class="form-control" placeholder="Search..." />--%>
    <%--<span class="input-group-btn">--%>
    <%--<button class="btn btn-info btn-search">search</button>--%>
    <%--</span>--%>
    <%--</form>--%>

    <div class="text-end">
        <a href="<%=path%>/admin/user/logout">
            <button type="button" class="btn btn-outline-danger me-2">Logout</button>
        </a>
    </div>

    <div class="text-end invisible">
        <button type="button" class="btn btn-outline-primary me-2">Login</button>
    </div>


</nav>







