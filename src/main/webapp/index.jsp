<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="auth" uri="/com.tuzhi.auth"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>图智信息权限系统</title>
<link rel="stylesheet" href="./static/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="static/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./static/build/css/app.css" media="all">
<style type="text/css">
	.kit-side-fold i{
		line-height: 35px;
	}
</style>
</head>

<body>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">图智信息权限系统</div>
			<div class="layui-logo kit-logo-mobile">K</div>
			<ul class="layui-nav layui-layout-left kit-nav">
				<li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
				<li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
				<li class="layui-nav-item"><a href="javascript:;" id="pay"><i
						class="fa fa-gratipay" aria-hidden="true"></i> 捐赠我</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;">邮件管理</a>
						</dd>
						<dd>
							<a href="javascript:;">消息管理</a>
						</dd>
						<dd>
							<a href="javascript:;">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right kit-nav">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="./static/images/0.jpg" class="layui-nav-img"> ${SESSION_USER.realname}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:;" onclick="modifyPassword();">修改密码</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="admin/logout"><i
						class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black kit-side">
			<div class="layui-side-scroll">
				<div class="kit-side-fold">
					<i class="fa fa-navicon" aria-hidden="true"></i>
				</div>
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar"
					kit-navbar>

					<li class="layui-nav-item"><a href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 基础功能</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'navbar.html',icon:'&#xe658;',title:'Navbar',id:'61'}"><i
									class="layui-icon">&#xe658;</i><span> 机构信息</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'tab.html',icon:'&#xe658;',title:'TAB',id:'72'}"><i
									class="layui-icon">&#xe658;</i><span> 仓库管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'tab.html',icon:'&#xe658;',title:'TAB',id:'73'}"><i
									class="layui-icon">&#xe658;</i><span> 令牌管理</span></a>
							</dd>
						</dl></li>

					<li class="layui-nav-item layui-nav-itemed"><a
						href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span>
								系统维护</span></a>
						<dl class="layui-nav-child">
							<auth:hasPermission value="/user.html">
								<dd>
									<a href="javascript:;" kit-target
										data-options="{url:'views/sys/user.html',icon:'fa-user;',title:'用户管理',id:'64'}">
										<i class="fa fa-user"></i><span> 用户管理</span>
									</a>
								</dd>
							</auth:hasPermission>
							<auth:hasPermission value="/menu.html">
								<dd>
									<a href="javascript:;" kit-target
										data-options="{url:'views/sys/menu.html',icon:'fa-paperclip',title:'菜单管理',id:'715'}">
										<i class="fa fa-paperclip"></i><span> 菜单管理</span>
									</a>
								</dd>
							</auth:hasPermission>
							<auth:hasPermission value="/role.html">
								<dd>
									<a href="javascript:;" kit-target
										data-options="{url:'views/sys/role.html',icon:'fa-paperclip',title:'角色管理',id:'76'}">
										<i class="fa fa-paperclip"></i><span> 角色管理</span>
									</a>
								</dd>
							</auth:hasPermission>
							<auth:hasPermission value="/resource.html">
								<dd>
									<a href="javascript:;" kit-target
										data-options="{url:'views/sys/resource.html',icon:'fa-paperclip',title:'资源管理',id:'722'}">
										<i class="fa fa-paperclip"></i><span> 资源管理</span>
									</a>
								</dd>
							</auth:hasPermission>
						</dl></li>

					<li class="layui-nav-item"><a href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 日志管理</span></a>
						<dl class="layui-nav-child">
							<auth:hasPermission value="/logLogin.html">
								<dd>
									<a href="javascript:;" kit-target
										data-options="{url:'views/log/loginLog.html',icon:'&#xe658;',title:'登录日志',id:'61651'}"><i
										class="layui-icon">&#xe658;</i><span> 登录日志</span></a>
								</dd>
							</auth:hasPermission>
							<auth:hasPermission value="/log.html">
								<dd>
									<a href="javascript:;" kit-target
										data-options="{url:'tab.html',icon:'&#xe658;',title:'TAB',id:'77'}"><i
										class="layui-icon">&#xe658;</i><span> 数据访问日志</span></a>
								</dd>
							</auth:hasPermission>
						</dl></li>

					<li class="layui-nav-item"><auth:hasPermission
							value="/main.html">
							<a href="javascript:;" data-url="main.html"
								data-icon="fa-paper-plane" data-title="系统更新日志" data-id='222'
								kit-target><i class="fa fa-paper-plane"></i><span>
									系统更新日志</span></a>
						</auth:hasPermission></li>
				</ul>
			</div>
		</div>
		<div class="layui-body" id="container">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">主体内容加载中...</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			2018 &copy; <a href="http://www.ituzhi.com/" target="_blank">www.tuzhi.com</a> 图智
			license

		</div>
	</div>
	<script src="static/layui/layui.js"></script>
	<script src="static/modules/common.js"></script>
	<script src="static/jquery/1.9.1/jquery.min.js"></script>
	<script src="static/modules/zUtil.js"></script>
	<script>
		var message;
		layui.config({
			base : 'static/build/js/'
		}).use([ 'app', 'message' ], function() {
			var app = layui.app, $ = layui.jquery, layer = layui.layer;
			//将message设置为全局以便子页面调用
			message = layui.message;
			//主入口
			app.set({
				type : 'iframe'
			}).init();
			$('#pay').on('click', function() {

				layer.open({
					title : false,
					type : 1,
					content : 'ffff',
					area : [ '500px', '250px' ],
					shadeClose : true
				});
			});
		});

		//手动去除没有菜单的菜单项
		$('.layui-nav-tree .layui-nav-item').each(function(per) {
			if (!$(this).find('a[kit-target]')[0])
				$(this).hide();
		})
		
		
		function modifyPassword(){
			var html =  '<form id="repassword_form" style="padding: 25px;">\
								<div class="layui-form-item">\
									<label class="layui-form-label">旧密码</label>\
									<div class="layui-input-inline">\
										<input type="text" check="旧密码" name="password" placeholder="请输入旧密码"\
											maxlength="20" autocomplete="off" class="layui-input">\
									</div>\
								</div>\
								<div class="layui-form-item">\
									<label class="layui-form-label">新密码</label>\
									<div class="layui-input-inline">\
										<input type="text" check="新密码" name="newPassword" placeholder="请输入新密码"\
											maxlength="20" autocomplete="off" class="layui-input">\
									</div>\
								</div>\
								<div class="layui-form-item">\
									<div class="layui-input-block">\
										<button type="button" class="layui-btn active" id="submit_btn_r">立即提交</button>\
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>\
									</div>\
								</div>\
						</form>'			
			layer.open({
				  title: '修改密码',			
				  type: 1, 	
				  content: html //数组第二项即吸附元素选择器或者DOM
			}); 
		}
		
		//修改密码
		$(document).on('click','#submit_btn_r',function(){
			console.log('check');
			$.ajax({
				url : path + '/user/admin/repassword',
				dataType:'json',
				type:'post',
				data : $('#repassword_form').serialize(),
				beforeSend:function(){
					$('#repassword_form').check();
					if($('#repassword_form .error')[0])
						return false;
					$('#submit_btn_r').attr('disabled','disabled');
				},
				complete:function(){
					$('#submit_btn_r').removeAttr('disabled');
				},
				success:function(res){
					if(res.success){
						layer.msg(res.msg||"操作成功",{time:1000,icon:1});
						$('.layui-layer-close').trigger('click');
					}
					else
						layer.msg(res.msg,{time:1000,icon:2})
				},
				error:function(e){
					if(e.status != 403){
						layer.msg('网络错误!',{time:1000,icon:2});
						console.log(e);
					}
				}
			})
		})
	</script>
</body>

</html>