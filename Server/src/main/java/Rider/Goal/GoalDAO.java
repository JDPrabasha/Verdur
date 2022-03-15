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


    public List<Goal> getGoals() {
        List<Goal> goals = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.initializeDB();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_GOALS);) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {


                int checkpoint = rs.getInt("checkpoint");
                int raise = rs.getInt("raisepc");
                int orders = rs.getInt("orders");

                goals.add(new Goal(checkpoint, raise, orders));
            }

        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }

        return goals;
    }

    private void printSQLException(SQLException e) {
    }
}
