package Rider;

import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class RiderDAO {
    private static final String GET_RIDER_DETAILS = " select * from rider r where r.riderID = ?";
    private static final String GET_MONTHLY_ORDERS = "select count(*) as total from orders o join delivery d on d.deliveryID =o.deliveryID where o.`timestamp` between '2010-01-01' and LAST_DAY('2030-01-01') and d.riderID =?";
    private static final String TOGGLE_AVAILIBILE = "update rider set status = ? where riderID=?";
    private static final String GET_RIDER_FOR_ORDER = "select d.riderID,CONCAT(u.firstName,\" \",+u.lastName) as name,u.contact from delivery d join orders o on d.deliveryID = o.deliveryID join employee e on d.riderID = e.empID join user u on e.userID = u.userID where o.status=\"delivering\" and o.custID = ?";

    private static final String SELECT_COMPLETED_ORDERS = " select count(*) as total from orders o join delivery d on o.deliveryID = d.deliveryID where d.riderID = ? and o.completed = 1 and o.timestamp >= ? ";

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

    public Rider getDeliveringRider(int id) {
        Rider rider = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDER_FOR_ORDER);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                rider = new Rider(name, contact);

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

    public int getCheckpointCount(int id) {
        int orderCount = 0;
        LocalDate monthBegin = LocalDate.now().withDayOfMonth(1);
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPLETED_ORDERS);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(monthBegin));

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            if (rs.next()) {

                orderCount = rs.getInt("total");


            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return orderCount;

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
            if (rs.next()) {

                status = rs.getString("status");


            }
            PreparedStatement secondStatement = connection.prepareStatement(TOGGLE_AVAILIBILE);


            if (Objects.equals(status, "available")) {
                secondStatement.setString(1, "unavailable");
            } else {
                secondStatement.setString(1, "available");

            }
            secondStatement.setInt(2, id);
            System.out.println(secondStatement);
            secondStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
    }
}


