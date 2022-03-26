package Manager.IngredientLogs;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static Manager.Output.outputResponse.sendresponse;

@WebServlet("/Manager/IngredientsLog/*")
public class IngredientsLogServlet extends HttpServlet {
    private IngredientsLogDAO ingredientsLog;

   public void init(){
       ingredientsLog = new IngredientsLogDAO();
   }

   @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
       List<IngredientsLog> allLogs = ingredientsLog.getAllLogs();
       String json = new Gson().toJson(allLogs);
       sendresponse(resp,json,200);
   }
}
