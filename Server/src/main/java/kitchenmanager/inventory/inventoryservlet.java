package kitchenmanager.inventory;

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

@WebServlet("/KitchenManager/inventory")
public class inventoryservlet extends HttpServlet {

    DB db = new DB();
//    inventorydao stock = new inventorydao(db);
    inventorydao inventory = new inventorydao(db);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<inventory> stockitem = new ArrayList<>();


        try {
            stockitem = inventory.read();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String jsonresponce = new Gson().toJson(stockitem);
        outputResponse(resp, jsonresponce, 200);
    }

    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
//        stock inventoryitem = new Gson().fromJson(req.getReader(),stock.class);
//        stock inventoryitem = new Gson().fromJson(req.getReader(),inventory.class);
      inventory inventoryitem = new Gson().fromJson(req.getReader(),inventory.class);




        try {
            System.out.println("edit");
            inventory.editstock(inventoryitem);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
