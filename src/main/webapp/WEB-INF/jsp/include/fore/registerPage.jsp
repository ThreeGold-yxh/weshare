<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<c:if test="${not empty requestScope.ErrMsg && !(requestScope.ErrMsg eq null) }">
    <div style="text-align: center; color: red; display: none">
            ${requestScope.ErrMsg}
    </div>
    <div style="text-align: center; color: red">
            ${requestScope.ErrMsg}
    </div>
    <%--<script> alert(${requestScope.ErrMsg});</script>--%>
</c:if>

<c:if test="${not empty requestScope.SuccessMsg && !(requestScope.SuccessMsg eq null) }">
    <div style="text-align: center; color: greenyellow; display: none">
            ${requestScope.SuccessMsg}
    </div>
    <div style="text-align: center; color: greenyellow">
            ${requestScope.SuccessMsg}
    </div>
    <%--<script> alert(${requestScope.SuccessMsg});</script>--%>
</c:if>


<%--<c:if test="${(param.ErrMsg == 'error') }">--%>
<%--<script> alert("失败");</script>--%>
<%--</c:if>--%>

<% //todo 数据回显 还没有做 -----成功解决!%>
<%--<div class="feedmessage">--%>

<%--</div>--%>

<div class="container registerForm">
    <form method="post" action="${pageContext.request.contextPath}/fore/user/register" class="registerForm" novalidate>
        <%--<div style="font-family: Arial, serif; text-align: center">--%>
        <%--<h2>Create Your Account</h2>--%>
        <%--</div>--%>
        <div class="form-signin" style="text-align: center">
            <h2 class="form-signin-heading">Create Your Account</h2>
            <!-- class="sr-only"将label标签隐藏 -->
            <label for="exampleInputUsername" class="sr-only"></label>
            <input name="wsUserNickname" type="text" class="form-control registorRowControl"
                   id="exampleInputUsername"
                   placeholder="nickname"
                   value="${requestScope.userBO.wsUserNickname}">
            <label for="exampleInputUsername" class="sr-only"></label>
            <input name="wsUserAccount" type="text" class="form-control registorRowControl" id="exampleInputAccount"
                   placeholder="account"
                   value="${requestScope.userBO.wsUserAccount}">
            <label for="exampleInputUsername" class="sr-only"></label>
            <input name="wsUserPassword" type="password" class="form-control registorRowControl"
                   id="exampleInputPassword"
                   placeholder="password">
            <label for="exampleInputUsername" class="sr-only"></label>
            <input name="wsUserRepeatPassword" type="password" class="form-control registorRowControl"
                   id="exampleInputRepeatPassword"
                   placeholder="repeat password">
            <label for="exampleInputUsername" class="sr-only"></label>
            <input name="wsUserEmail" type="email" class="form-control registorRowControl" id="exampleInputEmail"
                   placeholder="email"
                   value="${requestScope.userBO.wsUserEmail}">
            <label for="exampleInputUsername" class="sr-only"></label>
            <input name="wsUserAddress" type="text" class="form-control registorRowControl" id="exampleInputAddress"
                   placeholder="address"
                   value="${requestScope.userBO.wsUserAddress}">

            <div class="checkbox">
                <label>
                    <!--<input type="checkbox">
                    记住密码-->
                </label>
            </div>
            <div style="text-align: center">
                <button class="btn btn-lg btn-primary btn-block " type="submit">sign up</button>
            </div>
        </div>
    </form>
</div>