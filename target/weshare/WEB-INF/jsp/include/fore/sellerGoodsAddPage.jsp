<c:if test="${not empty requestScope.ErrMsg && !(requestScope.ErrMsg eq null) }">
    <div style="text-align: center; color: red; display: none">
            ${requestScope.ErrMsg}
    </div>
    <div style="text-align: center; color: red">
            ${requestScope.ErrMsg}
    </div>
    <%--<script> alert(${requestScope.ErrMsg});</script>--%>
</c:if>


<div class="container goodsAddForm">
    <%--enctype="multipart/form-data"  这句话是必须要有的，因为提交的表单中包含了文件--%>
    <form method="post" action="${pageContext.request.contextPath}/fore/seller/goods/add" class="registerForm" novalidate enctype="multipart/form-data">
        <%--<div style="font-family: Arial, serif; text-align: center">--%>
        <%--<h2>Create Your Account</h2>--%>
        <%--</div>--%>


        <div class="form-goods" style="text-align: center">
            <h2 class="form-goods-heading">Sell My Item</h2>
            <!-- class="sr-only"将label标签隐藏 -->

            <label for="exampleInputProductName" class="form-label"></label>
            <input name="wsGoodsName" type="text"
                   class="form-control productRowControl"
                   id="exampleInputProductName"
                   placeholder="item name"
                   value="${requestScope.goodsBO.wsGoodsName}">

            <label for="exampleInputProductPrice" class="form-label"></label>
            <input name="wsGoodsPrice" type="number"
                   class="form-control productRowControl"
                   id="exampleInputProductPrice"
                   placeholder="prize (GBP)"
                   min="0"
                   max="10000"
                   step="0.01"
                   value="${requestScope.goodsBO.wsGoodsPrice}">

            <label for="exampleInputProductStock" class="form-label"></label>
            <input name="wsGoodsAmount" type="number"
                   class="form-control productRowControl"
                   id="exampleInputProductStock"
                   placeholder="stock"
                   min="0"
                   max="10000"
                   value="${requestScope.goodsBO.wsGoodsAmount}">

            <label for="exampleInputProductCategory" class="form-label"></label>
            <select name="wsGoodsCategoryId"
                    class="form-select"
                    id="exampleInputProductCategory"
                    aria-label="select your category">
                <option selected>Select Your Category</option>
                <c:forEach items="${requestScope.categories}" var="category" varStatus="status">
                    <option value=${category.wsCategoryId}>${category.wsCategoryName}</option>
                </c:forEach>
            </select>

            <%--一定要注意textarea标签之间一定不能留有空格！！！！--%>
            <%-- ex. <textarea></textarea> --%>
            <label for="exampleInputProductDescription" class="form-label"></label>
            <textarea name="wsGoodsDescription"
                      type="text"
                      class="form-control productRowControl"
                      id="exampleInputProductDescription"
                      placeholder="description"
                      style="height: 150px">${requestScope.goodsBO.wsGoodsDescription}</textarea>
            <%--cols="2"--%>
            <%--rows="6"--%>

            <%--上传图片--%>
            <%--  accept="image/*" --> 默认过滤掉所有非图片文件：--%>
            <label for="exampleInputProductUpLoadFile" class="form-label"></label>
            <input name="wsGoodsImage" id="exampleInputProductUpLoadFile" type="file" accept="image/*" onchange="showImg(this)" class="form-control productRowControl"/>
            <img src="" alt="" id="goods_add_img">


            <%--<div class="checkbox">--%>
                <%--<label>--%>
                    <%--<!--<input type="checkbox">--%>
                    <%--记住密码-->--%>
                <%--</label>--%>
            <%--</div>--%>

            <div style="text-align: center">
                <button class="btn btn-lg btn-primary btn-block submit-button" type="submit">confirm</button>
            </div>

        </div>

    </form>
</div>

<script>
    // function showImg() {
    //     var r = new FileReader();
    //     f = document.getElementById('file').files[0];
    //
    //     r.readAsDataURL(f);
    //     r.onload = function (e) {
    //         document.getElementById('show').src = this.result;
    //     };
    // }
    function showImg(obj) {
        var file = $(obj)[0].files[0];    //获取文件信息
        var imgdata='';
        if (file) {
            document.getElementById('goods_add_img').src = "";
            var reader = new FileReader();  //调用FileReader
            reader.readAsDataURL(file); //将文件读取为 DataURL(base64)
            reader.onload = function (e) {   //读取操作完成时触发。
                // $("#img").attr('src', e.target.result)  //将img标签的src绑定为DataURL
                document.getElementById('goods_add_img').src = this.result;
            };
        } else {
            document.getElementById('goods_add_img').src = "";
            alert("please select a image!");
        }
    }
</script>
