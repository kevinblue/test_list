<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
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
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}

</script>
<!--多行控件  -->
<input type="hidden"  id="id_json_current_regulating_of_breathing_str" name="json_current_regulating_of_breathing_str" value='<mini:param  name="json_current_regulating_of_breathing_str" defaultValue="[]"/>'/>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 基准利率 --> 
			 <jsp:include page="common/base_rate_info.jsp"></jsp:include>
		 </td>
	   </tr>
	   <tr>
	   	<td colspan=4>
	   		<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" style="width: 99%;" bodyStyle="padding:0;border:0;">
				<div title="本次调息" name="current_regulating_of_breathing" iconCls="icon-cut">
				     <jsp:include page="common/current_regulating_of_breathing.jsp" >
				     	<jsp:param value="true" name="isView"/>
				     </jsp:include>
				</div>
				<div title="待调息" name="todo_regulating_of_breathing" iconCls="icon-cut" >
					 <jsp:include page="common/todo_regulating_of_breathing.jsp" >
					 	<jsp:param value="true" name="isView"/>
					 </jsp:include>
				</div>
				<div title="已调息" name="already_regulating_of_breathing" iconCls="icon-cut">
					<jsp:include page="common/already_regulating_of_breathing.jsp" ></jsp:include>
				</div>
				
			</div>
	   	</td>
	   </tr>
	   <tr class="tr-tx-info tr-even">
	      <td class="td-content-title">调息操作记录</td>
	   </tr>
	   <tr class="tr-tx-info tr-even">
	   <td class="td-content-title" >
	   		<input type="hidden" id="id_adjust_contractids" name="adjust_contractids" value="<mini:param  name="adjust_contractids"/>">
	   		<input type="hidden" id="id_adjustid" name="adjustid" value="<mini:param  name="adjustid" />">
	   		<input type="hidden" id="id_docid" name="docid" value="<mini:param  name="docid" />">
	   		<textarea style="width:98.9%;height:250px;float:left;" id="tx_text_memo" class="td-content-input td-content-readonly" readOnly name="tx_text_memo" ><mini:param  name="tx_text_memo" /></textarea>
	   </td>
	</tr>
	</table>
</div>		
<div id="id_station_message" class="mini-window"  closed="false" modal="true" title="操作反馈" style="display:none;width:810px;height:400px;">
   <center><div id="msg_memo"></div></center><br/>
   <div style="text-align:center;width:800px;vertical-align:bottom" id="id-dlg-buttons">
      <a  style="margin-left:20px;margin-top:5px;" href="javascript:void(0);" class="btn btn-primary" onclick='msgButton()'><span id="msgButtonText">取消</span></a>
   </div>
</div>

