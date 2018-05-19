<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金催收</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
</head>
<script type="text/javascript">
var selectStr = "";
<permission:action code="assetmanager">
	selectStr = " and  dunningid='${sessionScope.login_userid}'";
</permission:action>
var extendsql="";
<permission:action code="asset_manage_person">
extendsql = "and vcam.dunning_id = '${sessionScope.login_userid}' "
</permission:action>

var showTools = true;
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id8',
			width : globalClientWidth,
			height : globalClientHeight,
			iconCls : 'icon-node',
			hiddenQueryArea : false,
			editFormPopupWindowWidth : 700,
			queryButtonColSpan : 4,
			title : '租金催收' ,
			remoteOper : true,
			pageSize : 20,
			showPager : true,
			lazyLoad : false,	
			frozenStartColumn:0,
			frozenEndColumn:6,
			xmlFileName : '/eleasing/jsp/fund/fund_overdue/overdue_menu_rent.xml',
			params:{
	        	extendsql:extendsql
		  	},
			tools:[{
				html:'提醒/催收',
				plain : true,
				handler : function(miniTable, buttonText) {
					var row = miniTable.getSelected();
					if(row == null){
						mini.alert("请选中一条记录！");
					}else{
						var contractid=row.contractid;
						var params = "planid="+row.planid+"&custid="+row.custid+"&contractid="+row.contractid+
						"&contractnumber="+row.contractnumber+"&custname="+escape(encodeURIComponent(row.custname))+"&province="+escape(encodeURIComponent(row.province))+
						"&rentlist="+escape(encodeURIComponent(row.rentlist))+"&plandate="+row.plandate+"&rent="+row.rent+
						"&corpus="+row.corpus+"&interest="+row.interest+"&currentincome="+row.currentincome+
						"&curcorpusincome="+row.curcorpusincome+"&curinterestincome="+row.curinterestincome+
						"&currentoverage="+row.currentoverage+"&curcorpusoverage="+row.curcorpusoverage+
						"&curinterestoverage="+row.curinterestoverage+"&curpenaltyincome="+row.curpenaltyincome+
						"&custclass="+row.custclass+"&id="+row.id+"&specialrequirement="+escape(encodeURIComponent(row.specialrequirement))+"&projid="+row.projid;						
						var url =getRootPath()+"/leasing/fund/fund_overdue/overdue_menu_list_remind.bi?"+params;
						
						var sheight = window.screen.availHeight - 30;
			  			var swidth = window.screen.availWidth - 10;
			  			var winosption = "left=0px,top=0px,height="
			  					+ sheight
			  					+ "px,width="
			  					+ swidth
			  					+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
			  			window.open(url, '_blank', winosption);
					}
				}
			}
			/* ,'-',{
				html : '维护特殊要求',
				plain : true,//背景透明
				iconCls : 'icon-addfolder',//按钮图标类
				handler : function (miniTable, buttonText) {
					var row = miniTable.getSelected();
					if(row){
						mini.get("id_specialrequirement").setValue(row.specialrequirement==null ? "" : row.specialrequirement);
						mini.get("id_win_add_specialrequirement").show();
					}
				}
			} */
			],
			columns : [ 
			       {type:'indexcolumn'},
			       {type:'checkcolumn'},
			       {field:'id',header:'合同id',headerAlign:'center',width:100,allowSort:true,visible:false},
			       {field:'contractid',header:'合同编号',headerAlign:'center',width:120,allowSort:true,
			    	   renderer:function(e){
			    		   var rowData = e.record; 
			    		   return "<a href='javascript:void(0);' onclick='zijinshoufu(\""+rowData["id"]+"\")'>"+rowData["contractid"]+"</a>";
			    	   }
			       },
			       {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:120,allowSort:true},
			       {field:'custid',header:'客户id',headerAlign:'center',width:100,visible:false},
			       {field:'custname',header:'客户名称',headerAlign:'center',width:100,queryConfig:{},
			    	   renderer:function(e){
			    		   var rowData = e.record; 
			    		   return "<a href='javascript:void(0);' onclick='custserach(\""+rowData['custid']+"\",\""+rowData['custclass']+"\")'>"+rowData["custname"]+"</a>";
			                 }
			       },
			       {field:'projname',header:'项目名称',headerAlign:'center',width:100,queryConfig:{},
			       },
			       {field:'rentlist',header:'计划期项',headerAlign:'center',width:100,allowSort:true},
			       {field:'plandate',header:'计划日期',headerAlign:'center',width:100,
			    	   queryConfig : {
							type : 'date',							
							range : true
						}
					},
			       {field:'coun',header:'催收次数',headerAlign:'center',width:100,allowSort:true,
			    	   renderer:function(e){
			    		   var rowData=e.record;	
			    		   return "<a href='javascript:void(0);' onclick='rentserach(\""+rowData['planid']+"\")'>"+rowData["coun"]+"</a>";
			    	   }
			       },
			       {field:'custclass',header:'客户类别',headerAlign:'center',width:100,visible:false},
			       {field:'contractstatus',header:'合同状态',headerAlign:'center',width:100,visible:false},
			       {field:'province',header:'省份',headerAlign:'center',width:100,allowSort:true,
			    	   queryConfig:{
			    		   newLine:true,
	                   		 type:'combobox',	                   		
	                   		allowInput:true,
	                   		showNullItem:true,
		                   	 valueField:'value',
					         textField:'value',
					         params:{xmlFileName:'eleasing/jsp/base/t_district.xml',PID:'CHN'},
	                   	 }},
			       {field:'districtname',header:'区域',headerAlign:'center',width:100,allowSort:true,
			    	   queryConfig:{
			            	 colspan:1,
				              type:'combobox', 
						        valueField:'name',
						         textField:'name',
						         newLine:false,
						            params:{xmlFileName:'combos/comboDict.xml',pid:'district'},
						        allowInput:true, 
						      showNullItem:true}
			       },
			       {field:'rent',header:'应收租金',headerAlign:'center',width:100,allowSort:true},
			       {field:'corpus',header:'应收本金',headerAlign:'center',width:100,allowSort:true},
			       {field:'interest',header:'应收利息',headerAlign:'center',width:100,allowSort:true},
			       {field:'currentincome',header:'实收租金',headerAlign:'center',width:100,allowSort:true},
			       {field:'curcorpusincome',header:'实收本金',headerAlign:'center',width:100,allowSort:true},
			       {field:'curinterestincome',header:'实收利息',headerAlign:'center',width:100,allowSort:true},
			       {field:'currentoverage',header:'剩余租金',headerAlign:'center',width:100,allowSort:true},
			       {field:'curcorpusoverage',header:'剩余本金',headerAlign:'center',width:100,allowSort:true},
			       {field:'cuinterestadjustincome',header:'调整利息',headerAlign:'center',width:100,allowSort:true},
			       {field:'curinterestoverage',header:'剩余利息',headerAlign:'center',width:100,allowSort:true},
			       {field:'curpenaltyincome',header:'实收罚息',headerAlign:'center',width:100,allowSort:true},
			       {field:'curpenaltyadjustincome',header:'调整罚息',headerAlign:'center',width:100,allowSort:true},
			       {field:'planpenalty',header:'应收罚息',headerAlign:'center',width:100,allowSort:true},
			       {field:'penalty',header:'剩余罚息',headerAlign:'center',width:100,allowSort:true},
			       {field:'planid',header:'租金计划id',headerAlign:'center',width:100,visible:false},
			       {field:'specialrequirement',header:'特殊要求',headerAlign:'center',width:100,visible:true}
			]
		});
	});
});
function zijinshoufu(cid)
{
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}

function custserach(id,custclass){
 	var params = "id="+id+"&isView=true";
 	var url="";
	if(custclass=="CUST_INFO_COMPANY"){
		url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
	}else{
		url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
	}
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
function rentserach(id){
	 params="planid="+id;
	 mini.open({
         url: getRootPath() + "/leasing/fund/fund_overdue/overdue_menu_list_remind01.bi?"+params,                          
         title: "催收记录",
         width: 650,
         height: 335
     }); 
}

function opencustdetail(contractid,custid){
	 var params = "contractid="+contractid+"&custid="+custid;
	 var url = getRootPath()+"/leasing/fund/fund_overdue/overdue_menu_list_details.bi?"+params;
	 var sheight = window.screen.availHeight - 30;
	 var swidth = window.screen.availWidth - 10;
	 var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
</script>

<body>
<div id="id_win_add_specialrequirement" class="mini-window" title="维护特殊要求" style="width:30%;height:15%;" showModal="true" allowResize="true" allowDrag="true">
        <div id="id_div_distri_manager">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:100px;">特殊要求：</td>
                    <td >
                    <input style="width:300px;" id="id_specialrequirement" name="specialrequirement" class="mini-textbox"  maxlength="30"/>
                    </td>
                </tr>
                
                <tr>
                	<td style="width:100px;"></td>
                	<td style="width:100px;"></td>
                	<td style="width:100px;"></td>
                	<td>
                        <a class="mini-button " onclick="addspecialrequirement"><spring:message code="Confirm" text="确定"/></a> &nbsp;&nbsp;
               		</td>
                </tr>
            </table>
        </div>
     </div>
</body>
</html>
<script type="text/javascript">
function addspecialrequirement(){
	/* if (miniui_ext.submitFormValidation(["id_signdate"]) == false){
		alert("请选中项目！");
		return false}; */
	var miniTable=mini.get("table_id8");
	var row=miniTable.getSelected();
	var specialrequirement=mini.get("id_specialrequirement").getValue();
	var contractid = row.id;
	var params = {};
	params.contractid = contractid;
	params.specialrequirement=specialrequirement;
	$.ajax({
		url : '${pageContext.request.contextPath}/acl/saveSpecialrequirement.acl',
		method : 'POST',
		success:function(res){
			//console.info(res);//控制台显示info信息
			var scustinfo = res.responseText;
			mini.get("id_win_add_specialrequirement").hide();
			miniTable.reload();
		},
		async : false,
		failure : function(res) {
			currentLoadMask.hide();
		},
		data : params
	});
}
</script>
