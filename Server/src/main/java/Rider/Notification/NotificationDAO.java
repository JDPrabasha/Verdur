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
    private static final String GET_CUSTOMER_NOTIFICATIONS = " select * from customernotification where custID = ?";
    private static final String GET_RIDER_NOTIFICATIONS = " select * from ridernotification where riderID = ?";

    public List<Notification> getNotifications(int id, String role) {
        List<Notification> notifications = new ArrayList<>();

        String query = "";
        if (Objects.equals(role, "customer")) {
            query = GET_CUSTOMER_NOTIFICATIONS;
            try (Connection connection = DB.initializeDB();

                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();


                while (rs.next()) {
                    String timestamp = rs.getString("timestamp");
                    String text = rs.getString("status");
                    id = rs.getInt("id");
                    notifications.add(new Notification(id, text, timestamp));

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            query = GET_RIDER_NOTIFICATIONS;

        }
        // Step 1: Establishing a Connection


        return notifications;
    }


    private void printSQLException(SQLException e) {
    }
}


