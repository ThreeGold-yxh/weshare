<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/7/26
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="com.yxh.weshare.bean.pojo.User" %>
<%
    User user = (User)session.getAttribute("user");
    String nickName = user.getWsUserNickname();
%>
<div style="font-family: Arial, serif; text-align: center; margin-top: 5%">
    <h2>Welcome, <%=nickName%>, please wait....</h2>
</div>


<%
    String  previouspath = (String)pageContext.getSession().getAttribute("previouspath");
    if (null == previouspath){
        previouspath = request.getContextPath() + "/fore/home/show";
    }
    response.setHeader("refresh","1;URL=" + previouspath);
%>