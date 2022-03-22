package supplier.Stock;

import com.google.gson.Gson;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static Manager.Output.outputResponse.sendresponse;

@WebServlet("/Supplier/UpdateStockServlet")
public class UpdateStockServlet extends HttpServlet {


    private StockDAO stockDAO;

    public void init() { stockDAO = new StockDAO();
    }


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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            updateItem(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }
    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Stock item = new Gson().fromJson(request.getReader(), Stock.class);
        stockDAO.updateItem(item);



    }

    private void showItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String requestUrl = request.getRequestURI();
        String ingID = request.getParameter("ingID");
        String supplierID = request.getParameter("supplierID");
//        ViewStock currentDish = viewStockDAO.selectItem(Integer.parseInt(id));
        List<Stock> stockItems = stockDAO.selectAnItem(Integer.parseInt(supplierID), Integer.parseInt(ingID));
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        String json = new Gson().toJson(stockItems);
        sendresponse(response,json,200);

//        System.out.println(json);
//        response.getOutputStream().println(json);


    }
}
