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
    private Double longitude;
    private Double latitude;



    private List<dish> dishitem;

    public CashierOrders(Integer orderid, String status, Integer amount, Integer totalQuantity, String date, String paymentMethod) {
        this.orderid = orderid;
        this.status = status;
        this.amount = amount;
        this.totalQuantity = totalQuantity;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public CashierOrders(Integer orderid, String status, Integer deliveryid, Integer totalQuantity) {
        this.orderid = orderid;
        this.status = status;
        this.deliveryid = deliveryid;
        this.totalQuantity = totalQuantity;
    }

    public CashierOrders(Integer orderid, String status, Integer amount, Integer totalQuantity, String paymentMethod) {
        this.orderid = orderid;
        this.status = status;
        this.amount = amount;
        this.totalQuantity = totalQuantity;
        this.paymentMethod = paymentMethod;
    }


    public CashierOrders(Integer orderid, List<dish> dishitem, String status) {
        this.orderid = orderid;
        this.dishitem = dishitem;
        this.status = status;
    }

    public CashierOrders(Integer orderid) {
        this.orderid = orderid;

    }


    public CashierOrders(Integer orderid, String status, String name, String contact, String address, List<dish> dishitem) {
        this.orderid = orderid;
        this.status = status;
        this.name = name;
        this.contact = contact;
        this.address = address;
//        this.custID = custID;
        this.dishitem = dishitem;
    }


    public CashierOrders(Integer orderid, Integer deliveryid, String address, Double longitude, Double latitude, Integer Quantity) {
        this.orderid = orderid;

        this.deliveryid = deliveryid;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.Quantity = Quantity;
    }

    public CashierOrders(Integer orderid, Integer totalQuantity, String name, String contact, String address) {
        this.orderid = orderid;
        this.totalQuantity = totalQuantity;
        this.name = name;
        this.contact = contact;
        this.address = address;


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


    //    public CashierOrders(Integer orderid, Integer quantity, String name, String contact, String address) {
//        this.orderid = orderid;
//        this.Quantity = quantity;
//        this.name = name;
//        this.contact = contact;
//        this.address = address;
//    }

//    public CashierOrders(Integer orderid, String status, Integer quantity, Integer amount, String date, String paymentMethod) {
//        this.orderid = orderid;
//        this.status = status;
//        Quantity = quantity;
//        this.amount = amount;
//        this.date = date;
//        this.paymentMethod = paymentMethod;
//    }

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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public List<dish> getDishitem() {
        return dishitem;
    }

    public void setDishitem(List<dish> dishitem) {
        this.dishitem = dishitem;
    }
}
//package CashierOrders;
//
//import dishes.dish;
//
//import java.util.List;
//
//public class CashierOrders {
//    private Integer orderid;
//    private String status;
//    private Integer deliveryid;//
//    private Integer Quantity;//hasdish
//    private Integer amount;//payment
//    private Integer totalQuantity;
//    private String date;//currentdate
//    private String paymentMethod;//payment type
//    private String name;//user
//    private String contact;//user
//    private String address;//customer
////    private Integer custID;
//    private Double longitude;
//    private Double latitude;
//
//
//    private List<dish> dishitem;
//
//    public CashierOrders(Integer orderid, String status, Integer deliveryid, Integer totalQuantity) {
//        this.orderid = orderid;
//        this.status = status;
//        this.deliveryid = deliveryid;
//        this.totalQuantity = totalQuantity;
//    }
//
//    public CashierOrders(Integer orderid, String status, Integer amount, Integer totalQuantity, String paymentMethod) {
//        this.orderid = orderid;
//        this.status = status;
//        this.amount = amount;
//        this.totalQuantity = totalQuantity;
//        this.paymentMethod = paymentMethod;
//    }
//
//
//    public CashierOrders(Integer orderid, List<dish> dishitem, String status) {
//        this.orderid = orderid;
//        this.dishitem = dishitem;
//        this.status = status;
//    }
//
//    public CashierOrders(Integer orderid) {
//        this.orderid = orderid;
//
//    }
//
//
//    public CashierOrders(Integer orderid, String status, String name, String contact, String address, List<dish> dishitem) {
//        this.orderid = orderid;
//        this.status = status;
//        this.name = name;
//        this.contact = contact;
//        this.address = address;
////        this.custID = custID;
//        this.dishitem = dishitem;
//    }
//
//
//    public CashierOrders(Integer orderid, Integer deliveryid, String address, Double longitude, Double latitude, Integer Quantity) {
//        this.orderid = orderid;
//
//        this.deliveryid = deliveryid;
//        this.address = address;
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.Quantity = Quantity;
//    }
//
//    public CashierOrders(Integer orderid, Integer totalQuantity, String name, String contact, String address) {
//        this.orderid = orderid;
//        this.totalQuantity = totalQuantity;
//        this.name = name;
//        this.contact = contact;
//        this.address = address;
//
//
//    }
//
//    public CashierOrders(int orderid, int quantity) {
//        this.orderid = orderid;
//        this.totalQuantity = quantity;
//
//    }
//
//    public CashierOrders(int orderid, List<dish> dishitem, String status, int price) {
//    }
//
//
//    //    public CashierOrders(Integer orderid, Integer quantity, String name, String contact, String address) {
////        this.orderid = orderid;
////        this.Quantity = quantity;
////        this.name = name;
////        this.contact = contact;
////        this.address = address;
////    }
//
////    public CashierOrders(Integer orderid, String status, Integer quantity, Integer amount, String date, String paymentMethod) {
////        this.orderid = orderid;
////        this.status = status;
////        Quantity = quantity;
////        this.amount = amount;
////        this.date = date;
////        this.paymentMethod = paymentMethod;
////    }
//
//    public Integer getOrderid() {
//        return orderid;
//    }
//
//    public void setOrderid(Integer orderid) {
//        this.orderid = orderid;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Integer getDeliveryid() {
//        return deliveryid;
//    }
//
//    public void setDeliveryid(Integer deliveryid) {
//        this.deliveryid = deliveryid;
//    }
//
//    public Integer getQuantity() {
//        return Quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        Quantity = quantity;
//    }
//
//    public Integer getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Integer amount) {
//        this.amount = amount;
//    }
//
//    public Integer getTotalQuantity() {
//        return totalQuantity;
//    }
//
//    public void setTotalQuantity(Integer totalQuantity) {
//        this.totalQuantity = totalQuantity;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getContact() {
//        return contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public List<dish> getDishitem() {
//        return dishitem;
//    }
//
//    public void setDishitem(List<dish> dishitem) {
//        this.dishitem = dishitem;
//    }
//}