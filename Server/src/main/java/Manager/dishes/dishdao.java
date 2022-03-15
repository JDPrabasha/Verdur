package Manager.dishes;

import Manager.Dao.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dishdao {
    private Connection conn;

    static String insertDishQuery = "INSERT INTO dish (dishcode,name,description,image,estTime,enabled) VALUES (?,?,?,?,?,?)";
    static String insertIngredients = "INSERT INTO hasingredient (ddish,ingID,min,max,defaultValue,type) VALUES (?,?,?,?,?,?)";

    public dishdao(DB db){
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<dish> read() throws SQLException {
        List <dish> dishes = new ArrayList<dish>();
        String query = "SELECT * from dish";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()){
            List<hasIngredient> ingredients = new ArrayList<>();

            String dishid =rs.getString("ddishID");
            String dishname =rs.getString("name");
            String dishcode =rs.getString("dishcode");
            String image=rs.getString("image");

            String ingQuery = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE ddish = ? and h.ingID=i.ingID ";
            PreparedStatement st2 = this.conn.prepareStatement(ingQuery);
            st2.setString(1 , dishid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()){
                int ingID = rs2.getInt("I.ingID");
                String itemcode = rs2.getString("itemcode");
                String type = rs2.getString("type");
                String name = rs2.getString("name");
                String ingimage = rs2.getString("image");
                int carbpg = rs2.getInt("carbpg");
                int calpg  = rs2.getInt("calpg");
                int proteinpg  = rs2.getInt("proteinpg");
                int min  = rs2.getInt("min");
                int max  = rs2.getInt("max");
                int defaultValue  = rs2.getInt("defaultValue");


                ingredients.add(new hasIngredient(ingID,type,min,defaultValue,max,itemcode,name,image,carbpg,calpg,proteinpg));
            }

            dishes.add(new dish(dishid,dishname,dishcode,image,ingredients));


        }
        return dishes;

    }

    public List<dish> readname(String s) throws SQLException {
        List <dish> dishes = new ArrayList<>();
        String query = "SELECT * from dish WHERE name LIKE ?";
        PreparedStatement st = this.conn.prepareStatement(query);
        st.setString(1, s);
        ResultSet rs = st.executeQuery();

        while (rs.next()){


            String dishid =rs.getString("ddishID");
            String dishname =rs.getString("name");
            String dishcode =rs.getString("dishcode");
            String image=rs.getString("image");
            dishes.add(new dish(dishid,dishname,dishcode,image));


        }
        return dishes;
    }

public dish readbyid(String id) throws SQLException {
        String query = "SELECT * from dish WHERE dish_id = "+"'"+id+"'";
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        dish dishes = null;
    while (rs.next()){

        String dishid =rs.getString("dish_id");
        String dishname =rs.getString("dish_name");
        int defaultprice =rs.getInt("default_price");
        String estimatedtime =rs.getString("estimated_time");

        dishes= new dish(dishid,dishname,defaultprice,estimatedtime);


    }

        return dishes;
    }


    public int add(dish d) {
        int rs;
        PreparedStatement st = null;
        int[] rsset;
        try {
//            conn.setAutoCommit(false);
            st = this.conn.prepareStatement(insertDishQuery, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, d.getDishcode());
            st.setString(2, d.getDishname());
            st.setString(3, d.getDescription());
            st.setString(4, d.getImage());
            st.setString(5, d.getEstimatedtime());
            st.setString(6, "-1");
            rs = st.executeUpdate();
            ResultSet rs1 = st.getGeneratedKeys();
            int dishID = 0;
            while (rs1.next()) {
                dishID = rs1.getInt(1);
            }
            System.out.println("DishID " + dishID);
            List<hasIngredient> ingredients = d.getIngredients();
            PreparedStatement insertingStatement = this.conn.prepareStatement(insertIngredients);


            for (hasIngredient i : ingredients) {
                insertingStatement.setInt(1, dishID);
                insertingStatement.setInt(2, i.getIngID());
                insertingStatement.setInt(3, i.getMinimum());
                insertingStatement.setInt(4, i.getMaximum());
                insertingStatement.setInt(5, i.getDefaultv());
                insertingStatement.setString(6, i.getType());
                insertingStatement.addBatch();
//                System.out.println("ID " + i.getIngID()+"Default "+i.getDefaultv()+"Max"+i.getMaximum());
            }
            rsset = insertingStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (conn != null) {
                try {
                    System.err.print("Insertion is being rolled back");
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }
        for(int x : rsset){
            if(x==0){
                return 0;
            }
        }
        return rs;
//        List<hasIngredient> ingredients = d.getIngredients();
//        for(hasIngredient i : ingredients){
//            System.out.println(i.getIngID());
//        }
//
//        return 0;
    }
}
