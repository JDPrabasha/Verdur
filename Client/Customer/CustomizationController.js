$(document).ready(function () {
  url_str = window.location.href;
  let url = new URL(url_str);
  let search_params = url.searchParams;
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  let edit = search_params.get("edit");
  let id = search_params.get("id");
  let cid = search_params.get("customID");
  if (edit == "true") {
    console.log("jk");
    // getCustomizationDetails();
    getDishDetails();
  } else {
    getDishDetails();

    console.log(id);
  }

  function getCustomizationDetails() {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/Server_war_exploded/customize/edit?id=" + cid,
      headers: {
        authorization: authHeader,
      },

      success: function () {
        console.log("pass");

        console.log(payment);
      },
      failure: function () {
        console.log("fail");
        alert("fail");
      },
    });
  }

  function getDishDetails() {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/Server_war_exploded/customize/" + id,
      headers: {
        authorization: authHeader,
      },
    }).then(function (data) {
      $("div").remove(".skeleton-text , .skeleton-text-small");
      console.log(data);

      var dish = $.parseJSON(data);
      console.log(dish);
      const deSerializedData = DishSerializer.deSerialize(dish);
      new Dish(deSerializedData).addDishDetails();
      var array = deSerializedData.ingredients;

      const deSerializedIngredients = array.map(
        IngredientSerializer.deSerialize
      );
      console.log(deSerializedIngredients);
      deSerializedIngredients.map((params) =>
        new Ingredient(params).addCustomizableIngredients()
      );
      let i = document.querySelector("input");

      // use 'change' instead to see the difference in response
      // i.addEventListener(
      //   "input",
      //   function () {
      //     console.log(i.value);
      //   },
      //   false
      // );

      console.log("cshcsh");
    });
  }
});
