package Customer.MealPlan;


import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mealplan/*")
public class MealPlanServlet extends HttpServlet {


    private MealPlanDAO mealPlanDAO;

    public void init() {

        mealPlanDAO = new MealPlanDAO();
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (Objects.equals(action, "/add")) {

            try {
                addMeal(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(action, "/reset")) {

            resetPlan(request, response);
        } else {
            try {
                updatePlan(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void resetPlan(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("alho");
        String id = request.getParameter("customer");
  
        System.out.println(id);
        mealPlanDAO.resetProgress(Integer.parseInt(id));
        System.out.println("hih");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("im adding");
            addMeal(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);

        try {

            displayProfileProgress(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void displayProfileProgress(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/mealplan/".length());
        MealPlan mealPlan = mealPlanDAO.getMealPlanDetails(Integer.parseInt(id));
        System.out.println("hih");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        String json = new Gson().toJson(mealPlan);

        System.out.println(json);


//        String ingredientJson = new Gson().toJson(listIngredient);

//        System.out.println(json);

        response.getOutputStream().println(json);

        System.out.println("wtf");


    }


    private void updatePlan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String id = request.getParameter("customer");
        MealPlan mealPlan = new Gson().fromJson(request.getReader(), MealPlan.class);
        System.out.println(id);
        mealPlanDAO.updatePlan(mealPlan, Integer.parseInt(id));
        System.out.println("hih");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        String json = new Gson().toJson(mealPlan);

        System.out.println(json);


//        String ingredientJson = new Gson().toJson(listIngredient);

//        System.out.println(json);

        response.getOutputStream().println(json);

        System.out.println("wtf");


    }

    private void addMeal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("right fn");

        String id = request.getParameter("customer");
        MealPlan mealPlan = new Gson().fromJson(request.getReader(), MealPlan.class);
        System.out.println(mealPlan);
        System.out.println(id);
        mealPlanDAO.addMeal(mealPlan, Integer.parseInt(id));
        System.out.println("hih");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        String json = new Gson().toJson(mealPlan);

        System.out.println(json);


//        String ingredientJson = new Gson().toJson(listIngredient);

//        System.out.println(json);

        response.getOutputStream().println(json);

        System.out.println("wtf");


    }

}

