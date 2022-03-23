import { order } from "./order.js";
// import { dish } from "./dish.js";
import { orderserializer } from "./orderserializer.js";
// import { test } from "./test.js";

$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader);
    let chefid = localStorage.getItem("id")
    $.ajax({
        // url:"http://localhost:8080/Server_war_exploded/KitchenManager/order?chefID="+chefid,
        url:"http://localhost:8080/Server_war_exploded/Chef/order?chefID="+1,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
         console.log(array);
         const deserializeddata = array.map(i=>orderserializer.doserializer(i));
        //  console.log(deserializeddata);
         deserializeddata.map(params=>new order(params).printneworder());
     })
});
// readdish(function(){
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     var url = new URL(window.location.href);
//     var dishid = url.searchParams.get("id");
//     console.log(dishid);
// })




