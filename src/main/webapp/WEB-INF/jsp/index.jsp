<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>校园请假系统_首页</title>
<style>
.button_span {
	border: none;
}
</style>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script>
    $(window).scroll(function () {
        //小屏幕下的导航条折叠
        if ($(window).width() < 768) {
            //点击导航链接之后，把导航选项折叠起来
            $("#order").onclick(function () {
                $("#order").collapse('hide');
            });
            //滚动屏幕时，把导航选项折叠起来
            $(window).scroll(function () {
                $("#order").collapse('hide');
            });
        }
    });
    </script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<div id="carousel-example-generic" class="carousel slide container"
		data-ride="carousel">
		<!-- Indicators -->
		<%--  创建容器 --%>
		<ol class="carousel-indicators">
			<%-- 图片序号 --%>
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="images/jpg3.jpg" alt="">
				<div class="carousel-caption">
					<h3>文字描述xxx</h3>
				</div>
			</div>
			<div class="item">
				<img src="images/jpg2.jpg" alt="">
				<div class="carousel-caption">
					<h3>文字描述xxx</h3>
				</div>
			</div>
			<div class="item">
				<img src="images/jpg3.jpg" alt="">
				<div class="carousel-caption">
					<h3>文字描述xxx</h3>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
</body>
</html>