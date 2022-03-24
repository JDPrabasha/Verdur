package chef;

import User.ConnectionFactory.DB;
import kitchenmanager.kitchenmanagerorder.orderkm;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class chefdao {

    private Connection conn;


    public chefdao(DB db) {
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //    public List<chef> readcheflist() throws SQLException{
    public List<chef> readcheflist(String id) throws SQLException {

        List<chef> cheflist = new ArrayList<>();
        String query = "SELECT * FROM user u JOIN employee e ON u.userID=e.userID WHERE u.role =\"Chef\"";
        PreparedStatement st1 = this.conn.prepareStatement(query);
        ResultSet rs = st1.executeQuery();

        while (rs.next()) {

            int chefid = rs.getInt("empID");
            String chefname = rs.getString("firstName");
            String chefimage = rs.getString("photo");

            List<orderkm> order = new ArrayList<>();
//            String query2 = "SELECT * FROM orders WHERE chefID=?";
            String query2 = "SELECT * FROM orders WHERE orders.status = \"assigned\" AND chefID=?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, chefid);
            ResultSet rs2 = st2.executeQuery();


            while (rs2.next()) {
                int orderid = rs2.getInt("orderID");
                order.add(new orderkm(orderid));

            }
            cheflist.add(new chef(chefid, chefname, chefimage, order));
//            cheflist.add(new chef(chefid,chefname,chefimage,status));
        }

        return cheflist;


    }

    public int assignchef(chef d) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE orders SET chefID = ? ,status=\"assigned\", assignTimestamp = ?  WHERE orderID = ?";
        int orderid = d.getOrderid();
        int chefid = d.getChefid();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);


        System.out.println(chefid);
        PreparedStatement st = this.conn.prepareStatement(quary);
        st.setInt(1, chefid);
        st.setInt(2, orderid);
        st.setString(3,timestamp);
        return st.executeUpdate();
    }


}
