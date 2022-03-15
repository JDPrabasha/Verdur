function assignrider(rc) {
    let ric = "#" + rc;
    //  let oid = "#" + orderid;
    var orid = $("#rider").attr("value");
    console.log("hi");
    $(ric).css("display","none")
    console.log(ric)
    console.log(orid)
    var jwt = window.localStorage.getItem('jwt');
    var accept = {
        "riderID": parseInt(rc),
        "orderid": parseInt(orid)
    };
    $.ajax({
        type:'PUT',
        url :'http://localhost:8080/Server_war_exploded/Cashier/rider',
        headers: {
            "authorization":jwt
        },
        data: JSON.stringify(accept)
    }).done(function(){
        console.log("done");
        $(`#dishcard-${rc}`).attr("style","display:none")
    })
    
}