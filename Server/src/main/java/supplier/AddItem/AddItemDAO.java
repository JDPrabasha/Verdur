package supplier.AddItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddItemDAO {
    private String jdbcURL = "jdbc:mysql://verdur.mysql.database.azure.com:3306/verdur?useSSL=false&autoReconnect=true";
    private String jdbcUsername = "dulaj@verdur";
    private String jdbcPassword = "hojwe1-Tynxod-razveh";

//    private String jdbcURL = "jdbc:mysql://localhost:3306/verdur_db?useSSL=false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "";

//    private static final String INSERT_ITEM_SQL = "INSERT INTO inventory" + "  (itemName,itemType,quantity,price,dateAdded) VALUES " +
//            " (?, ?, ?, ?,?);";

    private static final String INSERT_ITEM_SQL = "INSERT INTO inventory" + "  (supplierID,ingID,quantity,price,dateAdded) VALUES " +
            " (?, ?, ?, ?,CURRENT_DATE );";

//    private static final String SELECT_DISH = "select * from dish where dish_id =?";
//    private static final String SELECT_ALL_DISHES = "select * from dish";


    public AddItemDAO() {}

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



    public void addItem(AddItem item) throws SQLException {
         LocalDate time= LocalDate.now();
         String s=time.toString();
        System.out.println(java.time.LocalDate.now());
        System.out.println(INSERT_ITEM_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
            preparedStatement.setInt(1, item.getSupplierID());
            preparedStatement.setInt(2, item.getIngID());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.setInt(4, item.getPrice());
            //preparedStatement.setString(5, s);
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            //System.out.println("ok");
        } catch (SQLException e) {
            printSQLException(e);
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
