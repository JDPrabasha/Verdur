import { orders_cashier } from "./orders_cashier.js";
import { dish } from "./dish.js";
import { orders_cashier_serializer } from "./orders_cashier_serializer.js";
import { test } from "./test.js";

$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader);
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Cashier/CashierOrders?type=all",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
    }).then(function (data) {

        var array = $.parseJSON(JSON.stringify(data));
        // console.log(data);
        var pending = data.filter(i => i.status == "pending")
        // console.log(pending);
        const deserializeddata = pending.map(i => orders_cashier_serializer.doserializer(i));
        deserializeddata.map(params => new orders_cashier(params).printneworder());
        $("#loading").trigger("loaded")

        // console.log(deserializeddata);


    })


});


