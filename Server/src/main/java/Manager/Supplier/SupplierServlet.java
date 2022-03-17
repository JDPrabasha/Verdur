package Manager.Supplier;

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

@WebServlet("/Manager/Supplier")
public class SupplierServlet extends HttpServlet {
    private SupplierDAO supplier;

    public void init(){
        supplier = new SupplierDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Supplier> supplierList = new ArrayList<>();
        try{
            supplierList = supplier.getSuppliers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String json = new Gson().toJson(supplierList);

        outputResponse(resp,json,200);

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

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
