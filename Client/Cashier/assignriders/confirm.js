function confirm(rc) {
    let ric = "#confirmdelivery" + rc;

    

    var jwt = "Bearer " + window.localStorage.getItem('jwt');
 

   
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Server_war_exploded/Cashier/CashierOrders/confirm?deliveryID='+rc,
        headers: {
            "authorization": jwt
        },
       
    }).done(function () {
       $(ric).css("display", "none")
       console.log("done")
    })

}