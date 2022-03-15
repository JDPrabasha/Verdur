package Customer.Dish;


import Core.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DishDAO {


    private static final String SELECT_DISH = "select * from dish where dishID =?";
    private static final String FILTER_DISHES_BY_NAME = "select * from dish where name like ?";
    private static final String FILTER_DISHES_BY_MEAL = "select * from dish d join hastag h on h.dishID = d.dishID join tag t on t.tagID =h.tagID where t.name = ? and d.enabled =1";
    private static final String FILTER_DISHES_BY_INGREDIENT = "select * from dish where dishID in (select h.dishID from ingredient i join hasingredient h on h.ingID = i.ingID where i.name in (?,?,?,?,?,?,?,?,?,?,?,?,?,?))";
    private static final String FILTER_DISHES_BY_FILTERS = "select * from dish where time<=? AND price<=? AND dishID in (?,?,?,?,?,?,?)";

    private static final String SELECT_CART_DISHES = "select c.cdishID, c.dishID, c.quantity, c.price, d.image, d.name from dish d join customizeddish c on d.dishID = c.dishID where c.custID =? and inCart=1";
    private static final String SELECT_ALL_DISHES = "select * from dish where enabled=1";
    private static final String SELECT_LATEST_DISHES = "select * from dish where enabled=1 limit 8";
    private static final String SELECT_DISHES_BY_TIME = "select * from dish where enabled=1";
    private static final String SELECT_RECENT_DISHES = "select d.name,d.image,d.price,d.dishID from dish d join customizeddish c on c.dishId =d.dishID join hasdish h on c.cdishID = h.cdishID join orders o on h.orderID = o.orderID  where o.custID = ? and status = ? order by o.timestamp limit 4";


    private static final String INSERT_DISH = "INSERT INTO customizedDish VALUES " +
            " (?, ?, ?, ?);";

    private static final String INSERT_CUSTOMIZATION = "INSERT INTO customization VALUES " +
            " (?, ?, ?, ?);";


    public List<Dish> selectAllDishes() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DISHES);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> selectRecentDishes(Integer id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECENT_DISHES);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "pending");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int did = rs.getInt("dishID");

                dishes.add(new Dish(name, image, did, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByName(String query) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_DISHES_BY_NAME);) {
            preparedStatement.setString(1, "%" + query + "%");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByIngredients(String[] ingredients) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_DISHES_BY_INGREDIENT);) {
            for (int i = 0; i < 14; i++) {
                preparedStatement.setString(i + 1, null);
            }
            for (int i = 0; i < ingredients.length; i++) {
                preparedStatement.setString(i + 1, ingredients[i]);
            }

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByDiet(String[] preferences, String[] lifestyles, String[] allergies) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        String preference = "select d.dishID from dish d join hastag h on d.dishID =h.dishID join tag t on h.tagID =t.tagID where t.name in (?,?,?)";
        String allergyquery = "select d.dishID from dish d join hastag h on d.dishID =h.dishID join tag t on h.tagID =t.tagID where t.name =?";
        String dietquery = "select d.dishID from dish d join hastag h on d.dishID =h.dishID join tag t on h.tagID =t.tagID where t.name in (?,?,?,?,?)";
        String finalquery = "select * from dish where dishID in (" + preference + ") AND dishID in (" + dietquery + ")";
        for (int i = 0; i < allergies.length; i++) {
            finalquery += " AND dishID in (" + allergyquery + ")";
        }

        System.out.println(finalquery);

        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(finalquery);) {
            for (int i = 0; i < 3; i++) {
                preparedStatement.setString(i + 1, null);
            }
            for (int i = 0; i < preferences.length; i++) {
                preparedStatement.setString(i + 1, preferences[i]);
            }
            for (int i = 0; i < 6; i++) {
                preparedStatement.setString(i + 3, null);
            }
            for (int i = 0; i < lifestyles.length; i++) {
                preparedStatement.setString(i + 3, lifestyles[i]);
            }
            for (int i = 0; i < allergies.length; i++) {
                preparedStatement.setString(i + 8, allergies[i]);
            }

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByFilters(int budget, int time, String[] types) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_DISHES_BY_FILTERS);) {
            for (int i = 0; i < 7; i++) {
                preparedStatement.setString(i + 1, null);
            }
            for (int i = 0; i < types.length; i++) {
                preparedStatement.setString(i + 2, types[i]);
            }

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> selectCartDishes(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_DISHES);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                id = rs.getInt("dishID");
                int customID = rs.getInt("cdishID");
                int quantity = rs.getInt("quantity");

                dishes.add(new Dish(id, customID, name, image, price, quantity));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }


    public Dish selectDish(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        Dish dish = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

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
                Integer price = rs.getInt("price");

                String description = rs.getString("description");
                System.out.println(description);

                int time = rs.getInt("estTime");

                dish = new Dish(id, name, image, description, time, price);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dish;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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

    public List<Dish> selectNewDishes() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LATEST_DISHES);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByMeal(String query) {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Dish> dishes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_DISHES_BY_MEAL);) {
            preparedStatement.setString(1, query);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }
}

