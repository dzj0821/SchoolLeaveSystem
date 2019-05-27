<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请</title>
<script type="text/javascript">
var image_count = 0;
var max_image_count = 5;
function addImage(){
	if(image_count >= max_image_count){
		alert("最多只允许上传" + max_image_count + "张图片！");
		return;
	}
	image_count++;
	var line = document.createElement("div");
	var p = document.createElement("span");
	p.innerText = "图片" + image_count + "：";
	var input = document.createElement("input");
	input.type = "file";
	input.name = "image";
	line.append(p);
	line.append(input);
	document.getElementById("images").append(line);
}

function clearImages(){
	document.getElementById("images").innerHTML = "";
	image_count = 0;
}
</script>
</head>
<body>
<h2>请假申请</h2>
<form name="form" action="${pageContext.request.contextPath}/api/leave/create" method="POST" enctype="multipart/form-data">
请假开始日期：
<select name="startYear">
<c:forEach items="${years }" var="year">
<option value=${year }>${year }</option>
</c:forEach>
</select>
年
<select name="startMonth">
<c:forEach var="i" begin="${currentMonth }" end="12">
<option value=${i }>${i }</option>
</c:forEach>
</select>
月
<select name="startDay">
<c:forEach var="i" begin="${currentDay }" end="${lastDay }">
<option value=${i }>${i }</option>
</c:forEach>
</select>
日
第
<select name="startLesson">
<c:forEach var="i" begin="1" end="10">
<option value=${i }>${i }</option>
</c:forEach>
</select>
节课
<br>
请假结束日期：
<select name="endYear">
<c:forEach items="${years }" var="year">
<option value=${year }>${year }</option>
</c:forEach>
</select>
年
<select name="endMonth">
<c:forEach var="i" begin="${currentMonth }" end="12">
<option value=${i }>${i }</option>
</c:forEach>
</select>
月
<select name="endDay">
<c:forEach var="i" begin="${currentDay }" end="${lastDay }">
<option value=${i }>${i }</option>
</c:forEach>
</select>
日
第
<select name="endLesson">
<c:forEach var="i" begin="1" end="10">
<option value=${i }>${i }</option>
</c:forEach>
</select>
节课
<br>
请假原因：<textarea name="reason"></textarea>
<br>
<div id="images">
</div>
<button type="button" onclick="addImage()">添加图片</button>
<a href="javascript:clearImages()">清空</a>
<br>
<input type="submit"/>
</form>
</body>
</html>