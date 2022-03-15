$(document).ready(function orderslist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/OrdersServlet?id=1" ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).then(function(data){
        
         var array = $.parseJSON(data);
         console.log(array);
         const deserializeddata = array.map(i=>ordersserializer.doserializer(i));
         deserializeddata.map(params=>new orders(params).printorders());

         deserializeddata.map(params=>new orders(params).printallorders());
         deserializeddata.map(params=>new orders(params).printcompletedorders()); 
     })
});