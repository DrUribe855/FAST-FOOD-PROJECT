package com.example.appfastfood;

public class CartList {

    String nameProduct, cantProducts, subtotal, total;

    public CartList(String nameProduct, String cantProducts, String subtotal, String total) {
        this.nameProduct = nameProduct;
        this.cantProducts = cantProducts;
        this.subtotal = subtotal;
        this.total = total;
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

