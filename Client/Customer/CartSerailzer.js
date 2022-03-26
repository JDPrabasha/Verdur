class CartSerializer {
  static deSerialize(data) {
    return {
      name: data.name,
      image: data.image,
      id: data.id,
      cid: data.custID,
      price: data.price,
      quantity: data.quantity,
    };
  }
}
