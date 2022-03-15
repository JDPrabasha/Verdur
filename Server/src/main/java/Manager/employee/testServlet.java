package Manager.employee;

import Manager.Dao.DB;
import Manager.Output.outputResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

@WebServlet("/Manager/ImageTest/*")
@MultipartConfig
public class testServlet extends HttpServlet {

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
        String id = requestUrl.substring("/Server_war_exploded/Manager/ImageTest/".length());
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        System.out.println();
        System.out.println();
        System.out.println("came to image test");
//        System.out.println(req.getParameter("image"));
        try {
            Part image = req.getPart("image");
            InputStream filename = image.getInputStream();

//            Connection conn = initializeDB();
            PreparedStatement st = conn.prepareStatement("Insert into image(image) values(?)",Statement.RETURN_GENERATED_KEYS);
            st.setBlob(1,filename);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int imageID = 0;
            while (rs.next()) {
                imageID = rs.getInt(1);
            }
            JsonObject rootElement = new JsonObject();
            JsonElement imageid = new Gson().toJsonTree(imageID);

            rootElement.add("imageID",imageid);

            outputResponse.sendresponse(resp,rootElement.toString(),200);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        String requestUrl = req.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/Manager/ImageTest/".length());
//        Connection conn = initializeDB();
        try {
            PreparedStatement st = conn.prepareStatement("Delete from image where id = ?");
            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();
        } catch (SQLException e) {
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
