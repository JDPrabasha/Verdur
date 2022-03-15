package kitchenmanager.order;

import kitchenmanager.dishes.dish;
import kitchenmanager.dishes.hasIngredient;

import java.util.List;

public class order {
    private int orderID;
    private List<hasIngredient> Ingredients;
    private List<dish> dishitem;

    private int chefid;
    private String chefname;
    private String chefimage;
    private String status;
//    private int dishID;
//    private String dishname;
//    private String dishimage;
//    private int quantity;
//    private int ingid;
//    private String ingname;
//    private String ingimage;
//    private int ingquantity;


    public order(int orderID, List<dish> dishitem, int chefid, String chefname, String chefimage) {
        this.orderID = orderID;
        this.dishitem = dishitem;
        this.chefid = chefid;
        this.chefname = chefname;
        this.chefimage = chefimage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public order(List<hasIngredient> ingredients, List<dish> dishitem) {
        Ingredients = ingredients;
        this.dishitem = dishitem;
    }

    public order(int orderID, List<dish> dishitem) {
        this.orderID = orderID;
        this.dishitem = dishitem;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<hasIngredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<hasIngredient> ingredients) {
        Ingredients = ingredients;
    }

    public List<dish> getDishitem() {
        return dishitem;
    }

    public void setDishitem(List<dish> dishitem) {
        this.dishitem = dishitem;
    }
}