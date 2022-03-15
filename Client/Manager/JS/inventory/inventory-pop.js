
// $(document).ready(function (){
    $("#addarea").load(`/Client/Manager/Manager-Add-Inventory.html #form_frame`);
// })
function displaypop(){
    document.getElementById("main").classList.add("blur-back");
    document.getElementById("sidenav").classList.add("blur-back");
    document.getElementById("notificationbox").classList.add("blur-back");
    document.getElementById("fotter").classList.add("blur-back");
    document.getElementById("form_frame").style.display="flex";
    document.getElementById("form").style.display="flex";
    $("#addarea").trigger("done");
}
function hidepop(){
    document.getElementById("main").classList.remove("blur-back");
    document.getElementById("sidenav").classList.remove("blur-back");
    document.getElementById("notificationbox").classList.remove("blur-back");
    document.getElementById("fotter").classList.remove("blur-back");
    document.getElementById("form_frame").style.display="none";
    document.getElementById("form").style.display="none";
}
