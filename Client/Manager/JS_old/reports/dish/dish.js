export default class dish{

    constructor(params){
        this.id = params.id;
        this.name = params.name;
        this.code = params.code;
        this.sold = params.sold;
        this.margin = params.margin;
        this.profit = params.profit;
    }

    printRowRecords(){
        let table   = $("#topDishesReports"),
            row     = $(document.createElement('tr')).attr("id","report-dish"+this.id),

                code    = $(document.createElement('td')).html(this.code),
                name    = $(document.createElement('td')).html(this.name),
                sold    = $(document.createElement('td')).html(this.sold),
                margin  = $(document.createElement('td')).html(this.margin+"%"),
                profit  = $(document.createElement('td')).html("Rs."+this.profit.toLocaleString()).attr("style","text-align:right");
            
            row.append(code,name,sold,margin,profit);
        table.append(row);
    }

}