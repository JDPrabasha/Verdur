class restockrequestserializer{
    static doserializer(data){
        return{
            restockID: data.restockID,
            itemID: data.itemID,
            itemName: data.itemName,
            quantity: data.quantity,
            unit: data.unit,
            price: data.price,
            responseDeadline: data.responseDeadline,
            deliveryRequestDate: data.deliveryRequestDate,
            timeTillExpiry: data.timeTillExpiry,
            
        }
    }

}