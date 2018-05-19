<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
	 var tabledate=info.tabledate;
	 if(""!=tabledate){
	    var grid=mini.get(tableid);
        grid.set({data:mini.decode(tabledate)});
	 }
     mini.get("id_minitableimport").hide();
     mini.unmask(document.body);
}


jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "five_classfy_apply_audit",
			renderTo: "id_table_five_classfy_apply_audit_detail",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			title:'',
			importOrExPortParam:{'exclid':'EXCLcheck'},//用来校验租赁物单价*数量=总价
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行
			showToolbar: showTools,
			virtualScroll:true,
			tools: ['add', '-', 'edit', '-','remove', '-', 'copy','-','exportExcel','-','importExcel'],
			data: JsonUtil.decode('${empty json_five_classfy_apply_audit_str ? "[]" : json_five_classfy_apply_audit_str }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'docid',header:'docid',visible: false},
				/* 
				项目编号projid，承租人custname，项目名称projectname，最近一次资产质量分类日期fivedate，
												最近一次资产质量分类结果contractfive_business，下一次资产质量分类日期nextfivedate，
				融资金额cleanmoney，已收回本金corpusoverage，融资余额corpus，已收回本金比例hasrate
				质押股权总价pledge_sumprice，质押股权比例pledge_ratio，股权质押评估值pledge_ratingvalue，电费质押评估值electric_ratingvalue，
				设备抵押评估值equip_ratingvalue，其他抵质押物评估值other_pledge_ratingvalue，抵/质押物评估值总额pledgeratingsumvalue，
				 最新基准利率base_rate，最新租赁利率yearrate，抵/质押率pledgerate
																				
												*/				
				{field:'projid',header:'项目编号',
					  formEditorConfig:{
						      required:true,
						    labelWidth:105,
						     maxLength:120,
						       colspan:3,
						         width:'100%'}},

				{field:'custname', header:'承租人',
									  formEditorConfig:{
										       newLine: true,
										    labelWidth:100,
										     maxLength:120,
										       colspan: 3,
										         width: '100%'}},
				{field:'projectname', header:'项目名称',align:'right',
					  formEditorConfig:{
					           newLine:true,
					              type:'text'
					            }},
				{field:'fivedate', header:'最近一次资产质量分类日期',align:'right',width:160,
									  formEditorConfig:{
									           newLine:true,
									              type:'date'
									            
									     }},
				
				{field:'fivename', header:'最近一次资产质量分类结果',align:'right',width:160,
													  formEditorConfig:{
													           newLine:true,
													              type:'text'
													            
													     
													           }},
				{field:'nextfivedate', header:'下一次资产质量分类日期',align:'right',width:160,
																	  formEditorConfig:{
																	           newLine:true,
																	              type:'date'
																	            
																	      }},									           
	           {
					field:'cleanmoney', 
					dataType:"currency",
					header:'融资金额(RMB)',
					align:'right',
					summary:true,				  
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         newLine:true
					},		  
				},
				{
					field:'corpusoverage', 
					header:'已收回本金(RMB)',
					dataType:"currency",
					align:'right',
					summary:true,				  
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         labelWidth:100,
					},		  
				},
				{field:'corpus', header:'融资余额(RMB)',align:'right',summary:true,
					dataType:"currency",
					  formEditorConfig:{
					        	  type:'text',
						         vtype:'float',
						         newLine:true,
							  readOnly: true}},
				{field: 'hasrate', header: '已收回本金比例',
					      formEditorConfig:{
							       colspan:3,      
						         maxLength:120}},
						         
				{field:'pledge_sumprice', 
										dataType:"currency",
										header:'质押股权总价(RMB)',
										align:'right',
										summary:true,
										width:160,
										formEditorConfig:{
								        	  type:'text',
									         vtype:'float',
									         newLine:true
										},		  
									},
				{field: 'pledge_ratio', header: '质押股权比例',
									      formEditorConfig:{
											       colspan:3,      
										         maxLength:120}},
				//质押股权总价，质押股权比例，股权质押评估值，电费质押评估值，设备抵押评估值，其他抵质押物评估值，抵/质押物评估值总额，最新基准利率，最新租赁利率，抵/质押率
				
		/* {field: 'vndr', header: '供应商', visible: false,
				  formEditorConfig:{
			               newLine:true,
			               colspan: 3,
			                 width: '100%',
			                  type: 'queryinput',
			             textField: 'name',
			            valueField: 'value',
			            allowInput: false,
			          fieldVisible: true,
		                 	params: {cust_type: 'cust_type.vndr',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}}, */
				
			   {
					field:'pledge_ratingvalue', 
					header:'股权质押评估值',
					summary:true,				  
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         newLine:true
					},	
				},
				 {
					field:'electric_ratingvalue', 
					header:'电费质押评估值',
					summary:true,				  
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         newLine:true
					},	
				},
				 {
					field:'equip_ratingvalue', 
					header:'设备抵押评估值',
					summary:true,
					width:160,
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         newLine:true
					},	
				},
				{
					field:'other_pledge_ratingvalue', 
					header:'其他抵质押物评估值',
					summary:true,
					width:160,
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         newLine:true
					},		  
				},
				
				{field:'pledgeratingsumvalue', header:'抵/质押物评估值总额',
					summary:true,
					width:160,
					formEditorConfig:{
			        	  type:'text',
				         vtype:'float',
				         newLine:true
					},	
					},				
				{field:'base_rate', header:'最新基准利率(%)',
					      formEditorConfig:{   
							     maxLength:120}},
				
				{field:'yearrate',header:'最新租赁利率(%)',
					      formEditorConfig:{
					             maxLength:120,
					               newLine:true,
					               colspan:3,
					                height:70,
					                 width:'100%'}},
				{field:'pledgerate',header:'抵/质押率(%)',
					      formEditorConfig:{
					             maxLength:500,
					               newLine:true,
					                  type:'textarea',
					               colspan:3,
					                height:70,
					                 width:'100%'
				}},
				
			]
		});
	});
});


</script>
<div id="id_table_five_classfy_apply_audit_detail"></div>