package Manager.RestocksOrders;

import Manager.Dao.DB;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Manager/RestockOrders")
public class RestockOrdersServlet extends HttpServlet {

    private DB db;
    private RestockOrdersDAO restocks;

    public void init(){
        db = new DB();
        restocks = new RestockOrdersDAO(db);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<RestockOrders> restocksarr = new ArrayList<>();
        try{
            restocksarr = restocks.listPayments();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String json = new Gson().toJson(restocksarr);

        outputResponse(resp,json,200);

    }



    private void outputResponse(HttpServletResponse response, String payload, int status){
        response.setHeader("Content-Type", "application/json");
        try{
            response.setStatus(status);
            if (payload != null){
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
