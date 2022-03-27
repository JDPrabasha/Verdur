import { orderkm } from "./orderkm.js";
import { dish } from "./dish.js";
import { orderkmserializer } from "./orderkmserializer.js";
import { test } from "./test.js";

let flag = 0;
$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    // console.log(authHeader);
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/KitchenManager/orderkm?type",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
    }).then(function (data) {

        var array = $.parseJSON(JSON.stringify(data));
        console.log(array);
        const deserializeddata = array.map(i => orderkmserializer.doserializer(i));
        console.log(deserializeddata);
        deserializeddata.map(params => new orderkm(params).printongoingorders());

        $("[id^=orderTime").each(function () {
            var timecont = $(this),
                time = timecont.attr("time")
            setInterval(function () {
                timecont.html(getTimeRemaining(time))
                    // console.log(flag)
                    if(flag!=0){
                        timecont.attr("style","color:red;")
                    }
                
            }, 1000);
            //  $(this).html(getTimeRemaining($(this).attr("time")))
        })
    })

    function getTimeRemaining(x) {
        // console.log(new Date(x))
        // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
        if (new Date(x) > new Date()) {
            let difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
            // console.log("difference = " + difference);




            let months = difference.getMonth(),
                days = difference.getDate(),
                hours = difference.getHours(),
                mins = difference.getMinutes(),
                secs = difference.getSeconds(),
                timeremain;
            if (months != 0) {
                timeremain = hours + ":" + mins + ":" + secs
            } else if (days != 0) {
                timeremain = hours + " :" + mins + " :" + secs
            } else if (hours != 0) {
                timeremain = hours + ":" + mins + ":" + secs
            } else if (mins != 0) {
                timeremain = "00:" + mins + ":" + secs
            } else {
                timeremain = "00:00:" + secs
            }
            flag=0;
            return timeremain;
        }else{
            let difference = new Date(new Date() - new Date(x) + (new Date().getTimezoneOffset() * 60 * 1000))
            // console.log("difference = " + difference);


            flag = 1;

            let months = difference.getMonth(),
                days = difference.getDate(),
                hours = difference.getHours(),
                mins = difference.getMinutes(),
                secs = difference.getSeconds(),
                timeremain;
            if (months != 0) {
                timeremain =  hours + ":" + mins + ":" + secs
            } else if (days != 0) {
                timeremain = hours + " :" + mins + " :" + secs
            } else if (hours != 0) {
                timeremain = hours + ":" + mins + ":" + secs
            } else if (mins != 0) {
                timeremain = "00:" + mins + ":" + secs
            } else {
                timeremain = "00:00:" + secs
            }
            return timeremain;
        }

    }

    //  $(document).ready( function(){
    //     // console.log($("#searchQueryInput").val());
    //     $.ajax({
    //         url: `http://localhost:8080/Server_war_exploded/KitchenManager/orderkm?type`,
    //         beforeSend: function(xhr) {
    //             xhr.setRequestHeader("authorization", authHeader);
    //           },
    //     },).then(function(data) {
    //         // console.log(data['data']);
    //         $("#unassign").html('');
    //         var array = $.parseJSON(JSON.stringify(data));
    //         console.log(array);
    //         const deserializeddata = array.map(i=>orderkmserializer.doserializer(i));
    //         deserializeddata.map(params=>new orderkm(params).printongoingorders());
    //     })

    // })

});
