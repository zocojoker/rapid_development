	//js封装
			/* 	strvalue:$("#ageid"),
						re: /^(\d+)$/i,//正则表达式
						nullmsg:"不能为空",//为空时候 的验证信息
						erromsg:"格式错误",//格式错误的时候的验证信息
						errolength : "您输入的字符过长", //字符过长时候的验证信息
						backflag : true,//函数的返回值
						require : false  是否为必填项  TURE 是必填项 false非必填项*/
		//以json格式数据传输
		function checkutil(jsons){
				var v = jsons.strvalue;
				var reqflag = jsons.require;
				console.log($(v).val());
				$("#"+jsons.ageidmsg+"_checknamemsg").remove();
				
				if($(v).val().length ==0 ){//判断是否为空
					$(v).after("<span class='help-block m-b-none' style='color:red' id='"
							+jsons.ageidmsg+"_checknamemsg'>"+jsons.nullmsg+"</span>");
					
					jsons.backflag=false;
					if(!reqflag){//判断是否为必填项
						jsons.backflag=true;
						$("#"+jsons.ageidmsg+"_checknamemsg").remove();
					}
				}else if($(v).val().length >=40){//判断是否过长
					$(v).after("<span class='help-block m-b-none' style='color:red' id='"
							+jsons.ageidmsg+"_checknamemsg'>"+jsons.errolength+"</span>");
					
					jsons.backflag=false;
				}else if(!jsons.re.test($(v).val())) {//格式验证
					$(v).after("<span class='help-block m-b-none' style='color:red' id='"
							+jsons.ageidmsg+"_checknamemsg'>"+jsons.erromsg+"</span>");
					jsons.backflag=false;
				}

				//返回值
			return jsons.backflag;
			}