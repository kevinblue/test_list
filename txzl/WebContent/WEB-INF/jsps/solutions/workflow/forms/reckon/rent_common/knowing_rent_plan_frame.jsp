<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "condition_frame1",
			renderTo: "content_table_id10",
			width: 675,
			height: 330,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			sortMode : "client",
			tools: ['add', '-', 'edit', '-', 'remove',
			      	{html:'保存并关闭',plain:true,iconCls:'icon-save',
	                	handler:function(miniTable,buttonText){
	                		var rentOrRate = $minigetvalue("rentorrate");
	                		var periodType = $minigetvalue("periodtype");
	                		if("knowing_rent" == rentOrRate){
	                			//下拉如何取值？
	                			var leaseAmt = getNumber("cleanleasemoney"); //融资额
	                			if(leaseAmt <= 0){
	                				mini.alert("融资额为0无法计算年租息率!");
	                				return;
	                			}
	                			
	                			var knowingRent = miniTable.getData();
	                			var incomeNumber = checkKnowingRentList(knowingRent);
	                			if(incomeNumber == 0){
	                				jQuery("#id_json_knowing_rent_plan_str").val("[]");
	                				return;
	                			} else {
	                				$mini("incomenumber").setValue(incomeNumber); //填充还租次数
	                				$mini("grace").setValue(0); //宽限期设置为0
	                				//onInput();
	                			}
	                			//月  付	income_1 , 季  付	income_3,半年付	income_6,年  付	income_12,双月付	income_2
	                			var incomeNumberYear = $minigetvalue("incomenumberyear");//租金支付类型
	                			incomeNumberYear = incomeNumberYear.replace('income_', '');
	                			incomeNumberYear = 12 / incomeNumberYear;
	                			
	                			//var tempAttr = $("#id_json_knowing_rent_plan_str").val();
	                			
	                			//tableJsonData.sort(by("startrentlist"));
	                			knowingRent.sort(function(a,b){return a.startrentlist - b.startrentlist;});//排序
	                			var tableJsonData = mini.encode(knowingRent);
	                			//$("input[name='']").attr('value',tableJsonData);
	                			$("#id_json_knowing_rent_plan_str").val(tableJsonData);
	                			mini.mask({
	                				el: document.body,
	                				cls: 'mini-mask-loading',
	                				html: '正在计算年利率，请稍后...'
	                			});
	                			$.ajax({
	                		        url: "${pageContext.request.contextPath}/leasing/knowRentCalculate.action",
	                		        type: "post",
	                		        data:  {
	                					periodType : periodType,
	                					leaseAmt : leaseAmt,
	                					incomeNumberYear : incomeNumberYear,
	                					endRentList : incomeNumber,
	                					tempAttr : tableJsonData
	                				} ,
	                		        success: function (res) {
	                		        	if (res != '') {
	                		        		res = res.replace(/(^\s+)|(\s+$)/g, ""); 
	                			     	    if(res != '') {
	                				    	 	mini.unmask(document.body);
	                			     	    	mini.alert('年利率测算成功，请点击租金测算按钮测算内部收益率');
	                				    	 	$mini("yearrate").setValue(res);
	                				    	 	$miniSetCombValue("settlemethod","irregular_rent","不规则");
	                				    	 	//changeRentPlanTableButton('a');
	                			     	    }
	                			     	}
	                		        },
	                		        error: function (jqXHR, textStatus, errorThrown) {
	                		            alert(jqXHR.responseText);
	                		            mini.unmask(document.body);
	                		        }
	                		    });
	                		}
	                		var editWindow = mini.get("editWindow");
	            			editWindow.hide();
	            			changeRentPlanTableButton('a');
				        }
					}
			],
			data: mini.decode('${empty json_knowing_rent_plan_str ? "[]" : json_knowing_rent_plan_str }'),
			addOperCallBack :function(miniTable,rowData){
				//miniTable.sortBy("startrentlist"); 
				var rowDatas = miniTable.getData();
				rowDatas.sort(function(a,b){return a.startrentlist - b.startrentlist;});
				miniTable.setData(rowDatas);
			},
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				if("add" == operFlag){
					var startrentlist = mini.getbyName("startrentlist");
					var row = miniTable.getRow(miniTable.getData().length-1);
					if(null != row && row != 'undifined'){
						endrentlist = row.endrentlist;
						startrentlist.setValue(parseInt(endrentlist)+1);
					}else{
						startrentlist.setValue(1);
					}
					startrentlist.setEnabled(false);
				}
			},
			completeCallBack: function(miniTable){
				var rowDatas = miniTable.getData();
				rowDatas.sort(function(a,b){return a.startrentlist - b.startrentlist;});
				miniTable.setData(rowDatas);
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'startrentlist', header: '开始期次',
					formEditorConfig:
					{
						newLine :true,
						required: true,
						labelWidth:100,
						maxLength:120,
						colspan: 3,
						width: 345,
						readonly :true
					}
				},
				{field: 'endrentlist', header: '截止期次',
					formEditorConfig:
					{
						newLine :true,
						required: true,
						labelWidth:100,
						maxLength:120,
						colspan: 3,
						width: 345
					}
				},
				{field: 'rent', header: '租金',
					dataType:"currency",
					formEditorConfig:
					{
						newLine :true,
						required: true,
						labelWidth:100,
						maxLength:120,
						colspan: 3,
						width: 345,
						type : 'thousand'
					}
				}
			]
		});
	});
})
</script>
 <div id='content_table_id10'></div>