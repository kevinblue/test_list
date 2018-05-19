package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_RELATIONITEM")
public class RelationItem {

	public static enum DIRECTION {
		LEFT, RIGHT
	};

	public static enum ITEMTYPE {
		USER, GROUP, DEPT, DEPTROLE
	};

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(name="DIRECTION_")
	private DIRECTION direction;

	@Enumerated(EnumType.STRING)
	private ITEMTYPE itemType;

	private String addressId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public ITEMTYPE getItemType() {
		return itemType;
	}

	public void setItemType(ITEMTYPE itemType) {
		this.itemType = itemType;
	}


	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}

}
