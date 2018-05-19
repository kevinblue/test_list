<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};;
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:"todo_regulating_of_breathing",
			renderTo:"id_todo_regulating_of_breathing",
			width:globalClientWidth - 30,
			height:600,
			lazyLoad:false,
			isClickLoad:false,
			remoteOper :false,
			showPager:true,
			showToolbar:showTools,
			multiSelect:true,
			xmlFileName:'/eleasing/workflow/rent/regulating_breathing/todo_regulating_breathing_list.xml',
			tools :[
						{
							html:'调息',
							plain:true,
							iconCls:'icon-addfolder',
							handler:function(miniTable, buttonText) {
								var rowDatas = miniTable.getSelecteds();
				        		 if(rowDatas.length == 0){
				        			 jQuery.messager.alert('错误提示',"<div style='font-size:18px;line-height:30px;'>请选择需要调息的合同</div>",'error');
				        		 }else {
				        			 todoTX(rowDatas);
				        		 }
							
							}
						}],
			params:{adjustid:"${requestScope['fund_standard_interest.id'] }",docid:flowUnid},
		    columns:[
					{type:'indexcolumn'}, 
					{type:'checkcolumn'},
					{header:'id',field:'id', visible:false},
					{header:'proj_id',field:'projid', visible:false},
					{header:'custid',field:'custid', visible:false},
					{header:'docid',field:'docid', visible:false},
					{header:'合同号',field:'contractid',width:100,queryConfig:{}, visible:false},
					{header:'业务合同号',field:'contractnumber',width:150,queryConfig:{}},
					{header:'客户名',field:'custname',width:200,queryConfig:{isNewLine:true}},
					{header:'设备款',field:'equipamt', visible:false},
					{header:'首付款',field:'firstpayment', visible:false},
					{header:'还租次数',field:'incomenumber'},
					{header:'付租类型',field:'incomenumberyear'},
					{header:'付租方式',field:'periodtype'},
					{header:'起租日期',field:'startdate'},
					{header:'下次调息日',field:'plan_date'},
					{header:'利率',field:'yearrate',width:60, visible:false},
					{header:'内部收益率',field:'irr'},
					{header:'利率浮动类型',field:'ratefloattypename', visible:false},
					{header:'调息时机',field:'adjuststylename',width:60},
		            {header:'操作',align:'center',width:120,
						     renderer:function(e){
						             var id = e.record.id;
		                             return "<a href='javascript:void(0);' onclick='viewShowCondition(\""+id+"\")'>当前商务条件查看</a>";}}
	        ]
		});});});	
//查看商务条件
function viewShowCondition(contractId){
	var URL = getRootPath()+"/leasing/selectContractCondition.action?contractid="+contractId;
    openFullScreenWindow(URL);
}
var isContinue=true;//控制循环是否继续
var c;
//总循环控制调息
function todoTX(rowDatas){
	if(confirm("确认对选中合同进行调息?")){
		isContinue=true;
		c=rowDatas.length;
		
		jQuery("#msgButtonText").html("停止请求");//设置控制按钮
		
		shwoMessageWindwos();//显示操作信息层
		
		for(var i=0;i<rowDatas.length&&isContinue;i++){
			showMsg(rowDatas[i]["contractnumber"]+"开始请求调息!");
			doSendTX(rowDatas[i]["id"],rowDatas[i]["contractnumber"]);//调用调息ajax
		}
	}
}

//显示操作信息层
function shwoMessageWindwos(){
	mini.get("id_station_message").show();
    mini.unmask(document.body);
    
}
function msgButton(){
	if(isContinue){
		if(confirm("确认中断调息吗?")){
			isContinue=false;
			showMsg("调息被人为中断!");
			jQuery("#msgButtonText").html("关闭");//设置控制按钮
			
			return;
		}
	}else{
		//把信息写入富文本域
		var msg=jQuery("#msg_memo").html();
		while(msg.indexOf("<br>")>=0){
			msg=msg.replace("<br>","\n");
		}
		jQuery("#tx_text_memo").val(jQuery("#tx_text_memo").val()+msg);
		jQuery("#msg_memo").html("");
		//重新加载列表
		mini.get("todo_regulating_of_breathing").load();
		mini.get("current_regulating_of_breathing").load();
		
		mini.get("id_station_message").hide();
	}
}
//往控制台显示消息
function showMsg(msg){
	msg="-------------------------------------------------------------------------<br>"+msg+"<br>";
	jQuery("#msg_memo").html(jQuery("#msg_memo").html()+msg);
}
//发contract——id 到后台调用调息函数
function doSendTX(cid,contractNumber){
	ajaxRequest({
	     url:"${pageContext.request.contextPath}/leasing/acl/addAdjustinterest.acl",
	     params:{adjustid:"${requestScope['fund_standard_interest.id'] }",docid:flowUnid,contractid:cid},
	     timeout:30*1000,
	     async:true,//改为同步请求
	     success:function(res){
	 		res=res.responseText;
	 		res=res.replace(/(^\s+)|(\s+$)/g, ""); 
	 		showMsg(contractNumber+"调息请求成功!反馈如下["+res+"]");
	 		txBackFun(cid);
	     },
	     failure:function(res){
	    	res=res.responseText;
	 		res=res.replace(/(^\s+)|(\s+$)/g, "");
	 		showMsg("<strong id='red'>"+contractNumber+"调息请求失败!反馈如下["+res+"]</strong>");
	 		txBackFun();
	     }
	});
}
function txBackFun(id){
	c--;
	if(c<=0){
		isContinue=false;
		jQuery("#msgButtonText").html("关闭");//设置控制按钮
	}
	if(id){
		jQuery("#id_adjust_contractids").val(jQuery("#id_adjust_contractids").val()+(jQuery("#id_adjust_contractids").val()==""?"":",")+id);
	}
}
</script>

<div id="id_todo_regulating_of_breathing"></div>