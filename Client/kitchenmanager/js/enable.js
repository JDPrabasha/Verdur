function enable(rc) {
    let ric = "#" + rc;
    console.log("hi");
    $(ric).css("display","none")
    console.log(rc)
    var jwt = window.localStorage.getItem('jwt');
    var accept = {
        "dishid": parseInt(rc)
    };
    $.ajax({
        type:'PUT',
        url :'http://localhost:8080/Server_war_exploded/kitchenManager/dish/enable',
        headers: {
            "authorization":jwt
        },
        data: JSON.stringify(accept)
    }).done(function(){
        console.log("done");
        $(`#dishcard-${rc}`).attr("style","display:none")
    })
    
}