
package User;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {


    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            uppdateCustomerInformation(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (Objects.equals(action, "/customer")) {
            try {
                getCustomerInformation(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {

            try {
                System.out.println("Mahalo");
                String role = request.getParameter("role");
                System.out.println(role);
                if (Objects.equals(role, "customer")) {
                    getCustomerDetails(request, response);
                } else {
                    System.out.println("hgg");
                    getEmployeeDetails(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    private void getCustomerInformation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        System.out.println("i got here");
        User user = userDAO.getCustomerDetails(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
        System.out.println(user.getAddress());
        String json = new Gson().toJson(user);
        System.out.println("im in menu");
//        System.out.println(json);

        response.getOutputStream().println(json);
    }

    private void getCustomerDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String id = request.getParameter("id");

        User user = userDAO.getUserDetails(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(user);
        System.out.println("im in menu");
//        System.out.println(json);

        response.getOutputStream().println(json);


//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

    private void getEmployeeDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {


        String id = request.getParameter("id");

        User user = userDAO.getEmployeeDetails(Integer.parseInt(id));
//        request.setAttribute("listDish", listDish);
        String json = new Gson().toJson(user);
        System.out.println("im in menu");
//        System.out.println(json);

        response.getOutputStream().println(json);


//        System.out.println(listDish.get(0).getName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dish.jsp");
//        dispatcher.forward(request, response);
    }

    private void uppdateCustomerInformation(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");

        User user = new Gson().fromJson(request.getReader(), User.class);
        userDAO.updateCustomerDetails(Integer.parseInt(id), user);

    }

}

