package Manager.Ingredients;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ingredientdao {

    private Connection conn;
    private static String insertIngredient = "INSERT INTO ingredient (itemcode,name,unit,image,description,carbsphg,calphg,proteinphg,type,fatsphg,expandable) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static String insertStock = "insert into stock (ingID,min,max,normal,quantity) values(?,?,?,?,0)";
    private static String updateStockLevels = "update stock set min = ? ,normal = ?,max = ? where ingID = ?";
    private static String deleteIngredientsQuery = "delete from ingredient where ingID = ?";

    private static String insertUnitsQuery = "insert into ingredientweight(ingID,unit,weight) values (?,?,?)";

    public ingredientdao(){
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ingredient> read() throws SQLException {
        List <ingredient> ingredients = new ArrayList<>();
//        String query = "SELECT ingID,itemcode,name,image,unit from ingredient";
        String query = "SELECT *, (s.quantity/s.max) as percent from ingredient i JOIN stock s on i.ingID = s.ingID order by percent";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()){
            int ingId = rs.getInt("ingID");
            String itemcode = rs.getString("itemcode");
            String name = rs.getString("name");
            String image = rs.getString("image");
            String unit = rs.getString("unit");
//            ingredients.add(new ingredient(ingId,itemcode,name,image,unit));
            String description = rs.getString("description");
            int carbpg  = rs.getInt("carbsphg");
            int calpg   = rs.getInt("calphg");
            int proteinpg= rs.getInt("proteinphg");
            String type = rs.getString("type");
            int min     = rs.getInt("min");
            int max     = rs.getInt("max");
//            int max     = 20;
            int normal  = rs.getInt("normal");
            int quantity= rs.getInt("quantity");

            ingredients.add(new ingredient(ingId,itemcode,name,image,unit,description,carbpg,calpg,proteinpg,type,min,max,normal,quantity));
        }
        return ingredients;
    }

    public List<ingredient> readbytype(String type) throws SQLException {
        List <ingredient> ingredients = new ArrayList<>();
        String query = "SELECT ingID,itemcode,name,image,unit from ingredient WHERE type=?";
        PreparedStatement stm = this.conn.prepareStatement(query);
        stm.setString(1,type);
        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int ingId = rs.getInt("ingID");
            String itemcode = rs.getString("itemcode");
            String name = rs.getString("name");
            String image = rs.getString("image");
            String unit = rs.getString("unit");
            ingredients.add(new ingredient(ingId,itemcode,name,image,unit));
        }
        return ingredients;
    }


    public int addIngredients(ingredient i){
        try{
//            itemcode,name,unit,image,description,carbpg,calpg,proteinpg,type
            this.conn.setAutoCommit(false);
            PreparedStatement st = this.conn.prepareStatement(insertIngredient,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,i.getIngcode());
            st.setString(2,i.getIngname());
            st.setString(3,i.getUnit());
            st.setString(4,i.getImage());
            st.setString(5,i.getDescription());
            st.setInt(6,i.getCarbpg());
            st.setInt(7,i.getCalpg());
            st.setInt(8,i.getProteinpg());
            st.setString(9,i.getType());
            st.setInt(10,i.getFatspg());
            st.setInt(11,i.getExpandable());

            st.executeUpdate();
            ResultSet keyR = st.getGeneratedKeys();
            int ingID = 0;
            while (keyR.next()){
                ingID = keyR.getInt(1);
            }
            PreparedStatement stockQ = this.conn.prepareStatement(insertStock);
            stockQ.setInt(1,ingID);
            stockQ.setInt(2,i.getMin());
            stockQ.setInt(3,i.getMax());
            stockQ.setInt(4,i.getNormal());
            stockQ.executeUpdate();

            List<Units> unitsList = i.getUnitsList();
            if(!unitsList.isEmpty()){
                PreparedStatement insertUnitsQ = this.conn.prepareStatement(insertUnitsQuery);
                for (Units u:unitsList) {
                    insertUnitsQ.setInt(1,ingID);
                    insertUnitsQ.setString(2,u.getUnit());
                    insertUnitsQ.setDouble(3,u.getRatio());
                    insertUnitsQ.addBatch();
                }
                insertUnitsQ.executeBatch();
            }
            System.out.println("commited Insert");
            this.conn.commit();
            this.conn.setAutoCommit(true);
            return 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if(this.conn!=null){
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            return 0;
        }
    }

    public int updateStockLevels(ingredient i){
        try {
            PreparedStatement updateStockingQ = this.conn.prepareStatement(updateStockLevels);
            updateStockingQ.setInt(1,i.getMin());
            updateStockingQ.setInt(2,i.getNormal());
            updateStockingQ.setInt(3,i.getMax());
            updateStockingQ.setInt(4,i.getIngid());
            updateStockingQ.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteIngredients(int id){
        try {
            PreparedStatement deleteIngredientsQ = this.conn.prepareStatement(deleteIngredientsQuery);
            deleteIngredientsQ.setInt(1,id);
            return deleteIngredientsQ.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}







