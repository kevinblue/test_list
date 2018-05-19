package com.tenwa.leasing.serviceImpl.fund.redout;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.service.fund.redout.FundRedOutService;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value="fundRedOutService")
public class FundRedOutServiceImpl  extends AbstractBaseServiceImpl implements FundRedOutService{
	Logger logger =Logger.getLogger(FundRedOutServiceImpl.class);
	@Resource(name = "tableService")
	private TableService tableService;
	@Resource
	private BaseDao baseDao;
	
	@Override
	public void getStartRedOutInfo(List<ContractFundFundCharge> fundFundChargeList,ContractInfo contractinfo) throws Exception {
		FundEbankData  fundEbankData = new FundEbankData();//存一条数据
    	DictionaryData dictfeetype = null;
    	String sumStr ="";
    	BigDecimal fmoney=BigDecimal.ZERO;
    	for ( int i =0; i < fundFundChargeList.size(); i++)
    	{
    		if (fundFundChargeList.get(i).getEbankNumber() == null)
    		{
    			System.out.println("进入手工生成虚拟网银构建:"+fundFundChargeList.get(i).getFeeType().getName());
    			/*红冲手工导入问题 :特殊情况  如果网银编号为空的情况,虚拟一笔网银到网银表*/
    			dictfeetype= this.tableService.findEntityByID(DictionaryData.class,fundFundChargeList.get(i).getFeeType().getCode());
    			sumStr += contractinfo.getContractNumber()+"的"+dictfeetype.getName()+"在"+DateUtil.getSystemDate()+"红冲;";
    			fmoney =fmoney.add(fundFundChargeList.get(i).getFactMoney());
    			
    			fundEbankData.setOwnAccNumber(fundFundChargeList.get(0).getAccNumber());
    			fundEbankData.setOwnAccount(fundFundChargeList.get(0).getAccount());
    			fundEbankData.setOwnBank(fundFundChargeList.get(0).getAccountBank());
    			fundEbankData.setClientAccNumber(fundFundChargeList.get(0).getClientAccnumber());
    			fundEbankData.setClientAccount(fundFundChargeList.get(0).getClientAccount());
    			fundEbankData.setClientBank(fundFundChargeList.get(0).getClientBank());
    			
    			String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null,this.getBussinessDao().getHibernateTemplate(), this.getBussinessDao().getJdbcTemplate());
    			fundEbankData.setCoin("RMB");
    			fundEbankData.setMoneyType("人民币");
    			//fundEbankData.setIsdiuse("1");
    			fundEbankData.setContractId(contractinfo.getId());//标识数据那个合同的红冲数据
    			
    			fundEbankData.setEbdataId(ebankNumber);
    			fundEbankData.setCreateDate(DateUtil.getSystemDateDetailTime());
    			fundEbankData.setCreator(SecurityUtil.getPrincipal());
    			//fundEbankData.setEnabled(0);
    			fundEbankData.setCustId(contractinfo.getCustId());
    			fundEbankData.setSummary(sumStr);
    			fundEbankData.setClientName(contractinfo.getCustId().getCustName());
    			fundEbankData.setFactDate(DateUtil.getSystemDate());
    			fundEbankData.setFactMoney(fmoney);
    			//fundEbankData.setWhmoney(BigDecimal.ZERO);
    			this.tableService.saveEntity(fundEbankData);
    			this.tableService.getBussinessDao().getHibernateTemplate().flush();
    		}
    	}
    	//return fundEbankData;
		
	}
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}

	
	
	
}
