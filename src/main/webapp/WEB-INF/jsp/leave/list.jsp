<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假列表</title>
</head>
<body>
<table>
<tr>
    <th>序号</th>
    <th>请假申请者</th>
    <th>所属班级</th>
    <th>请假时间</th>
    <th>请假申请时间</th>
  </tr>
  <c:forEach items="${leaveListViews }" var="leave">
  	<tr>
  		<td>${leave.id }</td>
  		<td>${leave.user.name }</td>
  		<td>${leave.clazzFullName }</td>
  		<td>${leave.leaveTime }</td>
  		<td>${leave.createTime }</td>
  		<c:if test="${leave.type == 'WAIT' }">
  			<td><a href="#">审核</a></td>
  		</c:if>
  	</tr>
  </c:forEach>
</table>
</body>
</html>