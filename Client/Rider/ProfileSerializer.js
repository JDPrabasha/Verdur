class ProfileSerializer {
  static deSerialize(data) {
    return {
      id: data.id,
      name: data.name,
      avatar: data.image,
      contact: data.contact,
    };
  }
}
