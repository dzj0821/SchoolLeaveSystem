<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>审核</title>

</head>

<body class="gray-bg">
	<div class="container">
		<form action="${pageContext.request.contextPath}/leave/review" method="POST">
			<input id="id" name="id" type="hidden" value="${leaveInfoView.id }">
			<div class="form-group">
				<label class="col-sm-3 control-label">姓名：</label>
				<div class="col-sm-8">
					<input id="user" name="user" class="form-control" type="text"
						value="${leaveInfoView.user.name}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">班级：</label>
				<div class="col-sm-8">
					<input id="clazz" name="clazz" class="form-control" type="text"
						value="${leaveInfoView.clazzFullName}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">电话：</label>
				<div class="col-sm-8">
					<input id="telephone" name="telephone" class="form-control"
						type="text" value="${leaveInfoView.telephone}">

				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">请假时间：</label>
				<div class="col-sm-8">
					<input id="startDate" name="startDate" class="form-control"
						type="text" value="${leaveInfoView.leaveTime}">
				</div>

			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">请假理由：</label>
				<div class="col-sm-8">
					<input id="reason" name="reason" class="form-control" type="text"
						value="${leaveInfoView.reason}"> <label
						class="col-sm-3 control-label">附件：</label>
					<c:forEach items="${leaveInfoView.images }" var="image">
						<img src="${pageContext.request.contextPath}/${image.path }" />
					</c:forEach>


				</div>

			</div>


			<div class="form-group">
				<label class="col-sm-3 control-label">审批结果：</label>
				<div class="col-sm-8">
					<!-- 不通过 <input type ="checkbox" value="1" name="type">
									通过 <input type ="checkbox" value="2" name="type"> -->
					<select id="type" name="access" class="form-control">
						<option value="false">不通过</option>
						<option value="true">通过</option>
					</select>

				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8 col-sm-offset-3">
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</div>
		</form>
	</div>
</body>

</html>
