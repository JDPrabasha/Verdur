package Manager.employee;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Manager/Email")
public class testmailservlet extends HttpServlet {
    private SendMail mailer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        mailer = new SendMail("test",123);
        mailer.sendEmail();
    }
}
