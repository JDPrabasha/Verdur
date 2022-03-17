package Manager.Manager_Dish;

import User.ConnectionFactory.DB;
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

@WebServlet("/Manager/dishRequests")
public class DishRequestServlet extends HttpServlet {
    private DishDAO dish;

    public void init() {
//        System.out.println("dishawa");
        dish = new DishDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Dish> dishes = new ArrayList<>();
        try{
            dishes = dish.readDishRequest();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            this.outputResponse(resp,"",406);
        }
        String json = new Gson().toJson(dishes);

        this.outputResponse(resp,json,200);
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
