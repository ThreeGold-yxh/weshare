<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/7/26
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
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

<% //todo 数据回显 还没有做%>
<div class="feedmessage">

</div>

<div class="container loginForm">
    <form method="post" action="${pageContext.request.contextPath}/fore/user/login" class="loginForm" novalidate>
        <%--<div style="font-family: Arial, serif; text-align: center">--%>
        <%--<h2>Create Your Account</h2>--%>
        <%--</div>--%>
        <div class="form-signin" style="text-align: center">
            <h2 class="form-signin-heading">Please Login</h2>
            <!-- class="sr-only"将label标签隐藏 -->
            <label for="exampleInputAccount" class="sr-only"></label>
            <input name="wsUserAccount" type="text" class="form-control loginRowControl"
                   id="exampleInputAccount"
                   placeholder="account">
            <label for="exampleInputAccount" class="sr-only"></label>
            <input name="wsUserPassword" type="password" class="form-control loginRowControl" id="exampleInputPassword"
                   placeholder="password">
            <div class="checkbox">
                <label>
                    <!--<input type="checkbox">
                    记住密码-->
                </label>
            </div>
            <div style="text-align: center">
                <button class="btn btn-lg btn-primary btn-block " type="submit">login</button>
            </div>
        </div>
    </form>
</div>

<div style="display: inline-block">

</div>

<div class="container" style="margin-left: 14%">
    <div class="row">
        <div class="col-4">
            <div style="margin-left: 50%">
                Role: "admin"
            </div>
            <div style="margin-left: 50%">
                Role: "supporter"
            </div>
            <div style="margin-left: 50%">
                Role: "buyer and seller"
            </div>
            <div style="margin-left: 50%">
                Role: "buyer"
            </div>
        </div>
        <div class="col-3">
            <div style="margin-left: 10%">
                Account: "admin_tester"
            </div>
            <div style="margin-left: 10%">
                Account: "staff"
            </div>
            <div style="margin-left: 10%">
                Account: "test"
            </div>
            <div style="margin-left: 10%">
                Account: "Lily"
            </div>
        </div>
        <div class="col-3">
            <div>
                Password: "admin_tester"
            </div>
            <div>
                Password: "123"
            </div>
            <div>
                Password: "123"
            </div>
            <div>
                Password: "123"
            </div>
        </div>

    </div>
</div>



