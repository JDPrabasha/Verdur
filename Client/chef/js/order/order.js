

import { dish } from "./dish.js";
import { Ingredient } from "./ingredient.js";
export class order{
    constructor(params)
    {
       
        this.orderID = params.orderID;
        this.chefID = params.chefID;
        this.chefname = params.chefname;
        this.chefimage = params.chefimage;
        this.dish = params.dish;
        this.ingredient = params.ingredient
      

      
    } 
    printneworder(){

        var neworder = $("#neworder");
        var ordercard = $(document.createElement('div')).attr("class","card").attr("id","results").attr("style","height: 390px");
        var orderid = $(document.createElement('h2')).attr("class","ml-2").html(`${this.orderID}`);
        var window = $(document.createElement('div')).attr("class","mt-3 hidescroll").attr("style","padding: 10px; border:1px solid #bacdd8;border-radius: 10% ;height:240px;overflow-y: scroll;");
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        var dishes = this.dish.map(i => new dish(i).printdish());
        
        var button = $(document.createElement('div')).attr("class","flex-space-evenly mt-2 mb-6").html(` <div> <div class="btn tag-other text-bold"  onclick="completeorder(${this.orderID})" >Complete Order</div>
        </div>`);
        

      
    window.append(dishes);
    ordercard.append(orderid,window,button);
    neworder.append(ordercard);
      
    }

    printdish()
    {
        

        // var ingredienttable = $("#ingredienttable");
        // var dishname = $(document.createElement('div')).attr("class","row");

        var cauztomization =$("#caustomization");

        var topic = this.dish.map(i => new dish(i).printdishtopic());
      
        var table = $(document.createElement('table')).attr( "class","restocktable").attr("style","width: 80%;");
        var tablehead =$(document.createElement('thead')).html(`  <th></th>
        <th>Image</th>
        <th>Ingredient</th>
        <th>Quantity</th>`);

        var ingredeinttablebody = this.ingredient.map(i => new Ingredient(i).printdishingredient());

        table.append(tablehead,ingredeinttablebody);
        cauztomization.append(topic,table)


      



        

    //     <div class="row"><div class="col-3 "></div><div class="col-6"> <h4 class="text-darkgrey">Avocado Smoothie</h4></div> </div>
    //     <div class="row"><div class="col-4"></div><div class="col-6"><h2 class="text-info">Quantity : 2</h2></div></div>
        
   
 
    
}
}


    