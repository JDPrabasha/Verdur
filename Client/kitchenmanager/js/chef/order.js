export class order{
    constructor(params){
        this.orderid = params.orderid;
        this.chefid = params.chefid;
    }

    printorder(){
        var order = $(document.createElement('div')).html(`${this.orderid}`);
        return order;
    }
    
}