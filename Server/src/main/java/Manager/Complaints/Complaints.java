package Manager.Complaints;

import java.util.ArrayList;

public class Complaints {
    private Integer complaintID;
    private Integer orderID;
    private String custName;
    private String type;
    private String riderName;
    private String description;
    private String date;
    private String deliveryLocation;
    private String chef;
    private Integer itemCount;
    private Integer payment;
    private ArrayList<String> orderItems;

    public Complaints(Integer complaintID, Integer orderID, String custName, String riderName, String description, String deliveryLocation, String chef, Integer itemCount, Integer payment, ArrayList<String> orderItems) {
        this.complaintID = complaintID;
        this.orderID = orderID;
        this.custName = custName;
        this.riderName = riderName;
        this.description = description;
        this.deliveryLocation = deliveryLocation;
        this.chef = chef;
        this.itemCount = itemCount;
        this.payment = payment;
        this.orderItems = orderItems;
    }

    public Complaints(Integer complaintID, String custName, String type, String riderName, String description, String date) {
        this.complaintID = complaintID;
        this.custName = custName;
        this.type = type;
        this.riderName = riderName;
        this.description = description;
        this.date = date;
    }

    public Integer getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(Integer complaintID) {
        this.complaintID = complaintID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
