export class Complaints_Serializer{
    static doSerialize(data){
        return{
            complaintID : data.complaintID,
            customer    : data.custName,
            type        : data.type,
            rider       : data.riderName,
            date        : data.date != undefined ? data.date.split(' ')[0]:null,
            time        : data.date != undefined ? data.date.split(' ')[1]:null,
            description : data.date != undefined & data.description.length>60 ?data.description.slice(0,60) + ".........": data.description,
            orderID     : data.orderID,
            location    : data.deliveryLocation,
            chef        : data.chef,
            itemCount   : data.itemCount,
            payment     : data.payment,
            orderItems  : data.orderItems
        }
    }
}