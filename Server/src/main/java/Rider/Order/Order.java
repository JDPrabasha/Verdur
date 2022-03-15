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

    public Order(int id, String payment, Double longitude, Double latitude, int price, List<Dish> dishes) {
        this.id = id;
        this.payment = payment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.dishes = dishes;
    }

    private int quantity;
    private String payment;

    public Order(int id, Double longitude, Double latitude, List<Dish> dishes) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dishes = dishes;
    }

    private Double longitude;
    private Double latitude;
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


    public Order(String payment, Double longitude, Double latitude, Float distance, String timestamp, int price, int customer, List<Dish> dishes) {
        this.payment = payment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.timestamp = timestamp;
        this.price = price;
        this.customer = customer;
        this.dishes = dishes;
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
