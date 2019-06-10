<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<title>请假列表</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<div class="container">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<th>序号</th>
					<th>请假申请者</th>
					<th>所属班级</th>
					<th>请假时间</th>
					<th>请假申请时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${leaveListViews }" var="leave">
					<c:if test="${leave.type == 'WAIT' }">
						<tr class="active">
					</c:if>
					<c:if test="${leave.type == 'CANCEL' }">
						<tr class="warning">
					</c:if>
					<c:if test="${leave.type == 'PASS' }">
						<tr class="success">
					</c:if>
					<c:if test="${leave.type == 'NOT_PASS' }">
						<tr class="danger">
					</c:if>
					<td><a href="info?id=${leave.id }">${leave.id }</a></td>
					<td>${leave.user.name }</td>
					<td>${leave.clazzFullName }</td>
					<td>${leave.leaveTime }</td>
					<td>${leave.createTime }</td>
					<td>
						<c:if test="${leave.type == 'WAIT' }">
						待审核
					</c:if>
						<c:if test="${leave.type == 'CANCEL' }">
						已取消
					</c:if>
						<c:if test="${leave.type == 'PASS' }">
							审核通过
						</c:if>
						<c:if test="${leave.type == 'NOT_PASS' }">
							审核未通过
						</c:if>
					</td>
					<td>
						<c:if
							test="${leave.type == 'WAIT' && user.type != 'NORMAL_USER' }">
							<a
								href="${pageContext.request.contextPath}/leave/info?id=${leave.id }">审核</a>
						</c:if>
						<c:if
							test="${leave.type == 'WAIT' && user.type == 'NORMAL_USER' }">
							<a
								href="${pageContext.request.contextPath}/leave/cancel?id=${leave.id }">取消申请</a>
						</c:if>
					</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>