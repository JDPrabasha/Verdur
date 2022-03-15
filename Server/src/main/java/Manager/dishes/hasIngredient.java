package Manager.dishes;

public class hasIngredient {
    private int ingID;
    private String type;
    private int minimum;
    private int defaultv;
    private int maximum;
    private String itemcode;
    private String name;
    private String image;
    private int carbpg;
    private int calpg;
    private int proteinpg;

    public hasIngredient(int ingID, String type, int minimum, int defaultv, int maximum, String itemcode, String name, String image, int carbpg, int calpg, int proteinpg) {
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

    public hasIngredient(int ingID, String type, int minimum, int defaultv, int maximum) {
        this.ingID = ingID;
        this.type = type;
        this.minimum = minimum;
        this.defaultv = defaultv;
        this.maximum = maximum;
    }

    public int getIngID() {
        return ingID;
    }

    public void setIngID(int ingID) {
        this.ingID = ingID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getDefaultv() {
        return defaultv;
    }

    public void setDefaultv(int defaultv) {
        this.defaultv = defaultv;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
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
}



