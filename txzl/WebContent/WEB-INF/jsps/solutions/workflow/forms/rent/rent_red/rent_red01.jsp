<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
</style>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	if(submitCheck() == false) return;
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_red_detail_str" name="json_rent_red_detail_str" value='${empty json_rent_red_detail_str ? "[]" : json_rent_red_detail_str }'></input>
<div id="rent_red_form">
	<!-- 插入合同基本信息 --><jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;height:500px;">
		<div title="租金回笼历史" name="rent_income_his" iconCls="icon-node">
			<jsp:include page="rent_income_his.jsp" ></jsp:include>
		</div>
		<div title="本次租金红冲明细" name="rent_red_detail" iconCls="icon-node">
			<jsp:include page="rent_red_detail.jsp" ></jsp:include>
		</div>
	</div>
</div>
<script>
jQuery(function(){
	miniui_ext.initformenabled("contract_base_info_form");
});

function submitCheck(){
	var rentRedDetailDatas = mini.get("rent_red_detail").getData();
	if(rentRedDetailDatas.length > 0){
		return true;
	}else{
		alert("请选择需要红冲的数据！");
		return false;
	}
}
</script>