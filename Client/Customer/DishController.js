$(document).ready(function () {
  url_str = window.location.href;
  let url = new URL(url_str);
  let search_params = url.searchParams;

  if (!window.localStorage.getItem("customer")) {
    console.log("hello");
    var signUp = $(document.createElement("li")).addClass("ml-5 btn");

    $("#nav-items").append(signUp);
    var upLink = $(document.createElement("a"))
      .attr("href", "customer-login.html")
      .html("Sign Up");
    signUp.append(upLink);

    var signIn = $(document.createElement("li")).addClass("ml-5 btn bg-black");
    var inLink = $(document.createElement("a"))
      .attr("href", "customer-login.html")
      .html("Sign In");
    signIn.append(inLink);
    $("#nav-items").append(signUp);
    $("#nav-items").append(signIn);
  } else {
    var name = $(document.createElement("li"))
      .addClass("fw-b pl-5")
      .html(window.localStorage.getItem("name"));

    var avatarContainer = $(document.createElement("li")).addClass("pl-5");
    var link = $(document.createElement("a")).attr(
      "href",
      "customer-profile.html"
    );
    var avatar = $(document.createElement("img"))
      .addClass("icon-2")
      .attr("src", window.localStorage.getItem("avatar"));
    link.append(avatar);
    avatarContainer.append(link);
    $("#nav-items").append(avatarContainer);
    $("#nav-items").append(name);
  }

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
