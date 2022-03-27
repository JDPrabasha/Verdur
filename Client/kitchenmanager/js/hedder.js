$(document).ready(function(){
    // $("#sidenavkm").html(` 
    //     <img class="mt-4" src="/Client/images/Asset 304x.png" alt="">    
    //     <a href="home.html"><i class="material-icons">home</i></a>
    //     <a href="inventory.html"><i class="material-icons">inventory_2</i></a>

       
    //     <a href="dish.html"><i class="material-icons">lunch_dining</i></a>
    //     <a href="restockrequestorders.html"><i class="material-icons">autorenew</i></a>`);
    $("#notification").load("/Client/kitchenmanager/headerkm.html #notification",function(){
        $("#header_name").html(window.localStorage.getItem("name"))
        $("#header_image").attr("src",window.localStorage.getItem("photo"))
        // console.log(window.localStorage.getItem("photo"))
    })
  
})
