package Manager.Supplier;

import User.ConnectionFactory.DB;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private Connection conn;
    private static String supplierQuery = "SELECT * FROM `supplier` s INNER JOIN user u INNER JOIN login l WHERE s.userID=u.userID AND s.userID=l.userID";
    private static String supplierAddQuery = "INSERT INTO supplier (userID,location,image)";
    static String userquery         = "INSERT INTO user (role,name,contact_No) VALUES (?,?,?)";
    static String loginquery        = "INSERT INTO login (userID,email,password,code) VALUES (?,?,?,?)";

    public SupplierDAO(){
        try{
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> getSuppliers() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(supplierQuery);
        while (rs.next()){
            int id          = rs.getInt("userID");
            String image    = rs.getString("image");
            String name     = rs.getString("name");
//            String org      = rs.getString("org");
            String org      = "";
            int ordersDue   = 0;
//            int ordersDue   = rs.getInt("ordersDue");
            int ordersDone  = 0;
//            int ordersDone  = rs.getInt("ordersDone");
            String email    = rs.getString("email");
//            String contact  = "";
            String contact  = rs.getString("contact");
            String location = rs.getString("location");
            String joinedon = "";
//            String joinedon = rs.getString("joinedon");
            int pendingPay  = 0;
//            int pendingPay  = rs.getInt("pendingPay");

            supplierList.add(new Supplier(id,image,name,org,ordersDue,ordersDone,email,contact,location,joinedon,pendingPay));
        }
        return supplierList;
    }

    public int addnew(Supplier s, int code) {
//        PreparedStatement st1 = this.conn.prepareStatement("INSERT INTO user (role,name,contact_No) VALUES (\"Kitchen Manager\",\"Test\",\"123456\")",Statement.RETURN_GENERATED_KEYS);
        int rs3 =0;
        try {
//              conn.setAutoCommit(false);
            PreparedStatement st1 = this.conn.prepareStatement(userquery, Statement.RETURN_GENERATED_KEYS);
            st1.setString(1, "Supplier");
            st1.setString(2, s.getName());
            st1.setString(3, s.getContact());
            st1.executeUpdate();
            ResultSet rs1 = st1.getGeneratedKeys();
            int userID = 0;
            while (rs1.next()) {
                userID = rs1.getInt(1);
            }

            System.out.println(userID);
            PreparedStatement st2 = this.conn.prepareStatement(loginquery);
            st2.setInt(1, userID);
            st2.setString(2, s.getEmail());
            st2.setString(3, "");
            st2.setInt(4,code);
            int rs2 = st2.executeUpdate();
            //        userID,address,photo,nic.dob
            PreparedStatement st3 = this.conn.prepareStatement(supplierAddQuery);
            st3.setInt(1, userID);
            st3.setString(2, s.getLocation());
            st3.setString(3, s.getImage());
            rs3 = st3.executeUpdate();
        } catch (SQLException throwables) {
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

        return rs3;
//        System.out.println(s.getAddress());
//        System.out.println(s.getDob());
//        return 0;
    }
}
