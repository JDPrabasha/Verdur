function changeImage(){
    // var image = $("#Ingredient-Name").active.attr("value");
    $("#ingredient-insert-image").attr("src","https://i.ibb.co/5vGZV96/message-page-1-82-no-items-535x501.png");
    var images= document.getElementById("Ingredient-Name");
    var image =$(images.options[images.selectedIndex]).attr("src");
    // console.log(image);
    $("#ingredient-insert-image").attr("src",image);
}