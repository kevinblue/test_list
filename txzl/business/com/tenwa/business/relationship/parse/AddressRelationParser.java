package com.tenwa.business.relationship.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DepartmentRole;
import com.tenwa.business.entity.Group;
import com.tenwa.business.entity.RelationItem;
import com.tenwa.business.entity.RelationMap;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.entity.UserDepartmentRole;
import com.tenwa.business.entity.UserGroup;
import com.tenwa.business.service.BaseService;

public class AddressRelationParser implements RelationParser {
	private static final Logger log = LoggerFactory.getLogger(AddressRelationParser.class);
	private BaseService baseService;

	public AddressRelationParser(BaseService baseService) {
		this.baseService = baseService;
	}

	@Override
	public List<User> parser(User currentUser, RelationMap rmap) {
		List<User> rightUsers = new ArrayList<User>();
		List<String> orgKeys = getUserOrgKeys(currentUser);

		if (isUserInLeft(orgKeys, rmap.getLeftItems())) {
			for (RelationItem rightItem : rmap.getRightItems()) {
				List<User> addressUsers = null;
				try {
					String hql = "";
					switch (rightItem.getItemType()) {
					case GROUP:
						hql = "select u from User u left join u.userGroups ug left join ug.group g where g.id=:id";
						break;
					case DEPT:
						hql = "select u from User u left join u.userDepts ud left join ud.dept d where d.id=:id";
						break;
					case DEPTROLE:
						hql = "select u from User u left join u.userDeptRoles udr left join udr.deptRole dr where dr.id=:id";
						break;
					case USER:
						hql = "from User u where u.id=:id";
						break;
					default:
						break;
					}
					if (!"".equals(hql)) {
						System.out.println("hql---"+hql+"addressid---"+rightItem.getAddressId());
						addressUsers = this.baseService.findResultsByNamedParamHSQL(hql, new String[] { "id" },
								new String[] { rightItem.getAddressId() });
					}
				} catch (Exception e) {
					log.error("", e);
				}
				if (addressUsers != null) {
					rightUsers.addAll(addressUsers);
				}
			}
		}

		return rightUsers;
	}

	private boolean isUserInLeft(List<String> orgKeys, Set<RelationItem> leftItems) {

		for (RelationItem item : leftItems) {
			String compareKey = item.getItemType().name() + "#" + item.getAddressId();
			if (orgKeys.contains(compareKey.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private List<String> getUserOrgKeys(User currentUser) {
		List<String> orgKeys = new ArrayList<String>();
		orgKeys.add((RelationItem.ITEMTYPE.USER.name() + "#" + currentUser.getId()).toLowerCase());
		for (UserDepartment ud : currentUser.getUserDepts()) {
			Department d = ud.getDept();
			if (d != null) {
				orgKeys.add((RelationItem.ITEMTYPE.DEPT.name() + "#" + d.getId()).toLowerCase());
			}
		}
		for (UserGroup ug : currentUser.getUserGroups()) {
			Group g = ug.getGroup();
			if (g != null) {
				orgKeys.add((RelationItem.ITEMTYPE.GROUP.name() + "#" + g.getId()).toLowerCase());
			}
		}
		for (UserDepartmentRole udr : currentUser.getUserDeptRoles()) {
			DepartmentRole dr = udr.getDeptRole();
			if (dr != null) {
				orgKeys.add((RelationItem.ITEMTYPE.DEPTROLE.name() + "#" + dr.getId()).toLowerCase());
			}
		}
		return orgKeys;
	}

}
