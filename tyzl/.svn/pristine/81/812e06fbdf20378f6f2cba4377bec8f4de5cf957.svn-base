package com.tenwa.business.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


@Entity
@FieldName(name="行业信息")
@Table(name="T_INDUSTRY")
public class Industry {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=100)
	private String id;
	
	private String name;
	private String totalName;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="pid")
	@JsonBackReference
	private Industry parentIndustry;
	
	
	@OneToMany(mappedBy="parentIndustry",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Industry> childrenIndustry = new HashSet<Industry>();

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Industry getParentIndustry() {
		return parentIndustry;
	}

	public Set<Industry> getChildrenIndustry() {
		return childrenIndustry;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentIndustry(Industry parentIndustry) {
		this.parentIndustry = parentIndustry;
	}

	public void setChildrenIndustry(Set<Industry> childrenIndustry) {
		this.childrenIndustry = childrenIndustry;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

}
