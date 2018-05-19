package com.tenwa.business.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.AttachmentFileDao;
import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.exception.BusinessException;

@Service(value="projAttachmentFileList")
public class ProjAttachmentFileListImpl extends AbstractAttachmentFileList{
	
    @Resource(name="attachmentFileDao")
	private AttachmentFileDao attachmentFileDao;

	@Override
	public List<DictionaryData> getAttachmentFileDictList(
			String attachmentType, Map<String, String> identifiersMap,
			Map<String, String> modelData) throws Exception {
		
		//###################start附件上传 及附件分类判断###################################
		//担保人附件清单
		//将项目立项里面的附件带出来
		//判断是法人还是自然人
		String custclass = modelData.get("custclass");
		//自然人担保评审资料清单natural_person_list
		//自然人客户评审资料清单natural_person_cust_list
		//法人客户评审资料清单corporate_client_list
		//法人担保评审资料清单legal_person_list
		String custType = "";
		if (custclass == "自然人" || "自然人".equals(custclass)) {
			attachmentType = "natural_person_list";
			custType       = "natural_person_cust_list";
		}else if (custclass == "法人" || "法人".equals(custclass)) {
			attachmentType = "legal_person_list";
			custType       = "corporate_client_list";	
		}
		//###################end附件上传 及附件分类判断###################################	
		String custname = modelData.get("custname");
		String custnumber = modelData.get("custnumber");
		String datas = modelData.get("onSelectDatas");
		String attachmentFileUnionKeyFieldMapping = modelData.get("attachmentFileUnionKeyFieldMapping");
		JSONArray jsonArray = null;
		try{
			jsonArray = new JSONArray(datas);
		}catch(Exception b){
			jsonArray = null;
			b.getMessage();
		}
		//客户资料目录（自然人/法人）
		List<Dictionary> custDicts = this.attachmentFileDao.getAttachmentFileDictsListByAttachmentType(custType);
		List<DictionaryData> newDDList   = new ArrayList<DictionaryData>();
		for(Dictionary custDict : custDicts){
			newDDList.addAll(custDict.getDatas());
		}
		//List<DictionaryData> newDDList = this.attachmentFileDao.getAttachmentFileDictListByAttachmentType(custType);
		for(DictionaryData dd : newDDList){
			Map<String,String> otherAttributes = new HashMap<String,String>();
			dd.setUnionKey(custnumber);
			otherAttributes.put("bondsman", custname);
			otherAttributes.put("uploadtype", "承租人资料");
			dd.setAttributes(otherAttributes);
		}
		//担保人资料目录（自然人/法人）
		List<Dictionary> assurorDicts  = this.attachmentFileDao.getAttachmentFileDictsListByAttachmentType(attachmentType);
		List<DictionaryData> ddList      = new ArrayList<DictionaryData>();
		for(Dictionary assurorDict : assurorDicts){
			ddList.addAll(assurorDict.getDatas());
		}
		//List<DictionaryData> ddList = this.attachmentFileDao.getAttachmentFileDictListByAttachmentType(attachmentType);
		if(jsonArray!=null){
			for(int i=0;i<jsonArray.length();i++){
				JSONObject	jsonObj = jsonArray.getJSONObject(i);
				for(DictionaryData dd : ddList){
					DictionaryData newDD = new DictionaryData();
					newDD.setId(dd.getId());
					newDD.setCode(dd.getCode());
					newDD.setDescription(dd.getDescription());
					newDD.setName(dd.getName());
					newDD.setUnionKey(jsonObj.getString(attachmentFileUnionKeyFieldMapping));
					newDD.setBelongDictionary(dd.getBelongDictionary());
					newDD.setCharacter(dd.getCharacter());
					newDD.setGradeLevel(dd.getGradeLevel());
					newDD.setIsMust(dd.getIsMust());
					Map<String,String> otherAttributes = new HashMap<String,String>();
					otherAttributes.put("bondsman", jsonObj.getString("assurorname"));
					otherAttributes.put("uploadtype", "担保人资料");
					newDD.setAttributes(otherAttributes);
					newDDList.add(newDD);
				}
			}
		}
		return newDDList;
		
		/*2013年12月11日 客户要求 上传附件 恢复到单附件上传
		String custType = "cust_list";//数据字典类型（客户资料）
		List<DictionaryData> custDicts = this.attachmentFileDao.getAttachmentFileDictDatasListByAttachmentType(custType);
		return custDicts;*/
		
	}

}
