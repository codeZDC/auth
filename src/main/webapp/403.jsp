<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>权限错误!</title>
<style type="text/css">
	html,body{
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	.img{
		    position: relative;
			/* width: 63%; */
			height: 60%;
			background: url(${pageContext.request.contextPath}/static/images/403.png) no-repeat;
			margin-top: 50px;
			left: 50%;
			margin-left: -25%;
	}
</style>
<script>
	if(self!=top)
		top.location.href = self.location.href;
</script>
</head>
<body>
	<div class="img">
	</div>
	<center>
		<div><a href="${pageContext.request.contextPath}/index.jsp">返回主页</a></div>
		<div><a href="${pageContext.request.contextPath}/login.html">返回登录</a></div>
	</center>
</body>
</html>