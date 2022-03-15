import { complaints } from "./complaints.js";

$(document).ready(function () {
    // console.log(complaints);
    $("#complaints").html('');
    var x = complaints.map(i=>printcomplaints(i));
})

function printcomplaints(x){
    var table = $("#complaints");

    let row = $(document.createElement('tr')),
    
    comID  = $(document.createElement('td')).html(x.complaintID),
    cus    = $(document.createElement('td')).html(x.customer),
    type   = $(document.createElement('td')).html(x.type),
    rider  = $(document.createElement('td')).html(x.rider),
    // chef  = $(document.createElement('td')).html(x.chef),
    date   = $(document.createElement('td')).html(x.date),
    time   = $(document.createElement('td')).html(x.time),
    desc   = $(document.createElement('td')).html(x.description).attr("style","width:30%"),
    button = $(document.createElement('td')).html("<button class='btn-confirm'>Trace Order</button>");


    row.append(comID,cus,type,rider,date,time,desc,button);

    table.append(row);
}
