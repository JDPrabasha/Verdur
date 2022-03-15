package Manager.employee;

import Manager.Dao.DB;
import Manager.Output.outputResponse;
import com.google.gson.Gson;
//import org.apache.naming.factory.SendMailFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/Manager/employee/*")
public class EmployeeServlet extends HttpServlet {
    private DB db;
    private EmployeeDAO employee;
    private SendMail mailer;

    public void init(){
        db = new DB();
        employee = new EmployeeDAO(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Employee> employeearr = new ArrayList<>();

        String s = req.getParameter("search");
        try{
            if(s!=null){
                employeearr = employee.readbyname("%"+s+"%");
            }else{
                employeearr = employee.readallnew();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String json = new Gson().toJson(employeearr);

        outputResponse.sendresponse(resp,json,200);
    }

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Employee employeeItem = new Gson().fromJson(req.getReader(),Employee.class);
        String json;
        int code = getRandom();
        if (employee.addnew(employeeItem,code) != 0) {
            json = "{\"message:Success\"}";
            mailer = new SendMail(employeeItem.getEmail(),code);
            mailer.sendEmail();
            outputResponse.sendresponse(resp,json,201);
        } else {
            json =  "{\"message:Failed\"}";
            outputResponse.sendresponse(resp, json, 406);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Employee employeeItem = new Gson().fromJson(req.getReader(),Employee.class);
        if(employee.updateEmployee(employeeItem) != 0 ){
            outputResponse.sendresponse(resp,"",204);
        }else{
            outputResponse.sendresponse(resp,"",406);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        String requestUrl = req.getRequestURI();
        String id = requestUrl.substring("/Server_war_exploded/Manager/Manager.employee/".length());
        if(employee.deleteEmployee(Integer.parseInt(id))){
            outputResponse.sendresponse(resp,null,200);
        }else{
            outputResponse.sendresponse(resp,null,204);
        }
    }


    public int getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return Integer.parseInt(String.format("%06d", number));
    }
}
