package Manager.RestocksOrders;

import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestockOrdersDAO {
    private Connection conn;
//    private String paymentsQuery = "SELECT * FROM restockorder r INNER JOIN user u ON r.supplierID = u.userID INNER JOIN restockrequest re on r.reorderID = re.restockID  INNER  JOIN ingredient i on re.ingID = i.ingID";
    private String paymentsQuery = "SELECT * FROM restockorder r INNER JOIN user u ON r.supplierID = u.userID INNER JOIN restockrequest re on r.restockID = re.restockID  INNER  JOIN ingredient i on re.ingID = i.ingID";

    public RestockOrdersDAO(){
        try{
            this.conn = DB.initializeDB();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<RestockOrders> listPayments() throws SQLException {
        List<RestockOrders> restockOrders = new ArrayList<>();
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(paymentsQuery);

        while (rs.next()){
//            int restockID   = rs.getInt("reorderID");
            int restockID   = rs.getInt("restockID");
            String item     = rs.getString("i.name");
//            String item     = "";
//            String supplier = rs.getString("u.name");
            String supplier = rs.getString("u.firstName")+" "+rs.getString("u.lastName");
            String comdate  = rs.getString("deliveryDate");
            String status   = rs.getString("status");

//            int unitPrice      = rs.getInt("unitPrice");
            int unitPrice      = rs.getInt("price");
            int quantity       = rs.getInt("quantity");
            int amount      = unitPrice*quantity;
//            int amount      = 0;

            restockOrders.add(new RestockOrders(restockID,item,supplier,comdate,status,amount));
        }
        return restockOrders;
    }
}
