var tableIns,option,data;

layui.use('table', function() {
	var table = layui.table;
	option = {
			elem : '#data_table',
			url : path + '/role/list' // 数据接口
			,
			page : true // 开启分页
			,
			loading:true
			,even: true //开启隔行背景
			,
			request: {
				  pageName: 'pageNum' //页码的参数名称，默认：page
				  ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
				} ,
			cols : [ [ // 表头
				{title : '序号',sort : true, align:'center',templet:function(r){
					return r.LAY_INDEX;
				}}, 
				{field : 'name',title : '角色名称', align:'center'}, 
				{field : 'type',title : '类型', align:'center'},
				{title : '授权',align:'center',templet:function(r){
					return '<button type="button" onclick="setMenuTree(\''+r.id+'\');" class="layui-btn layui-btn-sm layui-btn-normal">点击授权</button>';
				}},
				{field : 'description',title : '描述', align:'center'}, 
				{fixed: 'right',title : '操作', width:180, align:'center', toolbar: '#toolBar'} 
				]],
			done:function(res){
				data = res.data;
			}
		};
	tableIns = table.render(option);
});

//添加按钮
$('#add_btn').click(function(){
	//我不想重置$('#add_form')[0].reset();
	$('#data_div').hide();
	$('#add_div').show();
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
function edit(id){
	$('#data_div').hide();
	$('#edit_div').show();

	//给表格赋值,,
	data.forEach(function(per){
		if(per.id == id){//1赋值 
			for ( var key in per) {
					$('#edit_form [name='+key+']').val(per[key]);
			}
		}
	})
}
//添加提交按钮
$('#submit_btn_a').click(function(){
	$.ajax({
		url : path + '/role/save',
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
		url : path + '/role/edit',
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
		url : path + '/role/deleteById',
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