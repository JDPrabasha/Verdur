package Manager.employee;

import Manager.Dao.DB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

@WebServlet("/Images/*")
public class imageServlet extends HttpServlet {
//    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
//    private static String dbUrl = "jdbc:mysql://localhost:3306/";
//    private static String dbName = "test";
//    private static String dbUname = "root";
//    private static String dbPass = "";

    private Connection conn;

    public void init(){
        try {
            conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String requestUrl = req.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/Images/".length());
//        Connection conn = initializeDB();
        PreparedStatement st = null;
        OutputStream os = null;
        try {
            st = conn.prepareStatement("select * from image where id = ?");
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            InputStream is = null;
            while (rs.next()){
                is = rs.getBinaryStream("image");
            }
            resp.setContentType("image/jpeg");
            os = resp.getOutputStream();
            int num;
            byte buf[] = new byte[1024];
            while ((num=is.read(buf))!= -1){
                os.write(buf,0,num);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static Connection initializeDB()  {
////        String dbDriver = "com.mysql.jdbc.Driver";
//
////        Connection con = DriverManager.getConnection(dbUrl+dbName+"?useSSL=true&autoReconnect=true",dbUname,dbPass);
//        Connection con = null;
//        try {
//            Class.forName(dbDriver);
//            con = DriverManager.getConnection(dbUrl+dbName+"?useSSL=true&autoReconnect=true",dbUname,dbPass);
//        } catch (SQLException e) {
//            initializeDB();
//        } catch (ClassNotFoundException e) {
//            initializeDB();
//        }
//        return con;
//    }
}
