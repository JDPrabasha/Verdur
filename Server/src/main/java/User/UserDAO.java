package User;

import User.ConnectionFactory.DB;


import java.sql.*;
import java.time.LocalDate;
import java.util.Random;


public class UserDAO {


    private static final String FIND_USER = "select * from login where email =? and password=?";
    private static final String GET_ROLE = "select role from user where userID = ?";
    private static final String CHECK_USER_CODE = "select * from users where username =? and code=?";
    private static final String INSERT_USER = "INSERT INTO user (firstName,lastName,contact, dateAdded, role) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer (userID) VALUES (?)";
    private static final String ADD_LOGIN = "INSERT INTO login (userID, email, password, code) VALUES (?, ?, ?, ?)";
    private static final String VERIFY_USER = "UPDATE users SET verified=1 WHERE username=? AND code=?";
    private static final String RESET_CODE = "UPDATE users SET code=? WHERE username=?";
    private static final String GET_NAVBAR_DETAILS = "SELECT firstName, lastName, image, c.custID from customer c join user u on c.userID=u.userID join avatar a on c.avatarID = a.avatarID where c.userID=?";
    private static final String GET_EMPLOYEE_DETAILS = "SELECT firstName, lastName, photo, e.empID from user u join employee e on e.userID=u.userID where e.userID=?";
    private static final String GET_SUPPLIER_DETAILS = "SELECT firstName, lastName, image as photo, s.supplierID as empID from user u join supplier s on s.userID=u.userID where s.userID=?";
    private static final String GET_CUSTOMER_DETAILS = "select address ,contact,email from customer c join user u on c.userID =u.userID join login l on u.userID =l.userID where c.custID =? ";
    private static final String UPDATE_CUSTOMER_DETAILS = "update customer c join user u on c.userID = u.userID set contact =?,address=?,avatar=(select avatarID from avatar where image = ?) where c.custID =? ";


    private Connection conn;

    public UserDAO() {
        try {
            this.conn = DB.initializeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public User findUser(String username, String password) throws SQLException {
        System.out.println(FIND_USER);
        User user = null;
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(FIND_USER)) {
            preparedStatement.setString(1, username);
            String hashPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            preparedStatement.setString(2, hashPassword);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                boolean verified = rs.getBoolean("verified");
                Integer id = rs.getInt("userID");
                user = new User(id, username, password, verified);
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public User checkUserCode(String username, String code) throws SQLException {
        System.out.println(CHECK_USER_CODE);
        User user = null;
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(CHECK_USER_CODE)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, code);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {


                String password = rs.getString("password");


                user = new User(username, password);
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }


    public User getUserDetails(int userID) throws SQLException {

        User user = null;
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(GET_NAVBAR_DETAILS)) {
            preparedStatement.setInt(1, userID);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {


                String name = rs.getString("firstName") + " " + rs.getString(("lastName"));
                String avatar = rs.getString("image");
                Integer id = rs.getInt("custID");


                user = new User(id, avatar, name);
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public User getCustomerDetails(int custID) throws SQLException {

        User user = null;
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(GET_CUSTOMER_DETAILS)) {
            preparedStatement.setInt(1, custID);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {


                String contact = rs.getString("contact");
                String address = rs.getString("address");


                user = new User.UserBuilder().setContact(contact).setAddress(address).build();

            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public void updateCustomerDetails(int custID, User u) throws SQLException {

        User user = null;
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(UPDATE_CUSTOMER_DETAILS)) {
            conn.setAutoCommit(false);
            preparedStatement.setString(1, user.getContact());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getAvatar());
            preparedStatement.setInt(4, custID);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);


        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }

    }

    public User getEmployeeDetails(int userID, boolean supplier) throws SQLException {

        User user = null;
        String QueryString;
        if (supplier) {
            QueryString = GET_SUPPLIER_DETAILS;
        } else {
            QueryString = GET_EMPLOYEE_DETAILS;
        }
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(QueryString)) {
            preparedStatement.setInt(1, userID);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {


                String name = rs.getString("firstName") + " " + rs.getString(("lastName"));
                String avatar = rs.getString("photo");
                Integer id = rs.getInt("empID");

                user = new User(id, avatar, name);
            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public void addUser(User user, int code) throws SQLException {
        System.out.println(INSERT_USER);
        try (
                PreparedStatement preparedStatement = this.conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            String firstName = user.getName().split(" ")[0];
            String lastName = user.getName().split(" ")[1];
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, user.getContact());

            LocalDate date = LocalDate.now();
            preparedStatement.setDate(4, Date.valueOf(date));
            preparedStatement.setString(5, "Customer");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            Integer gid = 0;
            ResultSet gkeys = preparedStatement.getGeneratedKeys();
            if ((gkeys != null) && (gkeys.next())) {
                gid = gkeys.getInt(1);
                System.out.println(gid);
            }
            PreparedStatement secondStatement = this.conn.prepareStatement(ADD_LOGIN);
            secondStatement.setInt(1, gid);
            secondStatement.setString(2, user.getUsername());
            String hashPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
            secondStatement.setString(3, hashPassword);
            secondStatement.setInt(4, code);

            System.out.println(secondStatement);
            secondStatement.executeUpdate();

            PreparedStatement thirdStatement = this.conn.prepareStatement(INSERT_CUSTOMER);
            secondStatement.setInt(1, gid);
            System.out.println(thirdStatement);
            thirdStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
    }

    public String getRole(int userID) throws SQLException {

        String role = null;
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(GET_ROLE)) {
            preparedStatement.setInt(1, userID);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {


                role = rs.getString("role");


            }
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return role;
    }

    public void verifyUser(User user) throws SQLException {
        System.out.println(VERIFY_USER);
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(VERIFY_USER)) {
            conn.setAutoCommit(false);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getCode());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            PreparedStatement secondStatement = this.conn.prepareStatement(VERIFY_USER);
            preparedStatement.setInt(1, 123456);
            preparedStatement.setString(2, user.getUsername());
            System.out.println(secondStatement);
            secondStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

