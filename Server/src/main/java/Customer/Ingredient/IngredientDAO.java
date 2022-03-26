package Customer.Ingredient;

import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    private static final String SELECT_INGREDIENTS = "select i.ingID,i.name, h.defaultValue as quantity, i.image, h.unit, i.expandable, i.image,h.min,h.max from dish d join hasingredient h on d.dishID = h.dishID join ingredient i on h.ingID = i.ingID join ingredientweight w on w.unit = h.unit and w.ingID=i.ingID where d.dishID=?";
    private static final String SELECT_CUSTOMIZABLE_INGREDIENTS = "select i.ingID,i.name, h.min, h.max, h.ppq,h.type,i.proteinphg, i.carbsphg, i.calphg, i.fatsphg,w.weight, h.defaultValue as quantity, i.image, i.unit, i.expandable, i.image from dish d join hasingredient h on d.dishID = h.dishID join ingredient i on h.ingID = i.ingID join ingredientweight w on w.unit = h.unit and w.ingID=i.ingID where d.dishID =? and h.type!=?";
    private static final String SELECT_INGREDIENT_DETAILS = "select i.description, n.description as benefit, i.ingID, i.name, i.image from ingredient i join nutritionbenefits n on n.ingID=i.ingID where i.ingID = ? ";
    private Connection conn;

    public IngredientDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> selectAllIngredients(int id) {
        System.out.println("im in ing");
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Ingredient> ingredients = new ArrayList<>();
        // Step 1: Establishing a this.conn
        try (

                // Step 2:Create a statement using this.conn object
                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_INGREDIENTS);) {
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                Integer ingID = rs.getInt("ingID");
                Integer quantity = rs.getInt("quantity");
                String image = rs.getString("image");
                String unit = rs.getString("unit");
                Boolean expandable = rs.getBoolean("expandable");

                ingredients.add(new Ingredient(ingID, name, image, unit, quantity, expandable));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return ingredients;
    }

    public List<Ingredient> selectCustomizableIngredients(int id) {
        System.out.println("im in ing");
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Ingredient> ingredients = new ArrayList<>();
        // Step 1: Establishing a this.conn
        try (

                // Step 2:Create a statement using this.conn object
                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_CUSTOMIZABLE_INGREDIENTS);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "fixed");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("name");
                Integer ingID = rs.getInt("ingID");
                Integer quantity = rs.getInt("quantity");
                Integer minimum = rs.getInt("min");
                Integer proteinphg = rs.getInt("proteinphg");
                Integer caloriesphg = rs.getInt("calphg");
                Integer carbsphg = rs.getInt("carbsphg");
                Integer fatsphg = rs.getInt("fatsphg");
                Integer maximum = rs.getInt("max");
                Integer weightperunit = rs.getInt("weight");
                Integer priceperquantity = rs.getInt("ppq");
                String image = rs.getString("image");
                String unit = rs.getString("unit");
                String type = rs.getString("type");


                ingredients.add(new Ingredient(ingID, name, unit, image, proteinphg, caloriesphg, carbsphg, fatsphg, priceperquantity, weightperunit, quantity, type, minimum, maximum));
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return ingredients;
    }

    public Ingredient selectIngredient(int id) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        Ingredient ingredient = null;
        // Step 1: Establishing a this.conn
        try (

                // Step 2:Create a statement using this.conn object
                PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_INGREDIENT_DETAILS);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            List<String> benefits = new ArrayList<>();

            String name = null;
            String description = null;
            String image = null;
            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                name = rs.getString("name");
                System.out.println(name);
                image = rs.getString("image");
                description = rs.getString("description");

                benefits.add(rs.getString("benefit"));

                System.out.println(description);


            }

            ingredient = new Ingredient(id, name, image, description, benefits);
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return ingredient;
    }


    private void printSQLException(SQLException e) {
    }


}
