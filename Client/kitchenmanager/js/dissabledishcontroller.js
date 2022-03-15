import { dish } from "./dish.js";
import { dishserializer } from "./dishserializer.js";

$(document).ready(function dishlist() {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    var url = new URL(window.location.href);
    var ingID = url.searchParams.get("ingID");
    // console.log(c);
    // var ingID =
        $.ajax({
            url: `http://localhost:8080/Server_war_exploded/KitchenManager/dish?ingID=${ingID}`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            },
        }).then(function (data) {

            var array = $.parseJSON(JSON.stringify(data));
            console.log(array);
            $("#dishbying").html('');
            const deserializeddata = array.map(i => dishserializer.doserializer(i));
            deserializeddata.map(params => new dish(params).printdishbyingredient());
        })
})

