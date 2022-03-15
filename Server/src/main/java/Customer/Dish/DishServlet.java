package Customer.Dish;

import Customer.Ingredient.Ingredient;
import Customer.Ingredient.IngredientDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dish/*")
public class DishServlet extends HttpServlet {

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

        String taxion = request.getContextPath();
        String action = request.getPathInfo();
        System.out.println(taxion);
        System.out.println("action is" + action);

        if (Objects.equals(action, "filter")) {
            System.out.println("hello searcher");
            String query = request.getParameter("name");
            List<Dish> listDish = dishDAO.filterDishesByName(query);
            String json = new Gson().toJson(listDish);
            response.getOutputStream().println(json);
        } else if (Objects.equals(action, "/recents")) {
            System.out.println("aloha");
            String id = request.getParameter("customer");
            System.out.println(id);
            List<Dish> listDish = dishDAO.selectRecentDishes(Integer.parseInt(id));
            String json = new Gson().toJson(listDish);
            System.out.println(json);
            response.getOutputStream().println(json);
            System.out.println("gello");
        } else {
            try {
                showDish(request, response);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }


    private void showDish(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/dish/".length());
        Dish currentDish = dishDAO.selectDish(Integer.parseInt(id));
        System.out.println("hih");
        List<Ingredient> listIngredient = ingredientDAO.selectAllIngredients(Integer.parseInt(id));


        JsonElement listjson = new Gson().toJsonTree(listIngredient);

        System.out.println(listjson);
        JsonElement jsonElement = new Gson().toJsonTree(currentDish);

        jsonElement.getAsJsonObject().add("ingredients", listjson);
        String s = new Gson().toJson(jsonElement);
        System.out.println(s);
        System.out.println(listjson);
        response.getOutputStream().println(s);


    }
}
