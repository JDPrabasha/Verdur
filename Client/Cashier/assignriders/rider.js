import { order } from "./order.js";
export class rider{
    constructor(params){
        this.riderID = params.riderID;
        this.status = params.status;
        this.photo = params.photo;
        this.name = params.name;
        this.order = params.order
        this.orderid = params.orderid;
        this.deliveryID = params.deliveryid
       

    }

    printriderlist(){

        var rider = $("#rider");
        var input = $(document.createElement('input')).attr("type","radio").attr("name","rGroup").attr("id",`r${this.riderID}`).attr("value",`${this.riderID}`);
        var labal = $(document.createElement('label')).attr("class","radio").attr("for",`r${this.riderID}`);
        var orders = $(document.createElement('div')).html(this.order.map(i=> new order(i).printorder()));
        var onerider = $(document.createElement('div')).attr("class","row").html(`  <div class="col-3"><img class="round-2" id="chefimage" src="${this.photo}" alt="" style="width:70px;height:70px;"></div>
        <div class="col-3 mt-4">
            <h1 id="ridername">${this.name}</h1>
        </div>`);

        // var button = $(document.createElement("button")).html('Assign').attr("onclick","console.log(document.querySelector('input[name=\"rGroup\"]:checked').value)");
        onerider.append(orders);
        labal.append(onerider);

        rider.append(input,labal);
       
        
    }
    printconfirmassignedorders(){
        var confirm = $("#confirmdelivery");

        console.log(this.order)
        let orderNum="",Totalquantity = 0 ;
        this.order.forEach(element => {
            orderNum += " " + element.orderid+"," ;
            Totalquantity += element.totalQuantity
            console.log(element)
        });
        

        var card = $(document.createElement('div')).attr("id","confirmdelivery"+this.deliveryID).html(` <div id="5" class="flex-space-evenly">
        <div class="row">
            <div class="col-8 pl-3">
                <h1>Rider ID: ${this.riderID}</h1>
                <p>Order IDs : ${orderNum}</p>
                <p>Total Items : ${Totalquantity}</p>
           

            </div>

            <div style="flex-direction: column;">

                <div class="col-3">
                    <button id="" class="btn2 mt-4 ml-6 " onclick="confirm(${this.deliveryID})" >Confirm</button>
                </div>

            </div>
        </div>
    </div>`)
        // var labal1 = $(document.createElement('label')).attr("class","radio").attr("for",`r${this.riderID}`);
        

        confirm.append(card);

        // lable1.append(orders);
        // confirm.append(lable1);
    }


    


}