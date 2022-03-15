class orders{
    constructor(params)
    {
        this.reorderID = params.reorderID;
        this.item= params.item;
        this.quantity= params.quantity;
        this.totalPrice= params.totalPrice;
        this.requestedDate= params.requestedDate;
        this.deliveryDate= params.deliveryDate; 
        this.timeTillDeadline=params.timeTillDeadline;
        this.invoiceDate=params.invoiceDate;
        this.status=params.status;
    }
    printorders(){
            var view = $("#output1");
            var row = $(document.createElement('tr')).attr("id",this.reorderID);
            var reorderID = $(document.createElement('td')).html(this.reorderID);
            var item = $(document.createElement('td')).html(this.item);
            var quantity = $(document.createElement('td')).html(this.quantity);
            var totalPrice = $(document.createElement('td')).html(this.totalPrice);
            var requestedDate = $(document.createElement('td')).html(this.requestedDate);
            var deliveryDate = $(document.createElement('td')).html(this.deliveryDate);
            var timeTillDeadline = $(document.createElement('td')).html(this.timeTillDeadline);
            var invoiceDate = $(document.createElement('td')).html(this.invoiceDate);
            var status = $(document.createElement('td')).html(this.status);
            
            



            

        row.append(reorderID);
        row.append(item);
        row.append(quantity);
        row.append(totalPrice);
        row.append(requestedDate);
        row.append(deliveryDate);
        row.append(timeTillDeadline);
        row.append(invoiceDate);
        row.append(status);
        

        view.append(row);
    }




    printallorders(){
        var view = $("#output2");
        var row = $(document.createElement('tr')).attr("id",this.reorderID);
        var reorderID = $(document.createElement('td')).html(this.reorderID);
        var item = $(document.createElement('td')).html(this.item);
        var quantity = $(document.createElement('td')).html(this.quantity);
        var totalPrice = $(document.createElement('td')).html(this.totalPrice);
        var requestedDate = $(document.createElement('td')).html(this.requestedDate);
        var deliveryDate = $(document.createElement('td')).html(this.deliveryDate);
        var timeTillDeadline = $(document.createElement('td')).html(this.timeTillDeadline);
        var invoiceDate = $(document.createElement('td')).html(this.invoiceDate);
        var status = $(document.createElement('td')).html(this.status);
        
        



        

    row.append(reorderID);
    row.append(item);
    row.append(quantity);
    row.append(totalPrice);
    row.append(requestedDate);
    row.append(deliveryDate);
    row.append(timeTillDeadline);
    row.append(invoiceDate);
    row.append(status);
    

    view.append(row);
}



printcompletedorders(){
    var view = $("#output3");
    var row = $(document.createElement('tr')).attr("id",this.reorderID);
    var reorderID = $(document.createElement('td')).html(this.reorderID);
    var item = $(document.createElement('td')).html(this.item);
    var quantity = $(document.createElement('td')).html(this.quantity);
    var totalPrice = $(document.createElement('td')).html(this.totalPrice);
    var requestedDate = $(document.createElement('td')).html(this.requestedDate);
    var deliveryDate = $(document.createElement('td')).html(this.deliveryDate);
    var timeTillDeadline = $(document.createElement('td')).html(this.timeTillDeadline);
    var invoiceDate = $(document.createElement('td')).html(this.invoiceDate);
    var status = $(document.createElement('td')).html(this.status);
    
    



    

row.append(reorderID);
row.append(item);
row.append(quantity);
row.append(totalPrice);
row.append(requestedDate);
row.append(deliveryDate);
row.append(timeTillDeadline);
row.append(invoiceDate);
row.append(status);


view.append(row);
}


}

function hiderow(rc){
    let ric="#"+rc;
   
    $(ric).css("display","none")
   
}