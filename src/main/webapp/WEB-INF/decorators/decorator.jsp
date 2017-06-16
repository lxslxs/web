<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!-- css文件引入 -->
<%@include file="../layouts/top.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords"
	content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
<meta name="description"
	content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>超妍科技管理系统-<sitemesh:write property='title' /></title>
</head>
<body>
	<!-- 头部和左侧菜单以及弹出框 -->
	<%@include file="../layouts/user_title.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<!-- 左边菜单 -->
			<%@include file="../layouts/table.jsp"%>
			<!-- 皮肤选择 -->
			<%-- <%@include file="../layouts/style.jsp"%> --%>
		</div>
	</div>
	<!-- 写入body -->
	<sitemesh:write property='body'></sitemesh:write>
	<!-- js文件引入 -->
	<%@include file="../layouts/bottom.jsp"%>
</body>
</html>
