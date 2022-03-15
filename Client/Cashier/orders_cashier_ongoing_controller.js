
import { orders_cashier } from "./orders_cashier.js";
import { orders_cashier_serializer } from "./orders_cashier_serializer.js";


$(document).ready(function ongoingorderslist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Cashier/CashierOrders?type=ongoing" ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).then(function(data){
        var array = $.parseJSON(JSON.stringify(data));
        //  var array = $.parseJSON(data);
         console.log(data);
         const deserializeddata = array.map(i => orders_cashier_serializer.doserializer(i));

         deserializeddata.map(params => new orders_cashier(params).printongoingorders());
     })
});
