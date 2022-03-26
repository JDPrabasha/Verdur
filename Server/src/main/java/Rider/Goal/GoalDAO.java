package Rider.Goal;

import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoalDAO {
    private static final String GET_GOALS = " select * from goal";

    private Connection conn;

    public GoalDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Goal> getGoals() {
        List<Goal> goals = new ArrayList<>();
        // Step 1: Establishing a this.conn
        try (

                // Step 2:Create a statement using this.conn object
                PreparedStatement preparedStatement = this.conn.prepareStatement(GET_GOALS);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {


                int checkpoint = rs.getInt("checkpoint");
                int raise = rs.getInt("raisepc");
                int orders = rs.getInt("orders");

                goals.add(new Goal(checkpoint, raise, orders));
            }

        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

        return goals;
    }

    private void printSQLException(SQLException e) {
    }
}
