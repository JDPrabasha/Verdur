package kitchenmanager.order;

import Dao.DB;
import kitchenmanager.dishes.dish;
import kitchenmanager.dishes.hasIngredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class orderdao {

    private Connection conn;

    public orderdao(DB db){
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<order> readOrder() throws SQLException{
        List<order> orderitem = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE chefID = 1 AND orders.`status` = \"assigned\" ";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);


        while (rs.next()) {
            List<dish> dishitem = new ArrayList<>();
            //orders
            int orderid = rs.getInt("orderID");
            //employee
            int chefid = 0;
            String chefname = "";
            String chefimage = "";


            String query2 = "SELECT * FROM orders JOIN hasdish ON orders.orderID=hasdish.orderID join customizeddish ON hasdish.cdishID=customizeddish.cdishID JOIN customization ON customizeddish.cdishID=customization.cdishID JOIN dish ON customizeddish.dishID= dish.dishID WHERE orders.orderID = ?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1,orderid);
            ResultSet rs2= st2.executeQuery();
            while (rs2.next()){
                int dishid = rs2.getInt("cdishID");
                String dishname = rs2.getString("name");
                int dishquantity =rs2.getInt("quantity");
                String dishimage =rs2.getString("image");
//                int esttime = rs2.getInt("estTime");   make time a string
                int esttime = 0;

                dishitem.add(new dish(dishid,dishname,esttime,dishimage,dishquantity));

            }

            orderitem.add(new order(orderid, dishitem, chefid, chefname, chefimage));


        }
        return orderitem;

    }


    public order readdish(String id) throws SQLException {
        String query = "SELECT * from customizeddish c JOIN dish d ON c.dishID=d.dishID WHERE c.cdishID  = "+"'"+id+"'";
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        order dishitem = null;
        List<dish> onedish = new ArrayList<>();
        while (rs.next()){

            int dishid = rs.getInt("cdishID");
            String dishname = rs.getString("name");
            int quantity =rs.getInt("quantity");
            List <hasIngredient> ingredient = new ArrayList<>();

            String query2= "SELECT * FROM customizeddish JOIN customization ON customizeddish.cdishID=customization.cdishID JOIN dish ON dish.dishID=customizeddish.dishID JOIN ingredient ON ingredient.ingID=customization.ingID WHERE customizeddish.cdishID=?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1,dishid);
            ResultSet rs2= st2.executeQuery();


            while (rs2.next()){
                int ingid = rs2.getInt("ingID");
                String ingname = rs2.getString("ingredient.name");
                String ingimage =rs2.getString("ingredient.image");
                int ingquantity = rs2.getInt("quantity");
                String unit = rs2.getString("unit");

                ingredient.add(new hasIngredient(ingid,ingname,ingimage,ingquantity,unit));

            }

//            onedish= new dish(dishid,dishname,quantity);
            onedish.add(new dish(dishid,dishname,quantity));
            dishitem = new order(ingredient,onedish);

        }


        return dishitem;
    }

    public int completeorder(order o) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE orders SET status=\"cooked\" WHERE orders.orderID = ?";
        int orderid= o.getOrderID();
        PreparedStatement st = this.conn.prepareStatement(quary);
//        st.setString(1,"Cooked");
        st.setInt(1,orderid);
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
