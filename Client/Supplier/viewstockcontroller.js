$(document).ready(function dishlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    console.log (authHeader) 
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/StockServlet?id=1" ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).then(function(data){
        
         var array = $.parseJSON(data);
         console.log(array);
         const deserializeddata = array.map(i=>dishserializer.doserializer(i));
         deserializeddata.map(params=>new dish(params).printdish());
     })

     
});