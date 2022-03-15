import { dish } from "./dish.js";
import { dishserializer } from "./dishserializer.js";
import {ingredients} from "./ingredients.js";

$(document).ready(function () {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    var url = new URL(window.location.href);
    var id = url.searchParams.get("id");
    console.log(id);
    // var ingID =
        $.ajax({
            url: `http://localhost:8080/Server_war_exploded/KitchenManager/dish?id=${id}`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            },
        }).then(function (data) {

            var array = $.parseJSON(JSON.stringify(data));
            console.log(data);
            $("#updatedishdetail").html('');
            const deserializeddata = array.map(i => dishserializer.doserializer(i));
            // console.log(deserializeddata);
            deserializeddata.map(params => new dish(params).printupdatedish());
            console.log(deserializeddata);
            $("#ingredients-list").trigger("update")
        })


        $("#ingredients-list").on("update",function(){
            $("[id^=edit-").each(function(){
                $(this).click(function(){
                    edit_button(this)
                })
            })
        })
})

function edit_button(x){
    let maindiv = x.parentElement.parentElement
    $(maindiv).find("#ingtype").prop("disabled",false)
    $(maindiv).find("#min").prop("disabled",false)
    $(maindiv).find("#default").prop("disabled",false)
    $(maindiv).find("#max").prop("disabled",false)

    $(x).attr("style", "display:none")
    console.log(x);

    $(`#done-edit-${x.id.split('-')[1]}`).attr("style", "display:block");
    $(`#cancel-edit-${x.id.split('-')[1]}`).attr("style", "display:block");

    let type = $(maindiv).find("#ingtype").val(),
        min = $(maindiv).find("#min").val(),
        defaultv = $(maindiv).find("#default").val(),
        max = $(maindiv).find("#max").val();

        $(`#cancel-edit-${x.id.split('-')[1]}`).click(function (){
            $(maindiv).find('#ingtype').val(type) ;
            $(maindiv).find('#min').val(min) ;
            $(maindiv).find('#default').val(defaultv) ;
            $(maindiv).find('#max').val(max) ;

            $(maindiv).find("#ingtype").prop("disabled",true)
            $(maindiv).find("#min").prop("disabled",true)
            $(maindiv).find("#default").prop("disabled",true)
            $(maindiv).find("#max").prop("disabled",true)

            $(this).attr("style", "display:none")
            $(`#edit-${x.id.split('-')[1]}`).attr("style", "display:inline-block");
            $(`#done-edit-${x.id.split('-')[1]}`).attr("style", "display:none");
         })

        $(`#done-edit-${x.id.split('-')[1]}`).click(function (){  
            $(this).attr("style", "display:none")
            $(`#edit-${x.id.split('-')[1]}`).attr("style", "display:inline-block");
            $(`#cancel-edit-${x.id.split('-')[1]}`).attr("style", "display:none");
         })



}




