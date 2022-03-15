package Manager.Restock;

import Manager.Dao.DB;
;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RestockDAO {

    private Connection conn;
//    private String restockQuery         = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID INNER JOIN user u on r.supplierID = u.userID WHERE (status='' or status='Manager.Supplier Pending') and expired=0 and approvalStatus != 'managerDecline'";

    private String restockQuery         = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='' or status='Manager.Supplier Pending') and expired=0 and approvalStatus != 'managerDecline'";
    private String getSupplierNameQuery = "select concat(u.firstName,' ',u.lastName) as name from supplier s inner join user u on s.userID = u.userID where s.supplierID = ?";

//    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID INNER JOIN user u on r.supplierID = u.userID WHERE (status='Delivering' or status='Delivered') and expired=0";
//    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='Delivering' or status='Delivered') and expired=0";
//    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='pending' or status='Delivered') and expired=0 order by r.dueBy";
    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='pending') and expired=0 order by r.dueBy";
    private String priceQuery           = "SELECT price from inventory WHERE supplierID = ? and ingID = ?";
    private String updateApprovalStatusQuery    = "UPDATE restockrequest SET approvalStatus = ? WHERE restockID = ?";
    private String updateStatusQuery = "UPDATE restockrequest SET status = ? WHERE restockID = ?";
//    private String createRestockOrderQuery = "INSERT INTO restockorder VALUES(?,?,?,?,?,?)";
    private String createRestockOrderQuery = "UPDATE restockorder set  invoiceNo = ? ,status = ?, deliveryDate = ? where restockID = ?";

    public RestockDAO(DB db){
        try{
            this.conn = db.initializeDB();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Restock> listRestockRequests() throws SQLException {
        List<Restock> restocks = new ArrayList<>();
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(restockQuery);

        while(rs.next()){
            int restockID       = rs.getInt("restockID");
            String item         = rs.getString("i.name");
            String type         = rs.getString("type");

//            String supplier     = rs.getString("u.name");
//            String supplier     = rs.getString("u.firstName") + rs.getString("u.lastName");
            int supplierID      = rs.getInt("supplierID");
            String supplier     = getSupplierName(supplierID);
//            String supplier     = "";
            String issueddate   = rs.getString("requestedAt");
            String approvalstatus= rs.getString("approvalstatus");
            String timeremain   = rs.getString("deadline");
            int quantity        = rs.getInt("quantity");

//            int unitprice           = rs.getInt("unitPrice");
            int unitprice           = rs.getInt("price");
            int price = unitprice*quantity;


            restocks.add(new Restock(restockID,item,type,supplier,issueddate,approvalstatus,timeremain,quantity,price));
        }

        return restocks;
    }

    private String getSupplierName(int supplierID){
        try {
            PreparedStatement getSupplierNameQ = this.conn.prepareStatement(getSupplierNameQuery);
            getSupplierNameQ.setInt(1,supplierID);
            ResultSet rs = getSupplierNameQ.executeQuery();
            String supplier = null;
            while (rs.next()){
                supplier = rs.getString("name");
            }
            return supplier;
        } catch (SQLException e) {
            e.printStackTrace();
            return getSupplierName(supplierID);
        }
    }
//    public List<Manager.Restock> listRestockRequests() throws SQLException {
//        List<Manager.Restock> restocks = new ArrayList<>();
//        Statement st = this.conn.createStatement();
//        ResultSet rs = st.executeQuery(restockQuery);
//
//        while(rs.next()){
//            int restockID       = rs.getInt("restockID");
//            String item         = rs.getString("i.name");
//            String type         = rs.getString("type");
////            String supplier     = rs.getString("u.name");
//            String supplier     = rs.getString("u.firstName") + rs.getString("u.lastName");
////            String supplier     = "";
//            String issueddate   = rs.getString("requestedAt");
//            String approvalstatus= rs.getString("approvalstatus");
//            String timeremain   = rs.getString("deadline");
//            int quantity        = rs.getInt("quantity");
//
////            int unitprice           = rs.getInt("unitPrice");
//            int unitprice           = rs.getInt("price");
//            int price = unitprice*quantity;
//
//
//            restocks.add(new Manager.Restock(restockID,item,type,supplier,issueddate,approvalstatus,timeremain,quantity,price));
//        }
//
//        return restocks;
//    }

    public List<Restock> listDeliveryPendingRequests() throws SQLException {
        List<Restock> restocks = new ArrayList<>();
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(restockDeliveryQuery);

        while(rs.next()){
            int restockID       = rs.getInt("restockID");
            String item         = rs.getString("i.name");
            String type         = rs.getString("type");
//            String supplier     = rs.getString("u.name");
//            String supplier     = "";
//            String supplier = rs.getString("u.firstName")+" "+rs.getString("u.lastName");
            int supplierID      = rs.getInt("supplierID");
            String supplier     = getSupplierName(supplierID);

            String issueddate   = rs.getString("requestedAt");
            String status= rs.getString("status");
//            String timeremain   = rs.getString("deadline");
            String timeremain   = rs.getString("dueby");
            int quantity        = rs.getInt("quantity");
//            int price           = rs.getInt("price");

//            int unitprice           = rs.getInt("unitPrice");
            int unitprice           = rs.getInt("price");

//            int unitprice           = 0;
//            int supplierID      = rs.getInt("r.supplierID");
//            int ingID           = rs.getInt("r.ingID");
//            PreparedStatement st2 = this.conn.prepareStatement(priceQuery);
//            st2.setInt(1,supplierID);
//            st2.setInt(2,ingID);
//            ResultSet rs2 = st2.executeQuery();
//
//            while (rs2.next()){
//                unitprice = rs2.getInt("price");
//            }

            int price = unitprice*quantity;

            restocks.add(new Restock(restockID,item,type,supplier,issueddate,timeremain,quantity,price,status));
        }

        return restocks;
    }

    public int managerApproval(Restock r) throws SQLException {
        int restockid = r.getRestockID();
        PreparedStatement st = this.conn.prepareStatement(updateApprovalStatusQuery);
        st.setString(1,r.getApprovalstatus());
        st.setInt(2,restockid);
        return st.executeUpdate();
    }

    public int managerDeliveryCompletion(Restock r) throws SQLException {
        int restockid = r.getRestockID();
        PreparedStatement st2 = null;
            PreparedStatement st = this.conn.prepareStatement(updateStatusQuery);
            st.setString(1,r.getStatus());
            st.setInt(2,restockid);
            int r1 = st.executeUpdate();
            st2 = this.conn.prepareStatement(createRestockOrderQuery);
//            private String createRestockOrderQuery = "INSERT INTO restockorder VALUES(?,?,?,?,?,?)";
            st2.setInt(1,restockid);
            st2.setInt(4,restockid);
            st2.setString(2,"delivered");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            st2.setString(3,dtf.format(now));
//            st2.setInt(4,38);
//        st2.setInt(4,getsupplier);
//            st2.setString(5,"Payment Due");
//            st2.setString(6,dtf.format(now));
            int r2 = st2.executeUpdate();
            conn.setAutoCommit(true);
            if ((r1==1)&&(r2==1)){
                return 1;
            }else{
                return 0;
            }

//            conn.setAutoCommit(true);
//            return 0;

//
    }
}