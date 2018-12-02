package com.example.listviewtest.bean;

public class Fruit {
	private String fruitName;
	private int imageId;
	
	public Fruit(String fruitName, int imageId) {
		this.fruitName = fruitName;
		this.imageId = imageId;
	}
	public String getFruitName() {
		return fruitName;
	}
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
}
