package Customer.Customization;

import Customer.Dish.Dish;
import Customer.Dish.DishDAO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart/*")


public class CustomizationServlet extends HttpServlet {

    private CustomizationDAO customizationDAO;
    private DishDAO dishDAO;

    public void init() {
        customizationDAO = new CustomizationDAO();
        dishDAO = new DishDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addOrder(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addItemsToCart(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            listDish(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("hui");
            String requestUrl = request.getRequestURI();

            String id = requestUrl.substring("/Server_war_exploded/cart/".length());

            customizationDAO.removeFromCart(Integer.parseInt(id));
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("i am trying to add to cart");
        Customization customizedDish = new Gson().fromJson(request.getReader(), Customization.class);
        customizationDAO.addCustomizeddish(customizedDish);


    }

    private void addItemsToCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("i am trying to add to cart");
        String id = request.getParameter("id");
        customizationDAO.addDishesToCart(id);


    }

    private void listDish(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/cart/".length());

        List<Dish> listDish = dishDAO.selectCartDishes(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(listDish);
        System.out.println("im in menu");
//        System.out.println(json);
        System.out.println(json);
        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }
}
