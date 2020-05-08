package Model;

public class MenuModel {
    private String image, name, category;
    private int price;

    public MenuModel(String image, String category) {
        this.image = image;
        this.category = category;
    }

    public MenuModel(String image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price=price;
    }

    //getter setter
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




}
