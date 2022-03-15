package cashier.CashierPayments;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Cashier/CashierPaymentsServlet")
public class CashierPaymentsServlet extends HttpServlet {
    private CashierPaymentsDAO cashierPaymentsDAO;

    public void init() {

        cashierPaymentsDAO = new CashierPaymentsDAO(); }


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
        List<CashierPayments> currentDish = cashierPaymentsDAO.selectAllItems();
        System.out.println("danaji");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("dish", currentDish);
        String json = new Gson().toJson(currentDish);

//        System.out.println(json);
        response.getOutputStream().println(json);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        cashierPaymentsDAO.completeOrderCashier(Integer.parseInt(id));
    }
}
