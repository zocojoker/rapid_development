		//js封装 参数信息
		/* 	strvalue : $("#ageid"),      输入框的id
			re : /^(\d+)$/i,             正则表达式
			nullmsg : "不能为空",           验证消息是否为空
			erromsg : "格式错误",	      	     返回格式错误的验证信息
			errolength : "您输入的字符过长",   返回过长的验证信息
			backflag : true,        返回信息boolean 值   TRUE 表示通过  false表示未通过
			require : false               是否为必填项 */    
			function checkutil(jsons) {
				var v = jsons.strvalue;
				$("#v_span").remove();//清空错误提示信息
				//验证是否为空
				if ($(v).val().length == 0) {
					$(v).after("<span class='help-block m-b-none' id="+$(v).val()+'v_span'+" style='color:red'>"
									+ jsons.nullmsg + "</span>");
					jsons.backflag = false;//返回的结果
					if (!jsons.require) {//如果为非必填项则始终返回true
						jsons.backflag = true;
						$("#"+$(v).val()+'_span').html('');
					} 
					//验证长度小于40
				} else if ($(v).val().length >= 40) {
					//<span class='help-block m-b-none' >jsons.errolength</span>
					$(v).after("<span class='help-block m-b-none' style='color:red'>"
									+ jsons.errolength + "</span>");
					jsons.backflag = false;
					//格式验证
				}else if (!jsons.re.test($(v).val())) {
					$("#_span").html("");
						$(v).after("<span class='help-block m-b-none' id='v_span' style='color:red'>"
										+ jsons.erromsg + "</span>");
						jsons.backflag = false;
				} 
				return jsons.backflag;
			}//验证字符串的工具方法
	 