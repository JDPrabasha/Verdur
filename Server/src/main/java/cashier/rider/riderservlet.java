package cashier.rider;

import CashierOrders.CashierOrdersDAO;
import Db.DB;

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

@WebServlet("/Cashier/rider")
public class riderservlet extends HttpServlet {

    DB db = new DB();
    riderdao  riderdao = new riderdao(db);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<rider> riderlist = new ArrayList<>();
        String id= req.getParameter("id");


        try {
            riderlist = riderdao.readriderlist(id);

//            riderlist = riderdao. readconfirmriderlist(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String jsonresponce = new Gson().toJson(riderlist);
        outputResponse(resp, jsonresponce, 200);
    }



    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){

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
