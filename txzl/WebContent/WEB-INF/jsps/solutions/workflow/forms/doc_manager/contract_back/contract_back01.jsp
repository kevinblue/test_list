<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
    //流程保存之前回调
	function workflowSaveCallBack()
	{
		miniui_ext.saveIncludeData("tabDetails");
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{
		//if (miniui_ext.submitFormValidation(["id_table_contract_borrow_present"]) == false) return false;
		   //if (miniui_ext.submitFormValidation(["id_table_contract_borrow_present"]) == false) return false;
		   if("" ==mini.get("contract_back").getData()){
				mini.alert("没有需要归还的文件，无法提交！");
				return false;
			}
			var a=mini.get("contract_back").getData();
			var b=[];
			for (var i=0;i<a.length;i++){
				if(a[i].borrowingstatus=="归还"){
				b.push(a[i].borrowingstatus);}
			}
			if(b.length==0){
			   mini.alert("请将需要规还的档案借阅状态改为归还状态！");
				return false;
			}
		   miniui_ext.saveIncludeData("tabDetails"); 
			return true;
	}
</script>
<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_contract_back_str" 	name="json_contract_back_str" value='${empty json_contract_back_str ? "[]" : json_contract_back_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_fundput_file_back_str" 	name="json_fundput_file_back_str" value='${empty json_fundput_file_back_str ? "[]" : json_fundput_file_back_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	</table>
</div>
<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="border:0px;">
	<div title="本次归还" id="contract_back" name="contract_back" iconCls="icon-cut" >
		 <jsp:include page="contract_back_present.jsp" ></jsp:include>
	</div>
	<div title="历史借阅" name="contract_back" iconCls="icon-cut" >
		 <jsp:include page="contract_back_his.jsp" ></jsp:include>
	</div>
</div>