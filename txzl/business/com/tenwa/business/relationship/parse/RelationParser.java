package com.tenwa.business.relationship.parse;

import java.util.List;

import com.tenwa.business.entity.RelationMap;
import com.tenwa.business.entity.User;

public interface RelationParser {

	public List<User> parser(User currentUser, RelationMap rmap);

}
