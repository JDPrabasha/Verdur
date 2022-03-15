package cashier.CashierPayments;

public class CashierPayments {
    private int orderID;
    private int riderID;
    private int dueAmount;
    private String assignedTime;
    private String status;
    private String type;
    private String paymentCompleted;

    public CashierPayments(int orderID, int riderID, int dueAmount, String assignedTime, String status, String type, String paymentCompleted) {
        this.orderID = orderID;
        this.riderID = riderID;
        this.dueAmount = dueAmount;
        this.assignedTime = assignedTime;
        this.status = status;
        this.type = type;
        this.paymentCompleted = paymentCompleted;
    }
}
