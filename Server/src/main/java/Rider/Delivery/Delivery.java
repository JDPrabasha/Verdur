package Rider.Delivery;

import Rider.Order.Order;
import User.User;

public class Delivery {
    private Order order;
    private User customer;
    private int distance;

    public Delivery(int distance, int fee) {
        this.distance = distance;
        this.fee = fee;
    }

    private int fee;


    public Delivery(User customer, Order order) {
        this.customer = customer;
        this.order = order;
    }


}
