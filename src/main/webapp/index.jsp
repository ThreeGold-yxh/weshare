<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    //　　1.request.getSchema();可以返回当前页面所使用的协议，就是"http"
//　　2.request.getServerName();返回当前页面所在服务器的名字，就是上面例子中的"localhost"
//　　3.request.getServerPort();返回当前页面所在服务器的端口号，就是上面例子中的"8080"
//　　4.request.getContextPath();返回当前页面所在的应用的名字
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.sendRedirect("fore/home/show");
%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<body>--%>
<%--Hello World--%>
<%--</body>--%>
<%--</html>--%>