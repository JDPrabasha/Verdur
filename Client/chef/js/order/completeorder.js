function completeorder(rc) {
    let ric = "#" + rc;
    console.log("hi");
    $(ric).css("display","none")
    console.log(rc)
    var jwt = window.localStorage.getItem('jwt');
    var accept = {
        "orderID": parseInt(rc)
    };
    $.ajax({
        type:'PUT',
        url :'http://localhost:8080/Server_war_exploded/Chef/order',
        headers: {
            "authorization":jwt
        },
        data: JSON.stringify(accept)
    }).done(function(){
        console.log("done");
        // $(`#dishcard-${rc}`).attr("style","display:none")
        $("#caustomization").html("");
        var text = $(document.createElement('h2')).attr("class","ml-2").html(`Select An Order`);
        $("#caustomization").append(text);
        window.location.reload();
        

    })
    
}