package Manager.RestocksOrders;

public class RestockOrders {
    private int restockID;
    private String item;
    private String supplier;
    private String comdate;
    private String status;
    private int amount;

    public RestockOrders(int restockID, String item, String supplier, String comdate, String status, int amount) {
        this.restockID = restockID;
        this.item = item;
        this.supplier = supplier;
        this.comdate = comdate;
        this.status = status;
        this.amount = amount;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getComdate() {
        return comdate;
    }

    public void setComdate(String comdate) {
        this.comdate = comdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
