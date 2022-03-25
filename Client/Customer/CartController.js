$(window).on("load", function () {
  var ingredientDetailsArray = [];

  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var customer = window.localStorage.getItem("customer");

  //for cart

  function getCartItems() {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/Server_war_exploded/cart/" + customer,
      headers: {
        authorization: authHeader,
      },
    }).then(function (data) {
      console.log(data);
      var array = $.parseJSON(data);
      console.log(array);
      if (array.length) {
        const deSerializedData = array.map(DishSerializer.deSerialize);
        console.log(deSerializedData);
        console.log("hyu");
        deSerializedData.map((params) => new Dish(params).addCartItem());
      } else {
        var text = $(document.createElement("p"))
          .addClass(" fw-b text-center ")
          .html(
            "Looks like your cart is empty at the moment. How about taking a look around our menu?"
          );

        var image = $(document.createElement("img"))
          .attr("src", "../images/emptyCart.png")
          .attr("width", "40%")
          .addClass("ml-70");
        $("#cart").empty();
        $("#cart").append(image);
        $("#cart").append(text);
      }
    });
  }

  getCartItems();

  $("#cart-items").on("click", ".remove", function () {
    id = $(this).attr("data-id");
    $.ajax({
      type: "DELETE",
      url: "http://localhost:8080/Server_war_exploded/cart/" + id,
      headers: {
        authorization: authHeader,
      },

      success: function () {
        console.log("yatta");
        getCartItems();
      },
      failure: function () {
        console.log("fail");
      },
    });
  });

  $(".modal-close").click(function (e) {
    e.preventDefault();
    $("#vtf").removeClass("modal-active ");
    $(".modal").hide();
  });

  $("#cartBtn").click(function (e) {
    e.preventDefault();
    $("#vtf").toggleClass("modal-active ");
    var quantity = parseInt($("#quantity").html());
    var price = parseInt($("#price").html().split(" ")[1]);
    $("#totalPrice").html("Rs. " + quantity * price);
    $(".modal").show();
  });

  $("#increaseCount").click(function (e) {
    e.preventDefault();
    var quantity = parseInt($("#quantity").html());
    var price = parseInt($("#price").html().split(" ")[1]);

    quantity += 1;
    $("#quantity").html(quantity > 9 ? quantity : "0" + quantity);
    $("#totalPrice").html("Rs. " + quantity * price);
  });
  $("#decreaseCount").click(function (e) {
    e.preventDefault();
    e.preventDefault();
    var quantity = parseInt($("#quantity").html());
    var price = parseInt($("#price").html().split(" ")[1]);

    quantity -= 1;
    quantity = quantity > 0 ? quantity : 1;
    $("#quantity").html(quantity > 9 ? quantity : "0" + quantity);
    $("#totalPrice").html("Rs. " + quantity * price);
  });

  $("#addToCart").click(function (e) {
    let url = new URL(url_str);
    let search_params = url.searchParams;
    console.log("jvb");
    console.log($("#oned").val());

    let id = search_params.get("id");
    e.preventDefault();
    $("#vtf").toggleClass("modal-active ");
    $(".modal").show();
    // // $(".left").children().each();
    var dish_id = id;
    var quantity = parseInt($("#quantity").html());
    console.log(quantity);
    var price = parseInt($("#totalPrice").html().split(" ")[1]);
    console.log(price);

    $.each($(".ingredient"), function (index, value) {
      var id = parseInt($(this).attr("data-id"));

      var amount = parseFloat(
        $(this)
          .children(".container")
          .children(".quantity")
          .text()
          .split(" ")[0]
      );
      ingredientDetailsArray.push({ id: id, quantity: amount });
    });

    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/Server_war_exploded/cart",
      headers: {
        authorization: authHeader,
      },

      data: JSON.stringify({
        id: dish_id,
        quantity: quantity,
        price: price,
        customer: customer,
        ingredients: ingredientDetailsArray,
      }),
      contentType: "application/json; charset=utf-8",

      success: function (response) {
        console.log("yatta");
        window.location = "customer-orders.html";
      },
      failure: function (response) {
        console.log("fail");
        alert(response.d);
      },
    });
  });

  // $.ajax({
  //   type: "POST",
  //   url: "http://localhost:8080/Server_war_exploded/cart",
  //   headers: {
  //     authorization: authHeader,
  //   },

  //   data: JSON.stringify({
  //     dish_id: dish_id,
  //     quantity: quantity,
  //     price: price,
  //   }),
  //   contentType: "application/json; charset=utf-8",
  //   dataType: "json",

  //   success: function (response) {
  //     if (response.d == true) {
  //       alert("You will now be redirected.");
  //       window.location = "//www.aspsnippets.com/";
  //     }
  //   },
  //   failure: function (response) {
  //     alert(response.d);
  //   },
  // });
});
