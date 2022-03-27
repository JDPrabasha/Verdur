package Rider.Order;

import Rider.Rider;
import Rider.RiderDAO;
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

@WebServlet("/order/*")


public class OrderServlet extends HttpServlet {


    private OrderDAO orderDAO;
    private RiderDAO riderDAO;

    public void init() {
        orderDAO = new OrderDAO();
        riderDAO = new RiderDAO();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        finishReview(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        System.out.println("action is " + action);

        if (Objects.equals(action, "/complaint")) {
            fileComplaint(request, response);
        } else {
            try {

                addOrder(request, response);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
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
            getOrderInformation(request, response);
        } else {

            try {
                selectActiveOrders(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


    private void getOrderInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customer = request.getParameter("customer");
        String status = request.getParameter("status");
        System.out.println(status);
        if (Objects.equals(status, "accepted")) {
            String remTime = orderDAO.getGetAcceptedTime(Integer.parseInt(customer));
            response.getOutputStream().println(remTime);
        } else if (Objects.equals(status, "delivering")) {
            System.out.println("got here");
            Rider rider = riderDAO.getDeliveringRider(Integer.parseInt(customer));
            String json = new Gson().toJson(rider);
            System.out.println(json);
            response.getOutputStream().println(json);

        } else if (Objects.equals(status, "rejected")) {
            String reason = orderDAO.getRejectionReason(Integer.parseInt(customer));
            response.getOutputStream().println(reason);
        } else if (Objects.equals(status, "delivered")) {
            int payment = orderDAO.getDeliveryPayment(Integer.parseInt(customer));
            response.getOutputStream().println(payment);
        }

    }


    private void selectActiveOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String requestUrl = request.getRequestURI();

        String customer = requestUrl.substring("/Server_war_exploded/order/".length());
        Order order = orderDAO.selectActiveOrders(Integer.parseInt(customer));
        String json = new Gson().toJson(order);
        System.out.println("im in menue");
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

    private void fileComplaint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order complaintOrder = new Gson().fromJson(request.getReader(), Order.class);
        System.out.println("got here");
        orderDAO.addComplaint(complaintOrder);
        System.out.println("done");
    }

    private void finishReview(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("i try");
        String requestUrl = request.getRequestURI();

        String customer = request.getParameter("customer");
        orderDAO.finishReview(customer);


        System.out.println("im in done");
    }
}