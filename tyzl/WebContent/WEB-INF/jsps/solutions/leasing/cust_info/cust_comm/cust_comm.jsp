<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
	//手机号码
	function onMoblieValidation(e) {
		if("" == e.value)return;
	    if (e.isValid) {
	        var pattern =  /^\d{11}$/;
	        if (pattern.test(e.value) == false) {
	            e.errorText = "手机号码必须11位数字";
	            e.isValid = false;
	        }else{
	        	pattern = /^1[3|4|5|8|7][0-9]\d{4,8}$/;
		        if (pattern.test(e.value) == false) {
		            e.errorText = "不是完整的11位手机号或者正确的手机号前七位";
		            e.isValid = false;
		        }else{
		        	e.sender.setIsValid(true);
		        }
	        	
	        }
	    }
	}
	//邮箱号码 
	function onPostcodeValidation(e) {
		if("" == e.value)return;
	    if (e.isValid) {
	        var pattern =  /^\d{6}$/;
	        if (pattern.test(e.value)==false) {
	            e.errorText = "邮箱号码必须6位数字";
	            e.isValid = false;
	        }else{
	        	e.sender.setIsValid(true);
	        }
	    }
	}
	//身份证校验
	function onIDCardsValidation(e) {
		var isValid = cidInfo(e.value);
		if(!(true == isValid)){
			e.errorText = isValid;
			e.isValid = false;
		}
	}
	//通用级联
	function changecommon(currentObjId,pid,text){
		//获取combobox对象
		var cb=mini.get(currentObjId);
		var pidValue = $mini(pid).getValue();
		if("" == pidValue){
			mini.alert("请先选择"+text);
			cb.setData([]);
			return;
		}
		var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';//url 
	    var xmlFileName = cb._xmlFileName;
		cb.setUrl(url+"&xmlFileName="+xmlFileName+"&pid="+pidValue);
	}
	//客户所属行业门类
	function onindustrychanged(e){
		$mini("industrylevelbig").setValue("");
		$mini("industrylevelmiddle").setValue("");
		$mini("industrylevelsmall").setValue("");
	}
	//行业门类大类
	function onindustrylevelbigchanged(e){
		$mini("industrylevelmiddle").setValue("");
		$mini("industrylevelsmall").setValue("");
	}
	//行业门类中类
	function onindustrylevelmiddlechanged(e){
		$mini("industrylevelsmall").setValue("");
	}
	//清空省份
	function oncountrychanged(e){
		miniui_ext.get("province").setValue("");
		miniui_ext.get("city").setValue("");
		var countryCombo = mini.get("country");
	    var provinceCombo = mini.get("province");
		var id = countryCombo.getValue();
		provinceCombo.setValue("");
	    var url = urlPrefix + "/eleasing/jsp/sysmgr/sysdatamgr/get_procity_byid.xml&pid=" + id
	    provinceCombo.setUrl(url);
	    onprovincechanged();
	}
	//清空城市
	function onprovincechanged(e){
		miniui_ext.get("city").setValue("");
	    var provinceCombo = mini.get("province");
	    var cityCombo = mini.get("city");
		var id = provinceCombo.getValue();
		cityCombo.setValue("");
	    var url = urlPrefix + "/eleasing/jsp/sysmgr/sysdatamgr/get_procity_byid.xml&pid=" + id
	    cityCombo.setUrl(url);
	}
	function checkDraftByProjInfo(){
		var custidvalue = $mini("custid").getValue();
		var str = "";
		$.ajax({
	        url: getRootPath()+"/acl/checkCustByProjInfo.acl",
	        type: "post",
	        cache: false, 
	        data :{ "custId": custidvalue},
	        async : false,
	        success: function (text) {
	        	var obj=mini.decode(text);
	        	if("repeat" == obj.message){
	        		mini.alert("该客户已有项目立项记录，不能修改为草稿，并且不能废弃！");
	        	}
	        	str = obj.message;
	        }
	    });
		return str;
	}
	var idcardStr="";
	var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	function cidInfo(sId){
		if(sId.length==15)
		{
		 sId = sId.replace(/([\d]{6})(\d{9})/,"$119$2x");
		}
		 var iSum=0
		 var info=""
		 if("" == sId) return true;
		 if(!/^\d{17}(\d|x)$/i.test(sId))return "Error:身份证错误";
		 sId=sId.replace(/x$/i,"a");
		 if(aCity[parseInt(sId.substr(0,2))]==null)return "Error:非法地区";
		 sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
		 var d=new Date(sBirthday.replace(/-/g,"/"))
		 if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "Error:非法生日";
		 for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11)
		 if(iSum%11!=1)return "Error:非法证号";
		 var province = sId.substr(0,2)+"0000";
		 var city = sId.substr(0,2) + sId.substr(2,2) +"00";
		 idcardStr = {"brithdate" : sBirthday,"sex" : sId.substr(16,1)%2?"男":"女","province":province,"city" : city};
		 return true;
	}
</script>
