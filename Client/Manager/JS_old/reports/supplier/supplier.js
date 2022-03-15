export default class supplier{

    constructor(params){
        this.id     = params.id;
        this.name   = params.name;
        this.org    = params.org;
        this.orders = params.orders;
        this.percent= params.percent;
        this.amount = params.amount;
    }

    printRowReports(){
        let table   = $("#topSuppliersReports"),
            row     = $(document.createElement('tr')).attr("id","Sup-Report-"+this.id),

                name    = $(document.createElement('td')).html(this.name),
                org     = $(document.createElement('td')).html(this.org),
                orders  = $(document.createElement('td')).html(this.orders),
                percent = $(document.createElement('td')).html(this.percent+"%"),
                amount  = $(document.createElement('td')).html("Rs."+this.amount.toLocaleString()).attr("style","text-align: right;width: 19%;");
            
            row.append(name,org,orders,percent,amount);
        table.append(row);
                
    }
}