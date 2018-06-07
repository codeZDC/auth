/**
 * zhengdechao的表单校验
 * 	2017年12月05日
 * email  : /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
 * url    : /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i
 * number : /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/
 * 邮政编码 : /^[0-9]{6}$/;
 * phone  : /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/
 */

(function($){
	var minLength = 3 ; 
	var maxLength = 15 ; 
	var rule = '';
	$.prototype.zdc = function(){
		$(this).find('.check').each(function(){
			rule = $(this).attr('check-rule');
			//添加校验规则,下列语句中使用return false,,只要有检验不通过的就停止校验
			if(rule.indexOf('number')>-1){
				if(this.value==''||!/^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(this.value)){
					$(this).addClass('onError').attr('check-result',$(this).data('msg')+'处请填写数值');
					return false;
				}
				if(typeof $(this).attr('maxNumber')!= 'undefined'&&Number(this.value)>Number($(this).attr('maxNumber'))){
					$(this).addClass('onError').attr('check-result',$(this).data('msg')+'最大值为'+$(this).attr('maxNumber'));
					return false;
				}
				if(typeof $(this).attr('minNumber')!= 'undefined'&&Number(this.value)<Number($(this).attr('minNumber'))){
					$(this).addClass('onError').attr('check-result',$(this).data('msg')+'最小值为'+$(this).attr('minNumber'));
					return false;
				}
			}
			if(rule.indexOf('length')>-1){//console.log('开始校验length');
				var min = typeof $(this).attr('minLength')== 'undefined'?minLength:$(this).attr('minLength');
				var max= typeof $(this).attr('minLength')== 'undefined'?maxLength:$(this).attr('maxLength');
				if($(this).val().trim().length>max||$(this).val().trim().length<min){
					$(this).addClass('onError').attr('check-result',$(this).data('msg')+'长度必须为'+min+'-'+max+'位');
					return false;
				}
			}
			if(rule.indexOf('notEmpty')>-1){//console.log('开始校验notEmpty');
				if($(this).val().trim()==''){
					$(this).addClass('onError').attr('check-result',$(this).data('msg')+'不能为空字符串');
					return false;
				}
			}
			$(this).removeClass('onError');
		});
		if($(this).find('.onError')[0]){
			$.alert($(this).find('.onError').eq(0).attr('check-result'));
			return false;
		}
		return true;
   }
})(jQuery);