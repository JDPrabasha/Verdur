package supplier.RestockRequest;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RestockRequestServlet", value = "/Supplier/RestockRequestServlet")
public class RestockRequestServlet extends HttpServlet {
    private RestockRequestDAO restockRequestDAO;

    public void init() {
        restockRequestDAO = new RestockRequestDAO(); }


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
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            acceptRequest(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }
    private void acceptRequest(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        RestockRequest reorder = new Gson().fromJson(request.getReader(), RestockRequest.class);
        String supplierID = request.getParameter("supplierID");
        restockRequestDAO.acceptRequest(reorder, Integer.parseInt(supplierID));



    }



    private void showItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String requestUrl = request.getRequestURI();
        String id = request.getParameter("id");
//        ViewStock currentDish = viewStockDAO.selectItem(Integer.parseInt(id));
        List<RestockRequest> restockRequestList = restockRequestDAO.selectAllItems(Integer.parseInt(id));
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("dish", restockRequestList);
        String json = new Gson().toJson(restockRequestList);

//        System.out.println(json);
        response.getOutputStream().println(json);


    }
}
