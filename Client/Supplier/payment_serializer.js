export class paymentserializer{
    static doserializer(data){
        return{
            invoiceID: data.invoiceID,
            invoiceDate: data.invoiceDate,
            totalAmount: data.totalAmount,
            requestedDate: data.requestedDate,
            deliveryDate: data.deliveryDate,
            status: data.status,
          
            
            
        }
    }

}