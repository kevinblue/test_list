<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#reckonContainer .mini-panel-header{
		background :rgb(74,165,219);
	}
	 #reckonContainer .mini-textbox {
		width :125px;
	}
	
	#reckonContainer .mini-buttonedit {
		width :125px;
	} 
</style>
<div class="fillTableContainer" id="reckonContainer">
	<table class="fillTable" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=4>
				<jsp:include page="condition_frame_5_1.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
var form = new mini.Form("businessconditionFormOnhireOnhire");
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
</script>

