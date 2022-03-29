import { order } from "./order.js";
export class chef{
    constructor(params){
        this.chefid = params.chefid;
        this.chefname = params.chefname;
        this.chefimage = params.chefimage;
        this.order = params.order;
        this.orderid = params.orderid;

    }

    printchelist(){

        var chef = $("#chef");
        var input = $(document.createElement('input')).attr("type","radio").attr("name","rGroup").attr("id",`r${this.chefid}`).attr("value",`${this.chefid}`);
        var labal = $(document.createElement('label')).attr("class","radio").attr("for",`r${this.chefid}`);
        var orders = $(document.createElement('div')).html(this.order.map(i=> new order(i).printorder()));
        var onechef = $(document.createElement('div')).attr("class","row").html(`  <div class="col-3"><img class=" icon-4" id="chefimage" src="${this.chefimage}" alt=""></div>
        <div class="col-3 mt-4">
            <h1 id="chefname">${this.chefname}</h1>
        </div>`);
        // var button = $(document.createElement("button")).html('Assign').attr("onclick","console.log(document.querySelector('input[name=\"rGroup\"]:checked').value)");
        onechef.append(orders);
        labal.append(onechef);

        chef.append(input,labal);
       
        // console.log( $(this).attr("data-id"));
        // console.log("hello");

        // $("#chefname").html(this.chefname);
        // $("#chefimage").attr("src",this.chefimage)
        // $("#order-list").html()
        // $("#assignbutton").html(` <buttom  onclick="assignchef(${this.chefid}) class="btn fw-b tag-green text-2 text-center" style="width: 50%;">Assign</button>`);
    }

    // <!-- <input type="radio" name="rGroup" value="3" id="r3" />
    // <label class="radio" for="r3">
    //     <div id="chef">
    //     <div class="row">
    //         <div class="col-3"><img class="round-2" id="chefimage" alt="" style="width:70px;height:70px;"></div>
    //         <div class="col-3 mt-4">
    //             <h1 id="chefname"> devid</h1>
    //         </div>
    //         <div class="col-4"></div>
    //         <div class="col-2 mt-4">
    //             <h1></h1>
    //         </div>
    //     </div>
    // </div>
    // </label> -->
}