package Manager.Manager_Dish;

import User.ConnectionFactory.DB;
import Manager.dishes.hasIngredient;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDAO {
    private Connection conn;
    static private String readDishQuery = "SELECT * FROM dish WHERE enabled = 1 OR enabled = 0";
//    static private String readDishRequestQuery = "SELECT * FROM dish WHERE approvalStatus = \"\"";
    static private String readDishRequestQuery = "SELECT * FROM dish WHERE  NOT (enabled = 1 or enabled = 0)";
//    static private String readDishbyIDQuery = "SELECT * FROM dish WHERE ddishID = ?";
    static private String readDishbyIDQuery = "SELECT * FROM dish WHERE dishID = ?";
//    static private String getDishStatus = "SELECT enabled FROM dish WHERE ddishid = ?";
    static private String getDishStatus = "SELECT enabled FROM dish WHERE dishid = ?";
//    static private String deleteDishQuery = "DELETE FROM dish WHERE ddishid = ?";
    static private String deleteDishQuery = "DELETE FROM dish WHERE dishid = ?";
//    static private String updateEnabled = "UPDATE dish SET enabled = ? WHERE ddishid = ?";
    static private String updateEnabled = "UPDATE dish SET enabled = ? WHERE dishid = ?";

    static private String readUpdatedDishQuery = "SELECT * FROM updatedish WHERE dishid = ?";

    static private String deleteUpdateDishQuery = "DELETE FROM updatedish WHERE dishid = ?";

    static private String updateDishQuery = "UPDATE dish SET dishCode = ?, name =?, description = ?,estTime = ?,enabled = 1,price =?,image = ? WHERE dishid=?";

    static private String deleteAllingredients = "DELETE FROM hasingredient WHERE dishid =?";

    static private String insertHasIngredient = "INSERT INTO hasingredient VALUES (?,?,?,?,?,?,?,?)";




    public DishDAO(){
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Dish> readDish() throws SQLException {
      List<Dish> disharray = new ArrayList<>();
      Statement st = this.conn.createStatement();
      ResultSet rs = st.executeQuery(readDishQuery);
      while(rs.next()){
          int dishID        = rs.getInt("dishID");
//          int dishID        = rs.getInt("ddishID");
          String dishName   = rs.getString("name");
          int defaultPrice  = rs.getInt("price");
//          int defaultPrice  = rs.getInt("defaultPrice");
          String image      = rs.getString("image");

          disharray.add(new Dish(dishID,dishName,defaultPrice,image));
      }
      return  disharray;
    }

    public Dish readDishByID(int id) throws SQLException {
        Dish dish = null;
        PreparedStatement st = this.conn.prepareStatement(readDishbyIDQuery);
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
//            int dishid =rs.getInt("ddishID");
            int dishid =rs.getInt("dishID");
            String dishname =rs.getString("name");
            String dishcode =rs.getString("dishcode");
            String image=rs.getString("image");
            String description = rs.getString("description");
            int price = rs.getInt("price");
            int estTime = rs.getInt("estTime");
            System.out.println("");
            System.out.println("");
            System.out.println(estTime);
            System.out.println("");
            System.out.println("");


//            String ingQuery = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE ddish = ? and h.ingID=i.ingID ";
            String ingQuery = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE dishID = ? and h.ingID=i.ingID ";
            PreparedStatement st2 = this.conn.prepareStatement(ingQuery);
            st2.setInt(1 , dishid);
            ResultSet rs2 = st2.executeQuery();
            List<hasIngredient> ingredients = new ArrayList<>();
            while (rs2.next()){
                int ingID = rs2.getInt("I.ingID");
                String itemcode = rs2.getString("itemcode");
                String type = rs2.getString("type");
                String name = rs2.getString("name");
                String ingimage = rs2.getString("image");
//                int carbpg = rs2.getInt("carbpg");
//                int calpg  = rs2.getInt("calpg");
//                int proteinpg  = rs2.getInt("proteinpg");
                int carbpg = rs2.getInt("carbsphg");
                int calpg  = rs2.getInt("calphg");
                int proteinpg  = rs2.getInt("proteinphg");
                int min  = rs2.getInt("min");
                int max  = rs2.getInt("max");
                int defaultValue  = rs2.getInt("defaultValue");
                ingredients.add(new hasIngredient(ingID,type,min,defaultValue,max,itemcode,name,ingimage,carbpg,calpg,proteinpg));
            }
//            System.out.println(rs.getInt("enabled"));
            int enabled = rs.getInt("enabled");
            if(enabled!=-2){
                dish = new Dish(dishid,dishname,image,dishcode,description,ingredients,price,estTime);
            }else{
                Dish updatedDish = readUpdatedDish(dishid);
                dish = new Dish(dishid,dishname,image,dishcode,description,ingredients,updatedDish,price,estTime);
            }

        }
        return dish;
    }

    public Dish readUpdatedDish(int id) throws SQLException {
        System.out.println("******came to read updated*******");
        System.out.println("******came to read updated*******");
        System.out.println("******came to read updated*******");
        Dish dish = null;
        PreparedStatement st = this.conn.prepareStatement(readUpdatedDishQuery);
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int dishid =rs.getInt("dishID");
            String dishname =rs.getString("name");
            String dishcode =rs.getString("dishcode");
            String image=rs.getString("image");
            String description = rs.getString("description");
            int price   = rs.getInt("price");
            int estTime = rs.getInt("estTime");

//            String ingQuery = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE ddish = ? and h.ingID=i.ingID ";
            String ingQuery = "SELECT * FROM updatehasingredients h INNER JOIN ingredient i WHERE dishID = ? and h.ingID=i.ingID ";
            PreparedStatement st2 = this.conn.prepareStatement(ingQuery);
            st2.setInt(1 , dishid);
            ResultSet rs2 = st2.executeQuery();
            List<hasIngredient> ingredients = new ArrayList<>();
            while (rs2.next()){
                int ingID = rs2.getInt("I.ingID");
                String itemcode = rs2.getString("itemcode");
                String type = rs2.getString("type");
                String name = rs2.getString("name");
                String ingimage = rs2.getString("image");
                int carbpg = rs2.getInt("carbsphg");
                int calpg  = rs2.getInt("calphg");
                int proteinpg  = rs2.getInt("proteinphg");
                int min  = rs2.getInt("min");
                int max  = rs2.getInt("max");
                int defaultValue  = rs2.getInt("defaultValue");
                ingredients.add(new hasIngredient(ingID,type,min,defaultValue,max,itemcode,name,ingimage,carbpg,calpg,proteinpg));
            }
//            System.out.println(rs.getInt("enabled"));
            dish = new Dish(dishid,dishname,image,dishcode,description,ingredients,price,estTime);

        }
        return dish;
    }

    public List<Dish> readDishRequest() throws SQLException {
        List<Dish> disharray = new ArrayList<>();
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(readDishRequestQuery);
        while(rs.next()){
//            int dishID        = rs.getInt("ddishID");
            int dishID        = rs.getInt("dishID");
            String dishName   = rs.getString("name");
//            int defaultPrice  = rs.getInt("defaultPrice");
            int defaultPrice  = rs.getInt("price");
            String image      = rs.getString("image");
            int enabled       = rs.getInt("enabled");
            String request = " ";
            if(enabled==-1){
               request = "Add";
            }else if(enabled==-2){
                request ="Update";
            }else if(enabled==-3){
                request = "Delete";
            }
            disharray.add(new Dish(dishID,dishName,defaultPrice,image,request));
        }
        return  disharray;
    }

    public int deleteDish(int id){
        try{
            PreparedStatement st = this.conn.prepareStatement(deleteDishQuery);
            st.setInt(1,id);
            return st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public String checkCurrentEnabledStatus(int id){
        try{
            PreparedStatement st = this.conn.prepareStatement(getDishStatus);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            int x=0;
            while(rs.next()){
                x = rs.getInt("enabled");
            }
            if(x==-1){
               return "Add";
            }else if(x==-2){
                return "Update";
            }else if(x==-3){
                return "Delete";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

//    public int managerDeny(int id){
//        System.out.println(checkCurrentEnabledStatus(id));
//        String dishRequest = checkCurrentEnabledStatus(id);
//        if (dishRequest=="Add"){
//            return deleteDish(id);
//        }else if(dishRequest=="Update"||dishRequest=="Delete"){
//            return updateStatus(id,1);
//        }
//        return 0;
//    }

    public int updateStatus(int id,int status){
        try{
            PreparedStatement st = this.conn.prepareStatement(updateEnabled);
            st.setInt(1,status);
            st.setInt(2,id);
            PreparedStatement st2 = this.conn.prepareStatement(deleteUpdateDishQuery);
            st2.setInt(1,id);
            st.executeUpdate();
            return st2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public int updateDishtoUpdated(int id){
        try {
            Dish ud = readUpdatedDish(id);
            this.conn.setAutoCommit(false);
            PreparedStatement updateDish = this.conn.prepareStatement(updateDishQuery);
//          "UPDATE dish SET dishCode = ?, name =?, description = ?,estTime = ?,enabled = 1,price =?,image = ? WHERE dishid=?";
            updateDish.setString(1,ud.getDishcode());
            updateDish.setString(2,ud.getDishName());
            updateDish.setString(3,ud.getDescription());
            updateDish.setInt(4,ud.getEstimatedTime());
            updateDish.setInt(5,ud.getDefaultPrice());
            updateDish.setString(6,ud.getImage());
            updateDish.setInt(7,id);
            updateDish.executeUpdate();

            PreparedStatement removeAllIngredients = this.conn.prepareStatement(deleteAllingredients);
            removeAllIngredients.setInt(1,id);
            removeAllIngredients.executeUpdate();

            List<hasIngredient> ingredients = ud.getIngredients();
            PreparedStatement insertIngredient = this.conn.prepareStatement(insertHasIngredient);
            for( hasIngredient i : ingredients){
                insertIngredient.setInt(1,id);
                insertIngredient.setInt(2,i.getIngID());
                insertIngredient.setInt(3,0);
//                insertIngredient.setInt(3,ppq);
                insertIngredient.setInt(4,i.getDefaultv());
//                insertIngredient.setString(5,i.getUnit());
                insertIngredient.setString(5,"g");
                insertIngredient.setString(6,i.getType());
                insertIngredient.setInt(7,i.getMinimum());
                insertIngredient.setInt(8,i.getMaximum());
                insertIngredient.addBatch();
            }
            insertIngredient.executeBatch();
            PreparedStatement deleteUpdatedDish = this.conn.prepareStatement(deleteUpdateDishQuery);
            deleteUpdatedDish.setInt(1,id);
            deleteUpdatedDish.executeUpdate();
            this.conn.commit();
            this.conn.setAutoCommit(true);
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (this.conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }
        return 0;
    }

}
