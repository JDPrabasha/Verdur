$(document).ready(function sideMenu() {
    // $(document.getElementById("sidenav")).html(`<img class="" src="/Client/images/Asset 304x.png" alt="">
    //         <a href="/Client/Manager/Manager-Home.html"><i class="material-icons">home</i></a>
    //         <a href="/Client/Manager/Manager-Payments.html"><i class="material-icons">request_page</i></a>
    //         <a href="/Client/Manager/Manager-Restock.html"><i class="material-icons">fact_check</i></a>
    //         <a href="/Client/Manager/Manager-Inventory.html"><i class="material-icons">inventory</i></a>
    //         <a href="/Client/Manager/Manager-Dishes.html"><i class="material-icons">restaurant_menu</i></a>
    //         <a href="/Client/Manager/Manager-Employee.html"><i class="material-icons">person_search</i></a>
    //         <a href="/Client/Manager/Manager-Supplier.html"><i class="material-icons">local_shipping</i></a>`);
    $("#sidenav").load("/Client/sidemenu.html #sidenav")
    $(document.getElementById("sidenav")).attr("style","transform:scale(1.3);transform-origin: 0% 0% 0px;");
});
function showinfo(x){
    $(`#s-${x}`).attr("style","display:block")
}
function hideinfo(x){
    $(`#s-${x}`).attr("style","display:none")
}