package Rider.Delivery;

import User.ConnectionFactory.DB;


import Customer.Dish.Dish;
import Rider.Order.Order;
import User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO {
    private Connection conn;

    private static final String SELECT_ORDERS = "select o.orderID,o.custID,o.latitude,o.longitude,p.amount,p.type from delivery d join orders o on d.deliveryID = o.deliveryID join payment p on o.orderID = p.orderID where o.status=\"delivering\" and d.riderID=?";
    private static final String SELECT_CUSTOMER = "SELECT firstName, lastName, c.custID,u.contact from customer c join user u on c.userID=u.userID join orders o on o.custID = c.custID where o.orderID=?";
    private static final String SELECT_ORDER_DISHES = " select c.quantity, d.name, c.cdishID, o.orderID,d.image,c.price from orders o  join hasdish h on o.orderId = h.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on c.dishID = d.dishID where o.orderID=?";
    private static final String SELECT_DELIVERY_FEES = " select * from deliveryfee";
    private static final String CONFIRM_CASH_ORDER = " update orders set status=? where orderid=?";
    private static final String CONFRIM_CARD_ORDER = " update orders set status=?,completed=1 where orderid=?";


    public DeliveryDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public List<Delivery> getDeliveryDetails(int id) {
        List<Delivery> deliveries = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        User user1 = null;
        System.out.println("fn exec");
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_ORDERS);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                List<Dish> dishes = new ArrayList<>();
                id = rs.getInt("orderID");
                String longitude = rs.getString("longitude");
                String latutude = rs.getString("latitude");
                int amount = rs.getInt("amount");
                String type = rs.getString("type");
                PreparedStatement secondStatement = this.conn.prepareStatement(SELECT_ORDER_DISHES);
                secondStatement.setInt(1, id);
                ResultSet secondSet = secondStatement.executeQuery();
                System.out.println(secondStatement);
                while (secondSet.next()) {

                    int dishID = secondSet.getInt("cdishID");
                    int quantity = secondSet.getInt("quantity");
                    String name = secondSet.getString("name");
                    int price = secondSet.getInt("price");
                    String image = secondSet.getString("image");
                    dishes.add(new Dish(dishID, name, image, price, quantity));
                }

                Order order = new Order(id, type, longitude, latutude, amount, dishes);

                PreparedStatement thirdStatement = this.conn.prepareStatement(SELECT_CUSTOMER);

                thirdStatement.setInt(1, id);
                ResultSet thirdSet = thirdStatement.executeQuery();
                System.out.println(thirdStatement);
                while (thirdSet.next()) {
                    String name = thirdSet.getString("firstName") + " " + thirdSet.getString("lastName");
                    String contact = thirdSet.getString("contact");


                    user1 = new User.UserBuilder().setName(name).setContact(contact).build();

                }

                deliveries.add(new Delivery(user1, order));


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deliveries;
    }

    public List<Delivery> getDeliveryFees() {
        List<Delivery> deliveries = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_DELIVERY_FEES);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int distance = rs.getInt("distance");
                int fee = rs.getInt("feePerDistance");


                deliveries.add(new Delivery(distance, fee));


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deliveries;
    }

    public void confirmCashOrder(int id) {
        try {
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = this.conn.prepareStatement(CONFIRM_CASH_ORDER);
            preparedStatement.setString(1, "delivered");

            preparedStatement.setInt(2, id);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void confirmCardOrder(int id) {
        try {
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = this.conn.prepareStatement(CONFRIM_CARD_ORDER);
            preparedStatement.setString(1, "delivered");

            preparedStatement.setInt(2, id);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}