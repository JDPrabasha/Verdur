class NotificationSerializer {
  static deSerialize(data) {
    return {
      id: data.id,
      description: data.description,
      timestamp: data.timestamp,
    };
  }
}
