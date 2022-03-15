import { payment } from "./payment.js";
import { paymentserializer } from "./payment_serializer.js";

$(document).ready(function paymentlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/PaymentServlet?id=1" ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).then(function(data){
        
         var array = $.parseJSON(data);
         console.log(array);
         const deserializeddata = array.map(i=>paymentserializer.doserializer(i));
         deserializeddata.map(params=>new payment(params).printallpayment());
         

         
     })
});


// $(document).ready(function orderslist(){
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     // console.log(authHeader)
//     $.ajax({
//         url:"http://localhost:8080/Server_war_exploded/Supplier/OrdersServlet?id=1" ,
//         beforeSend: function(xhr){
//             xhr.setRequestHeader("authorization", authHeader);
//         }
        
//      },).then(function(data){
        
//          var array = $.parseJSON(data);
//          console.log(array);
//          const deserializeddata = array.map(i=>ordersserializer.doserializer(i));
//          deserializeddata.map(params=>new orders(params).printorders());

//          deserializeddata.map(params=>new orders(params).printallorders());
//          deserializeddata.map(params=>new orders(params).printcompletedorders()); 
//      })
// });
// import { orders_cashier } from "./payment.js";

// import { orders_cashier_serializer } from "./payment_serializer.js";


// $(document).ready(function () {
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     console.log(authHeader);
//     $.ajax({
//         url: "http://localhost:8080/Server_war_exploded/Cashier/PaymentServlet?id=1",
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader("authorization", authHeader);
//         },
//     }).then(function (data) {

//         var array = $.parseJSON(JSON.stringify(data));
//         console.log(data);
//         var pending = data.filter(i => i.status == "pending")
//         console.log(pending);
//         const deserializeddata = pending.map(i => orders_cashier_serializer.doserializer(i));
//         deserializeddata.map(params=>new payment(params).printpaymentpending());
        

//         console.log(deserializeddata);


//     })
// });


