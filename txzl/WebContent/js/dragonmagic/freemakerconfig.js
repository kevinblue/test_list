define(function(require,exports,module){
	exports.init = function(){
		loadEntityTree();
		loadEntityColumn();
	}
	var pageHeight = document.documentElement.clientHeight;
	var pageWidth = document.documentElement.clientWidth;
	
	function loadEntityTree(){
		var tree = new mini.Tree();
		bindEntityContextEvent();				
		tree.set({
			id:'freemaker_entity_tree',
			url: globalWebRoot + "/freemaker/getEntityJsonInfo.action?parent=0",
			allowDrag:true,
			allowDrop:true,
			allowLeafDropIn:true,
			height: pageHeight-45,			
			expandOnLoad: false,
			contextMenu:'#freemaker_entity_tree_contextmenu',			
			onnodeclick:function(e){
				var node = e.node;
				if(node.attributes['entityType'] == 'ENTITY'){
					var miniTable = mini.get('table_id_entity_column');
					miniTable.setParams({entityId:node.id});
					miniTable.reload();
					mini.get('entityId').setValue(node.id);
					$('#id_activityType').text(node.attributes.entityName);
				}
			},
			ongivefeedback:function(e){
				//禁止向非目录结点添加子结点
				var dropNode = e.targetNode;
				var effect = e.effect;
				var tree = e.sender;
				if(!dropNode){
					return;
				}
				var node  = e.node;
				//同级可拖拽
				if(tree.getParentNode(dropNode).id !=tree.getParentNode(node).id){
					e.effect = "no";
				}
				
			},
			onDrop:function(e){
				changeNodePosition(e,'Report','parentReport');
			}
		});
		tree.render($('#freemaker_entity_tree')[0]);
	}
	
	function bindEntityContextEvent(){
		mini.get('freemaker_entity_tree_contextmenu').set({
			onbeforeopen:function(e){
				var tree = mini.get('freemaker_entity_tree');
				var node = tree.getSelectedNode();
				
				if(!node){
					e.cancel=true;
					return;
				}
				
				var ctx = mini.get('freemaker_entity_tree_contextmenu');
				for(var i = 0 ; i < ctx.items.length; i++){
					ctx.items[i].show();
				}
				//判断如果节点的类型为ENTITY,则不允许在新增包和和class类
				if(node.attributes.entityType == 'ENTITY'){
					$('#freemaker_entity_contextmenu_add_package,#freemaker_entity_contextmenu_add_entity').hide();
				}
			},
			onitemclick:function(event){
				var item = event.item;
				switch(item.id){
				case 'freemaker_entity_contextmenu_add_entity':
					showEntity('ENTITY',true);
					break;
				case 'freemaker_entity_contextmenu_add_package':
					showEntity('PACKAGE',true);
					break;	
				case 'freemaker_entity_contextmenu_edit':
					showEntity('',false);
					break;
				case 'freemaker_entity_contextmenu_modify':
					removeEntity();
					break;
				default:
				}
			}
		});	
		
		mini.get('entity_saveButton').set({
			onclick:function(){
				saveEntity();	
			}
		});
		mini.get('entity_closeButton').set({
			onclick:function(){
				mini.get('id_form_entity').hide();
			}
		});
		
		mini.get('id_queryentity_tree').on('enter',function(e){
			queryEntityTree(e);
		});
		
		mini.get('id_queryentity_tree_button').on('click',function(e){
			queryEntityTree(e);
		});
		
		mini.get('id_createalldata_button').on('click',function(e){
			recreateAllData(e);
		});
	}
	var recreateAllData = function(){
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '操作正在进行中，请稍后...'
		});
		$.ajax({
			url:globalWebRoot + '/freemaker/createDataByAllEntity.action',
	        type: "post",
	        dataType:'text',
	        success: function (text) {
	        	if(text == 'success'){
	        		mini.alert('操作成功！');
	        	}else{
	        		mini.alert(text);
	        	}
	        	mini.get('freemaker_entity_tree').reload();
	        	mini.unmask(document.body);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	alert(jqXHR.responseText);
	        	mini.unmask(document.body);
	        }
	    });
	}
	var queryEntityTree = function(e){
		var tree = mini.get("freemaker_entity_tree");
		var value  = mini.get('id_queryentity_tree').getValue();
		if(value.trim() == ''){
			tree.clearFilter();
		}else{
			tree.filter (function(node){
				if(node.text.indexOf(value.trim()) != -1){
					return true;
				}					
			},false);
		}
	};
	
	function showEntity(entityType,isNew){
		new mini.Form("form_entity").reset();
			
		var tree = mini.get("freemaker_entity_tree");

		var node = tree.getSelectedNode();
		var parentId = "";
		var position = 0;
		var entityName = node.attributes.entityName;
		if(isNew){
			parentId = node.id;
			//新增名称默认，上级目录+"."
			entityName += "\.";
			var childNodes = tree.getChildNodes(node);
			if(childNodes.length > 0){
				position = childNodes[childNodes.length -1].attributes['position'] + 1;
			}
			
	    }else{
	    	var parentNode = tree.getParentNode(node).id;
	    	parentId = tree.getParentNode(node).id;	
	    	entityType = node.attributes.entityType;
	    }

	    var form = new mini.Form("form_entity");
	    if(isNew){
			mini.get("entityType").setValue(entityType);		
			mini.get("parent").setValue(parentId);
			mini.get('position').setValue(position);
			mini.get("entityName").setValue(entityName);
		}else{
			form.setData(node.attributes);
		}
	    if(entityType == 'PACKAGE'){
	    	$('#tableDescTr,#tableNameTr,#sourcefoldernameTr').hide();
	    }else{
	    	$('#tableDescTr,#tableNameTr,#sourcefoldernameTr').show();
	    }
		mini.get("id_form_entity").show();
	}
	function saveEntity(){
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '操作正在进行中，请稍后...'
		});
		var form = new mini.Form("form_entity");
		var data = form.getData();
		//判断类型是否是
		if(data.entityType == 'ENTITY'){
			//判断source fold是否为空
			if(!data.sourcefoldername){
				mini.alert('添加Class类，source fold不能为空！！！！');
				return;
			}
		}
		form.validate();
		if(form.isValid()){
			$.ajax({
				url: globalWebRoot + "/freemaker/saveEntity.action",
		        type: "post",
		        data:  data ,
		        dataType:'text',
		        success: function (text) {
		        	if(text != null){
						mini.alert("保存成功");
						mini.get('id_form_entity').hide();
						mini.get('freemaker_entity_tree').reload();
					}else{
						mini.alert("保存失败");
					}
		        	mini.unmask(document.body);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	mini.unmask(document.body);
		            alert(jqXHR.responseText);
		        }
		    });
	        
		}else{
			mini.alert("请录入完整必填项！");
		}
	}
	function removeEntity(){
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '操作正在进行中，请稍后...'
		});
		var tree = mini.get("freemaker_entity_tree");
		var node = tree.getSelectedNode();
		var id = node.id;
		if(id == mini.get('entityId').getValue()){
			mini.get('entityId').setValue('');
		}
		$.ajax({
			url: globalWebRoot + "/freemaker/removeEntity.action",
	        type: "post",
	        data:  {id:id} ,
	        dataType:'text',
	        success: function (text) {
				mini.alert("刪除成功");
				mini.get('id_form_entity').hide();
				mini.get('freemaker_entity_tree').reload();
				mini.unmask(document.body);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            mini.unmask(document.body);
	        }
	    });
		
	}
	
function changeNodePosition(e){
		var newPos = 0;
		var newPid = 0;

		switch(e.dragAction){
		case 'add':
			var childNodes = e.sender.getChildNodes(e.dropNode);
			if(childNodes.length > 0){
				newPos = childNodes[childNodes.length -1].attributes['position'] + 1;
			}
			newPid = e.dropNode.id;
			break;
		case 'before':
			var parentNode = e.sender.getParentNode(e.dropNode);
			newPid = parentNode.id;
			newPos = e.dropNode.attributes['position'];
			break;
		case 'after':
			var parentNode = e.sender.getParentNode(e.dropNode);
			newPid = parentNode.id;
			newPos = e.dropNode.attributes['position'] + 1;
			break;
		default:
			return;
		}		
		mini.mask();
		var id = e.dragNode.id;
		$.ajax({
			url: globalWebRoot + "/freemaker/updatePosition.action",
	        type: "post",
	        data:  {'id':id,'position':newPos,'parent':newPid,'action':e.dragAction,'dropid':e.dropNode.id},
	        dataType:'text',
	        success: function (text) {
	        	mini.unmask();
				mini.get('freemaker_entity_tree').reload();
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	mini.unmask();
	            alert(jqXHR.responseText);
	        }
	    });
	}

	function loadEntityColumn(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id_entity_column',
				renderTo: "freemaker_entity_column_container",
				width:pageWidth - 335,
				height:pageHeight-10,
				title:'field',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,	
				params:{entityId:'-1'},
				xmlFileName:'/freemaker/queryFreemakerColumn.xml',
				tools:[
				    {
						html : '新增',//自定义按钮的名字
						plain : true,//按钮是否有立体感
						iconCls : 'icon-add',//按钮的图标
						handler : function(miniTable, buttonText) {//按钮响应函数
							if(!mini.get('entityId').getValue()){
								mini.alert('请你选择左侧的java类！！！');
								return;
							}
							operatorEntityColumn('add',miniTable);
							mini.get("id_freemaker_entity_setting").show();
						}
				    },'-',
				    {
						html : '修改',//自定义按钮的名字
						plain : true,//按钮是否有立体感
						iconCls : 'icon-edit',//按钮的图标
						handler : function(miniTable, buttonText) {//按钮响应函数
							var datas =  miniTable.getSelecteds();
							if(!datas || datas.length <= 0 ){
								mini.alert('请你选择需要修改的数据！');
								return;
							}
							operatorEntityColumn('edit',miniTable);
							mini.get("id_freemaker_entity_setting").show();
						}
				    },'-',
				    {
						html : '删除',//自定义按钮的名字
						plain : true,//按钮是否有立体感
						iconCls : 'icon-remove',//按钮的图标
						handler : function(miniTable, buttonText) {//按钮响应函数
							var datas =  miniTable.getSelecteds();
							if(datas.length <= 0 ){
								mini.alert('请你选择需要删除的数据！');
								return;
							}
							saveChangeToActivity('remove');
							//operatorEntityColumn('remove',miniTable);
						}
				    },
				    '-',
				    {
						html : '生成Entity实体',//自定义按钮的名字
						plain : true,//按钮是否有立体感
						iconCls : 'icon-add',//按钮的图标
						handler : function(miniTable, buttonText) {//按钮响应函数
							if(!mini.get('entityId').getValue()){
								mini.alert('请你选择左侧的java类！！！');
								return;
							}
							var pid = mini.get('entityId').getValue();
							createEntity(pid,'entity');
						}
				    },
				    '-',
				    {
						html : 'Entity实体映射到DB',//自定义按钮的名字
						plain : true,//按钮是否有立体感
						iconCls : 'icon-db',//按钮的图标
						handler : function(miniTable, buttonText) {//按钮响应函数
							if(!mini.get('entityId').getValue()){
								mini.alert('请你选择左侧的java类！！！');
								return;
							}
							var pid = mini.get('entityId').getValue();
							createEntity(pid,'db');
						}
				    },
				    '-',
				    {
						html : '生成基本属性',//自定义按钮的名字
						plain : true,//按钮是否有立体感
						iconCls : 'icon-add',//按钮的图标
						handler : function(miniTable, buttonText) {//按钮响应函数
							if(!mini.get('entityId').getValue()){
								mini.alert('请你选择左侧的java类！！！');
								return;
							}
							var pid = mini.get('entityId').getValue();
							createEntity(pid,'field');
						}
				    }

				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'fieldname',header:'属性名'},
				   	{field:'fieldtype',header:'属性类别'
				   	},
				   	{field:'fieldtypefullname',header:'属性类别',visible:false
				   	},
				   	{field:'tablecolumnname',header:'字段名'},
				   	{field:'tablecolumndesc',header:'列注释'},
				   	{field:'tablecolumntype',header:'约束类型',renderer:function(e){
		                var fieldtype = e.record.tablecolumntype;
		                var displayValue ;
		                switch (fieldtype) {
						case 'PRIMARY':
							displayValue="主键";
							break;
						case 'FOREIGNKEY':
							displayValue="外键";
							break;
						case 'ONETOMANY':
							displayValue="一对多";
							break;	
						case 'ONETOONE':
							displayValue="一对一";
							break;	
						case 'COMMON':
							displayValue = "普通";
							break;
						case 'ENUM':
							displayValue = "枚举";
							break;
						case 'TRANSIENT':
							displayValue = "非持久化";
							break;	
						default:
							displayValue = "普通";
							break;
						}
		                 return displayValue;}},
		           {field:'tableisindex',header:'是否建索引',renderer:function(e){
		                var fieldtype = e.record.tableisindex;
		                var displayValue ;
		                switch (fieldtype) {
						case '0':
							displayValue="否";
							break;
						case '1':
							displayValue="是";
							break;
						default:
							displayValue = "否";
							break;
						}
		                return displayValue;}}, 
		           {field:'tableindexname',header:'索引名称'},       
		           {field:'tablecolumnlength',header:'长度'},       
		           {field:'tablecolumnprecision',header:'精度'},       
		           {field:'tablecolumnscale',header:'位数'},       
		           {field:'tableisnotnull',header:'非空约束',renderer:function(e){
		                var fieldtype = e.record.tableisnotnull;
		                var displayValue ;
		                switch (fieldtype) {
						case '1':
							displayValue="否";
							break;
						case '0':
							displayValue="是";
							break;
						default:
							displayValue = "否";
							break;
						}
		                return displayValue;}}, 
                {field:'tableisunique',header:'唯一性约束',renderer:function(e){
	                var fieldtype = e.record.tableisunique;
	                var displayValue ;
	                switch (fieldtype) {
					case '0':
						displayValue="否";
						break;
					case '1':
						displayValue="是";
						break;
					default:
						displayValue = "否";
						break;
					}
	                return displayValue;}}, 
                {field:'columnorderby',header:'排序规则'}, 
                {field:'fetchtype',header:'抓取类型'}
                ]
			});
		});
		initComboboxField();
		mini.get('config_column_save_button').on('click',function(e){
			saveChangeToActivity(e);
		});
	}
	
	function initComboboxField(){
		mini.get('tablecolumntype').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['tablecolumntype'],
			onvaluechanged:function(e){
				var tableColumnType =  e.sender.value;
				tableColumnTypeValueChanged(tableColumnType);
				tableFieldTypeChanged(mini.get('fieldtype').getValue(),tableColumnType);
			}
		});
		mini.get('fieldtypefullname').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['commonFieldType'],
			onvaluechanged:function(e){
				var fieldType = e.sender.text;
				tableFieldTypeChanged(fieldType,mini.get('tablecolumntype').getValue);
				mini.get('fieldtype').setValue(fieldType);
			}
		});
		mini.get('tableisindex').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['yesOrNo'],
			value:'0',
			onvaluechanged:function(e){
				changeIsIndex(e.sender.value);
			}
		});
		mini.get('tableisnotnull').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['yesOrNoIsNull'],
			value:'0',
			onvaluechanged:function(e){
			}
		});
		mini.get('tableisunique').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['yesOrNo'],
			value:'0',
			onvaluechanged:function(e){
			}
		});
		mini.get('fetchtype').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['fetchType'],
			value:'0',
			showNullItem : true,
			onvaluechanged:function(e){
			}
		});
		mini.get('sourcefoldername').set({
			valueField:'value',
			textField:'text',
			data: comboboxDatas['sourceFold'],
			onvaluechanged:function(e){
			}
		});
		mini.get('tablecolumnposition').set({
			valueField:'value',
			textField:'name',
			url : window.globalWebRoot + '/table/getTableData.action?xmlFileName=freemaker/freemakerEntityColumnPosition.xml&entityId=-1',
			dataField : 'datas'
		});
	}
	
	function operatorEntityColumn(type,miniTable){
	
		var form = new mini.Form("id_freemaker_entity_setting");
		form.reset();
		var entityId = mini.get('entityId').getValue();
		mini.get('tablecolumnposition').load(window.globalWebRoot + '/table/getTableData.action?xmlFileName=freemaker/freemakerEntityColumnPosition.xml&entityId='+entityId);
		if(type == 'edit'){
			var selectData = miniTable.getSelected();
			form.setData(miniTable.getSelected());
			changeIsIndex(!selectData.tableisindex ? 0 : selectData.tableisindex);
			tableColumnTypeValueChanged(selectData.tablecolumntype);
			tableFieldTypeChanged(selectData.fieldtype,selectData.tablecolumntype);
			mini.get('tableisindex').setValue(!selectData.tableisindex ? 0 : selectData.tableisindex) ;	
			mini.get('tableisnotnull').setValue(!selectData.tableisnotnull ? 1 : selectData.tableisnotnull);	
			mini.get('tableisunique').setValue(!selectData.tableisunique ? 0 : selectData.tableisunique);
			mini.get('tablecolumnposition').setValue(selectData.tablecolumnposition);	
		}else{
			//将form表单所有vtype = int类型的全部赋值为Zero,以避免提交的时候校验不了
			var fields = form.getFields();
			for(var i = 0 ; i < fields.length ; i++){
				var field = fields[i];
				if(field.vtype == "int"){
					field.setValue(0);
				}
			}
			$('#bigdecimal_config_tr').hide();
			mini.get('tableisindex').setValue(0);	
			mini.get('tableisnotnull').setValue(1);	
			mini.get('tableisunique').setValue(0);	
			mini.get('tablecolumnposition').setValue(-1);	
			mini.get('tableindexname').disable();
			mini.get('entity').setValue(mini.get('entityId').getValue());
		}
		
	}
	function changeIsIndex(isIndex){
		if(isIndex == '0'){	
			mini.get('tableindexname').disable();
		}else{
			mini.get('tableindexname').enable();
		}
	}
	function tableColumnTypeValueChanged(tableColumnType){
		if(tableColumnType == 'COMMON'){
			mini.get('fieldtypefullname').setAllowInput(false);
			mini.get('fieldtypefullname').setData(comboboxDatas['commonFieldType']);
			mini.get('tablecolumnlength').enable();
			
		}else if(tableColumnType == 'BLOB' || tableColumnType == 'CLOB'){
			mini.get('fieldtypefullname').setAllowInput(false);
			mini.get('fieldtypefullname').setData(comboboxDatas['blobOrClobFieldType']);
			mini.get('tablecolumnlength').disable();
		}else if(tableColumnType == 'ENUM'){
			mini.get('fieldtypefullname').setAllowInput(true);
			mini.get('fieldtypefullname').setData(comboboxDatas['numsFieldType']);
			mini.get('tablecolumnlength').enable();
		}else{
			if(tableColumnType == 'PRIMARY'){
				mini.get('fieldtypefullname').setData(comboboxDatas['commonFieldType']);
				mini.get('tablecolumnname').setValue('ID');
				mini.get('fieldtypefullname').setValue("String");
				mini.get('fieldtype').setValue("String");
			}else{
				mini.get('fieldtypefullname').setAllowInput(true);
				mini.get('fieldtypefullname').setData(comboboxDatas['foreignFieldType']);
			}
			mini.get('tablecolumnlength').disable();
		}
	}
	function tableFieldTypeChanged(fieldType,tablecolumntype){
		if(fieldType == "BigDecimal"){
			$('#bigdecimal_config_tr').show();
			mini.get('tablecolumnlength').setValue(0);
			mini.get('tablecolumnlength').disable();
		}else if(fieldType == "Integer" || fieldType == "Float" || fieldType == "Double"){
			mini.get('tablecolumnlength').setValue(0);
			mini.get('tablecolumnlength').disable();
			$('#bigdecimal_config_tr').hide();
		}else if(fieldType == "String"){
			//mini.get('tablecolumnlength').setValue(0);
			if(tablecolumntype == "CLOB" || tablecolumntype == "BLOB"){
				mini.get('tablecolumnlength').disable();
			}else{
				mini.get('tablecolumnlength').enable();
			}
			$('#bigdecimal_config_tr').hide();
		}else{//其他情况
			$('#bigdecimal_config_tr').hide();
			mini.get('tablecolumnlength').setValue(0);
			mini.get('tablecolumnlength').disable();
		}
	}
	function saveChangeToActivity(e){
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '操作正在进行中，请稍后...'
		});
		var checkResult = true;
		var data = {};
		var operFlag;
		var url;
		if(typeof(e) != 'object'){
			var id = mini.get('table_id_entity_column').getSelected().id;
			data = {id:id};
			operFlag = "删除";
			url= "/freemaker/removeEntityField.action";
		}else{
			var form = new mini.Form("id_freemaker_entity_setting");
			data = form.getData();
			if(data.id){
				operFlag = "修改";
			}else{
				operFlag = "新增";
			}
			if(form.isValid()){
				checkResult = true;
				url = "/freemaker/saveEntityColumn.action"
			}else{	
				var errorFields = form.getErrors();
				var errorText = "";
				for(var i = 0;i < errorFields.length;i ++){
					errorText += (errorFields[i].label + " " + errorFields[i].errorText + "<br/>");
				}
				mini.alert(errorText);
				checkResult = false;
			}
		}
		if(checkResult){
			$.ajax({
				url: globalWebRoot + url,
		        type: "post",
		        data:  data ,
		        dataType:'text',
		        success: function (text) {
		        	if(operFlag == '删除'){
		        		mini.alert('删除成功！！！！');
		        	}else{
		        		if(text != null){
		        			mini.alert(operFlag+"成功");
		        			mini.get('id_freemaker_entity_setting').hide();
		        		}else{
		        			mini.alert("保存失败");
		        		}
		        	}
		        	mini.get('table_id_entity_column').reload();
		        	mini.unmask(document.body);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		            mini.unmask(document.body);
		        }
		    });
	        
		}
	}
	function createEntity(pid,type){
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '操作正在进行中，请稍后...'
		});
		if(type == "entity"){
			url =  globalWebRoot + '/freemaker/createEntity.action';
		}else if(type="field"){
			url =  globalWebRoot + '/freemaker/createEntityBaseField.action';
		}else{
			url =  globalWebRoot + '/freemaker/createDataFromEntity.action';
		}
		var data = {pid:pid}
		$.ajax({
			url:url,
	        type: "post",
	        data:  data ,
	        dataType:'text',
	        success: function (text) {
	        	if(text == 'success'){
	        		mini.alert('操作成功！');
	        	}else{
	        		mini.alert(text);
	        	}
	        	if(type != "entity"){
	        		var entityId = mini.get('entityId').getValue();
	        		var miniTable = mini.get('table_id_entity_column');
	        		miniTable.setParams({entityId:entityId});
	        		miniTable.reload();
	        	}
	        	mini.unmask(document.body);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	mini.unmask(document.body);
	            alert(jqXHR.responseText);
	        }
	    });
	}
});