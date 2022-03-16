package User;

import User.ConnectionFactory.DB;


import java.sql.*;
import java.time.LocalDate;


public class UserDAO {


    private static final String FIND_USER = "select * from login where email =? and password=?";
    private static final String GET_ROLE = "select role from user where userID = ?";
    private static final String CHECK_USER_CODE = "select * from users where username =? and code=?";
    private static final String INSERT_USER = "INSERT INTO user (firstName,lastName,contact, dateAdded, role) VALUES (?, ?, ?, ?, ?)";
    private static final String ADD_LOGIN = "INSERT INTO login (userID, email, password, code) VALUES (?, ?, ?, ?)";
    private static final String VERIFY_USER = "UPDATE users SET verified=1 WHERE username=? AND code=?";
    private static final String GET_NAVBAR_DETAILS = "SELECT firstName, lastName, image, c.custID from customer c join user u on c.userID=u.userID join avatar a on c.avatarID = a.avatarID where c.userID=?";
    private static final String GET_EMPLOYEE_DETAILS = "SELECT firstName, lastName, photo, e.empID from user u join employee e on e.userID=u.userID where e.userID=?";
    private static final String GET_CUSTOMER_DETAILS = "select address ,contact,email from customer c join user u on c.userID =u.userID join login l on u.userID =l.userID where c.custID =? ";
    private static final String UPDATE_CUSTOMER_DETAILS = "update customer c join user u on c.userID = u.userID set contact =?,address=?,avatar=(select avatarID from avatar where image = ?) where c.custID =? ";


    public UserDAO() {
    }


    public User findUser(String username, String password) throws SQLException {
        System.out.println(FIND_USER);
        // try-with-resource statement will auto close the connection.
        User user = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER)) {
            preparedStatement.setString(1, username);
            String hashPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            preparedStatement.setString(2, hashPassword);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                boolean verified = rs.getBoolean("verified");
                Integer id = rs.getInt("userID");
                user = new User(id, username, password, verified);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public User checkUserCode(String username, String code) throws SQLException {
        System.out.println(CHECK_USER_CODE);
        // try-with-resource statement will auto close the connection.
        User user = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_CODE)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, code);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {


                String password = rs.getString("password");

//                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
//                System.out.println("hey im " + sha256hex);

                user = new User(username, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }


    public User getUserDetails(int userID) throws SQLException {

        // try-with-resource statement will auto close the connection.
        User user = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(GET_NAVBAR_DETAILS)) {
            preparedStatement.setInt(1, userID);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {


                String name = rs.getString("firstName") + " " + rs.getString(("lastName"));
                String avatar = rs.getString("image");
                Integer id = rs.getInt("custID");
//                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
//                System.out.println("hey im " + sha256hex);

                user = new User(id, avatar, name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public User getCustomerDetails(int custID) throws SQLException {

        // try-with-resource statement will auto close the connection.
        User user = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_DETAILS)) {
            preparedStatement.setInt(1, custID);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {


                String contact = rs.getString("contact");
                String address = rs.getString("address");


                user = new User.UserBuilder().setContact(contact).setAddress(address).build();

            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public void updateCustomerDetails(int custID, User u) throws SQLException {

        // try-with-resource statement will auto close the connection.
        User user = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_DETAILS)) {
            preparedStatement.setString(1, user.getContact());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getAvatar());
            preparedStatement.setInt(4, custID);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.

        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }

    }

    public User getEmployeeDetails(int userID) throws SQLException {

        // try-with-resource statement will auto close the connection.
        User user = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_DETAILS)) {
            preparedStatement.setInt(1, userID);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {


                String name = rs.getString("firstName") + " " + rs.getString(("lastName"));
                String avatar = rs.getString("photo");
                Integer id = rs.getInt("empID");
//                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
//                System.out.println("hey im " + sha256hex);

                user = new User(id, avatar, name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return user;
    }

    public void addUser(User user, int code) throws SQLException {
        System.out.println(INSERT_USER);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DB.initializeDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
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
            PreparedStatement secondStatement = connection.prepareStatement(ADD_LOGIN);
            secondStatement.setInt(1, gid);
            secondStatement.setString(2, user.getUsername());
            String hashPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
            secondStatement.setString(3, hashPassword);
            secondStatement.setInt(4, code);

            System.out.println(secondStatement);
            secondStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
    }

    public String getRole(int userID) throws SQLException {

        // try-with-resource statement will auto close the connection.
        String role = null;
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE)) {
            preparedStatement.setInt(1, userID);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {


                role = rs.getString("role");


            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return role;
    }

    public void verifyUser(User user) throws SQLException {
        System.out.println(VERIFY_USER);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DB.initializeDB(); PreparedStatement preparedStatement = connection.prepareStatement(VERIFY_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getCode());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
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
