package entities.concretes;

import entities.abstracts.Entity;

public class Course implements Entity {
  
	private int id;
	private String name;
	private double unitPrice;
	
	private String[] images;
	

	public Course() {
		super();
	}

	public Course(int id, String name, double unitPrice, String[] images) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.images = images;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	

	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}

}
