package Manager.employee;

import Manager.Dao.DB;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/Employee/Verification")
public class EmployeeVerificationServlet extends HttpServlet {
    private DB db;
    private EmployeeDAO employee;

    public void init(){
        db = new DB();
        employee = new EmployeeDAO(db);
    }
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws IOException {
//        Employee employeeitem = new Gson().fromJson(req.getReader(),Employee.class);
        String email = req.getParameter("key1");
        int code = Integer.parseInt(req.getParameter("key2"));
        if(checkAvailability(email,code)){
//        if(checkAvailability(employeeitem.getEmail(),employeeitem.getCode())){
            outputResponse(resp,"{}",200);
        }else{
            outputResponse(resp,"{}",409);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        Employee employeeitem = new Gson().fromJson(req.getReader(),Employee.class);
//        System.out.println("came to verify");
        if(checkAvailability(employeeitem.getEmail(),employeeitem.getCode())){
            if(employee.validate(employeeitem)!=0){
                outputResponse(resp,"{}",200);
            }else{
                outputResponse(resp,"{}",409);
            }
        }else{
            outputResponse(resp,"{}",403);
        }
    }


    boolean checkAvailability(String email , int code){
        if(employee.codevalid(email,code)!=0){
            return true;
        }else{
            return false;
        }
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
