package com.tenwa.leasing.serviceImpl.pledge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.pledge.Pledge;
import com.tenwa.leasing.service.pledge.PledgeCommMethod;

@Service(value = "pledgeCommMethod")
public class PledgeCommMethodImpl implements PledgeCommMethod {
	@Resource(name = "tableService")
	private TableService tableService; 

	@Override
	public void loadPledge(String contractid,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid",contractid);
		queryMainObjectMap.put("pledge_status","1");
		//未注销
		String pledgejsonone=this.tableService.getJsonArrayData("/eleasing/jsp/pledge/pledge_all_status.xml", queryMainObjectMap).toString(); 
		variablesMap.put("json_not_cancled_str", pledgejsonone);//未注销   
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid",contractid);
		queryMainObjectMap.put("pledge_status","2");  
	    //已注销
	    String pledgejsontwo=this.tableService.getJsonArrayData("/eleasing/jsp/pledge/pledge_all_status.xml", queryMainObjectMap).toString();
	    variablesMap.put("json_pledge_contract_str", pledgejsontwo);//已注销
	    
	}

	@Override
	public void updatePledgestatus(Map<String, String> variablesMap) throws Exception {         
		String json=variablesMap.get("json_present_cancled_str");
        List<Pledge>  list=new ArrayList<Pledge>();
        JSONArray jsonarray=new JSONArray(json);
        
		String pledgetime=variablesMap.get("pledge.pledgetime");
		String pledgereason=variablesMap.get("pledge.pledgereason");
    
        int status=2;
        for(int i=0;i<jsonarray.length();i++){
        	JSONObject obj=jsonarray.getJSONObject(i);
        	String id=obj.getString("id");
        	Pledge ss=this.tableService.findEntityByID(Pledge.class, id);
        	ss.setPledgestatus(status);
        	ss.setPledgetime(pledgetime);
        	ss.setPledgereason(pledgereason);
        	list.add(ss);
        }
       this.tableService.updateAllEntities(list);
	}

	@Override
	public void initCancledPledge(String fieldIput,
			Map<String, String> variablesMap,
			Map<String, String> queryMainObjectMap) throws Exception {
		String json_pledge_cancle_str = this.tableService.getJsonArrayData("eleasing/jsp/pledge/cancled_pledge_list.xml", queryMainObjectMap).toString();
		System.out.println("json_pledge_cancle_str:"+json_pledge_cancle_str);
		if ( json_pledge_cancle_str.length() > 0 && null != json_pledge_cancle_str)
		{
			variablesMap.put(fieldIput,json_pledge_cancle_str);
		} 		
		
	}
		
		

	
	



}
