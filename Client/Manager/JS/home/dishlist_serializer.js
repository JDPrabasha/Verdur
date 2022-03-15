export class Dish_List_Serializer{
    static doSerialize(data){
        return{
            dishID      : data.dishID,
            dishImage   : data.image,
            dishName    : data.name,
            units       : data.unitsSold,
            price       : data.price,
            stars       : data.rating,
            lastMonthSales : data.lastMonthSales
        }
        
    }
}