package cashier.CashierOrders;

import cashier.dishes.dish;

import java.util.List;

public class CashierOrders {
    private Integer orderid;
    private String status;
    private Integer deliveryid;//
    private Integer Quantity;//hasdish
    private Integer amount;//payment
    private Integer totalQuantity;
    private String date;//currentdate
    private String paymentMethod;//payment type
    private String name;//user
    private String contact;//user
    private String address;//customer
    //    private Integer custID;
    private String longitude;
    private String latitude;



    private List<dish> dishitem;

    public CashierOrders(Integer orderid, String status, Integer amount, Integer totalQuantity, String date, String paymentMethod) {
        this.orderid = orderid;
        this.status = status;
        this.amount = amount;
        this.totalQuantity = totalQuantity;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }


    public CashierOrders(int orderid, int quantity) {
        this.orderid = orderid;
        this.totalQuantity = quantity;

    }

    public CashierOrders(Integer orderid, String status, Integer amount, List<dish> dishitem) {
        this.orderid = orderid;
        this.status = status;
        this.amount = amount;
        this.dishitem = dishitem;
    }

    public CashierOrders(int orderid, String status, String name, String contact, List<dish> dishitem, int amount , String longitude, String latitude) {
        this.orderid = orderid;
        this.status = status;
        this.name = name;
        this.contact = contact;
//        this.address = address;
        this.dishitem = dishitem;
        this.amount = amount;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public CashierOrders(Integer orderid, Integer amount, Integer totalQuantity, String name, String contact, String longitude, String latitude) {
        this.orderid = orderid;
        this.amount = amount;
        this.totalQuantity = totalQuantity;
        this.name = name;
        this.contact = contact;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public CashierOrders(int orderid, int deliveryid, String longitude, String latitude, int Quantity) {
        this.orderid = orderid;
        this.deliveryid = deliveryid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.Quantity = Quantity;
    }



    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Integer deliveryid) {
        this.deliveryid = deliveryid;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<dish> getDishitem() {
        return dishitem;
    }

    public void setDishitem(List<dish> dishitem) {
        this.dishitem = dishitem;
    }
}
