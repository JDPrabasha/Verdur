$(document).ready(function(){
    // $(" #notification").html(` 
    //     <img class="mt-4" src="/Client/images/Asset 304x.png" alt="">    
    //     <a href="home.html"><i class="material-icons">home</i></a>
    //     <a href="inventory.html"><i class="material-icons">inventory_2</i></a>

       
    //     <a href="dish.html"><i class="material-icons">lunch_dining</i></a>
    //     <a href="restockrequestorders.html"><i class="material-icons">autorenew</i></a>`);
    $("#notification").load("/Client/Cashier/header.html #notification",function(){
        $("#header_image").attr("src",window.localStorage.getItem("avatar"))
        $("#header_name").html(window.localStorage.getItem("name"))
    })
  
})
