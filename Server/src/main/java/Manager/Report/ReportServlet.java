package Manager.Report;



import Manager.Output.outputResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/Manager/Report/*")
public class ReportServlet extends HttpServlet {
    private ReportDAO report;

    public void init(){
        report = new ReportDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String requestUrl = req.getRequestURI();
        String type = requestUrl.substring("/Server_war_exploded/Manager/Report/".length());
//        System.out.println(type);
        if(type.equals("dish")){
            List<reportsDish> topDish = report.getTopDish();
            String json = new Gson().toJson(topDish);
            outputResponse.sendresponse(resp,json,200);
        }else if(type.equals("reports")){
            JsonObject rootElement = new JsonObject();

            JsonElement topSuppliers = new Gson().toJsonTree(report.getTopSuppliers());
            JsonElement topDish = new Gson().toJsonTree(report.getTopDish());
            JsonElement totalDishSales = new Gson().toJsonTree(report.getAllTotalDishSales());
            JsonElement totalSupplierOrders = new Gson().toJsonTree(report.getAllSupplierOrders());
            JsonElement lastRevenue = new Gson().toJsonTree(report.getLastMonthSales());

            rootElement.add("topDish",topDish);
            rootElement.add("topSupplier",topSuppliers);
            rootElement.add("totalDishSales",totalDishSales);
            rootElement.add("totalSupplierOrders",totalSupplierOrders);
            rootElement.add("lastRevenue",lastRevenue);


            outputResponse.sendresponse(resp,rootElement.toString(),200);
        }else if(type.equals("home")){
            JsonObject rootElement = new JsonObject();

            JsonElement topDish = new Gson().toJsonTree(report.getTopDishHome());
            JsonElement topCustomer = new Gson().toJsonTree(report.getTopCustomersHome());
            JsonElement totalSales = new Gson().toJsonTree(report.getTotalSales());
            JsonElement totalExpenses = new Gson().toJsonTree(report.getTotalExpenses());
            JsonElement todayRevenue = new Gson().toJsonTree(report.getTodaysRevenue());
            JsonElement lastRevenue = new Gson().toJsonTree(report.getLastMonthSales());
            rootElement.add("topDish",topDish);
            rootElement.add("topCustomers",topCustomer);
            rootElement.add("totalSales",totalSales);
            rootElement.add("totalExpenses",totalExpenses);
            rootElement.add("todaysRevenue",todayRevenue);
            rootElement.add("lastRevenue",lastRevenue);

            outputResponse.sendresponse(resp, rootElement.toString(), 200);
        }

    }

}
