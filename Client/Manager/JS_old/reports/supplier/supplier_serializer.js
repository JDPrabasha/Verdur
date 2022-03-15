export default class supplier_Serializer{
    static doSerializer(data){
        return{
            id      : data.supplierID,
            name    : data.name,
            org     : data.organization,
            orders  : data.ordersDone,
            percent : data.orderPercentage,
            amount  : data.amount
        }
    }
}