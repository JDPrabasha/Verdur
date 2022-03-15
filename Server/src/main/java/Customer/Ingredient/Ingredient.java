package Customer.Ingredient;

import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private String unit;
    private String image;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;
    private boolean expandable;
    private int proteinphg;
    private int minimum;
    private int maximum;
    private List<String> benefits;
    private List<String> suppliers;
    private int caloriesphg;
    private int carbsphg;
    private int fatsphg;
    private int ppq;
    private int weightperunit;
    private int quantity;

    public Ingredient(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Ingredient(int id, String name, String unit, String image, int proteinphg, int caloriesphg, int carbsphg, int fatsphg, int ppq, int weightperunit, int quantity, String type, int minimum, int maximum) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.image = image;
        this.proteinphg = proteinphg;
        this.caloriesphg = caloriesphg;
        this.carbsphg = carbsphg;
        this.fatsphg = fatsphg;
        this.ppq = ppq;
        this.weightperunit = weightperunit;
        this.type = type;
        this.quantity = quantity;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public Ingredient(int id, String name, String image, String description, List<String> benefits) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.benefits = benefits;
    }


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

    public Ingredient(int id, String name, String image, String unit, Integer quantity, Boolean expandable) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.unit = unit;
        this.quantity = quantity;
        this.expandable = expandable;
    }


}
