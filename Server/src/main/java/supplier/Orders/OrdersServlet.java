package supplier.Orders;



import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrdersServlet", value = "/Supplier/OrdersServlet")
public class OrdersServlet extends HttpServlet {
    private OrdersDAO ordersDAO;

    public void init() {
        System.out.println("3333");
        ordersDAO = new OrdersDAO(); }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {

            showItem(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doGet(request, response);

    }

    private void showItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String requestUrl = request.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/dish/".length());
//        ViewStock currentDish = viewStockDAO.selectItem(Integer.parseInt(id));
        List<Orders> currentOrders = ordersDAO.selectAllOrders();
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("dish", currentOrders);
        String json = new Gson().toJson(currentOrders);

//        System.out.println(json);
        response.getOutputStream().println(json);


    }


}


//package Orders;
//
//
//
//import com.google.gson.Gson;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet(name = "OrdersServlet", value = "/Supplier/OrdersServlet")
//public class OrdersServlet extends HttpServlet {
//
//    private OrdersDAO ordersDAO;
//
//    public void init() {
//        ordersDAO = new OrdersDAO(); }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("1234");
//        String action = request.getServletPath();
//        System.out.println(action);
//        try {
//
//            showItem(request, response);
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
//
//    }
//
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        doGet(request, response);
//
//
//    }
//
//    private void showItem(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
////        int id = Integer.parseInt(request.getParameter("id"));
//        String requestUrl = request.getRequestURI();
//        String id = requestUrl.substring("/Server_war_exploded/dish/".length());
////        ViewStock currentDish = viewStockDAO.selectItem(Integer.parseInt(id));
//        List<Orders> currentOrders = ordersDAO.selectAllOrders();
////        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("dish", currentOrders);
//        String json = new Gson().toJson(currentOrders);
//
////        System.out.println(json);
//        response.getOutputStream().println(json);
//
//
//    }
//
//
//}
