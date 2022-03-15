export class dishserializer{
    static doserializer(data){
        return{
            dishid: data.dishid,
            dishname: data.dishname,
            defaultprice: data.defaultprice,
            estimatedtime: data.estimatedtime,
            dishcode: data.dishcode,
            description:data.description,
            image: data.image,
            enable: data.enable,
            estatus: data.status,
            defaultquantity: data.defaultquantity,
            ingredienttype: data.ingredienttype,
            ingid: data.ingid,
            unit: data.unit,
            ingredients: data.Ingredients
           
            
        }
        
    }

}