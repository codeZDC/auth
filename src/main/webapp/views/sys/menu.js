var tableIns,option,data;

layui.use('table', function() {
	var table = layui.table;
	option = {
			elem : '#data_table',
			url : path + '/menu/list' // 数据接口
			,
			page : true // 开启分页
			,
			
			cols : [ [ // 表头
			{field : 'id',title : 'ID',sort : true}, 
			{field : 'name',title : '菜单名称',sort : true}, 
			{field : 'ico',title : '图标'}, 
			{field : 'pname',title : '父菜单',sort : true}, 
			{field : 'orderBy',title : '排序号'}, 
			{field : 'description',title : '描述'}, 
			{fixed: 'right',title : '操作', width:180, align:'center', toolbar: '#toolBar'} 
			]],
			done:function(res){
				data = res.data;
			}
		};
	tableIns = table.render(option);
});


/*layui.use('form', function(){
	  var form = layui.form;
});*/

var selective;
//添加按钮
$('#add_btn').click(function(){
	//我不想重置$('#add_form')[0].reset();
	$('#data_div').hide();
	$('#add_div').show();
	if(!selective)
		buildMenuSelect();
});

//返回按钮
$('.cancel').click(function(){
	$('#data_div').show();
	$('.modal').hide();
	//编辑的时候将自己option隐藏了,现在显示一下
	$('option').show();
})
$(document).on('click','.edit_btn,.view_btn,.del_btn',function(){
	var id = $(this).parents('tr').data('id');
	if($(this).hasClass('edit_btn')){
		edit(id);
	}else if($(this).hasClass('del_btn')){
		del(id);
	}else
		layer.msg('功能升级中...');
})
$(document).on('click','.icon-span',function(){
	var ico = $(this).find('i').attr('class');
	console.log(ico);
	$('[name=ico]').val(ico);
	$('.show-icon').html($(this).find('i').clone());
})
//选择图标
$('.chooseIco').click(function(){
	var div = '<div class="icon-div">\
		<span class="layui-layer-close icon-span"><i class="fa fa-plug" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-flag" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-cog" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-circle" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-camera" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-calendar" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-comment" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-envelope" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-folder-open" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-envelope" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-phone-square" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-shopping-basket" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-send" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-server" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-tags" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-users" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-trash" aria-hidden="true"></i></span>\
		<span class="layui-layer-close icon-span"><i class="fa fa-truck" aria-hidden="true"></i></span>\
		<div>';
	layer.open({
	  type: 1,
	  anim :4,
	  title : '图标列表',
	  content: div,
	  area: ['500px', '300px']
	});
})
//构建父级菜单下拉框
function buildMenuSelect(){
	//获取父级菜单key,value
	$.ajax({
		url : path + '/menu/admin/getMenusByPid?pid=0',
		dataType:'json',
		async:false,
		success:function(res){
			if(res.success){
				console.log(res);
				selective = true;
				var select = $('[name=pid]');
				select.find('option[new]').remove();//删除option
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
	if(!selective)
		buildMenuSelect();
	//给表格赋值,,
	data.forEach(function(per){
		if(per.id == id){//1赋值  2图标显示 3将自己从父级菜单中删除
			for ( var key in per) {
				$('#edit_form [name='+key+']').val(per[key])
			}
			$('.show-icon').html('<i class="'+per.ico+'" aria-hidden="true"></i>');
			$('option[value="'+id+'"]').hide();
		}
	})
}
//添加提交按钮
$('#submit_btn_a').click(function(){
	$.ajax({
		url : path + '/menu/save',
		dataType:'json',
		type:'post',
		data : $('#add_form').zdata(),
		beforeSend:function(){
			console.log('start to check...');
			$('#add_form').check();
			if($('#add_form .error')[0])
				return false;
			$('#submit_btn_a').attr('disabled','disabled');
		},
		complete:function(){
			$('#submit_btn_a').removeAttr('disabled');
			//数据表格展示最新内容
			tableIns.reload(option);
			$('.cancel').trigger('click');
			
			selective = false ; 
		},
		success:function(res){
			if(res.success)
				layer.msg(res.msg,{time:1000,icon:1});
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
$('#submit_btn_e').click(function(){
	$.ajax({
		url : path + '/menu/edit',
		dataType:'json',
		type:'post',
		data : $('#edit_form').zdata(),
		beforeSend:function(){
			console.log('start to check...');
			$('#edit_form').check();
			if($('#edit_form .error')[0])
				return false;
			$('#submit_btn_e').attr('disabled','disabled');
		},
		complete:function(){
			$('#submit_btn_e').removeAttr('disabled');
			//数据表格展示最新内容
			tableIns.reload(option);
			$('.cancel').trigger('click');
			
			selective = false ; 
		},
		success:function(res){
			if(res.success)
				layer.msg(res.msg,{time:1000,icon:1});
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
function del(id){
	$.ajax({
		url : path + '/menu/deleteById',
		dataType:'json',
		type:'post',
		data : {id:id},
		beforeSend:function(){
			if(!confirm('是否删除!'))
				return false;
		},
		complete:function(){
			//数据表格展示最新内容
			tableIns.reload(option);
			selective = false ; 
		},
		success:function(res){
			if(res.success)
				layer.msg(res.msg,{time:1000,icon:1});
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
}