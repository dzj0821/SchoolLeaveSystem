<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'batchRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  <h2>批量注册</h2>
  <form name="batchRegister" action="${pageContext.request.contextPath}/api/user/batchRegister" onsubmit="onSubmit()" method="post">
  	<h3>学号</h3>
  	<textarea rows="11" cols="15" name="username" id="username">
  	
  	</textarea>
  	初始密码：
  	<input type="text" name="password" id="password">
  	<input id="publicKey" type='hidden' name="publicKey" value="${publicKey }" />
	<input type='hidden' name="timestamp" value="${timestamp }" />
	    <input type="submit" value="提交">
  
  
  </form>
	    
  </body>
  
</html>
