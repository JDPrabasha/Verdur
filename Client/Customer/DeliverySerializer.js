class DeliverySerializer {
  static deSerialize(data) {
    return {
      customer: data.customer,
      order: data.order,
    };
  }
}
