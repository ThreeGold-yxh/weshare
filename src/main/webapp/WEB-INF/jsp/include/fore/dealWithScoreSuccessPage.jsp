<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<div style="font-family: Arial, serif; text-align: center; margin-top: 5%">
    <h2>Thanks for your rating, please wait...</h2>
</div>


<%
    String  previouspath = (String)pageContext.getSession().getAttribute("previouspath");
    response.setHeader("refresh","1;URL=" + previouspath);
%>