var checkIDCard = function(card,message){
		var flag = true;
		var m = '';
		if(card === ''){
			m = message+'身份证号码不能为空！';
			return m;
		}else{
			flag = isCardNO(card);
			if(!flag){
				m = message+'身份证号码长度不正确或者含有非法字符！';
				return m; 
			}else if(flag){
				flag = checkProvince(card);
				if(flag){
					flag = checkBirthDay(card);
					if(!flag)
						/*flag = checkParity(card);
						if(!flag){
							jQuery.messager.alert('提示',message+'身份证校验位信息有误！','warning');
						}*/
					{
						m = message+'身份证出生日期信息有误！';
						return m;
					}
				}else{
					m = message+'身份证地址信息有误！','warning';
					return m;
				}
			}
		}
		return '合法';
	};
	
	//校验身份证号码的长度是否正确
	var isCardNO = function(card){
		var reg = /(^\d{15}$)|(^\d{17}(\d|(X|x))$)/;
		if(reg.test(card)===false){
			return false;
		}
		return true;
	};
	var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
			21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
			33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
			42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
			51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
			63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
			};
	//校验省份
	var checkProvince = function(card){
		var province = card.substr(0,2);
		if(vcity[province] == undefined){
			return false;
		}
		return true;
	};
	//检查生日
	var checkBirthDay = function(card){
		var len = card.length;
		//身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
		if(len == 15){
			var birReg =  /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
			var birthData = card.match(birReg);
			var year = birthData[2];
			var month = birthData[3];
			var day = birthData[4];
			var birthday  = new Date('19'+year+'/'+month+'/'+day);
			return checkDate('19'+year,month,day,birthday);
		}
		if(len == 18){
			var birReg = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|(X|x))$/;
			var birthData = card.match(birReg);
			var year = birthData[2];
			var month = birthData[3];
			var day = birthData[4];
			var birthday  = new Date(year+'/'+month+'/'+day);
			return checkDate(year,month,day,birthday);
		}
		return false;
	};
	//检查日期
	var checkDate = function(year,month,day,birthday){
		var now = new Date();
		var now_year = now.getFullYear();
		if(birthday.getFullYear() == year && (birthday.getMonth()+1)==month && birthday.getDate() == day){
			//判断年份的范围
			var years = now_year - year; 
			if(years >= 3 && years<=100){
				return true;	
			}
			return false;
		}
		return false;
	};
	//校验位的检测
	var checkParity = function(card)
	{
	//15位转18位
	card = changeFivteenToEighteen(card);
	var len = card.length;
	if(len == '18')
	{
	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
	var cardTemp = 0, i, valnum;
	for(i = 0; i < 17; i ++)
	{
	cardTemp += card.substr(i, 1) * arrInt[i];
	}
	valnum = arrCh[cardTemp % 11];
	if (valnum == card.substr(17, 1))
	{
	return true;
	}
	return false;
	}
	return false;
	};

	//15位转18位身份证号
	var changeFivteenToEighteen = function(card)
	{
	if(card.length == '15')
	{
	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
	var cardTemp = 0, i;
	card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
	for(i = 0; i < 17; i ++)
	{
	cardTemp += card.substr(i, 1) * arrInt[i];
	}
	card += arrCh[cardTemp % 11];
	return card;
	}
	return card;
	};
