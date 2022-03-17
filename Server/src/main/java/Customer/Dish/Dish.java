package Customer.Dish;

import Customer.Ingredient.Ingredient;

import java.util.List;

public class Dish {
    private int id;
    private int customID;

    private String name;
    private String image;
    private String description;
    private int time;

    public Dish(int id, int customID, String name, String image, int price, int quantity) {
        this.id = id;
        this.customID = customID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    private int price;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int quantity;
    private List<Ingredient> ingredients;

    public Dish(int id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Dish(int id) {
        this.id = id;
    }

    public Dish(int id, String name, String image, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Dish(int id, String name, String image, String description, int time, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.time = time;
        this.price = price;

    }

    public Dish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dish(String name, String image, int id, int price) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
