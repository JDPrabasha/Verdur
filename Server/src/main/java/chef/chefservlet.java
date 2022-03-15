package chef;

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

@WebServlet("/KitchenManager/chef")
public class chefservlet extends HttpServlet {

    DB db = new DB();
    chefdao chef = new chefdao(db);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<chef> cheflist = new ArrayList<>();
        String id= req.getParameter("id");


        try {
            cheflist = chef.readcheflist(id);
//            cheflist = chef.readcheflist();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String jsonresponce = new Gson().toJson(cheflist);
        outputResponse(resp, jsonresponce, 200);
    }
    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        chef chefitem = new Gson().fromJson(req.getReader(),chef.class);
//        System.out.println(restockitem.getApprovalstatus() != null);
        try {
//            if(dishitem.getApprovalstatus() != null){
//                restock.managerApproval(restockitem);
//            }else if(restockitem.getStatus() !=null){
//                restock.managerDeliveryCompletion(restockitem);
//            }
            chef.assignchef(chefitem);
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
