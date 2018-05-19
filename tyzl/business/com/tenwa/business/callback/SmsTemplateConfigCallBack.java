package com.tenwa.business.callback;

import java.util.List;
import java.util.Map;

import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.entity.notice.SmsTemplate;
import com.tenwa.business.service.BaseService;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SmsTemplateConfigCallBack implements EntityBeanCallBack {

	@Override
	public void beforePersistent(BaseService txService, List entityBeanList, List entityIdList, Map<String, String> paramMap, OperTypeEnum operType) throws Exception {

		List<SmsTemplate> templateList = (List<SmsTemplate>) entityBeanList;
		// List<String> userIdList = (List<String>)entityIdList;
		// com.tenwa.leasing.entity.insurance.InsuranceClaim cannot be cast to
		// com.tenwa.business.entity.Use
		switch (operType) {
		case ADD:
			// InsuranceClaim ins = userBeanList.get(0);
			// InsuranceInfo insurance = new InsuranceInfo();
			// insurance.setId(paramMap.get("insurancename"));
			// ins.setInsuranceId(insurance);
			break;
		case COPY: {
			break;
		}
		case EDIT: {
			break;
		}
		case REMOVE: {
			break;
		}
		}
	}

	@Override
	public void afterPersistent(BaseService txService, List entityBeanList, List entityIdList, Map<String, String> paramMap, OperTypeEnum operType) throws Exception {

		List<SmsTemplate> templateList = (List<SmsTemplate>) entityBeanList;

		switch (operType) {
		case ADD:
		case COPY: {
			break;
		}
		case EDIT: {
			break;
		}
		case REMOVE: {
			break;
		}
		}
	}

}
