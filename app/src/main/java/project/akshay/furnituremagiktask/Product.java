package project.akshay.furnituremagiktask;

public class Product {

    String productName;
    String quantity, productPrice, totalPrice;
    int id;

    public Product(String productName, String quantity, String productPrice, String totalPrice, int id) {
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
