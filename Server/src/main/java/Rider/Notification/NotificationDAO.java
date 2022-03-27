package Rider.Notification;


import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationDAO {
    private static final String GET_CUSTOMER_NOTIFICATIONS = " select * from customernotification where custID = ? order by timestamp desc";
    private static final String GET_RIDER_NOTIFICATIONS = " select * from ridernotification where riderID = ? order by timestamp desc";

    private Connection conn;

    public NotificationDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getNotifications(int id, String role) {
        List<Notification> notifications = new ArrayList<>();

        String query = "";
        if (Objects.equals(role, "customer")) {
            query = GET_CUSTOMER_NOTIFICATIONS;
            try {
                PreparedStatement preparedStatement = this.conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();


                while (rs.next()) {
                    String timestamp = rs.getString("timestamp");
                    String text = rs.getString("status");
                    id = rs.getInt("id");
                    notifications.add(new Notification(id, text, timestamp));

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            query = GET_RIDER_NOTIFICATIONS;
            try (

                    PreparedStatement preparedStatement = this.conn.prepareStatement(query);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();


                while (rs.next()) {
                    String timestamp = rs.getString("timestamp");
                    String text = rs.getString("orderCount");
                    id = rs.getInt("id");
                    notifications.add(new Notification(id, text, timestamp));

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


        return notifications;
    }


    private void printSQLException(SQLException e) {
    }
}


