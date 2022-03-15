$(document).ready(function() {
    getList(),
    setTimeout(function(){
        changeImage();
        changeType();
    }, 1500),
    $("#Ingredient-Name").on('change', function (){
        changeImage()
    }),$("#Ingredient-Category").on('change', function (){
        var category = $("#Ingredient-Category").val();
       getList(category)
           
        setTimeout(function(){
            changeImage()
        }, 100)
    }),$("#Ingredient-Type").on('change', function(){
        changeType();
    })
})


function getList(type = "All"){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        
        url: `http://localhost:8080/Server_war_exploded/Manager/ingredients?type=${type}`,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
    },).then(function(data) {
        // console.log(data);
        $("#Ingredient-Name").html('');
        var array=$.parseJSON(JSON.stringify(data));
        console.log(array);
            const deSerializedData = array.map(i=>Ingredient_List_Serializer.doSerializer(i));
            deSerializedData.map(params => new Ingredient_List(params).addSelection());
    })
}

function changeImage(){
    // var image = $("#Ingredient-Name").active.attr("value");
    $("#ingredient-insert-image").attr("src","https://i.ibb.co/5vGZV96/message-page-1-82-no-items-535x501.png");
    var images= document.getElementById("Ingredient-Name");
    var image =$(images.options[images.selectedIndex]).attr("src");
    // console.log(image);
    $("#ingredient-insert-image").attr("src",image);
}

function changeType(){
    var category = $("#Ingredient-Type");
        if(category.val()=="Fixed"){
            // $("#form_default").attr("display","block")
            $("#form_min_wrap").css({"display":"none"})
            $("#form_max_wrap").css({"display":"none"})
            $("#right-reset").css({"display":"block"})
            $("#left-reset").css({"display":"none"})
        }else{
            // $("#form_default").attr("display","0")
            $("#form_min_wrap").css({"display":"block"})
            $("#form_max_wrap").css({"display":"block"})
            $("#right-reset").css({"display":"none"})
            $("#left-reset").css({"display":"block"})
        }
}

function displaypop(){
    document.getElementById("main").classList.add("blur-back");
    document.getElementById("form_frame").style.display="flex";
    document.getElementById("form").style.display="flex";
}
function hidepop(){
    document.getElementById("main").classList.remove("blur-back");
    document.getElementById("form_frame").style.display="none";
    document.getElementById("form").style.display="none";
}


function addingredient(){
    var image = document.getElementById("ingredient-insert-image").src,
     option1 = document.getElementById("Ingredient-Name"),
     name = option1.options[option1.selectedIndex].text,
     code = $(option1.options[option1.selectedIndex]).attr("code");
     ingID = option1.value,
     option2 = document.getElementById("Ingredient-Type"),
     type = option2.options[option2.selectedIndex].text,
     minimum = $("#form_min").val(),
     defaultv = $("#form_default").val(),
     maximum = $("#form_max").val();
     document.getElementById("ingredients-list").innerHTML += `<td><img class="icon-1" src="${image}"></td>
                         <td>${code}</td>
                         <td>${name}</td>
                         <td style="display:none;">${ingID}</td>
                         <td>${type}</td>
                         <td>${minimum}</td>
                         <td>${defaultv}</td>
                         <td>${maximum}</td>`;
     //remove items already inserted
     $('#ingredients-list tr').each(function(row, tr) {
         console.log("hmm");
         $(`#Ingredient-Name option[value='${$(tr).find('td:eq(3)').text()}']`).remove();
     });
     $("#form_min").val(''),
     $("#form_default").val(''),
     $("#form_max").val('');
     hidepop();
     changeImage();
 }

 function submitDish(){
     var Ingredients = new Array();
     $('#ingredients-list tr').each(function(row, tr) {
         Ingredients[row] = {
             "ingID"     : parseInt($(tr).find('td:eq(3)').text()),
             "type"      : $(tr).find('td:eq(4)').text(),
             "minimum"   : parseInt($(tr).find('td:eq(5)').text()),
             "defaultv"  : parseInt($(tr).find('td:eq(6)').text()),
             "maximum"   : parseInt($(tr).find('td:eq(7)').text())
         } //tableData[row]
     });
     Ingredients.shift();
     var dishcode   = $("#dishcode").val(),
     dishname       = $("#dishname").val(),
     estimatedtime  = $("#estimatedTime").val(),
     description    = $("#description").val(),
     image          = "https://www.kindpng.com/picc/m/79-798754_hoteles-y-centros-vacacionales-dish-placeholder-hd-png.png";

     var json = JSON.stringify({
         "dishcode"         : dishcode,
         "dishname"         : dishname,
         "estimatedtime"    : estimatedtime,
         "description"      : description,
         "Ingredients"      : Ingredients,
         "image"            : image
     })
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/Server_war_exploded/Manager/dish";
    xhr.open("POST", url, true);
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    xhr.setRequestHeader("authorization", authHeader);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // var json = JSON.parse(xhr.responseText);
            // alert("Employee Added Successfully!");
            if(!alert("Dish Added Successfully!")){window.location.reload();}
        }else if(xhr.readyState === 4 && xhr.status==406 &&alertx!=0 ){
            // alert("Employee Creation Error!");
            if(!alert("Dish Added Creation Error!")){alertx=0;}
        }else if(xhr.status==500 && alertx!=0){
            if(!alert("Invalid Data !")){alertx=0;}
        }
    };
    console.log(json);
    xhr.send(json);
 }