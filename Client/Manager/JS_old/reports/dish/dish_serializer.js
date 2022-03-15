export default class dish_serializer{
    static doSerialize(data){
        return {
            id      : data.dishID,
            name    : data.dishName,
            code    : data.dishCode,
            sold    : data.unitsSold,
            margin  : data.profitMargin,
            profit  : data.profit
        }
    }
}