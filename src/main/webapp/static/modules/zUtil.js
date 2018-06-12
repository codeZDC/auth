(function($) { //虽然我不知道这句话的真正用处,但是我还是装逼写上了

	/**
	 * 		表单校验,只需要添加一个check属性即可,属性值(选填)为 "field-type" 
	 * 		推荐使用正则表达式
	 */
	$.prototype.check = function() {
		var _this = this;
		_this.find(':input[check]').each(function() {
			var arr = $(this).attr('check').split("-");
			var field = arr[0],
				type = arr[1];
			//首先判断非空,然后针对arr中[1]进行识别,然后判断字段的校验属性
			if(/^\s*$/.test(this.value)) {
				//$(this).addClass('error').attr('msg', (field || '必填项') + ' 不能为空或空字符串!');
				$(this).addClass('error').focus();
				layer.msg((field || '必填项') + ' 不能为空或空字符串!',{time:1000});
			} else if(type == 'tinyint' && !(/^\d+$/.test(this.value) && this.value > 0 && this.value < 126)) {
				//$(this).addClass('error').attr('msg', (field || '该数字项') + ' 只能是1-125之间的值!')
				$(this).addClass('error').focus();
				layer.msg((field || '该数字项') + ' 只能是1-125之间的整数!',{time:1000});
			} else if(type == 'int' && !/^\d{1,8}$/.test(this.value)) {
				//$(this).addClass('error').attr('msg', (field || '该数字项') + ' 只能是大于0的合理整数!')
				$(this).addClass('error').focus();
				layer.msg((field || '该数字项') + ' 只能是大于0的合理整数!',{time:1000});
			} else if(type == 'xie' && !/^\//.test(this.value)) {
				$(this).addClass('error').focus();
				layer.msg(('路径地址') + ' 必须以" / "开头!',{time:1000});
			}else {
				$(this).removeClass('error');
				return true;
			}
			return false;
		})
	}

	/**
	 *  针对form中的空字符串项表单项进行过滤,没必要传到后台
	 */
	$.prototype.zdata = function() {
		var _this = this;
		var zJson = {};
		_this.find(':input').each(function() {
			if($(this).is(':radio')){//单选框
				if($(this).is(':checked'))
					zJson[this.name] = this.value ;
				return true;
			}
			if($(this).is(':checkbox')){//复选框
				if($(this).is(':checked'))
					zJson[this.name] = this.value ;
				return true;
			}
			if(!/^\s*$/.test(this.value))
				zJson[this.name] = this.value.replace(/(^\s+)|(\s+$)/g, '');
		})
		return zJson;
	}
})(jQuery)

/**
 * 正则表达式范例
 * 	验证数字的正则表达式集 
		验证数字：^[0-9]*$ 
		验证n位的数字：^\d{n}$ 
		验证至少n位数字：^\d{n,}$ 
		验证m-n位的数字：^\d{m,n}$ 
		验证零和非零开头的数字：^(0|[1-9][0-9]*)$ 
		验证有两位小数的正实数：^[0-9]+(.[0-9]{2})?$ 
		验证有1-3位小数的正实数：^[0-9]+(.[0-9]{1,3})?$ 
		验证非零的正整数：^\+?[1-9][0-9]*$ 
		验证非零的负整数：^\-[1-9][0-9]*$ 
		验证非负整数（正整数 + 0） ^\d+$ 
		验证非正整数（负整数 + 0） ^((-\d+)|(0+))$ 
		验证长度为3的字符：^.{3}$ 
		验证由26个英文字母组成的字符串：^[A-Za-z]+$ 
		验证由26个大写英文字母组成的字符串：^[A-Z]+$ 
		验证由26个小写英文字母组成的字符串：^[a-z]+$ 
		验证由数字和26个英文字母组成的字符串：^[A-Za-z0-9]+$ 
		验证由数字、26个英文字母或者下划线组成的字符串：^\w+$ 
		验证用户密码:^[a-zA-Z]\w{5,17}$ 正确格式为：以字母开头，长度在6-18之间，只能包含字符、数字和下划线。 
		验证是否含有 ^%&',;=?$\" 等字符：[^%&',;=?$\x22]+ 
		验证汉字：^[\u4e00-\u9fa5],{0,}$ 
		验证Email地址：/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
		验证InternetURL：^http://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$ ；^[a-zA-z]+://(w+(-w+)*)(.(w+(-w+)*))*(?S*)?$ 
		验证电话号码：^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$：--正确格式为：XXXX-XXXXXXX，XXXX-XXXXXXXX，XXX-XXXXXXX，XXX-XXXXXXXX，XXXXXXX，XXXXXXXX。 
		验证身份证号（15位或18位数字）：^\d{15}|\d{}18$ 
		验证一年的12个月：^(0?[1-9]|1[0-2])$ 正确格式为：“01”-“09”和“1”“12” 
		验证一个月的31天：^((0?[1-9])|((1|2)[0-9])|30|31)$ 正确格式为：01、09和1、31。 
		整数：^-?\d+$ 
		非负浮点数（正浮点数 + 0）：^\d+(\.\d+)?$ 
		正浮点数 ^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$ 
		非正浮点数（负浮点数 + 0） ^((-\d+(\.\d+)?)|(0+(\.0+)?))$ 
		负浮点数 ^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$ 
		浮点数 ^(-?\d+)(\.\d+)?$
 */