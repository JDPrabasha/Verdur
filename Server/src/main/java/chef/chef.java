package chef;

import kitchenmanager.kitchenmanagerorder.orderkm;

import java.util.List;

public class chef {
    private int chefid;
    private String chefname;
    private String chefimage;
    private String status;
    private List<orderkm> order;
    private int orderid;
    private String timestamp;

    public chef(int chefid, String chefname, String chefimage, String status) {
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;
        this.status = status;
    }

    public chef(int chefid, String chefname, String chefimage,List<orderkm> order) {
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;
        this.order = order;
    }

    public chef(int chefid, String chefname, String chefimage) {
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;
    }

    public int getChefid() {
        return chefid;
    }

    public void setChefid(int chefid) {
        this.chefid = chefid;
    }

    public String getChefname() {
        return chefname;
    }

    public void setChefname(String chefname) {
        this.chefname = chefname;
    }

    public String getChefimage() {
        return chefimage;
    }

    public void setChefimage(String chefimage) {
        this.chefimage = chefimage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<orderkm> getOrder() {
        return order;
    }

    public void setOrder(List<orderkm> order) {
        this.order = order;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


