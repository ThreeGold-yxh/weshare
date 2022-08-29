<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/8/2
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%
    Integer goodsId = (Integer) request.getAttribute("goodsId");
    String appContextPath = request.getContextPath();
    String formPath = appContextPath + "/fore/goods/comment/reply/" + goodsId;
    request.setAttribute("formPath",formPath);
%>

<%--评论区--%>
<%--<div class="col-md-9 col-sm-9 col-xs-9" style="float: right ; border: 8px solid; border-color: green; height: 450px; margin-right: 10%">--%>
<div class="col-md-9 col-sm-9 col-xs-9" style="float: right; height: 450px; margin-right: 10%">
    <h3 style="color: #1a1e21; margin-left: 5px; margin-top: 5%">Comment</h3>
    <div style="visibility: hidden">
        <%--<div>x</div>--%>
        <%--<div>x</div>--%>
    </div>

    <div class="container-lg mt-5">
        <div class="card border p-2">

            <h5 class="card-header text-black  border-bottom border-info" style="background-color: white;">
                Write Your Comment Here
            </h5>

            <div class="card-body">

                <form method="post"
                      action="${pageContext.request.contextPath}/fore/goods/comment/add/${requestScope.goods.wsGoodsId}">

                    <!--            输入框-->
                    <div id="noteEdit">
                        <textarea name="comment"></textarea>
                    </div>

                    <!--提交列表-->
                    <div class="input list-inline mt-3 container-fluid">
                        <div class="row " style="text-align: center">

                            <div class="border rounded list-inline-item  col-lg-auto pr-0" style="visibility: hidden">
                                <i class="fa fa-user  mr-1" style="color: #ccc"></i>
                                <input type="text" placeholder="姓名" class="pl-2 m-0 h-100 border-0 pr-0">
                            </div>

                            <div class="border rounded list-inline-item  col-lg-auto pr-0" style="visibility: hidden">
                                <i class="fa fa-envelope  mr-1" style="color: #ccc"></i>
                                <input type="text" placeholder="邮箱" class="pl-2 h-100 border-0">
                            </div>

                            <div class="  list-inline-item align-self-center  col-lg-auto p-0 "
                                 style="visibility: hidden">
                                <div class="container-fluid mt-2 ">
                                    <label class="switch">
                                        <input type="checkbox">
                                        <div class="slider round"></div>
                                    </label>
                                </div>
                            </div>

                            <div class="  list-inline-item col-lg-auto  p-0">
                                <button type="submit" class="btn  btn-primary " style="width: 100%;height: 100%">
                                    confirm
                                </button>
                            </div>
                        </div>
                    </div>
                </form>


                <!--留言列表-->
                <div class=" container-fluid mt-5 border p-2" style="border-top: #17a2b8 solid 2px !important;">
                    <div class="p-1 comments">

                        <c:forEach items="${requestScope.rootVoList}" var="rootVo" varStatus="status">

                            <%--一个完整的评论--%>
                            <div class="container comment  m-2 p-0 mb-5" style="border-bottom: #86b7fe 2px dotted">
                                    <%--<a class="me-a avatar p-0 m-0">--%>
                                    <%--<img class="w-100 h-100 rounded-circle" src="https://s1.ax1x.com/2020/04/10/GTQcH1.png"--%>
                                    <%--alt="">--%>
                                    <%--</a>--%>

                                <div class=" ml-3 border-left">
                                    <div class="container ml-2">
                                        <a class="author" style="text-decoration: none">
                                            <span>${rootVo.wsCommentUserNickname}</span>
                                                <%--<span class="border-primary border rounded text-primary small  p-1">亿贫如洗</span>--%>
                                            <span class="summary-text small">${rootVo.wsCommentPublishDate}</span>
                                            <span id="comment_id_${rootVo.wsCommentId}_<%=goodsId%>_<%=appContextPath%>"
                                                  class="btn-primary ml-2 container m-2 reply-button"
                                                  style="width: 80px; text-align: center; cursor: pointer">reply</span>
                                        </a>
                                    </div>
                                    <div class="messageText ml-2 container m-2">${rootVo.wsCommentContent}</div>


                                        <%--<form method="post" action="${pageContext.request.contextPath}/fore/goods/comment/reply/${requestScope.goods.wsGoodsId}?commentId="><div style="width: 400px"><textarea  rows="4" cols="40" name="comment"></textarea></div><div class="  list-inline-item col-lg-auto  p-0"><button type="submit" class="btn  btn-danger " style="width: 100%;height: 100%">confirm</button></div></form>--%>

                                    <c:forEach items="${rootVo.childrenList}" var="childrenVo" varStatus="status">

                                        <!-- 评论回复-->
                                        <div class="p-1 comments m-3">
                                            <div class="container comment  m-1 p-0">
                                                    <%--<a class="me-a avatar p-0 m-0 ">--%>
                                                    <%--<img class="w-100 h-100 rounded-circle"--%>
                                                    <%--src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586518306370&di=600bf4377f924f0839d54e1c415228da&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201712%2F17%2F20171217170206_MAmwi.png"--%>
                                                    <%--alt="">--%>
                                                    <%--</a>--%>
                                                <div class=" ml-3  ">
                                                    <div class="container ml-2">
                                                        <a class="author " style="text-decoration: none">
                                                            <span>${childrenVo.wsCommentUserNickname}</span>
                                                                <%--<span class="border-primary border rounded text-primary small  p-1">机智一批</span>--%>
                                                            <span class="text-info">@${childrenVo.wsCommentParentUserNickname} </span>
                                                            <span class="summary-text small">${childrenVo.wsCommentPublishDate}</span>
                                                            <span id="comment_id_${childrenVo.wsCommentId}_<%=goodsId%>_<%=appContextPath%>"
                                                                  class="btn-primary ml-2 container m-2 reply-button"
                                                                  style="width: 80px; text-align: center; cursor: pointer">reply</span>
                                                        </a>
                                                    </div>
                                                    <div class="messageText ml-2 container m-2">${childrenVo.wsCommentContent}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                            <%--一个完整的评论--%>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


        <script type="text/javascript">
            $(function () {
                var isOpen = false;
                //页面加载完毕后开始执行的事件
                $(".reply-button").click(function () {
                    isOpen = !isOpen;

                    // var commentIdTet = event.target.id;
                    var commentIdTet = this.id;
                    var commentSplitList = commentIdTet.split("_");
                    var commentId = commentSplitList[2];
                    var goodsId = commentSplitList[3];
                    var contextPath = commentSplitList[4];

                    // alert(commentId);

                    $(".reply_textarea").remove();
                    if (isOpen) {
                        $(this).parent().parent().parent().append("<form id='reply_form' method='POST' action=''><div class='reply_textarea'><textarea name='comment' cols='40' rows='5'></textarea><br/><input type='submit' class='btn-danger' value='submit' /></div></form>");
                        <%--$(this).parent().parent().parent().append("<form method= 'post' action=\"${pageContext.request.contextPath}/fore/goods/comment/reply/${requestScope.goods.wsGoodsId}?commentId=\"><div style=\"width: 400px\"><textarea  rows=\"4\" cols=\"40\" name=\"comment\"></textarea></div><div class=\"  list-inline-item col-lg-auto  p-0\"><button type=\"submit\" class=\"btn  btn-danger \" style=\"width: 100%;height: 100%\">confirm</button></div></form>")--%>
                        document.getElementById('reply_form').action = contextPath+"/fore/goods/comment/reply/"+goodsId+"?commentId="+commentId;
                    }


                    <%--$(".reply_form").action = <%=formPath%>+"?commentId="+commentId;--%>
                    <%--document.getElementById('reply_form').action = <%=formPath%>+"?commentId="+commentId;--%>
                    // document.getElementById('reply_form').action = "/fore/goods/comment/reply/"+"?commentId="+commentId;
                    // $(this).parent().parent().parent().append("<form method='post' action=''\><div class='reply_textarea'><textarea href='' name='' cols='40' rows='5'></textarea><br/><input type='submit' class='btn-danger' value='submit' /></div></form>");
                    <%--$(this).parent().parent().parent().append("<form method= 'post' action=\"${pageContext.request.contextPath}/fore/goods/comment/reply/${requestScope.goods.wsGoodsId}?commentId=\"><div style=\"width: 400px\"><textarea  rows=\"4\" cols=\"40\" name=\"comment\"></textarea></div><div class=\"  list-inline-item col-lg-auto  p-0\"><button type=\"submit\" class=\"btn  btn-danger \" style=\"width: 100%;height: 100%\">confirm</button></div></form>")--%>
                });
            });
        </script>

    </div>
</div>
