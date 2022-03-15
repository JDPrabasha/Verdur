import { approval_pending_list } from "./approval_pending_list.js";
import { delivery_pending_list } from "./delivery_pending_list.js";

$(document).ready(function (){
    $("#approvalList").html('');
    approval_pending_list.map(i => printApprovalPending(i));
    $("#deliveryPendingList").html('');
    delivery_pending_list.map(i => printDeliveryPending(i));
})

function printApprovalPending(x){
    var statuses;
    if (x.status == "Manager Pending"){
        statuses = "ManagerPending"
    }else{
        statuses = "SupplierPending"
    }

    let table = $("#approvalList"),
        row = $(document.createElement('tr')).attr("id",x.restockID),
            restockID   = $(document.createElement('td')).html(x.restockID),
            item        = $(document.createElement('td')).html(x.item),
            supplier    = $(document.createElement('td')).html(x.supplier),
            issuedate   = $(document.createElement('td')).html(x.issuedate),
            status      = $(document.createElement('td')).html(`<div class="status_${statuses}"></div><div class="status_${statuses}_text">${x.status}</div>`).attr("style","width:14%"),
            timeremain  = $(document.createElement('td')).html(x.time_remain),
            quantity    = $(document.createElement('td')).html(x.quantity).css("text-align","right"),
            price       = $(document.createElement('td')).html("Rs. " +x.price).css("width","8%").css("text-align","right"),
            action      = $(document.createElement('td')).attr("style","width:14.6%");

            if (x.status == "Manager Pending"){
                action.html('<button class="btn-confirm">Confirm</button> <button class="btn-reject ml-6">Reject</button>');
            }

        row.append(restockID,item,supplier,issuedate,status,timeremain,quantity,price,action);
    table.append(row);
}

function printDeliveryPending(x){
    let table = $("#deliveryPendingList"),
        row = $(document.createElement('tr')).attr("id",x.restockID),
            restockID   = $(document.createElement('td')).html(x.restockID),
            item        = $(document.createElement('td')).html(x.item),
            supplier    = $(document.createElement('td')).html(x.supplier),
            issueddate   = $(document.createElement('td')).html(x.issueddate),
            status      = $(document.createElement('td')).html(`<div class="status_${x.status}"></div><div class="status_${x.status}_text">${x.status}</div>`).attr("style","width:10%"),
            timeremain  = $(document.createElement('td')).html(x.timeremain),
            quantity    = $(document.createElement('td')).html(x.quantity).css("text-align","right"),
            price       = $(document.createElement('td')).html("Rs. "+x.price).css("text-align","right"),
            action      = $(document.createElement('td')).attr("style","width:8%");

            if (x.status == "Pending"){
                action.html('<button class="btn-confirm">Confirm</button>');
            }

        row.append(restockID,item,supplier,issueddate,status,timeremain,quantity,price,action);
    table.append(row);
}