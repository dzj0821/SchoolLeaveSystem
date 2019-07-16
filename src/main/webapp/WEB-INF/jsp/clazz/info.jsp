<%@page import="pers.dzj0821.SchoolLeaveSystem.type.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<title>班级详情</title>
<script>
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<div class="container">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${users }" var="listUser">
					<tr>
						<td>${listUser.username }</td>
						<td>${listUser.name }</td>
						<td>${listUser.telephone }</td>
						<td>
							<div class="form-group">
								<button class="btn btn-default" data-toggle="modal" data-target="#changeTypeModal">移出班级</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>