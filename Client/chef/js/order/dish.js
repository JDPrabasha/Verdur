export class dish{
    constructor(params)
    {
       
        
        this.dishid = params.dishid;
        this.dishname = params.dishname;
        this.defaultprice = params.defaultprice;
        this.estimatedtime = params.estimatedtime;
        this.dishimage = params.image;
        this.enable = params.enable;
        this.quantity = params.quantity;

      
    } 
    printdish(){
        var container = $(document.createElement('div'));
        var dish =$(document.createElement('div')).attr("class","flex-space-evenly").attr("id",this.dishid).html(`
        <img class="round-2" src="${this.dishimage}" alt="" style="width:90px;height:90px;">
        <div style="flex-direction: column;">
            <h1 class=" m-1 text-upper">${this.dishname}</h1>
            <div class="ml-12">
                <p>Quantity : ${this.quantity}</p>
                <a class= "btn  tag-green text-center text-bold"  href="/Client/chef/home.html?id=${this.dishid}" style="size:1cm;">View</a>
            </div>    
        </div>`);
        var hr = $(document.createElement('hr')).attr("class","mb-2 mt-2")
        container.append(dish,hr)

        return container;
      
    }

    printdishtopic(){

        var topic = $(document.createElement('div')).html(` <div class="row"><div class="col-3 "></div><div class="col-6"> <h4 class="text-darkgrey">${this.dishname}</h4></div> </div>
        <div class="row"><div class="col-4"></div><div class="col-6"><h2 class="text-info">Quantity : ${this.quantity}</h2></div></div>`);

        return topic
       
    }
}

