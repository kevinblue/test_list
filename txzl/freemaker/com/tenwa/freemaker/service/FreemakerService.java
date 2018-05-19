package com.tenwa.freemaker.service;

import java.util.Map;

import org.json.JSONObject;

import com.tenwa.business.service.BaseService;
import com.tenwa.freemaker.entity.FreeMakerEntity;

public interface FreemakerService extends BaseService{
	public String saveOrQueryFreemakerEntities(Map<String, String> paramMap) throws Exception;
	public JSONObject getFreemakerEntityTreeAsJson(FreeMakerEntity rootEntity) throws Exception;
	public String updateFreemakerEntityPosition(Map<String, String> paramMap) throws Exception;
	public String saveEntityColumn(Map<String, String> paramMap) throws Exception;
	public String createEntity(Map<String, String> paramMap) throws Exception;
	public String createDataFromEntity(Map<String, String> paramMap) throws Exception;
	public String createDataByAllEntity(Map<String, String> paramMap) throws Exception;
}
