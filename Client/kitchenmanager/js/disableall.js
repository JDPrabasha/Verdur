function disableall() {
    var jwt = "Bearer " + window.localStorage.getItem("jwt");
    var url = new URL(window.location.href);
    var ingid = url.searchParams.get("ingID");
    console.log(ingid);
    $.ajax({
        type:'PUT',
        url :'http://localhost:8080/Server_war_exploded/KitchenManager/dish/disableall?ingid='+ingid,
        headers: {
            "authorization":jwt
        },
       
    }).done(function(){
        console.log("done");
        // location.reload();
    })
    
}