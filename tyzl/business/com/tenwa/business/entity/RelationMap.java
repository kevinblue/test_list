package com.tenwa.business.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.tenwa.business.relationship.parse.RelationParserEnum;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "T_RELATIONMAP")
@FieldName(name = "关系映射")
public class RelationMap {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@Column(nullable = false, unique = true)
	private String mapName;

	private String description;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RelationParserEnum parserClass;

	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY)
	@Where(clause = "DIRECTION_='LEFT'")
	@JoinColumn(name = "RELATION_ID")
	private Set<RelationItem> leftItems;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@Where(clause = "DIRECTION_='RIGHT'")
	@JoinColumn(name = "RELATION_ID")
	private Set<RelationItem> rightItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RelationParserEnum getParserClass() {
		return parserClass;
	}

	public void setParserClass(RelationParserEnum parserClass) {
		this.parserClass = parserClass;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Set<RelationItem> getLeftItems() {
		return leftItems;
	}

	public void setLeftItems(Set<RelationItem> leftItems) {
		this.leftItems = leftItems;
	}

	public Set<RelationItem> getRightItems() {
		return rightItems;
	}

	public void setRightItems(Set<RelationItem> rightItems) {
		this.rightItems = rightItems;
	}

}
