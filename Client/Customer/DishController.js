$(document).ready(function () {
  url_str = window.location.href;
  let url = new URL(url_str);
  let search_params = url.searchParams;

  let id = search_params.get("id");
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  console.log(id);
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/dish/" + id,
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    console.log(data);
    $("div").remove(".skeleton-text , .skeleton-text-small");
    $("p").removeClass("transparent");

    var dish = $.parseJSON(data);
    const deSerializedData = DishSerializer.deSerialize(dish);
    new Dish(deSerializedData).addDishDetails();
    var array = deSerializedData.ingredients;
    console.log(array);
    const deSerializedIngredients = array.map(IngredientSerializer.deSerialize);
    deSerializedIngredients.map((params) =>
      new Ingredient(params).addAllIngredients()
    );
  });
});
