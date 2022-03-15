package Customer.Ingredient;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ingredient/*")
public class IngredientServlet extends HttpServlet {


    private IngredientDAO ingredientDAO;

    public void init() {

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
        String id = requestUrl.substring("/Server_war_exploded/ingredient/".length());
        Ingredient currentIngredient = ingredientDAO.selectIngredient(Integer.parseInt(id));
        System.out.println("hih");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        String json = new Gson().toJson(currentIngredient);

        System.out.println(json);


//        String ingredientJson = new Gson().toJson(listIngredient);

//        System.out.println(json);
        outputResponse(response, json, 200);

        System.out.println("wtf");


    }

    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
