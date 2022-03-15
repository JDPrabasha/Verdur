package Manager.Manager_Dish;

import Manager.dishes.hasIngredient;

import java.util.List;

public class Dish {
    private int dishID;
    private String dishName;
    private int defaultPrice;
    private String image;
    private String request;
    private String description;
    private String dishcode;
    private List <hasIngredient> ingredients;
    private Dish updatedDish;
    private int estimatedTime;

    public Dish(int dishID, String dishName, String image, String dishcode,String description, List <hasIngredient> ingredients,Dish updatedDish,int defaultPrice,int estimatedTime) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.image = image;
        this.dishcode = dishcode;
        this.description = description;
        this.ingredients = ingredients;
        this.updatedDish = updatedDish;
        this.defaultPrice = defaultPrice;
        this.estimatedTime = estimatedTime;
    }

    public Dish(int dishID, String dishName, String image, String dishcode,String description, List <hasIngredient> ingredients,int defaultPrice,int estimatedTime) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.image = image;
        this.dishcode = dishcode;
        this.description = description;
        this.ingredients = ingredients;
        this.defaultPrice = defaultPrice;
        this.estimatedTime = estimatedTime;
    }

    public Dish(int dishID, String dishName, int defaultPrice, String image) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.defaultPrice = defaultPrice;
        this.image = image;
    }

    public Dish(int dishID, String dishName, int defaultPrice, String image, String request) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.defaultPrice = defaultPrice;
        this.image = image;
        this.request = request;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDishcode() {
        return dishcode;
    }

    public void setDishcode(String dishcode) {
        this.dishcode = dishcode;
    }

    public List<hasIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<hasIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Dish getUpdatedDish() {
        return updatedDish;
    }

    public void setUpdatedDish(Dish updatedDish) {
        this.updatedDish = updatedDish;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
