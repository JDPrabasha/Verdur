package Rider.Order;

import Customer.Dish.Dish;

import java.util.List;

public class Order {
    public Order(int id, int carbs, int protein, int calories, int fats, String timestamp, int price, List<Dish> dishes) {
        this.id = id;
        this.carbs = carbs;
        this.protein = protein;
        this.calories = calories;
        this.fats = fats;
        this.timestamp = timestamp;
        this.price = price;
        this.dishes = dishes;
    }

    private int id;
    private int carbs;
    private int protein;
    private int calories;
    private int fats;
    private String complaint;

    public Order(int id, String payment, String longitude, String latitude, int price, List<Dish> dishes) {
        this.id = id;
        this.payment = payment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.dishes = dishes;
    }

    private int quantity;
    private String payment;

    public Order(int id, String longitude, String latitude, List<Dish> dishes) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dishes = dishes;
    }

    private String longitude;
    private String latitude;
    private Float distance;
    private String timestamp;

    public Order(int id, String status, List<Dish> dishes) {
        this.id = id;
        this.status = status;
        this.dishes = dishes;
    }


    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public Order(String payment, String longitude, String latitude, Float distance, String timestamp, int price, int customer, List<Dish> dishes) {
        this.payment = payment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.timestamp = timestamp;
        this.price = price;
        this.customer = customer;
        this.dishes = dishes;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    private int price;
    private int customer;
    private String status;
    private List<Dish> dishes;

    public Order(int id, int customer, List<Dish> dishes) {
        this.id = id;
        this.customer = customer;
        this.dishes = dishes;
    }

    public Order(int customer, List<Dish> dishes) {
        this.customer = customer;
        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

}
