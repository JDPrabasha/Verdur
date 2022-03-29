import { dish } from "./dish.js";
import { dishserializer } from "./dishserializer.js";
let dishData;
$(document).ready(function dishlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/KitchenManager/dish",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).fail(function (jqXHR, textStatus, errorThrown) {
        window.location.href = "/Client/Manager/Invalid Token.html"
    }).then(function(data){
        
         var array = $.parseJSON(JSON.stringify(data));
         dishData = array;
         console.log(array);
         const deserializeddata = array.map(i=>dishserializer.doserializer(i));
         deserializeddata.map(params=>new dish(params).printdish());
         $("#loading").trigger("loaded")

     }),

     $("#searchQuerySubmit").click( function(){
        let searchString = $("#searchQueryInput").val()
        let filteredData = (filterData(dishData,searchString));
        $("#results").html('')
        const deserializeddata = filteredData.map(i=>dishserializer.doserializer(i));
         deserializeddata.map(params=>new dish(params).printdish());
        // console.log($("#searchQueryInput").val());
        // $.ajax({
        //     url: `http://localhost:8080/Server_war_exploded/KitchenManager/dish?search=${$("#searchQueryInput").val()}`,
        //     beforeSend: function(xhr) {
        //         xhr.setRequestHeader("authorization", authHeader);
        //       },
        // },).fail(function (jqXHR, textStatus, errorThrown) {
        //     window.location.href = "/Client/Manager/Invalid Token.html"
        // }).then(function(data) {
        //     // console.log(data['data']);
        //     $("#results").html('');
        //     var array = $.parseJSON(JSON.stringify(data));
        //     console.log(array);
        //     const deserializeddata = array.map(i=>dishserializer.doserializer(i));
        //     deserializeddata.map(params=>new dish(params).printdish());
        //     $("#loading").trigger("loaded")
        // })

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

function filterData(array,string){
    return array.filter(i => filterRecord(i,string))
}

function filterRecord(array,string){
    let i ,x = new Array;
    for (i in array){
        if(i=="image"){
            continue;
        }
        if (array[i].toString().toLowerCase().includes(string.toLowerCase())){
            // console.log("true")
            // console.log(array["name"] + " " + array[i])
            return true
        }
    }
    console.log("false")
    return false
}
