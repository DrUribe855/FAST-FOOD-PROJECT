package com.example.appfastfood;

public class InfoProductsCategory {
    String id_product;
    String product_name;
    String price;
    String image_url;
    String description;

    public InfoProductsCategory(String id_product, String product_name, String price, String image_url, String description) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.price = price;
        this.image_url = image_url;
        this.description = description;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
