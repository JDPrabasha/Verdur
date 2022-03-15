package Customer.Cart;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/car")


public class CartServlet extends HttpServlet {

    private CartDAO cartDAO;

    public void init() {
        cartDAO = new CartDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addOrder(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Cart cart = new Gson().fromJson(request.getReader(), Cart.class);
        cartDAO.addOrder(cart);


    }

}


