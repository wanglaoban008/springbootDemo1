<%--
  Created by IntelliJ IDEA.
  User: BOSSWANG
  Date: 2018/3/3
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function changImg(){
            var img = document.getElementById("servletImg");
            var d = new Date();
            var time = d.getTime();//如果没有这个
            //下面这一句不会起作用，因为浏览器的缓存技术，图片并不会刷新
            //img.src="/myHelloWeb/servlet/ImageServlet";
            img.src="/aa/getCode?"+time;
            //?号后面的东西是通过get方式传递的
        }

    </script>
    <style type="text/css">
        .title {
        width: 200px;
        height: 200px;
        vertical-align: middle;
        display: table-cell;
        text-align: center;
    }</style>
</head>

<body>
<div class="title">这是我的图片验证码主页！</div>
<div class="title"><img id="servletImg" src="/aa/getCode" /><a href="javascript:changImg()">看不清</a></div>

</body>

</html>
