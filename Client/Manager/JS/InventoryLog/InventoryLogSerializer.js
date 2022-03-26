export class InventoryLogSerializer{
    static doserialize(data){
        return {
            logID : data.logID,
            ingID : data.ingID,
            ingredientName : data.ingredientName,
            changedQuantity : data.changedQuantity,
            currentQuantity : data.currentQuantity,
            timestamp : data.timestamp
        }
    }
}