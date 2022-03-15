package kitchenmanager.dishes;

import java.util.List;

public class dish {
    private Integer dishid;
    private String dishname;
    private Integer defaultprice;
    private Integer estimatedtime;

    private String dishcode;
    private String description;
    private String image;
    private String approvalstatus;
    private Integer enable;
    private Integer quantity;
    private String request;
    private Integer defaultquantity;
    private String ingredienttype;
    private Integer ingid;
    private String unit;
    private List<hasIngredient> Ingredients;

//    public dish(int dishid) {
//        this.dishid = dishid;
//    }


    public dish(Integer dishid, String dishname, String dishcode, String image, Integer enable, Integer defaultquantity, String ingredienttype) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
        this.enable = enable;
        this.defaultquantity = defaultquantity;
        this.ingredienttype = ingredienttype;
    }

    public dish(Integer dishid, String dishname, String dishcode, String image, Integer enable, Integer defaultquantity, String ingredienttype, Integer ingid, String unit) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
        this.enable = enable;
        this.defaultquantity = defaultquantity;
        this.ingredienttype = ingredienttype;
        this.ingid = ingid;
        this.unit = unit;
    }

    public dish(Integer dishid, String dishname, String dishcode, String image, Integer enable, Integer defaultquantity, String ingredienttype, Integer ingid) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
        this.enable = enable;
        this.defaultquantity = defaultquantity;
        this.ingredienttype = ingredienttype;
        this.ingid = ingid;
    }

    public dish(Integer dishid, String dishname, String dishcode, String description, String image, List<hasIngredient> ingredients) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.description = description;
        this.image = image;
        Ingredients = ingredients;
    }
    //    public dish(Integer dishid, String dishname, String dishcode, String description, String image, List<hasIngredient> ingredients) {
//        this.dishid = dishid;
//        this.dishname = dishname;
//        this.dishcode = dishcode;
//        this.description = description;
//        this.image = image;
//        Ingredients = ingredients;
//    }


    public dish(Integer dishid, String dishname, Integer estimatedtime, String image, Integer quantity) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.estimatedtime = estimatedtime;
        this.image = image;
        this.quantity = quantity;
    }
    public dish(Integer dishid, String dishname,String image, Integer quantity) {
        this.dishid = dishid;
        this.dishname = dishname;

        this.image = image;
        this.quantity = quantity;
    }

    public dish(Integer dishid, Integer enable) {
        this.dishid = dishid;
        this.enable = enable;
    }

    public dish(Integer dishid, String dishname, String dishcode, String image, List<hasIngredient> ingredients) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
        Ingredients = ingredients;
    }

    public dish(Integer dishid, String dishname, String dishcode, String image, Integer enable, List<hasIngredient> ingredients) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
        this.enable = enable;
        Ingredients = ingredients;
    }

//    public dish(String dishname, Integer estimatedtime, String dishcode, String description, String image, List<hasIngredient> ingredients) {
//        this.dishname = dishname;
//        this.estimatedtime = estimatedtime;
//        this.dishcode = dishcode;
//        this.description = description;
//        this.image = image;
//        Ingredients = ingredients;
//    }

    public dish(Integer dishid, String dishname, Integer defaultprice, Integer estimatedtime) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.defaultprice = defaultprice;
        this.estimatedtime = estimatedtime;
    }


    public dish(Integer dishid, String dishname, String dishcode, String image) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.dishcode = dishcode;
        this.image = image;
    }

//    public dish(Integer dishid, String dishname, Integer estimatedtime) {
//        this.dishid = dishid;
//        this.dishname = dishname;
//        this.estimatedtime = estimatedtime;
//    }
    public dish(Integer dishid, String dishname, Integer quantity) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.quantity = quantity;
    }

    public dish(Integer estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDishid() {
        return dishid;
    }

    public void setDishid(Integer dishid) {
        this.dishid = dishid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public Integer getDefaultprice() {
        return defaultprice;
    }

    public void setDefaultprice(Integer defaultprice) {
        this.defaultprice = defaultprice;
    }

    public Integer getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(Integer estimatedtime) {
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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public List<hasIngredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<hasIngredient> ingredients) {
        Ingredients = ingredients;
    }
}