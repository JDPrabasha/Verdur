function reject(rc) {


    $("#rejectModel").attr("style", "display : block");

    console.log(rc)
    $("#orderID").val(rc)


}

// function submit(rc){


// }

// function submit(rc) {
//     let ric = "#submit" + rc;

//     $(ric).css("display", "none")
//     console.log(rc)
//     var jwt = window.localStorage.getItem('jwt');
//     // var submit = {
//     //     "orderid": parseInt(rc)
//     // };
// }




function submit() {
    let ric = $("#orderID").val(),
        reason = $("#reason").val()

    

    var jwt = "Bearer " + window.localStorage.getItem('jwt');
 


   
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Server_war_exploded/Cashier/CashierOrders/reject?orderID='+ric+'&reason='+reason,
        headers: {
            "authorization": jwt
        }
    
       
    }).done(function () {
     
       console.log("done")
    })

    


}