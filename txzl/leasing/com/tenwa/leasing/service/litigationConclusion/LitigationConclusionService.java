package com.tenwa.leasing.service.litigationConclusion;

import java.util.Map;

public interface LitigationConclusionService{
	public void updatelitigationConclusionRegistrationApplication(Map<String, String> variablesMap) throws Exception;
	public void addlitigationConclusionRegistrationApplication(Map<String, String> variablesMap) throws Exception;
	public void deletelitigationConclusionRegistrationApplication(Map<String, String> variablesMap) throws Exception;
}
