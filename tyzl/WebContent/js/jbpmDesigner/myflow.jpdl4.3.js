//项目根路径
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
}
(function($){
var myflow = $.myflow;

$.extend(true,myflow.config.props.props,{
	type : {name:'type', label:'流程分类', value:type, editor:function(){return new myflow.editors.selectEditor(getRootPath()+"/table/getTreeData.action?xmlFileName=/combos/comboDict.xml&pid=root.workflowType");}},
	displayName : {name:'displayName', label:'流程显示名称', value:displayName, editor:function(){return new myflow.editors.inputEditor({readOnly:false});}},
	code : {name:'code', label:'流程编号', value:code, editor:function(){return new myflow.editors.inputEditor({readOnly:false});}},
	name : {name:'name', label:'流程名称', value:workflowName, editor:function(){return new myflow.editors.inputEditor({readOnly:isDeployed});}},
	//key  : {name:'key', label:'标识', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	version : {name:'version', label:'版本', value:workflowVersion, editor:function(){return new myflow.editors.inputEditor({readOnly:isDeployed});}},
	jpdlVersion : {name:'jpdlVersion', label:'JPDL版本', value:"4.4", editor:function(){return new myflow.editors.inputEditor({readOnly:true});}},
	desc : {name:'desc', label:'描述', value:workflowDescription, editor:function(){return new myflow.editors.inputEditor();}},
	position : {name:'position', label:'排在', value:position, editor:function(){return new myflow.editors.selectEditor(getRootPath()+"/table/getTreeData.action?xmlFileName=/combos/comboWorkflowTypePosition.xml",true);}}
});

$.extend(true,myflow.config.tools.states,{
	start : {
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'开始'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/start_event_empty.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'开始'}
				    /*,
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}*/
				}},
			end : {type : 'end',
				name : {text:'<<end>>'},
				text : {text:'结束'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/end_event_terminate.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'结束'}
					/*,temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}*/
				}},
			'end-cancel' : {type : 'end-cancel',
				name : {text:'<<end-cancel>>'},
				text : {text:'取消'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/end_event_cancel.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'取消'}
					/*,temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}*/
				}},
			'end-error' : {type : 'end-error',
				name : {text:'<<end-error>>'},
				text : {text:'错误'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/end_event_error.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'错误'}
					/*,temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}*/
				}},
			state : {type : 'state',
				name : {text:'<<state>>'},
				text : {text:'状态'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/task_empty.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'状态'}
					/*,temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}*/
				}},
			fork : {type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'分支'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/gateway_exclusive.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'分支'}
					/*,temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}}*/
				}},
			join : {type : 'join',
				name : {text:'<<join>>'},
				text : {text:'合并'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/gateway_parallel.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'合并'}
					/*,temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}}*/
				}},
			task : {type : 'task',
				name : {text:'<<task>>'},
				text : {text:'任务'},
				img : {src : getRootPath()+'/css/jbpmDesigner/images/16/task_empty.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'任务'}
					/*,temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}}*/
				}}
});
})(jQuery);