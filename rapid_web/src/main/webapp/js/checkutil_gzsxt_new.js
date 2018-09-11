		//js封装 参数信息
		/* 	strvalue : $("#ageid"),      输入框的id
			re : /^(\d+)$/i,             正则表达式
			nullmsg : "不能为空",           验证消息是否为空
			erromsg : "格式错误",	      	     返回格式错误的验证信息
			errolength : "您输入的字符过长",   返回过长的验证信息
			backflag : true,        返回信息boolean 值   TRUE 表示通过  false表示未通过
			require : false               是否为必填项 */    
		function checkUtil(json){
    	 /* trvalue,re, nullmsg,erromsg,errolength,require,backflag */
    	 var flag = json.backflag;
			  //要验证的字符串
				var str = $("#"+json.strvalue).val();
				$("#"+json.strvalue+"_inputErrorid").html("");
			//  alert(json.re.test(str)+" ====json.re.test(str)");
				//不是必填项则可以为空
				if(json.require){
					if(str.length<= 0){
						  $("#"+json.strvalue).after("<label class='control-label' for='inputError1' id='"
								  +json.strvalue+"_inputErrorid'  >"+json.nullmsg+"</label>");
						  flag=false;
					}else if(str.length>0){
						   if(str.length> 10){
								  $("#"+json.strvalue).after("<label class='control-label' for='inputError1' id='"
										  +json.strvalue+"_inputErrorid'  >"+json.errolength+"</label>");
								  flag=false;
							  }else if(!json.re.test(str)){
								  $("#"+json.strvalue).after("<label class='control-label' for='inputError1' id='"
										  +json.strvalue+"_inputErrorid'  >"+json.erromsg+"</label>");
								  flag=false;
						}
					}
				}else{
					 if(str.length>0){
						   if(str.length> 50){
								  $("#"+json.strvalue).after("<label class='control-label' for='inputError1' id='"
										  +json.strvalue+"_inputErrorid'  >"+json.errolength+"</label>");
								  flag=false;
							  }else if(!json.re.test(str)){
								  $("#"+json.strvalue).after("<label class='control-label' for='inputError1' id='"
										  +json.strvalue+"_inputErrorid'  >"+json.erromsg+"</label>");
								  flag=false;
						}
					}
				}
		
			  return   flag;
    	}
	 