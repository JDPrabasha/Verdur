package Manager.Complaints;

import Manager.Output.outputResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Manager/complaints/*")
public class ComplaintsServlet extends HttpServlet {

    private ComplaintsDAO complaintD;

    public void init(){
         complaintD = new ComplaintsDAO();
     }

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String id  = req.getParameter("id");
        if(id==null){
            String json = new Gson().toJson(complaintD.getComplaints());
            outputResponse.sendresponse(resp,json,200);
        }else{
            String json = new Gson().toJson(complaintD.getAComplaint(Integer.parseInt(id)));
            outputResponse.sendresponse(resp,json,200);
        }

     }
}
