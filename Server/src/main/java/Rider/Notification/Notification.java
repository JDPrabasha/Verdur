package Rider.Notification;

public class Notification {

    private int customer;
    private int order;
    private int id;


    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Notification(int id, String description, String timestamp) {
        this.id = id;
        this.description = description;
        this.timestamp = timestamp;
    }


    private String description;
    private String timestamp;
}
