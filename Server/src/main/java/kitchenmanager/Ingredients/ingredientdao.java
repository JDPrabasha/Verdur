package kitchenmanager.Ingredients;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ingredientdao {

    private Connection conn;

    public ingredientdao(DB db) {
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ingredient> read() throws SQLException {
        List<ingredient> ingredients = new ArrayList<>();
        String query = "SELECT ingID,itemcode,name,image,unit from ingredient";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int ingId = rs.getInt("ingID");
            String itemcode = rs.getString("itemcode");
            String name = rs.getString("name");
            String image = rs.getString("image");
            String unit = rs.getString("unit");
            ingredients.add(new ingredient(ingId, itemcode, name, image, unit));
        }
        return ingredients;
    }

    public List<ingredient> readbytype(String type) throws SQLException {
        List<ingredient> ingredients = new ArrayList<>();
        String query = "SELECT ingID,itemcode,name,image,unit from ingredient WHERE type=?";
        PreparedStatement stm = this.conn.prepareStatement(query);
        stm.setString(1, type);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int ingId = rs.getInt("ingID");
            String itemcode = rs.getString("itemcode");
            String name = rs.getString("name");
            String image = rs.getString("image");
            String unit = rs.getString("unit");
            ingredients.add(new ingredient(ingId, itemcode, name, image, unit));
        }
        return ingredients;
    }


}







