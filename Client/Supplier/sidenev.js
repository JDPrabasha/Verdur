$(document).ready(function(){
    // $(" #sidenav").html(` 
    //     <img class="mt-4" src="/Client/images/Asset 304x.png" alt="">    
    //     <a href="home.html"><i class="material-icons">home</i></a>
    //     <a href="inventory.html"><i class="material-icons">inventory_2</i></a>

       
    //     <a href="dish.html"><i class="material-icons">lunch_dining</i></a>
    //     <a href="restockrequestorders.html"><i class="material-icons">autorenew</i></a>`);
    $("#sidenav").load("/Client/Supplier/sidemenusupplier.html #sidenav")
    $("#sidenav").attr("style","transform:scale(1.3);transform-origin: 0% 0% 0px")
})
function showinfo(x){
    $(`#s-${x}`).attr("style","display:block")
}
function hideinfo(x){
    $(`#s-${x}`).attr("style","display:none")
}
