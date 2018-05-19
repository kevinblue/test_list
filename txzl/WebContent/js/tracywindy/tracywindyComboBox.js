//if(window.tracywindyComboBox)alert("不能重复引用tracywindyComboBox.js");
function hiddenAllComboBox()
{
	for(var p in tracywindyObject)
	{
		if( 'combobox' == tracywindyObject[p].objectType)
		{
			tracywindyObject[p].dropDiv.style.display = "none";
			tracywindyObject[p].childCreate=false;
		}
	}
}
function initComboboxAllHiddenEvent(){
	   if(!window.isInitComboHidden){
		   var workflowFormContainer = document.getElementById('id_workflowFormContainer_inner');
		   if(workflowFormContainer)
		   {
		   	 var scrollHandler = (function(){
		   		 return function(){
		   			//同时我们把下拉的关闭掉。
		   		     //document.getElementById("selectChild" + $me.id).style.display="none";
		   			 //$me.childCreate=false;
		   			 hiddenAllComboBox();
		   		 };
		   	 })();
		   	 if(!SysBrowser.mobile){
		   		 workflowFormContainer.onscroll=scrollHandler;
		   		 document.body.onscroll= scrollHandler;
		   	 }else{
		   		 workflowFormContainer.ontouchstart = scrollHandler;
		   		 document.body.ontouchmovestart = scrollHandler;
		   	 }
		   }
		   if(document.documentElement)
		   {
		   	 var currentClickHandler = (function(){
		   		 return function(e){
		   			
		   			 var currentTarget = getEvent(e).srcElement||getEvent(e).target||getEvent(e).currentTarget;
		   			 
		   			 if(currentTarget){
		   				 if((((currentTarget.className+"")||" ").indexOf("x-panel-combo-droplist")>-1))
		   				 {
		   					 return false;
		   				 }
		   			 }
		   			 //同时我们把下拉的关闭掉。
		   		     //document.getElementById("selectChild" + $me.id).style.display="none";
		   		     //$me.childCreate=false;
		   			 hiddenAllComboBox();
		   		 };
		   	 })();
		   	//document.documentElement.onmouseup = currentClickHandler;
		   	 //document.documentElement.onscroll  = currentClickHandler;
		   	 document.documentElement.onmousedown  = currentClickHandler;
		   	 /*注册事件*/
		   	 if(document.addEventListener){
		   	      document.addEventListener('DOMMouseScroll',currentClickHandler,false);
		   	 }//W3C
		   	 window.onmousewheel=document.onmousewheel=currentClickHandler;//IE/Opera/Chrome/Safari
		   }
		   window.isInitComboHidden = true;
	   }
}
var tracywindyComboBox = function(config){
	if(config.id){
		var oldCombo = getTracywindyObject(config.id);
		if(oldCombo){
			try{oldCombo.renderToObj.removeChild(oldCombo.dom);}catch(e){}
			try{document.body.removeChild(oldCombo.dropDiv);}catch(e){}
		}
	}
   this.lazyLoad   = false;//config.lazyLoad||false;//false;//
   this.objectType = "combobox";
   this.loadMode = config.loadMode ||"ajax";
   this.url = config.url||(getRootPath()+'/table/getTableData.action');
   this.datas = config.datas||[];
   this.displayField = config.displayField||'name';
   this.valueField  = config.valueField||'value';
   this.value = config.value||'';
   this.rawValue = config.rawValue||'';
   this.childCreate=false;
   this.grantValue = true;
   this.topAdd = config.topAdd||0;
   this.leftAdd = config.leftAdd||0;
   this.id = config.id||GenerateGuid();
   this.name = config.name||this.id;
   this.dropListWidthAdd=config.dropListWidthAdd||0;
   tracywindyObject[this.id]=this;
   this.width = config.width||150;
   this.otherConfig = config.otherConfig||{};
   this.height = config.height||22;
   this.renderTo = config.renderTo;
   this.scrollContainer = config.scrollContainer;
   this.scrollContainerObject = config.scrollContainerObject;   //DOM元素对象数组
   this.isContainEmpty = (config.isContainEmpty == false)?false:true;
   this.start = this.start||0;
   this.dropListHeight = config.dropListHeight||200;
   this.constDropListHeight = this.dropListHeight;
   this.start = config.start||0;
   this.limit = config.limit||99999;
   this.xmlFileName = config.xmlFileName;
   this.readOnly = config.readOnly;
   this.params = config.params||{};
   this.isCheck = config.isCheck||false;//是否多选
   this.onSelect = config.onSelect; 
   this.onClick = config.onClick; 
   //modify by tracywindy 2013-11-26
   this.filterLoadComplete = config.filterLoadComplete;
   this.loadComplete= config.loadComplete||this.filterLoadComplete;
   this.positionDropIconTopAdd  = config.positionDropIconTopAdd||0;
   this.positionDropIconLeftAdd = config.positionDropIconLeftAdd||0;
   this.readOnlyData = config.readOnlyData||false;
   this.otherAttributes  = config.otherAttributes||'';
   this.isAjaxLenovo    = config.isAjaxLenovo||false;
   this.comboLoadFunc   = config.comboLoadFunc||function(){};
   this.isForceModify   = config.isForceModify||false;
   this.defaultIndex =  -2;
   //this.isAssignInput   = config.isAssignInput||false;
   this.assignInputDisplayInputId = config.assignInputDisplayInputId;
   this.assignInputHiddenInputId = config.assignInputHiddenInputId;
   this.relativeDisplayInputId = config.relativeDisplayInputId;
   this.relativeHiddenInputId =  config.relativeHiddenInputId;
   if(this.relativeDisplayInputId){
	   this.relativeDisplayInput = document.getElementById(this.relativeDisplayInputId);
   }
   if(this.relativeHiddenInputId){
	   this.relativeHiddenInput = document.getElementById(this.relativeHiddenInputId);
   }
   this.isAsync =  (false == config.isAsync) ? false : true;
   
	this.defaultIndex = parseInt(config.defaultIndex);
	if(isNaN(this.defaultIndex))
		this.defaultIndex = -2;
   		
 
   if((config.isViewHistoryTask ||window.isViewHistoryTask)&&!this.isForceModify)
   {
	   this.readOnlyData = true;
   }
   if((false==config.isViewHistoryTask)&&(true != config.readOnlyData)){
	   this.readOnlyData = false;
   }
   if(window.isCompletedTask){
	   this.readOnlyData = true;
   }
   if((this.readOnly!=false) || this.isCheck)
   {
	   this.readOnly = true;
   }
   if(this.readOnlyData)
   {
	   this.readOnly = true;
   }

   
   this.createObj();
};
tracywindyComboBox.prototype.reload=function(){
	if(this.loadMode=='ajax'){
		this.datas=[];
		this.lazyLoad = false;
	}
	this.loadSelect();
};
tracywindyComboBox.prototype.setParam=function(name,value){
    this.params[name]=value;
};
tracywindyComboBox.prototype.setParams=function(params){
    for(var param in params)
    {
        this.params[param] = params[param];
    }
};
//创建数据区
tracywindyComboBox.prototype.AddRow=function(rowData){
	this.datas.push(rowData);
	this.loadData();
	return rowData;
};
tracywindyComboBox.prototype.AddRows=function(rowDatas){
	if(rowDatas instanceof Array)
	{
		for(var index=0;index<rowDatas.length;index++)
		{
			this.datas.push(rowDatas[index]);
		}
	}
	else
	{
		this.datas.push(rowDatas);
	}
	this.loadData();
	return rowDatas;
};
//返回所有行json data 形如[{'value':'1'},{'value':'2'}]
tracywindyComboBox.prototype.getRowsData=function(){
	return this.datas;
};
tracywindyComboBox.prototype.removeRowAt=function(index){
	var rowData = this.datas.splice(index,1);
	this.loadData();
	return rowData;
};
tracywindyComboBox.prototype.addRowAt=function(index,rowData){
	this.datas.splice(index,0,rowData);
	this.loadData();
	return rowData;
};
tracywindyComboBox.prototype.addRowsAt=function(index,rowDatas){
	if(rowDatas instanceof Array)
	{
		for(var rindex=0;rindex<rowDatas.length;rindex++)
		{
			this.datas.splice(index++,0,rowDatas[rindex]);
		}
	}
	else
	{
	   this.datas.splice(index,0,rowDatas);
	}
	this.loadData();
	return rowDatas;
};
tracywindyComboBox.prototype.loadAjaxData=function(isAsync){
	if(typeof(isAsync)=="undefined")isAsync = true;
	//this.loadMask.show();
	if(this.loadMask)
	{
	   this.loadMask.show();
	}
	var params = this.params;
    var xmlFileName = this.xmlFileName;
    var $me = this;
    params['xmlFileName'] = xmlFileName;
    params['start'] = this.start;
    params['limit'] = this.limit;
	ajaxRequest({
        url:this.url,
        async:isAsync,
        success:function(res){
		   $me.jsonData = eval('('+res.responseText+')');
		   $me.datas = $me.jsonData['datas'];
		   $me.loadData();
		   if($me.loadComplete)
		   {
			   $me.loadComplete($me);
		   }
		   if($me.loadMask)
		   {
			   $me.loadMask.hide();
		   }
		   $me.isComplete=true;
        },
        failure:function(res){
        },
        params:params
    });
};

tracywindyComboBox.prototype.createObj=function(){
	var currentObj = this.renderTo;
    //创建主div
	if(!(typeof(this.renderTo)=='object'))
	{
		currentObj = document.getElementById(this.renderTo);
	}
	this.renderToObj = currentObj;
	if(!currentObj){
		alert("combo的renderTo组件未找到:["+this.renderTo+"]");
	}
	currentObj.style.width = (this.width+4)+"px";
	if(currentObj.className){
		if(currentObj.className.indexOf("leftComboContainer")>-1){
			currentObj.style.borderColor="transparent";
		}
	}
	this.comboBoxFrameDiv = document.createElement("div");
	this.comboBoxFrameDiv.style.width = (this.width)+"px";
	this.comboBoxFrameDiv.style.height = this.height+"px";
	this.comboBoxFrameDiv.style.textIndent = "0px";
	this.comboBoxFrameDiv.className = "x-panel-combo-frame-div";
	this.comboBoxFrameDiv.style.padding="0 0 0 0";
	this.comboBoxFrameDiv.style.margin="0 0 0 0";
	try{
		currentObj.appendChild(this.comboBoxFrameDiv);
	}catch(e){
		alert("tracywindyCombo不合法renderTO组件["+this.renderTo+"]");
	}
	this.dom = this.comboBoxFrameDiv;
	this.loadSelect();
	this.setDefaultValue();
};

tracywindyComboBox.prototype.setDefaultValue=function(index){
	var defaultIndex = undefined;
	if(this.defaultIndex != 'undefined')
		defaultIndex = this.defaultIndex;
	else if(index != 'undefined'){
		defaultIndex = parseInt(index);
	}
	if(isNaN(defaultIndex))
		return;


	if(defaultIndex > -1 && defaultIndex < this.datas.length){
		this.setValue(this.datas[defaultIndex][this.valueField]);
	}
	if(this.defaultIndex == -1){
		this.setValue('');
	}
	
};
tracywindyComboBox.prototype.GetOffset = function(object, offset) {
    if (!object)
        return;
    offset.x += object.offsetLeft;
    offset.y += object.offsetTop;

    this.GetOffset (object.offsetParent, offset);
};

tracywindyComboBox.prototype.GetScrolled = function (object, scrolled) {
    if (!object)
        return;
    scrolled.x += object.scrollLeft;
    scrolled.y += object.scrollTop;

    if (object.tagName.toLowerCase () != "html") {
    	this.GetScrolled (object.parentNode, scrolled);
    }
};

tracywindyComboBox.prototype.GetTopLeft = function (div) {
   // var div = document.getElementById ("myDiv");

    var offset = {x : 0, y : 0};
    this.GetOffset (div, offset);

    var scrolled = {x : 0, y : 0};
    this.GetScrolled (div.parentNode, scrolled);

    var posX = offset.x - scrolled.x;
    var posY = offset.y - scrolled.y;
    /*alert ("The top-left corner of the div relative to the top-left corner of the browser's client area: \n" 
            + " horizontal: " + posX + "px\n vertical: " +  posY + "px");*/
    return {posX:posX,posY:posY};
};

tracywindyComboBox.prototype.Offset=function(){
 var e = this.showValueDiv;
 var t = 0;
 var l = 0;
 var w = this.width;
 var h = this.height-2;
 var offset = this.GetTopLeft(e);
 t = offset.posY;
 l = offset.posX;
 /*while(e)
 {
	  t+=e.offsetTop;
	  l+=e.offsetLeft;
	  //t-=e.scrollTop;
	  //l-=e.scrollLeft;
	  e=e.offsetParent;
 }
 var el = this.showValueDiv.parentNode;
 while(el)
 {
	  if(el.className){
		  if( (el.className.indexOf("panel-body")>-1) && ((el.className.indexOf("window-body")>-1)||(el.className.indexOf("dialog-content")>-1))){
			  t-=el.scrollTop;
			  l-=el.scrollLeft;
			  break;
		  }
	  }
	  el = el.parentNode;
 }
 if(document.body)
 {
	// l-=document.body.scrollLeft;
	// t-=document.body.scrollTop;
 }
 var workflowFormContainer = document.getElementById('id_workflowFormContainer_inner');
 if(workflowFormContainer)
 {
	 if(isParent(this.comboBoxFrameDiv,workflowFormContainer))
	 {
		 l-= workflowFormContainer.scrollLeft;
		 t-= workflowFormContainer.scrollTop;
	 }
 }

 if(this.scrollContainer){
	 var scrollContainerArr =  this.scrollContainer.split(",");
	 for(var i =0;i<scrollContainerArr.length;i++){
		 var currentScrollContainer = scrollContainerArr[i];
		 if (!(typeof(this.scrollContainer) == 'object')) {
		     currentScrollContainer = document.getElementById(currentScrollContainer);
		 }
		 if((0 == currentScrollContainer.offsetHeight) || ("none" == currentScrollContainer.style.display)){
			 continue;
		 }
		 l-= currentScrollContainer.scrollLeft;
		 t-= currentScrollContainer.scrollTop;
	 }

 }else if(this.scrollContainerObject){  //DOM元素数组
 	for(var i = 0; i < this.scrollContainerObject.length; i++){
 		currentScrollContainer = this.scrollContainerObject[i];
 		if((0== currentScrollContainer.offsetHeight) || ("none" == currentScrollContainer.style.display)){
 			continue;
 		}
 		l -= currentScrollContainer.scrollLeft;
 		t -= currentScrollContainer.scrollTop;
 	}
 }*/

 return {
	  top    : t,
	  left   : l-2,
	  width  : w,
	  height : h
 };
};
var globalCurrentSelectedCombo = null;
tracywindyComboBox.prototype.loadSelect=function(){
	 var domInnerHTML = "";
	 var hiddenInputId = 'selectOfValue_'+this.id;
	 var displayInputId = 'selectOfRawValue_'+this.id;
	 if(!this.assignInputHiddenInputId){
		 domInnerHTML+= "<input type='hidden' id='selectOfValue_"+this.id+"' name='"+this.name+"'  value='' />";
	 }else{
		 hiddenInputId = this.assignInputHiddenInputId;
	 }
		 
	 if(!this.assignInputDisplayInputId){
		 domInnerHTML+= "<input type='hidden' id='selectOfRawValue_"+this.id+"' name='rawValue_"+this.name+"'  "+this.otherAttributes+"  value='' />";
	 }else{
		 displayInputId = this.assignInputDisplayInputId;
	 }
	 this.dom.innerHTML = domInnerHTML;
	 var idDivValueHidden = document.getElementById(hiddenInputId);
	 this.valueHidden = idDivValueHidden;
	 var idDivRawValueHidden = document.getElementById(displayInputId);
	 this.rawValueHidden = idDivRawValueHidden;
	 this.isComplete = false;
	 //第三步：虚拟一个div出来代替select
	  var iDiv = document.createElement("input");
	  this.showValueDiv = iDiv;
	  this.iDiv = iDiv;
	  iDiv.id="selectOf_" + this.id;
	  iDiv.readOnly = this.readOnly;
	  iDiv.style.width=(this.width-20)+"px";
	  //iDiv.className = "x-panel-combo-droplist-input";
	  // iDiv.style.borderRight = "1px solid #DDD";
	  //iDiv.style.borderColor = "#DDD";
	  if(this.readOnlyData)
	  {
		  iDiv.className = "td-content-input td-content-readonly";
	  }
	  if(this.readOnly)
	  {
		  iDiv.className = "td-content-input td-content-readonly";
	  }
	  addClass(iDiv,"x-panel-combo-droplist-input");
	  //iDiv.style.backgroundPosition=((20)+" "+(-1+this.positionDropIconTopAdd));
	  this.relayFinish = true;
	  window[this.id+'_setTimeOutFunc'] = function(comboId){
		  var combo = tracywindyObject[comboId];
		  var $me = combo;
		  $me.relayFinish=false;
		   combo.value = "";
		   combo.rawValue="";
		   combo.valueHidden.value = "";
		   combo.rawValueHidden.value = ""; 
		  if($me.isAjaxLenovo)
		  {
			  var params = $me.params; 
			  $me.isComplete=false;
			  params['currentRawValue'] = combo.iDiv.value;
			  ajaxRequest({
			        url:$me.url,
			        success:function(res){
					   $me.jsonData = eval('('+res.responseText+')');
					   if($me.jsonData.norecord=='true')
					   {
						   $me.datas = [];
					   }
					   else
					   {
						   $me.datas = $me.jsonData['datas'];
					   }
					   combo.setFilterRawValue(combo.iDiv.value,''); 
					   /*modify by tracywindy 2013-11-26*/
					   if($me.filterLoadComplete)
					   {
						   $me.filterLoadComplete($me);
					   }
					   if($me.loadMask)
					   {
						   $me.loadMask.hide();
					   }
					   $me.isComplete=true;
					   combo.relayFinish = true;
			        },
			        failure:function(res){
			        },
			        params:params
			    }); 
			    //combo.setFilterRawValue(combo.iDiv.value,''); 
		  }else{
			  if(combo.iDiv.value=='')
			  {
				  combo.setFilterRawValue(combo.iDiv.value,''); 
			  }
			  else
			  {
				  combo.setFilterRawValue(combo.iDiv.value,new RegExp("^.*"+combo.iDiv.value.replace(/\(/gi,"\\(").replace(/\)/gi,"\\)")+"+.*$")); 
			  }
			  combo.relayFinish = true;
		  }
	  };
	  var keyUpFunc = (function($me){
		  return function(e){
			  if($me.relayFinish)
			  {
				  $me.relayFinish=false;
				  setTimeout("window['"+$me.id+"_setTimeOutFunc']('"+$me.id+"')",100);
			  }
			  var ev = getEvent(e);
			  ev.cancelBubble = true;
			  try
			  {
				 ev.stopDefault(); 
			  }catch(ex){};
		      
		  };
	  })(this);
	  if(iDiv.readOnly==false)
	  {
		  if(!this.readOnlyData){
			  iDiv.onkeyup = keyUpFunc;
		  }
	  }
	  else
	  {
		  if(!this.readOnlyData)iDiv.onkeydown=function(ev){
			    var e = getEvent(ev);
				var keyCode = e.keyCode||e.charCode;
				var pass = true;
				if(keyCode==8)
				{
				  pass = false;
				}
				if(!pass){
					cancelBubble(e);
					e.returnValue = pass;
					e.cancelBubble = true;
					try
					{
						e.stopDefault();
					}catch(e)
					{
						e.returnValue = pass;
					}
				}
		  };
	  }
	  var comboTable = document.createElement("table");
	  comboTable.className = "x-panel-combo-table";
	  this.comboBoxFrameDiv.appendChild(comboTable);
	  var comboTBody = document.createElement("tbody");
	  comboTable.appendChild(comboTBody);
	  var comboTBodyRow =   document.createElement("tr");
	  comboTBody.appendChild(comboTBodyRow);
	  var comboTBodyRowTdInput = document.createElement("td");
	  comboTBodyRowTdInput.appendChild(iDiv);
	  comboTBodyRowTdInput.style.padding = "0px";
	  comboTBodyRowTdInput.style.margin = "0px";
	  comboTBodyRowTdInput.style.textIndent = "0px";
	  comboTBodyRow.appendChild(comboTBodyRowTdInput);
	  
	  this.comboBoxFrameDiv.appendChild(comboTable);
	  
	  var iconDiv = document.createElement("div");
	  this.comboBoxFrameDiv.dropIconDiv = iconDiv;
	  this.dropIconDiv = iconDiv;
	  with(iconDiv)
	  {
		  className = "x-panel-combo-dropIcon";
	  }
	  if(!this.readOnlyData)
	  {
		   this.comboBoxFrameDiv.onmouseover=function(){
		       addClass(this.dropIconDiv,"x-panel-combo-dropIcon-alt");
		    };
		   this.comboBoxFrameDiv.onmouseout=function(){
		       removeClass(this.dropIconDiv,"x-panel-combo-dropIcon-alt");
		    };
	  }
	  var comboTBodyRowTdIcon = document.createElement("td");
	  comboTBodyRowTdIcon.style.padding = "0px";
	  comboTBodyRowTdIcon.style.margin = "0px";
	  comboTBodyRowTdIcon.style.textIndent = "0px";
	  comboTBodyRowTdIcon.appendChild(iconDiv);
	  comboTBodyRow.appendChild(comboTBodyRowTdIcon);
	  if(this.readOnlyData)
	  {
		  this.dropIconDiv.style.display="none";
		  iDiv.style.width=(this.width)+"px";
	  }
	  else
	  {
		  iDiv.style.width=(this.width-20)+"px";
	  }
	 /* var idDivValueHidden = document.createElement("<input type='hidden' id='selectOfValue_"+this.id+"' name='"+this.name+"' value=''>");
	  this.comboBoxFrameDiv.appendChild(idDivValueHidden);
	  this.valueHidden = idDivValueHidden;
	  var idDivRawValueHidden = document.createElement("<input type='hidden' id='selectOfRawValue_"+this.id+"' name='rawValue_"+this.name+"' value=''>");
	  this.comboBoxFrameDiv.appendChild(idDivRawValueHidden);
	  this.rawValueHidden = idDivRawValueHidden;*/
	  //移除原来的下拉框
	  if(this.dropDiv)
	  {
		 document.body.removeChild(this.dropDiv); 
	  }
	 //第一步：取得Select所在的位置
	 //var offset=this.Offset();
	 var cDiv = document.createElement("div");
	 cDiv.id="selectChild" + this.id;
	 cDiv.className ="x-panel-combo-droplist-div";
	 cDiv.style.display="block";
	 //cDiv.style.width=(offset.width+2) + "px";
	 //cDiv.style.top=(offset.top+offset.height+2+this.topAdd) + "px";
	 //cDiv.style.left=(offset.left+1+this.leftAdd) + "px";
	/* if(this.dropListHeight)
	 {
		 cDiv.style.height=this.dropListHeight+"px";
	 }*/
	 this.dropDiv = cDiv;
	 var uUl = document.createElement("ul");
	 uUl.className =  "x-panel-combo-droplist-ul";
	 uUl.id="uUlchild" + this.id;
	 cDiv.appendChild(uUl);
	 //创建loadMask
	 //this.loadMask = new tracywindyLoadMask(cDiv,'数据加载中，请稍等...');
	 this.dropList = uUl;
	 document.body.appendChild(cDiv);  
	 this.childCreate=false;
	 //模拟鼠标点击
	 var clickHandler = (function($me){
		 return function(e){
			 
			 if($me.onClick){
				 $me.onClick($me);
			 }
			 
			 initComboboxAllHiddenEvent();
			 if (document.getElementById("selectChild" + $me.id)){//判断是否创建过div
				   if ($me.childCreate && $me.readOnly){//非只读情况下不关闭下拉框
				    //判断当前的下拉是不是打开状态，如果是打开的就关闭掉。是关闭的就打开。
				    document.getElementById("selectChild" + $me.id).style.display="none";
				    $me.childCreate=false;
				   }else{
					     if(!$me.readOnly)
					     {
					    	 /*$me.value    = '';
					    	 $me.rawValue = $me.iDiv.value;
					    	 if($me.loadMode=='ajax')
					    	 {
					    		 $me.loadAjaxData(false);    
					    	 }
					    	 else if($me.loadMode=='local')
					    	 {
					    		 $me.loadData();
					    	 }
					    	 $me.setFilterRawValue($me.iDiv.value);*/
					     }
					  	 if($me.loadMode=='ajax')
				    	 {
					    	 if($me.lazyLoad && (0==$me.datas.length)){
					    		 $me.loadAjaxData(false); 
					    	 }
				    	 }
					     var offset_inner=$me.Offset();
						 var cDiv_inner = $me.dropDiv;
						 cDiv_inner.style.width=(Math.max($me.dropList.offsetWidth,$me.dropList.scrollWidth)+$me.dropListWidthAdd) + "px";
						 //cDiv_inner.style.width=(offset_inner.width+$me.dropListWidthAdd) + "px";
						 cDiv_inner.style.top=(offset_inner.top+offset_inner.height+2+$me.topAdd)+ "px";
						 var clientHeight = (document.documentElement.clientHeight);
						 if((offset_inner.top+offset_inner.height+2+$me.dropListHeight)>=(clientHeight-1))
						 {
							 cDiv_inner.style.top=(offset_inner.top-$me.dropListHeight-2+$me.topAdd) + "px"; 
						 }
						 cDiv_inner.style.left=(offset_inner.left+2+$me.leftAdd) + "px";
					    if(!$me.isComplete)
					    {
					    	 if($me.loadMode!='local')
							 {
								//创建loadMask
							    $me.loadMask=new tracywindyLoadMask($me.dropDiv,'数据加载中，请稍等...');
							    $me.loadMask.show();
							 }
					    }
					     //if(!$me.readOnly)$me.setRawValue($me.iDiv.value);
					     hiddenAllComboBox();
					     document.getElementById("selectChild" + $me.id).style.display="block";
					     var oldWidth  = Math.max($me.dropList.offsetWidth,$me.dropList.scrollWidth);
					     var attrOldWidth = $me.dropList.getAttribute("oldWidth");
					     if(0 < parseInt(attrOldWidth)){
					    	 oldWidth = parseInt(attrOldWidth);
					     }
					     var oldHeight = Math.max($me.dropList.offsetHeight,$me.dropList.scrollHeight);
					     //var oldWidthAdd = 0;
					     var newWidth    = oldWidth;
					     if(oldHeight > $me.dropListHeight){
					    	 newWidth+=18;
					     }
					     if((oldWidth)<$me.width){
					    	 cDiv_inner.style.width=(Math.max(newWidth,$me.width)+$me.dropListWidthAdd) + "px";
					     }else{
					    	 cDiv_inner.style.width=(Math.max(newWidth,$me.width)+$me.dropListWidthAdd) + "px";
					     }
					     var currentClientWidth = document.documentElement.clientWidth;
					     var currentLeft = (offset_inner.left+2+$me.leftAdd);
				    	 if(( currentLeft + parseInt(cDiv_inner.style.width)) > (currentClientWidth)){
				    		 cDiv_inner.style.overflowX="auto";
				    		 cDiv_inner.style.width=(currentClientWidth-currentLeft-5) + "px";
				    	 }else{
				    		 cDiv_inner.style.overflowX="hidden";
				    	 }
					     $me.dropList.setAttribute("oldWidth",oldWidth);
					     if(newWidth<$me.width)$me.dropList.style.width = $me.width+"px";
					     else $me.dropList.style.width = oldWidth+"px";
					     $me.childCreate=true;
				   }
			 }
			  var ev = getEvent(e);
			  ev.cancelBubble = true;
			  try
			  {
				 ev.stopDefault(); 
			  }catch(ex){};
		 };
	 })(this);
	 if(!this.readOnlyData)
	 {
		 this.comboBoxFrameDiv.onclick=clickHandler;
		 this.comboBoxFrameDiv.onmousedown=function(e){			  
			 cancelBubble(e);
		};
	 }
	 else
	 {
		 this.comboBoxFrameDiv.onclick=null;
		 this.comboBoxFrameDiv.onmousedown=null;
	 }
	 if((this.loadMode=='ajax') && this.lazyLoad){
		  var valueHidden = this.valueHidden ;
		  valueHidden.value = this.value;
		  var rawValueHidden = this.rawValueHidden; 
		  rawValueHidden.value=this.rawValue;
		  this.showValueDiv.value=this.rawValue;
	 }
	 else{
		 //将select中默认的选项显示出来
		 var tValue=this.rawValue;
		 iDiv.value=tValue;
		 if(this.loadMode=='ajax')
		 {
			  this.loadAjaxData(this.isAsync);    
		 }
		 else if(this.loadMode=='local')
		 {
		     this.loadData();
		 }
	 }
};
tracywindyComboBox.prototype.setValue=function(value){
	this.showValueDiv.value='';
	this.value = value;
	this.rawValue="";
	this.loadData();
};
tracywindyComboBox.prototype.setFilterValue=function(value,filter){
	this.grantValue = true;
	this.value = value;
	this.rawValue="";
	this.loadData(filter);
};
tracywindyComboBox.prototype.setRawValue=function(rawValue){
	this.showValueDiv.value='';
	this.grantValue = true;
	this.rawValue = rawValue;
	this.value = "";
	this.loadData();
};
tracywindyComboBox.prototype.setFilterRawValue=function(rawValue,filter){
	this.rawValue=rawValue;
	this.value = "";
	this.loadData(filter);
};
tracywindyComboBox.prototype.getValue=function(){
	return this.valueHidden.value;
};
tracywindyComboBox.prototype.getRawValue=function(){
	return this.rawValueHidden.value;
};
tracywindyComboBox.prototype.loadData=function(filter){
      this.lazyLoad = false;
	  //清空数据
	  // var itemHeight = 20;
	   var itemCount = 0;
	   this.dropList.innerHTML = '';
	   
	   var datas = [];
	   var obj = {};
	   var isOwnerData = false;
	   if(this.datas){
		   if(this.datas.length == 1){
			   for(var p in this.datas[0]){
				   if(this.datas[0][p]){
					   isOwnerData = true;
					   break;
				   }
			   }
		   }
		   else
		   {
			   isOwnerData = true;
		   }
	   }
	   if(!isOwnerData){
		   this.datas = [];
	   }
	   if(this.datas.length>0)
	   {
		   var rowData = this.datas[0];
		   for(var p in rowData)
		   {
			   obj[p] = '';
		   }
		   if(!this.isCheck && this.isContainEmpty)datas.push(obj);
	   }
	   for(var ii=0;ii<this.datas.length;ii++)
	   {
		   datas.push(this.datas[ii]);
	   }
	   var isFoundData = false;
	   //重置返回数值
	   var valueHidden = this.valueHidden ;
	   valueHidden.value = '';
	   var rawValueHidden = this.rawValueHidden; 
	   rawValueHidden.value='';
	   if(this.readOnly||this.grantValue)
	   {
	      this.showValueDiv.value='';
	   }
	   this.grantValue = false;
	   for (var i=0;i<datas.length;i++)
	   {
		var data = datas[i];
		if(filter)
		{
			if(!filter.test(data[this.displayField]))
			{
				continue;
			}
		}
		isFoundData = true;
		itemCount++;
	    var lLi= document.createElement("li");
	    //document.createElement("<li name='dropListLi_"+this.id+"'>");
	    lLi.className ="x-panel-combo-droplist-li";
	    lLi.innerHTML=data[this.displayField]||'';
	    lLi.realValue = data[this.valueField]||'';
	    lLi.rowData = data;
	    this.dropList.appendChild(lLi);
	    if(this.isCheck)
	    {
	    	var checkString = "";
	    	if(this.value)
	    	{
	    		var realValues = this.value.split(",");
	    		for(var rIndex=0;rIndex<realValues.length;rIndex++)
		    	{
	    		   var currentValue = realValues[rIndex];
	    		   if(lLi.realValue == currentValue)
	  		       {
	    			   checkString = "checked";
	  		       }
		    	}
	    	}
	    	else if(this.rawValue)
	    	{
	    		var realRawValues = this.rawValue.split(",");
	    		for(var rIndex=0;rIndex<realRawValues.length;rIndex++)
		    	{
	    		   var currentRawValue = realRawValues[rIndex];
	    		   if(lLi.innerHTML == currentRawValue)
	  		       {
	    			   checkString = "checked";
	  		       }
		    	}
	    	}
	    	lLi.innerHTML="<input type='checkbox' class='x-panel-combo-droplist-li-checkbox' name='dropListLiCheckbox_"+this.id+"' "+checkString+" value='"+lLi.realValue+"' rawValue='"+lLi.innerHTML+"'/>"+(data[this.displayField]||'');
	    }
	    else
	    {
		    if(this.value!='')
		    {
		       if(this.value == lLi.realValue)
		       {
		    	   this.showValueDiv.value=lLi.innerHTML;
		    	   valueHidden.value = lLi.realValue;
				   rawValueHidden.value = lLi.innerHTML;
				   addClass(lLi,"x-panel-combo-droplist-li-click");
		       }
		    }
		    else if(this.rawValue!='')
		    {
		       if(this.rawValue == lLi.innerHTML)
			   {
		    	   if(this.showValueDiv.value != this.rawValue)this.showValueDiv.value=lLi.innerHTML;
		    	   valueHidden.value = lLi.realValue;
				   rawValueHidden.value = lLi.innerHTML;
				   addClass(lLi,"x-panel-combo-droplist-li-click");
			   }
		    }
		    //为li标签添加鼠标事件
		    lLi.onmouseover=function(){
		       addClass(this,"x-panel-combo-droplist-li-alt");
		    };
		    lLi.onmouseout=function(){
		       removeClass(this,"x-panel-combo-droplist-li-alt");
		    };
		    var clickHandler = (function($me,data,valueHidden,rawValueHidden){
				 return function(e){
					 //同时我们把下拉的关闭掉。
				     document.getElementById("selectChild" + $me.id).style.display="none";
				     $me.childCreate=false;
				     $me.showValueDiv.value=this.innerHTML;
				     valueHidden.value = this.realValue;
				     rawValueHidden.value = this.innerHTML;
					   //modify by tracywindy begin
					   var relativeHiddenInput = $me.relativeHiddenInput||{} ;
					   relativeHiddenInput.value = valueHidden.value;
					   var relativeDisplayInput = $me.relativeDisplayInput||{}; 
					   relativeDisplayInput.value=rawValueHidden.value;
					   //modify by tracywindy end
				     var lis = document.getElementById('uUlchild'+$me.id).getElementsByTagName("li");
				     for(var i =0;i<lis.length;i++)
				     {
				    	 var li = lis[i];
				    	 removeClass(li,"x-panel-combo-droplist-li-click");
				     }
				     addClass(this,"x-panel-combo-droplist-li-click");
				     if($me.onSelect){
						   $me.onSelect($me,data);
					   }
				     $me.isTriggerSelected = true;
					  var ev = getEvent(e);
					  ev.cancelBubble = true;
					  try
					  {
						 ev.stopDefault(); 
					  }catch(ex){};
				 };
			 })(this,data,valueHidden,rawValueHidden);
		    lLi.onclick=clickHandler;
	    }
	   }
	   if(this.isCheck)
	   {
		   this.value = "";
		   this.rawValue="";
		   valueHidden.value = "";
		   rawValueHidden.value = "";
		   var realAllValue = "";
		   var realAllRawValue = "";
		   var liChecks = document.getElementsByName("dropListLiCheckbox_"+this.id);
		   for(var lcIndex=0;lcIndex<liChecks.length;lcIndex++)
		   {
			   var liCheck = liChecks[lcIndex];
			   var rowData = liCheck.getAttribute("rowData");
			   var checkClickFunc = (function($me,data,valueHidden,rawValueHidden){
				   return function(e){
					   valueHidden.value = "";
					   rawValueHidden.value = "";
					   $me.value ="";
					   $me.rawValue ="";
					   
					   var realAllValue = "";
					   var realAllRawValue = "";
					   var liChecks = document.getElementsByName("dropListLiCheckbox_"+$me.id);
					   for(var lccIndex=0;lccIndex<liChecks.length;lccIndex++)
					   {
						   var liCheck = liChecks[lccIndex];
						   if(liCheck.checked)
						   {
							   realAllValue+=liCheck.value;
							   realAllValue+=",";
							   realAllRawValue+=liCheck.getAttribute("rawValue");
							   realAllRawValue+=",";
						   }
					   }
					   if(realAllValue.length>0)
					   {
						   $me.value = realAllValue.substring(0,realAllValue.length-1);
						   $me.rawValue = realAllRawValue.substring(0,realAllRawValue.length-1);
					   }
					   valueHidden.value = $me.value;
					   rawValueHidden.value = $me.rawValue;
					   $me.showValueDiv.value=  $me.rawValue;
					   //modify by tracywindy begin
					   var relativeHiddenInput = $me.relativeHiddenInput||{} ;
					   relativeHiddenInput.value = valueHidden.value;
					   var relativeDisplayInput = $me.relativeDisplayInput||{}; 
					   relativeDisplayInput.value=rawValueHidden.value;
					   //modify by tracywindy end
					   if($me.onSelect){
						   $me.onSelect($me,data);
					   }
						  var ev = getEvent(e);
						  ev.cancelBubble = true;
						  try
						  {
							 ev.stopDefault(); 
						  }catch(ex){};
				   };
			   })(this,rowData,valueHidden,rawValueHidden);
			   if(liCheck.checked)
			   {
				   realAllValue+=liCheck.value;
				   realAllValue+=",";
				   realAllRawValue+=liCheck.getAttribute("rawValue");
				   realAllRawValue+=",";
			   }
			   liCheck.onclick=checkClickFunc;
		   }
		   if(realAllValue.length>0)
		   {
			   this.value = realAllValue.substring(0,realAllValue.length-1);
			   this.rawValue = realAllRawValue.substring(0,realAllRawValue.length-1);
		   }
		   valueHidden.value = this.value;
		   rawValueHidden.value = this.rawValue;
		   this.showValueDiv.value=this.rawValue;
	   }
	   else
	   {
		  this.value=valueHidden.value;
		  this.rawValue=rawValueHidden.value;
	   }
	   //modify by tracywindy begin
	   var relativeHiddenInput = this.relativeHiddenInput||{} ;
	   relativeHiddenInput.value = valueHidden.value;
	   var relativeDisplayInput = this.relativeDisplayInput||{}; 
	   relativeDisplayInput.value=rawValueHidden.value;
	   //modify by tracywindy end
	   if(!isFoundData){
		    var lLi= document.createElement("li");
		    //document.createElement("<li name='dropListLi_"+this.id+"'>");
		    lLi.className ="x-panel-combo-droplist-li";
		    lLi.innerHTML='无数据';
		    lLi.realValue = '';
		    lLi.rowData = {};
		    this.dropList.appendChild(lLi);
	   }
	   this.dropDiv.style.display="block";
	   this.dropListHeight = this.dropList.offsetHeight>0?this.dropList.offsetHeight:this.dropList.scrollHeight;//Math.max(this.dropList.offsetHeight,this.dropList.scrollHeight);
	   if(typeof(filter) =='undefined')
	   {
		  this.dropDiv.style.display="none";
	   }
	   this.dropListHeight = Math.min(this.dropListHeight,this.constDropListHeight);
	   this.dropDiv.style.height = this.dropListHeight+"px";
	   if(window.isPagePrint){
		   this.showValueDiv.style.width = Math.max(this.showValueDiv.offsetWidth,this.showValueDiv.scrollWidth)+"px";
	   }
	   if(typeof(filter) !='undefined'){
		     var $me = this;
		     var offset_inner=$me.Offset();
			 var cDiv_inner = $me.dropDiv;
			 
		     var oldWidth  = Math.max($me.dropList.offsetWidth,$me.dropList.scrollWidth);
		     var attrOldWidth = $me.dropList.getAttribute("oldWidth");
		     if(0 < parseInt(attrOldWidth)){
		    	 oldWidth = parseInt(attrOldWidth);
		     }
		     var oldHeight = Math.max($me.dropList.offsetHeight,$me.dropList.scrollHeight);
		     //var oldWidthAdd = 0;
		     var newWidth    = oldWidth;
		     if(oldHeight > $me.dropListHeight){
		    	 newWidth+=18;
		     }
		     if((oldWidth)<$me.width){
		    	 cDiv_inner.style.width=(Math.max(newWidth,$me.width)+$me.dropListWidthAdd) + "px";
		     }else{
		    	 cDiv_inner.style.width=(Math.max(newWidth,$me.width)+$me.dropListWidthAdd) + "px";
		     }
		     $me.dropList.setAttribute("oldWidth",oldWidth);
		     if(newWidth<$me.width)$me.dropList.style.width = $me.width+"px";
		     else $me.dropList.style.width = oldWidth+"px";
		     
			 cDiv_inner.style.top=(offset_inner.top+offset_inner.height+2+$me.topAdd)+ "px";
			 var clientHeight = (document.documentElement.clientHeight);
			 if((offset_inner.top+offset_inner.height+2+$me.dropListHeight)>=(clientHeight-1))
			 {
				 cDiv_inner.style.top=(offset_inner.top-$me.dropListHeight-2+$me.topAdd) + "px"; 
			 }
	   }
  };
