class Manager_dish_serializer{
    static doSerialize(data){
        return{
            dishID : data.dishID,
            dishName : data.dishName,
            defaultPrice : data.defaultPrice,
            image : data.image
        }
    }
}