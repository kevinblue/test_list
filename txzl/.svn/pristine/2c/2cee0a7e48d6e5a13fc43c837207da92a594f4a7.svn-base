<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../cust_comm/cust_comm.jsp"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('${param.isView}' == 'true'){
		showTools = false;
	}
		tenwa.createTable({
			id:'table_hisd',
			width:globalClientWidth,
			height:420,
			iconCls:'icon-node',
			renderTo:'content_table_table_his',
			frozenStartColumn:0,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.cust.CustRelatedPersonHis',			
			pageSize:15,
			showPager:true,
		//	lazyLoad:false,
			xmlFileName:'/eleasing/jsp/cust_info/cust_relatedperson/cust_relatedperson_list_his.xml',
			params:{custid :custid},
			columns:[
					 {type:'indexcolumn'},
					 {type:'checkcolumn'}, 
					 {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false},
					 {field:'onhirenum',header:'起租合同数',headerAlign:'center',width:100,allowSort:true,visible:false},
					 {field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false},
					 {field:'personname',header:'重要个人名称',headerAlign:'center',width:100,allowSort:true},
					 {field:'sex',header:'性别',visible:true,headerAlign:'center',width:100,allowSort:true},
					 {field:'idcardno',header:'身份证号码',headerAlign:'center',width:100,allowSort:true},
					 {field:'birthdate',header:'出生年月',headerAlign:'center',width:100,allowSort:true,dateFormat:"yyyy-MM-dd"},
			         {field: 'rawValue_relationship', header: '关系'},
					 {field:'relationship',header:'关系',visible:false,headerAlign:'center',width:100,allowSort:true},
					 {field:'mainpersonflag',header:'是否主联系人',visible:true,headerAlign:'center',width:100,allowSort:true},
					  {field:'mailadd',header:'邮寄地址',headerAlign:'center',width:100,allowSort:true},
					  {field:'postcode',header:'邮编',headerAlign:'center',width:100,allowSort:true},
					  {field:'mobilenumber',header:'手机',headerAlign:'center',width:100,allowSort:true},
					  {field:'phone',header:'电话',headerAlign:'center',width:100,allowSort:true},
					 {field:'email',header:'email',headerAlign:'center',width:100,allowSort:true},
			         {field: 'rawValue_unitposition', header: '职务'},
					 {field:'unitposition',header:'职务',visible:false,headerAlign:'center',width:100,allowSort:true},
					 {field:'servicelife',header:'任职年限',headerAlign:'center',width:100,allowSort:true},
					 {field:'domicileplace',header:'户口所在地',headerAlign:'center',width:100,allowSort:true},
				 	 {field:'homeadd',header:'常住地址',headerAlign:'center',width:100,allowSort:true},
					 {field:'cpmemo',header:'备注',headerAlign:'center',width:100,allowSort:true},										
			         {field: 'creatorname',header:'登记人',	headerAlign:'center',width:100,allowSort:true},						
			         {field: 'createdate',header:'登记时间',	headerAlign:'center', dateFormat:"yyyy-MM-dd ",width:100,allowSort:true},
			        {field: 'modificatorname',header:'修改人',	headerAlign:'center' ,width:100,visible:true,allowSort:true},
			       {field: 'modifydate',header:'修改时间',	headerAlign:'center', visible:true,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true}
			        
			        ]
		});
});



</script>
<div id='content_table_table_his'></div>