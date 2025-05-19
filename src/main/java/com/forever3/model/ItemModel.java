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

		public int getItemId() {
			return item_id;
		}

		public void setItemId(int item_id) {
			this.item_id = item_id;
		}

		public String getItemName() {
			return item_name;
		}

		public void setItemName(String item_name) {
			this.item_name = item_name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getCategoryId() {
			return category_id;
		}

		public void setCategoryId(int category_id) {
			this.category_id = category_id;
		}

		public double getItemPrice() {
			return item_price;
		}

		public void setItemPrice(double item_price) {
			this.item_price = item_price;
		}

		public int getStockQuantity() {
			return stock_quantity;
		}

		public void setStockQuantity(int stock_quantity) {
			this.stock_quantity = stock_quantity;
		}

		public int getSupplierId() {
			return supplier_id;
		}

		public void setSupplierId(int supplier_id) {
			this.supplier_id = supplier_id;
		}

		public String getImageUrl() {
			return image_url;
		}

		public void setImageUrl(String image_url) {
			this.image_url = image_url;
		}
	    
	}
	   