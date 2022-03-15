import { restock } from "./restock.js";
import { restockserializer } from "./restockserializer.js";

$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    var url = new URL(window.location.href);
    var ingid = url.searchParams.get("id");
    console.log(ingid);
    // var ingID =
        $.ajax({
            url: `http://localhost:8080/Server_war_exploded/KitchenManager/restocks?id=${ingid}`,
        
            beforeSend: function (xhr) {
                console.log("requestcontroller");
                xhr.setRequestHeader("authorization", authHeader);
            },
        }).then(function (data) {

            var array = $.parseJSON(JSON.stringify(data));
            console.log(data);
            $("#ingredientdetails").html('');
            const deserializeddata = array.map(i => restockserializer.doserializer(i));
            deserializeddata.map(params => new restock(params).printitemdetails());
        })
})

