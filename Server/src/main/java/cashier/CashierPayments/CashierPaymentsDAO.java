package cashier.CashierPayments;

//import OngoingOrders.OngoingOrders;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashierPaymentsDAO {
//    private String jdbcURL = "jdbc:mysql://verdur.mysql.database.azure.com:3306/verdur?useSSL=false&autoReconnect=true";
//    private String jdbcUsername = "dulaj@verdur";
//    private String jdbcPassword = "hojwe1-Tynxod-razveh";

//    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/GS4RBezQeu?useSSL=false&autoReconnect=true";
//    private String jdbcUsername = "GS4RBezQeu";
//    private String jdbcPassword = "nhsxcISFuh";

    private String jdbcURL = "jdbc:mysql://localhost:3306/verdur_new?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";


    private static final String GET_ALL_ITEMS = "SELECT * FROM orders o JOIN payment p ON o.orderID=p.orderID JOIN delivery d ON d.deliveryID = o.deliveryID where DATE(d.`timestamp`)=? ";



    public List<CashierPayments> selectAllItems(String date) {
        System.out.println("hasara");
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < CashierPayments > items = new ArrayList< >();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ITEMS);) {
            preparedStatement.setString(1,date);
            System.out.println(preparedStatement);

            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                int orderID = rs.getInt("orderID");//orders
                int riderID=rs.getInt("riderID");//delivery
                int dueAmount=rs.getInt("amount");//payment

                String assignedTime=rs.getString("timestamp");//
                String status = rs.getString("status");//payment
                String type = rs.getString("type");//payment
                String paymentCompleted = rs.getString("completed");



                items.add(new CashierPayments(orderID,riderID,dueAmount,assignedTime,status,type,paymentCompleted));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Integer completeOrderCashier(int ID){
        String completeOrderQuery = "Update orders set completed = 1 where orderID = ?";
        try(Connection conn = DB.initializeDB()){
            PreparedStatement completeOrderQ = conn.prepareStatement(completeOrderQuery);
            completeOrderQ.setInt(1,ID);
            return completeOrderQ.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
