import { dish } from "./dish.js";
export class orders_cashier{
    constructor(params)
    {
       
        this.orderID = params.orderID;
        this.status = params.status;
        this.Quantity = params.Quantity;
        this.amount = params.amount;
        this.dishitem = params.dishitem;
        this.name = params.name;
        this.contact = params.contact;
        this.address = params.address;
        this.totalQuantity = params.totalQuantity;
        this.paymentMethod = params.paymentMethod;
        this.date = params.date
     

        
        
        
      

    }
    

    printneworder(){
    

    
      
        var neworder = $("#pending");
        
        var ordercard = $(document.createElement('div')).attr("class","card").attr("id","results").attr("style","height: 390px");
        var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`OrderID: ${this.orderID}`);
        var window = $(document.createElement('div')).attr("class","mt-3 hidescroll").attr("style","padding: 10px; border:1px solid #bacdd8;border-radius: 10% ;height:240px;overflow-y: scroll;");
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        var totalprice = $(document.createElement('h2')).attr("class","ml-2").html(`Rs. ${this.amount}`);
        var dishes = this.dishitem.map(i => new dish(i).printdish());


        
        var button = $(document.createElement('div')).attr("class","flex-space-evenly mt-2 mb-6").html(`  <button class="btn" id="btn" btn fw-b tag-green onclick="accept(${this.orderID})" tag-green>Accept</button><button class="btn fw-b tag-red" id="btn1" onclick="reject(${this.orderID})" >Reject</button>
        `);
        

      
    window.append(dishes);
    ordercard.append(orderid,window,totalprice,button);
    neworder.append(ordercard);

    }

   

    printnewordercooked(){

        var neworder = $("#cooked");
        var ordercard = $(document.createElement('div')).attr("class","card").attr("id","results").attr("style","height: 390px");
        var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`OrderID: ${this.orderID}`);
        var window = $(document.createElement('div')).attr("class","mt-3 hidescroll").attr("style","padding: 10px; border:1px solid #bacdd8;border-radius: 10% ;height:240px;overflow-y: scroll;");
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        // var totalprice = $(document.createElement('h2')).attr("class","ml-2").html(`Rs. ${this.amount}`);
        var dishes = this.dishitem.map(i => new dish(i).printdish());
        // var dishes = "";
        
        // var button = $(document.createElement('div')).attr("class","flex-space-evenly mt-2 mb-6").html(`  <button class="btn" id="myBtn" tag-green>Accept</button>
        // var button = $(document.createElement('div')).html(`<button class="btn2 ml-12 " style="height: 45px;" onclick="hiderow(${this.orderID})">Assign Rider</button>`);
        // `);

        var button = $(document.createElement('div')).attr("class","flex-space-evenly ml-2 mt-2 mb-6 h2").html(`Rs. ${this.amount} <button id="rider-${this.orderID}  class="btn2 ml-12 ">Assign Rider</button> `);
       

      
    window.append(dishes);
    ordercard.append(orderid,window,button);
    neworder.append(ordercard);
      
    }

    printneworderdelivering(){

        var neworder = $("#delivering");
        var ordercard = $(document.createElement('div')).attr("class","card").attr("id","results").attr("style","height: 390px");
        var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`OrderID: ${this.orderID}`);
        var window = $(document.createElement('div')).attr("class","mt-3 hidescroll").attr("style","padding: 10px; border:1px solid #bacdd8;border-radius: 10% ;height:240px;overflow-y: scroll;");
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        var totalprice = $(document.createElement('h2')).attr("class","ml-2").html(`Rs. ${this.amount}`);
        var dishes = this.dishitem.map(i => new dish(i).printdish());
        // var dishes = "";
        
        // var button = $(document.createElement('div')).attr("class","flex-space-evenly mt-2 mb-6").html(`  <button class="btn" id="myBtn" tag-green>Accept</button>
        // `);
        

      
    window.append(dishes);
    ordercard.append(orderid,window,totalprice);
    neworder.append(ordercard);
      
    }



    printongoingorders(){
        var view = $("#ongoing");
        var row = $(document.createElement('tr')).attr("id",this.orderID);
        var orderID = $(document.createElement('td')).html(this.orderID);
        var itemQuantity = $(document.createElement('td')).html(this.totalQuantity);
        var price = $(document.createElement('td')).html(this.amount);
        var date = $(document.createElement('td')).html(this.date);
        // var status = $(document.createElement('td')).html(this.status);

        if(this.status==`pending`)
            {
                var status = $(document.createElement('td')).html( `<button class="mybutton3 tag-yellow">Pending</button>`);   
            }else if(this.status==`delivering`){
                var status = $(document.createElement('td')).html( `<button class="mybutton3 tag-green">Delivering</button>`); 

            }

            else{
                var status = $(document.createElement('td')).html( `<button class="mybutton3 tag-blue">Cooked</button>`);

            }
        var paymentMethod = $(document.createElement('td')).html(this.paymentMethod);
        
        
        



        

    row.append(orderID);
    row.append(itemQuantity);
    row.append(price);
    row.append(date);
    row.append(status);
    row.append(paymentMethod);

    view.append(row);
}

printriderorders(){

   
    $("#totalQuantity").html(": " + this.totalQuantity)
    $("#riderOrderPrice").html(": " + this.amount)
    // console.log(this.amount)
    $("#riderOrderName").html(": " + this.name)
    $("#riderOrderName").attr("orderid",this.orderID)
    $("#customerContact").html(": " + this.contact)
    $("#riderOrderaddress").html(": " + this.address)

     
}


printriderorderslist(){
    var neworder = $("#riderorderslist");
    var container = $(document.createElement('div'));
    var order = $(document.createElement('div')).attr("class","flex-space-evenly").html(`<div class="row">
    <div class="col-8 pl-3">
        <h1>Order ID: ${this.orderID}</h1>
        <p>Items :${this.totalQuantity} </p>
        <p>${this.name}</p>
        <p>${this.contact}</p>
        <p>${this.address}</p>
    </div>

    <div style="flex-direction: column;">
        <div class="col-2">
            <button id="rider-${this.orderID} " class="btn2 mt-2 ml-6" >Assign
                Rider</button>
        </div>

    </div>
</div>`);
var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
container.append(order,hr)


neworder.append(container);


}


}



    