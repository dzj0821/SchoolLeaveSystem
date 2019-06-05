<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse" role="navigation">
	<!--container 导航栏容器-->
	<div class="container">
		<!--navbar-header 头部，设置项目名称或logo-->
		<div class="navbar-header ">
			<!--设置项目名称或logo-->
			<a href="${pageContext.request.contextPath}/" class="navbar-brand">校园请假系统</a>
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
				<li class="dropdown">
					<c:if test="${User != null }">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">
							请假管理
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="${pageContext.request.contextPath}/leave/create">请假申请</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/leave/list">申请记录</a>
							</li>
							<c:if test="${User.type != 'NORMAL_USER' }">
								<li role="separator" class="divider"></li>
								<li>
									<a href="${pageContext.request.contextPath}/leave/list?clazzId=${User.clazz.id }">审核名单</a>
								</li>
							</c:if>
						</ul>
					</c:if>
				</li>

				<!--<li><a href="">权限管理</a></li>-->
			</ul>
			<ul class="nav navbar-nav navbar-right ">
				<c:if test="${User == null }">
					<li>
						<a href="${pageContext.request.contextPath}/user/login"
							style="color: #9d9d9d">登录</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/user/register"
							style="color: #9d9d9d">注册</a>
					</li>
				</c:if>
				<c:if test="${User != null }">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-user"></span>
							<%-- 用户登陆成功，显示姓名--%>
							<span>${User.name }</span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a
									href="${pageContext.request.contextPath}/user/info?id=${User.id }"
									style="color: #9d9d9d">个人信息</a>
							</li>
							<li>
								<a
									href="${pageContext.request.contextPath}/user/modify"
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