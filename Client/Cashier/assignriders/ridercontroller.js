

// import { rider } from "./rider.js";
// import { riderserializer } from "./riderseirializer.js";



// $(window).on("load", function () {

//     $("[id^=rider-]").each(function(){
//        // $("#cheflist").html('');  
               
//         console.log(this);
//        $(this).on('click',function(){
//            //   $("#chef").html('');
//             console.log(this.id);
//            var number = this.id.split("-")
//            console.log(number[1]);
//            var orderid = number[1];

//            var authHeader = "Bearer " + window.localStorage.getItem("jwt");
//            $.ajax({
//                url:"http://localhost:8080/Server_war_exploded/Cashier/rider?id=" + number[1],
//                beforeSend: function (xhr) {
//                    xhr.setRequestHeader("authorization", authHeader);
//                }

//            }).then(function (data) {
//                var array = $.parseJSON(JSON.stringify(data));
//                $("#rider").html("");
//                const deSerializedData = array.map(i => riderserializer.doserializer(i));
//                deSerializedData.map(params => new rider(params).printriderlist());
//                console.log(deSerializedData)

               
//                $("#rider").append(button).attr("value",orderid);
//                     $("#riderlist").attr("style","display : block");
//                     // $("#chef").html('');
                     
//                 })


//             })

//          })
//      }

// )