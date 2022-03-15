export default class supplier_Serializer{
    static doSerializer(data){
        return{
            id      : data.supplierID,
            name    : data.name,
            org     : data.Org,
            orders  : data.ordersDone,
            percent : data.acceptPercentage.toFixed(2),
            // percent : data.acceptPercentage,
            amount  : data.amount
        }
    }
}