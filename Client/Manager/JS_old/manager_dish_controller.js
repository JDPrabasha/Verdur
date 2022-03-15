$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Manager/dishes",
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
    }).then(function(data){
        var array = $.parseJSON(JSON.stringify(data));
        const deSerializedData = array.map(i=>Manager_dish_serializer.doSerialize(i));
        deSerializedData.map(params => new Dish(params).printDish());
    })
})