$(document).ready(function () {
  url_str = window.location.href;
  let url = new URL(url_str);
  let search_params = url.searchParams;

  let id = search_params.get("id");
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  console.log(id);
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/ingredient/" + id,
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    console.log(data);
    // var information = $.parseJSON(data);
    // console.log(information);
    const deSerializedData = IngredientSerializer.deSerialize(data);
    console.log(deSerializedData.description);
    new Ingredient(deSerializedData).addIngredientInformation();

    // var ingredient = $.parseJSON(data);
    // console.log(ingredient);
    // const deSerializedData = DishSerializer.deSerialize(ingredient);
    // new Ingredient(deSerializedData).addIngredientInformation();
  });
});
