// $(document).ready(function paymentlist(){
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     // console.log(authHeader)
//     $.ajax({
//         url:"http://localhost:8080/Server_war_exploded/Supplier/PaymentServlet?id=1" ,
//         beforeSend: function(xhr){
//             xhr.setRequestHeader("authorization", authHeader);
//         }

import { payment } from "./payment.js";
import { paymentserializer } from "./payment_serializer.js";

        
//      },).then(function(data){
        
//         //  var array = $.parseJSON(data);
//         var array = $.parseJSON(JSON.stringify(data));
//         console.log(data);
//         var completed = data.filter(i => i.status == "completed")
//         console.log(completed);
//          console.log(array);
//          const deserializeddata = array.map(i=>paymentserializer.doserializer(i));
//          deserializeddata.map(params=>new payment(params).printpaymentcompleted());
         

         
//      })
// });





$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    console.log(authHeader);
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/PaymentServlet?id=1" ,
        // url: "http://localhost:8080/Server_war_exploded/Cashier/PaymentServlet?id=1",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
    }).then(function (data) {

        var array = $.parseJSON(data);
        console.log(data);
        var completed = array.filter(i => i.status == "completed")
        console.log(completed);
        const deserializeddata = completed.map(i => paymentserializer.doserializer(i));
        deserializeddata.map(params=>new payment(params).printcompletedpayment());
        

        console.log(deserializeddata);


    })
});
