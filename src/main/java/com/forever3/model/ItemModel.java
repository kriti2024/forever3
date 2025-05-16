package com.forever3.model;

	public class ItemModel {
	    private int item_id;
	    private String item_name;
	    private String description;
	    private int category_id;
	    private double item_price;
	    private int stock_quantity;
	    private int supplier_id;
	    private String image_url;

	    public ItemModel() {
	    }

	    public ItemModel(int item_id, String item_name, String description, int category_id,
	                     double item_price, int stock_quantity, int supplier_id, String image_url) {
	        this.item_id = item_id;
	        this.item_name = item_name;
	        this.description = description;
	        this.category_id = category_id;
	        this.item_price = item_price;
	        this.stock_quantity = stock_quantity;
	        this.supplier_id = supplier_id;
	        this.image_url = image_url;
	    }

	    // Getters and Setters
	    public int getItem_id() {
	        return item_id;
	    }

	    public void setItem_id(int item_id) {
	        this.item_id = item_id;
	    }

	    public String getItem_name() {
	        return item_name;
	    }

	    public void setItem_name(String item_name) {
	        this.item_name = item_name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getCategory_id() {
	        return category_id;
	    }

	    public void setCategory_id(int category_id) {
	        this.category_id = category_id;
	    }

	    public double getItem_price() {
	        return item_price;
	    }

	    public void setItem_price(double item_price) {
	        this.item_price = item_price;
	    }

	    public int getStock_quantity() {
	        return stock_quantity;
	    }

	    public void setStock_quantity(int stock_quantity) {
	        this.stock_quantity = stock_quantity;
	    }

	    public int getSupplier_id() {
	        return supplier_id;
	    }

	    public void setSupplier_id(int supplier_id) {
	        this.supplier_id = supplier_id;
	    }

	    public String getImage_url() {
	        return image_url;
	    }

	    public void setImage_url(String image_url) {
	        this.image_url = image_url;
	    }
	}

