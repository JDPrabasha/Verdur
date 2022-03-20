class NotificationSerializer {
  static deSerialize(data) {
    return {
      id: data.id,
      status: data.description,
      timestamp: data.timestamp,
    };
  }
}
