export class orderserializer{
    static doserializer(data){
        return{
            orderID: data.orderID,
            chefID: data.chefid,
            chefname: data.chefname,
            chefimage: data.chefimage,
            dish:data.dishitem,
            ingredient:data.Ingredients
        }
    }

}