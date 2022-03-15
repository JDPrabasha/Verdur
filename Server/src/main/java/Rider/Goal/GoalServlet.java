package Rider.Goal;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/goals")

public class GoalServlet extends HttpServlet {

    private GoalDAO goalDAO;

    public void init() {
        goalDAO = new GoalDAO();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            getGoals(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void getGoals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("i reached the gn");

        List<Goal> goals = goalDAO.getGoals();
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(goals);
//        System.out.println("im in menu");
//        System.out.println(json);
        System.out.println(json);
        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

}
