var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: null
			}
		},
		check: {
			enable: true,
			chkStyle: "checkbox"
		}
	};
/* 
 * for(var i in zNodes){ zNodes[i].icon ="di.png"; }
 */
var roleId ;
function setMenuTree(id){

	roleId = id ;
	var menuTree;
	$.ajax({
		url : contextPath + '/role/admin/getRoleResources',
		type:'get',
		dataType : 'json',
		async : false,
		data : {rid:id},
		success:function(res){
			if(res.success){
				menuTree = res.data ;
				
				//隐藏data_div 显示tree_div
				$('#data_div').hide();
				$('#tree_div').show();
			}
			else 
				layer.msg(res.msg,{icon:2});
		},
		error :function(){
			layer.msg('网络错误,请联系管理员!',{icon:2});
		}
	});
	/*if(!menuTree||!menuTree.length){
		layer.msg('没有菜单可以分配!');
		return;
	}*/
	$.fn.zTree.init($("#menuTree"), setting, menuTree);
}
function submitTree(){
	
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = [] ;
	for ( var node of nodes) {
		if(!node.isParent){
			if(node.id<9999){
				layer.msg(node.name + '  没有配置资源路径');
				return false;
			}
			else
				ids.push(node.id);
		}
	}
	if(!roleId)
		return;
	$.post(contextPath + '/role/setRoleResources',{ids:ids,rid:roleId},function(res){
		if(res.success){
			layer.msg('授权成功',{icon:1});
		}else{
			layer.msg('授权失败',{icon:2});
		}
		$('.cancel').trigger('click');
	},'json');
}