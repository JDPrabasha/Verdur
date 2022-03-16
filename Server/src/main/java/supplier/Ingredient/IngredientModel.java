package supplier.Ingredient;



public class IngredientModel {

    private int ingID;
    private String itemCode;
    private String itemName;
    private String unit;
    private String image;
    private String description;
    private int carbpg;
    private int calpg;
    private int proteinpg;

    public IngredientModel(int ingID, String itemCode, String itemName, String unit, String image, String description, int carbpg, int calpg, int proteinpg) {
        this.ingID = ingID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unit = unit;
        this.image = image;
        this.description = description;
        this.carbpg = carbpg;
        this.calpg = calpg;
        this.proteinpg = proteinpg;
    }

    public int getIngID() {
        return ingID;
    }

    public void setIngID(int ingID) {
        this.ingID = ingID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public String toString() {
        return "IngredientModel{" +
                "ingID=" + ingID +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", unit='" + unit + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", carbpg=" + carbpg +
                ", calpg=" + calpg +
                ", proteinpg=" + proteinpg +
                '}';
    }
}
