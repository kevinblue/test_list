<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText) //返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	var assetgrid = mini.get("asset_category_list").getData();
	var grid = mini.encode(assetgrid);
	mini.get("id_json_asset_category_str").setValue(grid);
	
	return true;
}
</script>
<input style="display:none;"	class="mini-textarea" id="id_json_asset_category_str" name="json_asset_category_str" value='${empty json_asset_category_str ? "[]" : json_asset_category_str }'></input>
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td >
				  <jsp:include page="comm/asset_base_info.jsp">
				      <jsp:param value="true" name="isView"/>
				  </jsp:include>
			    </td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="border:0px;">
		<div title="资产分类详情" name="asset_category" iconCls="icon-node">
			<jsp:include page="comm/asset_category_detail.jsp" ></jsp:include>
		</div>
	</div>
</div>