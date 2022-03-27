package Customer.MealPlan;

import User.ConnectionFactory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealPlanDAO {
    private static final String SELECT_MEAL_PLAN = " select * from mealplan where custID = ? ";
    private static final String SELECT_MEAL_PLAN_PROGRESS = " select * from mealprogress where custID = ?  ";
    private static final String UPDATE_PLAN_PROGRESS = "update mealprogress set calorieProgress=?,proteinProgress = ?, carbProgress = ?, fatProgress = ? where custID = ?";
    private static final String RESET_PLAN_PROGRESS = "update mealprogress set calorieProgress=0,proteinProgress = 0, carbProgress = 0, fatProgress = 0 where custID = ?";
    private static final String UPDATE_PLAN = "update mealplan set calorieGoal=?, proteinGoal = ?, carbGoal = ?, fatGoal = ? where custID = ?";

    private Connection conn;

    public MealPlanDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addMeal(MealPlan m, int customer) {
        try {

            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_MEAL_PLAN_PROGRESS);
            conn.setAutoCommit(false);
            preparedStatement.setInt(1, customer);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("exec");
            if (rs.next()) {
                int calories = rs.getInt("calorieProgress");
                int protein = rs.getInt("proteinProgress");
                int fats = rs.getInt("fatProgress");
                int carbs = rs.getInt("carbProgress");
                System.out.println("got here");
                PreparedStatement secondStatement = this.conn.prepareStatement(UPDATE_PLAN_PROGRESS);
                secondStatement.setInt(1, (int) (calories + m.getCalories()));
                secondStatement.setInt(2, (int) (protein + m.getProtein()));
                secondStatement.setInt(3, (int) (carbs + m.getCarbs()));
                secondStatement.setInt(4, (int) (fats + m.getFat()));
                secondStatement.setInt(5, customer);
                System.out.println(secondStatement);
                secondStatement.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
    }

    public void resetProgress(int customer) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(RESET_PLAN_PROGRESS);
            conn.setAutoCommit(false);
            preparedStatement.setInt(1, customer);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("exec");
            conn.commit();
            conn.setAutoCommit(true);


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
    }

    public void updatePlan(MealPlan m, int customer) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(UPDATE_PLAN);
            conn.setAutoCommit(false);
            preparedStatement.setInt(1, (int) m.getCaloriesgoal());
            preparedStatement.setInt(2, (int) m.getProteingoal());
            preparedStatement.setInt(3, (int) m.getCarbgoal());
            preparedStatement.setInt(4, (int) m.getFatgoal());
            preparedStatement.setInt(5, customer);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
    }

    public MealPlan getMealPlanDetails(int id) {
        MealPlan mealPlan = null;
        Integer gfat = 0;
        Integer gprotein = 0;
        Integer gcalorie = 0;
        Integer gcarb = 0;
        Integer pfat = 0;
        Integer pprotein = 0;
        Integer pcalorie = 0;
        Integer pcarb = 0;

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_MEAL_PLAN);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            PreparedStatement secondStatement = this.conn.prepareStatement(SELECT_MEAL_PLAN_PROGRESS);
            secondStatement.setInt(1, id);
            System.out.println(secondStatement);
            ResultSet rs2 = secondStatement.executeQuery();
            if (rs.next() && rs2.next()) {

                gfat = rs.getInt("fatGoal");
                gprotein = rs.getInt("proteinGoal");
                gcalorie = rs.getInt("calorieGoal");
                gcarb = rs.getInt("carbGoal");
                System.out.println("here too");
                pfat = rs2.getInt("fatProgress");
                pprotein = rs2.getInt("proteinProgress");
                pcalorie = rs2.getInt("calorieProgress");
                pcarb = rs2.getInt("carbProgress");
                mealPlan = new MealPlan(pfat, pprotein, pcalorie, pcarb, gfat, gprotein, gcalorie, gcarb);

                System.out.println(gfat + " " + gprotein + " " + gcalorie + " " + gcarb);


            }


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return mealPlan;
    }

    private void printSQLException(SQLException e) {
    }
}
