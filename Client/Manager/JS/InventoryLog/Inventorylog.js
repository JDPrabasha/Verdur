export class InventoryLog{
    constructor(params){
        this.logID              = params.logID;
        this.ingID              = params.ingID;
        this.ingredientName     = params.ingredientName;
        this.changedQuantity    = params.changedQuantity;
        this.currentQuantity    = params.currentQuantity;
        this.timestamp          = params.timestamp;
    }

    printInventoryLogs(){
        console.log(this.logID)
        let table = $("#ingredient_logs"),
            row = $("<tr>"),
            logID = $('<td>').html(this.logID),
            ingName = $('<td>').html(this.ingredientName),
            changedQuantity = $('<td>').html(this.changedQuantity),
            currentQuantity = $('<td>').html(this.currentQuantity),
            timestamp = $('<td>').html(this.timestamp)

        row.append(logID,ingName,changedQuantity,currentQuantity,timestamp)
        table.append(row)
    }
}