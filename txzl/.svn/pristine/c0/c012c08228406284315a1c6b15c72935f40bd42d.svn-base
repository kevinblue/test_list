/**
*通过客户类别设置不同的验证字段
*需要和数据字典匹配
*如果数据字典有增加客户类型请对应的添加
*增加 个人法人验证字段变量 和 custTypes 值以及ewlp,law函数
*2011-4-21  孙传良
**/
custType={
		//个人
		ewlpCust:["custname","idcardno","rawValue_sex","rawValue_maritalstatus","rawValue_creatordept","rawValue_custtype","brithdate","rawValue_province","rawValue_city","rawValue_county","mailadd","rawValue_dealerorcompany","rawValue_creatordept"],//承租人
		ewlpAssuror:["custname","idcardno","rawValue_sex","rawValue_maritalstatus","rawValue_creatordept","rawValue_custtype","rawValue_dealerorcompany","rawValue_creatordept"],//担保人
		ewlpVndr:["custname","rawValue_custtype","rawValue_creatordept","rawValue_dealerorcompany","rawValue_creatordept"],//供应商
		ewlpManufacturer:["custname","rawValue_custtype","rawValue_creatordept","rawValue_dealerorcompany","rawValue_creatordept"],//制造商
		ewlpGuarantor:["custname","rawValue_custtype","rawValue_creatordept","rawValue_dealerorcompany","rawValue_creatordept"],//抵押人
		//法人
		lawCust:["custname","orgcode","rawValue_custtype","rawValue_banklevel","rawValue_creatordept","rawValue_taxlevel","rawValue_province","rawValue_city","rawValue_county","mailadd","rawValue_dealerorcompany","rawValue_creatordept"],//承租人
		lawAssuror:["custname","rawValue_custtype","rawValue_banklevel","rawValue_province","rawValue_city","mailadd","rawValue_dealerorcompany","rawValue_creatordept"],//担保人
		lawVndr:["custname","rawValue_custtype","rawValue_banklevel","rawValue_dealerorcompany","rawValue_creatordept"],//供应商
		lawManufacturer:["custname","rawValue_custtype","rawValue_banklevel","rawValue_dealerorcompany","rawValue_creatordept"],//制造商
		lawGuarantor:["custname","rawValue_custtype","rawValue_banklevel","rawValue_dealerorcompany","rawValue_creatordept"],//抵押人
		//默认
		def:["cust_name","cust_type","rawValue_creatordept","rawValue_dealerorcompany"],
		//客户类别 值为数据字典想对应的值
		custTypes:["cust_type.cust","cust_type.assuror","cust_type.vndr","cust_type.manufacturer","cust_type.guarantor"],
		//此处的顺序有限制, custTypes值和ewlp(law)要在验证规则上一一对应
		//如: 客户类别为:cust_type.cust 其对应的ewlp为ewlpCust验证规则
		ewlp:function (i){//个人
			switch(i){
				case 0: return this.ewlpCust;
				case 1: return this.ewlpAssuror;
				case 2: return this.ewlpVndr;
				case 3: return this.ewlpManufacturer;
				case 4: return this.ewlpGuarantor;
				default : return this.def;
			}
		},
		law:function (i){//法人
			switch(i){
				case 0: return this.lawCust;
				case 1: return this.lawAssuror;
				case 2: return this.lawVndr;
				case 3: return this.lawManufacturer;
				case 4: return this.lawGuarantor;
				default : return this.def;
			}
		},
		getCheckField: function (type,cust_type){
			var index_t=this.getIndexType(cust_type);
			if(index_t>-1){
				if(type=='ewlp'){
					return this.ewlp(index_t);
				}else if (type=='law'){
					return this.law(index_t);
				}else{
					return this.def;
				}
			}else{
				return this.def;
			}		
		},
		getIndexType:function (cust_type){
			for(var i=0;i<this.custTypes.length;i++){
				if(cust_type==this.custTypes[i]){
					return i;
				}
			}
			return -1;
		}
	};

