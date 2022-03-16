class OrderSerializer {
  static deSerialize(data) {
    return {
      id: data.id,
      status: data.status,
      carbs: data.carbs,
      protein: data.protein,
      price: data.price,
      calories: data.calories,
      fats: data.fats,
      dishes: data.dishes,
      timestamp: data.timestamp,
      method: data.payment,
      lang: data.longitude,
      lat: data.latitude,
    };
  }
}
