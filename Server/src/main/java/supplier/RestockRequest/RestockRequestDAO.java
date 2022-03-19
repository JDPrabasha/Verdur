package supplier.RestockRequest;



import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RestockRequestDAO {
    private String jdbcURL = "jdbc:mysql://verdur.mysql.database.azure.com:3306/verdur?useSSL=false&autoReconnect=true";
    private String jdbcUsername = "dulaj@verdur";
    private String jdbcPassword = "hojwe1-Tynxod-razveh";
//    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/GS4RBezQeu?useSSL=false&autoReconnect=true";
//    private String jdbcUsername = "GS4RBezQeu";
//    private String jdbcPassword = "nhsxcISFuh";
//    private String jdbcURL = "jdbc:mysql://localhost:3306/verdur_db?useSSL=false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "";


//    private static final String GET_ALL_ITEMS = "SELECT * FROM restockrequest ";
    private static final String GET_ALL_ITEMS = "SELECT * FROM restockrequest r  INNER JOIN ingredient i WHERE r.ingID=i.ingID  and approvalStatus=\"managerApproved\" and (status=\"pending\" or status=\"\")and (r.supplierID=? or r.supplierID is NULL)";
    private static final String GET_REQUEST = " UPDATE `restockrequest` SET `status` = 'Delivering' WHERE `restockrequest`.`restockID` = ?";
    private static final String CREATE_NOTIFICATION = "insert into managernotification (type,description,targetID) values (?,?,?)";
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


    public List<RestockRequest> selectAllItems(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < RestockRequest > items = new ArrayList< >();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ITEMS);) {
            preparedStatement.setInt(1,id);
            System.out.println("hasara");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int restockID=rs.getInt("restockID");
                int itemID=rs.getInt("ingID");
                String itemName=rs.getString("name");//ingredient
                int quantity=rs.getInt("quantity");//restockrequest
                String unit=rs.getString("unit");//ingredient
                int price=rs.getInt("price");//ingredient
                String responseDeadline=rs.getString("deadline");//restockrequest
                String deliveryRequestDate=rs.getString("dueBy");//restockrequest
                String timeTillExpiry= rs.getString("expired");//restockrequest





                items.add(new RestockRequest(restockID,itemID,itemName,quantity,unit,price,responseDeadline,deliveryRequestDate,timeTillExpiry));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return items;
    }

    public void acceptRequest(RestockRequest reorder) throws SQLException {
        LocalDate time= LocalDate.now();
        String s=time.toString();
        System.out.println(java.time.LocalDate.now());
        System.out.println(GET_REQUEST);
        // try-with-resource statement will auto close the connection.
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(GET_REQUEST);
            preparedStatement.setInt(1, reorder.getRestockID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement(CREATE_NOTIFICATION);
//            "insert into managernotification (type,description,targetID) values (?,?,?)";

            preparedStatement1.setString(1,"restock");
            preparedStatement1.setString(2,"Restock Request Accepted By Supplier");
            preparedStatement1.setInt(3,reorder.getRestockID());
            preparedStatement1.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            //System.out.println("ok");
        } catch (SQLException e) {
            printSQLException(e);
            if(connection!=null){
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
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
