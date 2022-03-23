package Rider;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/rider/*")

public class RiderServlet extends HttpServlet {

    private RiderDAO riderDAO;

    public void init() {
        riderDAO = new RiderDAO();

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rider = request.getParameter("rider");
        System.out.println("Shoutout");
        riderDAO.toggleAvailibility(Integer.parseInt(rider));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (Objects.equals(action, "/orders")) {
            try {
                getMonthlyOrders(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {

                showOrders(request, response);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }

    }

    private void showOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("i reached the goon");
        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/rider/".length());
        System.out.println(id);
        Rider rider = riderDAO.getRiderDetails(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(rider);
//        System.out.println("im in menu");
//        System.out.println(json);
        System.out.println(json);
        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

    private void getMonthlyOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("i reached the gn");
        String id = request.getParameter("id");
        System.out.println(id);
        int orders = riderDAO.getCheckpointCount(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);

//        System.out.println("im in menu");
//        System.out.println(json);

        response.getOutputStream().println(orders);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

}
