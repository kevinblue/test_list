<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@include file="cust_comn.jsp"%>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<%String planid=request.getParameter("planid");
String projid=request.getParameter("projid");
%>
<script type="text/javascript">
var planid = "<%= planid %>";
var projid = "<%= projid %>";
jQuery(function() {
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false;}
	if('${param.isShow}' == 'true'){showTools = true;}
		tenwa.createTable({
			id:'table_id1x',
			width:globalClientWidth,
			height:300,
			iconCls:'icon-node',
			renderTo:'content_table_id1x',
			title:'联系人信息',
			frozenStartColumn:0,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			remoteOper:true,
			editRemoteOperUrl:getRootPath()+"/acl/updateCustRelatedPersonHis.acl",
			entityClassName:'com.tenwa.leasing.entity.cust.CustRelatedPerson',			
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			showToolbar: showTools,
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				if(checkIsPersonMain()=="repeat") return;
				if(checkCardNoPersonMain()=="repeat") return;
				return true;
			},
			updateOperCallBack:function(miniTable,rowData){
				mini.get("table_id1x").deselectAll(false);
			},
			xmlFileName:'/eleasing/jsp/cust_info/cust_relatedperson/cust_relatedperson_list.xml',
			operValidate:function(miniTable,rowData,oper){
				var deptobj = "<%=session.getAttribute("userDeptListStr")%>";
				/* if("edit"==oper||"remove"==oper){
					var checkRowDatas = miniTable.getSelecteds();
					if(checkRowDatas && checkRowDatas.length > 0){
						var rowData = checkRowDatas[0];
						//4028805e5261d9e001526206b4630066资产管理部
						if(rowData.onhirenum > '0' && deptobj.indexOf('4028805e5261d9e001526206b4630066')<0){
							mini.alert("该承租人有在起租的合同，请联系资产管理部进行修改");
							return false;
						}
					}
				} */
				return true;
			},
			params:{custid :custid},
			tools:[ {
				html:'新增催收记录',
				plain : true,
				handler : function(miniTable, buttonText){
					var row = miniTable.getSelected();
					if(row == null){
						mini.alert("请选中一条记录！");
					}else{
						var multiform = new mini.Form("overduedunningcored");
						var multieditWindow = mini.get("editWindow");
						multieditWindow.show();
						multiform.clear();
						isMulti = true;
						mini.get("custname").setValue(row.personname);
						mini.get("custid").setValue(row.id);
						mini.get("planid").setValue(planid);
					}
				}
			},'-','add', '-', 'edit','-',{
				html:'项目总表',
				plain : true,
				handler : function(miniTable, buttonText){
			          if(projid!=null||projid!=""||projid.length>0){
			        		 var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+projid;
							    openFullScreenWindow(URL);  
			          }else{
			        	  
			        	  mini.alert("该合同没有总表信息！");
			          }
				
				}
			}],
			columns:[
					 {type:'indexcolumn'},
					 {type:'checkcolumn'}, 
					 {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
                              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false }},
					 {field:'onhirenum',header:'起租合同数',headerAlign:'center',width:100,allowSort:true,visible:false,
                              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false }},
					 {field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false, 
					                     value:custid}},
					 {field:'personname',header:'重要个人名称',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                      type:'text',
					         fillFromFieldName:'fatherid',
					              fillProperty:'id',
					              fieldVisible:true,
					                  required:true,
					                     width:200,
					                labelWidth:100}},
					 {field:'sex',header:'性别',visible:true,headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                      type:'radiobuttonlist',
					               repeatItems: "0",
					              repeatLayout: "table",
					           repeatDirection: "vertical",
					                valueField:'text',
					                 textField:'text',
					                      data:[{text:'男'},{text:'女'}]}},
					  {field:'duties',header:'职务',headerAlign:'center',width:100,allowSort:true,
								formEditorConfig:{
									     width:200,
									     newLine:true,
									     type:'text',
									     fieldVisible:true}},
					  {field:'mobilenumber',header:'手机',headerAlign:'center',width:100,allowSort:true,
								formEditorConfig:{
									     width:200,
									     type:'text',
									     fieldVisible:true,
									     onvalidation:"onMoblieValidation"}},
					  {field:'phone',header:'电话',headerAlign:'center',width:100,allowSort:true,
								 formEditorConfig:{
									      width:200,
									      newLine:true,
									      type:'text',
									      fieldVisible:true}},
					  {field:'mainpersonflag',header:'是否主联系人',visible:true,headerAlign:'center',width:100,allowSort:true,
								 formEditorConfig:{
									     width:200,
									     showNullItem:"true", 
									     nullItemText:"",
									     emptyText:"",
									     type:'combobox',
									     valueField:'text',
									     textField:'text',
									     data:[{text:'是'},{text:'否'}]}},
				     /*  {field:'sendperson',header:'是否寄件人',visible:true,headerAlign:'center',width:100,allowSort:true,visible:false,
							     formEditorConfig:{
							            newLine:true,
					            	  	required:true,
								        width:200,
								        showNullItem:"true", 
								        nullItemText:"",
								        emptyText:"",
								        type:'combobox',
								        valueField:'text',
								        textField:'text',
								        data:[{text:'是'},{text:'否'}]}}, */
					  {field:'mailadd',header:'邮寄地址',headerAlign:'center',width:100,allowSort:true,
								  formEditorConfig:{
									     newLine:true,
									     width:"100%",
									     colspan:3,
									     type:'text',
									     newLine:true,
									     fieldVisible:true}},
					  {field:'idcardno',header:'身份证号码',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   newLine:true,
					                      type:'text',
					         fillFromFieldName:'fatherid',
					              fillProperty:'id',
					              fieldVisible:true,
					              onvalidation:"onIDCardsValidation",
					                     width:200,
					                labelWidth:100}},
					 {field:'birthdate',header:'出生年月',headerAlign:'center',width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
				              formEditorConfig:{
					                      type:'date',
					                allowInput:"false",
					                    format:'yyyy-MM-dd',
					                     width:200}},
			         {field: 'rawValue_relationship', header: '关系', 
				              formEditorConfig:{
					              fieldVisible: false,
					         fillFromFieldName:'relationship',
					              fillProperty:'name'}},
					 {field:'relationship',header:'关系',visible:false,headerAlign:'center',width:100,allowSort:true,
                              formEditorConfig:{
					                     width:200,
					                   newLine:true,
					              fieldVisible: true,
					              showNullItem:"true", 
					              nullItemText:"",
					                 emptyText:"",
					                      type:'combobox',
					                    params: {pid: 'relationship',xmlFileName: '/combos/comboDict.xml'},
					                 textField:'name',
					                valueField:'value'}},
					 {field:'postcode',header:'邮编',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   
					                     width:200,
					                      type:'text',
					              fieldVisible:true,
					              onvalidation:"onPostcodeValidation"}},
					  {field:'email',header:'email',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
				            	      newLine:true,
					                     width:200,
					                      type:'text',
					              fieldVisible:true,
					                     vtype:"email"}},
					                     /*
			         {field: 'rawValue_unitposition', header: '职务', visible:false,
				              formEditorConfig:{
					              fieldVisible: false,
					         fillFromFieldName:'unitposition',
					              fillProperty:'name'}},
					 {field:'unitposition',header:'职务',visible:false,headerAlign:'center',width:100,allowSort:true,
				           	  formEditorConfig:{
				           		fieldVisible: false,
					                     width:200,
					                   newLine:true,
					              fieldVisible: true,
					              showNullItem:"true", 
					              nullItemText:"",
					                 emptyText:"",
					                      type:'combobox',
					                    params: {pid: 'cust_jobposition',xmlFileName: '/combos/comboDict.xml'},
					                 textField: 'name',
					                valueField: 'value'}},
					                     */
					 {field:'servicelife',header:'任职年限',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                      type:'text',
					              fieldVisible:true}},
					 {field:'domicileplace',header:'户口所在地',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   colspan: 3,
				 	                   newLine:true,
					                     width:"100%",
				 	                      type:'text',
				 	              fieldVisible:true}},
				 	 {field:'homeadd',header:'常住地址',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   colspan: 3,
					                   newLine:true,
					                     width:"100%",
					                      type:'text',
					              fieldVisible:true}},
					 {field:'cpmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:"100%",
					                   newLine:true,
					                   colspan: 3,
					                      type:'textarea',
					              fieldVisible:true}},										
			         {field: 'creatorname',header:'登记人',	headerAlign:'center',width:100,allowSort:true,				
				              formEditorConfig:{
					                   newLine:true,
					                      type:'text',
					                  readOnly:true,
					                     value:'${sessionScope.loginUser.realname}',
					                labelWidth:100,								
					                     width:200}},						
			         {field: 'createdate',header:'登记时间',	headerAlign:'center', dateFormat:"yyyy-MM-dd ",width:100,allowSort:true, 							
				              formEditorConfig:{								
					                      type:'text',
					                  readOnly:true,		
					              defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
					                labelWidth:100,								
					                     width:200 }},
			        {field: 'modificatorname',header:'修改人',	headerAlign:'center' ,width:100,visible:true,allowSort:true,
				              formEditorConfig:{
					                   newLine:true,
					                      type:'text',
					                  readOnly:true,
					              fieldVisible:true,
					                labelWidth:100,								
					                     width:200}},
			       {field: 'modifydate',header:'修改时间',	headerAlign:'center', visible:true,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
				              formEditorConfig:{									
					                      type:'text',
					                  readOnly:true,
					              fieldVisible:true,
					                labelWidth:100,								
					                     width:200 								
					} 				   		
			}]
		});
});
//身份证校验
function onIDCardsValidation(e) {
	var sId = e.value||"";
	 var isValid = cidInfo(sId);
	 if(!(true == isValid)){
		e.errorText = isValid;
		e.isValid = false;
	}
	if(sId!=""){
	mini.get("table_id1x_editFormPopupWindow_form_birthdate").setValue(idcardStr.brithdate);
	mini.get("table_id1x_editFormPopupWindow_form_sex").setValue(idcardStr.sex);
	}
	return true;
}
function checkIsPersonMain(){
	var mainpersonflagvalue = getMiniEditFormField("table_id1x.mainpersonflag").getValue();//mini.getbyName("mainpersonflag").getValue();
	if("" == mainpersonflagvalue || "否" == mainpersonflagvalue)return "";
	var idvalue = getMiniEditFormField("table_id1x.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkIsPersonMain.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid},
        async:false,
        success: function (text) {
        	if("repeat" == text){
        		mini.alert("主联系人只能有一个！");
        	}
        	str = text;
        }
    });
	return str;
}
function checkCardNoPersonMain(){
	var cardNo = getMiniEditFormField("table_id1x.idcardno").getValue();//mini.getbyName("mainpersonflag").getValue();
	if("" == cardNo)return "";
	var idvalue = getMiniEditFormField("table_id1x.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkCardNoPersonMain.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid,"cardNo":cardNo},
        async:false,
        success: function (text) {
        	if("repeat" == text){
        		mini.alert("身份证号码不能重复！");
        	}
        	str = text;
        }
    });
	return str;
}
function add(){
	var planid=mini.get("planid").getValue();
	var cuid=mini.get("custid").getValue();
	var contactway=mini.get("contactWay").getValue();
	var commitmentinfo=mini.get("commitmentInfo").getValue();
	if(contactway == ""){
		mini.alert("请选择联系方式！");
		return ;
	}
	$.ajax({
	       url: getRootPath()+"/acl/addsOverdueDunningRecord.acl",
	       type: "post",
	       cache: false, 
	       data :{ "custid":cuid ,"planid":planid,"contactway":contactway,"commitmentinfo":commitmentinfo},
	       async:false,
	       success: function (text) {
	        	mini.alert("保存成功！");
			
	       }
	   });
	mini.get("editWindow").hide();
	mini.get("table_id1").reload();		

}

</script>
<div id='content_table_id1x'></div>
<div id="editWindow" class="mini-window" title="催收登记" style="width:300px;height:200px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="overduedunningcored">
		<table>
			<tr style="display:none">
				<td style="width:100px;">租金计划id：</td>
                <td >
                    <input name="planid" id="planid" class="mini-textbox" readOnly/>
                </td>
             </tr>
			<tr style="display:none">
				<td style="width:100px;">联系人id：</td>
                <td >
                    <input name="custid" id="custid" class="mini-textbox" readOnly/>
                </td>
             </tr>
             <tr>
				<td style="width:100px;">联系人姓名：</td>
                <td >
                    <input name="custname" id="custname" class="mini-textbox" readOnly/>
                </td>
			</tr>
			<tr>
				<td style="width:100px;">联系方式：</td>
				<td>
					<input id="contactWay" name="contactWay" 
	                  	 class="mini-combobox miniext-form-fit" 
							   dataField="datas"
								textField="name"
								valueField="value"
								showNullItem="true"
							   allowInput="false" 
							   data-options="{_params:{pid:'visit_mode',_xmlFileName:'combos/comboDict.xml'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
	                    onEnter="search"/>
				</td>
			</tr>
			<tr>
				<td style="width:100px;">联系情况：</td>
				<td><input name="commitmentInfo" id="commitmentInfo" class="mini-textarea  asLabel" width="95%"/></td>
			</tr>
			<tr>
		 	<td colspan="2" style="border-bottom: 1px solid #99CCFF;" align="center" >
			   <a class="mini-button" iconCls="icon-edit" onclick="add">保存</a>
		    </td>
		</tr>
		</table>
	</div>
</div>