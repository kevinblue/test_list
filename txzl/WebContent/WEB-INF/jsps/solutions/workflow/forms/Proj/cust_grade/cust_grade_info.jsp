<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="cust_base_info_form" title="客户基本信息">
	<div class="mini-panel" title="客户基本信息" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			style="width: 100%" id="contract_base_info">
			<tr class="tr-odd">
				<td class="td-content-title" width="14%">客户名称：</td>
				<td class="td-content" width="36%"><input id="cust_name"
					name="cust_info.custname" class="mini-textbox" readOnly
					require="true" label="客户名称" type="text"
					value="${requestScope['cust_info.custname'] }"></td>
				<td class="td-content-title" width="12.5%">客户编号：</td>
				<td class="td-content" width="36%"><input
					name="cust_info.custnumber" class="mini-textbox" label="客户编号 "
					readOnly type="text"
					value="${requestScope['cust_info.custnumber'] }" /></td>
			</tr>
			<tr class="tr-odd">
			<td class="td-content-title" width="12.5%">客户类别：</td>
				<td class="td-content" width="36%"><input id="custclass"
					name="cust_info.custclass" class="mini-textbox" readOnly
					require="true" label="客户类别" type="text"
					value="${requestScope['cust_info.custclass'] }">
				</td> 
				<td class="td-content-title">法人代表：</td>
				<td class="td-content"><input id="personrep"
					name="cust_info.personrep" class="mini-textbox" readOnly
					require="true" label="法人代表" type="text"
					value="${requestScope['cust_info.personrep'] }"></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" width="12.5%">客户规模：</td>
				<td class="td-content" width="36%">
				<input name="cust_info.custscale" class="mini-textbox" label="客户规模 "
					readOnly type="text" value="${requestScope['cust_info.custscale'] }" /></td>
				<td class="td-content-title" width="12.5%">与金风关系：</td>
				<td class="td-content" width="36%" ><input
					name="cust_info.group_relationship" class="mini-textbox"  label="与金风关系" readOnly type="text"
					value="${requestScope['cust_info.group_relationship'] }" /></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">统一社会信用代码/身份证：</td>
				<td class="td-content"><input id="code" name="cust_info.code"
					class="mini-textbox" readOnly require="true" label="统一社会信用代码/身份证"
					type="text" value="${requestScope['cust_info.code'] }"></td>
				<td class="td-content-title">客户内部行业：</td>
				<td class="td-content"><input name="cust_info.custkind"
					class="mini-textbox" label="客户内部行业 " readOnly type="text"
					value="${requestScope['cust_info.custkind'] }" /></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">客户信用等级：</td>
				<td class="td-content"><input  id="cust_grade.grade_result" name="cust_grade.grade_result"
					class="mini-textbox"  label="客户信用等级" readOnly
					type="text" value="${requestScope['cust_grade.grade_result'] }"></td>
				<td class="td-content-title">评分审核时间：</td>
				<td class="td-content"><input name="cust_grade.createdate"
					class="mini-datepicker"  label="评分审核时间 " readOnly allowInput="false"
					value="${requestScope['cust_grade.createdate'] }" /></td>
			</tr>
		</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("cust_base_info_form");
	};
});
function dosave(totalScoreValue){
	 var result=parseFloat(totalScoreValue).toFixed(2);
	    var cresult="";
		    if(result>=85){cresult="AAA";}
		    else if(result>=75){cresult="AA";}
		    else if(result>=65){cresult="A";}
		    else if(result>=55){cresult="BBB";}
		    else if(result>=45){cresult="BB";}
		    else if(result>=35){cresult="B";}
		    else if(result>=0){cresult="C";}
		   mini.get("cust_grade.grade_result").setValue(cresult);
	    
}
</script>