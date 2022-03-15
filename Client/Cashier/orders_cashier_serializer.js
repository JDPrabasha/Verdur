export class orders_cashier_serializer{
    static doserializer(data){
        return{
            orderID: data.orderid,
            status: data.status,
            Quantity: data.Quantity,
            amount: data.amount,
            dishitem:data.dishitem,
            deliveryid:data.deliveryid,
            name:data.name,
            contact:data.contact,
            address:data.address,
            totalQuantity:data.totalQuantity,
            paymentMethod: data.paymentMethod,
            date: data.date

            
        }
    }

    
}