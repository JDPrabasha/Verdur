import { orderkm } from "./orderkm.js";
import { dish } from "./dish.js";
import { orderkmserializer } from "./orderkmserializer.js";
import { test } from "./test.js";
import { chefserializer } from "../chef/chefseirializer.js";
import {chef} from "../chef/chef.js";

$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader);
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/KitchenManager/orderkm",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
        //  console.log(array);
         const deserializeddata = array.map(i=>orderkmserializer.doserializer(i));
        //  console.log(deserializeddata);
         deserializeddata.map(params=>new orderkm(params).printneworder());
         readAssignChefButton();
     })

     function readAssignChefButton() {
         $("[id^=chef-]").each(function(){
            // $("#cheflist").html('');  
                    
            //  console.log(this);
            $(this).on('click',function(){
                //   $("#chef").html('');
                 console.log(this.id);
                var number = this.id.split("-")
                console.log(number[1]);
                var orderid = number[1];

                var authHeader = "Bearer " + window.localStorage.getItem("jwt");
                $.ajax({
                    url:"http://localhost:8080/Server_war_exploded/KitchenManager/chef?id=" + number[1],
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("authorization", authHeader);
                    }

                }).then(function (data) {
                    var array = $.parseJSON(JSON.stringify(data));
                    const deSerializedData = array.map(i => chefserializer.doserializer(i));
                    $("#chef").html('');
                    deSerializedData.map(params => new chef(params).printchelist());
                     var button = $(document.createElement("button")).html('Assign').attr( "class","btn").attr("onclick","assignchef(document.querySelector('input[name=\"rGroup\"]:checked').value)");
                     $("#chef").append(button).attr("value",orderid);
                    $("#cheflist").attr("style","display : block");
                    // $("#chef").html('');
                     
                })


            })

         })
     }

 

});



