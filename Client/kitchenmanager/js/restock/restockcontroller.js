import { restock } from "./restock.js";

import { restockserializer } from "./restockserializer.js";



$(document).ready(function restocklist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    console.log(authHeader);
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/KitchenManager/restocks",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
         console.log(array);
         const deserializeddata = array.map(i=>restockserializer.doserializer(i));
         console.log(deserializeddata);
         deserializeddata.map(params=>new restock(params).printrestock());
         console.log("hi")
         $("#loading").trigger("loaded")

     })
});