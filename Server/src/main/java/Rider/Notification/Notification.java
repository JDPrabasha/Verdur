package Rider.Notification;

public class Notification {
    private int id;

    public Notification(int id, String description, String timestamp) {
        this.id = id;
        this.description = description;
        this.timestamp = timestamp;
    }

    private String description;
    private String timestamp;
}
