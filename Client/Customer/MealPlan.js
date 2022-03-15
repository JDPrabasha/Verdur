class MealPlan {
  constructor(params) {
    this.carbs = params.carbs;
    this.carbGoal = params.carbGoal;
    this.protein = params.protein;
    this.proteinGoal = params.proteinGoal;
    this.fats = params.fats;
    this.fatGoal = params.fatGoal;
    this.calories = params.calories;
    this.calorieGoal = params.calorieGoal;
  }

  setMeals() {
    $("#calories").html(this.calories);
    $("#calorieGoal").html(this.calorieGoal);
    $("#fats").html(this.fats);
    $("#fatGoal").html(this.fatGoal);
    $("#protein").html(this.protein);
    $("#proteinGoal").html(this.proteinGoal);
    $("#carbs").html(this.carbs);
    $("#carbGoal").html(this.carbGoal);
  }

  addMeal() {
    $("#active-order").html(this.id);
    $("#active-status").html(
      this.status.charAt(0).toUpperCase() + this.status.slice(1)
    );
  }
  updateGoals() {
    $("#active-order").html(this.id);
    $("#active-status").html(
      this.status.charAt(0).toUpperCase() + this.status.slice(1)
    );
  }
}
