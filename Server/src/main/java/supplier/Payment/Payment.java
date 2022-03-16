package supplier.Payment;

public class Payment {
    private int invoiceID;
    private String invoiceDate;
    private int totalAmount;
    private String requestedDate;
    private String deliveryDate;
    private String status;

    public Payment(int invoiceID, String invoiceDate, int totalAmount, String requestedDate, String deliveryDate, String status) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.requestedDate = requestedDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "invoiceID=" + invoiceID +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", requestedDate='" + requestedDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
