package User;

import Manager.employee.SendMail;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")


public class RegisterServlet extends HttpServlet {


    //    private User.SendMail mailer;
    private UserDAO newUser;

    public void init() {

        newUser = new UserDAO();


    }

    public int getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return Integer.parseInt(String.format("%06d", number));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            System.out.println("i made it to register");


            addUser(request, response);


        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int code = getRandom();
        User user = new Gson().fromJson(request.getReader(), User.class);
        boolean added = newUser.addUser(user, code);
        System.out.println(added);
        if (added) {
            SendMail mailer = new SendMail(user.getUsername(), code);
            mailer.sendActivationLink();
        } else {
            response.sendError(400, "Length too long");
            System.out.println("noo");
        }


    }

}







