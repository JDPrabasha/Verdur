let flag = 0;
$(document).ready(function orderslist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    let supplierID = window.localStorage.getItem("id")
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/OrdersServlet?id="+supplierID ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).fail(function (jqXHR, textStatus, errorThrown) {
      window.location.href = "/Client/Manager/Invalid Token.html"
  }).then(function(data){
        
         var array = $.parseJSON(data);
         if($("#output2").length!=0){
            const deserializeddata = array.map(i=>ordersserializer.doserializer(i));
            deserializeddata.map(params=>new orders(params).printallorders());
         }else if($("#output3").length!=0){
            var delivered = array.filter(i => i.status == "delivered")
            const deserializeddata = delivered.map(i => ordersserializer.doserializer(i));
            deserializeddata.map(params=>new orders(params).printcompletedorders());
         }else if($("#output1").length!=0){
            var pending = array.filter(i => i.status == "pending")
            const deserializeddata = pending.map(i => ordersserializer.doserializer(i));
            deserializeddata.map(params=>new orders(params).printorders());
            $("[id^=dead-time-").each(function(){
               let container = $(this),
               storedDate = container.attr("time")
               console.log(storedDate)
               setInterval(function () {
                  container.html(getTimeRemaining(storedDate))
                  // console.log(flag)
                  if(flag!=0){
                      container.attr("style","color:red;")
                  }
              }, 1000)
            })
         }
        $("#loading").trigger("loaded")
         
        //  deserializeddata.map(params=>new orders(params).printcompletedorders()); 
     })
});


function getTimeRemaining(x) {
   // console.log(new Date(x))
   // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
   let difference;
   if(new Date(x) > new Date()) {
       difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
       flag = 0;
   }
   else{
       difference = new Date(new Date() - new Date(x) + (new Date().getTimezoneOffset() * 60 * 1000))
       // console.log("difference = " + difference);
       flag = 1;
   }

   let months = difference.getMonth(), 
   days = difference.getDate(),
   hours = difference.getHours(),
   mins = difference.getMinutes(),
   secs = difference.getSeconds(),
   timeremain;
   if(months!=0){
       timeremain = months+"  "+" Months "+days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
   }else if(days!=0){
       timeremain = days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
   }else if(hours!=0){
       timeremain =  hours +" hours "+ mins + " mins " + secs +" secs"  
   }else if(mins!=0){
       timeremain = mins + " mins " + secs +" secs"  
   }else{
       timeremain =   secs +" secs"  
   }
   return timeremain;
}





// $(document).ready(function orderslist(){
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     // console.log(authHeader)
//     $.ajax({
//         url:"http://localhost:8080/Server_war_exploded/Supplier/OrdersServlet?id=1" ,
//         beforeSend: function(xhr){
//             xhr.setRequestHeader("authorization", authHeader);
//         }
        
//      },).then(function(data){

//         var array = $.parseJSON(data);
//         console.log(data);
//         var pending = array.filter(i => i.status == "pending")
//         console.log(pending);
//         const deserializeddata = pending.map(i => ordersserializer.doserializer(i));
//         deserializeddata.map(params=>new orders(params).printorders());
        

//      })
// });







// $(document).ready(function orderslist(){
//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//     // console.log(authHeader)
//     $.ajax({
//         url:"http://localhost:8080/Server_war_exploded/Supplier/OrdersServlet?id=1" ,
//         beforeSend: function(xhr){
//             xhr.setRequestHeader("authorization", authHeader);
//         }
        
//      },).then(function(data){


//         var array = $.parseJSON(data);
//         console.log(data);
//         var delivered = array.filter(i => i.status == "delivered")
//         console.log(delivered);
//         const deserializeddata = delivered.map(i => ordersserializer.doserializer(i));
//         deserializeddata.map(params=>new orders(params).printcompletedorders());
        
   
//      })
// });




