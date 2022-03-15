class CartSerializer {
  static deSerialize(data) {
    return {
      name: data.name,
      image: data.image,
      id: data.id,
      price: data.price,
      quantity: data.quantity,
    };
  }
}
