import { dish } from "./dish.js";
import { dishserializer } from "./dishserializer.js";
$(document).ready(function dishlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/KitchenManager/dish",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
         console.log(array);
         const deserializeddata = array.map(i=>dishserializer.doserializer(i));
         deserializeddata.map(params=>new dish(params).printdish());
         $("#loading").trigger("loaded")

     }),

     $("#searchQuerySubmit").click( function(){
        // console.log($("#searchQueryInput").val());
        $.ajax({
            url: `http://localhost:8080/Server_war_exploded/KitchenManager/dish?search=${$("#searchQueryInput").val()}`,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("authorization", authHeader);
              },
        },).then(function(data) {
            // console.log(data['data']);
            $("#results").html('');
            var array = $.parseJSON(JSON.stringify(data));
            console.log(array);
            const deserializeddata = array.map(i=>dishserializer.doserializer(i));
            deserializeddata.map(params=>new dish(params).printdish());
            $("#loading").trigger("loaded")
        })

    })

    function disablebuttonButton() {
        $("[id^=error-]").each(function(){
           // $("#cheflist").html('');  
                   
            console.log(this);
           $(this).on('click',function(){
               //   $("#chef").html('');
                console.log(this.id);
               var number = this.id.split("-")
               console.log(number[1]);
               var orderid = number[1];

               var authHeader = "Bearer " + window.localStorage.getItem("jwt");
               $.ajax({
                   url:"http://localhost:8080/Server_war_exploded/KitchenManager/dish?id=" + number[1],
                   beforeSend: function (xhr) {
                       xhr.setRequestHeader("authorization", authHeader);
                   }

               }).then(function (data) {
                   var array = $.parseJSON(JSON.stringify(data));
                   const deSerializedData = array.map(i => chefserializer.doserializer(i));
                 
                  
                   $("#msg").attr("style","display : block");
                   // $("#chef").html('');
                    
               })


           })

        })
    }


});
