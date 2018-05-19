package com.tenwa.server.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.tenwa.server.FundRequestERP;
import com.tenwa.server.bean.PaymentOrder;
import com.tenwa.server.util.ERPDataSource;
import com.tenwa.server.util.LogWriter;
import com.tenwa.server.util.YYSqlTableUtil;

public class FundRequestERPImpl implements FundRequestERP {
	private int rowCount=0;//ִ�в�������
	@Override
	public String example(String messageXml) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Document doc = DocumentHelper.parseText(messageXml);
		Element rootElt = doc.getRootElement(); // ��ȡ���ڵ� 
		System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������ 
		Iterator bill = rootElt.elementIterator("bill"); // ��ȡ���ڵ��µ��ӽڵ�bill
		int Flag=0;
		  while (bill.hasNext()) {  
	          Element recordEle = (Element) bill.next(); 
	           Flag = Integer.parseInt(recordEle.elementTextTrim("Flag")); 
		  }
		  if(Flag==0){//��ȡ���Ϊ0�������Ϊ���׳ɹ���
				Iterator billhead = rootElt.elementIterator("billhead"); //��ȡ���ڵ��µ��ӽڵ�billhead 
				PaymentOrder po=null;
				while (billhead.hasNext()) {  
					  po=new PaymentOrder();
			          Element recordEle = (Element) billhead.next(); 
			          String pk = recordEle.elementTextTrim("pk");
			          String flow_id = recordEle.elementTextTrim("flow_id");
			          String pk_paybill= recordEle.elementTextTrim("pk_paybill");
			          String org_name= recordEle.elementTextTrim("org_name"); 
			          String bill_no= recordEle.elementTextTrim("bill_no"); 
			          String pay_way= recordEle.elementTextTrim("pay_way"); 
			          String billmaker_date= recordEle.elementTextTrim("billmaker_date"); 
			          String account_no= recordEle.elementTextTrim("account_no"); 
			          String account_name= recordEle.elementTextTrim("account_name"); 
			          String pay_amt= recordEle.elementTextTrim("pay_amt"); 
			          String dept_name= recordEle.elementTextTrim("dept_name"); 
			          String operator= recordEle.elementTextTrim("operator"); 
			          String source_bill= recordEle.elementTextTrim("source_bill"); 
			          String prj_no= recordEle.elementTextTrim("prj_no"); 
			          String settle_date= recordEle.elementTextTrim("settle_date"); 
			          po.setPk(pk);
			          po.setFlow_id(flow_id);
			          po.setPk_paybill(pk_paybill);
			          po.setOrg_name(org_name);
			          po.setBill_no(bill_no);
			          po.setPay_way(pay_way);
			          po.setBillmaker_date(billmaker_date);
			          po.setAccount_no(account_no);
			          po.setAccount_name(account_name);
			          po.setPay_amt(pay_amt);
			          po.setDept_name(dept_name);
			          po.setOperator(operator);
			          po.setSource_bill(source_bill);
			          po.setPrj_no(prj_no);
			          po.setSettle_date(settle_date);
				  }
				ERPDataSource  erpDataSource =new ERPDataSource();
				String sql = YYSqlTableUtil.getAllobject(po,"PAYMENT_ORDER",0);
				rowCount =erpDataSource.executeUpdate(sql);
				erpDataSource.close(); 
				String result_xml="<Body><CountNum>"+rowCount+"</CountNum></Body>";
				LogWriter.logError("ִ�н���1������������"+sdf.format(new Date())+"������������"+result_xml);
				rowCount =0;                                          
				LogWriter.logError("ִ�н���2������������"+sdf.format(new Date())+"������������"+result_xml);
				return result_xml;
		  }
         
		
		
		} catch (DocumentException e) {
			LogWriter.logError("����DOM����,����ϵ����Ա��");
			e.printStackTrace();
		}	 catch (SQLException e) {
			LogWriter.logError("ִ�����ݿ��������,����ϵ����Ա��");
			e.printStackTrace();
		}
		return "<Body><CountNum>"+0+"</CountNum></Body>";	
	}

}
