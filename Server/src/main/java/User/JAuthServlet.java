package User;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
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


//@WebServlet("/jauth")
public class JAuthServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String authHeader = request.getHeader("authorization");
//        String encodedAuth = authHeader.substring(authHeader.indexOf(' ')+1);
//        String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
//        String username = decodedAuth.substring(0, decodedAuth.indexOf(':'));
//        String password = decodedAuth.substring(decodedAuth.indexOf(':')+1);
//        System.out.println(authHeader);
//        User loggedInPerson = null;
//        try {
//            generateToken();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String authHeader = request.getHeader("authorization");
        final int EXPIRY_DAYS = 3600;


        String encodedAuth = authHeader.substring(authHeader.indexOf(' ') + 1);
        String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
        String username = decodedAuth.substring(0, decodedAuth.indexOf(':'));
        String password = decodedAuth.substring(decodedAuth.indexOf(':') + 1);

        JSONObject jwtPayload = new JSONObject();
//        jwtPayload.put("status", 0);

        JSONArray audArray = new JSONArray();
//        audArray.put("admin");
        jwtPayload.put("sub", username);
        jwtPayload.put("pass", password);
        jwtPayload.put("aud", audArray);
        LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
        jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured


//        String token = new User.JWebToken("John",audArray,ldt.toEpochSecond(ZoneOffset.UTC)).toString();
        User loggedInPerson = null;
        String role = null;
        try {
            loggedInPerson = userDAO.findUser(username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //make sure user is in our data
        if (loggedInPerson == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //make sure password is valid
        //use hashed passwords in real life!
//        if(!password.equalsIgnoreCase(loggedInPerson.getPassword())){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
        int id = loggedInPerson.getId();
        try {
            role = userDAO.getRole(id);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        String token = new JWebToken(jwtPayload).toString();
        Login login = new Login(id, role, token);
        String json = new Gson().toJson(login);
        System.out.println(json);
        response.getOutputStream().print(json);

    }
//    public String generateToken(String authHeader){
//        final int EXPIRY_DAYS = 90;
//
//
//        String encodedAuth = authHeader.substring(authHeader.indexOf(' ')+1);
//        String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
//        String username = decodedAuth.substring(0, decodedAuth.indexOf(':'));
//        String password = decodedAuth.substring(decodedAuth.indexOf(':')+1);
//
//        JSONObject jwtPayload = new JSONObject();
////        jwtPayload.put("status", 0);
//
//        JSONArray audArray = new JSONArray();
////        audArray.put("admin");
//        jwtPayload.put("sub", username);
//        jwtPayload.put("pass", password);
////        jwtPayload.put("aud", audArray);
//        LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
//        jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured
//
//        String token = new User.JWebToken(jwtPayload).toString();
////        String token = new User.JWebToken("John",audArray,ldt.toEpochSecond(ZoneOffset.UTC)).toString();
//        User loggedInPerson = null;
//        try {
//            loggedInPerson = userDAO.findUser(username);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        //make sure user is in our data
//        if(loggedInPerson == null){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        //make sure password is valid
//        //use hashed passwords in real life!
//        if(!password.equalsIgnoreCase(loggedInPerson.getPassword())){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        return token;
//    }

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


