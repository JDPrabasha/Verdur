package cashier.CashierOrders;


import User.ConnectionFactory.DB;
import cashier.dishes.dish;
import cashier.rider.rider;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CashierOrdersDAO {
    private Connection conn;


    public CashierOrdersDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<CashierOrders> read() throws SQLException {
        List<CashierOrders> orderitem = new ArrayList<>();

        String query = "SELECT * FROM orders JOIN payment on orders.orderID=payment.orderID ";
        PreparedStatement st1 = this.conn.prepareStatement(query);
        ResultSet rs = st1.executeQuery();

        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");

            String status = rs.getString("status");
            int price = rs.getInt("amount");


            String query2 = "SELECT * FROM orders JOIN hasdish on orders.orderID=hasdish.orderID JOIN customizeddish ON customizeddish.cdishID=hasdish.cdishID JOIN dish on customizeddish.dishID=dish.dishID JOIN payment on orders.orderID=payment.orderID WHERE orders.orderID=?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, orderid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                String dishid = rs2.getString("cdishID");
                String dishname = rs2.getString("name");
                int dishquantity = rs2.getInt("quantity");
                String dishimage = rs2.getString("image");
//                int price = rs2.getInt("amount");
                int price1 = rs2.getInt("price");
//                int esttime = 0;

                dishitem.add(new dish(dishid, dishname, dishimage, dishquantity, price1));

            }

            orderitem.add(new CashierOrders(orderid, status, price, dishitem));


        }
        return orderitem;

    }


    public List<CashierOrders> readriderorders(String id) throws SQLException {
        List<CashierOrders> orderitem = new ArrayList<>();

        String query = "SELECT * FROM orders o JOIN customer c ON o.custID=c.custID JOIN user u ON u.userID=c.userID JOIN payment p ON o.orderID= p.orderID WHERE o.orderID=?; ";
        PreparedStatement st1 = this.conn.prepareStatement(query);
        st1.setString(1, id);
        ResultSet rs = st1.executeQuery();

        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");

            String status = rs.getString("status");
            String name = rs.getString("firstName") + " " + rs.getString("lastName");
            String contact = rs.getString("contact");
//            String address = rs.getString("address");
            int custID = rs.getInt("custID");
            int amount = rs.getInt("amount");

            String latitude = rs.getString("latitude");
            String longitude = rs.getString("longitude");



            String query2 = "SELECT * FROM orders JOIN hasdish on orders.orderID=hasdish.orderID JOIN customizeddish ON customizeddish.cdishID=hasdish.cdishID JOIN dish on customizeddish.dishID=dish.dishID WHERE orders.orderID=?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, orderid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                String dishid = rs2.getString("cdishID");
                String dishname = rs2.getString("dish.name");
                int dishquantity = rs2.getInt("quantity");
                String dishimage = rs2.getString("image");
                int price = rs2.getInt("price");
                int esttime = 0;

                dishitem.add(new dish(dishid, dishname, esttime, dishimage, dishquantity, price));

            }

            orderitem.add(new CashierOrders(orderid, status, name, contact, dishitem,amount,longitude,latitude));


        }
        return orderitem;

    }

    public List<CashierOrders> readcookedorders() throws SQLException {
        List<CashierOrders> orderitem = new ArrayList<>();

        String query = "SELECT o.orderID,SUM(quantity) AS quantity, u.firstName,u.lastName,u.contact,c.address,p.amount, o.longitude, o.latitude FROM orders o JOIN hasdish h on o.orderID=h.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID JOIN dish d on cd.dishID=d.dishID JOIN customer c ON o.custID=c.custID JOIN user u ON u.userID=c.userID join payment p on p.orderID = o.orderID WHERE o.status=\"cooked\" GROUP BY o.orderID";

//        String query = "SELECT o.orderID,SUM(quantity) AS quantity, u.firstName,u.lastName,u.contact,c.address FROM orders o JOIN hasdish h on o.orderID=h.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID JOIN dish d on cd.dishID=d.dishID JOIN customer c ON o.custID=c.custID JOIN user u ON u.userID=c.userID WHERE o.status=\"cooked\" GROUP BY o.orderID";

//        String query = "SELECT * FROM orders o JOIN hasdish h on o.orderID=h.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID JOIN dish d on cd.dishID=d.dishID JOIN customer c ON o.custID=c.custID JOIN user u ON u.userID=c.userID WHERE o.status=\"cooked\";";
        PreparedStatement st1 = this.conn.prepareStatement(query);
        ResultSet rs = st1.executeQuery();

        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");
            int totalQuantity = rs.getInt("quantity");
            String name = rs.getString("firstName") + " " + rs.getString("lastName");
            String contact = rs.getString("contact");
//            String address = rs.getString("address");
            int amount = rs.getInt("amount");
            String longitude = rs.getString("longitude");
            String latitude = rs.getString("latitude");
            orderitem.add(new CashierOrders(orderid,amount, totalQuantity, name, contact,longitude,latitude ));


        }
        return orderitem;

    }

    public List<CashierOrders> readongoingorders() throws SQLException {
        List<CashierOrders> orderitem = new ArrayList<>();
        String query4 = "SELECT o.orderID,SUM(quantity) AS quantity, p.amount,o.status,p.type,o.timestamp FROM orders o JOIN hasdish h on o.orderID=h.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID JOIN dish d on cd.dishID=d.dishID JOIN payment p ON p.orderID=o.orderID WHERE status=\"pending\" or status= \"cooked\" or status=\"delivering\" and o.orderID GROUP BY o.orderID";
//        String query4 ="SELECT o.orderID,SUM(quantity) AS quantity, p.amount,o.status,p.type,o.timestamp FROM orders o JOIN hasdish h on o.orderID=h.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID JOIN dish d on cd.dishID=d.dishID JOIN payment p ON p.orderID=o.orderID WHERE o.orderID GROUP BY o.orderID";

//        String query = "SELECT * FROM orders o JOIN hasdish h on o.orderID=h.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID JOIN dish d on cd.dishID=d.dishID JOIN customer c ON o.custID=c.custID JOIN user u ON u.userID=c.userID WHERE o.status=\"cooked\";";
        PreparedStatement st4 = this.conn.prepareStatement(query4);
        ResultSet rs = st4.executeQuery();

        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");
            int totalQuantity = rs.getInt("quantity");
            int amount = rs.getInt("amount");
            String status = rs.getString("status");
            String paymentMethod = rs.getString("type");
            String date = rs.getString(("timestamp"));
            orderitem.add(new CashierOrders(orderid, status, amount, totalQuantity, date, paymentMethod));


        }
        return orderitem;

    }


    public int accept(CashierOrders d) throws SQLException {
        System.out.println("step2");
        String quary = "UPDATE orders SET status=\"accepted\" WHERE orderID = ?";
        int orderid = d.getOrderid();
        System.out.println("step3");
        PreparedStatement st = this.conn.prepareStatement(quary);
        System.out.println("step4");
        st.setInt(1, orderid);
        return st.executeUpdate();
    }


    public Integer assignrider(rider d) {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        int orderid = d.getOrderID();
        int riderid = d.getRiderID();
        String deliveryIDQuery = "SELECT o.deliveryID FROM user u JOIN employee e ON u.userID=e.userID JOIN rider r ON e.empID=r.riderID join delivery d on d.riderID = r.riderID join orders o on o.deliveryID = d.deliveryID JOIN hasdish h ON h.orderID=o.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID where o.status =\"rider_assigned\" and r.riderID = ? GROUP BY o.deliveryID";
        String updateOrder = "Update orders SET deliveryID = ?, status ='rider_assigned' where orderID = ?";
        String createDelivery = "Insert INTO delivery(riderID,timestamp) Values (?,?)";
        PreparedStatement getDeliveryIDStatement = null;
        try {
            getDeliveryIDStatement = this.conn.prepareStatement(deliveryIDQuery);
            getDeliveryIDStatement.setInt(1, riderid);
            ResultSet deliveryIDRS = getDeliveryIDStatement.executeQuery();
            Integer deliryID = null;
            while (deliveryIDRS.next()) {
                deliryID = deliveryIDRS.getInt("deliveryID");
            }
            System.out.println(deliryID);
            if (deliryID == null) {
                this.conn.setAutoCommit(false);
                PreparedStatement createDeliveryStatement = this.conn.prepareStatement(createDelivery, Statement.RETURN_GENERATED_KEYS);
                createDeliveryStatement.setInt(1, riderid);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                createDeliveryStatement.setString(2, dtf.format(now));
                createDeliveryStatement.executeUpdate();
                ResultSet rs = createDeliveryStatement.getGeneratedKeys();
                while (rs.next()) {
                    deliryID = rs.getInt(1);
                }
                PreparedStatement updateDeliveryStatement = this.conn.prepareStatement(updateOrder);
                updateDeliveryStatement.setInt(1, deliryID);
                System.out.println(deliryID);
                updateDeliveryStatement.setInt(2, orderid);
                int x = updateDeliveryStatement.executeUpdate();

                String riderToAssigned = "update rider set status = 'assigned' where riderID = ?";
                PreparedStatement setRiderToAssigned = this.conn.prepareStatement(riderToAssigned);
                setRiderToAssigned.setInt(1,riderid);
                setRiderToAssigned.executeUpdate();

                this.conn.commit();
                this.conn.setAutoCommit(true);
                return x;
            } else {
                PreparedStatement updateDeliveryStatement = this.conn.prepareStatement(updateOrder);
                updateDeliveryStatement.setInt(1, deliryID);
                updateDeliveryStatement.setInt(2, orderid);
                return updateDeliveryStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (this.conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            return null;

        }


//        String quary = "UPDATE orders SET riderID = ? WHERE orderID = ?";
//
//        PreparedStatement st = this.conn.prepareStatement(quary);
//        st.setInt(1,riderid);
//        st.setInt(2,orderid);
//        return st.executeUpdate();
    }


    public void confirm(int id) {
        System.out.println("step2");
        String quary = "UPDATE orders SET status=\"delivering\" WHERE deliveryID = ? ";
        String updateRiderStatus = "Update rider set status = 'confirmed' where riderID = ?";
        String getRiderID = "select riderID from delivery where deliveryID = ?";

        try {
            this.conn.setAutoCommit(false);
            System.out.println("step3");
            PreparedStatement st = this.conn.prepareStatement(quary);
            System.out.println("step4");
            st.setInt(1, id);
            st.executeUpdate();

            PreparedStatement st2 = this.conn.prepareStatement(getRiderID);
            st2.setInt(1,id);
            ResultSet rs = st2.executeQuery();
            int riderID = 0 ;
            if(rs.next()){
                riderID = rs.getInt("riderID");
            }

            PreparedStatement st3 = this.conn.prepareStatement(updateRiderStatus);
            st3.setInt(1,riderID);
            st3.executeUpdate();

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if(this.conn!=null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }


    public int reject(int orderid, String reason) {

        String quary = "UPDATE orders SET status='rejected' WHERE orderID = ?";
        String quary1 = "INSERT INTO rejectorders(orderID,reason) VALUES(?,?)";

        try {
            this.conn.setAutoCommit(false);
            PreparedStatement st = this.conn.prepareStatement(quary);
            st.setInt(1, orderid);
            st.executeUpdate();

            PreparedStatement st2 = this.conn.prepareStatement(quary1);
            st2.setInt(1, orderid);
            st2.setString(2, reason);
            st2.executeUpdate();


            this.conn.commit();
            this.conn.setAutoCommit(true);
            return 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            if (this.conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

        }

        return 0;
    }


    public List<rider> readconfirmriderlist() throws SQLException {
//        public List<chef> readcheflist() throws SQLException{

        List<rider> riderlist = new ArrayList<>();
        String query = "SELECT d.deliveryID,d.riderID,o.status from delivery d join orders o on d.deliveryID = o.deliveryID where o.status='rider_assigned' GROUP BY d.deliveryID;";
        PreparedStatement st = this.conn.prepareStatement(query);
//        st.setInt(1,Integer.parseInt(id));
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            int riderID = rs.getInt("riderID");
            int deliveryID = rs.getInt("deliveryID");


            List<CashierOrders> order = new ArrayList<>();

            String query2 = "SELECT o.orderID,SUM(quantity) AS quantity, o.deliveryID, o.status FROM user u JOIN employee e ON u.userID=e.userID JOIN rider r ON e.empID=r.riderID join delivery d on d.riderID = r.riderID join orders o on o.deliveryID = d.deliveryID JOIN hasdish h ON h.orderID=o.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID where o.status =\"rider_assigned\" and r.riderID = ? GROUP BY o.orderID";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, riderID);
            ResultSet rs2 = st2.executeQuery();

            while (rs2.next()) {
                int orderid = rs2.getInt("orderID");

                int Quantity = rs2.getInt("quantity");
                String status = rs2.getString(("status"));
                order.add(new CashierOrders(orderid, Quantity));

            }


            riderlist.add(new rider(riderID, deliveryID, order));

        }

        return riderlist;


    }


}
