import dishes from "./Data/dishes.js";

function addDish(dish) {
  var dishes = $("#dishes");
  var card = $(document.createElement("div")).addClass("card");
  var content = $(document.createElement("div")).addClass(
    "flex flex-space-evenly"
  );

  var price = $(document.createElement("h1"))
    .html(dish.price)
    .addClass("text-2");
  var name = $(document.createElement("h1"))
    .html(dish.name)
    .addClass("m-1 text-upper");
  var line = $(document.createElement("hr")).addClass("mb-2 mt-2");

  var image = $(document.createElement("img"))
    .attr("src", dish.image)
    .addClass("round-2");
  var button = $(document.createElement("a"))
    .addClass("btn fw-b")
    .attr("href", "dish.html?id=" + dish.id)
    .html("+ Order Now");

  //   <div class="card">
  //   <img class="round-2" src="https://soufflebombay.com/wp-content/uploads/2019/02/Homemade-Blueberry-Swirl-for-Yogurt.jpg" alt="">
  //   <h1 class=" m-1">BLUEBERRY YOGHURT</h1>
  //   <hr class="mb-2 mt-2">
  //   <div class="flex-space-evenly">
  //     <h1 class="text-2">Rs. 600</h1>
  //     <a class= "btn" href="">ORDER NOW</a>
  //   </div>
  // </div>

  card.append(image);
  card.append(name);

  content.append(price);
  content.append(button);
  card.append(line);
  card.append(content);
  dishes.prepend(card);
}

console.log("hello world");

$(document).ready(function () {
  $("#ingredientBtn").click(function () {
    $("#target").show();
  });

  $("#close").click(function () {
    $("#target").hide();
  });

  $("#trigger").click(function () {
    $("#target").submit();
    // var dishes = $("#dishes");
    // dishes.html("");
  });

  $("#myInput").keyup(function () {
    var ich = $("#dishes");
    var input = document.getElementById("myInput");
    var filter = input.value;
    console.log(filter);
    ich.html("");
    const newd = dishes.filter((dish) => {
      return dish.name.toLowerCase().includes(filter);
    });

    console.log(newd);

    newd.map((dish) => {
      addDish(dish);
    });
  });

  // const searchBtn = document.querySelector(".search-btn");
  // const cancelBtn = document.querySelector(".cancel-btn");
  // const searchBox = document.querySelector(".search-box");

  // searchBtn.onclick = () => {
  //   console.log("you");
  //   searchBox.classList.add("active");
  // };

  // cancelBtn.onclick = () => {
  //   searchBox.classList.remove("active");
  // };

  dishes.map((dish) => {
    addDish(dish);
  });

  const newd = dishes.filter((dish) => {
    return dish.id === 4;
  });

  console.log(newd[0]);
});
