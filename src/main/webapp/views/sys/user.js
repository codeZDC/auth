var tableIns,option;
layui.use('table', function() {
	var table = layui.table;

	// 第一个实例
	option = {
			elem : '#data_table',
			height : 400,
			url : path + '/user/list' // 数据接口
			,
			page : true // 开启分页
			,
			cols : [ [ // 表头
			{field : 'id',title : 'ID',width : 300,sort : true,fixed : 'left'}, 
			{field : 'realname',title : '用户名'}, 
			{field : 'sex',title : '性别'}, 
			{field : 'status',title : '状态'}, 
			{field : 'tel',title : '电话'}, 
			{field : 'registtime',title : '注册时间'}, 
			{field : 'birth',title : '生日'}, 
			{field : 'email',title : '邮箱'}, 
			{field : 'description',title : '描述'},
			{fixed: 'right',title : '操作', width:180, align:'center', toolbar: '#toolBar'} 
			]]
		};
	tableIns = table.render(option);
});

layui.use('form', function(){
	  var form = layui.form;
	  //监听提交
	  form.on('submit(add_submit)', function(data){
		console.log(data.field);
		$.post( path + '/user/save',data.field,function(res){
			if(res.success){
				$('.cancel').trigger('click');
				tableIns.reload(option);
			}else{
				alert('网络错误');
			}
		},'json')
		//layer.msg(JSON.stringify(data.field));
	  });
	});


$('#add_btn').click(function(){
	$('#data_div').hide();
	$('.add_div').show();
});
$('.cancel').click(function(){
	$('#data_div').show();
	$('.modal').hide();
})
$(document).on('click','.edit_btn,.view_btn,.del_btn',function(){
	layer.msg('功能升级中...');
});
