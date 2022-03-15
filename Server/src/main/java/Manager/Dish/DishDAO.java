package Manager.Dish;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DishDAO {
        private String jdbcURL = "jdbc:mysql://localhost/dish?useSSL=false";
        private String jdbcUsername = "root";
        private String jdbcPassword = "";

        private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
                " (?, ?, ?);";

        private static final String SELECT_DISH = "select * from dish where dish_id =?";
        private static final String SELECT_ALL_DISHES = "select * from dish";

        private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
        private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";



        protected Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
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


        public List < Dish > selectAllDishes() {

            // using try-with-resources to avoid closing resources (boiler plate code)
            List < Dish > dishes = new ArrayList < > ();
            // Step 1: Establishing a Connection
            try (Connection connection = getConnection();

                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DISHES);) {

                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {

                    String name = rs.getString("name");
                    String image = rs.getString("image");

                    int id = rs.getInt("dish_id");

                    dishes.add(new Dish(name,image,id));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return dishes;
        }

    public Dish selectDish(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        Dish dish = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISH);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                System.out.println(name);
                String image = rs.getString("image");
                String description = rs.getString("description");
                System.out.println(description);
//                int price= rs.getInt("price");
                int time = rs.getInt("time");

                dish=new Dish(id, name, image, description, time);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return dish;
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

