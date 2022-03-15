import { Notification } from "./notifications.js";
import { Notification_Serializer } from "./Notification_Serializer.js";


$(document).ready(function retry(){
    let jwt = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/notification",
        type : 'GET',
        headers : {
            "authorization" : jwt
        },
        success: function(data){
            if(data==null){
                retry();
            }else{
                notificationUpdate(data)
            }
        }
    })
    
})

function notificationpop() {
    // console.log($(".notification-pop"))
    $(".notification-pop")[0].classList.toggle("notification-popup")
}

function removenotificationpop(){
    $(".notification-pop")[0].classList.remove("notification-popup")
}

function notificationUpdate(x){
    if(x.length!=0){
        $(".notification-count").html(x.length).attr("style","display:block")
        $(".notification-pop").html('')
        let SerializedNotification = x.map(i=>Notification_Serializer.doSerialize(i))
        SerializedNotification.map(i => new Notification(i).printNotification())
        $("#notification-bubble").click(function(){
            notificationpop();
        })
        $(".main").click(function(){
            removenotificationpop();
        })
        removeNotification();
    }
    else{
        $(".notification-count").attr("style","display:none")
    }
    
}

function removeNotification(){
    $("[id^=remove-notif-").click(function(){
        let jwt = "Bearer " + window.localStorage.getItem("jwt"),
            id = this.id.split("-")[2]
        $.ajax({
            url:"http://localhost:8080/Server_war_exploded/Manager/notification/"+id,
            type: 'DELETE',
            headers : {
                "authorization" : jwt
            },
            success: deleteNotificationUI(id)
        })
    })
        
}

function deleteNotificationUI(id){
    $("#notifID-"+id).attr("style","display:none");
    let count = $(".notification-count");
    count.html(parseInt(count)-1)
}