var tracywindyCommonUserSelection = function(config){
	  this.objectType='commonSelection';
	  //#######可配置项
	  //选择器种类：user（用户选择）/ dept（部门选择） / deptRole（角色选择）/ group（群组选择）
	  this.type               = config.type||'user';
	  //是否多选
	  this.isMultiSelect      = config.isMultiSelect||false;
	  //是否允许选择空值
	  this.isAllownChoseEmpty = config.isAllownChoseEmpty||false;
	  //将要赋值id的隐藏域（用逗号（,）隔开）
	  var hiddenInput         = config.hiddenInput;
	  //将要赋值显示文本的显示域（用逗号（,）隔开）
	  var displayInput        = config.displayInput;
	  //提示性信息
	  this.displayPromit      = config.displayPromit||"人员";
	  //选择成功后回调
	  this.choseCallBack      = config.choseCallBack;
	  //#############
	  this.draggable = (false == config.draggable)?false:true;
	  this.windowTop = config.windowTop||20;
	  this.params    = config.params||{};
	  
	  this.filterDeptid=config.filterDeptid||"";//根据ID选择主部门下全部子部门
	  this.queryDeptText=config.queryDeptText||"";//根据部门编号或名称选择指定部门
	  
	  //###########################
	  var  perfixUrl          = getRootPath();
	  this.leftUrl            = "";
	  this.rightUrl           = "";
	  switch(this.type){
		  case "user":{
			  this.leftUrl=(perfixUrl+'/table/getDeptWithUserTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getUserTreeData.action');
			  this.displayPromit="人员";break;
		  }
		  case "dept":{
			  this.leftUrl=(perfixUrl+'/table/getDeptTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getDeptSelectedTreeData.action');
			  this.displayPromit="部门";break;}
		  case "deptRole":{
			  this.leftUrl=(perfixUrl+'/table/getDeptWithRoleTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getRoleTreeData.action');
			  this.displayPromit="角色";break;}
		  case "group":{
			  this.leftUrl=(perfixUrl+'/table/getGroupTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getGroupSelectedTreeData.action');
			  this.displayPromit="群组";break;}
		  case "relation":{
			  this.leftUrl=(perfixUrl+'/table/getRelationTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getRelationSelectedTreeData.action');
			  this.displayPromit="关系";break;}
		  case "definedRelation":{
		  		this.leftUrl=(perfixUrl + '/table/getDefinedRelationTreeData.action');
		  		this.rightUrl = (perfixUrl + '/table/getDefinedRelationSelectedTreeData.action');
		  		this.displayPromit="预定义关系";
		  		break;}	
		  case "step":{
			  if(!this.params["deployId"]){
				  alert("部署参数deployId不能为空");
				  return;
			  }
			  this.leftUrl=(perfixUrl+'/table/getStepTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getStepSelectedTreeData.action');
			  this.displayPromit="历史步骤";break;
		  }
		  case "synchronizedWorkflowDesigners":{
			  this.leftUrl=(perfixUrl+'/jbpm/getSynchronizedWorkflowDesignersTreeData.action');
			  this.rightUrl=(perfixUrl+'/jbpm/getSynchronizedWorkflowDesignersSelectedTreeData.action');
			  this.displayPromit="同步流程";
			  break;
		  }
		  default:{
			  this.leftUrl=(perfixUrl+'/table/getDeptWithUserTreeData.action');
			  this.rightUrl=(perfixUrl+'/table/getUserTreeData.action');
			  this.displayPromit="人员";break;}
	  }
	  if(config.displayPromit){
		  this.displayPromit = config.displayPromit;
	  }
	  var selectedUserHiddenIdsDom = null;
	  //创建主div
	  if(!(typeof(hiddenInput)=='object'))
	  {
		  selectedUserHiddenIdsDom = document.getElementById(hiddenInput);
	  }
	  else{
		  selectedUserHiddenIdsDom = hiddenInput;
	  }
	 
	  if(!selectedUserHiddenIdsDom){
			//alert("tracywindyTable[CommonUserSelection],没有对应的html元素<"+hiddenInput+">");
			return;
	  }
	  
	  this.selectedUserHiddenIdsDom = selectedUserHiddenIdsDom;
	  
	  var selectedUserDisplayRealnameDom = null;
	 
	  //创建主div
	  if(!(typeof(displayInput)=='object'))
	  {
		  selectedUserDisplayRealnameDom = document.getElementById(displayInput);
	  }
	  else{
		  selectedUserDisplayRealnameDom = displayInput;
	  }
	  
	  if(!selectedUserDisplayRealnameDom){
			alert("tracywindyTable[CommonUserSelection],没有对应的html元素<"+displayInput+">");
			return;
	  }
	  this.selectedUserDisplayRealnameDom = selectedUserDisplayRealnameDom;
	  //创建window
	  this.id       = config.id||GenerateGuid();
	  var $id       = this.id;
	  tracywindyObject[this.id]= this;
	  //创建window结束
	  this.loadMask = null;
	  this.loadCommonWindow = function(){
		    if(null == this.loadMask){
				   this.loadMask = new tracywindyLoadMask(document.body/*"id_detailInfo"+this.id+"WindowContianer"*/,'数据加载中  请稍后...');
			    }
			this.loadMask.show();
			//判断隐藏值是不是mini渲染的
			if($(this.selectedUserHiddenIdsDom).attr("class") && $(this.selectedUserHiddenIdsDom).attr("class").indexOf("mini-queryinput") > -1){
				this.initComonUserSelectionDeptTree(mini.get(hiddenInput) ? mini.get(hiddenInput).getValue() : "");
				this.initComonUserSelectionUserTree(mini.get(hiddenInput) ? mini.get(hiddenInput).getValue() : "");
			}else{
				this.initComonUserSelectionDeptTree(this.selectedUserHiddenIdsDom.value);
				this.initComonUserSelectionUserTree(this.selectedUserHiddenIdsDom.value);
			}
			//加载初始化树形
      };
      this.setParams = function(params){
    	  for(var p in params){
    		  this.params[p] = params[p];
    	  }
      };
      this.reload = function(){
    	  this.loadCommonWindow();
      };
      this.show = function(){
    	    jQuery(this.selectedUserDisplayRealnameDom).blur();
			/*********/
    	    this.loadCommonWindow(); 
    	  //#############
			jQuery("#id_detailInfo"+this.id+"WindowContianer").show();
			jQuery("#id_detailInfo"+this.id+"WindowContianer").dialog({top:this.windowTop,zIndex:9999999,draggable:this.draggable});
			jQuery("#id_detailInfo"+this.id+"WindowContianer").dialog('open');
      };
      var isDeptTreeLoaded = false;
      var isUserTreeLoaded = false;
      this.initComonUserSelectionDeptTree = function(selectedUserIds){
    	isDeptTreeLoaded = false;
    	this.loadMask.show();
    	var $loadMask = this.loadMask;
    	var $me = this;
  		jQuery('#id_'+this.id+'_menu_tabs_menu').tree({
			url:this.leftUrl,
			checkbox:true,
			cascadeCheck:false,
			onlyLeafCheck:("dept" == this.type) ? false : true,
			onLoadSuccess:function(){
  			   isDeptTreeLoaded = true;
		       if(isUserTreeLoaded){
		    	   $loadMask.hide();
		       }
  		    },
  		  /*  onLoadError:function(){
  		    	alert('请核实参数后，重新加载！！！');
  		    	$loadMask.hide();
  		    },*/
			onBeforeLoad:function(node,params){
  		    	$loadMask.show();
				params['pid']  = node ? node.id : "0";
				
				if($me.filterDeptid !=""){
					params['pid']  = node ? node.id : $me.filterDeptid;
				}
				
				if($me.queryDeptText!=""){
					params['queryDeptText']=$me.queryDeptText;
				}
				
				if(selectedUserIds){
					params['selectedUserIds']  = selectedUserIds;
			    }
			    var queryText = jQuery("#id_"+$id+"_queryWorkflowsTableInput").val();
			    if(queryText){
			    	params['queryText']  = queryText;
				}
				if(!node){
					params['init'] = true; 
				}
				for(var p in $me.params){
					params[p] = $me.params[p];
				}
		    }
  		});
      };
      this.initComonUserSelectionUserTree = function(selectedUserIds){
    	  		  var $me = this;
    	          isUserTreeLoaded = false;
			      this.loadMask.show();
			      var $loadMask = this.loadMask;
				  jQuery('#id_'+this.id+'_menu_main_content_div').tree({
						checkbox:true,
						cascadeCheck:false,
						onlyLeafCheck:true,
						onLoadSuccess:function(){
					       isUserTreeLoaded = true;
					       if(isDeptTreeLoaded){
					    	   $loadMask.hide();
					       }
			  		    },
						url:this.rightUrl,
					    onBeforeLoad:function(node,params){
			  		    	    $loadMask.show();
								params['pid']  = node ? node.id : "0";
								if(selectedUserIds){
									params['selectedUserIds']  = selectedUserIds;
							    }
								if(!node){
									params['init'] = true; 
								}
								for(var p in $me.params){
									params[p] = $me.params[p];
								}
					   }
			  });
      };
      this.addComonUserSelectionUserTree = function (){
    	  var checkedNodes = jQuery('#id_'+this.id+'_menu_tabs_menu').tree('getChecked');
    	  var checkedNodesLen = checkedNodes.length;
    	  if(0 == checkedNodesLen){
                 alert("请从左侧勾选需要添加的"+this.displayPromit+"!");
                 return;
          }
          var selectedNodes = jQuery('#id_'+this.id+'_menu_tabs_menu').tree('options').data;
          var selectedUserArray = this.getSelectedUserIdsArray();
          for(var i=0;i<checkedNodesLen;i++){
              var checkedNode = checkedNodes[i];
              if(this.type != checkedNode.attributes['type']){
                  alert("只有"+this.displayPromit+"类型节点才能被添加到"+this.displayPromit+"选择列表！");
                  return;
              }
        	  selectedUserArray.push(checkedNode.id);
          }
          var selectedValue = selectedUserArray.join(",");
          this.initComonUserSelectionDeptTree(selectedValue);
          this.initComonUserSelectionUserTree(selectedValue);
      };
      this.removeComonUserSelectionUserTree = function (){
          var selectedUserArray = this.getSelectedUserIdsArray();
    	  var checkedNodes = jQuery('#id_'+this.id+'_menu_main_content_div').tree('getChecked');
    	  var checkedNodesLen = checkedNodes.length;
    	  if(0 == checkedNodesLen){
                 alert("请从右侧勾选需要移除的"+this.displayPromit+"!");
                 return;
          }
          for(var i=0;i<checkedNodesLen;i++){
              var checkedNodeId = checkedNodes[i].id;
              for(var j=0;j<selectedUserArray.length;j++){
                  if( checkedNodeId == selectedUserArray[j]){
                	  selectedUserArray.splice(j,1);
                      break;
                  }
              }
          }
          var selectedValue = selectedUserArray.join(",");
          
          this.initComonUserSelectionDeptTree(selectedValue);
          this.initComonUserSelectionUserTree(selectedValue);
      };
      this.getSelectedUserIdsArray = function(){
    	  var selectedUserArray = [];
    	  try{
        	  var childrenNodes = jQuery('#id_'+this.id+'_menu_main_content_div').tree('getRoots'); 
        	  for(var i=0;i<childrenNodes.length;i++){
        		  var childNode = childrenNodes[i];
        		  selectedUserArray.push(childNode.id);
        	  }
    	  }catch(e){
    	  }
    	  return selectedUserArray;
      };
      this.getSelectedUserRealNamesArray = function(){
    	  var selectedUserArray = [];
    	  try{
        	  var childrenNodes = jQuery('#id_'+this.id+'_menu_main_content_div').tree('getRoots'); 
        	  for(var i=0;i<childrenNodes.length;i++){
        		  var childNode = childrenNodes[i];
        		  selectedUserArray.push(childNode.text);
        	  }
    	  }catch(e){
    	  }
    	  return selectedUserArray;
      };
      this.changeCommonUserSelectionUserTree = function(){
    	  var selectedIdsUserArray = this.getSelectedUserIdsArray();
    	  var len = selectedIdsUserArray.length;
    	  if(0 == len){
    		  if(!this.isAllownChoseEmpty){
    			  alert("在已选择"+this.displayPromit+"列表中至少需要存在一个已选择"+this.displayPromit+"！");
    			  return;
    		  }
    	  }
    	  if(1 < len){
    		  if(!this.isMultiSelect){
    			  alert("在已选择"+this.displayPromit+"列表中只能存在一个已选择"+this.displayPromit+"！");
    			  return;
    		  }
    	  }
    	  var getSelectedUserRealNamesArray = this.getSelectedUserRealNamesArray();
    	  this.selectedUserHiddenIdsDom.value = selectedIdsUserArray.join(",");
    	  this.selectedUserDisplayRealnameDom.value  = getSelectedUserRealNamesArray.join(",");
    	  if(this.choseCallBack){
    		  if(this.choseCallBack()){
    			  jQuery("#id_detailInfo"+this.id+"WindowContianer").window("close");
    		  }
    	  }else{
    		  jQuery("#id_detailInfo"+this.id+"WindowContianer").window("close");
    	  }
    	  
      };
	  if(!document.getElementById("id_detailInfo"+this.id+"WindowContianer")){
		  var windowHTML = '<div id="id_detailInfo'+this.id+'WindowContianer" buttons="#'+this.id+'-id-dlg-buttons" closed="true" modal="true" title="'+this.displayPromit+'选择" style="display:none;width:810px;padding:5px;overflow:auto;">';
		  windowHTML+='  <center><table><tr>';
		  windowHTML+='  <td><div id="id_'+this.id+'_menu_main_content" style="width:325px;overflow:hidden;padding:5px;">';
		  windowHTML+='  <div class="panel-title" >'+this.displayPromit+'列表  ';
		  windowHTML+=			'<SPAN style="margin-left:50px;">搜索：<INPUT style="BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; WIDTH: 80px; BORDER-TOP: #ddd 1px solid; MARGIN-RIGHT: 4px; BORDER-RIGHT: #ddd 1px solid" id="id_'+this.id+'_queryWorkflowsTableInput" type="text" ></SPAN>';
		  windowHTML+='  </div>';
		  windowHTML+=  '<div id="id_'+this.id+'_menu_tabs_menu" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"  ></div>';
		  windowHTML+=	'</div></td>';
		  windowHTML+=	    '<td><div id="id_'+this.id+'_middle_btnContainer" style="text-align:center;background:transparent;border:1px solid transparent;margin-top:3px;">';
		  windowHTML+=			'<div class="panel-title" style="text-indent:0px;text-align:center;top:120px;position:relative;cursor:pointer;" onclick="getTracywindyObject(\''+this.id+'\').addComonUserSelectionUserTree();"    >&gt;&nbsp;&nbsp;&gt;</div>';
		  windowHTML+=			'<div class="panel-title" style="text-indent:0px;text-align:center;top:170px;position:relative;cursor:pointer;" onclick="getTracywindyObject(\''+this.id+'\').removeComonUserSelectionUserTree();" >&lt;&nbsp;&nbsp;&lt;</div>';
		  windowHTML+=		'</div></td>';
		  windowHTML+=	'<td><div id="id_'+this.id+'_menu_main_oper" style="overflow:hidden;padding:5px;">';
		  windowHTML+=	    '<div class="panel-title" >&nbsp;&nbsp;已选择'+this.displayPromit+'</div>';
		  windowHTML+=	    '<div id="id_'+this.id+'_menu_main_content_div" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>';
		  windowHTML+=	'</div></td></tr>';
		  windowHTML+=  '</table></center>';
		  windowHTML+='</div>';
		  
		  windowHTML+=  '<div style="text-align:center;width:800px;display:none;height:40px;line-height:100px;" id="'+this.id+'-id-dlg-buttons"><div style="height:15px;width:100%;"></div>';
		  windowHTML+=  '<a  href="javascript:void(0);" class="btn btn-primary" onclick="getTracywindyObject(\''+this.id+'\').changeCommonUserSelectionUserTree();"><span>确定</span></a>';
		  windowHTML+=  '<a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick=\'jQuery("#id_detailInfo'+this.id+'WindowContianer").window("close");\'><span>取消</span></a>';
		  windowHTML+=  '</div>';
		  
		  jQuery(document.body).append(windowHTML);
		    var pageHeight = Math.max(document.documentElement.clientHeight,document.body.clientHeight)-150;
		    var pageWidth  = 672;
		    var heightAdd = -2;
		    var widthAdd  = -357;
			jQuery("#id_"+this.id+"_menu_main_content").css("height",(pageHeight+heightAdd)+"px");
			jQuery("#id_"+this.id+"_menu_tabs_menu").css("height",(pageHeight+heightAdd-40)+"px");
			jQuery("#id_"+this.id+"_menu_main_content_div").css("height",(pageHeight+heightAdd-40)+"px");
			jQuery("#id_"+this.id+"_middle_btnContainer").css("width",98+"px");
			jQuery("#id_"+this.id+"_middle_btnContainer").css("height",(pageHeight-1)+"px");
			jQuery("#id_"+this.id+"_menu_main_oper").css("height",(pageHeight+heightAdd)+"px");
			jQuery("#id_"+this.id+"_menu_main_oper").css("width",((pageWidth + widthAdd) )+"px");
		    jQuery("#id_"+this.id+"_queryWorkflowsTableInput").keypress(function(e){
		         var code = e.keyCode||e.charCode;
		         if(13 == code){
		        	 var currentUserSelectionObj = getTracywindyObject($id);
		        	 var selectedUserArray = currentUserSelectionObj.getSelectedUserIdsArray();
		        	 currentUserSelectionObj.initComonUserSelectionDeptTree(selectedUserArray.join(","));
		         }
		     });
		    this.loadCommonWindow();
	  }
};