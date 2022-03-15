package kitchenmanager.inventory;



public class inventory {


    //    from ingredient table
    private int ingid;
    private String itemcode;
    private String itemtype;
    private String ingname;

    //    from stock table
    private int quantity;


    private int maxquantity;
    private int safelevel;
    private int restocklevel;



    private String image;
    //    from restock table
    private String restockdate;
    private String unit;




    public inventory(int ingid, String itemcode, String itemtype, String ingname, int quantity, int maxquantity, int safelevel, int restocklevel, String image, String restockdate) {
        this.ingid = ingid;
        this.itemcode = itemcode;
        this.itemtype = itemtype;
        this.ingname = ingname;
        this.quantity = quantity;
        this.maxquantity = maxquantity;
        this.safelevel = safelevel;
        this.restocklevel = restocklevel;
        this.image = image;
        this.restockdate = restockdate;
    }

    public inventory(int ingid, String itemcode, String itemtype, String ingname, int quantity, int maxquantity, int safelevel, int restocklevel, String image, String restockdate, String unit) {
        this.ingid = ingid;
        this.itemcode = itemcode;
        this.itemtype = itemtype;
        this.ingname = ingname;
        this.quantity = quantity;
        this.maxquantity = maxquantity;
        this.safelevel = safelevel;
        this.restocklevel = restocklevel;
        this.image = image;
        this.restockdate = restockdate;
        this.unit = unit;
    }

    public int getIngid() {
        return ingid;
    }

    public void setIngid(int ingid) {
        this.ingid = ingid;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getIngname() {
        return ingname;
    }

    public void setIngname(String ingname) {
        this.ingname = ingname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxquantity() {
        return maxquantity;
    }

    public void setMaxquantity(int maxquantity) {
        this.maxquantity = maxquantity;
    }

    public int getSafelevel() {
        return safelevel;
    }

    public void setSafelevel(int safelevel) {
        this.safelevel = safelevel;
    }

    public int getRestocklevel() {
        return restocklevel;
    }

    public void setRestocklevel(int restocklevel) {
        this.restocklevel = restocklevel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRestockdate() {
        return restockdate;
    }

    public void setRestockdate(String restockdate) {
        this.restockdate = restockdate;
    }


    //constructor



}
