import { supplier_List } from "./supplier_list.js";
import { supplier } from "./supplier.js";
import { supplierSerializer } from "./supplier_serializer.js";

$.getScript("/Client/Manager/JS/side_menu.js");

$(document).ready(function reload(){
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/Manager/fotterKM.html #KMfotter")
    $("#add_pop").load("/Client/Manager/Manager-Add-Supplier-Pop.html #form_frame")
    $.getScript("/Client/Manager/JS/functionalities/popup.js");
    //image Upload Script
    $.getScript("/Client/Manager/JS/functionalities/image_upload.js");
    
    // $("#supplier-tbody").html('');
    // let list = supplier_List;
    // let serialized_list = list.map(i => supplierSerializer.doSerialize(i));
    // serialized_list.map(i => new supplier(i).printSupplierRow());

    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Manager/Supplier",
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
})

function updateView(list){
    $("#supplier-tbody").html('');
    // let list = supplier_List;
    let serialized_list = list.map(i => supplierSerializer.doSerialize(i));
    serialized_list.map(i => new supplier(i).printSupplierRow());
}