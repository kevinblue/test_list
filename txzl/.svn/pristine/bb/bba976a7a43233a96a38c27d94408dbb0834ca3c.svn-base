<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>发票认证结果导入</title>
<%
	//前缀
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
	    <fieldset id="fd2">
	        <legend><label><input type="checkbox" checked id="checkbox1" onclick="toggleFieldSet(this, 'fd2')" hideFocus/>查询条件</label></legend>
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table">
	                <tr>
	                    <td style="width:100px;">合同号：</td>
	                    <td >
	                        <input name="raw_contractid" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td style="width:100px;">客户名称：</td>
	                    <td >
	                        <input name="raw_custid" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td style="width:100px;">发票号码：</td>
	                    <td >
	                        <input name="invoiceno" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="miniext-form-td">发票金额：</td>
	                    <td >
	                        <input name="invoicemoney" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td class="miniext-form-td">部门：</td>
	                    <td >
	                        <input name="projdept" class="mini-combobox miniext-form-fit" textField="value" 
			                  	   valueField="name"  
								   dataField="datas"
								   allowInput="false"
								   showNullItem="true" 
								   multiSelect="false"
								   data-options="{_xmlFileName:'/eleasing/jsp/base/t_dept_info.xml'}"
								   onbeforeshowpopup="onbeforeshowpopup"
								/>
	                    </td>
	                    <td class="miniext-form-td">状态：</td>
	                    <td >
		                        <input id="invoicestatus" name="invoicestatus" class="mini-combobox miniext-form-fit" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false"
								   showNullItem="true" 
								   multiSelect="false"
								   data-options="{_params:{pid:'invoice_status'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								/>
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
	                <a class="mini-button" iconCls="icon-edit" plain="true" onclick="download">模板下载</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-edit" plain="true" onclick="upload">导入认证结果</a>
	            </td>
            </tr>
        </table>
    </div>
    <!-- 列表部分 -->
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">
        
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="<%=prefix %>/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_imp_list.xml"  idField="id"
            sizeList="[5,10,20,50]" pageSize="10" showColumnsMenu="true"
            dataField="datas" contextMenu="#gridMenu" multiSelect="true">
            <div property="columns">
                <div type="checkcolumn" ></div>   
                <div field="contractid" headerAlign="center">合同号</div>
                <div field="custname" width="200"  headerAlign="center">客户名称</div>     
                <div field="invoiceno"  headerAlign="center" >发票号码</div>
                <div field="feetype"  headerAlign="center" >费用类型</div>                            
                <div field="invoicemoney" headerAlign="center" align="right">总金额</div>
                <div field="invoicemoney"  headerAlign="center" align="right">发票金额</div>
                <div field="invoicetype"  headerAlign="center" >发票种类</div>
                <div field="recorddate"  headerAlign="center">登记时间</div>
                <div field="creator"  headerAlign="center">登记人</div>
                <div field="raw_projdept"  headerAlign="center">出单部门</div>
                <div field="memo"  headerAlign="center">备注</div>
                <div field="invoicedate"  headerAlign="center">开票时间</div>
                <div field="corpustaxmoney"  headerAlign="center">认证金额</div>
                <div field="taxmoney"  headerAlign="center">税额</div>
                <div field="certificationresults"  headerAlign="center">认证结果</div>
                <div field="upuser"  headerAlign="center">回导人</div>
                <div field="uploaddate" headerAlign="center" >回导时间</div>
            </div>
        </div> 
        <ul id="gridMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
	        <li name="confirmtax" iconCls="icon-edit" onclick="confirmTax">模板下载</li>
		    <li name="back" iconCls="icon-remove" onclick="back">导入认证结果</li>   
	    </ul>
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
        
        grid.load();//加载数据
        
        grid.frozenColumns(0, 1);//锁定列
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
      	
        //模板下载
        function download(e){
        	attachmentDown({
      	       url:'/leasing/template/createFileByTemplateClass.action',
      	       returnType:'file',
      	       twoClassify:'taxvatuploadinfo',
      	      modelname:'taxvatuploadinfo'
          	       });
        }
     	//导入认证结果
		function upload(e) {
			attachmentUp({url:"/leasing/template/uploadExcelToDatabase.action",modelname:'taxvatuploadinfo',title:'导入认证结果',headerIndex:'1',dataIndex:'2',targetClass:'com.business.entity.invoice.vat.TaxVatUploadInfo',custoperatorMethod:'initTaxVatUploadInfo',parames:constructionColumn});
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

    </script>
</body>
</html>