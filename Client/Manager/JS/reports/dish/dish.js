export default class dish{

    constructor(params){
        this.id = params.id;
        this.name = params.name;
        this.code = params.code;
        this.sold = params.sold;
        this.margin = params.margin;
        this.profit = params.profit;
        this.totalSales = params.totalSales;
    }

    printRowRecords(totalSales){
        let table   = $("#topDishesReports"),
            row     = $(document.createElement('tr')).attr("id","report-dish"+this.id),

                // code    = $(document.createElement('td')).html(this.code),
                name    = $(document.createElement('td')).html(this.name),
                sold    = $(document.createElement('td')).html(this.sold),
                // margin  = $(document.createElement('td')).html(this.margin+"%"),
                margin  = $(document.createElement('td')).html((this.sold/totalSales*100).toFixed(2)+"%"),
                // profit  = $(document.createElement('td')).html("Rs."+this.profit.toLocaleString()).attr("style","text-align:right");
                profit  = $(document.createElement('td')).html("Rs."+this.totalSales.toLocaleString()).attr("style","text-align:right");
            // dishChartData.append(this.name + this.sold)
            // row.append(code,name,sold,margin,profit);
            row.append(name,sold,margin,profit);
        table.append(row);
    }

    getNameAndSoldList(x){
        x.push([
            this.name,
            this.sold
        ])
    }

}