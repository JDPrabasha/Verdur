package Rider.Delivery;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/delivery/*")

public class DeliveryServlet extends HttpServlet {

    private DeliveryDAO deliveryDAO;

    public void init() {
        deliveryDAO = new DeliveryDAO();

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateStatus(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        if (Objects.equals(action, "/fees")) {
            List<Delivery> feeList = deliveryDAO.getDeliveryFees();
            String json = new Gson().toJson(feeList);

            System.out.println(json);
            response.getOutputStream().println(json);

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
        System.out.println("i reached the gn");
        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/delivery/".length());
        System.out.println(id);
        List<Delivery> orderList = deliveryDAO.getDeliveryDetails(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(orderList);
//        System.out.println("im in menu");
//        System.out.println(json);
        System.out.println(json);
        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("i reached the gn");
        String id = request.getParameter("order");
        String method = request.getParameter("method");
        if (Objects.equals(method, "card")) {
            deliveryDAO.confirmCardOrder(Integer.parseInt(id));
        } else {
            deliveryDAO.confirmCashOrder(Integer.parseInt(id));

        }

//        List<User.Delivery> orderList = deliveryDAO.getDeliveryDetails(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
//        String json = new Gson().toJson(orderList);
////        System.out.println("im in menu");
////        System.out.println(json);
//        System.out.println(json);
//        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

}