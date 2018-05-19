/**
*通过设置input的require=true 来自动显示必填*号 并为StyleRequire指定的颜色
*如果有readonly则自动显示为StyleReadonly指定的颜色
*2011-3-1  孙传良
*/

MustFillIn = {
		StyleRequire : "required-content",//设置必填框提示的颜色 默认为红色
		RequireStr : "*",//设置必填提示默认*号
		RequireForms : [document.forms[0]],//如果没有form保单则默认第一个表单
		RequireElement : [""],//需要添加显示必填的的对象
		GoCheck : function(theForm){//方法主体
			var obj = theForm || event.srcElement;
			var count = obj.elements.length;
			this.RequireElement.length = 1;
			this.RequireForms.length = 1;
			this.RequireForms[0] = obj;
			for(var i=0;i<count;i++){
				with(obj.elements[i]){
					this.ClearState(obj.elements[i]);
					if(getAttribute("require") == "true"){
						this.AddRequireElement(i);
					}
				}
			}
			for(var i=1;i<this.RequireElement.length;i++){
				try{
					var span = document.createElement("SPAN");
					span.id = "__RequireMessagePanel";
					span.setAttribute("class", this.StyleRequire);
					span.innerHTML = this.RequireStr;
					var pNode=this.RequireElement[i].parentNode;
					while(pNode.tagName!='TD'){
						pNode=pNode.parentNode;
					}
					var lastNode = pNode.childNodes[pNode.childNodes.length-1];
					if(lastNode.id != "__RequireMessagePanel"){//不在重复添加
						pNode.appendChild(span);
					}
				}catch(e){
					alert("error:"+e.description);
				}
			}
		},
		ClearState : function(elem){
			with(elem){
				var pNode=parentNode;
				while(pNode.tagName!='TD'){
					pNode=pNode.parentNode;
				}
				var lastNode = pNode.childNodes[pNode.childNodes.length-1];
				if(lastNode.id == "__RequireMessagePanel"){
					pNode.removeChild(lastNode);
				}
			}
		},
		AddRequireElement : function(index){
			this.RequireElement[this.RequireElement.length] = this.RequireForms[0].elements[index];
		},
		setRequire:function(strs){//设置指定必填
			for(var i=0;i<strs.length;i++){
				try{
					document.getElementsByName(strs[i]).item(0).setAttribute("require","true");	 
				}catch(e){
					alert(strs[i]+"对象不存在!");
				}
			}
			
		},
		cancelRequire:function(strs){//清除指定必填
			for(var i=0;i<strs.length;i++){
				try{
					document.getElementsByName(strs[i]).item(0).setAttribute("require","false");	 
				}catch(e){
					alert(strs[i]+"对象不存在!");
				}
			}
		},
		cancelRequireAll : function(theForm){//清除所有必填设置
			var obj = theForm || event.srcElement;
			var count = obj.elements.length;
			this.RequireForms[0] = obj;
			for(var i=0;i<count;i++){
				with(obj.elements[i]){
					if(getAttribute("require") == "true"){
						this.ClearState(obj.elements[i]);
						setAttribute("require","false");
					}
				}
			}
		}
	 };