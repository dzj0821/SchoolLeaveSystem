<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<title>校园请假系统_请假申请</title>
<script src="${pageContext.request.contextPath}/js/leave/create.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<div class="container">
		<h2>请假申请</h2>
		<form name="form"
			action="${pageContext.request.contextPath}/api/leave/create"
			method="POST" enctype="multipart/form-data">
			请假开始日期： <select name="startYear">
				<c:forEach items="${years }" var="year">
					<option value=${year }>${year }</option>
				</c:forEach>
			</select> 年 <select name="startMonth">
				<c:forEach var="i" begin="${currentMonth }" end="12">
					<option value=${i }>${i }</option>
				</c:forEach>
			</select> 月 <select name="startDay">
				<c:forEach var="i" begin="${currentDay }" end="${lastDay }">
					<option value=${i }>${i }</option>
				</c:forEach>
			</select> 日 第 <select name="startLesson">
				<c:forEach var="i" begin="1" end="10">
					<option value=${i }>${i }</option>
				</c:forEach>
			</select> 节课 <br> 请假结束日期： <select name="endYear">
				<c:forEach items="${years }" var="year">
					<option value=${year }>${year }</option>
				</c:forEach>
			</select> 年 <select name="endMonth">
				<c:forEach var="i" begin="${currentMonth }" end="12">
					<option value=${i }>${i }</option>
				</c:forEach>
			</select> 月 <select name="endDay">
				<c:forEach var="i" begin="${currentDay }" end="${lastDay }">
					<option value=${i }>${i }</option>
				</c:forEach>
			</select> 日 第 <select name="endLesson">
				<c:forEach var="i" begin="1" end="10">
					<option value=${i }>${i }</option>
				</c:forEach>
			</select> 节课 <br> 请假原因：
			<textarea name="reason"></textarea>
			<br>
			<div id="images"></div>
			<button type="button" onclick="addImage()">添加图片</button>
			<a href="javascript:clearImages()">清空</a> <br> <input
				type="submit" />
		</form>
	</div>
</body>
</html>