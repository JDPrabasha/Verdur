class Supplier_Ingredient_serializer{
    static doSerialize(data){
        return{
            ingID : data.ingID,
            ingName : data.itemName
        }
    }
}
