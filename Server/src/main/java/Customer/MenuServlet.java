package Customer;

import Customer.Dish.Dish;
import Customer.Dish.DishDAO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu/*")
public class MenuServlet extends HttpServlet {

    private DishDAO dishDAO;

    public void init() {
        dishDAO = new DishDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String taxion = request.getContextPath();
        String action = request.getPathInfo();

        System.out.println(action);
        System.out.println(taxion);


        if (Objects.equals(action, "/search")) {
            System.out.println("hello searcher");
            String query = request.getParameter("name");
            List<Dish> listDish = dishDAO.filterDishesByName(query);
            String json = new Gson().toJson(listDish);

            System.out.println(json);

            response.getOutputStream().println(json);
        } else if (Objects.equals(action, "/specials")) {
            String query = request.getParameter("meal");
            List<Dish> listDish = dishDAO.filterDishesByMeal(query);
            String json = new Gson().toJson(listDish);
            response.getOutputStream().println(json);

        } else if (Objects.equals(action, "/new")) {

            List<Dish> listDish = dishDAO.selectNewDishes();
            String json = new Gson().toJson(listDish);
            System.out.println(json);
            response.getOutputStream().println(json);


        } else if (Objects.equals(action, "/filter")) {
            String ingredients = request.getParameter("ingredient");
            String diets = request.getParameter("preference");
            if (ingredients != null) {
                String[] array = ingredients.split(",", 0);
                List<Dish> listDish = dishDAO.filterDishesByIngredients(array);
                System.out.println(array[array.length - 1]);
                String json = new Gson().toJson(listDish);
                response.getOutputStream().println(json);
            } else if (diets != null) {
                String preferences = request.getParameter("preference");
                String[] parray = preferences.split(",", 0);
                String lifestyles = request.getParameter("lifestyle");
                String[] larray = lifestyles.split(",", 0);
                String allergies = request.getParameter("allergy");
                String[] aarray = allergies.split(",", 0);
                List<Dish> listDish = dishDAO.filterDishesByDiet(parray, larray, aarray);
                String json = new Gson().toJson(listDish);
                response.getOutputStream().println(json);

            } else {
                String budget = request.getParameter("budget");
                String time = request.getParameter("time");
                String types = request.getParameter("type");
                String[] array = types.split(",", 0);
                List<Dish> listDish = dishDAO.filterDishesByFilters(Integer.parseInt(budget), Integer.parseInt(time), array);
                System.out.println(array[array.length - 1]);
                String json = new Gson().toJson(listDish);
                response.getOutputStream().println(json);

            }


        } else {
            try {
                listDish(request, response);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }


    }

    private void listDish(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {


        List<Dish> listDish = dishDAO.selectAllDishes();
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(listDish);
        System.out.println("im in menu");
//        System.out.println(json);

        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }


}
