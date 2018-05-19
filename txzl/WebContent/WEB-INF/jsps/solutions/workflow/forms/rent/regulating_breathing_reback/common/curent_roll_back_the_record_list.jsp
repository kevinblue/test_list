<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};;
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "curent_roll_back_the_record",
			renderTo: "id_curent_roll_back_the_record",
			width: globalClientWidth - 25,
			height: 600,
			lazyLoad:false,
			isClickLoad:false,
			remoteOper : false,
			showPager: true,
			showToolbar:showTools,
			multiSelect: true,
			xmlFileName:'/eleasing/workflow/rent/regulating_breathing_back/curent_roll_back_the_record.xml',
			params:{
			     adjustid:"${requestScope['fund_standard_interest.id']}",
			     docid:"${requestScope['docid']}"
			     },
		     tools : [
					{
					  html : '撤销',
				      plain : true,
					  iconCls : 'icon-addfolder',
					  handler : function(miniTable, buttonText) {
						  var rowDatas = miniTable.getSelecteds();
					      if(rowDatas.length == 0){
					        jQuery.messager.alert('错误提示',"<div style='font-size:18px;line-height:30px;'>请选择需要撤销调息的合同</div>",'error');
					      }else {
					    	  removeRollBackTX(rowDatas);
					       }	
								}
							}         
						],
		 columns:[
			{type : 'indexcolumn'}, 
			{type : 'checkcolumn'},
			{header:'id',field:'id',visible : false},
			{header:'proj_id',field:'proj_id',visible : false},
			{header:'custid',field:'custid',visible : false},
			{header:'docid',field:'docid',visible : false},
			{header:'合同号',field:'contractid'},
			{header:'业务合同号',field:'contractnumber'},
			{header:'调息类型',field:'adjuststylename'},
			{header:'调息类型',field:'adjuststyle',visible : false},
			{header:'调息发起时间',field:'adjustdate'},
			{header:'客户名',field:'custname'},
			{header:'设备款',field:'equipamt'},
			{header:'首付款',field:'firstpayment'},
			{header:'还租次数',field:'incomenumber'},
			{header:'付租类型',field:'incomenumberyear'},
			{header:'付租方式',field:'periodtype'},
			{header:'起租日期',field:'startdate'},
			{header:'利率',field:'yearrate',visible : false},
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
			 var adjuststyle = data.adjuststyle;
			 var jspName;
			 if(adjuststyle=="onhire"){
				 jspName = "interestTempCompare";
			 }else{
				 jspName = "interestTempCompareBefore";
			 }
			   var URL = "${pageContext.request.contextPath}/leasing/query/condition_temp/"+jspName+".bi";
			   
			   var params = {cid:data.id,contractid:data.contractid,docid:data.docid,read_only :false};
			   openFullScreenWindow(URL,params);
		}
		//发contract——id 到后台调用调息函数
		var currentLoadMask;
		function removeRollBackTX(rowDatas){
			 rowDatas = JsonUtil.encode(rowDatas);
			if(confirm("是否撤销回滚此客户?")){
				var loadMaskMsg = ("正在对此客户进行撤销回滚,请稍后... ");
		        if(null == currentLoadMask){
		        	currentLoadMask = new tracywindyLoadMask(document.body,loadMaskMsg);
		        }
		        currentLoadMask.show();
		        ajaxRequest({
		            url:"${pageContext.request.contextPath}/leasing/acl/removeRollBackAdjustinterest.acl",
		            params:{
		                "already_json_val":rowDatas,
		                adjustid:"${requestScope['fund_standard_interest.id']}",
		       	        docid:"${requestScope['docid'] }"
		            },
		            timeout:30*1000,
		            success:function(res){
		    	   		res=res.responseText;
		    	   		res=res.replace(/(^\s+)|(\s+$)/g, ""); 
			     	    if(res!=''){
			     	    	//currentTable.reload();
				    	 	//getTracywindyObject("table_id_table_leasing_current_regulating_of_breathing_container").reload();
				   
			     	    	mini.alert("<div style='font-size:18px;line-height:30px;'>撤销回滚成功!</div>",'提示');
			     	    	currentLoadMask.hide();
			     	    	mini.get("already_regulating_of_breathing").load();
			     	    	mini.get("curent_roll_back_the_record").load();
			       			 //getTracywindyObject("id_table1").reload();
			    			 //getTracywindyObject("id_table2").reload();
			     	    }else{
			     	    	mini.alert("<div style='font-size:18px;line-height:30px;'>撤销回滚失败!</div>",'错误提示');
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
<div id="id_curent_roll_back_the_record"></div>