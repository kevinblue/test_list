<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="contract_cancel_reason_form" title="本次投放金额">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-even">
			<td class="td-content-title" width='12%'>本次投放编号：</td>
			<td class="td-content" width='38%'>
				<input id="put_info_putnum" name="put_info.putnum" class="mini-textbox" required="true" allowInput="true" value="<mini:param  name="put_info.putnum"/>"/>
			</td>
			<td class="td-content-title" width='12%'>合同总投放额：</td>
			<td class="td-content" width='38%'>
				<input id="putallequipamt" name="put_info.putallequipamt" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putallequipamt"/>"/>
			</td>
		</tr>
		
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>累计已投放金额：</td>
			<td class="td-content" width='38%'>
				<input id="putalreadyequipamt" name="put_info.putalreadyequipamt" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putalreadyequipamt"/>"/>
			</td>
			<td class="td-content-title" width='12%'>可投放金额：</td>
			<td class="td-content" width='38%'>
				<input id="putoverplusequipamt" name="put_info.putoverplusequipamt" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putoverplusequipamt"/>"/>
			</td>
		</tr>
		
		<tr class="tr-even">
			<td class="td-content-title" width='12%'>本次投放金额：</td>
			<td class="td-content" width='38%'>
				<input id="putcurequipamt" name="put_info.putcurequipamt" class="mini-textbox"  onkeyup='adjustTotalPrice(e)' required="true" allowInput="true" value="<mini:param  name="put_info.putcurequipamt"/>"/>
			</td>
			<td class="td-content-title" width='12%'>本次投放占比：</td>
			<td class="td-content" width='38%'>
				<input id="putcurrate" name="put_info.putcurrate" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putcurrate"/>"/><font class="required-tag">%</font>
			</td>
		</tr>
		
		<tr class="tr-odd">
			<td class="td-content-title">备注：</td>
			<td colspan="2" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="put_info_repealreason" style="width: 100%;height: 100px;" name="put_info.repealreason" required="true" class="mini-textarea" value="<mini:param  name="put_info.repealreason"/>" />
			</td>
			<td class="td-content">
			      <a class="mini-button" iconCls="icon-ok" id="id_submit" onclick="changeCondition()"  style="margin-left: 65px;color: red;">确认</a>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_cancel_reason_form");
		mini.get("id_submit").hide();
	};
});

function adjustTotalPrice(e){
	var putallequipamt = getNumber("putallequipamt");//设备款
	//setformatvalue($mini("putcurequipamt"));
	var putcurequipamt = mini.get("putcurequipamt").getInputText().replace(/,/g,"");//手续费
	var putcurrate = mini.get("putcurrate");//手续费比例
	var value =!putallequipamt?  0 : decimal(putcurequipamt/putallequipamt*100, 6);
	putcurrate.setValue(value);
}

function changeCondition(e){
	var putcurequipamt = mini.get("putcurequipamt").getInputText().replace(/,/g,"");//本次投放
	var putoverplusequipamt = mini.get("putoverplusequipamt").getInputText().replace(/,/g,"");//可投放
	if(parseFloat(putcurequipamt)>parseFloat(putoverplusequipamt)){
		mini.alert("本次投放金额不能大于可投放金额！！！");
		return false;
	}
	var equipamt = mini.get("equipamt");//比例
	equipamt.setValue(putcurequipamt);
	changeequipamtvalue();//同步变更比例
	//生成本次投放记录
	setFundPutCurrent(putcurequipamt);
	//清空资金计划
	mini.get("fund_fund_plan").setData('[]');
	mini.get("fund_cash_plan_frame").setData('[]');
	mini.alert('商务调息修改成功，请重新测算租金！');
}

function  setFundPutCurrent(putcurequipamt)
{
	debugger;
	var miniTable=mini.get("put_plan");
	var newRow = {};
	var currentGrid = mini.get("put_current");//获取本次计划grid
	//var rowDatas =currentGrid.getData();
	var rowDatas=[];
	newRow.fundfundchargeplan="";
	newRow.paymentid=1;//mini.get("put_info_putnum").getValue();
	newRow.pid="";
	newRow.feetype="feetype10";
	newRow.feetypename="设备款";
	newRow.settlemethod="payfund6";
	newRow.settlemethodname="电汇";
	newRow.paytype='pay_type_out';
	newRow.paytypename='付款';
	newRow.factdate=mini.formatDate(new Date(),"yyyy-MM-dd");
	newRow.factmoney=putcurequipamt;//本次投放金额
	newRow.factobject="";
	newRow.factobjectname="";
	newRow.comparemoney="";//把可投放的金额传到本次投放列表方便比较	
	newRow.feeadjust='0';
	newRow.ffcmemo="";
	rowDatas.push(mini.clone(newRow));
	
	currentGrid.setData(rowDatas);
	//miniTable.deselectAll(false);
}
</script>