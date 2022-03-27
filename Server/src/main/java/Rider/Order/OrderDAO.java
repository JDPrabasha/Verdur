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
    private static final String SELECT_ACTIVE_ORDER = " select p.orderID, status,amount,type from orders o join payment p on o.orderID=p.orderID where custID =?  and status != \"reviewed\" and completed=0 ";
    private static final String SELECT_RECENT_ORDERS = " select o.orderID,amount,o.timestamp from orders o join payment p on o.orderID = p.orderID where custID =? and completed = 1 ";
    private static final String SELECT_TOTAL_NUTRIENTS = " select  sum(c2.quantity * i.carbsphg * w.weight) as carbs,sum(c2.quantity * i.proteinphg * w.weight) as protein,sum(c2.quantity * i.calphg * w.weight) as calories,sum(c2.quantity * i.fatsphg * w.weight) as fats from orders o join hasdish h2 on o.orderID = h2.orderID join customizeddish c on c.cdishID =h2.cdishID join dish d on c.cdishID =d.dishID join hasingredient h on c.dishID = h.dishID join ingredient i on h.ingID = i.ingID join customization c2 on c2.ingID = h.ingID join ingredientweight w on w.unit = h.unit and w.ingID=i.ingID where o.orderID =?";
    private static final String SELECT_ORDER_DISHES = " select c.quantity, d.name, d.estTime ,c.cdishID, o.orderID,d.image,c.price,d.dishID from orders o  join hasdish h on o.orderId = h.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on c.dishID = d.dishID   where o.orderID=?";
    private static final String GET_ACCEPTED_TIME = "select timestamp from orders o where o.status=\"accepted\" and o.custID = ? ";
    private static final String GET_DELIVERED_PAYMENT = "select type,amount from orders o join payment p on o.orderID = p.orderID where o.status=\"delivered\" and o.custID = ?";
    private static final String FINISH_REVIEW = "update orders o set o.status=\"reviewed\" where o.custID = ? ";

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
        try {
            conn.setAutoCommit(false);
            System.out.println("hello");
            PreparedStatement preparedStatement = this.conn.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomer());
            preparedStatement.setFloat(2, order.getDistance());
            preparedStatement.setString(3, order.getLongitude());
            preparedStatement.setString(4, order.getLatitude());
            preparedStatement.setString(5, order.getTimestamp());
            System.out.println(preparedStatement);

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

            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return gid;
    }


    public String getGetAcceptedTime(int id) {
        String remTime = "";

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(GET_ACCEPTED_TIME);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

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

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(GET_DELIVERED_PAYMENT);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

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

        Order order = null;

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_ACTIVE_ORDER);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

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

        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_RECENT_ORDERS);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

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

        try {
            conn.setAutoCommit(false);
            System.out.println("hello");
            PreparedStatement preparedStatement = this.conn.prepareStatement(ADD_COMPLAINT);
            preparedStatement.setInt(1, order.getCustomer());
            preparedStatement.setInt(3, order.getId());

            preparedStatement.setString(2, order.getComplaint());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);

            preparedStatement.setString(4, s);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }

    public void finishReview(String id) {

        try {
            conn.setAutoCommit(false);
            System.out.println("hello");
            PreparedStatement preparedStatement = this.conn.prepareStatement(FINISH_REVIEW);
            preparedStatement.setString(1, id);


            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }

    private void printSQLException(SQLException e) {
    }


}
