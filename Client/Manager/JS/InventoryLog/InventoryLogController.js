import { InventoryLog } from "./Inventorylog.js"
import { InventoryLogSerializer } from "./InventoryLogSerializer.js"

console.log(123)
$(document).ready(function reload(){
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/Manager/fotterKM.html #KMfotter")
    let authHeader = "Bearer " +window.localStorage.getItem("jwt")
    $.ajax({
        url : "http://localhost:8080/Server_war_exploded/Manager/IngredientsLog/",
        headers: {
            "authorization": authHeader
        }
    }).then(function(data){
        console.log(data)
        let desirialized = data.map(i => InventoryLogSerializer.doserialize(i))
        console.log(desirialized)
        desirialized.map(i => new InventoryLog(i).printInventoryLogs())
    })
})