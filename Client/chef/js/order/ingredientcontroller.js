import { order } from "./order.js";
import { orderserializer } from "./orderserializer.js";

$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    var url = new URL(window.location.href);
    var dishid = url.searchParams.get("id");
    if(dishid!=null){
        $.ajax({
            url: `http://localhost:8080/Server_war_exploded/Chef/order?id=${dishid}`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            },
        }).then(function (data) {

            var array = $.parseJSON(JSON.stringify(data));
            console.log(array);
            $("#caustomization").html('');
            const deserializeddata = array.map(i => orderserializer.doserializer(i));
            deserializeddata.map(params => new order(params).printdish());
        })
    }
    console.log(dishid);
    
})

