$(document).ready(function restockrequestlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader)
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/UpdateStockServlet?id=1" ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).then(function(data){
        
         var array = $.parseJSON(data);
         console.log(array);
         const deserializeddata = array.map(i=>restockrequestserializer.doserializer(i));
         deserializeddata.map(params=>new restockrequest(params).printrestockrequest());
     })
});