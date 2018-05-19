package com.tenwa.leasing.service.ebank;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 网银导入特定模板
 * @author Gan
 *
 */
public interface FundEbankImportService {
    public String importIDBCEbank(MultipartFile multipartFile,Map<String, String> modelData ) throws Exception; 
    public String importNJEbank(MultipartFile multipartFile,Map<String, String> modelData ) throws Exception; 
    public String importCMBCEbank(MultipartFile multipartFile,Map<String, String> modelData ) throws Exception; 
    public String importCOMMEbank(MultipartFile multipartFile,Map<String, String> modelData ) throws Exception; 
}
