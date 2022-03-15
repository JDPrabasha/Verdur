package Rider.Order;

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

//@WebServlet("/order/*")


public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addOrder(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println(action);

        if (Objects.equals(action, "/recents")) {
            try {
                selectRecentOrders(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (Objects.equals(action, "/details")) {
            getRiderInformation(request, response);
        } else {

            try {
                selectActiveOrders(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private void getRiderInformation(HttpServletRequest request, HttpServletResponse response) {
        String customer = request.getParameter("customer");
        String status = request.getParameter("status");
        Order order = orderDAO.selectActiveOrders(Integer.parseInt(customer));
        String json = new Gson().toJson(order);
        System.out.println("im in menu");
    }


    private void selectActiveOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String requestUrl = request.getRequestURI();

        String customer = requestUrl.substring("/Server_war_exploded/order/".length());
        Order order = orderDAO.selectActiveOrders(Integer.parseInt(customer));
        String json = new Gson().toJson(order);
        System.out.println("im in menu");
//        System.out.println(json);

        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

    private void selectRecentOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String customer = request.getParameter("customer");
        System.out.println("recent orders");
        List<Order> listOrders = orderDAO.selectRecentOrders(Integer.parseInt(customer));
        String json = new Gson().toJson(listOrders);
        System.out.println("im in menu");
        response.getOutputStream().println(json);

    }

    private void reviewOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String customer = request.getParameter("customer");
        System.out.println("recent orders");
        List<Order> listOrders = orderDAO.selectRecentOrders(Integer.parseInt(customer));
        String json = new Gson().toJson(listOrders);
        System.out.println("im in menu");
        response.getOutputStream().println(json);

    }


    private void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("vvv");

        Order newOrder = new Gson().fromJson(request.getReader(), Order.class);
        System.out.println(newOrder);
        int order = orderDAO.addOrder(newOrder);
        System.out.println(order);
        response.getOutputStream().println(order);


    }
}
