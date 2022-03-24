package cashier.rider;

import User.ConnectionFactory.DB;
import cashier.CashierOrders.CashierOrders;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class riderdao {

    private Connection conn;


    public riderdao() {
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<rider> readriderlist(String id) throws SQLException {
//        public List<chef> readcheflist() throws SQLException{

        List<rider> riderlist = new ArrayList<>();
        String query = "SELECT * FROM user u JOIN employee e ON u.userID=e.userID JOIN rider r ON r.riderID=e.empID WHERE u.role =\"Rider\" and (r.status =\"available\" or r.status = \"assigned\") ";
        PreparedStatement st = this.conn.prepareStatement(query);
//        st.setInt(1,Integer.parseInt(id));
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            int riderID = rs.getInt("empID");
            String status = rs.getString("status");
            String photo = rs.getString("photo");
            String name = rs.getString("firstName");


            List<CashierOrders> order = new ArrayList<>();

            String query2 = "SELECT o.orderID,SUM(quantity) AS quantity, o.deliveryID, o.longitude,latitude FROM user u JOIN employee e ON u.userID=e.userID JOIN rider r ON e.empID=r.riderID join delivery d on d.riderID = r.riderID join orders o on o.deliveryID = d.deliveryID JOIN hasdish h ON h.orderID=o.orderID JOIN customizeddish cd ON cd.cdishID=h.cdishID where o.status =\"rider_assigned\" and r.riderID = ? GROUP BY o.orderID";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, riderID);
            ResultSet rs2 = st2.executeQuery();

            while (rs2.next()) {
                int orderid = rs2.getInt("orderID");
                int deliveryid = rs2.getInt("deliveryID");
//                String address = rs2.getString("address");
                Double longitude = rs2.getDouble("longitude");
                Double latitude = rs2.getDouble("latitude");
                int Quantity = rs2.getInt("quantity");


                order.add(new CashierOrders(orderid, deliveryid, "asd", longitude, latitude, Quantity));

            }


            riderlist.add(new rider(riderID, status, photo, name, order));

        }

        return riderlist;


    }


}