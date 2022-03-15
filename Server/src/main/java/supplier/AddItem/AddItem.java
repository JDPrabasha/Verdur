package supplier.AddItem;

public class AddItem {

    private int supplierID;
    private int ingID;
    private int quantity;
    private int price;
    private String dateAdded;

    public AddItem(int supplierID, int ingID, int quantity, int price) {
        this.supplierID = supplierID;
        this.ingID = ingID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getIngID() {
        return ingID;
    }

    public void setIngID(int ingID) {
        this.ingID = ingID;
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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }



}
