import { dish } from "./dish.js";
import { orderkmserializer } from "./orderkmserializer.js";
export class orderkm{
    constructor(params)
    {
       
        this.orderID = params.orderID;
        this.chefID = params.chefID;
        this.chefname = params.chefname;
        this.chefimage = params.chefimage;
        this.dish = params.dish;
      

      
    } 
    printneworder(){

        var neworder = $("#neworder");
        var ordercard = $(document.createElement('div')).attr("class","card").attr("id","results").attr("style","height: 390px");
        var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`${this.orderID}`);
        var window = $(document.createElement('div')).attr("class","mt-3 hidescroll").attr("style","padding: 10px; border:1px solid #bacdd8;border-radius: 10% ;height:240px;overflow-y: scroll;");
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        var dishes = this.dish.map(i => new dish(i).printdish());
        // var dishes = "";
        
        var button = $(document.createElement('div')).attr("class","flex-space-evenly mt-2 mb-6").html(` <div> <button class="btn" id="chef-${this.orderID}">Assign</button></div>
        </div>`).attr("data-id",$(this.orderID));
        

      
    window.append(dishes);
    ordercard.append(orderid,window,button);
    neworder.append(ordercard);
      
    }
    printongoingorders(){

        var anassignorder = $("#unassign");
         var totaltime = 0;
         this.dish.map(i => new dish(i).gettime(totaltime));
        //  console.log(totaltime);

         let initialValue = 0
    let sum = this.dish.reduce(function (previousValue, currentValue) {
    return previousValue + currentValue.estimatedtime
    }, initialValue)

// console.log(sum) // logs 6
var countDownTime = new Date(sum).getTime()
console.log(countDownTime)
var now = new Date().getTime();
console.log(now)
var distance = countDownTime - now;

// var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
var seconds = Math.floor((distance % (1000 * 60)) / 1000);

var time = minutes + ":" + seconds 


        var order = $(document.createElement('div')).attr("class","card ongoing_dashcard mb-4").html(`<div class="row py-3 pl-2 pr-12">
        <div class="circle flex-space-evenly col-2">
            <i class="material-icons">lunch_dining</i>
        </div>
        <div class="col-9 flex-col pl-3">
           <div class="row">
               
               <div class="col-4 text-2 text-bold"> ID : ${this.orderID}</div>
               <div class="col-3 text-2 text-bold">${time}</div>
               <div class="col-3"><img class="icon-5"style="width:2cm; height:2cm;" src="${this.chefimage}" alt=""></div>
               <div class="col-2 text-bold">${this.chefname}</div>
               
           </div>
        </div>
</div>`);

        anassignorder.append(order);

    }



}


    