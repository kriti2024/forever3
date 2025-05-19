package com.forever3.model;

public class CartItemModel {
    private ItemModel item;
    private int quantity;

    public CartItemModel(ItemModel item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
