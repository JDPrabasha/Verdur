function accept(rc) {
    let ric = "#" + rc;

    $(ric).css("display", "none")
    console.log(rc)
    var jwt = window.localStorage.getItem('jwt');
    var accept = {
        "orderid": parseInt(rc)
    };
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Server_war_exploded/Cashier/CashierOrders',
        headers: {
            "authorization": jwt
        },
        data: JSON.stringify(accept)
    }).done(function () {
        console.log("done");
    })

}