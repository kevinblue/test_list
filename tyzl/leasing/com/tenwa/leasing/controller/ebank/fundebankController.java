package com.tenwa.leasing.controller.ebank;

 

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.controller.vouchersFactory.EbanktoTemporaryService;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.ebank.EbankService;

/**
 * xuyunlong
 * 网银管理
 */
@Controller(value = "fundebankController")
@RequestMapping(value = "/**/acl")
public class fundebankController extends BaseController {
	private Logger logger=Logger.getLogger(fundebankController.class);

	@Resource(name="ebankService")
	private EbankService ebankService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name="ebanktoTemporaryService")
	private EbanktoTemporaryService ebanktoTemporaryService;

	// #################
	@RequestMapping(value = "/addfundebank.acl")
	@ResponseBody
	public String addEbank(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		return ebankService.addEbank(model);
	}
	@RequestMapping(value = "/updatefundebank.acl")
	@ResponseBody
	public String updateEbank(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 System.out.println(model);
		 FundEbankData fbd=this.baseService.findEntityByID(FundEbankData.class, model.get("id"));
		 BigDecimal sum=fbd.getFactMoney()==null?BigDecimal.ZERO:fbd.getFactMoney();
		 BigDecimal redsum=sum.multiply(new BigDecimal(-1));
		 ContractInfo curcinfo=null;
		 if(model.get("contracidincome")!=null){
			 curcinfo=this.baseService.findEntityByID(ContractInfo.class, model.get("contracidincome"));
		 }
		 ContractInfo oldcionfo=null;
//		 if(fbd.getContracIdIncome()!=null){		 
//			oldcionfo=this.baseService.findEntityByID(ContractInfo.class,fbd.getContracIdIncome());
//		 }
		if("0".equals(model.get("contractflag"))){//是否暂收款相关功能。0:转暂收款;1:取消暂收款
			model.put("nocontract", "0");
			/*转暂收款时凭证生成
			 * 1.网银暂收款
			 * 2.暂收款转暂收款
			 * 3.关联的合同号没有变化，不生成凭证
			 * */
//			if(!model.get("contracidincome").equals(fbd.getContracIdIncome())){
//				if(fbd.getContracIdIncome()==null||"".equals(fbd.getContracIdIncome())){
//				   //1.网银暂收款
//			       ebanktoTemporaryService.createVoucherReceiveEbank(curcinfo, sum, fbd);
//			    }else{
//			      //2.暂收款转暂收款
//			      //将第一个合同红冲，第二个重新生成
//			      ebanktoTemporaryService.createVoucherReceiveEbank(oldcionfo, redsum, fbd);	
//				  ebanktoTemporaryService.createVoucherReceiveEbank(curcinfo, sum, fbd);
//			    }
//			}
		}else if("1".equals(model.get("contractflag"))){
			model.put("nocontract", "1");
			//取消暂收款时凭证生成
			ebanktoTemporaryService.createVoucherReceiveEbank(oldcionfo,redsum, fbd);
		}else{
			model.put("nocontract", "1");
		}
		
		return this.ebankService.updateEbank(model);
	}
	
	@RequestMapping(value = "/updateCancalfundebank.acl")
	@ResponseBody
	public String updateCancalEbank(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		this.ebankService.updateCancalEbank(model);
		return null;
	}

	@RequestMapping(value = "/removefundebank.acl")
	@ResponseBody
	public String removeEbank(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		return this.ebankService.removeEbank(model);
	}
	/**
	 * 
	 * @date 2013-5-24
	 * xuyunlong
	 * 模板上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addEbankFromUpload.action")
	@ResponseBody
	public String insertEbankFromLoadFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		MultipartFile  multipartFile = multipartRequest.getFile("tableImportTemplate");
		modelData.put("importExcelColumnStr", modelData.get("parames"));
		String message=this.ebankService.insertEbankFromLoadFile(multipartFile, modelData);
	    String ajaxCallBackScript = "<script type='text/javascript'>window.parent.importEbankCallBack(\""+message+"\");</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return  null;
	}
}

