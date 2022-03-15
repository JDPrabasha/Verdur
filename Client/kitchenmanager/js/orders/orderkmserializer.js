export class orderkmserializer{
    static doserializer(data){
        return{
            orderID: data.orderid,
            chefID: data.chefid,
            chefname: data.chefname,
            chefimage: data.chefimage,
            dish:data.dishitem
        }
    }

}