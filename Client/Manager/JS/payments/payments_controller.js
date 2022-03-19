import { paymentlist } from "./payments.js";

$(document).ready(function reload(){
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/Manager/fotterKM.html #KMfotter")
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/RestockOrders",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        },
        error:function(){
            reload();
        }
    }).then(function (data) {
        updateView(data);
        $("#loading").trigger("loaded")
    })
    // $("#paymentList").html('');
    // paymentlist.map(i=>printpayments(i));
})

function updateView(data){
    $("#paymentList").html('');
    data.map(i=>printpayments(i));
}

function printpayments(x){
    if(x.status=="Payment Due")
    { 
        x.status = "Pending"
    }else{
        x.status = "Completed"
    }
    let table = $("#paymentList"),
        row = $(document.createElement('tr')).attr("id",x.restockID),
            restockID   = $(document.createElement('td')).html(x.restockID),
            item        = $(document.createElement('td')).html(x.item),
            supplier    = $(document.createElement('td')).html(x.supplier),      
            comdate     = $(document.createElement('td')).html(x.comdate),     
            status      = $(document.createElement('td')).html(`<div class ="status_${x.status}"></div><div class="status_${x.status}_text">${x.status}</div>`),     
            amount      = $(document.createElement('td')).html("Rs. " + x.amount.toLocaleString()),
            action      = $(document.createElement('td')).attr("style","width:11.5%;");
            if(x.status=="Pending"){
                action.html(`<button class="btn-confirm">Confirm Payment</button>`)
            }
        
        row.append(restockID,item,supplier,comdate,status,amount,action);
    table.append(row);
}