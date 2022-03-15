class IngredientSerializer {
  static deSerialize(data) {
    return {
      name: data.name,
      image: data.image,
      id: data.id,
      expandable: data.expandable,
      unit: data.unit,
      priceperunit: data.ppq,
      quantity: data.quantity,
      weightperunit: data.weightperunit,
      fatsphg: data.fatsphg,
      type: data.type,
      carbsphg: data.carbsphg,
      proteinphg: data.proteinphg,
      maximum: data.maximum,
      minimum: data.minimum,
      caloriesphg: data.caloriesphg,
      benefits: data.benefits,
      suppliers: data.suppliers,
      description: data.description,
    };
  }
}
