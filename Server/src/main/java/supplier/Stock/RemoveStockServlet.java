package supplier.Stock;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Supplier/RemoveStockServlet")
public class RemoveStockServlet extends HttpServlet {


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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            removeItem(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }
    private void removeItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Stock item = new Gson().fromJson(request.getReader(), Stock.class);
        stockDAO.removeItem(item);



    }

    private void showItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String requestUrl = request.getRequestURI();
        String id = request.getParameter("id");
//        ViewStock currentDish = viewStockDAO.selectItem(Integer.parseInt(id));
        List<Stock> currentDish = stockDAO.selectAllItems(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("dish", currentDish);
        String json = new Gson().toJson(currentDish);

//        System.out.println(json);
        response.getOutputStream().println(json);


    }
}
