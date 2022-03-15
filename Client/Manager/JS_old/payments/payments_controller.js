import { paymentlist } from "./payments.js";

$(document).ready(function(){
    $("#paymentList").html('');
    paymentlist.map(i=>printpayments(i));
})

function printpayments(x){
    let table = $("#paymentList"),
        row = $(document.createElement('tr')).attr("id",x.restockID),
            restockID   = $(document.createElement('td')).html(x.restockID),
            item        = $(document.createElement('td')).html(x.item),
            supplier    = $(document.createElement('td')).html(x.supplier),      
            comdate     = $(document.createElement('td')).html(x.comdate),     
            status      = $(document.createElement('td')).html(`<div class ="status_${x.status}"></div><div class="status_${x.status}_text">${x.status}</div>`),     
            amount      = $(document.createElement('td')).html(x.amount),
            action      = $(document.createElement('td')).attr("style","width:11.5%;");
            if(x.status=="Pending"){
                action.html(`<button class="btn-confirm">Confirm Payment</button>`)
            }
        
        row.append(restockID,item,supplier,comdate,status,amount,action);
    table.append(row);
}