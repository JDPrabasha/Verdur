import { approval_pending_list } from "./approval_pending_list.js";
import { delivery_pending_list } from "./delivery_pending_list.js";
import { restockRequest } from "./restockRequest.js";

let flag = 0;
$(document).ready(function reload() {
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/Manager/fotterKM.html #KMfotter")

    // $("#approvalList").html('');
    // approval_pending_list.map(i => printApprovalPending(i));
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/Restock",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
        error:function(){
            reload();
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        window.location.href = "/Client/Manager/Invalid Token.html"
    }).then(function (data) {
        $("#approvalList").html('');
        data.map(i => new restockRequest(i).printApprovalPending());
        $("#loading").trigger("loaded")
        $('[id^=time-cal-').each(function(){
            // console.log($(this).attr("time"))
            let time = $(this).attr("time"),
            timecont = $(this)
            setInterval(function () {
                timecont.html(getTimeRemaining(time))
                console.log(flag)
                if(flag!=0){
                    timecont.attr("style","color:red;")
                }
            }, 1000);
        }
        )
    })

    // $("#deliveryPendingList").html('');
    // delivery_pending_list.map(i => printDeliveryPending(i));


    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/Restock?type=1",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
    }).fail(function (jqXHR, textStatus, errorThrown) {
        window.location.href = "/Client/Manager/Invalid Token.html"
    }).then(function (data) {
        $("#deliveryPendingList").html('');
        console.log(data)
        data.filter(i=>i.status=="pending").map(i => new restockRequest(i).printDeliveryPending());
        data.filter(i=>i.status=="delivered").slice(0,4).map(i => new restockRequest(i).printDeliveryPending());
        updateButtons();
        $("#loading").trigger("loaded")
        $('[id^=time-cal-').each(function(){
            // console.log($(this).attr("time"))
            let time = $(this).attr("time"),
            timecont = $(this)
            if(time!=null){
                setInterval(function () {
                    timecont.html(getTimeRemaining(time))
                    if(flag!=0){
                        timecont.attr("style","color:red;")
                    }
                }, 1000);
            }
        }
        )
    })

    

})

function updateRestockApproval(x, y) {
    console.log(x);
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    let result = {
        "restockID": x,
        "approvalstatus": y
    }
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/Restock",
        type: "PUT",
        headers: {
            "authorization": authHeader
        },
        data: JSON.stringify(result)
    }).done(function (){
        console.log("done");
        location.reload()
    }).fail(function(){
        console.log("fail");
    })
}


function updateRestockStatus(x) {
    console.log(x);
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    let result = {
        "restockID": x,
        "status": "Delivered"
    }
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/Restock",
        type: "PUT",
        headers: {
            "authorization": authHeader
        },
        data: JSON.stringify(result)
    }).done(function (){
        console.log("done");
        location.reload()
    }).fail(function(){
        console.log("fail");
    })
}

function updateButtons() {
    //confirm buttons
    $(".btn-confirm").each(function () {
        $(this).click(function () {
            let buttonid = $(this.parentElement.parentElement).attr("id").split("-");
            if (buttonid[0] == "approval") {
                // updateRestockApproval(buttonid[1], "Manager Approved")
                updateRestockApproval(buttonid[1], "managerApproved")
            }else if(buttonid[0] == "delivery"){
                updateRestockStatus(buttonid[1])
            }
        })
    })

    //reject buttons
    $(".btn-reject").each(function () {
        $(this).click(function () {
            let buttonid = $(this.parentElement.parentElement).attr("id").split("-");
            if (buttonid[0] == "approval") {
                // updateApproval(buttonid[1], "Manager Declined")
                updateRestockApproval(buttonid[1], "managerDecline")
                // console.log(buttonid[1])
            }
        })
    })

}

function getTimeRemaining(x) {
    // console.log(new Date(x))
    // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
    let difference;
    if(new Date(x) > new Date()) {
        difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
        flag = 0;
    }
    else{
        difference = new Date(new Date() - new Date(x) + (new Date().getTimezoneOffset() * 60 * 1000))
        // console.log("difference = " + difference);
        flag = 1;
    }

    let months = difference.getMonth(), 
    days = difference.getDate(),
    hours = difference.getHours(),
    mins = difference.getMinutes(),
    secs = difference.getSeconds(),
    timeremain;
    if(months!=0){
        timeremain = months+"  "+" Months "+days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(days!=0){
        timeremain = days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(hours!=0){
        timeremain =  hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(mins!=0){
        timeremain = mins + " mins " + secs +" secs"  
    }else{
        timeremain =   secs +" secs"  
    }
    return timeremain;
}