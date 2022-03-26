package Manager.IngredientLogs;

public class IngredientsLog {
    private Integer logID;
    private Integer ingID;
    private String ingredientName;
    private String image;
    private Integer changedQuantity;
    private Integer currentQuantity;
    private String timestamp;

    public IngredientsLog(Integer logID, Integer ingID, String ingredientName, Integer changedQuantity, Integer currentQuantity, String timestamp) {
        this.logID = logID;
        this.ingID = ingID;
        this.ingredientName = ingredientName;
        this.changedQuantity = changedQuantity;
        this.currentQuantity = currentQuantity;
        this.timestamp = timestamp;
    }
}
