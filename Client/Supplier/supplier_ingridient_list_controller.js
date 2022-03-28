$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/IngredientServlet",
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        window.location.href = "/Client/Manager/Invalid Token.html"
    }).then(function(data){
        var requests = $("#items1");
        requests.html(` `);
        var array = (data);
        console.log("this is the array");
        console.log(array);
        const deserialized = array.map(i=>Supplier_Ingredient_serializer.doSerialize(i));
        console.log("After serialized");
        console.log(deserialized);
        
        // checkstats(array);
        deserialized.map(params => new Ingredient_list(params).addListing() );

    })
})