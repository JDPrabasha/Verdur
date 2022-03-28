package User;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;


@WebServlet("/jauth")
public class JAuthServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String authHeader = request.getHeader("authorization");
        final int EXPIRY_DAYS = 1;


        String encodedAuth = authHeader.substring(authHeader.indexOf(' ') + 1);
        String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
        String username = decodedAuth.substring(0, decodedAuth.indexOf(':'));
        String password = decodedAuth.substring(decodedAuth.indexOf(':') + 1);

        JSONObject jwtPayload = new JSONObject();


        User loggedInPerson = null;
        String role = null;
        try {
            loggedInPerson = userDAO.findUser(username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (loggedInPerson == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        int id = loggedInPerson.getId();
        try {
            role = userDAO.getRole(id);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONArray audArray = new JSONArray();
        audArray.put(role);
        jwtPayload.put("sub", username);
        jwtPayload.put("pass", password);
        jwtPayload.put("aud", audArray);
        LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
        jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured
        String token = new JWebToken(jwtPayload).toString();
        Login login = new Login(id, role, token);
        String json = new Gson().toJson(login);
        System.out.println(json);
        response.getOutputStream().print(json);

    }


    public boolean verifyToken(String bearerToken) throws NoSuchAlgorithmException {
        JWebToken incomingToken = new JWebToken(bearerToken);
        if (!incomingToken.isValid()) {
            List<String> audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            return false;
        }
        return true;
    }
}


