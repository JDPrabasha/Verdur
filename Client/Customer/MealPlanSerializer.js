class MealPlanSerializer {
  static deSerialize(data) {
    return {
      calories: data.calories,
      fats: data.fat,
      protein: data.protein,
      carbs: data.carbs,
      calorieGoal: data.caloriesgoal,
      proteinGoal: data.proteingoal,
      carbGoal: data.carbgoal,
      fatGoal: data.fatgoal,
    };
  }
}
