package Customer.Dish;


import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DishDAO {


    private static final String SELECT_DISH = "select * from dish where dishID =?";
    private static final String FILTER_DISHES_BY_NAME = "select * from dish where name like ? and enabled =1";
    private static final String FILTER_DISHES_BY_MEAL = "select * from dish d join hastag h on h.dishID = d.dishID join tag t on t.tagID =h.tagID where t.name = ? and d.enabled =1";
    private static final String FILTER_DISHES_BY_INGREDIENT = "select * from dish where dishID in (select h.dishID from ingredient i join hasingredient h on h.ingID = i.ingID where i.name in (?,?,?,?,?,?,?,?,?,?,?,?,?,?)) and enabled =1";
    private static final String FILTER_DISHES_BY_FILTERS = "select d.dishID,d.name,d.image,d.price from dish d join hastag h on d.dishID =h.dishID join tag t on h.tagID =t.tagID where d.estTime<=? AND d.price<=? AND  t.name in (?,?,?,?,?,?,?,?,?,?) and d.enabled =1 group by d.dishID having count(*)=?";
    private static final String SELECT_CART_DISHES = "select c.cdishID, c.dishID, c.quantity, c.price, d.image, d.name from dish d join customizeddish c on d.dishID = c.dishID where c.custID =? and inCart=1";
    private static final String SELECT_ALL_DISHES = "select * from dish where enabled=1";
    private static final String SELECT_LATEST_DISHES = "select * from dish where enabled=1 limit 8";
    private static final String SELECT_RECENT_DISHES = "select d.name,d.image,d.price,d.dishID from dish d join customizeddish c on c.dishId =d.dishID join hasdish h on c.cdishID = h.cdishID join orders o on h.orderID = o.orderID  where o.custID = ? and status in (?,?) order by o.timestamp limit 4";
    private static final String RATE_DISH = "update rating set rating=? where dishID=? and custID=?";
    private Connection conn;

    public DishDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Dish> selectAllDishes() {

        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_ALL_DISHES);) {

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> selectRecentDishes(Integer id) {

        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_RECENT_DISHES);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "delivered");
            preparedStatement.setString(3, "reviewed");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int did = rs.getInt("dishID");

                dishes.add(new Dish(name, image, did, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByName(String query) {

        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(FILTER_DISHES_BY_NAME);) {
            preparedStatement.setString(1, "%" + query + "%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByIngredients(String[] ingredients) {

        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(FILTER_DISHES_BY_INGREDIENT);) {
            for (int i = 0; i < 14; i++) {
                preparedStatement.setString(i + 1, null);
            }
            for (int i = 0; i < ingredients.length; i++) {
                preparedStatement.setString(i + 1, ingredients[i]);
            }

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }


    public List<Dish> filterDishesByFilters(int budget, int time, String[] tags) {

        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(FILTER_DISHES_BY_FILTERS);) {
            preparedStatement.setInt(1, time);
            preparedStatement.setInt(2, budget);
            for (int i = 0; i < 10; i++) {
                preparedStatement.setString(i + 3, null);
            }
            for (int i = 0; i < tags.length; i++) {
                preparedStatement.setString(i + 3, tags[i]);
            }

            preparedStatement.setInt(13, tags.length);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> selectCartDishes(int id) {

        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_CART_DISHES);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                id = rs.getInt("dishID");
                int customID = rs.getInt("cdishID");
                int quantity = rs.getInt("quantity");

                dishes.add(new Dish(id, customID, name, image, price, quantity));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public void rateDish(Dish dish, String customer) {
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(RATE_DISH)) {
            conn.setAutoCommit(false);

            preparedStatement.setInt(1, dish.getRating());
            preparedStatement.setInt(2, dish.getId());
            preparedStatement.setString(3, customer);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }


    }

    public Dish selectDish(int id) {

        Dish dish = null;
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_DISH);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();


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
        } catch (SQLException e) {
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
        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_LATEST_DISHES);) {

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }

    public List<Dish> filterDishesByMeal(String query) {
        List<Dish> dishes = new ArrayList<>();
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(FILTER_DISHES_BY_MEAL);) {
            preparedStatement.setString(1, query);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer price = rs.getInt("price");
                int id = rs.getInt("dishID");

                dishes.add(new Dish(name, image, id, price));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return dishes;
    }
}

