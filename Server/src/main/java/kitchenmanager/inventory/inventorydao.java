package kitchenmanager.inventory;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class inventorydao {

    private Connection conn;


    public inventorydao(DB db) {
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<inventory> read() throws SQLException {
        List<inventory> stockitem = new ArrayList<inventory>();
        String query = "SELECT * FROM stock JOIN ingredient ON ingredient.ingID=stock.ingID JOIN restockrequest ON restockrequest.ingID = stock.ingID JOIN restockorder ON restockorder.restockID=restockrequest.restockID";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {

            int ingid = rs.getInt("ingID");
            String ingcode = rs.getString("itemCode");
            String ingname = rs.getString("name");
            String ingtype = rs.getString("type");
            String image = rs.getString("image");
            String restockdate = rs.getString("deliveryDate");
            int quantity = rs.getInt("quantity");
            int maxlevel = rs.getInt("max");
            int safelevel = rs.getInt("normal");
            int restocklevel = rs.getInt("min");
            String unit = rs.getString("unit");

            stockitem.add(new inventory(ingid, ingcode, ingtype, ingname, quantity, maxlevel, safelevel, restocklevel, image, restockdate, unit));


        }
        return stockitem;


    }

    public int editstock(inventory i) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE stock SET quantity =? WHERE ingid = ?";
        int ingid = i.getIngid();
        int stock = i.getQuantity();
        PreparedStatement st = this.conn.prepareStatement(quary);
        st.setInt(1, stock);
        st.setInt(2, ingid);
        return st.executeUpdate();
    }

}
