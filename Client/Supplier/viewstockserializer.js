class dishserializer{
    static doserializer(data){
        return{
            itemID: data.itemID,
            itemName: data.itemName,
            itemType: data.itemType,
            quantity: data.quantity,
            unit: data.unit,
            price: data.price,
            dateAdded: data.dateAdded,
            
        }
    }

}