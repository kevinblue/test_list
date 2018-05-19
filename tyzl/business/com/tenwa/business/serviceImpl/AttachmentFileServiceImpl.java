/**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.serviceImpl
 * 文件名：         AttachmentFileServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-9-上午11:36:32
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.serviceImpl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tenwa.business.dao.AttachmentFileDao;
import com.tenwa.business.entity.AppImage;
import com.tenwa.business.entity.AttachmentFileUploadInfo;
import com.tenwa.business.entity.AttachmentFileUploadInfoDetail;
import com.tenwa.business.entity.AttachmentFileUploadInfoDetailDownload;
import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.FileProcess;
import com.tenwa.business.service.AppImageService;
import com.tenwa.business.service.AttachmentFileService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.utils.StrTools;

/**
 * 类名称： AttachmentFileServiceImpl 类描述： 创建人： tracywindy 修改人： tracywindy
 * 修改时间：2012-11-9 上午11:36:32 修改备注：
 * 
 * @version 1.0.0
 **/
@Service("attachmentFileService")
public class AttachmentFileServiceImpl implements AttachmentFileService {
	private Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "attachmentFileDao")
	private AttachmentFileDao attachmentFileDao;

	@Resource(name = "tableService")
	private TableService tableService;

	/**
	 * @param attachmentFileDao
	 *            the attachmentFileDao to set
	 **/

	public void setAttachmentFileDao(AttachmentFileDao attachmentFileDao) {
		this.attachmentFileDao = attachmentFileDao;
	}

	/**
	 * attachmentFileDao
	 * 
	 * @return the attachmentFileDao
	 * @since 1.0.0
	 **/

	public AttachmentFileDao getAttachmentFileDao() {
		return attachmentFileDao;
	}

	private final String[] identifierItems = new String[] { "One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};

	@Override
	public String getAttachmentFileDetailsJsonString(Map<String, String> modelData) throws Exception {
		String attachmentType = modelData.get("attachmentType");
		String attachmentFileUnionKeyFieldMapping = modelData.get("attachmentFileUnionKeyFieldMapping");

		int totalCount = Integer.MAX_VALUE;
		JSONObject tableJson = new JSONObject();
		tableJson.put("totalCount", totalCount);
		tableJson.put("norecord", "false");
		JSONArray datas = new JSONArray();
		tableJson.put("datas", datas);

		Map<String, String> identifiersMap = new HashMap<String, String>();
		for (int ii = 0; ii < identifierItems.length; ii++) {
			String item = identifierItems[ii];
			String identifierItem = ("identifier" + item);
			String identifierValue = modelData.get(identifierItem);
			identifierValue = StringUtil.nullToString(identifierValue).trim();
			if (!identifierValue.isEmpty()) {
				identifiersMap.put(item, identifierValue);
			}
		}
		// List<Object[]> attachmentFileInfosList =
		// this.attachmentFileDao.getAttachmentFileInfosListByAttachmentType(attachmentType,
		// identifiersMap);
		String attachmentFileDictListImplBeanName = StringUtil.empty2Other(modelData.get("attachmentFileDictListImplBeanName"), "commonAttachmentFileDictList");
		// attachmentFileDictList =
		// this.attachmentFileDao.getAttachmentFileDictListByAttachmentType(attachmentType);
		AbstractAttachmentFileList attachmentFileList = (AbstractAttachmentFileList) WebUtil.getApplicationContext().getBean(attachmentFileDictListImplBeanName);
		List<DictionaryData> attachmentFileDictList = attachmentFileList.getAttachmentFileDictList(attachmentType, identifiersMap, modelData);
		List<AttachmentFileUploadInfo> attachmentFileInfosList = attachmentFileList.getHaveUploadedAttachmentFileUploadInfoList(attachmentType, identifiersMap, modelData);
		for (DictionaryData attachmentFileDict : attachmentFileDictList) {
			Dictionary belongDict = attachmentFileDict.getBelongDictionary();
			JSONObject rowData = new JSONObject();
			datas.put(rowData);

			String attachmentFileDictId = attachmentFileDict.getId();
			String attachmentFileDictFileName = attachmentFileDict.getName();
			String attachmentFileUnionKey = attachmentFileDict.getUnionKey();
			rowData.put("attachmentFileDictId", attachmentFileDictId);
			rowData.put("attachmentFileDictFileName", attachmentFileDictFileName);
			rowData.put("isAttachmentChecked", (null == attachmentFileDict.getIsMust()) ? false : attachmentFileDict.getIsMust());
			rowData.put("type", belongDict.getName());
			rowData.put("character", attachmentFileDict.getCharacter());
			rowData.put("gradelevel", attachmentFileDict.getGradeLevel());
			rowData.put("description", attachmentFileDict.getDescription());
			/*
			 * name:'type',header:'资料类别',hidden:false},
			 * {name:'character',header:'资料类别',hidden:false},
			 * {name:'gradelevel',header:'客户id/担保人id',hidden:false},
			 * {name:'description'
			 */
			if (null != attachmentFileUnionKeyFieldMapping) {
				rowData.put(attachmentFileUnionKeyFieldMapping, StringUtil.nullToString(attachmentFileUnionKey));
			}
			StringBuffer attachmentFileUploadInfoDetailIds = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailActivityDetailIds = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailChineseFileNames = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailEncodeFileNames = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailFileTypes = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailFileSizes = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailUploadTimes = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailUploadUsers = new StringBuffer();
			StringBuffer attachmentFileUploadInfoDetailRemarks = new StringBuffer();
			String attachmentFileUploadInfoId = "";
			for (AttachmentFileUploadInfo attachmentFileUploadInfo : attachmentFileInfosList) {
				String attachmentFileDictId_inner = attachmentFileUploadInfo.getAttachmentFileDict().getId();
				String attachmentFileUnionKey_inner = attachmentFileUploadInfo.getUnionKey();
				// String attachmentFileDictId_inner =
				// attachmentFileInfos[0].toString();
				if (attachmentFileDictId.equals(attachmentFileDictId_inner)) {
					if ((null != attachmentFileUnionKey) && (!attachmentFileUnionKey.equals(attachmentFileUnionKey_inner))) {
						continue;
					}

					// AttachmentFileUploadInfo attachmentFileUploadInfo =
					// (AttachmentFileUploadInfo)attachmentFileInfos[1];
					attachmentFileUploadInfoId = attachmentFileUploadInfo.getId();
					Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails = attachmentFileUploadInfo.getAttachmentFileUploadInfoDetails();
					Iterator<AttachmentFileUploadInfoDetail> iterator = attachmentFileUploadInfoDetails.iterator();
					while(iterator.hasNext()){
						AttachmentFileUploadInfoDetail af=iterator.next();
						if(null!=af.getIdentifierSeven()&&!"".equals(af.getIdentifierSeven())){
							if(!af.getIdentifierSeven().equals(identifiersMap.get("Seven"))&&null!=identifiersMap.get("Seven")){
								iterator.remove();
							}
						}else if(null!=af.getIdentifierFour()&&!"".equals(af.getIdentifierFour())){
							if(!af.getIdentifierFour().equals(identifiersMap.get("Four"))&&null!=identifiersMap.get("Four")){
								iterator.remove();
							}
						}
					}
					int index = 0;
					for (AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail : attachmentFileUploadInfoDetails) {
						if (0 < index++) {
							String splitStr = JBPMUtil.getSplitStr();
							attachmentFileUploadInfoDetailIds.append(splitStr);
							attachmentFileUploadInfoDetailActivityDetailIds.append(splitStr);
							attachmentFileUploadInfoDetailChineseFileNames.append(splitStr);
							attachmentFileUploadInfoDetailEncodeFileNames.append(splitStr);
							attachmentFileUploadInfoDetailFileTypes.append(splitStr);
							attachmentFileUploadInfoDetailFileSizes.append(splitStr);
							attachmentFileUploadInfoDetailUploadTimes.append(splitStr);
							attachmentFileUploadInfoDetailUploadUsers.append(splitStr);
							attachmentFileUploadInfoDetailRemarks.append(splitStr);
						}
						String attachmentFileUploadInfoDetailId = attachmentFileUploadInfoDetail.getId();
						String attachmentFileUploadInfoActivityDetailId = "";
						ActivityDetail ad = attachmentFileUploadInfoDetail.getActivityDetail();
						if (null != ad) {
							attachmentFileUploadInfoActivityDetailId = ad.getId();
						}
						String attachmentFileUploadInfoDetailChineseFileName = attachmentFileUploadInfoDetail.getChineseFileName();
						String attachmentFileUploadInfoDetailEncodeFileName = attachmentFileUploadInfoDetail.getEncodeFileName();
						String attachmentFileUploadInfoDetailFileType = attachmentFileUploadInfoDetail.getFileType();
						long attachmentFileUploadInfoDetailFileSize = attachmentFileUploadInfoDetail.getFileSize();
						String attachmentFileUploadInfoDetailUploadTime = attachmentFileUploadInfoDetail.getUploadTime();
						String attachmentFileUploadInfoDetailRealName = attachmentFileUploadInfoDetail.getUploadUser().getRealname();
						String attachmentFileUploadInfoDetailRemark = StringUtil.getJsonString(attachmentFileUploadInfoDetail.getRemark());

						attachmentFileUploadInfoDetailIds.append(attachmentFileUploadInfoDetailId);
						attachmentFileUploadInfoDetailActivityDetailIds.append(attachmentFileUploadInfoActivityDetailId);
						attachmentFileUploadInfoDetailChineseFileNames.append(attachmentFileUploadInfoDetailChineseFileName);
						attachmentFileUploadInfoDetailEncodeFileNames.append(attachmentFileUploadInfoDetailEncodeFileName);
						attachmentFileUploadInfoDetailFileTypes.append(attachmentFileUploadInfoDetailFileType);
						attachmentFileUploadInfoDetailFileSizes.append(attachmentFileUploadInfoDetailFileSize);
						attachmentFileUploadInfoDetailUploadTimes.append(attachmentFileUploadInfoDetailUploadTime);
						attachmentFileUploadInfoDetailUploadUsers.append(attachmentFileUploadInfoDetailRealName);
						attachmentFileUploadInfoDetailRemarks.append(attachmentFileUploadInfoDetailRemark);
					}
					break;
				}
			}
			Map<String, String> attachmentFileDictOtherAttributes = attachmentFileDict.getAttributes();
			if (null != attachmentFileDictOtherAttributes) {
				for (String key : attachmentFileDictOtherAttributes.keySet()) {
					String value = attachmentFileDictOtherAttributes.get(key);
					rowData.put(key, value);
				}
			}

			rowData.put("attachmentFileUploadInfoId", attachmentFileUploadInfoId);
			rowData.put("attachmentFileUploadInfoDetailIds", attachmentFileUploadInfoDetailIds);
			rowData.put("attachmentFileUploadInfoDetailActivityDetailIds", attachmentFileUploadInfoDetailActivityDetailIds);
			rowData.put("attachmentFileUploadInfoDetailChineseFileNames", attachmentFileUploadInfoDetailChineseFileNames);
			rowData.put("attachmentFileUploadInfoDetailEncodeFileNames", attachmentFileUploadInfoDetailEncodeFileNames);
			rowData.put("attachmentFileUploadInfoDetailFileTypes", attachmentFileUploadInfoDetailFileTypes);
			rowData.put("attachmentFileUploadInfoDetailFileSizes", attachmentFileUploadInfoDetailFileSizes);
			rowData.put("attachmentFileUploadInfoDetailUploadTimes", attachmentFileUploadInfoDetailUploadTimes);
			rowData.put("attachmentFileUploadInfoDetailUploadUsers", attachmentFileUploadInfoDetailUploadUsers);
			rowData.put("attachmentFileUploadInfoDetailRemarks", attachmentFileUploadInfoDetailRemarks);
		}
		return tableJson.toString();
	}

	@Override
	public String getAttachmentUploadFileSize(MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		return multipartFile.getSize() + "";
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tenwa.business.service.AttachmentFileService#uploadAttachmentFile(org.springframework.web.multipart.MultipartFile,
	 *      java.util.Map)
	 **/

	@Override
	public String uploadAttachmentFile(HttpServletRequest request, MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		// 当前用户信息
		User user = (User) SecurityUtil.getPrincipal();

		String filename = multipartFile.getOriginalFilename();
		String encodeFileNameNoExtentName = UUIDUtil.getUUID();
		String currentTime = DateUtil.getSystemDateTime();
		String currentDate = currentTime.substring(0, 10);

		String module = modelData.get("module");
		String attachmentFileDictId = modelData.get("attachmentFileDictId");
		String attachmentFileUploadInfoId = StringUtil.nullToString(modelData.get("attachmentFileUploadInfoId"));
		String identifierOne = modelData.get("identifierOne");
		String identifierTwo = modelData.get("identifierTwo");
		String identifierThree = modelData.get("identifierThree");
		String identifierFour = modelData.get("identifierFour");
		String identifierFive = modelData.get("identifierFive");
		String identifierSix = modelData.get("identifierSix");
		String identifierSeven = modelData.get("identifierSeven");
		String identifierEight = modelData.get("identifierEight");
		String identifierNine = modelData.get("identifierNine");
		String identifierTen = modelData.get("identifierTen");
		String tFileImagesId = modelData.get("tFileImagesId");
		String remark = modelData.get("remark");
		String jbpmWorkflowHistoryInfoId = modelData.get("jbpmWorkflowHistoryInfoId");
		String attachmentFileUnionKey = modelData.get("attachmentFileUnionKey");
		// AttachmentFileDict attachmentFileDict =
		// (AttachmentFileDict)this.attachmentFileDao.getEntityObjectById(AttachmentFileDict.class,attachmentFileDictId);
		DictionaryData attachmentFileDict = (DictionaryData) this.attachmentFileDao.getEntityObjectById(DictionaryData.class, attachmentFileDictId);
		if ((null != multipartFile) && (!StringUtil.nullToString(filename).isEmpty())) {
			AttachmentFileUploadInfo attachmentFileUploadInfo = null;
			String extentName = filename.substring(filename.lastIndexOf("."), filename.length());
			if (!attachmentFileUploadInfoId.isEmpty()) {
				attachmentFileUploadInfo = (AttachmentFileUploadInfo) this.attachmentFileDao.getEntityObjectById(AttachmentFileUploadInfo.class, attachmentFileUploadInfoId);
			} else {
				attachmentFileUploadInfo = new AttachmentFileUploadInfo();
				attachmentFileUploadInfo.setModule(module);
				attachmentFileUploadInfo.setIdentifierOne(identifierOne);
				attachmentFileUploadInfo.setIdentifierTwo(identifierTwo);
				attachmentFileUploadInfo.setIdentifierThree(identifierThree);
				attachmentFileUploadInfo.setIdentifierFour(identifierFour);
				attachmentFileUploadInfo.setIdentifierFive(identifierFive);
				attachmentFileUploadInfo.setIdentifierSix(identifierSix);
				attachmentFileUploadInfo.setIdentifierSeven(identifierSeven);
				attachmentFileUploadInfo.setIdentifierEight(identifierEight);
				attachmentFileUploadInfo.setIdentifierNine(identifierNine);
				attachmentFileUploadInfo.setIdentifierTen(identifierTen);
				attachmentFileUploadInfo.setAttachmentFileDict(attachmentFileDict);
				attachmentFileUploadInfo.setModule(module);
				attachmentFileUploadInfo.setUnionKey(attachmentFileUnionKey);

				attachmentFileUploadInfoId = this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfo);
			}
			AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = new AttachmentFileUploadInfoDetail();
			attachmentFileUploadInfoDetail.setAttachmentFileUploadInfo(attachmentFileUploadInfo);
			attachmentFileUploadInfoDetail.setChineseFileName(filename);
			attachmentFileUploadInfoDetail.setEncodeFileName(encodeFileNameNoExtentName + extentName);
			attachmentFileUploadInfoDetail.setFileSize(multipartFile.getSize());
			attachmentFileUploadInfoDetail.setRemark(remark);
			attachmentFileUploadInfoDetail.setFileImagesId(tFileImagesId);
			attachmentFileUploadInfoDetail.setUploadUser(user);
			attachmentFileUploadInfoDetail.setUploadTime(currentTime);
			attachmentFileUploadInfoDetail.setIdentifierTwo(identifierTwo);
			attachmentFileUploadInfoDetail.setIdentifierFour(identifierFour);
			attachmentFileUploadInfoDetail.setIdentifierSeven(identifierSeven);

			if (null != jbpmWorkflowHistoryInfoId) {
				// 建立与流程关联
				JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo = (JBPMWorkflowHistoryInfo) this.attachmentFileDao.getEntityObjectById(JBPMWorkflowHistoryInfo.class, jbpmWorkflowHistoryInfoId);
				attachmentFileUploadInfoDetail.setJbpmWorkflowHistoryInfo(jbpmWorkflowHistoryInfo);
				HistoryProcessInstanceImpl historyProcessInstanceImpl = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl();
				attachmentFileUploadInfoDetail.setHistoryProcessInstanceImpl(historyProcessInstanceImpl);
				JbpmWorkflowDesigner jbpmWorkflowDesigner = jbpmWorkflowHistoryInfo.getDeploymentImpl().getJbpmWorkflowDesigner();
				attachmentFileUploadInfoDetail.setJbpmWorkflowDesigner(jbpmWorkflowDesigner);
				attachmentFileUploadInfoDetail.setActivityDetail(jbpmWorkflowHistoryInfo.getActivityDetail());
			}
			this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfoDetail);
			// /将attachmentFileUploadInfoDetail的id回显示给map
			modelData.put("currentCttachmentFileUploadInfoDetailId", attachmentFileUploadInfoDetail.getId());
			String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + "/" + module + "/" + FileUtil.getYearMonthDayPathByString(currentDate, "yyyy-MM-dd");
			/*
			 * +identifierOne;
			 * if(!StringUtil.nullToString(identifierTwo).isEmpty
			 * ()){uploadDirPath+=("/"+identifierTwo);}
			 * if(!StringUtil.nullToString
			 * (identifierThree).isEmpty()){uploadDirPath
			 * +=("/"+identifierThree);}
			 * if(!StringUtil.nullToString(identifierFour
			 * ).isEmpty()){uploadDirPath+=("/"+identifierFour);}
			 * if(!StringUtil.
			 * nullToString(identifierFive).isEmpty()){uploadDirPath
			 * +=("/"+identifierFive);}
			 * if(!StringUtil.nullToString(identifierSix
			 * ).isEmpty()){uploadDirPath+=("/"+identifierSix);}
			 * if(!StringUtil.nullToString
			 * (identifierSeven).isEmpty()){uploadDirPath
			 * +=("/"+identifierSeven);}
			 * if(!StringUtil.nullToString(identifierEight
			 * ).isEmpty()){uploadDirPath+=("/"+identifierEight);}
			 * if(!StringUtil
			 * .nullToString(identifierNine).isEmpty()){uploadDirPath
			 * +=("/"+identifierNine);}
			 * if(!StringUtil.nullToString(identifierTen
			 * ).isEmpty()){uploadDirPath+=("/"+identifierTen);}
			 */
			
			String uploadedFileFullPath = uploadDirPath + "/" + encodeFileNameNoExtentName + extentName;
			HttpSession session = request.getSession();
			FileProcess fileProcess = (FileProcess) session.getAttribute("attachmentFileProcessKey");
			InputStream in =null;
			try {
				in=multipartFile.getInputStream();
				ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, in, fileProcess);
			} catch (Exception e) {
				throw new BusinessException(e.getMessage());
			}finally{
				if(null!=in){FileUtil.safeCloseInputStream(in);}
			}
			return "文件上传成功!";
		} else {
			return "上传文件不能为空!";
		}
	}
	@Override
	public String uploadCustFile(HttpServletRequest request, MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		// 当前用户信息
		User user = (User) SecurityUtil.getPrincipal();

		String filename = multipartFile.getOriginalFilename();
		String encodeFileNameNoExtentName = UUIDUtil.getUUID();
		String currentTime = DateUtil.getSystemDateTime();
		String currentDate = currentTime.substring(0, 10);

		String custid=modelData.get("cust_id");
		String module = modelData.get("module");
		String attachmentFileDictId = modelData.get("attachmentFileDictId");
		String attachmentFileUploadInfoId = StringUtil.nullToString(modelData.get("attachmentFileUploadInfoId"));
		
		String tFileImagesId = modelData.get("tFileImagesId");
		// AttachmentFileDict attachmentFileDict =
		// (AttachmentFileDict)this.attachmentFileDao.getEntityObjectById(AttachmentFileDict.class,attachmentFileDictId);
		DictionaryData attachmentFileDict = (DictionaryData) this.attachmentFileDao.getEntityObjectById(DictionaryData.class, attachmentFileDictId);
		if ((null != multipartFile) && (!StringUtil.nullToString(filename).isEmpty())) {
			AttachmentFileUploadInfo attachmentFileUploadInfo = null;
			String extentName = filename.substring(filename.lastIndexOf("."), filename.length());
			Map<String,Object> map=new HashMap<String,Object>();
			if(custid!=null)
			{	
				map.put("cust", this.tableService.findEntityByID(CustInfo.class, custid));
				map.put("attachmentFileDict", this.tableService.findEntityByID(DictionaryData.class, attachmentFileDictId));
				map.put("module", module);
			}
			else
			{	
				map.put("module", module);
				map.put("identifierTwo", user.getId());
				map.put("attachmentFileDict", this.tableService.findEntityByID(DictionaryData.class, attachmentFileDictId));
			}
			List<AttachmentFileUploadInfo> list=this.tableService.findEntityByProperties(AttachmentFileUploadInfo.class, map);
			if (list.size()>0) {
				attachmentFileUploadInfo = list.get(0);
			} else {
				attachmentFileUploadInfo = new AttachmentFileUploadInfo();
				attachmentFileUploadInfo.setModule(module);
				attachmentFileUploadInfo.setAttachmentFileDict(attachmentFileDict);
				attachmentFileUploadInfo.setIdentifierOne("1");
				
				if(custid!=null)
					attachmentFileUploadInfo.setCust(this.tableService.findEntityByID(CustInfo.class, custid));
				else
					attachmentFileUploadInfo.setIdentifierTwo(user.getId());
				attachmentFileUploadInfoId = this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfo);
			}
			AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = null;
			Map<String,Object> detailmap=new HashMap<String,Object>();
			detailmap.put("attachmentFileUploadInfo", attachmentFileUploadInfo);
			List<AttachmentFileUploadInfoDetail> detaillist=this.tableService.findEntityByProperties(AttachmentFileUploadInfoDetail.class, detailmap);
			if(detaillist.size()>0)
			{
				attachmentFileUploadInfoDetail=detaillist.get(0);
				attachmentFileUploadInfoDetail.setChineseFileName(filename);
				attachmentFileUploadInfoDetail.setEncodeFileName(encodeFileNameNoExtentName + extentName);
				attachmentFileUploadInfoDetail.setFileSize(multipartFile.getSize());
				attachmentFileUploadInfoDetail.setFileImagesId(tFileImagesId);
				attachmentFileUploadInfoDetail.setUploadUser(user);
				attachmentFileUploadInfoDetail.setUploadTime(currentTime);
				
			}
			else
			{
				attachmentFileUploadInfoDetail = new AttachmentFileUploadInfoDetail();
				attachmentFileUploadInfoDetail.setAttachmentFileUploadInfo(attachmentFileUploadInfo);
				attachmentFileUploadInfoDetail.setChineseFileName(filename);
				attachmentFileUploadInfoDetail.setEncodeFileName(encodeFileNameNoExtentName + extentName);
				attachmentFileUploadInfoDetail.setFileSize(multipartFile.getSize());
				attachmentFileUploadInfoDetail.setFileImagesId(tFileImagesId);
				attachmentFileUploadInfoDetail.setUploadUser(user);
				attachmentFileUploadInfoDetail.setUploadTime(currentTime);
			}
			
			this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfoDetail);
			// /将attachmentFileUploadInfoDetail的id回显示给map
			modelData.put("currentCttachmentFileUploadInfoDetailId", attachmentFileUploadInfoDetail.getId());
			String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + "/" + module + "/" + FileUtil.getYearMonthDayPathByString(currentDate, "yyyy-MM-dd");

			
			String uploadedFileFullPath = uploadDirPath + "/" + encodeFileNameNoExtentName + extentName;
			HttpSession session = request.getSession();
			FileProcess fileProcess = (FileProcess) session.getAttribute("attachmentFileProcessKey");
			InputStream in =null;
			try {
				in=multipartFile.getInputStream();
				ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, in, fileProcess);
			} catch (Exception e) {
				throw new BusinessException(e.getMessage());
			}finally{
				if(null!=in){FileUtil.safeCloseInputStream(in);}
			}
			return "文件上传成功!"+","+attachmentFileUploadInfoDetail.getChineseFileName()+","+attachmentFileUploadInfoDetail.getId()+","+attachmentFileDictId;
		} else {
			return "上传文件不能为空!";
		}
	}
	@Override
	public String uploadAttachmentFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			
			String attachmentFileUploadInfoId = "";
			while (iter.hasNext()) {
				String filenameTemp = iter.next();
				MultipartFile multipartFile = multiRequest.getFile(filenameTemp);
				if (multipartFile != null) {
					String myFileName = multipartFile.getOriginalFilename();
					String fileType = myFileName.substring(myFileName.indexOf('.') + 1).toLowerCase();
					String tFileImagesId = "";
					if (fileType.matches("jpg|jpeg|png|bmp|gif")) {
						AppImageService appImage = (AppImageService) WebUtil.getApplicationContext().getBean("appImageService");
						String result = appImage.addAppImage(request, filenameTemp);
						JSONObject jo = new JSONObject(result);
						if(jo.has("id")){
							tFileImagesId = jo.getString("id");
							modelData.put("tFileImagesId", tFileImagesId);// 入库记录并与删除接口对接，时间数据同步。
						}
					}
					// 当前用户信息
					User user = (User) SecurityUtil.getPrincipal();

					String filename = multipartFile.getOriginalFilename();
					String encodeFileNameNoExtentName = UUIDUtil.getUUID();
					String currentTime = DateUtil.getSystemDateTime();
					String currentDate = currentTime.substring(0, 10);

					String module = modelData.get("module");
					String attachmentFileDictId = modelData.get("attachmentFileDictId");
					attachmentFileUploadInfoId = "".equals(attachmentFileUploadInfoId) ? StringUtil.nullToString(modelData.get("attachmentFileUploadInfoId")) : attachmentFileUploadInfoId;
					String identifierOne = modelData.get("identifierOne");
					String identifierTwo = modelData.get("identifierTwo");
					String identifierThree = modelData.get("identifierThree");
					String identifierFour = modelData.get("identifierFour");
					String identifierFive = modelData.get("identifierFive");
					String identifierSix = modelData.get("identifierSix");
					String identifierSeven = modelData.get("identifierSeven");
					String identifierEight = modelData.get("identifierEight");
					String identifierNine = modelData.get("identifierNine");
					String identifierTen = modelData.get("identifierTen");
					String remark = modelData.get("remark");
					String jbpmWorkflowHistoryInfoId = modelData.get("jbpmWorkflowHistoryInfoId");
					String attachmentFileUnionKey = modelData.get("attachmentFileUnionKey");
					// AttachmentFileDict attachmentFileDict =
					// (AttachmentFileDict)this.attachmentFileDao.getEntityObjectById(AttachmentFileDict.class,attachmentFileDictId);
					DictionaryData attachmentFileDict = (DictionaryData) this.attachmentFileDao.getEntityObjectById(DictionaryData.class, attachmentFileDictId);
					if ((null != multipartFile) && (!StringUtil.nullToString(filename).isEmpty())) {
						AttachmentFileUploadInfo attachmentFileUploadInfo = null;
						String extentName = filename.substring(filename.lastIndexOf("."), filename.length());
						if (!attachmentFileUploadInfoId.isEmpty()) {
							attachmentFileUploadInfo = (AttachmentFileUploadInfo) this.attachmentFileDao.getEntityObjectById(AttachmentFileUploadInfo.class, attachmentFileUploadInfoId);
						} else {
							attachmentFileUploadInfo = new AttachmentFileUploadInfo();
							attachmentFileUploadInfo.setModule(module);
							attachmentFileUploadInfo.setIdentifierOne(identifierOne);
							attachmentFileUploadInfo.setIdentifierTwo(identifierTwo);
							attachmentFileUploadInfo.setIdentifierThree(identifierThree);
							attachmentFileUploadInfo.setIdentifierFour(identifierFour);
							attachmentFileUploadInfo.setIdentifierFive(identifierFive);
							attachmentFileUploadInfo.setIdentifierSix(identifierSix);
							attachmentFileUploadInfo.setIdentifierSeven(identifierSeven);
							attachmentFileUploadInfo.setIdentifierEight(identifierEight);
							attachmentFileUploadInfo.setIdentifierNine(identifierNine);
							attachmentFileUploadInfo.setIdentifierTen(identifierTen);
							attachmentFileUploadInfo.setAttachmentFileDict(attachmentFileDict);
							attachmentFileUploadInfo.setModule(module);
							attachmentFileUploadInfo.setUnionKey(attachmentFileUnionKey);

							attachmentFileUploadInfoId = this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfo);
						}
						AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = new AttachmentFileUploadInfoDetail();
						attachmentFileUploadInfoDetail.setAttachmentFileUploadInfo(attachmentFileUploadInfo);
						attachmentFileUploadInfoDetail.setChineseFileName(filename);
						attachmentFileUploadInfoDetail.setEncodeFileName(encodeFileNameNoExtentName + extentName);
						attachmentFileUploadInfoDetail.setFileSize(multipartFile.getSize());
						attachmentFileUploadInfoDetail.setRemark(remark);
						attachmentFileUploadInfoDetail.setFileImagesId(tFileImagesId);
						attachmentFileUploadInfoDetail.setUploadUser(user);
						attachmentFileUploadInfoDetail.setUploadTime(currentTime);
						attachmentFileUploadInfoDetail.setIdentifierTwo(identifierTwo);
						attachmentFileUploadInfoDetail.setIdentifierFour(identifierFour);
						attachmentFileUploadInfoDetail.setIdentifierSeven(identifierSeven);
						if (null != jbpmWorkflowHistoryInfoId) {
							// 建立与流程关联
							JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo = (JBPMWorkflowHistoryInfo) this.attachmentFileDao.getEntityObjectById(JBPMWorkflowHistoryInfo.class, jbpmWorkflowHistoryInfoId);
							attachmentFileUploadInfoDetail.setJbpmWorkflowHistoryInfo(jbpmWorkflowHistoryInfo);
							HistoryProcessInstanceImpl historyProcessInstanceImpl = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl();
							attachmentFileUploadInfoDetail.setHistoryProcessInstanceImpl(historyProcessInstanceImpl);
							JbpmWorkflowDesigner jbpmWorkflowDesigner = jbpmWorkflowHistoryInfo.getDeploymentImpl().getJbpmWorkflowDesigner();
							attachmentFileUploadInfoDetail.setJbpmWorkflowDesigner(jbpmWorkflowDesigner);
							attachmentFileUploadInfoDetail.setActivityDetail(jbpmWorkflowHistoryInfo.getActivityDetail());
						}
						this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfoDetail);
						// /将attachmentFileUploadInfoDetail的id回显示给map
						modelData.put("currentCttachmentFileUploadInfoDetailId", attachmentFileUploadInfoDetail.getId());
						String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + "/" + module + "/" + FileUtil.getYearMonthDayPathByString(currentDate, "yyyy-MM-dd");
						String uploadedFileFullPath = uploadDirPath + "/" + encodeFileNameNoExtentName + extentName;
						HttpSession session = request.getSession();
						FileProcess fileProcess = (FileProcess) session.getAttribute("attachmentFileProcessKey");
						InputStream in = null;
						try {
							in=multipartFile.getInputStream();
							ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, in, fileProcess);
						} catch (Exception e) {
							throw new BusinessException(e.getMessage());
						}finally{
							if(null!=in){FileUtil.safeCloseInputStream(in);}
						}
				}
			}
		}
			return "文件上传成功!";
		} else {
			return "上传文件不能为空!";
		}
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tenwa.business.service.AttachmentFileService#removeAttachmentFile(java.lang.String)
	 **/

	@Override
	public String removeAttachmentFile(String uploadAttachmentFileDetailId) throws Exception {
		AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = (AttachmentFileUploadInfoDetail) this.attachmentFileDao.getEntityObjectById(AttachmentFileUploadInfoDetail.class, uploadAttachmentFileDetailId);
		// 取消与流程关联
		attachmentFileUploadInfoDetail.setJbpmWorkflowHistoryInfo(null);
		String uploadTime = attachmentFileUploadInfoDetail.getUploadTime();
		String uploadDate = uploadTime.substring(0, 10);
		AttachmentFileUploadInfo attachmentFileUploadInfo = attachmentFileUploadInfoDetail.getAttachmentFileUploadInfo();
		String module = attachmentFileUploadInfo.getModule();
		/*
		 * String identifierOne = attachmentFileUploadInfo.getIdentifierOne();
		 * String identifierTwo = attachmentFileUploadInfo.getIdentifierTwo();
		 * String identifierThree =
		 * attachmentFileUploadInfo.getIdentifierThree(); String identifierFour
		 * = attachmentFileUploadInfo.getIdentifierFour(); String identifierFive
		 * = attachmentFileUploadInfo.getIdentifierFive(); String identifierSix
		 * = attachmentFileUploadInfo.getIdentifierSix(); String identifierSeven
		 * = attachmentFileUploadInfo.getIdentifierSeven(); String
		 * identifierEight = attachmentFileUploadInfo.getIdentifierEight();
		 * String identifierNine = attachmentFileUploadInfo.getIdentifierNine();
		 * String identifierTen = attachmentFileUploadInfo.getIdentifierTen();
		 */

		for (AttachmentFileUploadInfoDetailDownload attachmentFileUploadInfoDetailDownload : attachmentFileUploadInfoDetail.getAttachmentFileUploadInfoDetailDownloads()) {
			this.attachmentFileDao.removeEntityObject(attachmentFileUploadInfoDetailDownload);
		}
		String tFileImagesId = attachmentFileUploadInfoDetail.getFileImagesId();
		if(tFileImagesId != null && !tFileImagesId.isEmpty()){
			tableService.removeEntityById(AppImage.class, tFileImagesId);
		}
		this.attachmentFileDao.removeEntityObject(attachmentFileUploadInfoDetail);

		if (1 == attachmentFileUploadInfo.getAttachmentFileUploadInfoDetails().size()) {
			this.attachmentFileDao.removeEntityObject(attachmentFileUploadInfo);
		}
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + "/" + module + "/" + FileUtil.getYearMonthDayPathByString(uploadDate, "yyyy-MM-dd");
		/*
		 * String uploadDirPath =
		 * FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath
		 * ())+"/"+uploadDate+"/"+identifierOne;
		 * if(!StringUtil.nullToString(identifierTwo
		 * ).isEmpty()){uploadDirPath+=("/"+identifierTwo);}
		 * if(!StringUtil.nullToString
		 * (identifierThree).isEmpty()){uploadDirPath+=("/"+identifierThree);}
		 * if
		 * (!StringUtil.nullToString(identifierFour).isEmpty()){uploadDirPath+=
		 * ("/"+identifierFour);}
		 * if(!StringUtil.nullToString(identifierFive).isEmpty
		 * ()){uploadDirPath+=("/"+identifierFive);}
		 * if(!StringUtil.nullToString(
		 * identifierSix).isEmpty()){uploadDirPath+=("/"+identifierSix);}
		 * if(!StringUtil
		 * .nullToString(identifierSeven).isEmpty()){uploadDirPath+=
		 * ("/"+identifierSeven);}
		 * if(!StringUtil.nullToString(identifierEight).isEmpty
		 * ()){uploadDirPath+=("/"+identifierEight);}
		 * if(!StringUtil.nullToString
		 * (identifierNine).isEmpty()){uploadDirPath+=("/"+identifierNine);}
		 * if(!
		 * StringUtil.nullToString(identifierTen).isEmpty()){uploadDirPath+=(
		 * "/"+identifierTen);}
		 */
		String encodeFileName = attachmentFileUploadInfoDetail.getEncodeFileName();
		String uploadedFileFullPath = uploadDirPath + "/" + encodeFileName;
		try {
			ResourceUtil.getFileUploadOperation().removeFile(uploadedFileFullPath);
		} catch (Exception e) {
			String info = "强制删除文件：" + uploadedFileFullPath;
			if (log.isInfoEnabled()) {
				log.info(info);
			}
		}
		return "删除成功！";
	}

	@Override
	public void downloadAttachmentFile(HttpServletResponse response, String uploadAttachmentFileDetailId, String browserType) throws Exception {
		AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = (AttachmentFileUploadInfoDetail) this.attachmentFileDao.getEntityObjectById(AttachmentFileUploadInfoDetail.class, uploadAttachmentFileDetailId);
		String fileTitleName = attachmentFileUploadInfoDetail.getChineseFileName();
		if ("firefox".equals(browserType)) {
			fileTitleName = new String(fileTitleName.getBytes("GB2312"), "ISO-8859-1");
		} else if ("chrome".equals(browserType)) {
			fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
		} else if ("safari".equals(browserType)) {
			fileTitleName = new String(fileTitleName.getBytes("UTF-8"), "ISO-8859-1");
		} else {
			fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
		}

		String uploadTime = attachmentFileUploadInfoDetail.getUploadTime();
		String uploadDate = uploadTime.substring(0, 10);
		AttachmentFileUploadInfo attachmentFileUploadInfo = attachmentFileUploadInfoDetail.getAttachmentFileUploadInfo();

		String module = attachmentFileUploadInfo.getModule();
		/*
		 * String identifierOne = attachmentFileUploadInfo.getIdentifierOne();
		 * String identifierTwo = attachmentFileUploadInfo.getIdentifierTwo();
		 * String identifierThree =
		 * attachmentFileUploadInfo.getIdentifierThree(); String identifierFour
		 * = attachmentFileUploadInfo.getIdentifierFour(); String identifierFive
		 * = attachmentFileUploadInfo.getIdentifierFive(); String identifierSix
		 * = attachmentFileUploadInfo.getIdentifierSix(); String identifierSeven
		 * = attachmentFileUploadInfo.getIdentifierSeven(); String
		 * identifierEight = attachmentFileUploadInfo.getIdentifierEight();
		 * String identifierNine = attachmentFileUploadInfo.getIdentifierNine();
		 * String identifierTen = attachmentFileUploadInfo.getIdentifierTen();
		 * 
		 * String uploadDirPath =
		 * ResourceUtil.getFileUploadDataPath()+"/"+uploadDate
		 * +"/"+identifierOne;
		 * if(!StringUtil.nullToString(identifierTwo).isEmpty
		 * ()){uploadDirPath+=("/"+identifierTwo);}
		 * if(!StringUtil.nullToString(identifierThree
		 * ).isEmpty()){uploadDirPath+=("/"+identifierThree);}
		 * if(!StringUtil.nullToString
		 * (identifierFour).isEmpty()){uploadDirPath+=("/"+identifierFour);}
		 * if(!
		 * StringUtil.nullToString(identifierFive).isEmpty()){uploadDirPath+=
		 * ("/"+identifierFive);}
		 * if(!StringUtil.nullToString(identifierSix).isEmpty
		 * ()){uploadDirPath+=("/"+identifierSix);}
		 * if(!StringUtil.nullToString(identifierSeven
		 * ).isEmpty()){uploadDirPath+=("/"+identifierSeven);}
		 * if(!StringUtil.nullToString
		 * (identifierEight).isEmpty()){uploadDirPath+=("/"+identifierEight);}
		 * if
		 * (!StringUtil.nullToString(identifierNine).isEmpty()){uploadDirPath+=
		 * ("/"+identifierNine);}
		 * if(!StringUtil.nullToString(identifierTen).isEmpty
		 * ()){uploadDirPath+=("/"+identifierTen);}
		 */
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + "/" + module + "/" + FileUtil.getYearMonthDayPathByString(uploadDate, "yyyy-MM-dd");

		String encodeFileName = attachmentFileUploadInfoDetail.getEncodeFileName();
		String downloadedFileFullPath = FileUtil.getFilePathString(uploadDirPath + "/" + encodeFileName);

		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
		response.setContentType("text/html; charset=UTF-8");
		OutputStream out = response.getOutputStream();
		ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
		// 当前用户信息
		User downloadUser = (User) SecurityUtil.getPrincipal();
		AttachmentFileUploadInfoDetailDownload attachmentFileUploadInfoDetailDownload = new AttachmentFileUploadInfoDetailDownload();
		attachmentFileUploadInfoDetailDownload.setAttachmentFileUploadInfoDetail(attachmentFileUploadInfoDetail);
		attachmentFileUploadInfoDetailDownload.setDownloadUser(downloadUser);
		attachmentFileUploadInfoDetailDownload.setDownloadTime(DateUtil.getSystemDateTime());
		this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfoDetailDownload);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tenwa.business.service.AttachmentFileService#downloadBatchAttachmentFiles(javax.servlet.http.HttpServletResponse,
	 *      java.lang.String, java.lang.String)
	 **/

	@Override
	public void downloadBatchAttachmentFiles(HttpServletResponse response, String fileTitleName, String attchmentDetailBatchDownloadIdStr, String browserType) throws Exception {
		if (null == fileTitleName) {
			fileTitleName = "批量下载" + DateUtil.getSystemTimeByFormat("yyyy-MM-dd:HH:mm:ss");
		}
		fileTitleName += ".zip";
		if ("firefox".equals(browserType)) {
			fileTitleName = new String(fileTitleName.getBytes("GB2312"), "ISO-8859-1");
		} else if ("chrome".equals(browserType)) {
			fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
		} else if ("safari".equals(browserType)) {
			fileTitleName = new String(fileTitleName.getBytes("UTF-8"), "ISO-8859-1");
		} else {
			fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
		}
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
		response.setContentType("text/html; charset=UTF-8");

		OutputStream os = null;
		ZipOutputStream out = null;
		String charSetName = "GB2312";
		String baseDir = new File(StrTools.fileNameCheck(ResourceUtil.getFileUploadDataPath())).getAbsolutePath();

		try {
			os = response.getOutputStream();
			out = new ZipOutputStream(os);
			out.setEncoding(charSetName);

			Collection<String> attchmentDetails = StringUtil.getCollectionByString("list", attchmentDetailBatchDownloadIdStr, ",");
			// AttachmentFileUploadInfoDetail
			// attachmentFileUploadInfoDetail=(AttachmentFileUploadInfoDetail)this.attachmentFileDao.getEntityObjectById(AttachmentFileUploadInfoDetail.class,
			// uploadAttachmentFileDetailId);
			String[] detailIds = new String[attchmentDetails.size()];
			detailIds = attchmentDetails.toArray(detailIds);
			List<AttachmentFileUploadInfoDetail> fileDetails = this.attachmentFileDao.findEntityByIDArray(AttachmentFileUploadInfoDetail.class, detailIds);
			int fileIndex = 0;
			String currentDateTime = DateUtil.getSystemDateTime();
			for (Object obj : fileDetails) {
				AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = (AttachmentFileUploadInfoDetail) obj;
				String uploadTime = attachmentFileUploadInfoDetail.getUploadTime();
				String uploadDate = uploadTime.substring(0, 10);
				AttachmentFileUploadInfo attachmentFileUploadInfo = attachmentFileUploadInfoDetail.getAttachmentFileUploadInfo();
				String module = attachmentFileUploadInfo.getModule();
				/*
				 * String identifierOne =
				 * attachmentFileUploadInfo.getIdentifierOne(); String
				 * identifierTwo = attachmentFileUploadInfo.getIdentifierTwo();
				 * String identifierThree =
				 * attachmentFileUploadInfo.getIdentifierThree(); String
				 * identifierFour =
				 * attachmentFileUploadInfo.getIdentifierFour(); String
				 * identifierFive =
				 * attachmentFileUploadInfo.getIdentifierFive(); String
				 * identifierSix = attachmentFileUploadInfo.getIdentifierSix();
				 * String identifierSeven =
				 * attachmentFileUploadInfo.getIdentifierSeven(); String
				 * identifierEight =
				 * attachmentFileUploadInfo.getIdentifierEight(); String
				 * identifierNine =
				 * attachmentFileUploadInfo.getIdentifierNine(); String
				 * identifierTen = attachmentFileUploadInfo.getIdentifierTen();
				 * 
				 * String uploadDirPath =
				 * ResourceUtil.getFileUploadDataPath()+"/"
				 * +uploadDate+"/"+identifierOne;
				 * if(!StringUtil.nullToString(identifierTwo
				 * ).isEmpty()){uploadDirPath+=("/"+identifierTwo);}
				 * if(!StringUtil
				 * .nullToString(identifierThree).isEmpty()){uploadDirPath
				 * +=("/"+identifierThree);}
				 * if(!StringUtil.nullToString(identifierFour
				 * ).isEmpty()){uploadDirPath+=("/"+identifierFour);}
				 * if(!StringUtil
				 * .nullToString(identifierFive).isEmpty()){uploadDirPath
				 * +=("/"+identifierFive);}
				 * if(!StringUtil.nullToString(identifierSix
				 * ).isEmpty()){uploadDirPath+=("/"+identifierSix);}
				 * if(!StringUtil
				 * .nullToString(identifierSeven).isEmpty()){uploadDirPath
				 * +=("/"+identifierSeven);}
				 * if(!StringUtil.nullToString(identifierEight
				 * ).isEmpty()){uploadDirPath+=("/"+identifierEight);}
				 * if(!StringUtil
				 * .nullToString(identifierNine).isEmpty()){uploadDirPath
				 * +=("/"+identifierNine);}
				 * if(!StringUtil.nullToString(identifierTen
				 * ).isEmpty()){uploadDirPath+=("/"+identifierTen);}
				 */
				String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + "/" + module + "/" + FileUtil.getYearMonthDayPathByString(uploadDate, "yyyy-MM-dd");
				int lastIndexOfChineseName = attachmentFileUploadInfoDetail.getChineseFileName().lastIndexOf(".");
				String chineseName = attachmentFileUploadInfoDetail.getChineseFileName().substring(0, lastIndexOfChineseName) + "（" + (++fileIndex) + "）" + attachmentFileUploadInfoDetail.getChineseFileName().substring(lastIndexOfChineseName);
				String encodeFileName = attachmentFileUploadInfoDetail.getEncodeFileName();
				String inputFileFullPath = FileUtil.getFilePathString(uploadDirPath + "/" + encodeFileName);
				// 下载文件
				// File inputFile = new File(StrTools.fileNameCheck(inputFileFullPath));
				// String parentFullPath =
				// inputFile.getParentFile().getAbsolutePath();
				// out.putNextEntry(new
				// ZipEntry(parentFullPath.substring(baseDir.length(),parentFullPath.length()).replaceAll("^(/|\\\\)+",
				// "")+"/"+chineseName));
				out.putNextEntry(new ZipEntry(currentDateTime + "/" + chineseName));
				ResourceUtil.getFileUploadOperation().readInputStreamToOutputStream(inputFileFullPath, out);
				// 当前用户信息
				User downloadUser = (User) SecurityUtil.getPrincipal();
				AttachmentFileUploadInfoDetailDownload attachmentFileUploadInfoDetailDownload = new AttachmentFileUploadInfoDetailDownload();
				attachmentFileUploadInfoDetailDownload.setAttachmentFileUploadInfoDetail(attachmentFileUploadInfoDetail);
				attachmentFileUploadInfoDetailDownload.setDownloadUser(downloadUser);
				attachmentFileUploadInfoDetailDownload.setDownloadTime(DateUtil.getSystemDateTime());
				this.attachmentFileDao.saveEntityObject(attachmentFileUploadInfoDetailDownload);
			}
		} finally {
			if (null != out) {
				out.closeEntry();
				out.flush();
				FileUtil.safeCloseOutStream(out);
			}
			if (null != os) {
				os.flush();
				FileUtil.safeCloseOutStream(os);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tenwa.business.service.AttachmentFileService#updateOnlineAttachmentFile(org.springframework.web.multipart.MultipartFile,
	 *      java.util.Map)
	 **/

	@Override
	public String updateOnlineAttachmentFile(MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		String filename = multipartFile.getOriginalFilename();
		String uploadOnlineDirPath = WebUtil.getWebContextRealPath();
		String fullPath = FileUtil.getFilePathString(uploadOnlineDirPath + filename);
		multipartFile.transferTo(new File(StrTools.fileNameCheck(fullPath)));
		if (log.isWarnEnabled()) {
			log.warn("在线修改文件路径：" + fullPath);
		}
		return "在线修改成功";
	}

}
