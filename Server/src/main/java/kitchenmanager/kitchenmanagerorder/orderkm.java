package kitchenmanager.kitchenmanagerorder;

import kitchenmanager.dishes.dish;

import java.util.List;

public class orderkm {
    private int orderid;
    private int chefid;
    private String chefname;
    private String chefimage;
    private String status;
    private List<dish> dishitem;

//    public orderkm(int orderid, int dishid, String dishname, String dishimage, int chefid, String chefname, String chefimage, int time, int quantity) {
//        this.orderid = orderid;
//        this.dishid = dishid;
//        this.dishname = dishname;
//        this.dishimage = dishimage;
//        this.chefid = chefid;
//        this.chefname = chefname;
//        this.chefimage = chefimage;
//        this.time = time;
//        this.quantity = quantity;
//    }


    public orderkm(int orderid, int chefid) {
        this.orderid = orderid;
        this.chefid = chefid;
    }

    public orderkm(int orderid) {
        this.orderid = orderid;
    }

    public orderkm(int orderid, int chefid, String chefname, String chefimage, String status) {
        this.orderid = orderid;
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;
        this.status = status;
    }

    public orderkm(int orderid, int chefid, String chefname, String chefimage, List<dish> dishitem) {
        this.orderid = orderid;
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;
        this.dishitem = dishitem;
    }
      public orderkm(int orderid, int chefid, String chefname, String chefimage) {
        this.orderid = orderid;
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;

    }


}
