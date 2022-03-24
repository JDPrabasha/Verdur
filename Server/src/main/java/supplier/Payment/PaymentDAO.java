package supplier.Payment;



import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private String jdbcURL = "jdbc:mysql://verdur.mysql.database.azure.com:3306/verdur?useSSL=false&autoReconnect=true";
    private String jdbcUsername = "dulaj@verdur";
    private String jdbcPassword = "hojwe1-Tynxod-razveh";

//    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/GS4RBezQeu?useSSL=false&autoReconnect=true";
//    private String jdbcUsername = "GS4RBezQeu";
//    private String jdbcPassword = "nhsxcISFuh";

//    private String jdbcURL = "jdbc:mysql://localhost:3306/verdur_db?useSSL=false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "";


    private static final String GET_ALL_ITEMS = "SELECT * FROM restockorder JOIN supplierpayment ON restockorder.restockID=supplierpayment.restockID JOIN restockrequest ON restockrequest.restockID = restockorder.restockID;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            connection = DB.initializeDB();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public List<Payment> selectAllPayments() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Payment > items = new ArrayList< >();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ITEMS);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                int invoiceID = rs.getInt("invoiceNo");//restockorder
                String invoiceDate = rs.getString("invoiceDate");//restockorder
                int totalAmount = rs.getInt("amount");//supplierpayment
                String requestedDate=rs.getString("dueDate");//restockrequest
                String deliveryDate = rs.getString("receivedDate");//supplierpayment
                String status = rs.getString("supplierpayment.status");//supplierpayment




                items.add(new Payment(invoiceID,invoiceDate,totalAmount,requestedDate,deliveryDate,status));
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
