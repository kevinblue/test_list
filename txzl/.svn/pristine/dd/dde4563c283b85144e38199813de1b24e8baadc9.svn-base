<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <input name="proj_info.custInfo" id="proj_info.custInfo" type="hidden"  value="${requestScope['proj_info.custInfo'] }"/>
    <input name="proj_info.projstatus" id="proj_info.projstatus" type="hidden"  value="${requestScope['proj_info.projstatus'] }"/>
    <input name="proj_info.custclass" id="proj_info.custclass" type="hidden"  value="${requestScope['proj_info.custclass'] }"/>
    <div id="riskreport_change_info_form" title="风险报送">

	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">风险名称：</td>
	             <td class="td-content" width="38%"><input name=" risk_info.riskname" id ="risk_name"  class="mini-textbox" label="项目编号" required=true type="text"  value="${requestScope['risk_info.riskname']}"></td>
	             <td class="td-content-title" width="12%">风险类型：</td>
	             <td class="td-content" width="38%"><input name=" risk_info.risktype" class="mini-combobox asLabel" required=true textField="text" 
	                  	    
	                  	   valueField="text"  
						   data="[{text:'运营风险'},{text:'战略风险'},{text:'操作风险'},{text:'财务风险'},{text:'外部风险'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['risk_info.risktype'] }" 
						   text="${requestScope['rawValue_risk_info.risktype'] }" 
						   />
						   <input id="rawValue_risk_info.risktype" name="rawValue_risk_info.risktype" value="${requestScope['rawValue_risk_info.risktype']}" class="mini-textbox" style="display:none"/>
               </td>
	         
	          </tr>
	          <tr class="tr-project-info tr-odd">  
		     	<td class="td-content-title">风险可能性：</td>
	             <td class="td-content">
			             <input name="risk_info.riskPoss" id="risk_info.riskPoss" label="风险可能性" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_xmlFileName:'combos/comboDict.xml',_params:{pid:'risk_poss'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   value="${requestScope['risk_info.riskPoss'] }" 
									   text="${requestScope['rawValue_risk_info.riskPoss'] }" 
									   onvaluechanged="comboboxChanged"
									   onitemclick="riskPoss"  />
					     <input id="rawValue_risk_info.riskPoss" name="rawValue_risk_info.riskPoss" value="${requestScope['rawValue_risk_info.riskPoss']}" class="mini-textbox" style="display:none" /> 
	             </td>
	             <td class="td-content-title" width="12%">风险可能性评分：</td>
	             <td class="td-content" width="38%"><input name="risk_info.possScore" id ="risk_info.possScore" readOnly class="mini-textbox"   label="风险可能性评分"   required=true type="text" value="${requestScope['risk_info.possScore']}"></td>
	          </tr>
	          <tr class="tr-project-info tr-odd">  
		     	 <td class="td-content-title" width="12%">风险概率：</td>
	             <td class="td-content" width="38%"><input name="risk_info.riskChance" id ="risk_info.riskChance" readOnly class="mini-textbox"   label="风险概率"   required=true type="text" value="${requestScope['risk_info.riskChance']}"></td>
	             <td class="td-content-title" width="12%">风险频率：</td>
	             <td class="td-content" width="38%"><input name="risk_info.riskQuency" id ="risk_info.riskQuency" readOnly class="mini-textbox"   label="风险频率"   required=true type="text" value="${requestScope['risk_info.riskQuency']}"></td>
	          </tr>
	          <tr class="tr-project-info tr-odd">  
		     	<td class="td-content-title">风险影响程度：</td>
	             <td class="td-content">
			             <input name="risk_info.riskDegree" id="risk_info.riskDegree" label="风险影响程度" class="mini-combobox" textField="name"  required="true"
				                  	   
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'risk_degree'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   value="${requestScope['risk_info.riskDegree'] }" 
									   text="${requestScope['rawValue_risk_info.riskDegree'] }" 
									   onvaluechanged="comboboxChanged"
									   onitemclick="riskDegree"
									   />
						 <font class="required-tag"></font> 
					     <input id="rawValue_risk_info.riskDegree" name="rawValue_risk_info.riskDegree"   value="${requestScope['rawValue_risk_info.riskDegree']}" class="mini-textbox" style="display:none"/> 
	             </td>
	             <td class="td-content-title" width="12%">风险影响程度评分：</td>
	             <td class="td-content" width="38%"><input name="risk_info.degreeScore" id ="risk_info.degreeScore" readOnly class="mini-textbox" label="风险影响程度评分"   required=true type="text" value="${requestScope['risk_info.degreeScore']}"></td>
	          </tr>
	          <tr class="tr-odd">
	             <td class="td-content-title">风险定性影响：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" readOnly label="风险定性影响：" name="risk_info.riskQualitative" value="${requestScope['risk_info.riskQualitative'] }" class="mini-textarea"   type="text" >  </td>
	           </tr>
	           <tr class="tr-odd">
	             <td class="td-content-title">风险定量影响：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" readOnly label="风险定量影响：" name="risk_info.riskRation" value="${requestScope['risk_info.riskRation'] }" class="mini-textarea"   type="text" >  </td>
	           </tr>
	           <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">风险等级：</td>
	             <td class="td-content" width="38%"><input name="risk_info.risklevel" id ="risk_level" readOnly class="mini-textbox" label="风险等级"   required=true type="text" value="${requestScope['risk_info.risklevel']}"></td>
	             <td class="td-content-title" width="12%">风险类别：</td>
	             <td class="td-content" width="38%"><input name="risk_info.riskcategories" id ="risk_info.riskcategories" readOnly class="mini-textbox" label="风险等级"   required=true type="text" value="${requestScope['risk_info.riskcategories']}"></td>
	             <%-- <td class="td-content" width="38%"><input name="risk_info.riskcategories" class="mini-combobox asLabel" required=true textField="text" 
	                  	   valueField="value"  
						   data="[{text:'绿色',value:'绿色'},{text:'黄色',value:'黄色'},{text:'红色',value:'红色'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['risk_info.riskcategories'] }" 
						   text="${requestScope['rawValue_risk_info.riskcategories'] }" 
						   onvaluechanged="oncreditcardyesorno"/>
						   <input id="rawValue_risk_info.riskcategories" name="rawValue_risk_info.riskcategories" value="${requestScope['rawValue_risk_info.riskcategories']}" class="mini-textbox" style="display:none"/>
                 </td> --%>
	          </tr>
	        
	           <tr class="tr-odd">
	             <td class="td-content-title">风险描述：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" label="风险描述：" name="risk_info.riskdescription"    value="${requestScope['risk_info.riskdescription'] }" class="mini-textarea"  type="text" >  </td>
	           </tr> 
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">风险控制策略：</td>
	             <td class="td-content" colspan=3>
	             <input name=" risk_info.riskcontrolstrategy" class="mini-combobox asLabel" required=true textField="text" 
	                  	    
	                  	   valueField="text"  
						   data="[{text:'减轻风险'},{text:'风险转移'},{text:'风险承担'},{text:'拒绝风险'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['risk_info.riskcontrolstrategy'] }" 
						   text="${requestScope['rawValue_risk_info.riskcontrolstrategy'] }" 
						   />
						   <input id="rawValue_risk_info.riskcontrolstrategy" name="rawValue_risk_info.riskcontrolstrategy" value="${requestScope['rawValue_risk_info.riskcontrolstrategy']}" class="mini-textbox" style="display:none"/>
	          </tr>
	         
	           <tr class="tr-odd">
	             <td class="td-content-title">风险控制措施：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" label="风险控制措施" required=true name="risk_info.riskcontrolmeasures"    value="${requestScope['risk_info.riskcontrolmeasures'] }" class="mini-textarea"  type="text" >  </td>
	           </tr>    
	</table>
	
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("riskreport_change_info_form");};
});	
function riskPoss(e){
	var riskPoss= mini.getbyName("risk_info.riskPoss").getValue();
	switch(riskPoss){
	case 'risk_poss.01':
		mini.getbyName("risk_info.possScore").setValue("1");
		mini.getbyName("risk_info.riskChance").setValue("年发生概率在6.25%以下");
		mini.getbyName("risk_info.riskQuency").setValue("未来5年几乎不会发生");
		break;
	case 'risk_poss.02':
		mini.getbyName("risk_info.possScore").setValue("2");
		mini.getbyName("risk_info.riskChance").setValue("年发生概率在6.25%到12.5%");
		mini.getbyName("risk_info.riskQuency").setValue("未来3到5年可能发生一次");
		break;
	case 'risk_poss.03':
		mini.getbyName("risk_info.possScore").setValue("3");
		mini.getbyName("risk_info.riskChance").setValue("年发生概率在12.5%到25%");
		mini.getbyName("risk_info.riskQuency").setValue("未来1到2年可能发生一次");
		break;
	case 'risk_poss.04':
		mini.getbyName("risk_info.possScore").setValue("4");
		mini.getbyName("risk_info.riskChance").setValue("年发生概率在25%到50%");
		mini.getbyName("risk_info.riskQuency").setValue("可能1年内发生一次");
		break;
	default :
		mini.getbyName("risk_info.possScore").setValue("5");
	    mini.getbyName("risk_info.riskChance").setValue("年发生概率在50%以上");
	    mini.getbyName("risk_info.riskQuency").setValue("1年内很可能发生一次以上");
		;
	}
	risk();
}
function riskDegree(e){
	var riskDegree= mini.getbyName("risk_info.riskDegree").getValue();
	switch(riskDegree){
	case 'risk_degree.01':
		mini.getbyName("risk_info.degreeScore").setValue("1");
		mini.getbyName("risk_info.riskQualitative").setValue("如果风险发生，对公司的财务指标、资产价值或经营目标造成的影响极小。");
		mini.getbyName("risk_info.riskRation").setValue("对公司经营目标达成影响额小于5%，或项目租 赁本息的预期损失率在5%以内。");
		break;
	case 'risk_degree.02':
		mini.getbyName("risk_info.degreeScore").setValue("2");
		mini.getbyName("risk_info.riskQualitative").setValue("如果风险发生，对对公司的财务指标、资产价值或经营目标造成的影响较小。");
		mini.getbyName("risk_info.riskRation").setValue("对公司经营目标达成影响额在 5%-10%，或项目租赁本息的预期损失率在 5%-20%之间。");
		break;
	case 'risk_degree.03':
		mini.getbyName("risk_info.degreeScore").setValue("3");
		mini.getbyName("risk_info.riskQualitative").setValue("如果风险发生，对公司的财务指标、资 产价值或经营目标造成的中度影响。");
		mini.getbyName("risk_info.riskRation").setValue("对公司经营目标达成影响额在10%-20%，或项目租赁本息的预期损失率在20%-40%之间。");
		break;
	case 'risk_degree.04':
		mini.getbyName("risk_info.degreeScore").setValue("4");
		mini.getbyName("risk_info.riskQualitative").setValue("如果风险发生，对公司的财务指标、资产价值或经营目标造成的影响较大。");
		mini.getbyName("risk_info.riskRation").setValue("对公司经营目标达成影响额在20%-30%，或项目租赁本息的预期损失率在40%-90%（含）之间。");
		break;
	default :
		mini.getbyName("risk_info.degreeScore").setValue("5");
	    mini.getbyName("risk_info.riskQualitative").setValue("如果风险发生，对公司的财务指标、资产价值或经营目标造成的影响非常大。 ");
	    mini.getbyName("risk_info.riskRation").setValue("对公司经营目标达成影响额超过30%，或项目租赁本息的预期损失率超过90%。 ");
		;
	}
	risk();
}
function risk(){
	var possScore=mini.getbyName("risk_info.possScore").getValue();
	var degreeScore=mini.getbyName("risk_info.degreeScore").getValue();
	var risklevel=parseInt(possScore)*parseInt(degreeScore);
	mini.getbyName("risk_info.risklevel").setValue(risklevel);
	if(risklevel<5&&risklevel>=1){
		mini.getbyName("risk_info.riskcategories").setValue("绿色");
	}else if(risklevel>=5&&risklevel<=10){
		mini.getbyName("risk_info.riskcategories").setValue("黄色");
	}else if(risklevel>10&&risklevel<=25){
		mini.getbyName("risk_info.riskcategories").setValue("红色");
	}
}
function onbeforeshowpopup(e) {
	miniui_ext.onbeforeshowpopup(e);
}
</script>

