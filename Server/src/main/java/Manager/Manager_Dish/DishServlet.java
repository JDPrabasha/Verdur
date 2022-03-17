package Manager.Manager_Dish;

import User.ConnectionFactory.DB;
import com.google.gson.Gson;

//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Manager/dishes/*")
public class DishServlet extends HttpServlet {
    private DishDAO dish;

    public void init() {
//        System.out.println("dishawa");
        dish = new DishDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        List<Dish> dishes = new ArrayList<>();
        try{
            if (id==null){
                dishes = dish.readDish();
            }else{
                dishes.add(dish.readDishByID(Integer.parseInt(id)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            this.outputResponse(resp,"",406);
        }
        String json = new Gson().toJson(dishes);

        this.outputResponse(resp,json,200);
    }

    @Override
    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Dish d = new Gson().fromJson(req.getReader(),Dish.class);
        String requestUrl = req.getRequestURI();
        String type = requestUrl.substring("/Server_war_exploded/Manager/Manager.dishes/".length());
//        System.out.println(type);
//        System.out.println(type.equals("reject"));
        if(type.equals("reject")){
            managerDeny(d.getDishID());
        }else if(type.equals("approve")){
            managerApprove(d.getDishID());
        }
    }

    public int managerDeny(int id){
//        System.out.println(dish.checkCurrentEnabledStatus(id));
        String dishRequest = dish.checkCurrentEnabledStatus(id);
        if (dishRequest.equals("Add")){
            return dish.deleteDish(id);
        }else if(dishRequest.equals("Update") || dishRequest.equals("Delete")){
            return dish.updateStatus(id,1);
        }
        return 0;
    }

    public int managerApprove(int id){
        String dishRequest = dish.checkCurrentEnabledStatus(id);
        if(dishRequest.equals("Add")){
            return dish.updateStatus(id,1);
        }else if(dishRequest.equals("Update")){
            return dish.updateDishtoUpdated(id);
        }else if(dishRequest.equals("Delete")){
            return dish.deleteDish(id);
        }
        return 0;
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
