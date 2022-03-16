package supplier.RestockRequest;

public class RestockRequest {
    private int restockID;
    private int itemID;
    private String itemName;
    private int quantity;
    private String unit;
    private int price;
    private String responseDeadline;
    private String deliveryRequestDate;
    private String timeTillExpiry;


    public RestockRequest(int restockID, int itemID, String itemName, int quantity, String unit, int price, String responseDeadline, String deliveryRequestDate, String timeTillExpiry) {
        this.restockID = restockID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.responseDeadline = responseDeadline;
        this.deliveryRequestDate = deliveryRequestDate;
        this.timeTillExpiry = timeTillExpiry;
    }

    public int getRestockID() {
        return restockID;
    }

    public void setRestockID(int restockID) {
        this.restockID = restockID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getResponseDeadline() {
        return responseDeadline;
    }

    public void setResponseDeadline(String responseDeadline) {
        this.responseDeadline = responseDeadline;
    }

    public String getDeliveryRequestDate() {
        return deliveryRequestDate;
    }

    public void setDeliveryRequestDate(String deliveryRequestDate) {
        this.deliveryRequestDate = deliveryRequestDate;
    }

    public String getTimeTillExpiry() {
        return timeTillExpiry;
    }

    public void setTimeTillExpiry(String timeTillExpiry) {
        this.timeTillExpiry = timeTillExpiry;
    }

    @Override
    public String toString() {
        return "RestockRequest{" +
                "restockID=" + restockID +
                ", itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", responseDeadline='" + responseDeadline + '\'' +
                ", deliveryRequestDate='" + deliveryRequestDate + '\'' +
                ", timeTillExpiry='" + timeTillExpiry + '\'' +
                '}';
    }
}


