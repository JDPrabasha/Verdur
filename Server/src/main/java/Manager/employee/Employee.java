package Manager.employee;

public class Employee {
    private int employeeID;
    private String name;
    private String status;
    private String experience;
    private String email;
    private String contact_no;
    private String Address;
    private String idno;
    private String dob;
    private String hiring_date;
    private String role;
    private String image;
    private String password;
    private int code;

    public Employee(int employeeID, String password, int code) {
        this.employeeID = employeeID;
        this.password = password;
        this.code = code;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHiring_date() {
        return hiring_date;
    }

    public void setHiring_date(String hiring_date) {
        this.hiring_date = hiring_date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee(int employeeID, String name, String status, String experience, String email, String contact_no, String address, String idno, String dob, String hiring_date, String role,String image) {
        this.employeeID = employeeID;
        this.name = name;
        this.status = status;
        this.experience = experience;
        this.email = email;
        this.contact_no = contact_no;
        Address = address;
        this.idno = idno;
        this.dob = dob;
        this.hiring_date = hiring_date;
        this.role = role;
        this.image = image;
    }

    public Employee(String name, String email, String contact_no, String address, String idno, String dob, String hiring_date, String role,String image,String password) {
        this.name = name;
        this.email = email;
        this.contact_no = contact_no;
        Address = address;
        this.idno = idno;
        this.dob = dob;
        this.hiring_date = hiring_date;
        this.role = role;
        this.image = image;
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
