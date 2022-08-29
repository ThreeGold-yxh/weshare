

<div style="font-family: Arial, serif; text-align: center; margin-top: 5%">
    <h2>Successful edit your item! Please wait...</h2>
</div>


<%
    String  previouspath = (String)pageContext.getSession().getAttribute("previouspath");
    response.setHeader("refresh","1;URL=" + previouspath);
%>