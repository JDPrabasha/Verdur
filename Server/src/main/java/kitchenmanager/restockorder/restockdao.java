package kitchenmanager.restockorder;

import Dao.DB;
import kitchenmanager.inventory.inventory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class restockdao {
    private Connection conn;

    public restockdao(DB db) {
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<restock> read() throws SQLException {
        List<restock> restockrequests = new ArrayList<>();
        String query = "SELECT * FROM restockrequest r INNER JOIN ingredient i ON r.ingID=i.ingID order by approvalStatus";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {

            int restockid = rs.getInt("restockID");
            int ingid = rs.getInt("ingID");

            String status = rs.getString("approvalStatus");
            String deadline = rs.getString("deadline");
            String requestdate = rs.getString("requestedAt");
            String ingname = rs.getString("name");

            restockrequests.add(new restock(restockid, ingid, status, deadline, ingname, requestdate));

        }
        return restockrequests;

    }

    public restock readrestockrequest(String id) throws SQLException {
//        String query = "SELECT * FROM stock JOIN ingredient ON ingredient.ingID=stock.ingID JOIN restockrequest ON restockrequest.ingID = stock.ingID JOIN restockorder ON restockorder.restockID=restockrequest.restockID WHERE ingredient.ingID =?";
        String query = "SELECT * FROM stock JOIN ingredient ON ingredient.ingID=stock.ingID WHERE ingredient.ingID =?";
        PreparedStatement st3 = this.conn.prepareStatement(query);
        st3.setString(1,id);
//        st2.setInt(2,r.getSupplierid());
        ResultSet rs= st3.executeQuery();

//        Statement st = this.conn.createStatement();
//        ResultSet rs = st.executeQuery(query);
        restock inventoryitem = null;
        List<inventory> stockitem = new ArrayList<>();
        while (rs.next()) {

            int ingid = rs.getInt("ingID");
            String ingcode = rs.getString("itemCode");
            String ingname = rs.getString("name");
            String ingtype = rs.getString("type");
            String image = rs.getString("image");
            String restockdate = rs.getString("description");
            int quantity = rs.getInt("quantity");
            int maxlevel = rs.getInt("max");
            int safelevel = rs.getInt("normal");
            int restocklevel = rs.getInt("min");
            String unit = rs.getString("unit");

            List<supplier> supplierlist = new ArrayList<>();
            String query2 = "SELECT * FROM inventory i JOIN supplier s ON i.supplierID=s.supplierID JOIN user u ON u.userID=s.userID WHERE i.ingID=?";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, ingid);
            ResultSet rs2 = st2.executeQuery();

            while (rs2.next()) {
                int supplierid = rs2.getInt("supplierID");
                String suppliername = rs2.getString("firstName");
                int price = rs2.getInt("price");
                supplierlist.add(new supplier(supplierid, suppliername,price));

            }

            stockitem.add(new inventory(ingid, ingcode, ingtype, ingname, quantity, maxlevel, safelevel, restocklevel, image,restockdate,unit));
            inventoryitem = new restock(stockitem, supplierlist);


        }
        return inventoryitem;


    }

    public int addrestockrequest(restock r) throws SQLException{
        int rs = 0;
        PreparedStatement st = null;
        String quary = "INSERT INTO restockrequest(ingid,dueBy,quantity,status,requestedAt,deadline,approvalStatus,expired,supplierID,price) VALUES(?,?,?,?,?,?,?,?,?,?)";
        String quary2 = "SELECT * FROM inventory WHERE ingID =? and supplierID=?";

        try{
            int price = 0;
            PreparedStatement st2 = this.conn.prepareStatement(quary2);
            st2.setInt(1,r.getIngID());
            st2.setInt(2,r.getSupplierid());
            ResultSet rs2 = st2.executeQuery();
            while(rs2.next()){
                price = rs2.getInt("price");

            }


            st = this.conn.prepareStatement(quary, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, r.getIngID());
            st.setString(2, r.getDueBy());
            st.setInt(3, r.getQuantity());
            st.setString(4, "pending");

//            fvetrtvet
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            st.setString(5, dtf.format(now));
            st.setString(6, r.getDeadline());
            st.setString(7, "pending");
            st.setInt(8, r.getExpired());
            if(r.getSupplierid()==0){
                st.setNull(9,0);
            }
            else{
                st.setInt(9, r.getSupplierid());

            }
            st.setInt(10,price);
            rs = st.executeUpdate();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            if(conn!=null){
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return rs;
    }
}



