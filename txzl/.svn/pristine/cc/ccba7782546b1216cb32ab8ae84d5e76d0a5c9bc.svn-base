<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    

<script type="text/javascript">

//刷新页面当中的所有的输入框的钱数为千分位
function updateInputThousand(){
	var fields = businessForm.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		if(fields[index].vtype == 'double'){
			fields[index].setValue(formatNumberThousand(fields[index].getValue()));
		}
	}
}

//获取对象的数值
function getNumber(id){
    var value = mini.get(id).getValue();
    try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}
function decimalReal(num,v)
{
   var dight  =(num*Math.pow(10,v)/Math.pow(10,v)).toFixed(v);  
   return parseFloat(dight);
} 
//四舍五入
function decimal(num,v)
{	//num表示要四舍五入的数,v表示要保留的小数位数。
	if(0 == v)
	{
		return decimalReal(decimalReal(num,2),0);
	}
    return decimalReal(num,v);		
}
function $minigetvalue(id){
	return mini.get(id).getValue();
}
function $mini(id){
	return mini.get(id);
}
function $minigetinputtext(id){
	var value = mini.get(id).getInputText();
	try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}



function $miniEnable(id){
	var miniObj = mini.get(id);
	miniObj.enable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-textbox'){
		jQueryObj.find('.mini-textbox-border').attr("style", "");
	}else if(uiCl == 'mini-combobox'){
		jQueryObj.find('.mini-textbox-border').attr("style", "");
		jQueryObj.find('.mini-buttonedit-border').attr("style", "");
		jQueryObj.find(".mini-buttonedit-button").css("display", "block");
	}
}
function $miniDisable(id){
	var miniObj = mini.get(id);
	miniObj.disable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-combobox'){
		jQueryObj.find(".mini-buttonedit-button").css("display", "none");
	}
}
function $miniSetCombValue(id,value,text){
	var miniObj = mini.get(id);
	miniObj.setValue(value);
	miniObj.setText(text);
	var miniHiddenObj = mini.get("rawValue_"+id).setValue(text);
}

function changeInputToThoud(e){
	setformatvalue(e.sender);
}
function $setThouValue(id){
	setformatvalue(mini.get(id));
}

</script>