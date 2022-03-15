package Manager.Report;

public class reportSupplier {
    private int supplierID;
    private String name;
    private String Org;
    private int ordersDone;
    private double acceptPercentage;
    private int amount;

    public reportSupplier(int supplierID, String name, String org, int ordersDone,double acceptPercentage, int amount) {
        this.supplierID = supplierID;
        this.name = name;
        Org = org;
        this.ordersDone = ordersDone;
        this.acceptPercentage = acceptPercentage;
        this.amount = amount;
    }
}
