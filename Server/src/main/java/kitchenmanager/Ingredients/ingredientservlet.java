package kitchenmanager.Ingredients;


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


@WebServlet("/KitchenManager/ingredients")
public class ingredientservlet  extends HttpServlet{

    DB db = new DB();
    ingredientdao ingredient = new ingredientdao(db);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String typepara= req.getParameter("type");
        System.out.println(typepara);
        List<ingredient> ingredients = new ArrayList<>();
        try {
//            System.out.println(typepara.contains("All"));
//            System.out.println("All"=="All");
            if(typepara.contains("All")) {
                System.out.println("All");
                ingredients = ingredient.read();
            }else{
                System.out.println("Not All");
                ingredients = ingredient.readbytype(typepara);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String jsonresponce = new Gson().toJson(ingredients);
        outputResponse(resp,jsonresponce,200);

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
