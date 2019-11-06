package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tax")
public class Tax {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tax_id")
	private int taxId;
	private String tax_type;
	private float tax_percentage;

	public String getTax_type() {
		return tax_type;
	}
	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}
	public float getTax_percentage() {
		return tax_percentage;
	}
	public void setTax_percentage(float tax_percentage) {
		this.tax_percentage = tax_percentage;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	@Override
	public String toString() {
		return "Tax [taxId=" + taxId + ", tax_type=" + tax_type + ", tax_percentage=" + tax_percentage + "]";
	}
	
	
	

}
