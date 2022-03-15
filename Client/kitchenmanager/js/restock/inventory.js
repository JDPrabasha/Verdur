export class inventory{
constructor(params)
    {
        this.ingid = params.ingid;
        this.itemcode = params.itemcode;
        this.itemtype = params.itemtype;
        this.ingname = params.ingname;
        this.quantity = params.quantity;
        this.maxlevel = params.maxquantity;
        this.safelevel = params.safelevel;
        this.restocklevel = params.restocklevel;
        this.image = params.image;
        this.restockdate = params.restockdate;
        this.unit = params.unit;

        this.presentage = parseInt(this.quantity/this.maxlevel*100)
    }

    printingredientdetails(){
        var ingredient = $(document.createElement('div')).attr("class","main flex-center").html(`  <form style="font-size: 0.9cm;">
                    
        <div class="image-upload m-3 ">
                
                <label for="file-input">
                    <img class="icon-10" src="${this.image}">
            
            </div>    
            
        </form>
        <form class="m-6 mt-12" style="width: 60% ;font-size:0.7cm;">
            
            <label class="text-bold">Item</label>
            <p class="mb-8">${this.ingname}</p>
            <label class="text-bold"> Current Stock   </label>
            <p>${this.quantity} ${this.unit}</p>
            <input class="mb-8"  type="range" min="1" max="100" value="${this.presentage}" disabled>
            <label class="text-bold mt-8">Current Level</label><br>
           <p class="m-4"> Maximum Capacity : ${this.maxlevel}  ${this.unit}</p>
           <p class="m-4">Safe Level : ${this.safelevel}  ${this.unit}</p>
           <p class="m-4">Critical Level : ${this.restocklevel}  ${this.unit}</p>   
        </form>`);
        return ingredient;


    }
    sendunit()
    {
        var unit = $(document.createElement('div')).html(`${this.unit}`);
        return unit;
    }

 
}