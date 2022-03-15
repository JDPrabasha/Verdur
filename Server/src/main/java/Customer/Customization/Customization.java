package Customer.Customization;

import Customer.Ingredient.Ingredient;

import java.util.List;

public class Customization {
    private int id;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Customization(int id, int quantity, List<Ingredient> ingredients) {
        this.id = id;
        this.quantity = quantity;
        this.ingredients = ingredients;
    }

    public Customization(int id, int quantity, int price, List<Ingredient> ingredients, int customer) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.ingredients = ingredients;
        this.customer = customer;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public Customization(int id) {
        this.id = id;
    }

    private int customer;
    private int quantity;
    private int price;
    private List<Ingredient> ingredients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
