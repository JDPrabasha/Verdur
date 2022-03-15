$(document).ready(function(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Manager/dishRequests",
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
    }).then(function(data){
        var requests = $("#requests");
        requests.html(` `);
        console.log(data)
        var array = $.parseJSON(JSON.stringify(data));
        checkstats(array);
        array.map(params => new Dish_Requests(params).printRequest());
    })
})

function checkstats(array){
    var oldarray = array;
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Manager/dishRequests",
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
    }).then(function(data){
        var array = $.parseJSON(JSON.stringify(data));
        console.log(array);
        console.log(oldarray);
        console.log(JSON.stringify(array)==JSON.stringify(oldarray));
        if(JSON.stringify(array)!=JSON.stringify(oldarray)){
            var requests = $("#requests");
            requests.html(` `);
            array.map(params => new Dish_Requests(params).printRequest());
        }
        setTimeout(function(){
            checkstats(array);
        }, 30000);
    });
}

