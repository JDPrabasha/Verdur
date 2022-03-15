package Core;

public class User {
    private String username;

    public User() {

    }

    public User(UserBuilder userBuilder) {
        this.name = userBuilder.name;

        this.address = userBuilder.address;
        this.contact = userBuilder.contact;
    }

    public User(String contact, String address, String avatar) {
        this.contact = contact;
        this.address = address;
        this.avatar = avatar;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    private String password;
    private int code;
    private String contact;
    private String address;

    public User(String username, String password, String contact, String name) {
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.name = name;
    }


    private int id;
    private String avatar;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int id, String avatar, String name) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
    }

    private boolean verified;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password, int code, boolean verified) {

        this.username = username;
        this.password = password;
        this.code = code;
        this.verified = verified;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User(int id, String username, String password, boolean verified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.verified = verified;

    }

    public static class UserBuilder {

        private String name;
        private String contact;
        private String address;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setContact(String contact) {
            this.contact = contact;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }


        public UserBuilder() {

        }

        public User build() {
            return new User(this);
        }
    }
}
