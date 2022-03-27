import { order } from "./order.js";
// import { dish } from "./dish.js";
import { orderserializer } from "./orderserializer.js";
// import { test } from "./test.js";

$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    var chefID = window.localStorage.getItem("id")
    console.log(chefID)
    // console.log(authHeader);
    let chefid = localStorage.getItem("id")
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Chef/order?chefID="+chefid,
        // url:"http://localhost:8080/Server_war_exploded/Chef/order?chefID="+1,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).fail(function (jqXHR, textStatus, errorThrown) {
        window.location.href = "/Client/Manager/Invalid Token.html"
    }).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
         console.log(array);
         const deserializeddata = array.map(i=>orderserializer.doserializer(i));
        //  console.log(deserializeddata);
         deserializeddata.map(params=>new order(params).printneworder());
        $("#loading").trigger("loaded")
     })
});





