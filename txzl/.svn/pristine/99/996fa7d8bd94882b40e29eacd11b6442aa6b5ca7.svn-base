package com.tenwa.leasing.service.ebank;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.file.BaseFile;


public interface EbankService {
     public String addEbank(Map<String,String> model) throws Exception;
     public String updateEbank(Map<String,String> model) throws Exception;
     public String removeEbank(Map<String,String> model) throws Exception;
     public String checkLoadEbank(String ebankInfo,Map<String,String> model) throws Exception;
     public List<FundEbankData> uploadEbankFromFile(String ebankInfo,Map<String,String> model,BaseFile bf) throws Exception;
     public String insertEbankFromLoadFile(MultipartFile  multipartFile,Map<String,String> model)throws Exception;
     public String updateCancalEbank(Map<String,String> model)throws Exception;
}
