<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<div style="font-family: Arial, serif; text-align: center; margin-top: 5%">
    <h2>Bye~~</h2>
</div>

<%
    String  frontHomePath = request.getContextPath() + "/fore/home/show";
    response.setHeader("refresh","1;URL=" + frontHomePath);
%>