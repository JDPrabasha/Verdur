package kitchenmanager.dishes;

public class hasIngredient {
    private Integer ingID;
    private String type;
    private Integer minimum;
    private Integer defaultv;
    private Integer maximum;

    private String itemcode;
    private String name;
    private String image;
    private Integer carbpg;
    private Integer calpg;
    private Integer proteinpg;
    private Integer custquantity;
    private String unit;
    private int stock;
    private int restocklevel;

    public hasIngredient(Integer ingID, String name, String image, Integer custquantity, String unit) {
        this.ingID = ingID;
        this.name = name;
        this.image = image;
        this.custquantity = custquantity;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public hasIngredient(Integer ingID, String name, String image, Integer custquantity) {
        this.ingID = ingID;
        this.name = name;
        this.image = image;
        this.custquantity = custquantity;
    }

    public hasIngredient(Integer ingID, String type, Integer minimum, Integer defaultv, Integer maximum, String itemcode, String name, String image, Integer carbpg, Integer calpg, Integer proteinpg) {
        this.ingID = ingID;
        this.type = type;
        this.minimum = minimum;
        this.defaultv = defaultv;
        this.maximum = maximum;
        this.itemcode = itemcode;
        this.name = name;
        this.image = image;
        this.carbpg = carbpg;
        this.calpg = calpg;
        this.proteinpg = proteinpg;

    }

    public hasIngredient(Integer ingID, String type, Integer minimum, Integer defaultv, Integer maximum, String itemcode, String name, String image, Integer carbpg, Integer calpg, Integer proteinpg, int stock, int restocklevel) {
        this.ingID = ingID;
        this.type = type;
        this.minimum = minimum;
        this.defaultv = defaultv;
        this.maximum = maximum;
        this.itemcode = itemcode;
        this.name = name;
        this.image = image;
        this.carbpg = carbpg;
        this.calpg = calpg;
        this.proteinpg = proteinpg;
        this.stock = stock;
        this.restocklevel = restocklevel;
    }
    //    public hasIngredient(Integer ingID, String type, Integer minimum, Integer defaultv, Integer maximum) {
//        this.ingID = ingID;
//        this.type = type;
//        this.minimum = minimum;
//        this.defaultv = defaultv;
//        this.maximum = maximum;
//    }

    public Integer getCustquantity() {
        return custquantity;
    }

    public void setCustquantity(Integer custquantity) {
        this.custquantity = custquantity;
    }

    public hasIngredient(String type, Integer defaultv) {
        this.type = type;
        this.defaultv = defaultv;
    }

    public Integer getIngID() {
        return ingID;
    }

    public void setIngID(Integer ingID) {
        this.ingID = ingID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getDefaultv() {
        return defaultv;
    }

    public void setDefaultv(Integer defaultv) {
        this.defaultv = defaultv;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCarbpg() {
        return carbpg;
    }

    public void setCarbpg(Integer carbpg) {
        this.carbpg = carbpg;
    }

    public Integer getCalpg() {
        return calpg;
    }

    public void setCalpg(Integer calpg) {
        this.calpg = calpg;
    }

    public Integer getProteinpg() {
        return proteinpg;
    }

    public void setProteinpg(Integer proteinpg) {
        this.proteinpg = proteinpg;
    }
}



