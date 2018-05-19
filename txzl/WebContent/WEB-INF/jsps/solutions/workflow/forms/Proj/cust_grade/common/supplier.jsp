<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript">
	var toolsArray = [];
 	/* <permission:action code="ports">  */
	toolsArray.push(/* 'edit', '-',  */'importExcel');/*'-','exportExcel', '-' , {
		html : '模板下载',
		plain : true,
		iconCls : 'icon-addfolder',
		handler : function(miniTable, buttonText) {
			var fileTeplate = new fileTemplateConfig({
				templateno : 'F-201411002',
				tableid : miniTable.config.id,
				modelname : miniTable.config.title,
				returntype : 'file',
				parames : {}
			});
			fileTeplate.createFile();
		}
	},'-', 'remove'); */
/* 	</permission:action>  */
	
	function importMiniTableCallBack(message, tableid) {
		var info = eval("(" + message + ")");
			mini.alert(info.message);
			mini.get("id_minitableimport").hide();
			mini.unmask(document.body);
			mini.get(tableid).reload(); 
			var score=document.getElementById("Score");
			gradeToSupplier();
	}
	jQuery(function() {
		var custid = "${requestScope['custInfoTojsp']}";
		seajs.use(	[ "js/apcomponent/aptable/aptable.js" ],
			function(ApTable) 
			   {new ApTable(
					{
										//导入Excel配置
									    //importOrExPortParam:{'vatInvoiceContract':'vatInvoiceContract'},//万瑞用来校验发票登记自定义的方法
		                                importTargetClass:'com.tenwa.leasing.entity.cust.Supplier',
										/* importOrExPortCallBack : 'checkFundEbankDataTX',//回调方法用来校验对象属性 */
										importDataIndex : '4',
										importHeaderIndex : '3',
										id: "supplier",
										renderTo: "id_table_supplier",
										width : globalClientWidth,
										height : globalClientHeight,
										multiSelect : true,
										editFormPopupWindowWidth : 700,
										editFormPopupWindowHeight : 450,
										hiddenQueryArea : false,
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										xmlFileName : 'eleasing/jsp/cust_info/cust_grade/supplier_grade.xml',
										tools : toolsArray,
										remoteOper : true,
										lazyLoad : false,
										pageSize:700,
										showPager : false,
									/* 	completeCallBack: function(miniTable){
											
										}, */
										loadMode : 'ajax',
										entityClassName : 'com.tenwa.leasing.entity.cust.Supplier',
										params:{"custid":custid,"sgradeid":flowUnid},
										columns : [
												{field : 'id',header:'id',visible : false},
												{field:'num',header:'序号'}, 
												{field:'target',header:'指标'},
												{field:'scoreweight',header:'分值权重'},
												{field:'type',header:'类别'},
												{field:'responsibledept',header:'负责的部门'},	
												{field:'process',header:'取值'},	
												{field:'score',header:'得分'},	
												{field : 'total',header : 'total', visible : false},
												]
									});

						});
		 $(function(){
				var sumtable=0;
				 var cresult="";
				var allscore=(Number)(mini.get("Score").getValue());
		 		/* allscore.setValue(""+sumtable);  */
				if(allscore==""){cresult="";}
					else if(allscore>=75){cresult="AA";}
				    else if(allscore>=50){cresult="A";}
				    else if(allscore>=25){cresult="B";}
				    else if(allscore>=0){cresult="C";} 
		 	 mini.get("cust_grade.grade_result").setValue(cresult);
		 });
	});
	
	function gradeToSupplier(){
		var data=mini.get("supplier").getData();
		var sumtable=0;
		if(data.length>0){
			for(var i=0;i<data.length-1;i++){
				//console.info("===="+data[i].score)
				var a=(Number)(data[i].score);
				sumtable+=a;
			}
		}
			var cresult="";
			var allscore=mini.get("Score");
			allscore.setValue(""+sumtable);
			if(sumtable==""){cresult="";}
				else if(sumtable>=75){cresult="AA";}
			    else if(sumtable>=50){cresult="A";}
			    else if(sumtable>=25){cresult="B";}
			    else if(sumtable>=0){cresult="C";} 
	 	 mini.get("cust_grade.grade_result").setValue(cresult);
	 }
</script>
<div id="id_table_supplier"></div>