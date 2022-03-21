import { riderserializer } from "./assignriders/riderseirializer.js";
import { rider } from "./assignriders/rider.js";
import { orders_cashier } from "./orders_cashier.js";
import { dish } from "./dish.js";
import { orders_cashier_serializer } from "./orders_cashier_serializer.js";
import { test } from "./test.js";
import { order } from "./assignriders/order.js";
var cookedOrders = new Array
$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader);
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Cashier/CashierOrders?type=cooked",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
    }).then(function (data) {

        var array = $.parseJSON(JSON.stringify(data));
        console.log(data);
        $("#riderorderslist").html("")
        cookedOrders = array.map(i => orders_cashier_serializer.doserializer(i));
        cookedOrders.map(params => new orders_cashier(params).printriderorderslist());
        readAssignRiderButton()


        // console.log(deserializeddata);


    })

    assignriderButton();


    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Cashier/CashierOrders?type=confirmrider",
        headers:
        {
            "authorization": authHeader
        }
    }).then(function(data){
     
        // console.log(array)
        $("#confirmdelivery").html("")
        const deSerializedData = data.map(i => riderserializer.doserializer(i));
        console.log(deSerializedData)

        deSerializedData.map(params => new rider(params).printconfirmassignedorders());
        
    })

})




function assignriderButton() {
    $("#Popup_AssignButton").click(function () {
        // console.log("hey")
        let orderID = $("#riderOrderName").attr("orderid"),
            riderID = $('input[name="rGroup"]:checked').val();
        if (riderID == undefined) {
            alert("Please Select a Rider!")
        } else {
            var authHeader = "Bearer " + window.localStorage.getItem("jwt");
            $.ajax({
                url: "http://localhost:8080/Server_war_exploded/Cashier/CashierOrders/",
                type: 'PUT',
                headers: {
                    "authorization": authHeader
                },
                data: JSON.stringify({
                    "orderID" : orderID,
                    "riderID" : riderID
                })
            }).then(
                console.log("done")
            )
        }

    })
}


function readAssignRiderButton() {
    $("[id^=rider-]").each(function () {
        // $("#cheflist").html('');  

        // console.log(this);
        $(this).on('click', function () {
            //   $("#chef").html('');
            // console.log(this.id);
            var number = this.id.split("-")
            console.log(number[1]);
            var orderid = number[1];
            var filteredCooked = cookedOrders.filter(i => i['orderID'] == orderid)
            console.log(filteredCooked)
            filteredCooked.map(params => new orders_cashier(params).printriderorders());
            //    console.log(cookedOrders)



            var authHeader = "Bearer " + window.localStorage.getItem("jwt");

            $("#myModal").attr("style", "display : block");

            $.ajax({
                url: "http://localhost:8080/Server_war_exploded/Cashier/CashierOrders?id=" + orderid,

                beforeSend: function (xhr) {
                    xhr.setRequestHeader("authorization", authHeader);
                },
            }).then(function (data) {
                console.log(data)

                // console.log(data[0]["dishitem"])
                $("#pop_up_dish_list").html(data[0]["dishitem"].map(i => new dish(i).printdish()))


            })
            $.ajax({
                url: "http://localhost:8080/Server_war_exploded/Cashier/rider",

                beforeSend: function (xhr) {
                    xhr.setRequestHeader("authorization", authHeader);
                },
            }).then(function (data) {


                console.log(data)
                $("#rider").html("");
                const deSerializedData = data.map(i => riderserializer.doserializer(i));
                deSerializedData.map(params => new rider(params).printriderlist());
                console.log(deSerializedData)
                // $("#pop_up_dish_list").html(data[0]["dishitem"].map(i => new dish(i).printdish()))

            })




        })

    })
}









