$(document).ready(function () {
    // console.log($("#file-input"))
    $("#file-input").on("change", function () {
        console.log("change")
        let dataval = new FormData($("#image-form")[0])
        var jwt = "Bearer " + window.localStorage.getItem('jwt');
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/ImageTest",
            type: "POST",
            headers: {
                "authorization": jwt
            },
            enctype: 'multipart/form-data',
            data: dataval,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log(data);
                $("#label-image").attr("src", "http://localhost:8080/Server_war_exploded/Images/" + data['imageID']).attr("imageid",data['imageID'])

            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        })
    })

    $("#close-pop").click(function () {
        deleteImage("/Client/images/employee-icon.jpg")
    })

    $("#close-pop-inv").click(function(){
        deleteImage("https://www.alstedefarms.com/wp-content/plugins/wp-blog-and-widgets/placeholder.png")
    })
})

function deleteImage(defaultImage){
    id = $("#label-image").attr("imageid")
    var jwt = "Bearer " + window.localStorage.getItem('jwt');
    if(id!=undefined && id!=""){
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/ImageTest/"+id,
            type: "DELETE",
            headers: {
                "authorization": jwt
            },
            success: function () {
                $("#label-image").attr("src", defaultImage)
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        })
    }
}