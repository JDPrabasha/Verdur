package kitchenmanager.kitchenmanagerorder;

import Dao.DB;
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

@WebServlet("/KitchenManager/orderkm")
public class orderkmservelet  extends HttpServlet {
    DB db = new DB();
    orderkmdao orderkm = new orderkmdao(db);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List <orderkm> orderitem = new ArrayList<>();


//        String s = req.getParameter("search");
//        String id= req.getParameter("id");
//        System.out.println("/nHI/n");
        String type = req.getParameter("type");

        try {
            if(type == null)
            {
                orderitem=orderkm.read();
            }
            else {
                orderitem=orderkm.readongoingorders();
            }
//            if(id != null)
//            {
//                orderitem = orderkm.readallchefs(id);
//            }




        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String orderresponce = new Gson().toJson(orderitem);
        outputResponse(resp,orderresponce,200);

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
