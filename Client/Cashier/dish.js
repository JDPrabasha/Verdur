export class dish{
    constructor(params)
    {
       
        
        this.dishID = params.dishID;
        this.dishname = params.dishname;
        this.dishimage = params.image;
        this.time = params.time;
        this.quantity = params.quantity;
        this.price = params.price;

      
    } 
    printdish(){
        var container = $(document.createElement('div'));
        var dish =$(document.createElement('div')).attr("class","flex-space-evenly").attr("id",this.dishID).html(`
        <img class="round-2" src="${this.dishimage}" alt="" style="width:90px;height:90px;">
        <div style="flex-direction: column;">
            <h1 class=" m-1 text-upper">${this.dishname}</h1>
            <div class="ml-12">
                <p>Quantity : x${this.quantity}</p>
                <p>Rs.${this.price}x${this.quantity}</p>
            </div>    
        </div>`);
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        container.append(dish,hr)

        return container;
      
    }
}