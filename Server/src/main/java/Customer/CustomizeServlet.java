package Customer;

import Customer.Dish.Dish;
import Customer.Dish.DishDAO;
import Customer.Ingredient.Ingredient;
import Customer.Ingredient.IngredientDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customize/*")
public class CustomizeServlet extends HttpServlet {

    private DishDAO dishDAO;
    private IngredientDAO ingredientDAO;

    public void init() {
        dishDAO = new DishDAO();
        ingredientDAO = new IngredientDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {

            showDish(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void showDish(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/customize/".length());
        Dish currentDish = dishDAO.selectDish(Integer.parseInt(id));
        System.out.println("hih");
        List<Ingredient> listIngredient = ingredientDAO.selectCustomizableIngredients(Integer.parseInt(id));

//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        String json = new Gson().toJson(currentDish);
        JsonElement listjson = new Gson().toJsonTree(listIngredient);

        System.out.println(listjson);
        JsonElement jsonElement = new Gson().toJsonTree(currentDish);

        jsonElement.getAsJsonObject().add("ingredients", listjson);
        String s = new Gson().toJson(jsonElement);
        System.out.println(s);
        System.out.println(listjson);
//        String ingredientJson = new Gson().toJson(listIngredient);


//        System.out.println(json);
        response.getOutputStream().println(s);


    }
}
