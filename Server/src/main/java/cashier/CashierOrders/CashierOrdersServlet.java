package cashier.CashierOrders;


import User.ConnectionFactory.DB;
import com.google.gson.Gson;
import cashier.rider.rider;
//import order.orderdao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Cashier/CashierOrders/*")
public class CashierOrdersServlet extends HttpServlet {
    DB db = new DB();
    CashierOrdersDAO cashierOrdersDAO = new CashierOrdersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<CashierOrders> orderitem = new ArrayList<>();
        List<rider> riderList = new ArrayList<>();


        String type = req.getParameter("type");//status
        String id = req.getParameter("id");//status
        System.out.println(type);
        try {
            if (id == null) {
                if (type.equals("all")) {
                    orderitem = cashierOrdersDAO.read();
                } else if (type.equals("cooked")) {
                    orderitem = cashierOrdersDAO.readcookedorders();

                } else if (type.equals("ongoing")) {
                    orderitem = cashierOrdersDAO.readongoingorders();
                } else if (type.equals("confirmrider")) {
//                    riderlist = cashierOrdersDAO.readconfirmriderlist();
                    riderList = cashierOrdersDAO.readconfirmriderlist();
                }


            } else {
                orderitem = cashierOrdersDAO.readriderorders(id);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        System.out.println(orderitem.isEmpty());
        if (!orderitem.isEmpty()) {
            String orderresponce = new Gson().toJson(orderitem);
            outputResponse(resp, orderresponce, 200);
        } else {
            System.out.println("false una");
            outputResponse(resp, new Gson().toJson(riderList), 200);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String requestUrl = req.getRequestURI();
        String type = requestUrl.substring("/Server_war_exploded/Cashier/CashierOrders/".length());
        System.out.println(type);
        System.out.println(type.equals("reject"));

        if (type.equals("confirm")) {
            String id = req.getParameter("deliveryID");//status
            System.out.println(id);
            try {
                cashierOrdersDAO.confirm(Integer.parseInt(id));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        } else if (type.equals("reject")) {
            String id = req.getParameter("orderID");
            String reason = req.getParameter("reason");
            cashierOrdersDAO.reject(Integer.parseInt(id), reason);

        } else if (type.equals("accept")){
            CashierOrders c = new Gson().fromJson(req.getReader(),CashierOrders.class);
            try {
                cashierOrdersDAO.accept(c);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            rider orders = new Gson().fromJson(req.getReader(), rider.class);

            if (cashierOrdersDAO.assignrider(orders) == null) {
                outputResponse(resp, "", 406);
            }
            ;

            System.out.println();


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

