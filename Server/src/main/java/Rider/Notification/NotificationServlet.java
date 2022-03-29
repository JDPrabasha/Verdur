package Rider.Notification;


import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notification/*")


public class NotificationServlet extends HttpServlet {

    private NotificationDAO notificationDAO;

    public void init() {
        notificationDAO = new NotificationDAO();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            getNotifications(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void getNotifications(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String requestUrl = request.getRequestURI();

        String user = request.getParameter("id");
        String role = request.getParameter("role");
        System.out.println("i tried to notify firt");
        List<Notification> notifications = notificationDAO.getNotifications(Integer.parseInt(user), role);
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(notifications);
        System.out.println("i tried to notify");
//        System.out.println(json);

        response.getOutputStream().println(json);
//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }


}
