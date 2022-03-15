class ordersserializer{
    static doserializer(data){
        return{
            reorderID: data.reorderID,
            item: data.item,
            quantity: data.quantity,
            totalPrice: data.totalPrice,
            requestedDate: data.requestedDate,
            deliveryDate: data.deliveryDate,
            timeTillDeadline: data.timeTillDeadline,
            invoiceDate: data.invoiceDate,
            status: data.status,
            
        }
    }

}