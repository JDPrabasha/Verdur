class MenuSerializer {
  static deSerialize(data) {
    return {
      name: data.name,
      image: data.image,
      id: data.id,
      price: data.price,
      getd: data.pojo,
    };
  }
}
