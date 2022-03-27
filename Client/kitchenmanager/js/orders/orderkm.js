import { dish } from "./dish.js";
import { orderkmserializer } from "./orderkmserializer.js";
export class orderkm{
    constructor(params)
    {
       
        this.orderID = params.orderID;
        this.chefID = params.chefID;
        this.chefname = params.chefname;
        this.chefimage = params.chefimage;
        this.timestamp = params.timestamp;
        this.dish = params.dish;
      

      
    } 
    printneworder(){

        var neworder = $("#neworder");
        var ordercard = $(document.createElement('div')).attr("class","card").attr("id","results").attr("style","height: 390px");
        var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`Order ID : ${this.orderID}`);
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
        // console.log(totaltime);

         let initialValue = 0
    let sum = this.dish.reduce(function (previousValue, currentValue) {
    return previousValue + currentValue.estimatedtime
    }, initialValue)

    console.log("total order time = "+sum) // logs 6
    
    console.log("timestamp = "+this.timestamp);
  
    var newDateObj = moment(this.timestamp).add(sum, 'm').toDate();
    console.log("timestamp + total order time ="+newDateObj);
    console.log("answer = "+this.getTimeRemaining(newDateObj))
    var time = this.getTimeRemaining(newDateObj)
    

        var order = $(document.createElement('div')).attr("class","card ongoing_dashcard mb-4").attr("style","width:fit-content").html(`<div class="row py-3 pl-2 pr-12">
        <div class="circle flex-space-evenly col-1">
            <i class="material-icons">lunch_dining</i>
        </div>
        <div class="col-10 flex-col pl-3">
           <div class="row">
               
               <div class="col-3 text-2"> ID : ${this.orderID}</div>
               <div class="col-5 text-2 " id="orderTime-${this.orderID}" time ="${newDateObj}">${time}</div>
               <div class="col-3"><img class="icon-5"style="width:2cm; height:2cm;" src="${this.chefimage}" alt=""></div>
               <div class="col-1 text-bold">${this.chefname}</div>
               
           </div>
        </div>
</div>`);

        anassignorder.append(order);

    }

    getTimeRemaining(x) {
        // console.log(new Date(x))
        // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
        let difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
        console.log("difference = "+difference);
        
    
    
    
        let months = difference.getMonth(), 
        days = difference.getDate(),
        hours = difference.getHours(),
        mins = difference.getMinutes(),
        secs = difference.getSeconds(),
        timeremain;
        if(months!=0){
            timeremain = hours +":"+ mins + ":" + secs  
        }else if(days!=0){
            timeremain = hours +" :"+ mins + " :" + secs  
        }else if(hours!=0){
            timeremain =  hours +":"+ mins + ":" + secs  
        }else if(mins!=0){
            timeremain = "00:"+mins + ":" + secs 
        }else{
            timeremain =   "00:00:"+secs 
        }
        return timeremain;
    
    }



}


    