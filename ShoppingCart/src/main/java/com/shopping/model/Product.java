package com.shopping.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	private String name;
	private String description;
	private float price;
	private int discounted_price;
	private String image;
	private String image_2;
	private String thumbnail;
	private int display;
	@OneToMany
	@JoinTable(name="review",
			joinColumns = {@JoinColumn(name="product_id",referencedColumnName="product_id")},
			inverseJoinColumns = {@JoinColumn(name="review_id",referencedColumnName="review_id")})
	private List<Review> reviews;
	@ManyToOne
	@JoinTable(name="product_category",
			joinColumns = {@JoinColumn(name="product_id",referencedColumnName="product_id")},
			inverseJoinColumns = {@JoinColumn(name="category_id",referencedColumnName="category_id")})
	private Category category;
	
	
	@ManyToMany
	@JoinTable(name="product_attribute",
			joinColumns = {@JoinColumn(name="product_id",referencedColumnName="product_id")},
			inverseJoinColumns = {@JoinColumn(name="attribute_value_id",referencedColumnName="attribute_value_id")})
	private List<AttributeValue> attributeValues;
	
	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}
	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getDiscounted_price() {
		return discounted_price;
	}
	public void setDiscounted_price(int discounted_price) {
		this.discounted_price = discounted_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage_2() {
		return image_2;
	}
	public void setImage_2(String image_2) {
		this.image_2 = image_2;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}

	
	
	
	
}
