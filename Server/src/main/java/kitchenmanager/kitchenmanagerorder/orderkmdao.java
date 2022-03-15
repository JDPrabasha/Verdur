package kitchenmanager.kitchenmanagerorder;

import User.ConnectionFactory.DB;
import kitchenmanager.dishes.dish;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class orderkmdao {
    private Connection conn;


    public orderkmdao(DB db) {
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<orderkm> read() throws SQLException {
        List<orderkm> orderitem = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE chefID IS NULL AND orders.`status` = \"accepted\"";
        PreparedStatement st1 = this.conn.prepareStatement(query);
        ResultSet rs = st1.executeQuery();


        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");
            //employee
            int chefid = 0;
            String chefname = "";
            String chefimage = "";
            String query2 = "SELECT * FROM orders JOIN hasdish on orders.orderID=hasdish.orderID JOIN customizedDish ON customizedDish.cdishID=hasdish.cdishID JOIN dish on customizedDish.dishID=dish.dishID WHERE orders.orderID=?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, orderid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                int dishid = rs2.getInt("cdishID");
                String dishname = rs2.getString("name");
                int dishquantity = rs2.getInt("quantity");
                String dishimage = rs2.getString("image");
//                int esttime = rs2.getInt("estTime");   make time a string
//                int esttime = 0;

                dishitem.add(new dish(dishid, dishname, dishimage, dishquantity));

            }

            orderitem.add(new orderkm(orderid, chefid, chefname, chefimage, dishitem));


        }
        return orderitem;

    }


    public List<orderkm> readongoingorders() throws SQLException {
        List<orderkm> orderitem = new ArrayList<>();
//        String query = "SELECT orderID,chefID,status,userID,name FROM orders o JOIN user u WHERE u.userID=o.chefID AND chefID IS NOT NULL Group by orderID,chefID,status";
//        String query = "SELECT orderID,chefID,status,userID,name FROM orders o JOIN user u WHERE u.userID=o.chefID AND chefID IS NOT NULL Group by orderID,chefID,status";
        String query = "SELECT * From orders o JOIN employee e ON o.chefID=e.empID JOIN user u WHERE  o.`status` = \"assigned\" AND u.userID=e.userID AND chefID IS NOT NULL";
        //String query ="SELECT orderID,chefID,status,userID,name FROM orders o JOIN employee e ON e.empID=o.chefID JOIN user u WHERE u.userID=o.chefID AND chefID IS NOT NULL Group by orderID,chefID,status";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);


        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");
            //employee
            int chefid = rs.getInt("userID");
            String chefname = rs.getString("firstName");
            String chefimage = rs.getString("photo");
//            int chefid = 0;
//            String chefname = "";
//            String chefimage = "";


            String query2 = "SELECT * FROM orders JOIN hasdish  on orders.orderID=hasdish.orderID JOIN customizedDish ON customizedDish.cdishID=hasdish.cdishID JOIN dish on customizedDish.dishID=dish.dishID WHERE orders.orderID=?";
//            String query2 = "SELECT * FROM orders JOIN customizeddish ON orders.cdishID=customizeddish.cdish JOIN dish ON customizeddish.ddish = dish.ddishID WHERE orderID = ?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, orderid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                // String dishid = rs2.getString("cdishID");
                //String dishname = rs2.getString("name");
                //int dishquantity =rs2.getInt("quantity");
                // String dishimage =rs2.getString("image");
                int esttime = rs2.getInt("estTime");

                dishitem.add(new dish(esttime));

            }

            orderitem.add(new orderkm(orderid, chefid, chefname, chefimage, dishitem));
//            orderitem.add(new orderkm(orderid, chefid, chefname, chefimage));


        }
        return orderitem;

    }


}
