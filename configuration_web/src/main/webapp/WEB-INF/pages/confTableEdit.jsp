<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置表编辑</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugs/layer-v2.2/layer/layer.js"></script>
<script type="text/javascript">
var contextPath = '<%=request.getContextPath() %>';
$(document).ready(function(){
	$("#confList").height($(window).height()-80);
	$("#confList").find("tbody>tr").live("click",function(){
		$(this).find("td").each(function(){
			$("#editConf").find("tr:eq("+$(this).index()+")").find("input").val($(this).text());
		});
		$("#editConf").find("input:eq(0)").attr("disabled","disabled");
		$("#editConf").find("input:eq(1)").attr("disabled","disabled");
		var index = layer.open({
			  type: 1, //弹窗
			  closeBtn: 1, //不显示关闭按钮
			  shift: 5, //动画效果
			  area: ['500px', '270px'],
			  title:"编辑配置项",
			  content: $("#editConf"),
			  btn:["确定","取消","删除该配置项"],
			  yes: function(index, layero){
				  $.post("?action=edit",getConfInfo(),function(d){
					  if(d.flag == "error"){
							layer.alert(d.errMsg, {title:"提示",icon: 5});
					  }else{
						  searchConf();
						  layer.close(index);
					  }
				  });
			  },
			  btn3:function(){
				  var index2 = layer.confirm('确定要删除该配置项？', {
					  icon: 3,
					  title:"提示",
					  btn: ['是','否'] //按钮
					}, function(){
						$.post("?action=del",getConfInfo(),function(d){
							  if(d.flag == "error"){
									layer.alert(d.errMsg, {title:"提示",icon: 5});
							  }else{
								  searchConf();
								  layer.closeAll();
							  }
						  });
					}, function(){
						layer.close(index2);
					});
			  }
			});
	});
});
function addConf(){
	$("#editConf").find("input").val("").removeAttr("disabled");
	var index = layer.open({
		  type: 1, //弹窗
		  closeBtn: 1, //不显示关闭按钮
		  shift: 5, //动画效果
		  area: ['500px', '270px'],
		  title:"新增配置项",
		  content: $("#editConf"),
		  btn:["确定","取消"],
		  yes: function(index, layero){
			  $.post("?action=add",getConfInfo(),function(d){
				  if(d.flag == "error"){
						layer.alert(d.errMsg, {title:"提示",icon: 5});
				  }else{
					  searchConf();
					  layer.close(index);
				  }
			  });
		  }
		});
}
function publish(){
	var index = layer.confirm('发布之后将刷新配置中心的缓存，是否确定要发布？', {
		  icon: 3,
		  title:"提示",
		  btn: ['是','否'] //按钮
		}, function(){
			$.post("?action=publish",getConfInfo(),function(d){
				  if(d.flag == "error"){
						layer.alert(d.errMsg, {title:"提示",icon: 5});
				  }else{
					  layer.alert("发布成功！", {title:"提示",icon: 1});
				  }
			  });
		}, function(){
			layer.close(index);
		});
}
var isFirst = true;
function searchConf(){
	var index = layer.load(0,{shade: 0.2});
	$.post(contextPath+"/conf",{action:"reload",confTable:$("#confTable").val()},function(d){
		layer.close(index);
		$("#confList").html(d);
		var module = $("#module").val();
		var name = $("#name").val();
		var value = $("#value").val();
		$("#confList").find("tbody>tr").each(function(){
			if((module==""||$(this).find("td:eq(0)").html().indexOf(module)!=-1)
					&&(name==""||$(this).find("td:eq(1)").html().indexOf(name)!=-1)
					&&(value==""||$(this).find("td:eq(2)").html().indexOf(value)!=-1) ){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
		if(isFirst){
			isFirst = false;
			layer.alert("点击配置项可进行编辑！");
		}
	});
}

function getConfInfo(){
	var conInfo = {};
	conInfo.confTable = $("#confTable").val();
	conInfo.module = $("#editConf").find("input:eq(0)").val();
	conInfo.key = $("#editConf").find("input:eq(1)").val();
	conInfo.value = $("#editConf").find("input:eq(2)").val();
	conInfo.desc = $("#editConf").find("input:eq(3)").val();
	return conInfo;
}
</script>
<style type="text/css">
body{
	font-family: "微软雅黑";
}
.btn{ line-height:28px; margin-right:5px; border-radius:1px; padding:0px 20px; display:inline-block; border:1px solid transparent; color:#fff;cursor:pointer; 
transition:opacity 0.5s;-moz-transition:opacity 0.5s;-webkit-transition:opacity 0.5s;-o-transition:opacity 0.5s;text-decoration: none;}
.btn01{ background-color:#00a2c9}
.btn01:hover{background-color:#00b3de;}
#confList tbody tr:hover{
	background-color: rgb(240, 237, 237);
}
.editConf td{
	padding-top: 10px;
	padding-left: 10px;
}
.editConf td input{
	line-height: 2;
	width: 90%;
}
</style>
</head>
<body>
<div id="editConf" style="display: none;">
<table class="editConf" width='100%' border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td width="30%">模块：</td>
		<td><input value=""/></td>
	</tr>
	<tr>
		<td>配置项名称：</td>
		<td><input value=""/></td>
	</tr>
	<tr>
		<td>配置项值：</td>
		<td><input value=""/></td>
	</tr>
	<tr>
		<td>描述：</td>
		<td><input value=""/></td>
	</tr>
</table>
</div>
<table width='100%' border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td>
			<table width='100%' border='0' cellspacing='0' cellpadding='0' style="padding: 10px;box-shadow: 0px 1px 3px 1px #888888;">
				<tr>
					<td>配置表：</td>
					<td><input id="confTable" value="conf_default"/></td>
					<td>模块：</td>
					<td><input id="module" value=""/></td>
					<td>配置项名称：</td>
					<td><input id="name" value=""/></td>
					<td>配置项值：</td>
					<td><input id="value" value=""/></td>
					<td>
						<a class="btn btn01" href="javascript:searchConf();">查询</a>
						<a class="btn btn01" href="javascript:addConf();">增加</a>
						<a class="btn btn01" href="javascript:publish();">发布</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<div id="confList" style="overflow:auto;width: 100%;"></div>
		</td>
	</tr>
</table>
</body>
</html>