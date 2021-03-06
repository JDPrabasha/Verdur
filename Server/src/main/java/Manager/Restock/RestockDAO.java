package Manager.Restock;

import User.ConnectionFactory.DB;



import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestockDAO {

    private Connection conn;
//    private String restockQuery         = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID INNER JOIN user u on r.supplierID = u.userID WHERE (status='' or status='Manager.Supplier Pending') and expired=0 and approvalStatus != 'managerDecline'";

    private String restockQuery         = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='' or status='pending') and expired=0 and approvalStatus != 'managerDecline'";
    private String getSupplierNameQuery = "select concat(u.firstName,' ',u.lastName) as name from supplier s inner join user u on s.userID = u.userID where s.supplierID = ?";

//    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID INNER JOIN user u on r.supplierID = u.userID WHERE (status='Delivering' or status='Delivered') and expired=0";
//    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='Delivering' or status='Delivered') and expired=0";
//    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='pending' or status='Delivered') and expired=0 order by r.dueBy";
    private String restockDeliveryQuery = "SELECT * FROM restockrequest r INNER join ingredient i  ON  r.ingID = i.ingID WHERE (status='delivering' or status='delivered') and expired=0 order by r.dueBy";
    private String priceQuery           = "SELECT price from inventory WHERE supplierID = ? and ingID = ?";
    private String updateApprovalStatusQuery    = "UPDATE restockrequest SET approvalStatus = ? WHERE restockID = ?";
    private String updateStatusQuery = "UPDATE restockrequest SET status = ? WHERE restockID = ?";
//    private String createRestockOrderQuery = "INSERT INTO restockorder VALUES(?,?,?,?,?,?)";
    private String createRestockOrderQuery = "UPDATE restockorder set  invoiceNo = ? ,status = ?, deliveryDate = ? where restockID = ?";

    private String stockUpdate = "update stock set quantity=? where ingID = ?";
    private String getStock = "select quantity from stock where ingID=?";
    private String getRestockQuantity = "select quantity from restockrequest where restockID =?";
    private String getIngID = "select ingID from restockrequest where restockID = ?";

    private String timeoutExpiredRestockRequests = "update restockrequest r set r.expired = 1 WHERE r.deadline <=CURRENT_TIMESTAMP() and r.status in ('','pending') and r.approvalStatus != 'managerDecline'";
    private String notifyKmExpiredRequets = "insert into kitchenmanagernotifications (type, targetID, description, seen) values (?,?,?,?)";
    private String getAllExpiredRequests = "SELECT ingID from restockrequest r WHERE r.deadline <=CURRENT_TIMESTAMP() and r.status in ('','pending') and r.approvalStatus != 'managerDecline' and expired = 0";
    private String getIngName = "select name from ingredient where ingID = ?";

    private String getSupplierIDQ = "select supplierID from restockrequest where restockID = ?";
    private String getSupplierEmailQ = "SELECT l.email  from supplier s join login l on s.userID = l.userID where s.supplierID = ?";

    public RestockDAO(){
        try{
            this.conn = DB.initializeDB();
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
            String supplier = "To All";
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
            String status =rs.getString("status");
            if (Objects.equals(status, "delivering")){
                status = "pending";
            };
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

        Integer supplierID = getSupplierID(restockid);
        if(supplierID!=null){
            String supplierEmail = getSupplierEmail(supplierID);
            String ingredientName = getIngName(getIngID(restockid));
            SendSupplierMail mail = new SendSupplierMail(supplierEmail,ingredientName);
            mail.sendMail();
        }
        return st.executeUpdate();
    }

    public int managerDeliveryCompletion(Restock r) throws SQLException {
        int restockid = r.getRestockID();
        PreparedStatement st2 = null;
            PreparedStatement st = this.conn.prepareStatement(updateStatusQuery);
            st.setString(1,r.getStatus());
            st.setInt(2,restockid);
            int r1 = st.executeUpdate();

//            <<---------moved this part to supplier----------------->>

//            st2 = this.conn.prepareStatement(createRestockOrderQuery);
////            private String createRestockOrderQuery = "INSERT INTO restockorder VALUES(?,?,?,?,?,?)";
//            st2.setInt(1,restockid);
//            st2.setInt(4,restockid);
//            st2.setString(2,"delivered");
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDateTime now = LocalDateTime.now();
//            st2.setString(3,dtf.format(now));
////            st2.setInt(4,38);
////        st2.setInt(4,getsupplier);
////            st2.setString(5,"Payment Due");
////            st2.setString(6,dtf.format(now));
//            int r2 = st2.executeUpdate();
//        <<-------------------------------------------------------->>

            PreparedStatement updateRestockOrderSt = this.conn.prepareStatement("update restockorder set deliveryDate =  ? ,status = ? where restockID = ?");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            updateRestockOrderSt.setString(1,dtf.format(now));
            updateRestockOrderSt.setString(2,"delivered");
            updateRestockOrderSt.setInt(3,restockid);
            updateRestockOrderSt.executeUpdate();


            PreparedStatement increaseStockSt = this.conn.prepareStatement(stockUpdate);
            int ingID = getIngID(restockid);
            increaseStockSt.setDouble(1,getRestockQuantity(restockid)+getStock(ingID));
            increaseStockSt.setInt(2,ingID);
            int r2 =increaseStockSt.executeUpdate();


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

    private Double getStock(int ingID){
        try {
            PreparedStatement getStockSt = this.conn.prepareStatement(getStock);
            getStockSt.setInt(1,ingID);
            ResultSet stockR = getStockSt.executeQuery();
            if(stockR.next()){
                return stockR.getDouble("quantity");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Double getRestockQuantity(int restockID){
        try {
            PreparedStatement restockQuantitySt = this.conn.prepareStatement(getRestockQuantity);
            restockQuantitySt.setInt(1,restockID);
            ResultSet restockQuantityR = restockQuantitySt.executeQuery();
            if(restockQuantityR.next()){
                return restockQuantityR.getDouble("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer getIngID(int restockID){
        try {
            PreparedStatement ingIDSt = this.conn.prepareStatement(getIngID);
            ingIDSt.setInt(1,restockID);
            ResultSet ingIDR = ingIDSt.executeQuery();
            if(ingIDR.next()){
                return ingIDR.getInt("ingID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int timeOutRequests(){
        try {
//            this.conn.setAutoCommit(false);
            PreparedStatement getExpiredQ = this.conn.prepareStatement(getAllExpiredRequests);
            ResultSet expiredR = getExpiredQ.executeQuery();

            this.conn.setAutoCommit(false);
            PreparedStatement notifyKmSt = this.conn.prepareStatement(notifyKmExpiredRequets);
            while (expiredR.next()){
                notifyKmSt.setString(1,"Restock");
                notifyKmSt.setInt(2,expiredR.getInt("ingID"));
                notifyKmSt.setString(3,"Restock Expired on "+getIngName(expiredR.getInt("ingID")));
                notifyKmSt.setInt(4,0);
                notifyKmSt.addBatch();
            }
            this.conn.setAutoCommit(false);
            notifyKmSt.executeBatch();
            PreparedStatement timeOutRequests = this.conn.prepareStatement(timeoutExpiredRestockRequests);
            timeOutRequests.executeUpdate();
//            this.conn.setAutoCommit(false);
            this.conn.commit();
            this.conn.setAutoCommit(true);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            if(this.conn!=null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            return 0;
        }

    }

    private String getIngName(int ingID) {
        try {
            PreparedStatement getNameQ = this.conn.prepareStatement(getIngName);
            getNameQ.setInt(1,ingID);
            ResultSet getNameR = getNameQ.executeQuery();
            if(getNameR.next()){
                return getNameR.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer getSupplierID(int restockID){
        try {
            PreparedStatement getSupplierIDSt = this.conn.prepareStatement(getSupplierIDQ);
            getSupplierIDSt.setInt(1,restockID);
            ResultSet getSupplierIDR = getSupplierIDSt.executeQuery();
            if(getSupplierIDR.next()){
                return getSupplierIDR.getInt("supplierID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSupplierEmail(int supplierID){
        try {
            PreparedStatement getSupplierEmailSt = this.conn.prepareStatement(getSupplierEmailQ);
            getSupplierEmailSt.setInt(1,supplierID);
            ResultSet supplierRS = getSupplierEmailSt.executeQuery();
            if(supplierRS.next()){
                return supplierRS.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
