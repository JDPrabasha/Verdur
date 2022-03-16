package supplier.Stock;

public class Stock {

    private int supplierID;
    private int itemID;
    private String itemName;
    private String itemType;
    private String unit;
    private double quantity;
    private double price;
    private String dateAdded;

    public Stock(int itemID, String itemName, String itemType, double quantity, double price, String dateAdded,String unit) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemType = itemType;
        this.quantity = quantity;
        this.price = price;
        this.dateAdded = dateAdded;
        this.unit = unit;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "supplierID=" + supplierID +
                ", itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
