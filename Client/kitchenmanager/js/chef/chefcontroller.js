// import { chef } from "./chef.js";
// import { order } from "./order.js";
// import {chefserializer} from "./chefseirializer.js";
// $(document).ready(function cheflistlist() {

//     var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  
//     // console.log(orderID);


//     // var modal = document.getElementById("cheflist");


//     // var btn = document.getElementById("myBtn");



//     // var span = document.getElementsByClassName("close")[0];


//     // btn.onclick = function() {
//     // modal.style.display = "block";
//     // }


//     // function open(){
//     //     var modal = document.getElementById("cheflist");
//     //     modal.style.display = "block"; 

//     // }


//     // span.onclick = function() {
//     // modal.style.display = "none";
//     // }

//     // window.onclick = function(event) {
//     // if (event.target == modal) {
//     //     modal.style.display = "none";
//     // }
//     // }


//         $.ajax({
//             url: `http://localhost:8080/Server_war_exploded/KitchenManager/chef?id=${this.ordrID}`,
//             beforeSend: function (xhr) {
//                 xhr.setRequestHeader("authorization", authHeader);
//             },
//         }).then(function (data) {

//             var array = $.parseJSON(JSON.stringify(data));
//             console.log(array);
//             // $("#cheflist").html('');
//             const deserializeddata = array.map(i => chefserializer.doserializer(i));
//             deserializeddata.map(params => new chef(params).printchelist());
//         })
// })

