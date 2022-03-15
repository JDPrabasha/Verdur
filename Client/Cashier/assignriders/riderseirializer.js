export class riderserializer{
    static doserializer(data){
        return{
            riderID: data.riderID,
            status: data.status,
            photo: data.photo,
            name:data.name,
            order:data.riderorders,
            orderid:data.orderid,
            deliveryid:data.deliveryID,
            address:data.address,
            quantity:data.Quantity,
            longitude:data.longitude,
            latitude:data.latitude
        }
    }
}