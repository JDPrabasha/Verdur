export class supplier{

    constructor(params){
        this.id          = params.supplierID;
        this.name        = params.name;
        this.image       = params.image;
        this.org         = params.org;
        this.ordersDue   = params.ordersDue;
        this.ordersDone  = params.ordersDone;
        this.email       = params.email;
        this.contact     = params.contact;
        this.location    = params.location;
        this.joinedon    = params.joinedon;
        this.pendingPay  = params.pendingPay;
    }

    printSupplierRow(){
        let table   = $("#supplier-tbody"),
            row1    = $(document.createElement('tr')).attr("id","rec-"+this.id),
                // image   = $(document.createElement('td')).html(`<img class="icon-3 icon" src="${this.image}">`),
                image   = $(document.createElement('td')).html(`<img class="icon-3 icon" src="${this.image}">`),
                name    = $(document.createElement('td')).html(`
                            <p class="text-1-300">${this.name}</p>
                            <p class="text-grey ">${this.org}</p>`),
                due     = $(document.createElement('td')).html(this.ordersDue),
                done    = $(document.createElement('td')).html(this.ordersDone),
                contact = $(document.createElement('td')).html(`
                            <p>${this.email}</p>
                            <p class="text-grey">${this.contact}</p>`),
                location= $(document.createElement('td')).html(this.location),
                button  = $(document.createElement('td')).html(`<div class="dropdown" onclick="expand(1)"><i class="material-icons icon">arrow_drop_down</i></div>`),
            
            row2   = $(document.createElement('tr')).attr("id","rec-e-"+this.id),
                data = $(document.createElement('td')).attr("colspan","6").html(``)

            row1.append(image,name,due,done,contact,location,button);
        table.append(row1);
                
    }
}