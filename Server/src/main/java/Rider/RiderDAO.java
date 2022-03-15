package Rider;

import Core.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RiderDAO {
    private static final String GET_RIDER_DETAILS = " select * from rider r where r.riderID = ?";
    private static final String GET_MONTHLY_ORDERS = "select count(*) as total from orders o join delivery d on d.deliveryID =o.deliveryID where o.`timestamp` between '2010-01-01' and LAST_DAY('2030-01-01') and d.riderID =?";
    private static final String TOGGLE_AVAILIBILE = "update rider set status = ? where riderID=?";
    private static final String GET_RIDER_FOR_ORDER = "select e.empID, u.contact from orders o join  rider r on r.riderID = o.riderID join employee e on e.empID =r.riderID join user u on u.userID =e.userID where r.riderID = ?";

    public Rider getRiderDetails(int id) {
        Rider rider = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDER_DETAILS);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                int due = rs.getInt("totalPayable");
                String status = rs.getString("status");
                rider = new Rider(due, status);

            }

        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }

        return rider;
    }

    public int getOderCount(int id) {
        Rider rider = null;
        int total = 0;
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MONTHLY_ORDERS);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                total = rs.getInt("total");


            }

        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }

        return total;
    }

    private void printSQLException(SQLException e) {
    }

    public void toggleAvailibility(int id) {
        String status = "";
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDER_DETAILS);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            PreparedStatement secondStatement = connection.prepareStatement(GET_RIDER_DETAILS);
            if (rs.next()) {

                status = rs.getString("status");


            }

            if (Objects.equals(status, "available")) {
                secondStatement.setString(1, "unavailable");
            } else {
                secondStatement.setString(1, "available");

            }
            secondStatement.setInt(1, id);
            secondStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
    }
}


