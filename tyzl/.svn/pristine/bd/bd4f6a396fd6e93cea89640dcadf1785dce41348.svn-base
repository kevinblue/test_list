package com.tenwa.business.relationship.parse;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.RelationMap;
import com.tenwa.business.entity.Relationship;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.RelationService;
import com.tenwa.kernal.utils.WebUtil;

public class RelationParserFactory {
	private static final Logger log = LoggerFactory.getLogger(RelationParserFactory.class);
	@Resource
    private BaseDao baseDao;
	public static List<User> parser(User currentUser, String[] relationIds, BaseService baseService) {
		List<User> actorList = new ArrayList<User>();
		if(relationIds == null || relationIds.length ==0){
			return actorList;
		}
		RelationService relationService = WebUtil.getApplicationContext().getBean(RelationService.class);
		for(String relationId: relationIds){
			try {
				Relationship relation = relationService.findEntityByID(Relationship.class, relationId);
				if(relation.getRelationMaps() != null && relation.getRelationMaps().size() > 0){
					for(RelationMap rmap : relation.getRelationMaps()){
						RelationParser rp = null;
						switch(rmap.getParserClass()){
						case ADDRESS_PARSER:
							rp = new AddressRelationParser(baseService);
						default:
							break;
						
						}
						if(rp != null){
							actorList.addAll(rp.parser(currentUser,rmap));
						}
					}
				}
			} catch (DataAccessException e) {
				log.error("",e);
			} catch (Exception e) {
				log.error("",e);
			}
		}
		
		System.out.println("人员数量："+actorList.size());
		return actorList;
	}
}
