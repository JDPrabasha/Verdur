package kitchenmanager.dishes;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dishdao {
    private Connection conn;

    //    static String insertDishQuery = "INSERT INTO dish (dishcode,name,description,image,estTime,enabled) VALUES (?,?,?,?,?,?)";
//    static String insertIngredients = "INSERT INTO hasingredient (ddish,ingID,min,max,defaultValue,type) VALUES (?,?,?,?,?,?)";
    static String insertDishQuery = "INSERT INTO dish (dishCode,name,description,image,estTime,enabled,price) VALUES (?,?,?,?,?,?,0)";
    static String insertIngredients = "INSERT INTO hasingredient (dishID,ingID,min,max,defaultValue,type) VALUES (?,?,?,?,?,?)";

    public dishdao(DB db) {
        try {
            this.conn = db.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<dish> read() throws SQLException {
        List<dish> dishes = new ArrayList<dish>();
        String query = "SELECT * from dish WHERE enabled != -3";
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            List<hasIngredient> ingredients = new ArrayList<>();

            int dishid = rs.getInt("dishID");
            String dishname = rs.getString("name");
            String dishcode = rs.getString("dishCode");
            String image = rs.getString("image");
            int enable = rs.getInt("enabled");

//            String ingQuery = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE ddish = ? and h.ingID=i.ingID ";
            String ingQuery = "SELECT * FROM hasingredient h INNER JOIN ingredient i join stock s on s.ingID =i.ingID WHERE dishID = ? and h.ingID=i.ingID";
            PreparedStatement st2 = this.conn.prepareStatement(ingQuery);
            st2.setInt(1, dishid);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                int ingID = rs2.getInt("I.ingID");
                String itemcode = rs2.getString("itemCode");
                String type = rs2.getString("type");
                String name = rs2.getString("name");
//                String ingimage = rs2.getString("image");
                int carbpg = rs2.getInt("carbsphg");
                int calpg = rs2.getInt("calphg");
                int proteinpg = rs2.getInt("proteinphg");
                int min = rs2.getInt("min");
                int max = rs2.getInt("max");
                int defaultValue = rs2.getInt("defaultValue");
                int stock = rs2.getInt("s.quantity");
                int restocklevel = rs2.getInt("s.min");


                ingredients.add(new hasIngredient(ingID, type, min, defaultValue, max, itemcode, name, image, carbpg, calpg, proteinpg, stock, restocklevel));
            }

            dishes.add(new dish(dishid, dishname, dishcode, image, enable, ingredients));


        }
        return dishes;

    }

    public List<dish> readname(String s) throws SQLException {
        List<dish> dishes = new ArrayList<>();
        String query = "SELECT * from dish WHERE name LIKE ?";
        PreparedStatement st = this.conn.prepareStatement(query);
        st.setString(1, s);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {


            int dishid = rs.getInt("dishID");
            String dishname = rs.getString("name");
            String dishcode = rs.getString("dishCode");
            String image = rs.getString("image");
            dishes.add(new dish(dishid, dishname, dishcode, image));


        }
        return dishes;
    }


    public int add(dish d) {
        int rs;
        PreparedStatement st = null;
        int[] rsset;
        try {
            conn.setAutoCommit(false);
            st = this.conn.prepareStatement(insertDishQuery, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, d.getDishcode());
            st.setString(2, d.getDishname());
            st.setString(3, d.getDescription());
            st.setString(4, d.getImage());
            st.setInt(5, d.getEstimatedtime());
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
            this.conn.commit();
            this.conn.setAutoCommit(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (conn != null) {
                try {
                    System.err.print("Insertion is being rolled back");
                    conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }
        for (int x : rsset) {
            if (x == 0) {
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

    public List<dish> readbyingredient(String id) throws SQLException {
//        String query = "SELECT * from dish WHERE  = "+"'"+id+"'";
//        String query = "SELECT * from dish d INNER JOIN hasingredient h ON d.ddishID = h.ddish WHERE h.ingID = "+"'"+id+"'";
        String query = "SELECT * from dish d INNER JOIN hasingredient h ON d.dishID = h.dishID WHERE h.ingID = " + "'" + id + "'";
//        String query = "SELECT * from dish d INNER JOIN hasingredient h ON d.dishID = h.dishID WHERE h.ingID = ?";
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<dish> dishes = new ArrayList<>();
        while (rs.next()) {


            int dishid = rs.getInt("dishID");
            String dishname = rs.getString("name");
            String dishcode = rs.getString("dishCode");
            String image = rs.getString("image");
            int status = rs.getInt("enabled");
            int defaultIngAmount = rs.getInt("defaultValue");
            String customizetiontype = rs.getString("type");
            int ingid = rs.getInt("ingID");
            String unit = rs.getString("unit");
//            List<hasIngredient> ingredients = new ArrayList<>();
//            ingredients.add (new hasIngredient(customizetiontype,defaultIngAmount));

            dishes.add(new dish(dishid, dishname, dishcode, image, status, defaultIngAmount, customizetiontype, ingid, unit));


        }

        return dishes;
    }

    public int deletedish(dish d) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE dish SET enabled = ? WHERE dishID = ?";
        int dishid = d.getDishid();
        PreparedStatement st = this.conn.prepareStatement(quary);
        st.setInt(1, -3);
        st.setInt(2, dishid);
        return st.executeUpdate();
    }

    public int enable(dish d) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE dish SET enabled = ? WHERE dishID = ?";
        int dishid = d.getDishid();
        PreparedStatement st = this.conn.prepareStatement(quary);
        st.setInt(1, 1);
        st.setInt(2, dishid);
        System.out.println("enabled dish "+dishid);
        return st.executeUpdate();
    }

    public int dissable(dish d) throws SQLException {
//        String quary = "UPDATE dish SET enabled = ? WHERE ddishID = ?";
        String quary = "UPDATE dish SET enabled = ? WHERE dishID = ?";
        int dishid = d.getDishid();
        PreparedStatement st = this.conn.prepareStatement(quary);
//        st.setInt(1,ingid);
        st.setInt(1, 0);
        st.setInt(2, dishid);
        return st.executeUpdate();
    }

    public int disableall(int ingid) throws SQLException {
        String quary = "update dish d join hasingredient h on h.dishID =d.dishID set d.enabled = 0 where h.ingID =?";
        PreparedStatement st = this.conn.prepareStatement(quary);
        st.setInt(1, ingid);
        System.out.println(ingid);

        return st.executeUpdate();
    }
//    public int enableall(int ingid) throws SQLException {
//        String quary = "update dish d join hasingredient h on h.dishID =d.dishID set d.enabled = 1 where h.ingID =?";
//        PreparedStatement st = this.conn.prepareStatement(quary);
//        st.setInt(1,ingid);
//
//        return st.executeUpdate();
//    }


    public dish readupdatedish(String id) throws SQLException {

//        dish dishitem = null;
        String query = "SELECT * FROM dish WHERE dishID =?";
//        String quary = "UPDATE dish SET enabled = ? WHERE dishID = ?";
        PreparedStatement st = this.conn.prepareStatement(query);
        st.setInt(1, Integer.parseInt(id));
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int dishid = rs.getInt("dishID");
            String dishname = rs.getString("name");
            String dishcode = rs.getString("dishCode");
            String image = rs.getString("image");
            String description = rs.getString("description");

//            String query2 = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE ddish = ? and h.ingID=i.ingID ";
            String query2 = "SELECT * FROM hasingredient h INNER JOIN ingredient i WHERE dishID = ? and h.ingID=i.ingID ";
            PreparedStatement st2 = this.conn.prepareStatement(query2);
            st2.setInt(1, dishid);
            ResultSet rs2 = st2.executeQuery();
            List<hasIngredient> ingredients = new ArrayList<>();
            while (rs2.next()) {
                int ingID = rs2.getInt("I.ingID");
                String itemcode = rs2.getString("itemCode");
                String type = rs2.getString("type");
                String name = rs2.getString("name");
                String ingimage = rs2.getString("image");
                int carbpg = rs2.getInt("carbsphg");
                int calpg = rs2.getInt("calphg");
                int proteinpg = rs2.getInt("proteinphg");
                int min = rs2.getInt("min");
                int max = rs2.getInt("max");
                int defaultValue = rs2.getInt("defaultValue");
                ingredients.add(new hasIngredient(ingID, type, min, defaultValue, max, itemcode, name, ingimage, carbpg, calpg, proteinpg));
            }
            return new dish(dishid, dishname, dishcode, description, image, ingredients);
//            System.out.println("gona");
        }
        return null;
    }

    public int createupdate(dish d) {
        String changeStatus = "Update dish set enabled = -2 where dishID = ?";
        String updateDishInsert = "INSERT INTO updatedish (dishID,dishcode,name,description,image,estTime,enabled,price,approvalStatus) VALUES (?,?,?,?,?,?,?,0,1) on duplicate key update dishcode = ?,name=?,description=?,image=?,estTime=?,enabled=?";
        String clearUpdateHasIngredients = "Delete from updatehasingredients where dishID = ?";
        String updateHasIngredients = "INSERT INTO updatehasingredients (dishID,ingID,min,max,defaultValue,type,ppq,unit) VALUES (?,?,?,?,?,?,0,'g')";
        int rs;
        PreparedStatement st = null;
        int[] rsset;
        try {
            conn.setAutoCommit(false);
            PreparedStatement changeStatusQ = this.conn.prepareStatement(changeStatus);
            changeStatusQ.setInt(1, d.getDishid());
            changeStatusQ.executeUpdate();
            st = this.conn.prepareStatement(updateDishInsert);
            st.setInt(1, d.getDishid());
            st.setString(2, d.getDishcode());
            st.setString(3, d.getDishname());
            st.setString(4, d.getDescription());
            st.setString(5, d.getImage());
            st.setInt(6, d.getEstimatedtime() == null ? 0 : d.getEstimatedtime());
            st.setString(7, "-1");

            st.setString(8, d.getDishcode());
            st.setString(9, d.getDishname());
            st.setString(10, d.getDescription());
            st.setString(11, d.getImage());
            st.setInt(12, d.getEstimatedtime() == null ? 0 : d.getEstimatedtime());
            st.setString(13, "-1");
            rs = st.executeUpdate();

            int dishID = d.getDishid();
            PreparedStatement clearIngredientsQ = this.conn.prepareStatement(clearUpdateHasIngredients);
            clearIngredientsQ.setInt(1, d.getDishid());
            clearIngredientsQ.executeUpdate();

            List<hasIngredient> ingredients = d.getIngredients();
            PreparedStatement insertingStatement = this.conn.prepareStatement(updateHasIngredients);


            for (hasIngredient i : ingredients) {
                insertingStatement.setInt(1, dishID);
                insertingStatement.setInt(2, i.getIngID());
                insertingStatement.setInt(3, i.getMinimum() == null ? 0 : i.getMinimum());
                insertingStatement.setInt(4, i.getMaximum() == null ? 0 : i.getMaximum());
                insertingStatement.setInt(5, i.getDefaultv() == null ? 0 : i.getDefaultv());
                insertingStatement.setString(6, i.getType());
                insertingStatement.addBatch();
//                System.out.println("ID " + i.getIngID()+"Default "+i.getDefaultv()+"Max"+i.getMaximum());
            }
            rsset = insertingStatement.executeBatch();
            this.conn.commit();
            this.conn.setAutoCommit(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (conn != null) {
                try {
                    System.err.print("Insertion is being rolled back");
                    conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }
        for (int x : rsset) {
            if (x == 0) {
                return 0;
            }
        }
        return rs;

//
    }
}




