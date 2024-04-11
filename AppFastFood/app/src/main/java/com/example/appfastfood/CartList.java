package com.example.appfastfood;

public class CartList {

    String nameProduct, cantProducts, price;

    public CartList(String nameProduct, String cantProducts, String price) {
        this.nameProduct = nameProduct;
        this.cantProducts = cantProducts;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getCantProducts() {
        return cantProducts;
    }

    public void setCantProducts(String cantProducts) {
        this.cantProducts = cantProducts;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

