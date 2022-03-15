package kitchenmanager.restockorder;

import kitchenmanager.inventory.inventory;

import java.util.List;

public class restock {
    private int restockID;
    private int ingID;
    private String dueBy;
    private int quantity;
    private String status;
    private String requestedat;
    private String deadline;
    private String approvalstatus;
    private int expired;
    private String ingredientname;
    private int supplierid;
    private List<inventory> inventory;
    private List<supplier> supplier;


    public restock(int ingID, String dueBy, int quantity, String deadline, int supplierid) {
        this.ingID = ingID;
        this.dueBy = dueBy;
        this.quantity = quantity;
        this.deadline = deadline;
        this.supplierid = supplierid;
    }

    public int getRestockID() {
        return restockID;
    }

    public void setRestockID(int restockID) {
        this.restockID = restockID;
    }

    public int getIngID() {
        return ingID;
    }

    public void setIngID(int ingID) {
        this.ingID = ingID;
    }

    public String getDueBy() {
        return dueBy;
    }

    public void setDueBy(String dueBy) {
        this.dueBy = dueBy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedat() {
        return requestedat;
    }

    public void setRequestedat(String requestedat) {
        this.requestedat = requestedat;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(String approvalstatus) {
        this.approvalstatus = approvalstatus;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public List<inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<inventory> inventory) {
        this.inventory = inventory;
    }

    public List<kitchenmanager.restockorder.supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<kitchenmanager.restockorder.supplier> supplier) {
        this.supplier = supplier;
    }

    public restock(int restockID, int ingID, String status, String deadline, String ingredientname, String requestedat) {
        this.restockID = restockID;
        this.ingID = ingID;
        this.status = status;
        this.deadline = deadline;
        this.ingredientname = ingredientname;
        this.requestedat = requestedat;
    }

    public restock(List<inventory> inventory) {
        this.inventory = inventory;
    }

    public restock(List<inventory> inventory, List<kitchenmanager.restockorder.supplier> supplier) {
        this.inventory = inventory;
        this.supplier = supplier;
    }
}
