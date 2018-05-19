<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%String custid=request.getParameter("custid");%>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id2',
			width : globalClientWidth,
			height : screenHeight - topHeight,
			iconCls : 'icon-node',
			frozenStartColumn:0,
			frozenEndColumn:1,
			hiddenQueryArea : true,
			frozenStartColumn : 1,
			frozenEndColumn : 3,
			renderTo:'content_table_id2',
			editFormPopupWindowWidth : 700,
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				if("edit" == operFlag){
					var row = miniTable.getSelected(); 
					mini.getbyName("contractid").setValue(row.contractid);
					mini.getbyName("contractid").setText(row.contractnumber);
				}
			},
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				miniForm.validate();
	            if (miniForm.isValid() == false) return;
				var json = miniForm.getData(true,true);
				$.ajax({
			        url: getRootPath()+"/acl/saveOverdueDunningRecord.acl",
			        type: "post",
			        cache: false, 
			        data:json,
			        async:false,
			        success: function (text) {mini.alert("保存成功！");}
			    });
				mini.get("table_id2_editFormPopupWindow").hide();
				mini.get("table_id2").load();
				return false;
			},
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord',
			pageSize:10,
			showPager:true,
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/fund/fund_overdue/overdue_dunning_record.xml',
			params:{custid:custid},
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[
                   {type:'indexcolumn'},
                   {type:'checkcolumn'} , 
                   {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				           formEditorConfig:{
					               readOnly:true,
					           fieldVisible:false}},
				   {field:'custname',header:'客户名',headerAlign:'center',width:100,visible:false,
				           formEditorConfig:{
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100,
					               readonly:true}},
			       {field: 'contractnumber', header: '业务合同号', visible: true,
				           formEditorConfig:{
					           fieldVisible:false}},
			       {field: 'contractid', header: '合同号', visible: false,
				           formEditorConfig:{
					               required:true,
					                   type:'buttonedit',
					           fieldVisible:true,
					                  width:200,
					           onbuttonclick:"buttonclick",
					              allowInput:"false",
					              labelWidth:100}},
				   {field:'contactdate',header:'联系日期',headerAlign:'center',width:100,dateFormat:"yyyy-MM-dd",
				            formEditorConfig:{
					                 newLine:true,
					                    type:'date',
					                   width:200,
					              labelWidth:100,
					              allowInput:"false",
					                  format:'yyyy-MM-dd',
					            defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd")}},
				   {field: 'rawValue_contactway', header: '联系方式', 
				            formEditorConfig:{
					            fieldVisible: false,
					       fillFromFieldName:'contactway',
					            fillProperty:'name'}},
				   {field:'contactway',header:'联系方式',visible:false,headerAlign:'center',width:100,allowSort:true,
                            formEditorConfig:{
					              labelWidth:100,
					                   width:200,
					            fieldVisible: true,
					            showNullItem:"true", 
					            nullItemText:"",
					               emptyText:"",
					                    type:'combobox',
					                  params: {pid: 'visit_mode',xmlFileName: '/combos/comboDict.xml'},
					               textField: 'name',
					              valueField: 'value'}},
				   {field:'contactperson',header:'联系人',headerAlign:'center',width:100,allowSort:true,
							            formEditorConfig:{
								                 newLine:true,
								                    type:'text',
								            fieldVisible:true,
								                   width:"100%"}},
					{field:'commitmentmoney',header:'承诺还款金额',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{ 
					                    type:'text',
					            fieldVisible:true,
				   	                   width:200,
					              labelWidth:100}},
					 {field:'commitmentdate',header:'承诺还款日',headerAlign:'center',width:100,dateFormat:"yyyy-MM-dd",
				            formEditorConfig:{
				            	     newLine:true,
				                      	type:'date',
					                   width:200,
					              labelWidth:100,
					              allowInput:"false",
					                  format:'yyyy-MM-dd'}},
					 {field:'commitmentinfo',header:'联系情况',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                 newLine:true,
					                 colspan:3,
					                    type:'textarea',
					            fieldVisible:true,
					                   width:"100%"}},
					 {field:'nextcommitmentdate',header:'下次联系日期',headerAlign:'center',width:100,dateFormat:"yyyy-MM-dd",
				            formEditorConfig:{
					                 colspan:3,
					                 newLine:true,
					                    type:'date',
					                   width:200,
					              labelWidth:100,
					              allowInput:"false",
					                  format:'yyyy-MM-dd'
				}
			}]
		});
	});
});
</script>
<div id='content_table_id2'></div>
<div id="editWindow" class="mini-window" title="选择催款员" style="width:500px;height:400px;" showModal="true" allowResize="true" allowDrag="true">
    <div id="userform">
        <table>
            <tr>
                <td style="width:100px;">业务合同号：</td>
                <td >
                    <input name="contractnumber" class="mini-textbox miniext-form-fit" onenter="search"/>
                </td>
                <td >
                    <a class="mini-button " onclick="search">查询</a>
                    <a class="mini-button " onclick="submitData">确定</a>
                </td>
            </tr>   
        </table>
    </div>
    <div class = "mini-fit">
     <div id="usergrid" class="mini-datagrid" style="width:100%;height:100%;" 
        url="${urlPrefix}/eleasing/jsp/fund/fund_overdue/get_contract_list_by_custid.xml&custid=<%=custid %>"  idField="id"
           sizeList="[5,10,20,50]" pageSize="10" onrowdblclick = "submitData"
           dataField="datas" showColumnsMenu="true" multiSelect="true">
            <div property="columns">
            	<div type="indexcolumn" width="35"></div>
                <div type="checkcolumn" width="35"></div>
                <div field="contractnumber" width="200" headerAlign="center" allowSort="true">业务合同号</div>
            </div>
        </div>  
       </div>
</div>
<script>
	function buttonclick(){
		var usergrid = mini.get("usergrid");
		var userform = new mini.Form("userform");
		var editWindow = mini.get("editWindow");
		editWindow.show();
		userform.clear();
		usergrid.load();
	}
	function search(e) {
		var usergrid = mini.get("usergrid");
		var userform = new mini.Form("userform");
		var userdata = userform.getData(true,true);
		usergrid.setParams(userdata);
		usergrid.reload();
	}
	function submitData(e){
		var editWindow = mini.get("editWindow");
		editWindow.hide();
		var usergrid = mini.get("usergrid");
		var row = usergrid.getSelecteds();
		if(row.length>0){
			var str="";
			var contractidstr="";
			for(var i =0;i<row.length;i++){
				str+=row[i].contractnumber+",";
				contractidstr+=row[i].contractid+",";
			}
			str = str.substr(0,str.length-1);
			contractidstr = contractidstr.substr(0,contractidstr.length-1);
			mini.getbyName("contractid").setText(str);
			mini.getbyName("contractid").setValue(contractidstr);
			mini.getbyName("custname").setValue(row[0].custname);
			//mini.getbyName("overduedunninginfoname").setValue(row[0].rawValue_partdept);
		}
	}
</script>