<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<div style="font-family: Arial, serif; text-align: center; margin-top: 5%">
    <h2>Successful Payment, please wait....</h2>
</div>


<%
    String contextPath = request.getContextPath();
    String homePath = contextPath + "/fore/home/show";
    response.setHeader("refresh","2;URL=" + homePath);
%>