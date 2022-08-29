<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>


<div style="font-family: Arial, serif; text-align: center; margin-top: 5%" >
    <h2>Successful Sign-Up! Welcome!</h2>
</div>

<%
    String  targetPath = pageContext.getServletContext().getContextPath() + "/fore/page/login";
    response.setHeader("refresh","1;URL=" + targetPath);
%>


