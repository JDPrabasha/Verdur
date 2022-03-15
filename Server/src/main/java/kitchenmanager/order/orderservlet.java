package kitchenmanager.order;

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

//@WebServlet("/KitchenManager/order")
@WebServlet("/KitchenManager/order")
public class orderservlet extends HttpServlet {

    DB db = new DB();
    orderdao order = new orderdao(db);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<order> orderitem = new ArrayList<>();

        String id= req.getParameter("id");


        try {
            if (id == null){
                 orderitem = order.readOrder();
            }
            else{
                orderitem.add(order.readdish(id)) ;

            }

        }catch (SQLException throwables) {
        throwables.printStackTrace();
        }
        String orderdetail = new Gson().toJson(orderitem);
        outputResponse(resp,orderdetail,200);
    }

    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        order orderitem = new Gson().fromJson(req.getReader(),order.class);

        try {

            order.completeorder(orderitem);
            outputResponse(resp,"",204);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            outputResponse(resp,"",400);
        }

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
