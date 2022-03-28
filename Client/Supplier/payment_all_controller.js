import { payment } from "./payment.js";
import { paymentserializer } from "./payment_serializer.js";

$(document).ready(function paymentlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    var supplierID = window.localStorage.getItem("id")
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/PaymentServlet?id="+supplierID ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).fail(function (jqXHR, textStatus, errorThrown) {
      window.location.href = "/Client/Manager/Invalid Token.html"
  }).then(function(data){
        
         var array = $.parseJSON(data);
         if($("#all").length!=0){
            const deserializeddata = array.map(i=>paymentserializer.doserializer(i));
            deserializeddata.map(params=>new payment(params).printallpayment());
         }else if($("#completed").length!=0){
            console.log("completed")
            var completed = array.filter(i => i.status == "completed")
            const deserializeddata = completed.map(i => paymentserializer.doserializer(i));
            deserializeddata.map(params=>new payment(params).printcompletedpayment());
         }else if($("#pending").length!=0){
            var pending = array.filter(i => i.status == "pending")
            console.log(pending);
            const deserializeddata = pending.map(i => paymentserializer.doserializer(i));
            deserializeddata.map(params=>new payment(params).printpendingpayment());
         }
         
        $("#loading").trigger("loaded")
         

         
     })
});


// $(document).ready(function () {
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     console.log(authHeader);
//     $.ajax({
//         url:"http://localhost:8080/Server_war_exploded/Supplier/PaymentServlet?id=1" ,
//         // url: "http://localhost:8080/Server_war_exploded/Cashier/PaymentServlet?id=1",
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader("authorization", authHeader);
//         },
//     }).then(function (data) {

//         var array = $.parseJSON(data);
//         console.log(data);
//         var completed = array.filter(i => i.status == "completed")
//         console.log(completed);
//         const deserializeddata = completed.map(i => paymentserializer.doserializer(i));
//         deserializeddata.map(params=>new payment(params).printcompletedpayment());
        

//         console.log(deserializeddata);


//     })
// });



// $(document).ready(function paymentlist() {
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     console.log(authHeader);
//     $.ajax({
//         url:"http://localhost:8080/Server_war_exploded/Supplier/PaymentServlet?id=1" ,

//         // url: "http://localhost:8080/Server_war_exploded/Cashier/PaymentServlet?id=1",
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader("authorization", authHeader);
//         },
//     }).then(function (data) {

//         var array = $.parseJSON(data);
//         console.log(array);
//         var pending = array.filter(i => i.status == "pending")
//         console.log(pending);
//         const deserializeddata = pending.map(i => paymentserializer.doserializer(i));
//         deserializeddata.map(params=>new payment(params).printpendingpayment());
        

//         // console.log(deserializeddata);


//     })
// });






