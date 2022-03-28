package User;

import Manager.employee.SendMail;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


@WebServlet("/reset")


public class ResetPasswordServlet {
    private UserDAO user;

    public void init() {

        user = new UserDAO();
    }

    public int getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return Integer.parseInt(String.format("%06d", number));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            deactivateAccount(request, response);


        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {


        resetPassword(request, response);


    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String email = request.getParameter("key1");
        String code = request.getParameter("key2");
        User validUser = user.checkUserCode(email, code);
        if (validUser != null) {
            User resetUser = new Gson().fromJson(request.getReader(), User.class);
            user.resetPassword(resetUser);
        }

    }


    private void deactivateAccount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int code = getRandom();
        String username = request.getParameter("username");
        boolean exists = user.checkEmail(username);
        if (exists) {
            user.deactivateAccount(username, code);
            SendMail mailer = new SendMail(username, code);
            mailer.sendActivationLink();
        }

    }

}
