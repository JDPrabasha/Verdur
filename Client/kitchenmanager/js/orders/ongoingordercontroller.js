import { orderkm } from "./orderkm.js";
import { dish } from "./dish.js";
import { orderkmserializer } from "./orderkmserializer.js";
import { test } from "./test.js";

$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader);
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/KitchenManager/orderkm?type",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
         console.log(array);
         const deserializeddata = array.map(i=>orderkmserializer.doserializer(i));
        console.log(deserializeddata);
         deserializeddata.map(params=>new orderkm(params).printongoingorders());
     })

    //  $(document).ready( function(){
    //     // console.log($("#searchQueryInput").val());
    //     $.ajax({
    //         url: `http://localhost:8080/Server_war_exploded/KitchenManager/orderkm?type`,
    //         beforeSend: function(xhr) {
    //             xhr.setRequestHeader("authorization", authHeader);
    //           },
    //     },).then(function(data) {
    //         // console.log(data['data']);
    //         $("#unassign").html('');
    //         var array = $.parseJSON(JSON.stringify(data));
    //         console.log(array);
    //         const deserializeddata = array.map(i=>orderkmserializer.doserializer(i));
    //         deserializeddata.map(params=>new orderkm(params).printongoingorders());
    //     })

    // })

});
