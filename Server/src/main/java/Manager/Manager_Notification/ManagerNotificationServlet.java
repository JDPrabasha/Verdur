package Manager.Manager_Notification;

import Manager.Output.outputResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/Manager/notification/*")
public class ManagerNotificationServlet extends HttpServlet {
    private ManagerNotificationDAO notifications;

    public void init(){
        notifications = new ManagerNotificationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<ManagerNotification> notificationList = notifications.getmanagerNotifications();
        String json = new Gson().toJson(notificationList);
        outputResponse.sendresponse(resp,json,200);
    }

    @Override
    protected void doDelete(HttpServletRequest req,HttpServletResponse resp){
        String requestUrl = req.getRequestURI();
        int id = Integer.parseInt(requestUrl.substring("/Server_war_exploded/Manager/notification/".length()));
        if(notifications.deleteNotification(id)!=0){
            outputResponse.sendresponse(resp,null,200);
        }else {
            outputResponse.sendresponse(resp,null,404);
        };
    }
}
