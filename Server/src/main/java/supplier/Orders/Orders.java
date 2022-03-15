package supplier.Orders;

public class Orders {
    private int reorderID;
    private String item;
    private int quantity;
    private int totalPrice;
    private String requestedDate;
    private String deliveryDate;
    private String timeTillDeadline;
    private String invoiceDate;
    private String status;

    public Orders(int reorderID, String item, int quantity, int totalPrice, String requestedDate, String deliveryDate, String timeTillDeadline, String invoiceDate, String status) {
        this.reorderID = reorderID;
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.requestedDate = requestedDate;
        this.deliveryDate = deliveryDate;
        this.timeTillDeadline = timeTillDeadline;
        this.invoiceDate = invoiceDate;
        this.status = status;
    }

    public int getReorderID() {
        return reorderID;
    }

    public void setReorderID(int reorderID) {
        this.reorderID = reorderID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTimeTillDeadline() {
        return timeTillDeadline;
    }

    public void setTimeTillDeadline(String timeTillDeadline) {
        this.timeTillDeadline = timeTillDeadline;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "reorderID=" + reorderID +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", requestedDate='" + requestedDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", timeTillDeadline='" + timeTillDeadline + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

