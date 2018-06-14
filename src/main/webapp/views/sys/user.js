var tableIns,option,data;
layui.use('table', function() {
	var table = layui.table;
	// 第一个实例
	option = {
			elem : '#data_table',
			url : path + '/user/list' // 数据接口
			,
			page : true // 开启分页
			,
			request: {
						pageName: 'pageNum' //页码的参数名称，默认：page
						,limitName: 'pageSize' //每页数据量的参数名，默认：limit
			} ,
			cols : [ [ // 表头
			{field : 'id',title : 'ID',width : 300,sort : true,fixed : 'left'}, 
			{field : 'realname',title : '用户名'}, 
			{field : 'roleName',title : '角色名称'}, 
			{field : 'sex',title : '性别',templet:function(r){return (r.sex&&r.sex==1)?'男':'女';}}, 
			{field : 'tel',title : '电话'}, 
			{field : 'registtime',title : '注册时间'}, 
			{field : 'username',title : '账号'},
			{field : 'email',title : '邮箱'},
			{field : 'description',title : '描述'},
			{fixed: 'right',title : '操作', width:180, align:'center', toolbar: '#toolBar'} 
			]],
			done:function(res){
				data = res.data;
			}
		};
	tableIns = table.render(option);
});

var hasRoleSelect = false;//判断角色下拉框是否已经赋值
//添加按钮
$('#add_btn').click(function(){
	//我不想重置$('#add_form')[0].reset();
	$('#data_div').hide();
	$('#add_div').show();
	if(!hasRoleSelect)
		buildRoleSelect();
});

//返回按钮
$('.cancel').click(function(){
	$('#data_div').show();
	$('.modal').hide();
})
$(document).on('click','.edit_btn,.view_btn,.del_btn',function(){
	var id = $(this).parents('tr').data('id');
	if($(this).hasClass('edit_btn')){
		edit(id);
	}else if($(this).hasClass('del_btn')){
		layer.confirm('确认删除吗？', {
		  btn: ['确认', '取消']
		}, function(index, layero){
			del(id);
		});
	}else
		layer.msg('功能升级中...');
})
//添加提交按钮
$('#submit_btn_a').click(function(){
	$.ajax({
		url : path + '/user/save',
		dataType:'json',
		type:'post',
		data : $('#add_form').serialize(),
		beforeSend:function(){
			$('#add_form').check();
			if($('#add_form .error')[0])
				return false;
			$('#submit_btn_a').attr('disabled','disabled');
		},
		complete:function(){
			$('#submit_btn_a').removeAttr('disabled');
			$('.cancel').trigger('click');
			
		},
		success:function(res){
			//数据表格展示最新内容
			tableIns.reload(option);
			if(res.success)
				layer.msg(res.msg||"操作成功",{time:1000,icon:1});
			else
				layer.msg(res.msg,{time:1500,icon:2})
		},
		error:function(e){
			if(e.status != 403){
				layer.msg('网络错误!',{time:1500,icon:2});
				console.log(e);
			}
		}
	})
})
$('#submit_btn_e').click(function(){
	$.ajax({
		url : path + '/user/edit',
		dataType:'json',
		type:'post',
		data : $('#edit_form').serialize(),
		beforeSend:function(){
			$('#edit_form').check();
			if($('#edit_form .error')[0])
				return false;
			$('#submit_btn_e').attr('disabled','disabled');
		},
		complete:function(){
			$('#submit_btn_e').removeAttr('disabled');
			$('.cancel').trigger('click');
			
		},
		success:function(res){
			//数据表格展示最新内容
			tableIns.reload(option);
			if(res.success)
				layer.msg(res.msg||"操作成功",{time:1000,icon:1});
			else
				layer.msg(res.msg,{time:1500,icon:2})
		},
		error:function(e){
			if(e.status != 403){
				layer.msg('网络错误!',{time:1500,icon:2});
				console.log(e);
			}
		}
	})
})
function del(id){
	$.ajax({
		url : path + '/user/deleteById',
		dataType:'json',
		type:'post',
		data : {id:id},
		success:function(res){
			//数据表格展示最新内容
			tableIns.reload(option);
			if(res.success)
				layer.msg(res.msg||"操作成功",{time:1000,icon:1});
			else
				layer.msg(res.msg,{time:1500,icon:2})
		},
		error:function(e){
			if(e.status != 403){
				layer.msg('网络错误!',{time:1500,icon:2});
				console.log(e);
			}
		}
	})
}

//用户角色下拉框的初始化
function buildRoleSelect(){
	//获取父级菜单key,value
	$.ajax({
		url : path + '/role/admin/getRoles',
		dataType:'json',
		async:false,
		success:function(res){
			if(res.success){
				hasRoleSelect = true;
				var select = $('[name=roleId]');
				//select.find('option[new]').remove();//删除option
				res.data.forEach(function(per){
					select.append('<option new value="'+per.id+'">'+per.name+'</option>');
				})
			}
			else
				alert(res.msg);
		},
		error:function(e){
			if(e.status != 403){
				alert('网络错误!');
				console.log(e);
			}
		}
	})
}
function edit(id){
	$('#data_div').hide();
	$('#edit_div').show();
	if(!hasRoleSelect)
		buildRoleSelect();
	//给表格赋值,,
	data.forEach(function(per){
		if(per.id == id){//1赋值  2图标显示 3将自己从父级菜单中删除
			for ( var key in per) {
				$('#edit_form [name='+key+']').val(per[key])
			}
		}
	})
}