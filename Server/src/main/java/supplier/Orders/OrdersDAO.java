package supplier.Orders;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    private String jdbcURL = "jdbc:mysql://verdur.mysql.database.azure.com:3306/verdur?useSSL=false&autoReconnect=true";
    private String jdbcUsername = "dulaj@verdur";
    private String jdbcPassword = "hojwe1-Tynxod-razveh";

//    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/GS4RBezQeu?useSSL=false&autoReconnect=true";
//    private String jdbcUsername = "GS4RBezQeu";
//    private String jdbcPassword = "nhsxcISFuh";

//    private String jdbcURL = "jdbc:mysql://localhost:3306/verdur_db?useSSL=false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "";


//    private static final String GET_ALL_ORDERS = "SELECT * FROM ingredient JOIN restockrequest ON ingredient.ingID=restockrequest.ingID JOIN supplierpayment ON supplierpayment.restockID = restockrequest.restockID JOIN restockorder ON restockorder.invoiceNo=supplierpayment.invoiceNo";
    private static final String GET_ALL_ORDERS = "SELECT * FROM restockrequest r JOIN ingredient i on r.ingID = i.ingID WHERE NOT r.status=\"pending\" and r.supplierID = 1";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public List<Orders> selectAllOrders() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Orders > items = new ArrayList< >();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
               // int reorderID = rs.getInt("reorderID");//restockorder
                int reorderID = rs.getInt("restockID");//restockorder
                String item = rs.getString("name");//ingredient
                int quantity = rs.getInt("quantity");//restockrequest
//                int totalPrice = rs.getInt("amount");//supplierpayment
                int totalPrice = 0;
                String requestedDate = rs.getString("requestedAt");//restockrequest
//                String deliveryDate = rs.getString("deliveryDate");//restockrequest  >>>>>this one
                String deliveryDate = "";
                String timeTillDeadline = rs.getString("deadline");//restockrequest
//                String invoiceDate = rs.getString("invoiceDate");//restockorder >>>>and this one
                String invoiceDate = "";
                String status = rs.getString("status");//restockorder
                if(status=="Delivered"){
                    System.out.println("bla bla");
                    //query and get values from restock order table of completed orders
                    //SELECT * FROM `restockorder` WHERE `reorderID`= 1    <<< here 1 must be replaced by reorderid

                }





                items.add(new Orders(reorderID,item,quantity,totalPrice,requestedDate,deliveryDate,timeTillDeadline,invoiceDate,status));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return items;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

