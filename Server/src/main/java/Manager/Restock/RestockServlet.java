package Manager.Restock;


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

@WebServlet("/Manager/Restock")
public class RestockServlet extends HttpServlet {

    private RestockDAO restock;

    public void init(){
        restock = new RestockDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Restock> restocks = new ArrayList<>();
        String type = req.getParameter("type");
        try{
            if(type==null){
                restock.timeOutRequests();
                restocks = restock.listRestockRequests();
            }else{
//                restock.timeOutRequests();
                restocks = restock.listDeliveryPendingRequests();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String json = new Gson().toJson(restocks);

        outputResponse(resp,json,200);
    }

    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Restock restockitem = new Gson().fromJson(req.getReader(),Restock.class);
//        System.out.println(restockitem.getApprovalstatus() != null);
        try {
            if(restockitem.getApprovalstatus() != null){
                restock.managerApproval(restockitem);
            }else if(restockitem.getStatus() !=null){
                restock.managerDeliveryCompletion(restockitem);
            }
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
