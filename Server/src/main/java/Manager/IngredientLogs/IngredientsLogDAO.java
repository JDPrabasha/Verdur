package Manager.IngredientLogs;

import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientsLogDAO {
    private Connection conn;

    static private String getLogsQ = "SELECT * FROM stockupdatelog";
    static private String getName = "SELECT name from ingredient where ingID = ?";

    public IngredientsLogDAO(){
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<IngredientsLog> getAllLogs(){
        try {
            List<IngredientsLog> allLogs = new ArrayList<>();
            PreparedStatement getAllLogsSt = this.conn.prepareStatement(getLogsQ);
            ResultSet getAllLogsR = getAllLogsSt.executeQuery();
            while(getAllLogsR.next()){
                int logID = getAllLogsR.getInt("logID");
                int ingID = getAllLogsR.getInt("ingID");
                String ingName = getIngName(ingID);
                int quantityChanged = getAllLogsR.getInt("quantity");
                String timestamp = getAllLogsR.getString("timestamp");

                allLogs.add(new IngredientsLog(logID,ingID,ingName,quantityChanged,0,timestamp));
            }
            return allLogs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIngName(int ingID){
        try {
            PreparedStatement getNameSt = this.conn.prepareStatement(getName);
            getNameSt.setInt(1,ingID);
            ResultSet getNameR = getNameSt.executeQuery();
            if(getNameR.next()){
                return getNameR.getString("name");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
