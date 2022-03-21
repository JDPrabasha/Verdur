package kitchenmanager.dishes;

import User.ConnectionFactory.DB;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/KitchenManager/dish/*")
public class dishservlet extends HttpServlet {
    DB db = new DB();
    dishdao dish = new dishdao(db);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<dish> dishes = new ArrayList<>();


        String s = req.getParameter("search");
        String id = req.getParameter("id");
        String ingId = req.getParameter("ingID");
        System.out.println(ingId);
        try {
            if (s != null) {
                System.out.println("1");
                dishes = dish.readname("%" + s + "%");
            } else if (ingId != null) {
                System.out.println("2");
                dishes = dish.readbyingredient(ingId);


            } else if (id == null) {
                System.out.println("3");
                dishes = dish.read();
            } else if (id != null) {
                System.out.println("4");
                // dishes = dish.readupdatedish(id);
                dishes.add(dish.readupdatedish(id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String dishitem = new Gson().toJson(dishes);
        outputResponse(resp, dishitem, 200);

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        dish dishitem = new Gson().fromJson(req.getReader(), dish.class);
//        String action = req.getPathInfo();
//        String type = req.getParameter("type");
        String ingid = req.getParameter("ingid");
        String requestUrl = req.getRequestURI();
        String type = requestUrl.substring("/Server_war_exploded/KitchenManager/dish/".length());
        System.out.println(type);
        System.out.println(ingid);

        if (type.equals("enable")) {
            System.out.println("enable");
            try {
                dish.enable(dishitem);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (type.equals("disable")) {
            System.out.println("disable");
            try {
                dish.dissable(dishitem);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
//        else if(type.contains("enableall")){
//            try {
//
//                dish.enableall(Integer.parseInt(ingid));
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//        }
        else if (type.contains("disableall")) {
            try {
                System.out.println("disablealldish");
                dish.disableall(Integer.parseInt(ingid));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if (type.equals("delete")) {
            try {

//            if(dishitem.getApprovalstatus() != null){
//                restock.managerApproval(restockitem);
//            }else if(restockitem.getStatus() !=null){
//                restock.managerDeliveryCompletion(restockitem);
//            }
                System.out.println("delete");
                dish.deletedish(dishitem);

                outputResponse(resp, "", 204);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                outputResponse(resp, "", 400);
            }

        }
//        System.out.println(restockitem.getApprovalstatus() != null);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        dish dishitem = new Gson().fromJson(req.getReader(), dish.class);
        String json;
        System.out.println(dishitem.getRequest());
        String requestUrl = req.getRequestURI();
        String type = requestUrl.substring("/Server_war_exploded/KitchenManager/dish/".length());
//        if (dish.add(dishitem) != 0) {
//            System.out.println(dishitem);
//            json = "{\"message:Success\"}";
//            outputResponse(res,json,200);
//        } else {
//            json =  "{\"message:Failed\"}";
//            outputResponse(res,json,406);
////        }
        if (type.equals("add")) {
            if (dish.add(dishitem) != 0) {
                System.out.println(dishitem);
                json = "{\"message:Success\"}";
                outputResponse(res, json, 200);
            } else {
                json = "{\"message:Failed\"}";
                outputResponse(res, json, 406);
            }
        } else if (type.equals("update")) {
            if (dish.createupdate(dishitem) != 0) {
                System.out.println(dishitem);
                json = "{\"message:Success\"}";
                outputResponse(res, json, 200);
            } else {
                json = "{\"message:Failed\"}";
                outputResponse(res, json, 406);
            }
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