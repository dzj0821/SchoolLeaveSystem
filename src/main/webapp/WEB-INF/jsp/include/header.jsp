<%@page import="pers.dzj0821.SchoolLeaveSystem.type.UserType"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse" role="navigation">
	<!--container 导航栏容器-->
	<div class="container">
		<!--navbar-header 头部，设置项目名称或logo-->
		<div class="navbar-header ">
			<!--设置项目名称或logo-->
			<a href="${pageContext.request.contextPath}/" class="navbar-brand">大学请假管理系统</a>
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#order">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar button_span"></span>
				<span class="icon-bar button_span"></span>
				<span class="icon-bar button_span"></span>
			</button>
		</div>
		<!--其他导航，指定几行几个内容-->
		<div class="navbar-collapse collapse" id="order" aria-expanded="false">
			<ul class="nav navbar-nav">
				<c:if test="${user != null }">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">
							请假管理
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<c:if test="${user.type.code == UserType.NORMAL_USER.code }">
								<li>
									<a href="${pageContext.request.contextPath}/leave/create">请假申请</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/leave/list">申请记录</a>
								</li>
							</c:if>
							<c:if test="${user.type.code < UserType.NORMAL_USER.code }">
								<li>
									<a
										href="${pageContext.request.contextPath}/leave/list">审核名单</a>
								</li>
							</c:if>
						</ul>
					</li>
				</c:if>
				<c:if test="${user.type.code <= UserType.SUPER_ADMIN.code }">
					<li>
						<a href="${pageContext.request.contextPath}/grade/list">年级管理</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/collage/list">学院管理</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/user/list">用户管理</a>
					</li>
				</c:if>
				<c:if test="${user.type.code <= UserType.COLLAGE_ADMIN.code }">
					<li>
						<a href="${pageContext.request.contextPath}/major/list">专业管理</a>
					</li>
				</c:if>
				<c:if test="${user.type.code <= UserType.CLAZZ_ADMIN.code }">
					<li>
						<a href="${pageContext.request.contextPath}/clazz/list">班级管理</a>
					</li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right ">
				<c:if test="${user == null }">
					<li>
						<a href="${pageContext.request.contextPath}/user/login"
							style="color: #9d9d9d">登录</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/user/register"
							style="color: #9d9d9d">注册</a>
					</li>
				</c:if>
				<c:if test="${user != null }">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-user"></span>
							<%-- 用户登陆成功，显示姓名--%>
							<span>${user.name }</span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a
									href="${pageContext.request.contextPath}/user/info?id=${user.id }"
									style="color: #9d9d9d">个人信息</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/modify"
									style="color: #9d9d9d">修改个人资料</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/logout"
									style="color: #9d9d9d">登出</a>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>