class cashierpaymentsserializer{
    static doserializer(data){
        return{
            orderID: data.orderID,
            riderID: data.riderID,
            dueAmount: data.dueAmount,
            assignedTime: data.assignedTime,
            delivered: data.delivered,
            type: data.type,
            status:data.status,
            paymentCompleted: data.paymentCompleted
            
            
            
        }
    }

}