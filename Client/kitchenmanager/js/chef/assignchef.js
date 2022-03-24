// function assignchef(rc) {
//     let ric = "#" + rc;
//     console.log("hi");
//     $(ric).css("display","none")
//     console.log(rc)
//     var jwt = window.localStorage.getItem('jwt');
//     var accept = {
//         "chefid": parseInt(rc)
//         // "id": parseInt(rc)
//     };
//     $.ajax({
//         type:'PUT',
//         url :'http://localhost:8080/Server_war_exploded/KitchenManager/chef',
//         headers: {
//             "authorization":jwt
//         },
//         data: JSON.stringify(accept)
//     }).done(function(){
//         console.log("done");
//         $(`#dishcard-${rc}`).attr("style","display:none")
//     })
    
// }

function assignchef(rc) {
    let ric = "#" + rc;
    //  let oid = "#" + orderid;
    var orid = $("#chef").attr("value");
    console.log("hi");
    $(ric).css("display","none")
    console.log(ric)
    console.log(orid)
    var jwt = window.localStorage.getItem('jwt');
    var accept = {
        "chefid": parseInt(rc),
        "orderid": parseInt(orid)
    };
    $.ajax({
        type:'PUT',
        url :'http://localhost:8080/Server_war_exploded/KitchenManager/chef',
        headers: {
            "authorization":jwt
        },
        data: JSON.stringify(accept)
    }).done(function(){
        console.log("done");
        $(`#dishcard-${rc}`).attr("style","display:none")
        window.location.reload();
    })
    
}