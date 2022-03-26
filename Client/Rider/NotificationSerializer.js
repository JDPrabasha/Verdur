class NotificationSerializer {
  static deSerialize(data) {
    return {
      id: data.id,
      count: data.description,
      timestamp: data.timestamp,
    };
  }
}
