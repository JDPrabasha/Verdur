export class payment{
    constructor(params)
    {
        
        this.invoiceID= params.invoiceID;
        this.invoiceDate= params.invoiceDate;
        this.totalAmount= params.totalAmount;
        this.requestedDate= params.requestedDate;
        this.deliveryDate=params.deliveryDate;
        this.status=params.status;
        
    }
    printpendingpayment(){
            var view = $("#pending");
            var row = $(document.createElement('tr')).attr("id",this.invoiceID);
          
            var invoiceID = $(document.createElement('td')).html(this.invoiceID);
            var invoiceDate = $(document.createElement('td')).html(this.invoiceDate);
            var totalAmount = $(document.createElement('td')).html(this.totalAmount);
            var requestedDate = $(document.createElement('td')).html(this.requestedDate);
            var deliveryDate = $(document.createElement('td')).html(this.deliveryDate);
            console.log("here"+this.status);
            if(this.status==`Payed`)
            {
                var status = $(document.createElement('td')).html( `<button class="mybutton3 tag-green">Completed</button>`);   
            }
            else{
                var status = $(document.createElement('td')).html( `<button class="mybutton2-1">Pending</button>`);

            }
           
               
        row.append(invoiceID);
        row.append(invoiceDate);
        row.append(totalAmount);
        row.append(requestedDate);
        row.append(deliveryDate);
        row.append(status);


        view.append(row);
    }




printallpayment(){
    var view = $("#all");
    var row = $(document.createElement('tr')).attr("id",this.invoiceID);
  
    var invoiceID = $(document.createElement('td')).html(this.invoiceID);
    var invoiceDate = $(document.createElement('td')).html(this.invoiceDate);
    var totalAmount = $(document.createElement('td')).html(this.totalAmount);
    var requestedDate = $(document.createElement('td')).html(this.requestedDate);
    var deliveryDate = $(document.createElement('td')).html(this.deliveryDate);
    console.log("here"+this.status);
    // if(this.status==`Payment Due`)
    // {
    //     var status = $(document.createElement('td')).html( `<button class="btn text-1 bg-clr-paid fw-b text-center">Completed</button>`);   
    //     row.append(status);
    // }
    if(this.status==`Payed`)
            {
                var status = $(document.createElement('td')).html( `<button class="mybutton3 tag-green">Completed</button>`);   
            }
            else{
                var status = $(document.createElement('td')).html( `<button class="mybutton2-1">Pending</button>`);

            }
           
   
   
       
row.append(invoiceID);
row.append(invoiceDate);
row.append(totalAmount);
row.append(requestedDate);
row.append(deliveryDate);
row.append(status);



view.append(row);
}



printcompletedpayment(){
    var view = $("#completed");
    var row = $(document.createElement('tr')).attr("id",this.invoiceID);
  
    var invoiceID = $(document.createElement('td')).html(this.invoiceID);
    var invoiceDate = $(document.createElement('td')).html(this.invoiceDate);
    var totalAmount = $(document.createElement('td')).html(this.totalAmount);
    var requestedDate = $(document.createElement('td')).html(this.requestedDate);
    var deliveryDate = $(document.createElement('td')).html(this.deliveryDate);
    console.log("here"+this.status);
    if(this.status==`Payed`)
    {
        var status = $(document.createElement('td')).html( `<button class="btn text-1 bg-clr-paid fw-b text-center">Completed</button>`); 
        row.append(status);  
    }
   
   
       
row.append(invoiceID);
row.append(invoiceDate);
row.append(totalAmount);
row.append(requestedDate);
row.append(deliveryDate);
row.append(status);



view.append(row);
}

}
function hiderow(rc){
    let ric="#"+rc;
   
    $(ric).css("display","none")
   
}