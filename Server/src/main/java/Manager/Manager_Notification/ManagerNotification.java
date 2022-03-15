package Manager.Manager_Notification;

public class ManagerNotification {
    private int id;
    private String type;
    private String description;
    private int targetID;

    public ManagerNotification(int id, String type, String description, int targetID) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.targetID = targetID;
    }
}
