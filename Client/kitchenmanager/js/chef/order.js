export class order{
    constructor(params){
        this.orderid = params.orderid;
        this.chefid = params.chefid;
    }

    printorder(){
        var order = $(document.createElement('div')).attr("class","fw-b").html(`Order ID:${this.orderid}`);
        return order;
    }
    
}