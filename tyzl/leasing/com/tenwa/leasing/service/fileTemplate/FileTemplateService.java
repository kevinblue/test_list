package com.tenwa.leasing.service.fileTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.tenwa.business.entity.User;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.file.BaseFileTemplateData;
/**
 * 
* 类名: FileTemplateService
* 包名: com.tenwa.leasing.service.fileTemplate
* 类描述: TODO(这里用一句话描述这个类的作用)
* 作者: xuyunlong
* 创建时间: 2014-11-14 下午02:22:37
*
 */
public interface FileTemplateService {
   public String addFileTemplate(Map<String,String> model) throws Exception;
   public String updateFileTemplate(Map<String,String>Model)throws Exception;
   public String removeFileTemplate(Map<String,String>model)throws Exception;
   public String addFileTempalteDateConfig(Map<String,String>model)throws Exception;
/**
 * 
* 方法名: loadFileTemplateDateConfig
* 方法描述: TODO(这里用一句话描述这个方法的作用)
* 参数:  @param model
* 参数:  @return
* 参数:  @throws Exception    设定文件
* 返回类型 String    返回类型
* @throws
 */
   public String loadFileTemplateDateConfig(Map<String,String>model)throws Exception;
   /**
    * 
   * 方法名: uploadTemplateFtl
   * 方法描述: TODO(这里用一句话描述这个方法的作用)
   * 参数据:  @param request
   * 参数据:  @param response
   * 参数据:  @return
   * 参数据:  @throws Exception    设定文件
   * 返回类型 String    返回类型
   * @throws
    */
   public String uploadTemplateFtl(HttpServletRequest request,HttpServletResponse response)throws Exception; 
   public String downloadTemplateFtl(HttpServletRequest request,HttpServletResponse response)throws Exception;
    public List<BaseFileTemplate> LoadTemplateByClassify(Map<String,String> model) throws Exception;
    public BaseFileTemplate LoadTemplateByTemplateNo(Map<String,String>modelData) throws Exception;
   public String createCheckBoxByTemplate(List<BaseFileTemplate> wcList,int changeRowNum) throws Exception;
   public String createFileByTemplate(Map<String,String> model) throws Exception;
   public Map    getTemplateData(Set<BaseFileTemplateData>templateDataConfigs,Map<String,String> pageMap) throws Exception;
   public String createRealFile(BaseFileTemplate ft,Map<String,Object> dataMap,String modelname) throws Exception;
   public String downloadAttachById(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public String downloadAttachByIdToEdit(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public String downloadAttachByName(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public String uploadEditTemplate(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public String uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public void addlogFileOper(BaseFile bf,String operType) throws Exception;
   public List<BaseFile> createFileByTemplateClass(Map<String,String>model) throws Exception;
   public BaseFile saveUpFiletoService(MultipartFile  multipartFile,Map<String,String>modelData) throws Exception;
   public String getuploadFileAllDir(String modelname) throws Exception;
   public String getuploadFileDir(String modelname) throws Exception;
   public String getfile(Map<String,String> model,BaseFile bf) throws Exception;
   public void writeDatatoTemplateExcel(String tempfile,Map<String,Object> model,String targetFile ) throws Exception;
   public void writeDatatoTemplateWordbyBookMark(String tempfile,Map<String,Object> model,String targetFile ) throws Exception;
   public <T> Collection<T>  getEntitysFromExcel(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public String uploadExcelFileToObject(HttpServletRequest request,HttpServletResponse response) throws Exception;
   public String getDefaultAjaxCallBack(String message) throws Exception;
   public String getdownloadfileName(Map<String,String> model,String fileName)throws Exception;
   public String removeTemplate()throws Exception;
   public User getCurUser() throws Exception;
   public BaseFile saveCreateBaseFile(String fileRealName, Map<String,String>dataModel,BaseFileTemplate bf) throws Exception;
   public String saveuploadSoureFile(HttpServletRequest request,Map<String,String> model) throws Exception;
   public String saveFileState(Map<String,String> model) throws Exception;
   
}
