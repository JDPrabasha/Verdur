package Manager.Complaints;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintsDAO {

    private Connection conn;

    private static String getComplaintsQuery = "select c.complaintID, c.description, c.`type`,c.`timestamp`, concat(u.firstName,\" \",u.lastName )as customername, concat(u2.firstName,\" \",u2.lastName )as ridername from complaint c join customer c2 on c.custID =c2.custID join `user` u on c2.userID = u.userID join orders o on c.orderID =o.orderID join delivery d on o.orderID =d.deliveryID join employee e on d.riderID = e.empID join `user` u2 on e.userID = u2.userID ";
    private static String getSpecificComplaintQuery = "select c.complaintID,o.orderID,concat( o.longitude,\" \",o.latitude ) as location,o.chefID ,c.description, c.`type`,c.`timestamp`, concat(u.firstName,\" \",u.lastName )as customername, concat(u2.firstName,\" \",u2.lastName )as ridername from complaint c join customer c2 on c.custID =c2.custID join `user` u on c2.userID = u.userID join orders o on c.orderID =o.orderID join delivery d on o.orderID =d.deliveryID join employee e on d.riderID = e.empID join `user` u2 on e.userID = u2.userID where c.complaintID = ?";
    private static String getChefNameQuery = "select concat(u.firstName,\" \",u.lastName) as name from employee e join user u on e.userID = u.userID where empID = ?";
    private static String getPaymentQuery = "select amount from payment where orderID = ?";
    private static String getItemQuery = "select d.name from hasdish h join customizeddish c on h.cdishID =c.cdishID join dish d on c.dishID = d.dishID where h.orderID = ?";



    public ComplaintsDAO(){
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Complaints> getComplaints(){
        List<Complaints> complaints = new ArrayList<>();
        try {
            Statement getComplaintsQ = this.conn.createStatement();
            ResultSet comlpaintsR = getComplaintsQ.executeQuery(getComplaintsQuery);
            while (comlpaintsR.next()){
                int compaintID = comlpaintsR.getInt("complaintID");
                String custName = comlpaintsR.getString("customername");
                String type = comlpaintsR.getString("type");
//                String type = "";
                String riderName = comlpaintsR.getString("ridername");
                String description = comlpaintsR.getString("description");
                String date = comlpaintsR.getString("timestamp");

                complaints.add(new Complaints(compaintID,custName,type,riderName,description,date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return complaints;
    }

    public Complaints getAComplaint(int id){
        Complaints complaint = null;
        try {
            PreparedStatement getSpecificComplaintQ = this.conn.prepareStatement(getSpecificComplaintQuery);
            getSpecificComplaintQ.setInt(1,id);
            ResultSet specificQueryR = getSpecificComplaintQ.executeQuery();
            while(specificQueryR.next()){
                int orderID = specificQueryR.getInt("orderID");
                String conplaintDescription = specificQueryR.getString("description");
                String customerName = specificQueryR.getString("customername");
                String deliveryLocation = specificQueryR.getString("location");
                String rider = specificQueryR.getString("ridername");
                String chef = getChef(specificQueryR.getInt("chefID"));
                int itemCount = 0 ;
                int payment = getPayment(orderID);
                ArrayList<String> orderItems = new ArrayList<>();
                PreparedStatement getOrderItems = this.conn.prepareStatement(getItemQuery);
                getOrderItems.setInt(1,orderID);
                ResultSet orderItemsR = getOrderItems.executeQuery();
                while (orderItemsR.next()){
                    itemCount+=1;
                    orderItems.add(orderItemsR.getString("d.name"));
                }
                complaint = new Complaints(id,orderID,customerName,rider,conplaintDescription,deliveryLocation,chef,itemCount,payment,orderItems);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaint;
    }

    private String getChef(int chefID){
        try {
            PreparedStatement getChefQ = this.conn.prepareStatement(getChefNameQuery);
            getChefQ.setInt(1,chefID);
            ResultSet chefNameR = getChefQ.executeQuery();
            String chefName = null ;
            while (chefNameR.next()){
                chefName = chefNameR.getString("name");
            }
            return  chefName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getPayment(int orderID){
        try {
            PreparedStatement getPaymentQ =  this.conn.prepareStatement(getPaymentQuery);
            getPaymentQ.setInt(1,orderID);
            ResultSet paymentR = getPaymentQ.executeQuery();
            int payment = 0;
            while (paymentR.next()){
                payment = paymentR.getInt("amount");
            }
            return payment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
