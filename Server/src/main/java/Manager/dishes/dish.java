package Manager.dishes;

import java.util.List;

public class dish {
   private String dishid;
   private String dishname;
   private int defaultprice;
   private String estimatedtime;

   private String dishcode;
   private String description;
   private String image;
   private String approvalstatus;
   private String enable;
   private List<hasIngredient> Ingredients;

    public dish(String dishid, String dishname, String dishcode, String image, List<hasIngredient> ingredients) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
        Ingredients = ingredients;
    }

    public dish(String dishname, String estimatedtime, String dishcode, String description, String image, List<hasIngredient> ingredients) {
        this.dishname = dishname;
        this.estimatedtime = estimatedtime;
        this.dishcode = dishcode;
        this.description = description;
        this.image = image;
        Ingredients = ingredients;
    }

    public dish(String dishid, String dishname, int defaultprice, String estimatedtime) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.defaultprice = defaultprice;
        this.estimatedtime = estimatedtime;
    }


    public dish(String dishid, String dishname, String dishcode, String image) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
    }

    public dish(String dishid, String dishname, String estimatedtime) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.estimatedtime = estimatedtime;
    }




    public String getDishid() {
        return dishid;
    }

    public void setDishid(String dishid) {
        this.dishid = dishid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public int getDefaultprice() {
        return defaultprice;
    }

    public void setDefaultprice(int defaultprice) {
        this.defaultprice = defaultprice;
    }

    public String getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(String estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    public String getDishcode() {
        return dishcode;
    }

    public void setDishcode(String dishcode) {
        this.dishcode = dishcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(String approvalstatus) {
        this.approvalstatus = approvalstatus;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public List<hasIngredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<hasIngredient> ingredients) {
        Ingredients = ingredients;
    }
}
