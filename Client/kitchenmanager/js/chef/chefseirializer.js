export class chefserializer{
    static doserializer(data){
        return{
            chefid: data.chefid,
            chefname: data.chefname,
            chefimage: data.chefimage,
            order:data.order,
            orderid:data.orderid
        }
    }
}