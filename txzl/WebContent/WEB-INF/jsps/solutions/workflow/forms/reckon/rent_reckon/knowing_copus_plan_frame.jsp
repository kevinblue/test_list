<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
	new ApTable({
		id: "knowing_corpus",
		renderTo: "id_konwingcorpus_containter",
		width: 675,
		height: 330,
		lazyLoad: true,
		title: "",
		isClickLoad:false,
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		sortMode : "client",
		tools: ['add', '-', 'edit', '-', 'remove',
		      	{html:'保存并关闭',plain:true,iconCls:'icon-save',
                	handler:function(miniTable,buttonText){
                		//校验融资额和本金之和是否相同
               			var knowingCorpus = miniTable.getData();
                		var leaseMoney = getNumber('cleanleasemoney');
                		var copusTotal = 0;
                		for(var i = 0 ; i < knowingCorpus.length ; i++){
                			var corpuss = knowingCorpus[i].corpus;
                			var list = Number(knowingCorpus[i].endcorpuslist) -  Number(knowingCorpus[i].startcorpuslist) +1 ;
                			copusTotal = decimal(copusTotal+Number(corpuss)*list ,2);
                		}
                		if(leaseMoney != copusTotal){
                			mini.alert('本金之和需等于净融资额，请核对后在提交！');
                			return;
                		}
                		knowingCorpus.sort(function(a,b){return a.startcorpuslist - b.startcorpuslist;});//排序
                		var incomeNumber = checkKnowingCorpusList(knowingCorpus);
            			if(incomeNumber == 0){
            				jQuery("#id_json_knowing_corpus_plan_str").val("[]");
            				return;
            			} else {
            				$mini("incomenumber").setValue(incomeNumber); //填充还租次数
            				var incomeNumberYear = $minigetvalue("incomenumberyear");//租金支付类型
                			incomeNumberYear = incomeNumberYear.replace('income_', '');
                			$mini("leaseterm").setValue(decimal(Number(incomeNumber) *　Number(incomeNumberYear),0));
            				$mini("grace").setValue(0); //宽限期设置为0
            				//onInput();
            			}
               			var tableJsonData = mini.encode(knowingCorpus);
               			jQuery("#id_json_knowing_corpus_plan_str").val(tableJsonData);
                		var editWindow_knowing_corpus = mini.get("editWindow_knowing_corpus");
            			editWindow_knowing_corpus.hide();
			        }
				}
		],
		data: mini.decode('${empty json_knowing_corpus_plan_str ? "[]" : json_knowing_corpus_plan_str }'),
		addOperCallBack :function(miniTable,rowData){
			var rowDatas = miniTable.getData();
			rowDatas.sort(function(a,b){return a.startcorpuslist - b.startcorpuslist;});
			miniTable.setData(rowDatas);
		},
		afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
			if("add" == operFlag){
				var startrentlist = mini.getbyName("startcorpuslist");
				var row = miniTable.getRow(miniTable.getData().length-1);
				if(null != row){
					endcorpuslist = row.endcorpuslist;
					startrentlist.setValue(parseInt(endcorpuslist)+1);
				}else{
					startrentlist.setValue(1);
				}
				startrentlist.setEnabled(false);
			}
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'startcorpuslist', header: '开始期次',
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
			{field: 'endcorpuslist', header: '截止期次',
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
			{field: 'corpus', header: '本金',
				formEditorConfig:
				{
					newLine :true,
					required: true,
					labelWidth:100,
					maxLength:120,
					colspan: 3,
					width: 345
				}
			}
		]
	});
});
})
</script>
 <div id='id_konwingcorpus_containter'></div>