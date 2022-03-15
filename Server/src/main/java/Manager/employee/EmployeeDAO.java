package Manager.employee;

import Manager.Dao.DB;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection conn;
    static String readquery         = "SELECT * FROM employee e INNER JOIN user u INNER JOIN login l WHERE e.userID = u.userID and u.userID = l.userID";
    static String readbynameq       = "SELECT * FROM employee e INNER JOIN user u INNER JOIN login l WHERE e.userID = u.userID and u.userID = l.userID  and u.name LIKE ?";
    static String userquery         = "INSERT INTO user (role,firstName,lastName,contact,dateAdded) VALUES (?,?,?,?,?)";
    static String loginquery        = "INSERT INTO login (userID,email,password,code) VALUES (?,?,?,?)";
    static String employeequery     = "INSERT INTO employee (userID,address,photo,nic,dob) VALUES (?,?,?,?,?)";
    static String checkcodequery    = "SELECT (email) FROM login WHERE email = ? and code = ?";
    static String validatequery     = "UPDATE login SET code = NULL, verified = ?,password = ? WHERE email = ?";
//    static String updatepassquery   = "UPDATE login SET password = ? WHERE userID = ?";
//    static String checkcodequery    = "SELECT (userID) FROM user WHERE userID=28 and code =986935";
    static String userUpdateQuery   = "UPDATE user SET firstName = ?,lastName = ?, contact = ? WHERE userID = ?";
    static String employeeUpdateQuery = "UPDATE employee SET address = ? WHERE empID = ?";

    static String getUserIDQuery    = "select userID from employee where empID = ?";

    static String deleteUserQuery = "delete from user where userID = ?";



    public EmployeeDAO(DB db){
        try{
            this.conn = db.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> readallnew() throws SQLException{
        List <Employee> employeearray = new ArrayList<>();

        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(readquery);
        while (rs.next()){
            int employeeID = rs.getInt("empID");
//            String  name = rs.getString("name");
            String  name = rs.getString("firstName")+ " "+rs.getString("lastName");
            String status = "Offline";
            String experience = "";
            String email = rs.getString("email");
//            String contact_no = rs.getString("contact_No");
            String contact_no = rs.getString("contact");
            String Address = rs.getString("address");
            String idno = rs.getString("nic");
            String dob = rs.getString("dob");
            String hiring_date = rs.getString("dateAdded");
//            String hiring_date = "2020-10-01";
            String role = rs.getString("role");
            String image = rs.getString("photo");

            employeearray.add(new Employee(employeeID,name,status,experience,email,contact_no,Address,idno,dob,hiring_date,role,image));

        }
        return employeearray;
    }

    public List<Employee> readbyname(String s) throws SQLException {
        List <Employee> employeearray = new ArrayList<>();
        PreparedStatement st = this.conn.prepareStatement(readbynameq);
        st.setString(1,s);
        ResultSet rs = st.executeQuery();

        while (rs.next()){
            int employeeID = rs.getInt("empID");
            String  name = rs.getString("name");
            String status = "Offline";
            String experience = "";
            String email = rs.getString("email");
            String contact_no = rs.getString("contact_No");
            String Address = rs.getString("address");
            String idno = rs.getString("nic");
            String dob = rs.getString("dob");
//            String hiring_date = rs.getString("hiring_date");
            String hiring_date = "2020-10-01";
            String role = rs.getString("role");
            String image = rs.getString("photo");

            employeearray.add(new Employee(employeeID,name,status,experience,email,contact_no,Address,idno,dob,hiring_date,role,image));

        }
        return employeearray;
    }

    public int addnew(Employee e,int code) {
//        PreparedStatement st1 = this.conn.prepareStatement("INSERT INTO user (role,name,contact_No) VALUES (\"Kitchen Manager\",\"Test\",\"123456\")",Statement.RETURN_GENERATED_KEYS);
        int rs3 =0;
        try {
              conn.setAutoCommit(false);
              PreparedStatement st1 = this.conn.prepareStatement(userquery, Statement.RETURN_GENERATED_KEYS);
              st1.setString(1, e.getRole());
              st1.setString(2, e.getName().split(" ")[0]);
              st1.setString(3, e.getName().split(" ")[1]);
              st1.setString(4, e.getContact_no());
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
              LocalDateTime now = LocalDateTime.now();
              st1.setString(5, dtf.format(now));
              st1.executeUpdate();
              ResultSet rs1 = st1.getGeneratedKeys();
              int userID = 0;
              while (rs1.next()) {
                  userID = rs1.getInt(1);
              }

              System.out.println(userID);
              PreparedStatement st2 = this.conn.prepareStatement(loginquery);
              st2.setInt(1, userID);
              st2.setString(2, e.getEmail());
              st2.setString(3, e.getPassword());
              st2.setInt(4,code);
              int rs2 = st2.executeUpdate();
              //        userID,address,photo,nic.dob
              PreparedStatement st3 = this.conn.prepareStatement(employeequery);
              st3.setInt(1, userID);
              st3.setString(2, e.getAddress());
              st3.setString(3, e.getImage());
              st3.setString(4, e.getIdno());
              st3.setString(5, e.getDob());
              rs3 = st3.executeUpdate();
              this.conn.commit();
              this.conn.setAutoCommit(true);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
              if(this.conn!=null){
                  try {
                      System.err.print("Transaction is being rolled back");
                      this.conn.rollback();
                      this.conn.setAutoCommit(true);
                  } catch (SQLException sqlException) {
                      sqlException.printStackTrace();
                  }
              }
          }

        return rs3;
//        System.out.println(e.getAddress());
//        System.out.println(e.getDob());
//        return 0;
    }

    public int getUserID(int empID){
        try {
            PreparedStatement getUserIDQ = this.conn.prepareStatement(getUserIDQuery);
            getUserIDQ.setInt(1,empID);
            ResultSet rs = getUserIDQ.executeQuery();
            int id = 0;
            while (rs.next()){
                id = rs.getInt("userID");
            }
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }



    public int updateEmployee(Employee e){
        try {
            conn.setAutoCommit(false);
            PreparedStatement updateEmployeeQ = this.conn.prepareStatement(employeeUpdateQuery);
            updateEmployeeQ.setString(1,e.getAddress());
            updateEmployeeQ.setInt(2,e.getEmployeeID());
            updateEmployeeQ.executeUpdate();

            PreparedStatement updateUserQ = this.conn.prepareStatement(userUpdateQuery);
            updateUserQ.setString(1,e.getName().split(" ")[0]);
            updateUserQ.setString(2,e.getName().split(" ")[1]);
            updateUserQ.setString(3,e.getContact_no());
            updateUserQ.setInt(4,getUserID(e.getEmployeeID()));
            updateUserQ.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if(this.conn!=null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return 0;
    }

    public int codevalid(String email, int code) {
        int out = 0;
//        System.out.println("id: "+id+" code: "+code);
        try{
            PreparedStatement st = this.conn.prepareStatement(checkcodequery,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            st.setString(1,email);
            st.setInt(2,code);
            ResultSet rs = st.executeQuery();
            rs.last();
            out = rs.getRow();
            rs.beforeFirst();
//            System.out.println(out);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
        return out;
    }

    public int validate(Employee e) {
        int out=0;
        try{
            PreparedStatement st1 = this.conn.prepareStatement(validatequery);
//            st1.setString(1,NULL);
            st1.setInt(1,1);
            st1.setString(2,e.getPassword());
            st1.setString(3,e.getEmail());
            out= st1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
        return out;
    }

    public boolean deleteEmployee(int empID){
        int userID = getUserID(empID);
        try {
            PreparedStatement deleteUserQ = this.conn.prepareStatement(deleteUserQuery);
            deleteUserQ.setInt(1,userID);
            deleteUserQ.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
