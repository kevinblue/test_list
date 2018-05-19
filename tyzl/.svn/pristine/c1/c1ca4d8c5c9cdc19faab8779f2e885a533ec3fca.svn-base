<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>网银导入</title>
<%
	//前缀
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
	    <fieldset id="fd2">
	        <legend><label><input type="checkbox" checked id="checkbox1" onclick="toggleFieldSet(this, 'fd2')" hideFocus/>查询条件</label></legend>
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table">
	                <tr>
	                    <td style="width:100px;">流水号：</td>
	                    <td >
	                        <input name="sn" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td style="width:100px;">客户：</td>
	                    <td >
	                        <input name="custname" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="miniext-form-td">付款人：</td>
	                    <td >
	                        <input name="clientname" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td class="miniext-form-td">合同号：</td>
	                    <td >
							<input name="contractidincome" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                </tr>  
	                <tr>
	                    <td class="miniext-form-td">到账时间：</td>
	                    <td >
	                        	从
	                        	<input name="factdate_start" class="mini-datepicker" allowInput="false"/>
	                        	到
	                        	<input name="factdate_end" class="mini-datepicker" allowInput="false"/>
	                    </td>
	                    <td class="querybtn">
	                    	<input class="mini-button" text="&nbsp;&nbsp;&nbsp;" tooltip="查询"
	                    	iconCls="icon-query" onclick="search"
	                        />
	                    </td>
	                </tr>    
	            </table>
	        </div>
	    </fieldset>
    </div>
<!-- 按钮部分 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-edit" plain="true" onclick="upd">修改</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="ebankimport">网银上传</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="modeldownload">模板下载</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="remove">废弃</a>
	            </td>
            </tr>
        </table>
    </div>
    <!-- 列表部分 -->
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">
        
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="<%=prefix %>/eleasing/jsp/fund/fund_ebank/fund_ebank_info.xml"  idfield=""
            sizeList="[5,10,20,50]" pageSize="10" showColumnsMenu="true"
            datafield="datas"
        >
            <div property="columns">
                <div type="checkcolumn"></div>
                <div field="ebdataid"  headerAlign="center" >网银编号</div>
                <div field="sn"  headerAlign="center" >流水号</div>
                <div field="custname"  headerAlign="center" >客户</div>
                <div field="enabledname"  headerAlign="center" >是否有效</div>
                <div field="clientname"  headerAlign="center" >付款人</div>
                <div field="contractidincome"  headerAlign="center" >合同号</div>
                <div field="moneytype"  headerAlign="center" >到账金额类型</div>
                <div field="factmoney"  headerAlign="center" >到账金额</div>
                <div field="nowithmoney"  headerAlign="center" >与系统无关金额</div>
                <div field="hiremoney"  headerAlign="center" >已核销金额</div>
                <div field="owmoney"  headerAlign="center" >剩余金额</div>
                <div field="factdate"  headerAlign="center" >到账时间</div>
                <div field="nocontract"  headerAlign="center" >是否暂收款</div>
                <div field="summary"  headerAlign="center" >备注</div>
                <div field="creator_name"  headerAlign="center" >登记人</div>
                <div field="create_date"  headerAlign="center" >登记时间</div>
            </div>
        </div> 
    </div>
    <script type="text/javascript">
        function toggleFieldSet(ck, id) {
            var dom = document.getElementById(id);
            dom.className = !ck.checked ? "hideFieldset" : "";
            var _fit = mini.get("_fit");
            _fit.setHeight(window.document.body.clientHeight);
        }
    </script>
    <script type="text/javascript">
    	//初始化
        mini.parse();
        miniui_ext.addOnEnter("form1");
        var grid = mini.get("datagrid1");
        var form = new mini.Form("form1");
        //grid.load({"xmlFileName":"/eleasing/jsp/cust_info/cust_person/cust_person_list.xml"});
        grid.load();
        //grid.frozenColumns(0, 1);//锁定列
        //combobox懒加载
        function onbeforeshowpopup(e) {
        	miniui_ext.onbeforeshowpopup(e);
        }
        //查询
        function search(e) {
            var data = form.getData(true,true);
 		    grid.load(data);
        }
      	//格式化日期
        function onDateRenderer(e) {
            return miniui_ext.onDateRenderer(e);
        }
      //右键菜单
        function onBeforeOpen(e) {
            var menu = e.sender;
                    
            var row = grid.getSelected();
            var rowIndex = grid.indexOf(row);            
            if (!row) {
                e.cancel = true;
                //阻止浏览器默认右键菜单
                e.htmlEvent.preventDefault();
                return;
            }
        }
     	//修改
        function upd(e){
     		//获取选中行、并判断
        	var row = grid.getSelected();
            if (row) {
            	/*
		  			title:'标题'
		  			showModal:'是否遮罩'
		  			showMaxButton:'是否显示最大化按钮'
		  			onload:页面打开时加载
		  			ondestroy:页面关闭时加载
		  		*/
                mini.open({
                    url: getRootPath()+"/leasing/interface/financial_interface/add_ebank_import.bi",
                    title: "编辑", width: 800, height: 500,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        iframe.contentWindow.SetData(row);
                        
                    },
                    ondestroy: function (action) {
                    	if("savesuccess" == action){
                    		grid.reload();
                    	}
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
        }
        var importcolumn= [
            {header:'id',mapping:'id'},
            {header:'流水号',mapping:'sn'},
            {header:'到账金额',mapping:'factmoney'},
		    {header:'与系统无关金额',mapping:'nowithmoney'},
		    {header:'到账时间',mapping:'factdate'},
		    {header:'到账金额类型',mapping:'moneytype'},
            {header:'付款人',mapping:'clientname'},
            //{header:'本方银行',mapping:'ownbank'},
            //{header:'本方账户',mapping:'ownaccount'},
            {header:'本方账号',mapping:'ownaccnumber'},
            //{header:'对方银行',mapping:'clientbank'},
            //{header:'对方账户',mapping:'clientaccount'},
            {header:'对方账号',mapping:'clientaccnumber'},
            {header:'用途',mapping:'usefor'},
            {header:'备注1',mapping:'summary',hidden:true},
            {header:'备注2',mapping:'summarybak',hidden:true}
            //{header:'是否启用',mapping:'enabled'}
        ];
     	function ebankimport(e){
			var url="";
			url="/leasing/template/uploadExcelToDatabase.action";
			//attachmentUp({url:url,title:"网银上传",parames:{modename:"网银导入"}});
			attachmentUp({url:"/leasing/template/uploadExcelToDatabase.action",targetClass:'com.tenwa.leasing.entity.base.FundEbankData',custoperatorMethod:'checkEbankInfoDataImport',modelname:'ebankinfo',title:'网银上传',parames:importcolumn});
     	}
     	function modeldownload(e){
     		attachmentDown({url:'/leasing/template/downloadFileTemplateByClasses.action',returnType:'file',twoClassify:'wordtempletypetwo10',modelname:'ebankinfo'});
     	}
     	function remove(e){
     		//获取选中行、并判断
        	var plandata = grid.getSelecteds();
		    if (plandata.length>0) {
		    	var ids="";
	        	for(var i=0;i<plandata.length;i++){
	                var factmoney=plandata[i].factmoney;
	                var nowithmoney=plandata[i].nowithmoney;
	               	var owmoney=plandata[i].owmoney;
	               	var allmoney=parseFloat(factmoney)-parseFloat(nowithmoney);
	               	allmoney=allmoney.toFixed(2);
	               	if(parseFloat(allmoney)!=parseFloat(owmoney)){
	                    alert("网银编号为"+plandata[i].ebdataid+"已经核销过不能废弃");
	                    return false;
	               	}else{
	                  	if(ids!=""){ids=	ids+",";}
	                  	ids=ids+plandata[i].id;
	               	} 
	            }
	        	if(ids){
			    	mini.confirm("确定删除选中记录？","提示：",function(data){
			        	if("ok" == data){
			        		grid.loading("操作中，请稍后......");
				            $.ajax({
				                url: "<%=request.getContextPath() %>/acl/updateCancalfundebank.acl?ids="+ids,
				                data: {}  ,
				                success: function (text) {
				                    grid.reload();
				                },
				                error: function () {
				                }
				            });
			        	}
			        });
	        	}
		    } else {
                alert("请选中一条记录");
            }
     	}
    </script>
</body>
</html>