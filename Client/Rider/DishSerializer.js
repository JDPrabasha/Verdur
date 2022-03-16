class DishSerializer {
  static deSerialize(data) {
    return {
      name: data.name,
      image: data.image,
      id: data.id,
      cid: data.customID,
      price: data.price,
      rating: data.rating,
      quantity: data.quantity,
      time: data.time,
      description: data.description,
      ingredients: data.ingredients,
    };
  }
}
