package Rider.Order;

import User.ConnectionFactory.DB;
import Customer.Dish.Dish;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDAO {

    private static final String ADD_ORDER = " insert into orders(custID, distance, longitude, latitude, timestamp) values (?,?,?,?,?) ";
    private static final String ADD_PAYMENT = " insert into payment(orderID, type, amount) values (?,?,?) ";
    private static final String ADD_ORDER_DISHES = " insert into hasdish(orderID, cdishID) values (?,?) ";
    private static final String ADD_COMPLAINT = " insert into complaint(custID, description,orderID,timestamp ) values (?,?,?,?) ";
    private static final String EMPTY_DISHES_FROM_CART = " update customizeddish set inCart=0 where custID = ? ";
    private static final String SELECT_ACTIVE_ORDER = " select p.orderID, status,amount,type from orders o join payment p on o.orderID=p.orderID where custID =?  and status != \"completed\" ";
    private static final String SELECT_RECENT_ORDERS = " select o.orderID,amount,o.timestamp from orders o join payment p on o.orderID = p.orderID where custID =? and completed = 1 ";
    private static final String SELECT_TOTAL_NUTRIENTS = " select  sum(c2.quantity * i.carbsphg * w.weight) as carbs,sum(c2.quantity * i.proteinphg * w.weight) as protein,sum(c2.quantity * i.calphg * w.weight) as calories,sum(c2.quantity * i.fatsphg * w.weight) as fats from orders o join hasdish h2 on o.orderID = h2.orderID join customizeddish c on c.cdishID =h2.cdishID join dish d on c.cdishID =d.dishID join hasingredient h on c.dishID = h.dishID join ingredient i on h.ingID = i.ingID join customization c2 on c2.ingID = h.ingID join ingredientweight w on w.unit = h.unit and w.ingID=i.ingID where o.orderID =?";
    private static final String SELECT_ORDER_DISHES = " select c.quantity, d.name, d.estTime ,c.cdishID, o.orderID,d.image,c.price,d.dishID from orders o  join hasdish h on o.orderId = h.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on c.dishID = d.dishID   where o.orderID=?";
    private static final String GET_ACCEPTED_TIME = "select timestamp from orders o where o.status=\"accepted\" and o.custID = ? ";
    private static final String GET_DELIVERED_PAYMENT = "select type,amount from orders o join payment p on o.orderID = p.orderID where o.status=\"delivered\" and o.custID = ?";

    private Connection conn;

    public OrderDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int addOrder(Order order) throws SQLException {
        Integer gid = 0;
        // try-with-resource statement will auto close the this.conn.
        try {
            System.out.println("hello");
//            this.conn.setAutoCommit(false);
            PreparedStatement preparedStatement = this.conn.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
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

            PreparedStatement fourthStatement = this.conn.prepareStatement(EMPTY_DISHES_FROM_CART);
            System.out.println(fourthStatement);
            fourthStatement.setInt(1, order.getCustomer());
            fourthStatement.executeUpdate();

            PreparedStatement secondStatement = this.conn.prepareStatement(ADD_ORDER_DISHES);


            List<Dish> dishes = order.getDishes();
            System.out.println(dishes);


            for (Dish d : dishes) {

                secondStatement.setInt(1, gid);
                secondStatement.setInt(2, d.getId());

                System.out.println(secondStatement);
                secondStatement.addBatch();
            }
            secondStatement.executeBatch();

            System.out.println("done");

            PreparedStatement thirdStatement = this.conn.prepareStatement(ADD_PAYMENT);
            thirdStatement.setInt(1, gid);
            thirdStatement.setString(2, order.getPayment());
            thirdStatement.setInt(3, order.getPrice());
            System.out.println(thirdStatement);
            thirdStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return gid;
    }


    public String getGetAcceptedTime(int id) {
        String remTime = "";

        // Step 1: Establishing a this.conn
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(GET_ACCEPTED_TIME);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            if (rs.next()) {

                remTime = rs.getString("timestamp");


            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return remTime;
    }

    public int getDeliveryPayment(int id) {
        int due = 0;

        // Step 1: Establishing a this.conn
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(GET_DELIVERED_PAYMENT);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            if (rs.next()) {

                String type = rs.getString("type");
                System.out.println(type);
                if (Objects.equals(type, "cash")) {
                    due = rs.getInt("amount");
                }


            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return due;
    }

    public Order selectActiveOrders(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        Order order = null;

        // Step 1: Establishing a this.conn
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_ACTIVE_ORDER);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            if (rs.next()) {
                List<Dish> dishes = new ArrayList<>();
                int currentOrder = rs.getInt("orderID");
                String status = rs.getString("status");
                PreparedStatement secondStatement = this.conn.prepareStatement(SELECT_ORDER_DISHES);
                secondStatement.setInt(1, currentOrder);
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
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return order;
    }

    public List<Order> selectRecentOrders(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Order> orders = new ArrayList<>();

        // Step 1: Establishing a this.conn
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_RECENT_ORDERS);
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
                PreparedStatement thirdStatement = this.conn.prepareStatement(SELECT_TOTAL_NUTRIENTS);
                thirdStatement.setInt(1, id);
                ResultSet thirdSet = thirdStatement.executeQuery();
                if (thirdSet.next()) {
                    carbs = thirdSet.getInt("carbs");
                    protein = thirdSet.getInt("protein");
                    calories = thirdSet.getInt("calories");
                    fats = thirdSet.getInt("fats");
                }
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
                orders.add(new Order(id, carbs, protein, calories, fats, timestamp, amount, dishes));


            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return orders;
    }

    public void addComplaint(Order order) {

        // try-with-resource statement will auto close the this.conn.
        try {
            System.out.println("hello");
//            this.conn.setAutoCommit(false);
            PreparedStatement preparedStatement = this.conn.prepareStatement(ADD_COMPLAINT);
            preparedStatement.setInt(1, order.getCustomer());
            preparedStatement.setInt(3, order.getId());

            preparedStatement.setString(2, order.getComplaint());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);

            preparedStatement.setString(4, s);
            System.out.println(preparedStatement);
//            System.out.println("succsss");

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }

    private void printSQLException(SQLException e) {
    }


}
