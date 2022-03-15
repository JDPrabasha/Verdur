export class supplierSerializer {
    static doSerialize(data) {
        return {
            id          : data.supplierID,
            name        : data.name,
            image       : data.image,
            org         : data.org,
            ordersDue   : data.ordersDue,
            ordersDone  : data.ordersDone,
            email       : data.email,
            contact     : data.contact,
            location    : data.location,
            joinedon    : data.joinedon,
            pendingPay  : data.pendingPay
        }
    }
}