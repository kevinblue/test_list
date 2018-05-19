(function($){
var myflow = $.myflow;

$.extend(true, myflow.editors, {
	inputEditor : function(config){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;
			var inputElementString = '<input style="width:100%;"/>';
			if(config)
			{
				if(config.readOnly)
				{
					inputElementString = '<input style="width:100%;" readOnly/>';
				}
				if(config.isHidden){
					$('#'+_div).parent().parent().hide();
				}
			}
			if("key" == _k){
				if(!props[_k].value){
					props[_k].value = GenerateGuid();
				}
			}
			$(inputElementString).val((props[_k]||{}).value||"").change(function(){
				props[_k].value = $(this).val();
			}).appendTo('#'+_div);
			
			$('#'+_div).data('editor', this);
		};
		this.destroy = function(){
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
		};
	},
	selectEditor : function(arg,isPosition){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;
			/*var ratioWidth = 100;
			if(isPosition){
				ratioWidth = 80;
			}
			var sle = $('<select  style="width:'+ratioWidth+'%;"/>').val(props[_k].value).change(function(){
				props[_k].value = $(this).val();
				if(isPosition){
			          var currentRawValue = $(this.options[this.selectedIndex]).text();
			          if(("第一位" == currentRawValue)||("最后" == currentRawValue))
			          {
				          jQuery("#id_constantLabel").hide();
				      }
			          else
			          {
			        	  jQuery("#id_constantLabel").show();
				      }
				}
			}).appendTo('#'+_div);*/
		    var comboWidth = 190;
		    var comboId = "id_ptype_combo";
		    var displayField = "name";
		    var valueField   = "value";
		    if(isPosition){
		    	comboWidth = 150;
		    	comboId="id_pposition_combo";
		    	valueField = 'name';
		    }
			if(typeof arg === 'string'){
				$.ajax({
				   type: "POST",
				   url: arg,
				   async:false,
				   success: function(data){
				   if(jQuery("#"+_div)[0]){
					 var opts = eval(data);
					 if(opts && opts.length){
							new tracywindyComboBox({
								renderTo:_div,
								loadMode:'local',
								id:comboId,
								value:props[_k].value,
								datas:opts,
								width:comboWidth,
								topAdd:2,
								leftAdd:3,
								displayField:displayField,
								valueField:valueField,
								isContainEmpty:false,
								onSelect:function(combo,rowData){
									props[_k].value = combo.getValue();
									if(isPosition){
								          var currentRawValue = this.getRawValue();//$(this.options[this.selectedIndex]).text();
								          if(("第一位" == currentRawValue)||("最后" == currentRawValue))
								          {
									          jQuery("#id_constantLabel").hide();
									      }
								          else
								          {
								        	  jQuery("#id_constantLabel").show();
									      }
									}
							    }
							});
							if(isPosition)
							{
								$("#"+_div).css("width","190px");
								var suffixHtml=" <div style='float:left;width:40px;height:20px;' id='id_constantLabel'>&nbsp;&nbsp;之后 </div>";
								$(suffixHtml).appendTo('#'+_div);
							}
					    }
					 }
				   }
				});
			}else {
				/*for(var idx=0; idx<arg.length; idx++){
					sle.append('<option value="'+arg[idx].value+'">'+arg[idx].name+'</option>');
				}
				sle.val(_props[_k].value);*/
				var opts = arg;//eval(data);
				if(jQuery("#"+_div)[0]){
						 if(opts && opts.length){
								new tracywindyComboBox({
									renderTo:_div,
									loadMode:'local',
									id:comboId,
									value:props[_k].value,
									datas:opts,
									width:comboWidth,
									topAdd:2,
									leftAdd:3,
									displayField:displayField,
									valueField:valueField,
									isContainEmpty:false,
									onSelect:function(combo,rowData){
										props[_k].value = combo.getValue();
										if(isPosition){
									          var currentRawValue = this.getRawValue();//$(this.options[this.selectedIndex]).text();
									          if(("第一位" == currentRawValue)||("最后" == currentRawValue))
									          {
										          jQuery("#id_constantLabel").hide();
										      }
									          else
									          {
									        	  jQuery("#id_constantLabel").show();
										      }
										}
								    }
								});
								if(isPosition)
								{
									$("#"+_div).css("width","190px");
									var suffixHtml=" <div style='float:left;width:40px;height:20px;' id='id_constantLabel'>&nbsp;&nbsp;之后 </div>";
									$(suffixHtml).appendTo('#'+_div);
								}
						    }
						 }
			}
			this.comboId = comboId;
			$('#'+_div).data('editor', this);
		};
		this.destroy = function(){
			/*$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});*/
			try{
				var curCombo= getTracywindyObject(this.comboId);
				_props[_k].value = curCombo.getValue();
				document.body.removeChild(curCombo.dropDiv);
			}catch(e){}
		};
	}
});

})(jQuery);