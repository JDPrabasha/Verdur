package Customer.MealPlan;

public class MealPlan {
    private float fat;
    private float protein;
    private float calories;
    private float carbs;

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public int getFatgoal() {
        return fatgoal;
    }

    public void setFatgoal(int fatgoal) {
        this.fatgoal = fatgoal;
    }

    public int getProteingoal() {
        return proteingoal;
    }

    public void setProteingoal(int proteingoal) {
        this.proteingoal = proteingoal;
    }

    public int getCaloriesgoal() {
        return caloriesgoal;
    }

    public void setCaloriesgoal(int caloriesgoal) {
        this.caloriesgoal = caloriesgoal;
    }

    public int getCarbgoal() {
        return carbgoal;
    }

    public void setCarbgoal(int carbgoal) {
        this.carbgoal = carbgoal;
    }

    public MealPlan(float fat, float protein, float calories, float carbs, int fatgoal, int proteingoal, int caloriesgoal, int carbgoal) {
        this.fat = fat;
        this.protein = protein;
        this.calories = calories;
        this.carbs = carbs;
        this.fatgoal = fatgoal;
        this.proteingoal = proteingoal;
        this.caloriesgoal = caloriesgoal;
        this.carbgoal = carbgoal;
    }

    private int fatgoal;
    private int proteingoal;
    private int caloriesgoal;
    private int carbgoal;


}
