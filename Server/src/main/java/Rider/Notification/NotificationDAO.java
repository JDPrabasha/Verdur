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
    private static final String DELETE_NOTIFICATIONS = "select count(*) as total from orders o join delivery d on d.deliveryID =o.deliveryID where o.`timestamp` between '2010-01-01' and LAST_DAY('2030-01-01') and d.riderID =?";

    public List<Notification> getNotifications(int id, String role) {
        List<Notification> notifications = new ArrayList<>();

        String query = "";
        if (Objects.equals(role, "customer")) {
            query = GET_CUSTOMER_NOTIFICATIONS;
        } else {
            query = GET_RIDER_NOTIFICATIONS;
        }
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                String timestamp = rs.getString("timestamp");
                String text = rs.getString("description");
                id = rs.getInt("id");
                notifications.add(new Notification(id, text, timestamp));

            }

        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }

        return notifications;
    }


    private void printSQLException(SQLException e) {
    }
}


