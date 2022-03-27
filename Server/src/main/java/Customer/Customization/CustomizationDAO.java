package Customer.Customization;


import User.ConnectionFactory.DB;
import Customer.Ingredient.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomizationDAO {

    private Connection conn;
    private static final String ADD_CUSTOMIZED_DISH = " insert into customizeddish(dishID, price, quantity,custID) values (?,?,?,?) ";
    private static final String ADD_INGREDIENT = " insert into customization values (?,?,?) ";
    private static final String ADD_BACK_TO_CART = "update customizeddish set inCart=1 where orderID in (select orderID from orders o join hasdish h on o.orderID = h.orderID where o.orderID = ?)";
    private static final String REMOVE_ITEM_FROM_CART = "delete from customizeddish where cdishID =?";

    public CustomizationDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCustomizeddish(Customization customDish) throws SQLException {
        Integer x = 0;
        // try-with-resource statement will auto close the this.conn.
        try {
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = this.conn.prepareStatement(ADD_CUSTOMIZED_DISH);
            System.out.println("i am adding the dish");
            System.out.println(customDish.getPrice() + " cust" + customDish.getCustomer() + " id" + customDish.getId() + " q" + customDish.getQuantity());
            preparedStatement.setInt(1, customDish.getId());
            preparedStatement.setInt(2, customDish.getPrice());
            preparedStatement.setInt(3, customDish.getQuantity());
            preparedStatement.setInt(4, customDish.getCustomer());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            PreparedStatement secondStatement = this.conn.prepareStatement("SELECT LAST_INSERT_ID() as last");
            ResultSet rs = secondStatement.executeQuery();
            while (rs.next()) {

                x = rs.getInt("last");
            }

            List<Ingredient> ingredients = customDish.getIngredients();

            PreparedStatement thirdStatement = this.conn.prepareStatement(ADD_INGREDIENT);

            for (Ingredient i : ingredients) {
                thirdStatement.setInt(1, x);
                thirdStatement.setInt(2, i.getId());
                thirdStatement.setInt(3, i.getQuantity());
                System.out.println(thirdStatement);
                thirdStatement.addBatch();
            }
            System.out.println("i am adding the ingrdients");
            thirdStatement.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }

    private void printSQLException(SQLException e) {
    }

    public void addDishesToCart(String id) throws SQLException {


        try {

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = this.conn.prepareStatement(ADD_BACK_TO_CART);
            preparedStatement.setString(1, id);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }

    public void removeFromCart(int id) throws SQLException {

        try {
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = this.conn.prepareStatement(REMOVE_ITEM_FROM_CART);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }


}
