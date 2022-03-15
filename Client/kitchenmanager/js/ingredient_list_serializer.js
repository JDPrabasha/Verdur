class Ingredient_List_Serializer{
    static doSerializer(data){
        return{
            ingID   : data.ingid,
            ingCode : data.ingcode,
            ingName : data.ingname,
            image   : data.image,
            unit    : data.unit
        }
    }
}