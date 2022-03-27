package kitchenmanager.order;

import User.ConnectionFactory.DB;
import kitchenmanager.dishes.dish;
import kitchenmanager.dishes.hasIngredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class orderdao {

    private Connection conn;

    public orderdao(DB db) {
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<order> readOrder(int chefID) throws SQLException {
        System.out.println(chefID);
        List<order> orderitem = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE chefID = ? AND orders.`status` = \"assigned\" ";
        PreparedStatement stm = this.conn.prepareStatement(query);
        stm.setInt(1,chefID);
        ResultSet rs = stm.executeQuery();


        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");
            //employee
            int chefid = 0;
            String chefname = "";
            String chefimage = "";


//            String query2 = "SELECT * FROM orders JOIN hasdish ON orders.orderID=hasdish.orderID join customizeddish ON hasdish.cdishID=customizeddish.cdishID JOIN customization ON customizeddish.cdishID=customization.cdishID JOIN dish ON customizeddish.dishID= dish.dishID WHERE orders.orderID = ?";
            String query2 = "SELECT * FROM orders o join hasdish h on o.orderID =h.orderID join customizeddish c on c.cdishID =h.cdishID join dish d on d.dishID = c.dishID WHERE o.orderID = ?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, orderid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                int dishid = rs2.getInt("cdishID");
                String dishname = rs2.getString("name");
                int dishquantity = rs2.getInt("quantity");
                String dishimage = rs2.getString("image");
//                int esttime = rs2.getInt("estTime");   make time a string
                int esttime = 0;

                dishitem.add(new dish(dishid, dishname, esttime, dishimage, dishquantity));

            }

            orderitem.add(new order(orderid, dishitem, chefid, chefname, chefimage));


        }
        return orderitem;

    }


    public order readdish(String id) throws SQLException {
        String query = "SELECT * from customizeddish c JOIN dish d ON c.dishID=d.dishID WHERE c.cdishID  = " + "'" + id + "'";
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        order dishitem = null;
        List<dish> onedish = new ArrayList<>();
        while (rs.next()) {

            int dishid = rs.getInt("cdishID");
            String dishname = rs.getString("name");
            int quantity = rs.getInt("quantity");
            List<hasIngredient> ingredient = new ArrayList<>();

            String query2 = "SELECT * from customizeddish c join customization c2 on c.cdishID =c2.cdishID join hasingredient h on h.dishID = c.dishID and h.ingID = c2.ingID join ingredient i on i.ingID =c2.ingID WHERE c.cdishID =?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, dishid);
            ResultSet rs2 = st2.executeQuery();


            while (rs2.next()) {
                int ingid = rs2.getInt("ingID");
                String ingname = rs2.getString("i.name");
                String ingimage = rs2.getString("i.image");
                int ingquantity = rs2.getInt("c.quantity");
                String unit = rs2.getString("h.unit");

                ingredient.add(new hasIngredient(ingid, ingname, ingimage, ingquantity, unit));

            }

//            onedish= new dish(dishid,dishname,quantity);
            onedish.add(new dish(dishid, dishname, quantity));
            dishitem = new order(ingredient, onedish);

        }


        return dishitem;
    }

    public int completeorder(order o) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE orders SET status=\"cooked\" WHERE orders.orderID = ?";
        int orderid = o.getOrderID();
        PreparedStatement st = this.conn.prepareStatement(quary);
//        st.setString(1,"Cooked");
        st.setInt(1, orderid);
        return st.executeUpdate();
    }


//    public int assignchef(chef d) throws SQLException {
////        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
//        String quary = "UPDATE orders SET chefID = ? ,status=\"assigned\" WHERE orderID = ?";
//        int orderid = d.getOrderid();
//        int chefid = d.getChefid();
//        System.out.println(chefid);
//        PreparedStatement st = this.conn.prepareStatement(quary);
//        st.setInt(1,chefid);
//        st.setInt(2,orderid);
//        return st.executeUpdate();
//    }
}
