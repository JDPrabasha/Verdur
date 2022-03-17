package Manager.Manager_Notification;

import User.ConnectionFactory.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerNotificationDAO {
    private Connection conn;

    private String queryNotification        = "select * from managernotifications";
    private String deleteNotificationQuery  = "delete from managernotifications where notificationID = ?";

    public ManagerNotificationDAO(){
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ManagerNotification> getmanagerNotifications(){
        List<ManagerNotification> notifications = new ArrayList<>();
        try {
            Statement notificationQ = this.conn.createStatement();
            ResultSet rs = notificationQ.executeQuery(queryNotification);
            while (rs.next()){
                int id = rs.getInt("notificationID");
                String type = rs.getString("type");
                String description = rs.getString("description");
                int targetID = rs.getInt("targetID");
                 notifications.add(new ManagerNotification(id,type,description,targetID));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return notifications;
    }

    public int deleteNotification(int id){
        try {
            PreparedStatement deleteNotificationQ = this.conn.prepareStatement(deleteNotificationQuery);
            deleteNotificationQ.setInt(1,id);
            return deleteNotificationQ.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
