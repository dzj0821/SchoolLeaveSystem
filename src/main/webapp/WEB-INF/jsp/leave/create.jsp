<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<title>请假申请</title>
<script src="${pageContext.request.contextPath}/js/leave/create.js" charset="UTF-8" ></script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<div class="container" style="font-weight: 2	">
		<h2>请假申请</h2>
		<form name="form"
			action="${pageContext.request.contextPath}/leave/create"
			method="POST" enctype="multipart/form-data">	
		 <!--请假日期模块  -->
		 <div class="container">
		    <!-- 开始日期 -->
		    <div class="row">
		      <div class="col-xs-12 col-md-10 col-md-offset-2">
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
			</select> 节课
		      </div>
		    </div>
		      <!-- 结束日期  -->
		      <div class="row">	
		     <div class="col-xs-12 col-md-10 col-md-offset-2">
		     请假结束日期： <select name="endYear">
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
			</select> 节课 
		     </div>
			</div>
		 </div>
		 <div class="container">
		    <div class="row" style="padding-top: 20px;">
		    <div class="col-xs-12 col-md-10 col-md-offset-2">
			 请假原因：<br>
			<textarea class="form-control" rows="3" name="reason"></textarea>
			</div>
			</div>
			<div class="row">
			<div id="images" class=" col-xs-12 col-md-10 col-md-offset-2" style="padding-top:20px; padding-bottom: 20px;"></div>
			<div class="row" style="padding-top: 20px;">
			<div class="col-xs-12 col-md-10 col-md-offset-2">
			<button type="button" onclick="addImage()" class="btn btn-default active"><span class="glyphicon glyphicon-picture"> &nbsp;添加图片</span></button>
			<a href="javascript:clearImages()">清空</a> <br>
			 <button type="submit" class="btn btn-default active"   style="margin-top: 10px;"><span class="glyphicon glyphicon-open">&nbsp;&nbsp;&nbsp;&nbsp;提交</span></button>
			 </div>
			</div>
		</div>
		</div>
		</form>
	</div>
</body>
</html>