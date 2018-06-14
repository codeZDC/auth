var tableIns,option,data;

layui.use('table', function() {
	var table = layui.table;
	option = {
			elem : '#data_table',
			//height : 400,
			url : path + '/resource/list' // 数据接口
			,
			page : true // 开启分页
			,
			loading:true
				  ,even: true //开启隔行背景
				  ,size: 'sm' //小尺寸的表格
					 ,
			request: {
				  pageName: 'pageNum' //页码的参数名称，默认：page
				  ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
				} ,
			cols : [ [ // 表头
				{field : 'id',title : 'ID',sort : true, align:'center'}, 
				{field : 'name',title : '资源名称',sort : true}, 
				{field : 'url',title : '路径',sort : true}, 
				{field : 'isMenuUrl',title : '菜单属性',templet:
					function(r){return r.isMenuUrl == 1?'<button type="button" class="layui-btn layui-btn-warm layui-btn-xs">菜单</button>':'按钮' }
					, align:'center'}, 
				{field : 'pmenuName',title : '所属菜单', align:'center',sort : true}, 
				{field : 'orderBy',title : '排序号', align:'center'}, 
				{fixed: 'right',title : '操作', width:180, align:'center', toolbar: '#toolBar'} 
				]],
			done:function(res){
				data = res.data;
			}
		};
	tableIns = table.render(option);
});

var selective;//控制是否请求获取下拉框,只想获取一次
//添加按钮
$('#add_btn').click(function(){
	//我不想重置$('#add_form')[0].reset();
	$('#data_div').hide();
	$('#add_div').show();
	if(!selective)
		buildPmenuSelect();
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
//构建所属父级菜单下拉框(二级菜单)
function buildPmenuSelect(){
	//获取父级菜单key,value
	$.ajax({
		url : path + '/menu/admin/getSecondMenus',
		dataType:'json',
		async:false,
		success:function(res){
			if(res.success){
				selective = true;
				var select = $('[name=menuId]');
				//select.find('option[new]').remove();//删除option,当前页面没有需要删除的,,因为只需要获取一下下拉列表
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
		buildPmenuSelect();
	//给表格赋值,,
	data.forEach(function(per){
		if(per.id == id){//1赋值 
			for ( var key in per) {
				if('isMenuUrl' != key )
					$('#edit_form [name='+key+']').val(per[key]);
				else{
					$('#edit_form [name=isMenuUrl][value='+per.isMenuUrl+']')
					.next().trigger('click');
				}
			}
		}
	})
}
//添加提交按钮
$('#submit_btn_a').click(function(){
	$.ajax({
		url : path + '/resource/save',
		dataType:'json',
		type:'post',
		data : $('#add_form').serialize(),
		beforeSend:function(){
			console.log('start to check...');
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
		url : path + '/resource/edit',
		dataType:'json',
		type:'post',
		data : $('#edit_form').serialize(),
		beforeSend:function(){
			console.log('start to check...');
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
		url : path + '/resource/deleteById',
		dataType:'json',
		type:'post',
		data : {id:id},
		complete:function(){
		},
		success:function(res){
			//数据表格展示最新内容
			tableIns.reload(option);
			if(res.success)
				layer.msg(res.msg||"操作成功",{time:1000,icon:1});
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

//搜索按钮
$('.filter').change(function(){
	//获取搜索参数
	var filter = {};
	filter[this.name] = this.value.replace(/(^\s+)|(\s+$)/g, '');
	option.where = filter;
	tableIns.reload(option);
});