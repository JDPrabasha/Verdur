package kitchenmanager.restockorder;



public class supplier {

    private int supplierid;
    private String suppliername;
    private int price;

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public supplier(int supplierid, String suppliername) {
        this.supplierid = supplierid;
        this.suppliername = suppliername;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public supplier(int supplierid, String suppliername, int price) {
        this.supplierid = supplierid;
        this.suppliername = suppliername;
        this.price = price;
    }
}
