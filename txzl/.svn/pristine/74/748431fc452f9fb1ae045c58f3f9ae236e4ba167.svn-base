package com.tenwa.leasing.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tenwa.business.model.Table;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.TableServiceImpl;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
public class PaymentInterface {
	
	private  TableService tableService=new TableServiceImpl(); 

	//如果状态是1，说明是从endaction传过来的数据；0是说明要读取错误日志文件获取数据
	/**
	 * 
	 * @param status 0:读取错误日志文件获取数据;1读取endaction中的variableMap
	 * @param map
	 * @param jsonStr
	 */
	public static Map<String,String> payment(int status,Map<String,String> map,String jsonStr){
        Map<String,String> returnMap=new HashMap<String,String>();  
		if(status==1){
			jsonStr=map.get("json_payment_current_str");
		}
		Connection conn=null;
		DataBaseManagerNew db=null;
		JSONArray jsonAry=null;
		JSONObject obj=null;
		String sql="insert into AUTHORIZATION_TO_PAYMENT"
				+ "(ERP_PAYMENT_ID,"	//主键
				+ "RECORD_STATUS,"		//指令状态(Available...)(notsure/required)
				+ "PAYMENT_TYPE_ID,"	//支付类型(202默认值/204/401)(notsure/required)
				+ "PAYMENT_BUSTYPE_ID,"	//支付子类型(默认0)(notsure/required)
				+ "PAYMENT_METHOD_TYPE_ID,"//结算方式(0/2默认值/3/4)
				+ "PAYMENT_ACCOUNTS,"		//付款方银行账号
				+ "CURRENCY_TYPE,"			//币种(10-人民币/21-港币/32-美元/35-欧元/43-英镑/65-日元)
				+ "DEPOSIT_BANK_NAME,"		//收款方银行开户行
				+ "DEPOSIT_ACCOUNTS_NAME,"	//收款方银行账户名称
				+ "DEPOSIT_ACCOUNTS,"		//收款方银行账号
				+ "DEPOSIT_BANK_TYPE,"		//收款方银行类型(notsure/required)
				+ "AMOUNT,"					//交易金额
				+ "PURPOSE,"				//用途
				+ "VERSION,"				//版本号(0客户输入值)(notsure/required)
				+ "CHECK_CODE) "			//校验码(notsure/required)
				
				+ "values"
				
				+ "(seq_default.nextval,"						
				+ "'Available',"			//Available，等待CBS系统读取数据
				+ "'202',"					//支付类型(202默认值)
				+ "'0',"					//支付子类型(默认0)
				+ "?,"						
				+ "?,"
				+ "'10',"					//收款方银行类型
				+ "?,?,?,"
				+ "'bbc',"					//收款方银行账户名称
				+ "?,?,"					
				+ "0,"						//版本号(0客户输入值)
				+ "'1101')";				//校验码(随便输的)
		try {
			db=DataBaseManagerNew.getInstance();
			conn=db.getConnection();
			  //设置事务的提交方式为非自动提交：
            conn.setAutoCommit(false);
            
			PreparedStatement  ps=conn.prepareStatement(sql);
			
			//把endaction中的json字符串转换为json数组
			jsonAry = new JSONArray(jsonStr);
			for(int i=0;i<jsonAry.length();i++){
				obj=jsonAry.getJSONObject(i);
				//String erpPaymentId=obj.getString("fundfundchargeplan");//主键生成策略？？？
				//String id=Long.toHexString(System.currentTimeMillis()+Math.random());
				String settlemethod=obj.getString("settlemethod");		//结算方式(0/2默认值/3/4)获取的值为payfund6(长度太长)
				String method=getSettleMethod(settlemethod);			//根据payfund*判断结算方式。
				String clientaccnumber=obj.getString("clientaccnumber");//付款方银行账号
				//String currency=obj.getString("currency");				//币种(10-人民币/21-港币/32-美元/35-欧元/43-英镑/65-日元)
				String accountbank=obj.getString("accountbank");		//收款方银行开户行
				String account=obj.getString("account");				//收款方银行账户名称
				String accnumber=obj.getString("accnumbers");			//收款方银行账号
				double factmoney=Double.parseDouble(obj.getString("factmoney"));//交易金额
				String feetype=obj.getString("feetype");				//用途
				int j=0;//用途
				//ps.setString(++j, id);
				ps.setString(++j, method);
				ps.setString(++j, clientaccnumber);
				//ps.setString(, "10");
				ps.setString(++j, accountbank);
				ps.setString(++j, account);
				ps.setString(++j, accnumber);
				ps.setDouble(++j, factmoney);
				ps.setString(++j, feetype);
				ps.addBatch();
				
			}	
			 //在try块内添加事务的提交操作，表示操作无异常，提交事务。
			ps.executeBatch();
            conn.commit();
            
            //没有异常的时候,打印日志
    		String fullPath=LogWriter.operationLog(jsonStr);
    		//保存日志信息到数据库
    		//RecordInfo(fullPath,map,1);
    		returnMap.put("status", "1");
    		returnMap.put("filepath", fullPath);
    		return returnMap;
		}catch (SQLException e) {
			e.printStackTrace();
			String fullPath=LogWriter.logError(e.toString(),jsonStr);//打印错误信息
//			RecordInfo(fullPath,map,0);
			try {
				conn.rollback();//异常时rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			returnMap.put("status", "0");
    		returnMap.put("filepath", fullPath);
    		return returnMap;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			String fullPath=LogWriter.logError(e.toString(),jsonStr);//打印错误信息
//			RecordInfo(fullPath,map,0);
			//这里不用rollback，因为肯定没有创建链接
			returnMap.put("status", "0");
    		returnMap.put("filepath", fullPath);
    		return returnMap;
		} catch (JSONException e) {
			e.printStackTrace();
			String fullPath=LogWriter.logError(e.toString(),jsonStr);//打印错误信息
//			RecordInfo(fullPath,map,0);
			try {
				conn.rollback();//异常时rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			returnMap.put("status", "0");
    		returnMap.put("filepath", fullPath);
    		return returnMap;
		}finally{
			 //设置事务提交方式为自动提交：
            try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.CloseConnection(conn);	
		}	
	}
	/**
	 * 如果保存日志文件也出错？？？？
	 * @param fullPath	日志文件完整路径
	 * @param map		页面传过来的map
	 * @param status	正常1或错误0
	 */
	private static void RecordInfo(String fullPath, Map<String,String> map,int status) {
		String userId=map.get("login_userid");
		String contractId=map.get("contract_id");
		String jsonStr=map.get("json_payment_current_str");
		Connection conn=null;
		DataBaseManagerNew db=null;
		try {
			db=DataBaseManagerNew.getInstance();
			conn=db.getConnection();
			String sql="insert into PAYMENT_LOG("
					+ "id,"
					+ "PAYMENT_STATUS,"
					+ "CONTRACT_ID,"
					+ "PAYMENT_ID,"
					+ "CREATOR_,"
					+ "CREATE_DATE,"
					+ "FILE_PATH)"
					
					+ "values"
					
					+ "(SYS_GUID (),"		//	主键
					+ "?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			JSONArray jsonAry = new JSONArray(jsonStr);
			JSONObject obj=new JSONObject();
			for(int i=0;i<jsonAry.length();i++){
				obj=jsonAry.getJSONObject(i);
				String paymentId=obj.getString("fundfundchargeplan");
				int j=0;
				ps.setString(++j,status+"");
				ps.setString(++j, contractId);
				ps.setString(++j, paymentId);
				ps.setString(++j, userId);
				ps.setString(++j, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				ps.setString(++j, fullPath);
				ps.addBatch();
			}			
			ps.executeBatch();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (JSONException e) {
			e.printStackTrace();
		}finally{         
			db.CloseConnection(conn);	
		}		
	}

	/**
	 * 根据获取的settlemethod判断结算方式0/2/3/4。
	 * payfund6:电汇【3】
	 * payfund4:支票【2】【默认值】
	 * payfund1/2/3:汇票【4】(	银行汇票/银行承兑汇票/商业承兑汇票)
	 * 其他：其他【0】
	 * @param settlemethod
	 * @return
	 */
	private static String getSettleMethod(String settlemethod) {
		//如果没值，选择默认值
		if("".equals(settlemethod)||"payfund4".equals(settlemethod)){
			return "2";
		}
		if("payfund6".equals(settlemethod)){
			return "3";
		}
		if("payfund1".equals(settlemethod)||"payfund2".equals(settlemethod)||"payfund3".equals(settlemethod)){
			return "4";
		}
		return "0";
	}
	/**
	 * 出现异常，从日志文件中取的数据，再次发起存储过程。
	 */
	public static void rePayment(){
		
	}
	
	public static void main(String[] args) throws Exception, Exception {
//		JSONArray jsonAry=new JSONArray("[{'fundfundchargeplan':'4028801158f104ce0158f10980a700a8','paymentid':'3','feetype':'feetype18','feetypename':'厂商保证金抵扣','settlemethod':'payfund6','settlemethodname':'电汇','paytype':'pay_type_out','paytypename':'付款','factdate':'2024-12-16','factmoney':'853227.13','comparemoney':'853227.13','feeadjust':'0','_id':8,'_uid':8,'_state':'added','factobjectname':'安徽财经大学附属经济研究所','customertype':'proj_type1','customertypename':'集团内', 'factobjectid':'4028800358e774de0158e7c829290078','accountbankname':'bbc','accountbank':'bbc','account':'000000000000000000000','accnumber':'3901181209000008343','clientbankname':'上海建设银行','clientbank':'上海建设银行','clientbankno':'123023','clientaccount':'周博鹏账号','clientaccnumber':'12345625'}]");
//		payment(0,null,jsonAry.toString());
		//LogWriter.logError("dihewfiw", "adsadas");
		System.out.println("sssGB31".matches("^(.)+(GB[0-9]+)$"));
	}
	
	@Test
	public void test() throws Exception{
		String contractid="4028801158f104ce0158f1097d270027";
		ContractInfo contract=tableService.findEntityByID(ContractInfo.class, contractid);
		Set<ContractFundFundCharge> set=contract.getFundFundCharges();
		for(ContractFundFundCharge charge:set){
			String paymentId=charge.getFundFundChargePlan().getPaymentId();
			System.out.println(paymentId);
		}
		System.out.println("sssGB31".matches("^(.)+(GB[0-9]+)$"));
	}
}
