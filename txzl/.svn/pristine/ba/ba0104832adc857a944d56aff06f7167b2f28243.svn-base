
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         AttachmentDaoImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-9-上午11:35:43
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.AttachmentFileDao;
import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.kernal.utils.StringUtil;


 /**
 * 类名称：     AttachmentDaoImpl
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-9 上午11:35:43
 * 修改备注：
 * @version 1.0.0
 **/
@Repository(value="attachmentFileDao")
public class AttachmentDaoImpl  extends AbstractBaseDaoImpl implements AttachmentFileDao 
{
	/*@SuppressWarnings("unchecked")
	@Override
	public List getAttachmentFileInfosListByAttachmentType(
			String attachmentType, String identifierOne) throws Exception {
		String[] types = StringUtil.nullToString(attachmentType).split(",");
		String queryHsql = " select afd.id,afui from Dictionary dict left join  dict.datas afd left join afd.attachmentFileUploadInfos afui  " +
				" where dict.id in(:attachmentType) " +
				" and   afui.identifierOne = :identifierOne";
		
		List attachmentFileInfosList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"attachmentType","identifierOne"},new Object[]{types,identifierOne});
		return attachmentFileInfosList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List getAttachmentFileInfosListByAttachmentType(
			String attachmentType, String identifierOne, String identifierTwo)
			throws Exception {
		String[] types = attachmentType.split(",");
		String queryHsql = " select afd.id,afui from Dictionary dict left join  dict.datas  afd left join afd.attachmentFileUploadInfos afui  " +
				" where dict.id in(:attachmentType) " +
				" and   afui.identifierOne = :identifierOne " +
				" and   afui.identifierTwo = :identifierTwo ";
		
		List attachmentFileInfosList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"attachmentType","identifierOne","identifierTwo"},new Object[]{types,identifierOne,identifierTwo});
		return attachmentFileInfosList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List getAttachmentFileInfosListByAttachmentType(
			String attachmentType, String identifierOne, String identifierTwo,
			String identifierThree) throws Exception {
		String[] types = attachmentType.split(",");
		String queryHsql = " select afd.id,afui from Dictionary dict left join  dict.datas  afd left join afd.attachmentFileUploadInfos afui  " +
				" where dict.id in(:attachmentType) " +
				" and   afui.identifierOne = :identifierOne " +
				" and   afui.identifierTwo = :identifierTwo " +
				" and   afui.identifierThree = :identifierThree ";
		
		List attachmentFileInfosList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"attachmentType","identifierOne","identifierTwo","identifierThree"},new Object[]{types,identifierOne,identifierTwo,identifierThree});
		return attachmentFileInfosList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getAttachmentFileInfosListByAttachmentType(
			String attachmentType, String identifierOne, String identifierTwo,
			String identifierThree, String identifierFour) throws Exception {
		String[] types = attachmentType.split(",");
		String queryHsql = " select afd.id,afui from Dictionary dict left join  dict.datas  afd left join afd.attachmentFileUploadInfos afui  " +
				" where dict.id in(:attachmentType) " +
				" and   afui.identifierOne = :identifierOne " +
				" and   afui.identifierTwo = :identifierTwo " +
				" and   afui.identifierThree = :identifierThree " +
				" and   afui.identifierFour = :identifierFour ";
		
		List attachmentFileInfosList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"attachmentType","identifierTwo","identifierThree","identifierFour"},new Object[]{types,identifierOne,identifierTwo,identifierThree,identifierFour});
		return attachmentFileInfosList;
	}*/
	/*@Override
	public List<Object[]> getAttachmentFileInfosListByAttachmentType(
			String attachmentType, Map<String,String> identifiersMap) throws Exception {
		String[] types = StringUtil.nullToString(attachmentType).split(",");
		List<String> identifiersParamList = new ArrayList<String>();
		identifiersParamList.add("attachmentType");
		StringBuffer attachmentWhere = new StringBuffer(""); 
		List<Object> identifiersValueList = new ArrayList<Object>();
		identifiersValueList.add(types);
		for(String item : identifiersMap.keySet()){
			String identifierItem = "identifier"+item;
			attachmentWhere.append(" and afui.identifier"+item+" = :"+identifierItem);
			identifiersParamList.add(identifierItem);
			identifiersValueList.add(identifiersMap.get(item));
		}
		String []identifiersParam = new String[identifiersParamList.size()];
		Object []identifiersValue = new Object[identifiersValueList.size()];
		identifiersParam = identifiersParamList.toArray(identifiersParam);
		identifiersValue = identifiersValueList.toArray(identifiersValue);
		String queryHsql = " select afd.id,afui from Dictionary dict left join  dict.datas  afd left join afd.attachmentFileUploadInfos afui  " +
				" where dict.id in(:attachmentType) " + attachmentWhere.toString();
		
		List<Object[]> attachmentFileInfosList = this.findResultsByNamedParamHSQL(queryHsql, identifiersParam,identifiersValue);
		return attachmentFileInfosList;
	}
	public List<AttachmentFileUploadInfo> getAttachmentFileInfosListByIdentifierOne(Map<String,String> identifiersMap) throws Exception{
		String queryHsql = " select afui from AttachmentFileUploadInfo afui  " +
		" where afui.identifierOne = :identifierOne";
		List<AttachmentFileUploadInfo> attachmentFileInfosList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"identifierOne"},new Object[]{identifiersMap.get("identifierOne")});
		return attachmentFileInfosList;
	}*/
	@Override
	public List<DictionaryData> getAttachmentFileDictDatasListByAttachmentType(String attachmentType) throws Exception {
		String[] types = StringUtil.nullToString(attachmentType).split(",");
		String queryHsql = " select afd from Dictionary dict inner join dict.datas  afd where dict.id in(:attachmentType) " +
				" and dict.enabled = 1 and afd.enabled = 1 " +
				"order by dict.position,afd.position" ;
		List<DictionaryData> attachmentFileDictList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"attachmentType"},new Object[]{types});
		return attachmentFileDictList;
	}
	@Override
	public List<Dictionary> getAttachmentFileDictsListByAttachmentType(String attachmentType) throws Exception {
		String[] types = StringUtil.nullToString(attachmentType).split(",");
		String queryHsql = " select dict from Dictionary dict  where dict.parentDictionary.id in(:attachmentType) and dict.parentDictionary.enabled = 1 and dict.enabled = 1 order by dict.position" ;
		List<Dictionary> attachmentFileDictList = this.findResultsByNamedParamHSQL(queryHsql, new String[]{"attachmentType"},new Object[]{types});
		return attachmentFileDictList;
	}
	@Override
	public Object getEntityObjectById(Class<? extends Object> entityClass,String id) throws Exception {
		return this.getHibernateTemplate().get(entityClass, id);
	}
	
	
	@Override
	public String saveEntityObject(Object  entityObject)
			throws Exception {
		return this.getHibernateTemplate().save(entityObject).toString();
	}
	
	@Override
	public void removeEntityObject(Object entityObject) throws Exception {
		this.getHibernateTemplate().delete(entityObject);
		
	}
	@Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}
	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return this.jdbcTemplate;
	}
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	
	@SuppressWarnings({"unchecked"})
	@Override
	public RedisTemplate<String, String> getRedisTemplate() throws Exception {
		return this.redisTemplate;
	}
}
