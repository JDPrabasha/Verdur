package supplier.AddItem;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Supplier/AddItemServlet")
public class AddItemServlet extends HttpServlet {


//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
    private AddItemDAO addItemDAO;

    public void init() {
        addItemDAO = new AddItemDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addItem(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }




    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        AddItem item = new Gson().fromJson(request.getReader(), AddItem.class);
        addItemDAO.addItem(item);



    }
}
