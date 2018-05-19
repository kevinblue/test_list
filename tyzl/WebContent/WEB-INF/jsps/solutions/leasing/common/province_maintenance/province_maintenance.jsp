<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>省市编码维护</title>

	<%@include file="/base/mini.jsp"%>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
    <style type="text/css">
		html,body{
			overflow:hidden;
		}
		.picItem{
			float:left;
			padding:5px 5px 5px 5px;
			border:1px solid silver;
		}
		.ul_district_list li{
			list-style-type: none;
			line-height: 20px;
			font-size: 12px;
			padding-left: 10px;
			cursor: pointer;
		}
		.li_selected{
			background-color: #FBEC88;
		}
		.li_hover{
			background-color: #FAFAFA;
		}
		li span{
			margin-right: 100px;
			float: right;
		}
	</style>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight;
	var ship = {
			country : {child : "province", name : "国家"},
			province : {parent : "country" , child : "city", name : "省份" },
			city : {parent : "province" , child : "county", name : "城市" },
			county : {parent : "city", name : "区县" }
		};
	var operNames = {
			"add" : "新增", 
			"update" : "修改", 
			"delete" : "删除"
		};
	
	//【省份 城市 县区】选中事件及鼠标悬停事件
	function bindEvent(ul_id_district,obj,tempstr){
		//【省份 城市 县区】绑定鼠标悬停事件
		$("#" + ul_id_district + " li").mouseover(function(){
			if($(this).hasClass("li_selected") == false){
				$(this).addClass("li_hover");
			}
		}).mouseout(function(){
			$(this).removeClass("li_hover");
		});
		

		//【省份 城市 县区】绑定单击事件
		$("#" + ul_id_district + " li").bind("click",function(){
			if(obj != null){
				obj.removeClass("li_selected");
			}
			$(this).removeClass("li_hover");
			$(this).addClass("li_selected");
			obj = $(this);
			if(tempstr){
				loadDistrict(tempstr,{pid: $(this).attr("did")});
				var fix = "（" + obj.attr("dname") + "）";
				var type = tempstr.replace('id_list_','');
				mini.get("menu_main_" + type).setTitle(ship[type].name + fix);
			}
		});
	}
	
	//ajax方式加载地区，
	function loadDistrict(ul_id_district, param, callback){
		var params = {};
		params['xmlFileName'] = "/acl/queryDistrict.xml";
		jQuery.extend(params, param);
		
		tenwa.ajaxRequest({
			url: "${pageContext.request.contextPath}/table/getTableData.action",
			params:params,
			success: function(res) {
				var jsondata = eval('(' + res.responseText + ')').datas;
				var dataBox = jQuery("#" + ul_id_district);
				dataBox.html('');
				if(jsondata && jsondata.length > 0){
					for(var i = 0;i < jsondata.length;i ++){
						var dataTemp = jsondata[i];
						var dataName = dataTemp.name;
						var dataId = dataTemp.id;
						var dataCode = dataTemp.code;
						var li = jQuery("<li/>").attr('did',dataId).attr('dname',dataName).html(dataName + (dataCode ? ("[" + dataCode + "]") : ""));
						li.css({"color" : "#444","font-color" : "#444"});
						dataBox.append(li);
					}
				}
				
				var dataType = ul_id_district.replace("id_list_", "");
				if(ship[dataType].child){
					bindEvent(ul_id_district,null,"id_list_" + ship[dataType].child);
				} else {
					bindEvent(ul_id_district,null,"");
				}
				
				if(callback){
					callback();
				}
			},
            failure: function(res) {
                mini.alert("Server Unavailable");
            }
		});
	}
	
	jQuery(function(){
		mini.parse();
		//加载省份数据，生成省份列表，默认中国，以后可以随意扩展
		loadDistrict("id_list_country",{id: "000000"},function(){
			var subList = jQuery("#id_list_country").find('li');
			var first = jQuery("#id_list_country").find('li:first');
			if(subList && subList.length > 0){
				subList.removeClass('li_selected');
				first.addClass('li_selected');
				mini.get("menu_main_province").setTitle(ship['country'].name + "（" + first.attr("dname") + "）");
			}
		});
		loadDistrict("id_list_province",{pid: "000000"});
	});
	
	
	function __addCountry(){
		showDiv('add-country');
	}
	function __updateCountry(){
		showDiv('update-country');
	}
	function __removeCountry(){
		removeDistrict('country');
	}
	
	function __addProvince(){
		showDiv('add-province');
	}
	function __updateProvince(){
		showDiv('update-province');
	}
	function __removeProvince(){
		removeDistrict('province');
	}
	
	function __addCity(){
		showDiv('add-city');
	}
	function __updateCity(){
		showDiv('update-city');
	}
	function __removeCity(){
		removeDistrict('city');
	}
	
	function __addCounty(){
		showDiv('add-county');
	}
	function __updateCounty(){
		showDiv('update-county');
	}
	function __removeCounty(){
		removeDistrict('county');
	}
</script>
</head>
<body>

<div id='menu_main_country' class="mini-panel" title="国家" showToolbar="true" style="width: 33%;float: left;margin-right: 3px;display: none;">
	<div property="toolbar" style="padding-left: 5px;">
		<a onclick="__addCountry" class="mini-button" iconCls="icon-add">添加</a>
		<a onclick="__updateCountry" class="mini-button" iconCls="icon-edit">修改</a>
		<a onclick="__removeCountry" class="mini-button" iconCls="icon-remove">删除</a>
	</div>
	<div id="id_div_country" style="border:1px solid #DDD;overflow:auto;">
		<ul id="id_list_country" class="ul_district_list" style="padding-left: 5px;margin-top: 3px;"></ul>
	</div>
</div>

<div id='menu_main_province' class="mini-panel" title="省份" showToolbar="true" style="width: 33%;float: left;margin-right: 3px;">
	<div property="toolbar" style="padding-left: 5px;">
		<a onclick="__addProvince" class="mini-button" iconCls="icon-add">添加</a>
		<a onclick="__updateProvince" class="mini-button" iconCls="icon-edit">修改</a>
		<a onclick="__removeProvince" class="mini-button" iconCls="icon-remove">删除</a>
	</div>
	<div id="id_div_province" style="border:1px solid #DDD;overflow:auto;">
		<ul id="id_list_province" class="ul_district_list" style="padding-left: 5px;margin-top: 3px;"></ul>
	</div>
</div>

<div id='menu_main_city' class="mini-panel" title="城市" showToolbar="true" style="width: 33%;float: left;margin-right: 3px;">
	<div property="toolbar" style="padding-left: 5px;">
		<a onclick="__addCity" class="mini-button" iconCls="icon-add">添加</a>
		<a onclick="__updateCity" class="mini-button" iconCls="icon-edit">修改</a>
		<a onclick="__removeCity" class="mini-button" iconCls="icon-remove">删除</a>
	</div>
	<div id="id_div_city" style="border:1px solid #DDD;overflow:auto;">
		<ul id="id_list_city" class="ul_district_list" style="padding-left: 5px;margin-top: 3px;"></ul>
	</div>
</div>

<div id='menu_main_county' class="mini-panel" title="区县" showToolbar="true" style="width: 33%;float: left;margin-right: 3px;">
	<div property="toolbar" style="padding-left: 5px;">
		<a onclick="__addCounty" class="mini-button" iconCls="icon-add">添加</a>
		<a onclick="__updateCounty" class="mini-button" iconCls="icon-edit">修改</a>
		<a onclick="__removeCounty" class="mini-button" iconCls="icon-remove">删除</a>
	</div>
	<div id="id_div_county" style="border:1px solid #DDD;overflow:auto;">
		<ul id="id_list_county" class="ul_district_list" style="padding-left: 5px;margin-top: 3px;"></ul>
	</div>
</div>

<div id="id_detailInfoWindowContianer" class="mini-window" style="display:none;width:250px;height:145px;">
	<div id="id_detailInfoForm">
		<table style="width:100%;" class="fillTable">
			<tr style="display:none">
				<td colspan="2">
					<input id="_pid" name="pid" class="mini-hidden" value=""/>
					<input id="_opertype" name="opertype" class="mini-hidden" value=""/>
					<input id="_datatype" name="datatype" class="mini-hidden" value=""/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">名称:</td>
				<td class="td-content" nowrap="nowrap">
					<input id="_name" name="name" class="mini-textbox" style="width: 180px;"/><font color="red">*</font>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">编码:</td>
				<td class="td-content" nowrap="nowrap">
					<input id="_id" name="id" class="mini-textbox" style="width: 180px;"/><font color="red">*</font>
				</td>
			</tr>
		</table>
		<div style="width: 100%;padding: 10px 0px;text-align: center;">
			<a class="mini-button" onclick='submitDistrict'><span>确定</span></a>
			<a class="mini-button" onclick='__submitDistrictClose'><span>取消</span></a>
		</div>
	</div>
</div>


</body>
</html>
<script type='text/javascript'>
	$('#menu_main_country').css("height",pageHeight);
	$('#menu_main_province').css("height",pageHeight);
	$('#menu_main_city').css("height",pageHeight);
	$('#menu_main_county').css("height",pageHeight);
	
	$('#id_div_country').css("height",pageHeight-60);
	$('#id_div_province').css("height",pageHeight-60);
	$('#id_div_city').css("height",pageHeight-60);
	$('#id_div_county').css("height",pageHeight-60);
	
	//新增 修改显示窗口
	function showDiv(oper){
		var operDataType = oper.split('-');
		var operType = operDataType[0];
		var current = operDataType[1];
		
		mini.get("_opertype").setValue(operType);
		mini.get("_datatype").setValue(current);
		
		var parent = ship[current].parent;
		var child = ship[current].child;
		
		var parentLi = $("#id_list_" + parent + " .li_selected");
		var currentLi = $("#id_list_" + current + " .li_selected");
		var childLi = $("#id_list_" + child + " .li_selected");
		
		//新增修改删除都要先选中父节点
		if(parent){
			if(isSelected(parent)){
				var pid = parentLi.attr('did');
				mini.get("_pid").setValue(pid);
			} else {
				mini.alert("请先选中" + ship[parent].name + "!");
				return;
			}
		}
		
		if(operType == "add"){
			mini.get("_id").setValue('');
			mini.get("_name").setValue('');
		}
		
		if(operType == "update" || operType == "delete"){
			if(!isSelected(current)){
				mini.alert("请先选择要" + operNames[operType] + "的" + ship[current].name + "!");
				return;
			}
		}
		
		if(operType == "update"){
			var id = currentLi.attr('did');
			var name = currentLi.attr('dname');
			mini.get("_id").setValue(id);
			mini.get("_name").setValue(name);
		}
		
		if(operType == "delete"){
			if(child && childLi && childLi.length > 0){
				mini.alert("请先删除所有属于该" + ship[current].name + "的" + ship[child].name + "！");
				return;
			}
		}
		
		var winTitle = operNames[operType] + ship[current].name;
		mini.get("id_detailInfoWindowContianer").setTitle(winTitle);
		mini.get("id_detailInfoWindowContianer").show();
	}

	
	//新增 修改提交方法
	function __submitDistrictClose(){
		mini.get("id_detailInfoWindowContianer").hide();
	}
	
	function submitDistrict(){
		var params = {};
		params.id = $.trim(mini.get("_id").getValue());
		params.pid = $.trim(mini.get("_pid").getValue());
		params.name = $.trim(mini.get("_name").getValue());
		params.opertype = $.trim(mini.get("_opertype").getValue());
		params.datatype = $.trim(mini.get("_datatype").getValue());
		
		var operType = params.opertype;
		var dataType = params.datatype;
		if(params.name == ''){mini.alert('请填写地区名称！');return;}
		if(params.id == ''){mini.alert('请填写地区编码！');return;}
		if(/\d{6}/.test(params.id) == false){mini.alert('地区编码必须是六位数字！');return;}
		if(isExist(dataType, operType, params.id)){mini.alert("该编码已存在，请重新输入！");return;}
		
		mini.confirm("确定" + operNames[operType] + "该记录么？", operNames[operType], function(action){
			if(action == 'ok'){
		        tenwa.ajaxRequest({
		            url: "${pageContext.request.contextPath}/district/" + operType + "District.acl",
		            params: params,
		            timeout: 30 * 1000,
		            success: function(res) {
		            	mini.alert(operNames[operType] + "成功！",operNames[operType],function(){
							var pid = mini.get("_pid").getValue();
			            	mini.get("id_detailInfoWindowContianer").hide();
			            	loadDistrict("id_list_" + dataType, {pid: pid});
		            	});
		            },
		            failure:function(){
		            	mini.alert(operNames[operType] + "失败！");
		            }
		        });
			}
		});
	}
	
	//删除地区
	function removeDistrict(dist){
		var did = $("#id_list_" + dist + " .li_selected").attr("did");
		var dname = $("#id_list_" + dist + " .li_selected").attr("dname");
		var pid = $("#id_list_" + ship[dist].parent + " .li_selected").attr("did");
		
		if(!isSelected(dist)){mini.alert("请先选中要删除的" + ship[dist].name + "！");return;}
		if(ship[dist].child){
			if($("#id_list_" + ship[dist].child + ":has(li)").html()!=undefined){mini.alert("请先删除所有属于该" + ship[dist].name + "的" + ship[ship[dist].child].name + "！");return;}
		}
		
		mini.confirm("确定删除该" + ship[dist].name + "[" + dname + "]" + "么？",'删除？',function(action){
			if(action == 'ok'){
				tenwa.ajaxRequest({
		            url: "${pageContext.request.contextPath}/district/removeDistrict.acl",
		            params: {id : did},
		            timeout: 30 * 1000,
		            success: function(res) {
		            	mini.alert("删除成功!",'提示',function(){
			            	loadDistrict("id_list_" + dist, {pid: pid});
		            	});
		            }
		        });
			}
		});
	}
	
	//判断某ul是否有选中元素
	function isSelected(dist){
		var area = $("#id_list_" + dist + " .li_selected");
		return area && area.length > 0 && !!(area.attr("did"));
	}
	
	//查看某编码是否已存在
	function isExist(dataType, operType, id){
		var exit = $("#id_list_" + dataType + " li[did='" + id + "']");
		if(exit && exit.length > 0){
			var selected = $("#id_list_" + dataType + " .li_selected");
			if(operType == "update" && selected.attr("did") == id){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
</script>