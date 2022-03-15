package User;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/activate")


public class ActivateAccountServlet extends HttpServlet {


    private UserDAO newUser;

    public void init() {

        newUser = new UserDAO();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            activateUser(request, response);


        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void activateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");
        User target = newUser.checkUserCode(email, hash);
        if (target != null) {
            newUser.verifyUser(target);
        }

    }

}

