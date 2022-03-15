package kitchenmanager.restockorder;

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

@WebServlet("/KitchenManager/restocks")
public class restockservlet extends HttpServlet {

    DB db = new DB();
    restockdao restock = new restockdao(db);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        List<restock> restockrequests = new ArrayList<>();


//        String s = req.getParameter("search");
        String id = req.getParameter("id");

        try {
            if (id == null) {
                restockrequests = restock.read();
            } else {
                restockrequests.add(restock.readrestockrequest(id));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String jsonresponse = new Gson().toJson(restockrequests);
        System.out.println(jsonresponse);
        outputResponse(resp, jsonresponse, 200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        restock restockitem = new Gson().fromJson(req.getReader(), restock.class);
        String json;
        try {
            if (restock.addrestockrequest(restockitem) != 0) {
                json = "{\"message:Success\"}";
                outputResponse(res, json, 200);
            } else {
                json = "{\"message:Failed\"}";
                outputResponse(res, json, 406);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
