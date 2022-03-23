package supplier.Stock;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private String jdbcURL = "jdbc:mysql://verdur.mysql.database.azure.com:3306/verdur?useSSL=false&autoReconnect=true";
    private String jdbcUsername = "dulaj@verdur";
    private String jdbcPassword = "hojwe1-Tynxod-razveh";
//    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/GS4RBezQeu?useSSL=false&autoReconnect=true";
//    private String jdbcUsername = "GS4RBezQeu";
//    private String jdbcPassword = "nhsxcISFuh";

//    private String jdbcURL = "jdbc:mysql://localhost:3306/verdur_db?useSSL=false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "";

//    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
//            " (?, ?, ?);";

    //    private static final String SELECT_DISH = "select * from inventory where dish_id =?";
    private static final String SELECT_ALL_ITEMS = "SELECT * FROM supplier s  INNER JOIN ingredient i INNER JOIN inventory iv WHERE s.userID=iv.supplierID and iv.ingID=i.ingID and iv.supplierID =?";
    private static final String SELECT_AN_ITEM = "select * from inventory i join ingredient i2 on i.ingID = i2.ingID  where supplierID = ? and i.ingID = ?";

    private static final String INSERT_ITEM_SQL = "INSERT INTO inventory  VALUES  (?, ?, ?, ?,?);";
    private static final String UPDATE_ITEM_SQL ="UPDATE inventory SET quantity = ?, price = ? WHERE ingID = ? and supplierID = ? ";
    private static final String REMOVE_ITEM_SQL ="DELETE FROM inventory WHERE ingID = ? AND supplierID = ?";
//
//    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
//    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
//


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


    public List<Stock> selectAllItems(String id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Stock> items = new ArrayList< >();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
            preparedStatement.setString(1,id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                int itemID = rs.getInt("ingID");
                String itemName = rs.getString("i.name");
                String itemType=rs.getString("type");
              //  String itemType=" ";
                double quantity=rs.getDouble("quantity");
                double price=rs.getDouble("price");
                String dateAdded=rs.getString("dateAdded");
                String unit=rs.getString("unit");



                items.add(new Stock(itemID,itemName,itemType,quantity,price,dateAdded,unit));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return items;
    }

    public List<Stock> selectAnItem(int supplierID,int ingID) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Stock> items = new ArrayList< >();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AN_ITEM);) {
            preparedStatement.setInt(1,supplierID);
            preparedStatement.setInt(2,ingID);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                int itemID = rs.getInt("ingID");
                String itemName = rs.getString("i2.name");
                String itemType=rs.getString("type");
              //  String itemType=" ";
                double quantity=rs.getDouble("quantity");
                double price=rs.getDouble("price");
                String dateAdded=rs.getString("dateAdded");
                String unit=rs.getString("unit");



                items.add(new Stock(itemID,itemName,itemType,quantity,price,dateAdded,unit));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return items;
    }







    ///////////////////////////////////////////
    public void addItem(Stock item) throws SQLException {
        LocalDate time= LocalDate.now();
        String s=time.toString();
        System.out.println(java.time.LocalDate.now());
        System.out.println(INSERT_ITEM_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
            preparedStatement.setInt(1, item.getSupplierID());
            preparedStatement.setInt(2, item.getItemID());
            preparedStatement.setDouble(3, item.getQuantity());
            preparedStatement.setDouble(4, item.getPrice());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setString(5, dtf.format(now));

            //preparedStatement.setString(5, s);
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            //System.out.println("ok");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }





    public void updateItem(Stock item) throws SQLException {
        LocalDate time= LocalDate.now();
        String s=time.toString();
        System.out.println(java.time.LocalDate.now());
        System.out.println(UPDATE_ITEM_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_SQL)) {
            preparedStatement.setDouble(1, item.getQuantity());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getItemID());
            //preparedStatement.setString(5, s);
            preparedStatement.setInt(4, item.getSupplierID());


            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("ok");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }







    public void removeItem(Stock item) throws SQLException {
        LocalDate time= LocalDate.now();
        String s=time.toString();
        System.out.println(java.time.LocalDate.now());
        System.out.println(REMOVE_ITEM_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ITEM_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, item.getSupplierID());
            preparedStatement.setInt(2, item.getItemID());
            // preparedStatement.setDouble(3, item.getQuantity());
            //preparedStatement.setDouble(4, item.getPrice());
            //preparedStatement.setString(5, s);
            System.out.println(preparedStatement);
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
