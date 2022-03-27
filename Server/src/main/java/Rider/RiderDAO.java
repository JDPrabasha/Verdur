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
    private static final String TOGGLE_AVAILIBILE = "update rider set status = ? where riderID=?";
    private static final String GET_RIDER_FOR_ORDER = "select d.riderID,CONCAT(u.firstName,\" \",+u.lastName) as name,u.contact from delivery d join orders o on d.deliveryID = o.deliveryID join employee e on d.riderID = e.empID join user u on e.userID = u.userID where o.status=\"delivering\" and o.custID = ?";
    private static final String SELECT_COMPLETED_ORDERS = " select count(*) as total from orders o join delivery d on o.deliveryID = d.deliveryID where d.riderID = ? and o.completed = 1 and o.timestamp >= ? ";

    private Connection conn;

    public RiderDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Rider getRiderDetails(int id) {
        Rider rider = null;
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(GET_RIDER_DETAILS);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                int due = rs.getInt("totalPayable");
                String status = rs.getString("status");
                rider = new Rider(due, status);

            }

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

        return rider;
    }

    public Rider getDeliveringRider(int id) {
        Rider rider = null;
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(GET_RIDER_FOR_ORDER);
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                rider = new Rider(name, contact);

            }

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

        return rider;
    }


    private void printSQLException(SQLException e) {
    }

    public int getCheckpointCount(int id) {
        int orderCount = 0;
        LocalDate monthBegin = LocalDate.now().withDayOfMonth(1);
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_COMPLETED_ORDERS);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(monthBegin));

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                orderCount = rs.getInt("total");


            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return orderCount;

    }

    public void toggleAvailibility(int id) {
        String status = "";
        try (

                PreparedStatement preparedStatement = this.conn.prepareStatement(GET_RIDER_DETAILS);) {
            conn.setAutoCommit(false);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                status = rs.getString("status");


            }
            PreparedStatement secondStatement = this.conn.prepareStatement(TOGGLE_AVAILIBILE);


            if (Objects.equals(status, "available")) {
                secondStatement.setString(1, "unavailable");
            } else {
                secondStatement.setString(1, "available");

            }
            secondStatement.setInt(2, id);
            System.out.println(secondStatement);
            secondStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
    }
}


