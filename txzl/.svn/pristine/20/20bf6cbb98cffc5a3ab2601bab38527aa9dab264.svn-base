package com.tenwa.leasing.entity.proj;
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
@FieldName(name = "供应商类别信息")
@Table(name = "SUPPLIER_INFORMATION_TYPE")
public class SupplierInformationType implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;

	private String name;
	private String totalName;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="pid")
	@JsonBackReference
	private SupplierInformationType parentSupplier;
	
	@OneToMany(mappedBy="parentSupplier",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<SupplierInformationType> childrenSupplier = new HashSet<SupplierInformationType>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

	public SupplierInformationType getParentSupplier() {
		return parentSupplier;
	}

	public void setParentSupplier(SupplierInformationType parentSupplier) {
		this.parentSupplier = parentSupplier;
	}

	public Set<SupplierInformationType> getChildrenSupplier() {
		return childrenSupplier;
	}

	public void setChildrenSupplier(Set<SupplierInformationType> childrenSupplier) {
		this.childrenSupplier = childrenSupplier;
	}

	
	
	

	
	
	
}
