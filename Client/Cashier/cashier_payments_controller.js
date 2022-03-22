let authHeader;
$(document).ready(function cashierpaymentslist(){
    authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    var today = new Date(); var dd = String(today.getDate()).padStart(2, '0'); var mm = String(today.getMonth() + 1).padStart(2, '0'); 
    var yyyy = today.getFullYear(); today = mm + '/' + dd + '/' + yyyy;
    let datepass = yyyy+"-"+mm+"-"+dd; 

    $("#filter_date").val(yyyy+"-"+mm+"-"+dd)
    uiLoad(today,datepass);
    
     
});

$("#filter_date").change(function(){
    let udate = $(this).val(),
    yyyy=udate.slice(0,4),
    mm = udate.slice(5,7),
    dd = udate.slice(8,10),
    today = mm + '/' + dd + '/' + yyyy;
    $("#date_out").html(today)
    datepass =yyyy+"-"+mm+"-"+dd;
    uiLoad(today,datepass)

})


function uiLoad(today,datepass){
    $("#output").html('');
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Cashier/CashierPaymentsServlet?date="+datepass ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).then(function(data){
        
         var array = $.parseJSON(data);
        //  console.log(array);
         const deserializeddata = array.map(i=>cashierpaymentsserializer.doserializer(i));
         deserializeddata.map(params=>new cashierpayments(params).printcashierpayments());
         initializeConfirmButton();
         console.log(array[0]['type']);
var cashtotal=0;
var cardtotal=0;
var total=0;
         for(var i=0;i<array.length;i++){
             if(array[i]['type']==="cash" && array[i]['completed']==="1" ){
                 cashtotal+=array[i]['dueAmount'];

             }
             else if(array[i]['type']==="card"  ){
                cardtotal+=array[i]['dueAmount'];

            }
          
         }
         total=cashtotal+cardtotal;
         



         
         document.getElementById("date").innerHTML='<div class="col-3 mt-25 "><h4>Payments</h4></div>'
         +'<div class="col-2  mt-27 mb-4 status-yellow text-center" ><label class= "  text-center mt-10 text-2  fw-b "> Date <br><div id="date_out" class="text-2" style="font-size: 20px; width: 160px;"> '+ today +'</div> </lable> </div>'
         +'<div class="col-2  mt-27 mb-4 status-green  " ><label class= "  text-center mt-10 text-2  fw-b "> Cash Amount <br><div class="text-1" style="font-size: 20px;">Rs.'+ cashtotal +'</div> </lable> </div>'
         +'<div class="col-2  mt-27 mb-4 status-purple "><lable class= "text-2  text-center fw-b tag-purple " > Card Amount<br> <div class="text-1" style="font-size: 20px;">Rs.'+ cardtotal +'</div> </lable></div>'
         +'<div class="col-2  mt-27 mb-4 status-blue  "><lable class= " text-2  fw-b text-center" > Total Amount<br> <div class="text-1" style="font-size: 20px;">Rs.'+ total +' </div> </lable></div>';
    
     })
}

function initializeConfirmButton(){
    $("[id^=confirm-Pament-").click(function(){
        let id = this.id.split('-')[2],
            jwt = "Bearer " + window.localStorage.getItem("jwt")
        $.ajax({
            url:"http://localhost:8080/Server_war_exploded/Cashier/CashierPaymentsServlet?id="+id,
            type:"PUT",
            headers:{
                "authorization" :jwt
            },
            success: function(){
                window.location = "/Client/Cashier/cashierPayments.html"
            },
            error: function(e){
                console.log(e)
            }
        })
    })
    
}