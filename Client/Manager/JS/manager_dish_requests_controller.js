import { dish_new } from "./dish/dish_new.js";
import { dish_new_serialier } from "./dish/dish_new_serializer.js";

$(document).ready(function reload() {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/dishRequests",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
        error:function(){
            reload()
        }
    }).then(function (data) {
        var requests = $("#requests");
        requests.html(` `);
        console.log(data)
        var array = $.parseJSON(JSON.stringify(data));
        checkstats(array);
        // $("#cards").html('');
        array.map(params => new Dish_Requests(params).printRequest());
        updateReview();
        // $(".card").each(function(){
        //     $(this).on('click',function(){
        //         console.log(this.id);
        //     })
        // })
    })
})

function checkstats(array) {
    var oldarray = array;
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/dishRequests",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        }
    }).then(function (data) {
        var array = $.parseJSON(JSON.stringify(data));
        console.log(array);
        console.log(oldarray);
        console.log(JSON.stringify(array) == JSON.stringify(oldarray));
        if (JSON.stringify(array) != JSON.stringify(oldarray)) {
            var requests = $("#requests");
            requests.html(` `);
            array.map(params => new Dish_Requests(params).printRequest());
            updateReview();
        }
        setTimeout(function () {
            checkstats(array);
        }, 30000);
    });
}

function updateReview() {
    // console.log("came here")
    $("[id^=deny-]").each(function () {
        // console.log(this.id.split("-")[1]);
        $(this).on('click', function() {
            console.log(this.id);
            var dishid = this.id.split("-")[1],
                result = {
                    "dishID": dishid
                }
            var authHeader = "Bearer " + window.localStorage.getItem("jwt");
            $.ajax({
                url: "http://localhost:8080/Server_war_exploded/Manager/dishes/reject",
                type: "PUT",
                data: JSON.stringify(result),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("authorization", authHeader);
                },
            }).then(function (data) {
                console.log("done")
                checkstats([]);
            })

        })
    })


    //  -----------Review Button ---------------------------
    $("[id^=review-").each(function () {
        // console.log(this.id)
        $(this).on("click", function () {
            var id_no = this.id.split('-')[1]
            let request_type = $(this.parentElement.parentElement).html();
            if (request_type.includes("Add Dish")) {
                $("#request-out").html("(Add Dish)")
            } else if (request_type.includes("Update Dish")) {
                $("#request-out").html("(Update Dish)")
            } else if (request_type.includes("Delete Dish")) {
                $("#request-out").html("(Delete Dish)")
            }
            var authHeader = "Bearer " + window.localStorage.getItem("jwt");
            $.ajax({
                url: "http://localhost:8080/Server_war_exploded/Manager/dishes?id=" + id_no,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("authorization", authHeader);
                }
            }).then(function (data) {
                var array = $.parseJSON(JSON.stringify(data));
                // console.log("DISH")
                // console.log(array)
                // console.log("DISH")
                // console.log(array.map(i=>i.updatedDish).includes(undefined));
                const deSerializedData = array.map(i => dish_new_serialier.doSerialize(i));
                if (array.map(i => i.updatedDish).includes(undefined)) {
                    deSerializedData.map(params => new dish_new(params).updateDishPop());
                    $("#form").trigger("done");
                }else{
                    console.log(deSerializedData)
                    deSerializedData.map(params => new dish_new(params).updateDishPopUpdated());
                    $("#form").trigger("done");
                }  
            })
        })

    })
}

