package com.reckon.util;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.leasing.utils.MoneyUtils;
import com.tenwa.leasing.utils.Tools;
/**
 * 
 * <p>保险费相关处理工具类。</p>
 * <p>所有租金测算中和保险费相关的操作请在该类中处理。</p>
 * <p>2014-3-25</p>
 * @author sea
 * @version 4.5
 */
public class InsureTypeTools {
	
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(InsureTypeTools.class);
	
	
	/**
	  * <p>计算保险费。</p>
	  * <p>保险费测算方式为手工输入 insureMoneyType0: 直接取前台输入的值。</p>
	  * <p>保险费测算方式为系统计算insureMoneyType1：根据残租金及设备分类(保险)计算保险费。</p>
	  * <p>残租金计算规则：以等额租金为例，假设每期租金值为‘3000’，总还租次数为N次。
	  *   <pre>
	  *   第一期残租金  =  第一期租金值‘3000’ * 1 		
	  *   第二期残租金  =  第二期租金值‘3000’ * 2		
	  *   第三期残租金  =  第三期租金值‘3000’ * 3
	  *   ...每期残租金值累加即为总的残租金值
	  *   付租方式为期初情况下，总残租金值需减去第一期残租金值		
	  *   </pre>
	  * </p>
	  * <p>保险费率根据数据字典进行可配置化，取对应数据字典的属性1值(propOne)。</p>
	  * <p>残租金。</p>
	  * @author sea
	  * @param cb 交易结构实体
	  * @param frpb 租金计划实体
	  * @return String 计算后的保险费
	 */
	public static String getInsureMoney(ConditionBean cb,FundRentPlanBean frpb){
		String returns = "";
		String insuremoneytype = cb.getInsureMoneyType();//保险费测算方式 手工输入 insureMoneyType0  系统计算insureMoneyType1
		if("insureMoneyType0".equals(insuremoneytype)){
			//前台输入的保险费值
			String insureMoney = NumberUtils.nullToZero( cb.getInsureMoney() ); 
			returns = insureMoney;
			logger.info("前台输入-保险费:"+returns);
		}else{
			BigDecimal allRemainRentTotal = BigDecimal.ZERO;
			logger.info("保险费支付方式:"+insuremoneytype);
			//保险费支付方式 insureType 本司付款insure_type1/代付insure_type2，代付情况下计算
			if(insuremoneytype.matches("insure_type[1,2]{1}")){
				
				//第一期残租金单独计算，下方会用到
				BigDecimal oneAllRemainRentTotal = BigDecimal.ZERO;
				//残租金计算
				for (int i = 0; i < frpb.getRentList().size(); i++) {
					BigDecimal temp = new BigDecimal(frpb.getRentList().get(i)).multiply(new BigDecimal(i + 1));
					allRemainRentTotal = allRemainRentTotal.add(temp);
					
					oneAllRemainRentTotal = oneAllRemainRentTotal.add( new BigDecimal( frpb.getRentList().get(i)) );
				}
				
				// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
				//残租金总额需考虑期初情况,期初情况下减去第一期租金值
				if("period_type_1".equals(cb.getPeriodType()) || "1".equals(cb.getPeriodType())){
					logger.info("期初情况下残租金总值减去第一期租金前的值:"+allRemainRentTotal);
					logger.info("期初情况下残租金总值减去的值:"+oneAllRemainRentTotal);
					//原本代码:allRemainRentTotal = allRemainRentTotal.subtract(new BigDecimal(frpb.getRentList().get(0)));
					allRemainRentTotal = allRemainRentTotal.subtract( oneAllRemainRentTotal );
				}
				logger.info("最终残租金值:"+allRemainRentTotal);
				//logger.info("保险费率:"+cb.getAmtInsureMoneyRate());
				//allRemainRentTotal = allRemainRentTotal.multiply(new BigDecimal(cb.getAmtInsureMoneyRate()));//残租金乘以设备保险费率
				//cb.setInsureMoney(allRemainRentTotal.toString());
			}
			returns = NumberUtils.nullToZero(String.valueOf( allRemainRentTotal ));
			logger.info("系统计算-保险费:"+returns);
		}
		
		
		return returns;
	}
	
	/**
	  * FIXME ALL 目前该方法只实现了设备分类为‘移动设备’的投保费率情况(欧力士项目需求)，其余情况请重写该方法
	  * 
	  * <p>根据设备分类(保险)的选择取数据字典中配置的设备投保费率。</p>
	  * @author sea 
	  * @param eqptbigblass 设备分类(保险)具体值  modelData.get("framework_info.eqptbigblass")
	  * @param dict  DictionaryData设备分类(保险)数据字典对象,其中属性1(propOne)必须有值
	  * @return 设备分类(保险)空情况下返回0，具体设备情况下取对应数据字典的属性1值(propOne)，其它设备分类返回0
	  * 		移动设备保险费率需 除以100再除以12的操作(保险费率/1200),暂时保留六位小数
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public static String getAmtInsureMoneyRate(String eqptbigblass,DictionaryData dict) {
		String amtInsureMoneyRate = "0.00";//设备保险费率
		//String eqptbigblass = modelData.get("framework_info.eqptbigblass");
		logger.info("设备分类(保险):"+eqptbigblass);
		
		if(Tools.isNullOrEmpty(eqptbigblass)){
			logger.error("设备分类(保险)为空,保险费率无法获取!");
			return amtInsureMoneyRate;
		}
		
		//保险分类：移动设备  eqptbigclass2  本司付款 取移动设备的保险费率
		if("eqptbigclass2".equals(eqptbigblass)){
			amtInsureMoneyRate = MoneyUtils.getZeroStr( dict.getPropOne() );
			logger.info("保险费率除1200前:"+amtInsureMoneyRate);
			if(Tools.isNullOrEmpty(amtInsureMoneyRate)){
				amtInsureMoneyRate = "0";
			}else{
				//获取的 移动设备保险费率需 除以100再除以12的操作(保险费率/1200)
				BigDecimal num = new BigDecimal(amtInsureMoneyRate).divide(new BigDecimal((12*100)),20,BigDecimal.ROUND_HALF_EVEN);
				amtInsureMoneyRate = String.valueOf(num);
			}
		}
		//...其它情况实现
		
		logger.info("保险费率除1200后:"+amtInsureMoneyRate);
		return amtInsureMoneyRate;
	}
	
	
}
