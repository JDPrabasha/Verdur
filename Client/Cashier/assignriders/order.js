export class order{
    constructor(params){
        this.orderid = params.orderid;
        // this.riderID = params.riderID;
        this.deliveryid = params.deliveryid;
        this.quantity= params.Quantity;
        this.longitude = params.longitude;
        this.latitude = params.latitude;
    }

    printorder(){
        // var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`OrderID: ${this.orderID}`);
        var order = $(document.createElement('div')).html(`Order ID:${this.orderid}`);
        // var order = $(document.createElement('div')).html(`${this.riderID}`);
        // var deliveryid = $(document.createElement('div')).html(`Delivery ID: ${this.deliveryid}`);
        var quantity = $(document.createElement('div')).html(`Total Items: ${this.quantity}`);
        // var longitude = $(document.createElement('div')).html(`${this.longitude}`);
        // var latitude = $(document.createElement('div')).html(`${this.latitude}`);

        order.append(quantity)

        return order;
    }

    printassignedorders(){
        var order = $(document.createElement('div')).html(`${this.orderid}`);
        // var riderID = $(document.createElement('div')).html(`${this.riderID}`);
        // var quantity = $(document.createElement('div')).html(`${this.quantity}`);
      
        // order.append(quantity)

        return order;
    }
    printquantity(){
        var quantity = $(document.createElement('div')).html(`${this.quantity}`);
        return quantity;

    }



}