package Manager.dishes;

import Manager.Dao.DB;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/Manager/dish")
public class dishservlet extends HttpServlet {
    DB db = new DB();
    dishdao dish = new dishdao(db);


@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp){
    List <dish> dishes = new ArrayList<>();


    String s = req.getParameter("search");
    String id= req.getParameter("id");
//    System.out.println("HI");
    try {
        if (s != null) {
            dishes = dish.readname("%" + s + "%");
        } else if (id == null){
            dishes = dish.read();
        }
        else{
            dishes.add(dish.readbyid(id));

        }
    }catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    String dishitem = new Gson().toJson(dishes);
    outputResponse(resp,dishitem,200);

}

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
        dish dishitem = new Gson().fromJson(req.getReader(), dish.class);
        String json;
        if (dish.add(dishitem) != 0) {
            json = "{\"message:Success\"}";
            outputResponse(res,json,200);
        } else {
            json =  "{\"message:Failed\"}";
            outputResponse(res,json,406);
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