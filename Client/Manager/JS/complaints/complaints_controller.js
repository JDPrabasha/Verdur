import { complaints } from "./complaints.js";
import { Complaints_Serializer } from "./complaints_serializer.js";

$(document).ready(function complaints() {
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/fotterKM.html #KMfotter")
    // console.log(complaints);
    $("#complaints").html('');
    // var x = complaints.map(i=>printcomplaints(i));
    let jwt = "Bearer " + window.localStorage.getItem("jwt");
    $("#form").load("/Client/Manager/Manager-Order-Trace-Pop.html #reader")
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/complaints",
        headers: {
            "authorization": jwt
        },
        success: function (data) {
            let serializedComplaints = data.map(i => Complaints_Serializer.doSerialize(i))
            serializedComplaints.map(i => printcomplaints(i))
            $("#loading").trigger("loaded")
            traceorder();
        },
        error: function () {
            complaints();
        }
    })
})

function printcomplaints(x) {
    var table = $("#complaints");

    let row = $(document.createElement('tr')),

        comID = $(document.createElement('td')).html(x.complaintID),
        cus = $(document.createElement('td')).html(x.customer),
        type = $(document.createElement('td')).html(x.type),
        rider = $(document.createElement('td')).html(x.rider),
        // chef  = $(document.createElement('td')).html(x.chef),
        date = $(document.createElement('td')).html(x.date),
        time = $(document.createElement('td')).html(x.time),
        desc = $(document.createElement('td')).html(x.description).attr("style", "width:30%"),
        button = $(document.createElement('td')).html(`<button class='btn-confirm' id='trace-order-${x.complaintID}'>Trace Order</button>`);


    row.append(comID, cus, type, rider, date, time, desc, button);

    table.append(row);
}

function traceorder() {
    $('[id^=trace-order-').click(function () {
        console.log(this.id.split('-')[2])
        loadComaplaint(this.id.split('-')[2])
    })
    $("#close-btn").click(function () {
        $("#form_frame").attr("style", "display:none;")
        $(".main")[0].classList.remove("blur-back");
        $("#sidenav")[0].classList.remove("blur-back");
        $("#notificationbox")[0].classList.remove("blur-back");
        $("#fotter")[0].classList.remove("blur-back");
        $("#searchQueryInput")[0].classList.remove("blur-back");
    })

    $("#form_frame").on("updated",function(){
        $("#form_frame").attr("style", "display:flex;width:60%")
        $(".main")[0].classList.add("blur-back");
        $("#sidenav")[0].classList.add("blur-back");
        $("#notificationbox")[0].classList.add("blur-back");
        $("#fotter")[0].classList.add("blur-back");
        $("#searchQueryInput")[0].classList.add("blur-back");
    })
}

function loadComaplaint(id){
    let jwt = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Manager/complaints?id="+id,
        headers:{
            "authorization": jwt
        },
        success:function(data){
            let deSerializedData = Complaints_Serializer.doSerialize(data)
            updateComplaintsPopUp(deSerializedData)
        }
    }
    )
}

function updateComplaintsPopUp(data){
    $("#orderID").html(data.orderID)
    $("#customer").html(data.customer)
    $("#address").html(data.location)
    $("#rider").html(data.rider)
    $("#chef").html(data.chef)
    $("#item-count").html(data.itemCount)
    $("#payment").html(data.payment)
    $("#orderItems").html("")
    $("#description").html(data.description)
    try{
        data.orderItems.map(i => addOrderItems(i))
    }catch{
        console.log("orderItems is empty")
    }
    $("#form_frame").trigger("updated")
}

function addOrderItems(itemName){
    let item = $(document.createElement('div')).html(itemName)
    $("#orderItems").append(item)
}
