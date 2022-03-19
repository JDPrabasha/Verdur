import { dish_new } from "./dish_new.js";
import { dish_new_serialier } from "./dish_new_serializer.js";

$(document).ready(function () {
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/fotterKM.html #KMfotter")

    $("#main").on("done", function () {
        $(".card").each(function () {
            $(this).on('click', function () {
                console.log(this.id);
                getDishPopUpDetails(this.id)
            })
        })



        $("#form").on("done", function () {
            // $("#request-out").html('');
            document.getElementById("main").classList.add("blur-back");
            document.getElementById("sidenav").classList.add("blur-back")
            // $("#sidenav").trigger('pop');
            document.getElementById("notificationbox").classList.add("blur-back");
            document.getElementById("fotter").classList.add("blur-back");
            document.getElementById("form_frame").style.display = "flex";
            document.getElementById("form").style.display = "flex";
            disableScrolling();
            $("#approve").click(function () {
                console.log($(this).attr("val"))
                approvalRequest($(this).attr("val"), "approve")
            })
            $("#reject").click(function () {
                approvalRequest($(this).attr("val"), "reject")
            })
        })
    })

    function approvalRequest(dishid, approval) {
        var result = {
            "dishID": dishid
        }
        var authHeader = "Bearer " + window.localStorage.getItem("jwt");
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/dishes/" + approval,
            type: "PUT",
            data: JSON.stringify(result),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            }
        }).then(function () {
            console.log("done")
            console.log(dishid)
            hidepop();
        })
    }

    let searchParams = new URLSearchParams(window.location.search),
        dishID = searchParams.get('id'),
        requestType = searchParams.get('type');
    if (dishID != null) {
        getDishPopUpDetails(dishID,requestType)
    }

})

function getDishPopUpDetails(id,requestType) {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/dishes?id=" + id,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
        error:function(){
            getDishPopUpDetails(id,requestType)
        }
    }).then(function (data) {
        // var array = $.parseJSON(JSON.stringify(data));
        // // console.log("DISH")
        // // console.log(array)
        // // console.log("DISH")
        // const deSerializedData = array.map(i => dish_new_serialier.doSerialize(i));
        // deSerializedData.map(params => new dish_new(params).updateDishPop());
        // $("#form").trigger("done");
        if(requestType!=null){
            $("#request-out").html("(" + requestType + ")")
        }
        var array = $.parseJSON(JSON.stringify(data));
        const deSerializedData = array.map(i => dish_new_serialier.doSerialize(i));
        if (array.map(i => i.updatedDish).includes(undefined)) {
            deSerializedData.map(params => new dish_new(params).updateDishPop());
            $("#form").trigger("done");
        } else {
            console.log(deSerializedData)
            deSerializedData.map(params => new dish_new(params).updateDishPopUpdated());
            $("#form").trigger("done");
        }

    })
}