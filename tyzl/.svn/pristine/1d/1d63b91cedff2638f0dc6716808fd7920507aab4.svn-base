<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/idCard.js"></script>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use(["js/apcomponent/aptable/aptable.js"], function(ApTable) {
		new ApTable({
			id : 'vip_person',
			title:"承租企业重要个人信息",
			width : globalClientWidth-20,
			height : 200,
			frozenStartColumn:0,
			frozenEndColumn:1,
			renderTo:'table_id__vip_person1',
			showToolbar  :showTools,
			hiddenQueryArea : true,
			frozenStartColumn : 1,
			frozenEndColumn : 3,
			editFormPopupWindowWidth : 700,
			data: JsonUtil.decode('<mini:param  name="proj_report_vip_person" defaultValue="[]"/>'),
			pageSize : 15,
			lazyLoad : false,
			tools : [ 'add', '-', 'edit', '-', 'remove'],
			columns : [ 
			           {type:'indexcolumn'},
				       {type:'checkcolumn'}, 
				       {field : 'id',header : '编号',headerAlign : 'center',width:100,allowSort:true,visible:false,
							      formEditorConfig:{
							 	          readOnly:true,
								      fieldVisible:false }},
                       {field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false,
							      formEditorConfig:{
								          readOnly:true,
								      fieldVisible:false, 
								             value:custid}},
					   {field:'personname',header:'重要个人名称',headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								              type:'text',
								 fillFromFieldName:'fatherid',
								      fillProperty:'id',
								      fieldVisible:true,
								          required:true,
								             width:200,
								        labelWidth:100}},
					   {field:'sex',header:'性别',visible:true,headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								             width:200,
								              type:'radiobuttonlist',
								       repeatItems:"0",
								      repeatLayout:"table",
								   repeatDirection:"vertical",
								        valueField:'text',
							         	 textField:'text',
								              data:[{text:'男'},{text:'女'}]}},
				       {field:'idcardno',header:'身份证号码',headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								           newLine:true,
								              type:'text',
								 fillFromFieldName:'fatherid',
								      fillProperty:'id',
								      fieldVisible:true,
								      onvalidation:"onIDCardsValidation",
								             width:200,
								        labelWidth:100}},
					   {field:'birthdate',header:'出生年月',headerAlign:'center',width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
							      formEditorConfig:{
								              type:'date',
								        allowInput:"false",
								            format:'yyyy-MM-dd',
								             width:200}},
					   {field:'rawValue_relationship', header:'关系', 
							      formEditorConfig:{
								      fieldVisible:false,
								 fillFromFieldName:'relationship',
								      fillProperty:'name'}},
					    {field:'relationship',header:'关系',visible:false,headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								              width:200,
								            newLine:true,
								       fieldVisible:true,
								       showNullItem:"true", 
								       nullItemText:"",
								          emptyText:"",
								               type:'combobox',
								             params:{pid:'relationship',xmlFileName:'/combos/comboDict.xml'},
								          textField:'name',
								         valueField:'value'}},
		                {field:'mainpersonflag',header:'是否主联系人',visible:true,headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								              width:200,
								       showNullItem:"true", 
								       nullItemText:"",
								          emptyText:"",
								               type:'combobox',
								         valueField:'text',
								          textField:'text',
								               data:[{text:'是'},{text:'否'}]}},
						 {field:'mailadd',header:'邮寄地址',headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								            newLine:true,
								              width:"100%",
								            colspan:3,    
								               type:'text',
								            newLine:true,
								       fieldVisible:true}},
						 {field:'postcode',header:'邮编',headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								            newLine:true,
								              width:200,
								               type:'text',
								       fieldVisible:true,
								       onvalidation:"onPostcodeValidation"}},
						 {field:'mobilenumber',header:'手机',headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								              width:200,
								               type:'text',
								       fieldVisible:true,
								       onvalidation:"onMoblieValidation"}},
						 {field:'phone',header:'电话',headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								              width:200,
								            newLine:true,
							 	               type:'text',
								       fieldVisible:true}},
						 {field:'email',header:'email',headerAlign:'center',width:100,allowSort:true,
							       formEditorConfig:{
								              width:200,
								               type:'text',
								       fieldVisible:true,
								              vtype:"email"}},
						{field:'rawValue_unitposition', header:'职务', 
							       formEditorConfig:{
								       fieldVisible:false,
								  fillFromFieldName:'unitposition',
								       fillProperty:'name'}},
						 {field:'unitposition',header:'职务',visible:false,headerAlign:'center',width:100,allowSort:true,
                                   formEditorConfig:{
								              width:200,
								            newLine:true,
								       fieldVisible:true,
								       showNullItem:"true", 
							 	       nullItemText:"",
								          emptyText:"",
								               type:'combobox',
								            params:{pid:'cust_jobposition',xmlFileName:'/combos/comboDict.xml'},
								         textField:'name',
								        valueField:'value'}},
						 {field:'servicelife',header:'任职年限',headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								             width:200,
								              type:'text',
								      fieldVisible:true}},
						 {field:'domicileplace',header:'户口所在地',headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								           colspan:3,
								           newLine:true,
								             width:"100%",
								              type:'text',
								      fieldVisible:true}},
						{field:'homeadd',header:'常住地址',headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								           colspan:3,
								           newLine:true,
								             width:"100%",
								              type:'text',
								      fieldVisible:true}},
						{field:'cpmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,
							      formEditorConfig:{
								             width:"100%",
								           newLine:true,
								           colspan:3,
							               	  type:'textarea',
								      fieldVisible:true
							}
						}

				       ]
		});
	});
});
function $mini(id){
	return mini.getbyName(id);
}
//邮编
function onPostcodeValidation(e) {
	if("" == e.value)return;
    if (e.isValid) {
        var pattern = /\d*/;
        if (e.value.length != 6 && pattern.test(e.value) == true) {
            e.errorText = "邮编必须6位数字";
            e.isValid = false;
        }else{
        	e.sender.setIsValid(true);
        }
    }
}
//手机号码
function onMoblieValidation(e) {
	if("" == e.value)return;
    if (e.isValid) {
        var pattern = /\d*/;
        if (e.value.length != 11 && pattern.test(e.value) == true) {
            e.errorText = "手机号码必须11位数字";
            e.isValid = false;
        }else{
        	e.sender.setIsValid(true);
        }
    }
}
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
//身份证校验
function onIDCardsValidation(e) {
	var isValid = cidInfo(e.value);
	if(!(true == isValid)){
		e.errorText = isValid;
		e.isValid = false;
	}
}
function cidInfo(sId){
	if(!sId){
		return "不能为空";
	}
	sId=sId.toUpperCase();
	if(sId.length==10){
		if(sId.indexOf("(")>0||sId.indexOf(")")>0){
			if(!/^[a-zA-Z][0-9]+[\(][0-9A][\)]$/.test(sId)){
				return "香港身份证错误";
			}else{
				var _array=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
				var sid_array=sId.split("");
				for(var i=0;i<_array.length;i++){
					if(sid_array[0]==_array[i]){
						if(!(11-((i+1)*8+sid_array[1]*7+sid_array[2]*6+sid_array[3]*5+sid_array[4]*4+sid_array[5]*3+sid_array[6]*2)%11==sid_array[8])){
							alert(11-(i*8+sid_array[1]*7+sid_array[2]*6+sid_array[3]*5+sid_array[4]*4+sid_array[5]*3+sid_array[6]*2)%11==sid_array[8]);
							return "香港身份证有误";
						}else{
							return true;
						} 
					}
				}
			}
		}else if(!/^[a-zA-Z][1-2][0-9]+$/.test(sId)){
			return "台湾身份证错误";
		}else{
			return true;
		}
	}else if(sId.length==15||sId.length==18)
	{
		return checkIDCard(sId,"");
	}else{
		return "身份证号错误";
	}
}
</script>
<div id='table_id__vip_person1'></div>