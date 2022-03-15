package cashier.rider;

import cashier.CashierOrders.CashierOrders;


import java.util.List;

public class rider {
    private Integer orderID;
    private Integer riderID;//rider
    private Integer deliveryID;
    private String status;//rider
    private String photo;//employee
    private String name;//user
    private List<CashierOrders> riderorders;


    public rider(Integer riderID, Integer deliveryID, List<CashierOrders> riderorders) {
        this.riderID = riderID;
        this.deliveryID = deliveryID;
        this.riderorders = riderorders;
    }

    public rider(Integer riderID, String status, String photo, String name, List<CashierOrders> riderorders) {
        this.riderID = riderID;
        this.status = status;
        this.photo = photo;
        this.name = name;
        this.riderorders = riderorders;
    }

    public rider(Integer riderID, List<CashierOrders> riderorders) {
        this.riderID = riderID;
        this.riderorders = riderorders;
    }

    public Integer getRiderID() {
        return riderID;
    }

    public void setRiderID(Integer riderID) {
        this.riderID = riderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }
}


