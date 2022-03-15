import { inventory } from "./inventory.js";
import {supplier} from "./supplier.js"
export class restock{
    constructor(params)
    {
        this.restockid = params.restockid;
        this.ingname = params.ingname;
        this.issuedate = params.issuedate;
        this.status = params.status;
        this.timeremain =params.timeremain;
        this.inventory = params.inventory;
        this.supplier = params.supplier;

        this.timecountdown = this.getTimeRemaining(params.timeremain);
      
      
    } 
    printrestock(){

        // var currentdate = new Date();
        // var raandate = new Date(this.timeremain);
        // // console.log(raandate);
        

        // // var date = currentdate.getFullYear()+'-'+(currentdate.getMonth()+1)+'-'+currentdate.getDate();
        // var dif = Math.abs(currentdate-raandate);
        // var drft = new Date(dif);
        // // var difdate = drft.getDate() + (drft.getDate()>1?" Days ":drft.getDate()==1?" Day ":"")+ drft.getHours() + "hrs" + drft.getMinutes() + "mins" + drft.getSeconds() ;
        // var difdate = drft.getDate() + (drft.getDate()>1?" Days ":drft.getDate()==1?" Day ":"")+ drft.getHours() + " Hrs " + drft.getMinutes() + " Mins" ;
        // //;



        // //  const diffDays = Math.ceil(dif/ (1000 * 60 * 60 * 24));
        

        // console.log(drft);
        // console.log(difdate);
    
        // console.log(diffDays);
        var statusStyle;
        if(this.status == "Supplier Pending"){
            statusStyle = "SupplierPending"
        }
        else{
            statusStyle = this.status
        }

        var statusm;
        if(this.status == "managerApproved"){
            statusm = "Manager Approved"
        }
        else{
            statusm = this.status
        }
        
        var restock = $("#restocktable");
            var restocktable = $(document.createElement('tr')).attr( "class","res").html(`
           
            <td>${this.ingname}</td>
            <td>${this.issuedate.split(" ")[0]}</td>
            <td><div class=status-${statusStyle}>${statusm}</div></td>
            <td>${this.timecountdown}</td>`);
            
        restock.append(restocktable);

        // setTimeout(function () {
        //     this.printrestock();
        // }, 1000);

        
    }
    

    

    
    printitemdetails(){
        

        $("#ingredientdetails").html(this.inventory.map(i => new inventory(i).printingredientdetails()));
        $("#supplierdetails").html(`<option id="0">All</option>`)
        $("#supplierdetails").append(this.supplier.map(i => new supplier(i).printsupplier()))
        $("#restockunit").html(this.inventory.map(i => new inventory(i).sendunit()));
        // $("[id='supplierdetails']:eq(1)").html(this.supplier.map(i => new supplier(i).printsupplier()))
        // console.log(this.supplier)

        // ingredientdetailsform.append(ingredientdetails);
        // $("#ingredients-list").html(this.ingredients.map(i=> new ingredients(i).printingredients()))

    }

    getTimeRemaining(x) {
        // console.log(new Date(x))
        // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
        let difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
    
    
    
        let days = difference.getDate(),
        hours = difference.getHours(),
        mins = difference.getMinutes(),
        secs = difference.getSeconds(),
        timeremain;
        if(days!=null){
            timeremain = days+" Days " + hours +" hours "+ mins + " mins " 
        }else if(hours!=null){
            timeremain =  hours +" hours "+ mins + " mins " 
        }else if(mins!=null){
            timeremain = mins + " mins "   
        }else{

        }
      
        
        return timeremain;
    
    }
}