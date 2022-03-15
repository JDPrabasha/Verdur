export default class dish_serializer{
    static doSerialize(data){
        return {
            id      : data.dishID,
            name    : data.name,
            code    : data.dishCode,
            sold    : data.unitsSold,
            margin  : data.profitPercentage,
            profit  : data.totalSales * data.profitPercentage/100
        }
    }
}