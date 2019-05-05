<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<body>
<h2>注册</h2>
<form name="form" action="${pageContext.request.contextPath}/api/user/register" onsubmit="onSubmit()">
用户名：<input type="text" name="username"/>
密码：<input id="password" type="password" name="password">
姓名：<input type="text" name="name">
手机号：<input type="text" name="telephone">
<input id="publicKey" type='hidden' name="publicKey" value="${publicKey }" />
<input type='hidden' name="timestamp" value="${timestamp }" />
<input type="submit"/>
</form>
</body>
</html>