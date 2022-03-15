package kitchenmanager.Ingredients;

public class ingredient {

    private int ingid;
    private String ingcode;
    private String ingname;
    private String image;
    private String unit;

    public ingredient(int ingid, String ingcode, String ingname, String image, String unit) {
        this.ingid = ingid;
        this.ingcode = ingcode;
        this.ingname = ingname;
        this.image = image;
        this.unit = unit;
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
}
