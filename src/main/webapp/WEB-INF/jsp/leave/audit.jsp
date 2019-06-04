<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审核</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css" />
	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-table.min.js"></script>
  </head>
  
   <body class="gray-bg">
   <div class="wrapper wrapper-content ">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
					<input  type="hidden" id="qid">
						<form class="form-horizontal m-t" id="signupForm">
							<input id="id" name="id" type="hidden" >
														<div class="form-group">	
								<label class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-8">
									<input id="user" name="user" class="form-control" type="text" value="${leaves.user.name}">
								</div>
							</div>
														<div class="form-group">	
								<label class="col-sm-3 control-label">班级：</label>
								<div class="col-sm-8">
									<input id="clazz" name="clazz" class="form-control" type="text" value="${leaves.clazzFullName}">
								</div>
							</div>
														<div class="form-group">	
								<label class="col-sm-3 control-label">电话：</label>
								<div class="col-sm-8">
									<input id="telephone" name="telephone" class="form-control" type="text" value="${leaves.telephone}">
									
								</div>
							</div>
							<div class="form-group">	
								<label class="col-sm-3 control-label">请假时间：</label>
								<div class="col-sm-8">
									<input id="startDate" name="startDate" class="form-control" type="text" value="${leaves.leaveTime}">
								</div>

							</div>
						
									<div class="form-group">	
								<label class="col-sm-3 control-label">请假理由：</label>
								<div class="col-sm-8">
									<input id="reason" name="reason" class="form-control" type="text" value="${leaves.reason}">
									<label class="col-sm-3 control-label">附件：</label>
									<c:forEach items="${imgUrl}" var="url">
									<img src="${pageContext.request.contextPath}/${url } }" name="leaveImages" id="leaveImages"></c:forEach>
									
									
								</div>

							</div>
							
							
														<div class="form-group">	
								<label class="col-sm-3 control-label">审批结果：</label>
								<div class="col-sm-8">
									<!-- 不通过 <input type ="checkbox" value="1" name="type">
									通过 <input type ="checkbox" value="2" name="type"> -->
									<select id="type" name="type" class="form-control">
										<option value="1">不通过</option>
										<option value="2">通过</option>
									</select> 
									
								</div>
							</div>
												
										
													
																					<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button type="submit" class="btn btn-primary" onclick="update()">提交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			</div>
			</div>
  </body>

</html>
