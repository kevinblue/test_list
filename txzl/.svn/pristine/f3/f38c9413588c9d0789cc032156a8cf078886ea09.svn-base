<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};;
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "current_regulating_of_breathing",
			renderTo: "id_current_regulating_of_breathing",
			width: globalClientWidth - 50,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: true,
			pageSize : 500,//每页显示几条数据
			showToolbar: showTools,
			multiSelect: true,
			title:'本次调息',
			xmlFileName:'/eleasing/workflow/rent/regulating_breathing_before/current_regulating_breathing_list_new.xml',
			params:{ adjustid:"${requestScope['fund_standard_interest.id'] }",docid:flowUnid,Central_Bank_id:'${empty Central_Bank_id ? "[]" : Central_Bank_id }'},
			tools : [
							{
								html : '撤销',
								plain : true,
								iconCls : 'icon-addfolder',
								handler : function(miniTable, buttonText) {
									var rowDatas = miniTable.getSelecteds();
					        		 if(rowDatas.length == 0){
					        			 jQuery.messager.alert('错误提示',"<div style='font-size:18px;line-height:30px;'>请选择需要调息的合同</div>",'error');
					        		 }else {
					        			 revocationTX(rowDatas);
					        		 }
								
								}
							}         
						],
		     columns:[
						{type:'indexcolumn'}, 
						{type:'checkcolumn'},
						{header:'id',field:'id', visible:false},
						{header:'faictid',field:'faictid', visible:false},
						{header:'proj_id',field:'proj_id', visible:false},
						{header:'custid',field:'custid', visible:false},
						{header:'docid',field:'docid', visible:false},
						{header:'合同号',field:'contractid',queryConfig:{}},
						{header:'业务合同号',field:'contractnumber',queryConfig:{}},
						{header:'客户名',field:'custname',queryConfig:{}},		
						{header:'设备款',field:'equipamt',queryConfig:{}},
						{header:'首付款',field:'firstpayment'},
						{header:'利率浮动类型',field:'ratefloattypename'},
						{header:'调息生效节点',field:'adjuststylename'},					
						{header:'还租次数',field:'incomenumber'},
						{header:'付租类型',field:'incomenumberyear'},
						{header:'付租方式',field:'periodtype'},
						{header:'起租日期',field:'startdate'},
						/* {header:'利率',field:'yearrate'}, */
						{header:'调息前利率',field:'rateoriginal'},
						{header:'调息后利率',field:'rateadjust'},
						{header:'调息前内部收益率',field:'oldirr'},
						{header:'调息后内部收益率',field:'newirr'},
					    {header:'操作',align:'center',width:120,
									renderer:function(e){
									var  table = e.record;
					                return "<a href='#' onclick='previewhis("+JsonUtil.encode(table)+")'>对比</a>";}}

	        ]
		});});	});
function previewhis(data){
 
   var URL = "${pageContext.request.contextPath}/leasing/query/condition_temp/interestTempCompareBefore.bi";
   
   var params = {cid:data.id,contractid:data.contractid,docid:data.docid,read_only :false};
   openFullScreenWindow(URL,params);
}

//发Ajax到后台调用调息撤销acl
function revocationTX(rowDatas){
	if(confirm("确认对选中合同进行撤销?")){
		isContinue=true;
		c=rowDatas.length;
		jQuery("#msgButtonText").html("停止请求");//设置控制按钮
		shwoMessageWindwos();//显示操作信息层
		for(var i=0;i<rowDatas.length&&isContinue;i++){
			showMsg(rowDatas[i]["contractnumber"]+"开始请求撤销调息!");
			doSendTXCX(rowDatas[i]["id"],rowDatas[i]["contractnumber"]);//调用调息ajax
		}
	}
}
function doSendTXCX(cid,contractNumber){
	ajaxRequest({
	     url:"${pageContext.request.contextPath}/leasing/acl/removeAdjustinterest.acl",
	     params:{adjustid:"${requestScope['fund_standard_interest.id'] }",docid:flowUnid,contractid:cid},
	     timeout:30*1000,
	     async:true,//改为同步请求
	     success:function(res){
	 		res=res.responseText;
	 		res=res.replace(/(^\s+)|(\s+$)/g, ""); 
	 		showMsg(contractNumber+"撤销调息请求成功!反馈如下["+res+"]");
	 		txcxBackFun(cid);
	     },
	     failure:function(res){
	    	res=res.responseText;
	 		res=res.replace(/(^\s+)|(\s+$)/g, "");
	 		showMsg("<strong id='red'>"+contractNumber+"撤销调息请求失败!反馈如下["+res+"]</strong>");
	 		txcxBackFun(cid);
	  	 }
	});
}
function txcxBackFun(id){
	c--;
	if(c<=0){
		isContinue=false;
		jQuery("#msgButtonText").html("关闭");//设置控制按钮
	}
	if(id){
		var ids=jQuery("#id_adjust_contractids").val();
		ids=ids.replace(id,"").replace(",,",",");
		if(ids.indexOf(",")==0){
			ids=ids.substring(1);
		}
		if(ids.indexOf(",")==ids.length-1){
			ids=ids.substring(0,ids.length-1);
		}
		jQuery("#id_adjust_contractids").val(ids);
	}
}
</script>
<div id="id_current_regulating_of_breathing"></div>