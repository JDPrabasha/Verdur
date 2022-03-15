package Manager.Ingredients;

import java.util.List;

public class ingredient {

    private int ingid;
    private String ingcode;
    private String ingname;
    private String image;
    private String unit;
    private String description;
    private int carbpg;
    private int calpg;
    private int proteinpg;
    private int fatspg;
    private String type;
    private int min;
    private int max;
    private int normal;
    private int quantity;
    private int expandable;
    private List<Units> unitsList;

    public ingredient(int ingid, String ingcode, String ingname, String image, String unit) {
        this.ingid = ingid;
        this.ingcode = ingcode;
        this.ingname = ingname;
        this.image = image;
        this.unit = unit;
    }

    public ingredient(int ingid, String ingcode, String ingname, String image, String unit, String description, int carbpg, int calpg, int proteinpg, String type, int min, int max, int normal, int quantity) {
        this.ingid = ingid;
        this.ingcode = ingcode;
        this.ingname = ingname;
        this.image = image;
        this.unit = unit;
        this.description = description;
        this.carbpg = carbpg;
        this.calpg = calpg;
        this.proteinpg = proteinpg;
        this.type = type;
        this.min = min;
        this.max = max;
        this.normal = normal;
        this.quantity = quantity;
    }

    public int getIngid() {
        return ingid;
    }

    public void setIngid(int ingid) {
        this.ingid = ingid;
    }

    public String getIngcode() {
        return ingcode;
    }

    public void setIngcode(String ingcode) {
        this.ingcode = ingcode;
    }

    public String getIngname() {
        return ingname;
    }

    public void setIngname(String ingname) {
        this.ingname = ingname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCarbpg() {
        return carbpg;
    }

    public void setCarbpg(int carbpg) {
        this.carbpg = carbpg;
    }

    public int getCalpg() {
        return calpg;
    }

    public void setCalpg(int calpg) {
        this.calpg = calpg;
    }

    public int getProteinpg() {
        return proteinpg;
    }

    public void setProteinpg(int proteinpg) {
        this.proteinpg = proteinpg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFatspg() {
        return fatspg;
    }

    public void setFatspg(int fatspg) {
        this.fatspg = fatspg;
    }

    public int getExpandable() {
        return expandable;
    }

    public void setExpandable(int expandable) {
        this.expandable = expandable;
    }

    public List<Units> getUnitsList() {
        return unitsList;
    }

    public void setUnitsList(List<Units> unitsList) {
        this.unitsList = unitsList;
    }
}
