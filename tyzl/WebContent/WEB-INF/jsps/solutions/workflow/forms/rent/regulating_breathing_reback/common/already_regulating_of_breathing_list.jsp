<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script>
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;};
	
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "already_regulating_of_breathing",
			renderTo: "id_already_regulating_of_breathing",
			width: globalClientWidth - 25,
			height: 600,
			lazyLoad:false,
			isClickLoad:false,
			remoteOper : false,
			showPager: true,
			showToolbar:showTools,
			multiSelect: true,
			xmlFileName:'eleasing/workflow/rent/regulating_breathing_back/already_regulating_breathing_list.xml',
			params:{
			     adjustid:'<mini:param  name="fund_standard_interest.id" />',
			     docid:'<mini:param  name="docid" />'
			     },
		     tools : [
					{
					  html : '调息撤销',
				      plain : true,
					  iconCls : 'icon-addfolder',
					  handler : function(miniTable, buttonText) {
						  var rowDatas = miniTable.getSelecteds();
					      if(rowDatas.length == 0){
					        jQuery.messager.alert('错误提示',"<div style='font-size:18px;line-height:30px;'>请选择需要撤销调息的合同</div>",'error');
					      }else {
					         rollBackTX(rowDatas);
					       }	
								}
							}         
						],
		 columns:[
			{type : 'indexcolumn'}, 
			{type : 'checkcolumn'},
  			{header:'id',field:'id',visible : false},
			{header:'projid',field:'projid',visible : false},
			{header:'custid',field:'custid',visible : false},
			{header:'adjustid',field:'adjustid',visible : false},
			{header:'docid',field:'docid',visible : false},
			{header:'合同号',field:'contractid'},
			{header:'业务合同号',field:'contractnumber'},
			{header:'客户名',field:'custname'},
			{header:'设备款',field:'equipamt'},
			{header:'首付款',field:'firstpayment'},
			{header:'还租次数',field:'incomenumber'},
			{header:'付租类型',field:'incomenumberyear'},
			{header:'付租方式',field:'periodtype'},
			{header:'起租日期',field:'startdate'},
			{header:'利率',field:'yearrate'},
			{header:'内部收益率',field:'irr'},
			{header:'调息前利率',field:'rateoriginal'},
			{header:'调息后利率',field:'rateadjust'},
			{header:'调息前内部收益率',field:'oldirr'},
			{header:'调息后内部收益率',field:'newirr'},
		    {header:'操作',align:'center',width:120,
						renderer : function(e){
						var  table = e.record;
		                return "<a href='#' onclick='previewhis("+JsonUtil.encode(table)+")'>对比</a>";
						}
		    	     }

	        ]
		});});});	
function previewhis(data){
	 
	   var URL = "${pageContext.request.contextPath}/leasing/query/condition_his/interestTempCompare.bi";
	   
	   var params = {cid:data.id,contractid:data.contractid,docid:data.docid,read_only :false};
	   openFullScreenWindow(URL,params);
}
//发contract——id 到后台调用调息函数
var currentLoadMask;
function rollBackTX(rowDatas){
	 rowDatas = JsonUtil.encode(rowDatas);
	if(confirm("是否回滚此客户?")){
		var loadMaskMsg = ("正在对此客户进行回滚,请稍后... ");
        if(null == currentLoadMask){
        	currentLoadMask = new tracywindyLoadMask(document.body,loadMaskMsg);
        }
        currentLoadMask.show();
        ajaxRequest({
            url:"${pageContext.request.contextPath}/leasing/acl/rollBackAdjustinterest.acl",
            params:{
			     adjustid:'<mini:param  name="fund_standard_interest.id" />',
			     docid:'<mini:param  name="docid" />',
                "already_json_val":rowDatas
            },
            timeout:30*1000,
            success:function(res){
    	   		res=res.responseText;
    	   		res=res.replace(/(^\s+)|(\s+$)/g, ""); 
    	   		
	     	    if(res!=''){
	     	     	mini.alert("<div style='font-size:18px;line-height:30px;'>回滚成功!</div>","提示");
	     	    	currentLoadMask.hide();
	     	    	mini.get("already_regulating_of_breathing").load();
	     	    	mini.get("curent_roll_back_the_record").load();
	       			//getTracywindyObject("id_table1").reload();
	    			//getTracywindyObject("id_table2").reload();
	    			 
	     	    }else{
	     	    	mini.alert("<div style='font-size:18px;line-height:30px;'>回滚失败!</div>",'错误提示');
	     	    	currentLoadMask.hide();
	       			//getTracywindyObject("id_table1").reload();
	    			//getTracywindyObject("id_table2").reload();
	     	    }
            },
            
            failure:function(res){
                mini.alert("<div style='font-size:18px;line-height:30px;'>服务器通信失败!</div>",'错误提示');
                currentLoadMask.hide();
            }
       });
    }
}
</script>
<div id="id_already_regulating_of_breathing"></div>