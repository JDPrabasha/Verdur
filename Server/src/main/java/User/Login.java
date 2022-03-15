package Core;

public class Login {
    private int id;
    private String role;

    public Login(int id, String role, String token) {
        this.id = id;
        this.token = token;
        this.role = role;
    }

    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
