package Manager.Ingredients;


import Manager.Output.outputResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/Manager/ingredients/*")
public class ingredientservlet  extends HttpServlet{
    ingredientdao ingredient = new ingredientdao();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
//        String typepara= req.getParameter("type");
//        System.out.println(typepara);
        List<ingredient> ingredients = new ArrayList<>();
        try {
//            System.out.println(typepara.contains("All"));
//            System.out.println("All"=="All");
//            if(typepara.contains("All")) {
//                System.out.println("All");
//                ingredients = ingredient.read();
//            }else{
//                System.out.println("Not All");
//                ingredients = ingredient.readbytype(typepara);
//            }
              ingredients = ingredient.read();


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String jsonresponce = new Gson().toJson(ingredients);
        outputResponse.sendresponse(resp,jsonresponce,200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ingredient ingredientitem = new Gson().fromJson(req.getReader(), ingredient.class);
        if(ingredient.addIngredients(ingredientitem) == 1){
            outputResponse.sendresponse(resp,"{'message':'success'}",200);
        }else{
            outputResponse.sendresponse(resp, null, 406);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        ingredient ingredientitem = new Gson().fromJson(req.getReader(),ingredient.class);
        if(ingredient.updateStockLevels(ingredientitem) == 1){
            outputResponse.sendresponse(resp,"",204);
        }else{
            outputResponse.sendresponse(resp,"",406);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req,HttpServletResponse resp){
        String requestUrl = req.getRequestURI();
        int id = Integer.parseInt(requestUrl.substring("/Server_war_exploded/Manager/ingredients/".length()));
        if(ingredient.deleteIngredients(id)==0){
            outputResponse.sendresponse(resp,null,406);
        }
    }


}
