class cashierpayments{
    constructor(params)
    {
        // this.itemID = params.itemID;
        this.orderID= params.orderID;
        this.riderID = params.riderID;
        this.dueAmount= params.dueAmount;
        this.assignedTime= params.assignedTime;
        this.status= params.status; 
        this.type=params.type;
        this.paymentCompleted=params.paymentCompleted;

        this.date = this.assignedTime.split(" ")[0];
        this.time = this.assignedTime.split(" ")[1];
    
    }
    printcashierpayments(){
        
        // if($this.delivered=="Yes"){
        //     var button = "<button>Confirm</button>";
          
        // } 
            var view = $("#output");
            var row = $(document.createElement('tr')).attr("id",this.orderID);
            var orderID = $(document.createElement('td')).html(this.orderID);
            var riderID = $(document.createElement('td')).html(this.riderID);
            var dueAmount = $(document.createElement('td')).html(this.dueAmount);
            var assignedTime = $(document.createElement('td')).html(this.time);
            // var assignedTime = $(document.createElement('td')).html(this.assignedTime);
            // var delivered = $(document.createElement('td')).html(this.delivered);
            // var delivered = $(document.createElement('td')).html(this.delivered);
            // console.log(this.status)
            if(this.status==`delivered` )

            {
                var delivered = $(document.createElement('td')).html(`Yes`);   
            }

            else{
                var delivered = $(document.createElement('td')).html( `No`);

            }
            // console.log(this.orderID + " "+ this.paymentCompleted +" "+this.status)
            
            if(this.paymentCompleted==`0` && this.status==`delivered` )
            {
                var payment = $(document.createElement('td')).html( `<button id="confirm-Pament-${this.orderID}" class="mybutton3 tag-blue">Confirm</button>`);

            }

            else{
                var payment = $(document.createElement('td')).html('');   


            }

            // var confirm = $(document.createElement('td')).html(button);

            

            // var today = new Date(); var dd = String(today.getDate()).padStart(2, '0'); var mm = String(today.getMonth() + 1).padStart(2, '0'); 
            // var yyyy = today.getFullYear(); today = mm + '/' + dd + '/' + yyyy;
            
            // document.getElementById("date").innerHTML='<div class="col-3 mt-25 "><h4>Payments</h4></div>'
            // +'<div class="col-2  mt-27 mb-4 status-yellow text-center" ><label class= "  text-center mt-10 text-2  fw-b "> Date <br><div class="text-2" style="font-size: 20px; width: 160px;"> '+ today +'</div> </lable> </div>'
            // +'<div class="col-2  mt-27 mb-4 status-green  " ><label class= "  text-center mt-10 text-2  fw-b "> Cash Amount <br><div class="text-1" style="font-size: 20px;">'+ 2000 +'</div> </lable> </div>'
            // +'<div class="col-2  mt-27 mb-4 status-purple "><lable class= "text-2  text-center fw-b tag-purple " > Card Amount<br> <div class="text-1" style="font-size: 20px;">Rs.'+ 10000 +'</div> </lable></div>'
            // +'<div class="col-2  mt-27 mb-4 status-blue  "><lable class= " text-2  fw-b text-center" > Total Amount<br> <div class="text-1" style="font-size: 20px;">Rs.'+ 30000 +' </div> </lable></div>';

            
              

        row.append(orderID);
        row.append(riderID);
        row.append(dueAmount);
        row.append(assignedTime);
        row.append(delivered);
        row.append(payment);
 
        

        view.append(row);
    }
}

function hiderow(rc){
    let ric="#"+rc;
   
    $(ric).css("display","none")
   
}