package Model;

public class CartModel {

    //cart item
    private String  name, quantity;
    private int price;

    public CartModel() {
    }

    public CartModel(String name, String quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    // cart item

}
