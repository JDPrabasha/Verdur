package Rider.Order;

import User.ConnectionFactory.DB;
import Customer.Dish.Dish;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final String ADD_ORDER = " insert into orders(custID, distance, longitude, latitude, timestamp) values (?,?,?,?,?) ";
    private static final String ADD_PAYMENT = " insert into payment(orderID, type, amount) values (?,?,?) ";
    private static final String ADD_ORDER_DISHES = " insert into hasdish(orderID, cdishID) values (?,?) ";
    private static final String ADD_COMPLAINT = " insert into complaint(custID, description,orderID,timestamp ) values (?,?,?,?) ";
    private static final String EMPTY_DISHES_FROM_CART = " update customizeddish set inCart=0 where custID = ? ";
    private static final String SELECT_ACTIVE_ORDER = " select p.orderID, status,amount,type from orders o join payment p on o.orderID=p.orderID where custID =?  and status != \"completed\" ";
    private static final String SELECT_RECENT_ORDERS = " select o.orderID,amount,o.timestamp from orders o join payment p on o.orderID = p.orderID where custID =? and completed = 1 ";
    private static final String SELECT_TOTAL_NUTRIENTS = " select  sum(c2.quantity * i.carbsphg * w.weight) as carbs,sum(c2.quantity * i.proteinphg * w.weight) as protein,sum(c2.quantity * i.calphg * w.weight) as calories,sum(c2.quantity * i.fatsphg * w.weight) as fats from orders o join hasdish h2 on o.orderID = h2.orderID join customizeddish c on c.cdishID =h2.cdishID join dish d on c.cdishID =d.dishID join hasingredient h on c.dishID = h.dishID join ingredient i on h.ingID = i.ingID join customization c2 on c2.ingID = h.ingID join ingredientweight w on w.unit = h.unit and w.ingID=i.ingID where o.orderID =?";
    private static final String SELECT_ORDER_DISHES = " select c.quantity, d.name, d.estTime ,c.cdishID, o.orderID,d.image,c.price,r.rating,d.dishID from orders o  join hasdish h on o.orderId = h.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on c.dishID = d.dishID  join rating r on r.dishID =d.dishID  where o.orderID=?";

    public int addOrder(Order order) throws SQLException {
        Integer gid = 0;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DB.initializeDB()) {
            System.out.println("hello");
//            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomer());
            preparedStatement.setFloat(2, order.getDistance());
            preparedStatement.setString(3, order.getLongitude());
            preparedStatement.setString(4, order.getLatitude());
            preparedStatement.setString(5, order.getTimestamp());
            System.out.println(preparedStatement);
//            System.out.println("succsss");

            preparedStatement.executeUpdate();
            System.out.println("succsss");
            ResultSet gkeys = preparedStatement.getGeneratedKeys();
            if ((gkeys != null) && (gkeys.next())) {
                gid = gkeys.getInt(1);
                System.out.println(gid);
            }

            PreparedStatement fourthStatement = connection.prepareStatement(EMPTY_DISHES_FROM_CART);
            System.out.println(fourthStatement);
            fourthStatement.setInt(1, order.getCustomer());
            fourthStatement.executeUpdate();

            PreparedStatement secondStatement = connection.prepareStatement(ADD_ORDER_DISHES);


            List<Dish> dishes = order.getDishes();
            System.out.println(dishes);


            for (Dish d : dishes) {

                secondStatement.setInt(1, gid);
                secondStatement.setInt(2, d.getId());

                System.out.println(secondStatement);
                secondStatement.addBatch();
            }
            secondStatement.executeBatch();

            PreparedStatement thirdStatement = connection.prepareStatement(ADD_PAYMENT);
            thirdStatement.setInt(1, gid);
            thirdStatement.setString(2, order.getPayment());
            thirdStatement.setInt(3, order.getPrice());
            System.out.println(thirdStatement);
            thirdStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return gid;
    }


    public Order selectActiveOrders(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        Order order = null;

        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACTIVE_ORDER);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            if (rs.next()) {
                List<Dish> dishes = new ArrayList<>();
                id = rs.getInt("orderID");
                String status = rs.getString("status");
                PreparedStatement secondStatement = connection.prepareStatement(SELECT_ORDER_DISHES);
                secondStatement.setInt(1, id);
                ResultSet secondSet = secondStatement.executeQuery();
                System.out.println(secondStatement);
                while (secondSet.next()) {

                    int dishID = secondSet.getInt("dishID");
                    int customID = secondSet.getInt("cdishID");
                    int quantity = secondSet.getInt("quantity");
                    String name = secondSet.getString("name");
                    int price = secondSet.getInt("price");
                    String image = secondSet.getString("image");
                    dishes.add(new Dish(dishID, customID, name, image, price, quantity));
                }
                order = new Order(id, status, dishes);


            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return order;
    }

    public List<Order> selectRecentOrders(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Order> orders = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECENT_ORDERS);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                List<Dish> dishes = new ArrayList<>();
                int calories = 0;
                int protein = 0;
                int carbs = 0;
                int fats = 0;
                id = rs.getInt("orderID");
                int amount = rs.getInt("amount");
                String timestamp = rs.getString("timestamp");
                PreparedStatement thirdStatement = connection.prepareStatement(SELECT_TOTAL_NUTRIENTS);
                thirdStatement.setInt(1, id);
                ResultSet thirdSet = thirdStatement.executeQuery();
                if (thirdSet.next()) {
                    carbs = thirdSet.getInt("carbs");
                    protein = thirdSet.getInt("protein");
                    calories = thirdSet.getInt("calories");
                    fats = thirdSet.getInt("fats");
                }
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
                orders.add(new Order(id, carbs, protein, calories, fats, timestamp, amount, dishes));


            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return orders;
    }

    public void addComplaint(Order order) {

        // try-with-resource statement will auto close the connection.
        try (Connection connection = DB.initializeDB()) {
            System.out.println("hello");
//            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_COMPLAINT);
            preparedStatement.setInt(1, order.getCustomer());
            preparedStatement.setInt(3, order.getId());

            preparedStatement.setString(2, order.getComplaint());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);

            preparedStatement.setString(4, s);
            System.out.println(preparedStatement);
//            System.out.println("succsss");

            preparedStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }

    }

    private void printSQLException(SQLException e) {
    }


}
