(function(window){
	var $ = window.jQuery;
	window["tracywindyEmail"] = function(config){
		if(!config.renderTo){alert("renderTo is not allown empty !!!");return};
		this.id = config.id||GenerateGuid();
		this.objectType = "email";
		this.usersEnabledMapping = config.usersEnabledMapping||"流程发起人,指定人员,关系,部门,角色,群组,历史步骤,表单域,自定义";
		this.usersTypeMapping = {
				'流程发起人':'requestInitiator',
			    '指定人员':'user',
			    '关系':'relation',
			    '部门':'dept',
			    '角色':'deptRole',
			    '群组':'group',
			    '历史步骤':'step',
			    '表单域':'formField',
			    '自定义':'sql'
	    };
		
		tracywindyObject[this.id]= this;
		var currentObj = config.renderTo;
		if(!(typeof(this.renderTo)=='object'))
		{
			currentObj = document.getElementById(config.renderTo);
		}
		this.key = config.key||GenerateGuid();
		var isNeedUser    = true;
		var isNeedAddress = true;
		//append content to renderTo
		var $currentObj               = $(currentObj);
		var $emailContentDivContainer = $("<div></div>");
		$currentObj.append($emailContentDivContainer);
		var $emailContentTable = $("<table ></table>");
		$emailContentDivContainer.append($emailContentTable);
		var $emailContentTBody = $("<tbody></tbody>");
		$emailContentTable.append($emailContentTBody);
		if(isNeedUser){
			var emailUsersComboId          = "id_"+this.id+"_emailUsersComboId";
			this.emailUsersComboId = emailUsersComboId;
			var emailUsersComboIdContainer = emailUsersComboId+"_container";
			this.emailUsersComboIdContainer = emailUsersComboIdContainer;
			var $emailUsersTr     = $("<tr><td>参与人类型：</td><td colspan='2 'id='"+emailUsersComboIdContainer+"'></td></tr>");
			$emailContentTBody.append($emailUsersTr);
			this.createChoseSendEmailUsersCombo();
			var emailUsersValueInputHiddenId = "id_"+this.id+"_emailUsersValueInputHiddenId";
			this.emailUsersValueInputHiddenId = emailUsersValueInputHiddenId;
			var emailUsersValueInputDisplayId = "id_"+this.id+"_emailUsersValueInputDisplayId";
			this.emailUsersValueInputDisplayId = emailUsersValueInputDisplayId;
			var emailUsersValueTrClass = "need-set-value-cls-"+this.id;
			var emailChoseValueButtonId = emailUsersValueInputHiddenId+"-btn";
			this.emailChoseValueButtonId = emailChoseValueButtonId;
			var $emailUsersValueTr     = $("<tr class='"+emailUsersValueTrClass+"'><td>参与人：</td><td><textarea id='"+emailUsersValueInputDisplayId+"'></textarea><textarea style='display:none' id='"+emailUsersValueInputHiddenId+"'></textarea><td><a id='"+emailChoseValueButtonId+"' href='javascript:void(0);' onclick='' value='选择 {0}'/></td></td></tr>");
			$emailContentTBody.append($emailUsersValueTr);
		}
		if(isNeedAddress){
			var emailAddressHiddenInputId  = "id_" +this.id +"_emailAddressHiddenInputId";
			this.emailAddressHiddenInputId = emailAddressHiddenInputId;
			var emailAddressDisplayInputId = "id_" +this.id +"_emailAddressDisplayInputId";
			this.emailAddressDisplayInputId = emailAddressDisplayInputId;
			var $emailAddressTr     = $("<tr><td>指定地址（逗号分隔）：    </td><td colspan='2' ><textarea id='"+emailAddressDisplayInputId+"'></textarea><textarea id='"+emailAddressHiddenInputId+"' style='display:none'></textarea></td></tr>");
			$emailContentTBody.append($emailAddressTr);
		}
	};
	window["tracywindyEmail"].prototype.createChoseSendEmailUsersCombo = function(){
		 var $me = this;
		 var comboUserTypeDatas = [];
		 var enabledUsersTypes = this.usersEnabledMapping.split(",");
		 for(var i =0;i<enabledUsersTypes.length;i++){
			 var selectItemName  = enabledUsersTypes[i];
			 var selectItemValue = this.usersTypeMapping[selectItemName];
			 comboUserTypeDatas.push({
				 name:selectItemName,
				 value:selectItemValue
			 });
		 }
		 //选择节点参与人类型
		 var combo_initiatorType = new tracywindyComboBox({
		       dropListHeight:280,
		       id:this.emailUsersComboId,
		       width:160,
		       renderTo:this.emailUsersComboIdContainer,
		       loadMode:'local',
		       readOnly:true,
		       datas:comboUserTypeDatas,
		       value:'',
		       displayField:'name',
		       valueField:'value',
		       onSelect:function(combo,rowData){
		          var value = combo.getValue();
		          var rawValue = combo.getRawValue();
		          var attachmentId = $me.id;
		          var initiatorDisplayId = $me.emailUsersValueInputDisplayId;
		          var initiatorHiddenId  = $me.emailUsersValueInputHiddenId;
		          var choseButtonId = $me.emailChoseValueButtonId;
		          $me.initNodeInitiator(value,rawValue,initiatorDisplayId,initiatorHiddenId,choseButtonId,attachmentId);
		       }
		    }); 
	};
	window["tracywindyEmail"].prototype.initNodeInitiator = function(value,rawValue,initiatorDisplayId,initiatorHiddenId,choseButtonId,attachmentId){
		   var $choseButton = $("#"+choseButtonId);
		   switch(value){
	          case "requestInitiator":
	          case "user":
	          case "relation":
	          case "dept":
	          case "deptRole":
	          case "group":
	          case "step":{
		          $("#"+initiatorHiddenId).hide();
		          $("#"+initiatorDisplayId).attr("readOnly",true);
		          $("#"+initiatorDisplayId).show();
		          $("tr.need-set-value-cls-"+attachmentId).show();
		          if("requestInitiator"==value){
		        	  $("tr.need-set-value-cls-"+attachmentId).hide();
			      }
	        	  $choseButton.show();
		          $choseButton.html("选择 "+rawValue);
		          $choseButton[0].onclick=(function(value){
			          return function(e){
			        	 getTracywindyObject('id_'+value+attachmentId).show();
				      };
			      })(value);
		          break;
		      }
		      default:{
		    	  $("tr.need-set-value-cls-"+attachmentId).show();
		          $("#"+initiatorHiddenId).show();
		          $("#"+initiatorDisplayId).attr("readOnly",false);
		          $("#"+initiatorDisplayId).hide();
		    	  $choseButton.hide();
			  }
          }
			 //初始化选择器	 
			  new tracywindyCommonUserSelection({
				    hiddenInput:initiatorHiddenId||'id_initiator_display',
				    displayInput:initiatorDisplayId||'id_initiator',
				    isMultiSelect:true,
				    draggable:false,
				    windowTop:1,
				    id:'id_user'+attachmentId,
				    type:'user'
			   });
			  new tracywindyCommonUserSelection({
				    hiddenInput:initiatorHiddenId||'id_initiator_display',
				    displayInput:initiatorDisplayId||'id_initiator',
				    isMultiSelect:true,
				    draggable:false,
				    windowTop:1,
				    id:'id_relation'+attachmentId,
				    type:'relation'
			   });
			  new tracywindyCommonUserSelection({
				    hiddenInput:initiatorHiddenId||'id_initiator_display',
				    displayInput:initiatorDisplayId||'id_initiator',
				    isMultiSelect:true,
				    draggable:false,
				    windowTop:1,
				    id:'id_dept'+attachmentId,
				    type:'dept'
			   });
			  new tracywindyCommonUserSelection({
				    hiddenInput:initiatorHiddenId||'id_initiator_display',
				    displayInput:initiatorDisplayId||'id_initiator',
				    isMultiSelect:true,
				    draggable:false,
				    windowTop:1,
				    id:'id_deptRole'+attachmentId,
				    type:'deptRole'
			   });
			  new tracywindyCommonUserSelection({
				    hiddenInput:initiatorHiddenId||'id_initiator_display',
				    displayInput:initiatorDisplayId||'id_initiator',
				    isMultiSelect:true,
				    draggable:false,
				    windowTop:1,
				    id:'id_group'+attachmentId,
				    type:'group'
			   });
			  new tracywindyCommonUserSelection({
				    hiddenInput:initiatorHiddenId||'id_initiator_display',
				    displayInput:initiatorDisplayId||'id_initiator',
				    isMultiSelect:true,
				    draggable:false,
				    windowTop:1,
				    id:'id_step'+attachmentId,
				    type:'step',
				    params:{
						deployId:'-1',
		   			    currentActivityDetailId:'-1'
				    }
			   });
	};
	window["tracywindyEmail"].prototype.getChineseEmailUserType = function(emailUserType){
	    var chineseUserType = "";
	    switch(emailUserType){
	    	case  'requestInitiator': { chineseUserType = '流程发起人';break;}
	    	case  'user': { chineseUserType = '指定人员';break;}
	    	case  'relation': { chineseUserType = '关系';break;}
	    	case  'dept': { chineseUserType = '部门';break;}
	    	case  'deptRole': { chineseUserType = '角色';break;}
	    	case  'group': { chineseUserType = '群组';break;}
	    	case  'step': { chineseUserType = '历史步骤';break;}
	    	case  'formField': { chineseUserType = '表单域';break;}
	    	case  'sql': { chineseUserType = '自定义';break;}
	    	default:{chineseUserType = "";}
	    }
 		return chineseUserType;
	 };
})(window);