package com.tenwa.leasing.serviceImpl.vouchersFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.service.vouchersFactory.EbankImportService;
import com.tenwa.leasing.utils.Tools;


@Service(value = "ebankImportService")
public class EbankImportServiceImpl implements EbankImportService {

	@Resource(name = "baseService")
	private BaseService baseService;

	//@Resource(name = "voucherToV8Service")
	//private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="网银导入";

	/* 
	 *凭证：收到款项(网银导入)
	 */
	@Override
	public void createVoucherForFund(FundEbankData fbd) throws Exception {
		
		/** 第一步:建立凭证体 */
		/** 银行存款*/
		/************************************** 凭证体1-分割线-开始 ********************************************************/
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("accNumber", fbd.getOwnAccNumber());
		List<OwnAccount> listacc=this.baseService.findEntityByProperties(OwnAccount.class, propertiesMap);
		String accSubject="";//本方账号对应的科目号
		if(listacc.size()>0){
			 OwnAccount ownAccount = listacc.get(0);
			// accSubject = ownAccount.getAccSubject();             
		}else{
			throw new BusinessException("该网银没有本方账号！");
		}
		
		String currentDate = DateUtil.getSystemDate();                                                                                                  // 当前时间
		User currentUser = SecurityUtil.getPrincipal();  
		
		/* 参数1：合同号/contract_id  (合同表UUID，用于取凭证所属，合同信息等数据) 
		  * 参数2：业务模块/modleName (例如：已开票增值税发票回导流程/网银核销流程/租金实收流程...等等)*/
		
		Map<String,String> headMap = new HashMap<String, String>();
		//网银导入可以不传合同号
		headMap.put("contract_id","");
		headMap.put("moduleName",modlename);
		headMap.put("accNumber",fbd.getOwnAccNumber());
		
		/*参数二集合传入的数据是一个list集合，里面是一个个的map集合，一个map封装一个凭证分录
		  * 参数1：摘要/F5 
		  * 参数2：科目序号/F6  VOUCHERASS_STACTS_CONFIG表的ID
		  * 参数3：借方金额/F7   原币金额  贷方金额为0
		  * 参数4：贷方金额 /F8  原币金额  借方金额为0
		  * 参数5：到账日期/F15 网银到账日期 (会计记账日期通过函数getAccountDay传入业务日期获取,已提供公用查询接口getBookDate，也可以自行指定日期)
		  //* 参数6：行号/F59
		  * 参数7：供应商编码/F19  供应商编码 可为空
		  * 参数8：会计记账日期/F1 (自行指定日期情况下,可不传) */
		  //不含税融资额
		  List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		  
		  //借1
		  Map<String,String> map1 = new HashMap<String, String>();
		  map1.put("F5", "收款-"+fbd.getSn());
		  map1.put("F6",accSubject);//VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
		  map1.put("F7",Tools.formatNumberDoubleTwo(fbd.getFactMoney()==null?"0":fbd.getFactMoney().toString()));//借方金额
		  map1.put("F8", "0");  //贷方金额
		  map1.put("F15",fbd.getFactDate());// 网银到账日期
		  //map1.put("F1", currentDate);// 自行指定日期情况下,可不传
		  list.add(map1);
		  
		  //贷1
		  Map<String,String> map2 = new HashMap<String, String>();
		  map2.put("F5", "收款-"+fbd.getSn());
		  //根据发票类型确定增值税或是营业税
		  //获取租金发票类型
		  map2.put("F6","108");//VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
		  map2.put("F7","0");//借方金额
		  map2.put("F8",Tools.formatNumberDoubleTwo(fbd.getFactMoney()==null?"0":fbd.getFactMoney().toString()));  //贷方金额
		  map2.put("F15",fbd.getFactDate());// 网银到账日期
		  //map2.put("F1", currentDate);// 自行指定日期情况下,可不传
		  list.add(map2);
		
		  //voucherToV8Service.saveV8Message(headMap, list, currentUser);
	}
}
