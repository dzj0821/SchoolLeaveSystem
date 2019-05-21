<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看个人资料</title>
</head>
<body>
<h2>查看个人资料</h2>
<p>用户名：${userInfoView.username }</p>
<p>用户类型：${userInfoView.type.name }</p>
<p>姓名：${userInfoView.name }</p>
<p>电话：${userInfoView.telephone }</p>
<p>班级：${userInfoView.clazzFullName }</p>
</body>
</html>