<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>学生请假管理系统_注册</title>

    <script src="${pageContext.request.contextPath}/js/jsencrypt.min.js"></script>
    <script>
    function onSubmit(){
        var public_key = document.getElementById("publicKey").value;
        var password = document.getElementById("password").value;
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(public_key);
        var encrypted_password = encrypt.encrypt(password);
        document.getElementById("password").value = encrypted_password;
    }
</script>
    <style>
        a{
            color: black;
        }
    </style>
</head>
<body style="background-color: white">
<nav class="navbar navbar-inverse "   role="navigation">
    <!--container 导航栏容器-->

    <div class="container">
        <!--navbar-header 头部，设置项目名称或logo-->
        <div class="navbar-header" >
            <!--设置项目名称或logo-->
            <a href="#" class="navbar-brand">校园请假系统</a>
        </div>
        <!--其他导航，指定几行几个内容-->
    </div>
</nav>
<div class="container">
    <div style=" margin-top:10%;  font-size: 20px">
    <form action="" method="POST">
            <div class="form-group has-feedback form-inline  form-horizontal row">
                <span class="glyphicon glyphicon-user col-xs-3 col-md-1 col-md-offset-3" ></span><input type="text" name="username" placeholder="please input account" class="col-xs-8 col-md-5" maxlength="10">
                <span id="error_act" class="col-xs-6 col-md-4"  ></span>
            </div>
            <div class="form-group has-feedback form-inline  form-horizontal row">
                <span class="glyphicon glyphicon-lock col-xs-3 col-md-1 col-md-offset-3"></span><input type="password" name="password" id="password" placeholder="please input password" class="col-xs-8 col-md-5 " maxlength="10">
                <span id="error_psw" class="col-xs-6 col-md-4" ></span>
            </div>
            <div class="form-group has-feedback form-inline  form-horizontal row">
                <span class="glyphicon glyphicon-tag col-xs-3 col-md-1 col-md-offset-3"></span><input type="text" name="name" id="name" placeholder="please input name" class="col-xs-8 col-md-5 " maxlength="10">
                <span id="error_name" class="col-xs-6 col-md-4" ></span>
            </div>
            <div class="form-group has-feedback form-inline  form-horizontal row">
                <span class="glyphicon glyphicon-earphone col-xs-3 col-md-1 col-md-offset-3"></span><input type="text" name="telephone" id="telephone" placeholder="please input telephone" class="col-xs-8 col-md-5 " maxlength="10">
                <span id="error_tel" class="col-xs-6 col-md-4" ></span>
            </div>
            <div class="form-group has-feedback form-inline  form-horizontal row" style="margin: 0 auto; text-align: center">
                <button  type="button"  class="btn btn-default" onclick="">注册</button>
                <button  type="reset"  class="btn btn-default col-md-offset-1" >重置</button>
            </div>
            <div style="text-align: center; margin: 0 auto;font-size: 15px; padding-top: 5%;" class="row">
                <a href="javascript:void(0);" class="col-md-offset-2 col-md-4" onclick="loadLog('login.jsp')">已有账号，点击登陆...</a>
            </div>
    </form>
    </div>
</div>
<script>
    function loadLog(address)
    {
        window.location.href=address;
    }
</script>
</body>
</html>
