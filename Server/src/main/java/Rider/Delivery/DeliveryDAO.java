package Rider.Delivery;

import Core.ConnectionFactory.DB;
import Customer.Dish.Dish;
import Rider.Order.Order;
import Core.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO {
    private static final String SELECT_ORDERS = "select o.orderID,o.custID,o.latitude,o.longitude,p.amount,p.type from delivery d join orders o on d.deliveryID = o.deliveryID join payment p on o.orderID = p.orderID where o.completed=0 and d.riderID=?";
    private static final String SELECT_CUSTOMER = "SELECT firstName, lastName, c.custID,u.contact from customer c join user u on c.userID=u.userID where c.custID=?";
    private static final String SELECT_ORDER_DISHES = " select c.quantity, d.name, c.cdishID, o.orderID,d.image,c.price from orders o  join hasdish h on o.orderId = h.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on c.dishID = d.dishID where o.orderID=?";
    private static final String SELECT_DELIVERY_FEES = " select * from deliveryfee";
    private static final String CONFIRM_CASH_ORDER = " update orders set status=? where orderid=?";
    private static final String CONFRIM_CARD_ORDER = " update orders set status=?,completed=1 where orderid=?";

    public List<Delivery> getDeliveryDetails(int id) {
        List<Delivery> deliveries = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        User user1 = null;
        System.out.println("fn exec");
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                List<Dish> dishes = new ArrayList<>();
                id = rs.getInt("orderID");
                Double longitude = rs.getDouble("longitude");
                Double latutude = rs.getDouble("latitude");
                int amount = rs.getInt("amount");
                String type = rs.getString("type");
                PreparedStatement secondStatement = connection.prepareStatement(SELECT_ORDER_DISHES);
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

                PreparedStatement thirdStatement = connection.prepareStatement(SELECT_CUSTOMER);

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
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return deliveries;
    }

    public List<Delivery> getDeliveryFees() {
        List<Delivery> deliveries = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_FEES);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int distance = rs.getInt("distance");
                int fee = rs.getInt("feePerDistance");


                deliveries.add(new Delivery(distance, fee));


            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return deliveries;
    }

    public void confirmCashOrder(int id) {
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object

             PreparedStatement preparedStatement = connection.prepareStatement(CONFIRM_CASH_ORDER);) {
            preparedStatement.setString(1, "delivered");

            preparedStatement.setInt(2, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void confirmCardOrder(int id) {
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object

             PreparedStatement preparedStatement = connection.prepareStatement(CONFRIM_CARD_ORDER);) {
            preparedStatement.setString(1, "delivered");

            preparedStatement.setInt(2, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
