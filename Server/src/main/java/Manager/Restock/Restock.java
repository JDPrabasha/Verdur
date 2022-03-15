package Manager.Restock;

public class Restock {
    private int restockID;
    private String item;
    private String type;
    private String supplier;
    private String issueddate;
    private String approvalstatus;
    private String timeremain;
    private int quantity;
    private int price;
    private String status;

    public Restock(int restockID, String item, String type, String supplier, String issueddate, String status, String timeremain, int quantity, int price) {
        this.restockID = restockID;
        this.item = item;
        this.type = type;
        this.supplier = supplier;
        this.issueddate = issueddate;
        this.approvalstatus = status;
        this.timeremain = timeremain;
        this.quantity = quantity;
        this.price = price;
    }

    public Restock(int restockID, String item, String type, String supplier, String issueddate, String timeremain, int quantity, int price, String status) {
        this.restockID = restockID;
        this.item = item;
        this.type = type;
        this.supplier = supplier;
        this.issueddate = issueddate;
        this.timeremain = timeremain;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public int getRestockID() {
        return restockID;
    }

    public void setRestockID(int restockID) {
        this.restockID = restockID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getIssueddate() {
        return issueddate;
    }

    public void setIssueddate(String issueddate) {
        this.issueddate = issueddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeremain() {
        return timeremain;
    }

    public void setTimeremain(String timeremain) {
        this.timeremain = timeremain;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(String approvalstatus) {
        this.approvalstatus = approvalstatus;
    }
}
