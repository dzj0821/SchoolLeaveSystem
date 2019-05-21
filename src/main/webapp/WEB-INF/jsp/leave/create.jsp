<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请</title>
</head>
<body>
<h2>请假申请</h2>
<form name="form" action="${pageContext.request.contextPath}/leave/create" method="POST">
原密码：<input id="oldPassword" type="password" name="oldPassword">
新密码：<input id="newPassword" type="password" name="newPassword">
重复新密码：<input id="newPasswordRepeat" type="password">
姓名：<input type="text" name="name" value="${user.name }">
手机号：<input type="text" name="telephone" value="${user.telephone }">
<input id="publicKey" type='hidden' name="publicKey" value="${publicKey }" />
<input type='hidden' name="timestamp" value="${timestamp }" />
<input type="submit"/>
</form>
</body>
</html>