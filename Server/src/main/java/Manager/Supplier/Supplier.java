package Manager.Supplier;

public class Supplier {
    private int ID;
    private String image;
    private String name;
    private String org;
    private int ordersDue;
    private int ordersDone;
    private String email;
    private String contact;
    private String location;
    private String joinedon;
    private int pendingPay;

    public Supplier(int ID, String image, String name, String org, int ordersDue, int ordersDone, String email, String contact, String location, String joinedon, int pendingPay) {
        this.ID = ID;
        this.image = image;
        this.name = name;
        this.org = org;
        this.ordersDue = ordersDue;
        this.ordersDone = ordersDone;
        this.email = email;
        this.contact = contact;
        this.location = location;
        this.joinedon = joinedon;
        this.pendingPay = pendingPay;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public int getOrdersDue() {
        return ordersDue;
    }

    public void setOrdersDue(int ordersDue) {
        this.ordersDue = ordersDue;
    }

    public int getOrdersDone() {
        return ordersDone;
    }

    public void setOrdersDone(int ordersDone) {
        this.ordersDone = ordersDone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJoinedon() {
        return joinedon;
    }

    public void setJoinedon(String joinedon) {
        this.joinedon = joinedon;
    }

    public int getPendingPay() {
        return pendingPay;
    }

    public void setPendingPay(int pendingPay) {
        this.pendingPay = pendingPay;
    }
}
