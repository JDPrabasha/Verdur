class Dish {
  constructor(params) {
    this.id = params.id;
    this.cid = params.cid;
    this.name = params.name;
    this.image = params.image;
    this.price = params.price;
    this.quantity = params.quantity;
    this.description = params.description;
    this.time = params.time;
    this.rating = params.rating;
    this.ingredients = params.ingredients;
  }

  addOrderDish() {
    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("icon-2 dish");
    var dishes = $("#dishes");

    dishes.append(image);
  }

  addPendingDish() {
    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("icon-5");

    var name = $(document.createElement("p")).html(this.name).addClass("fw-b");
    var quantity = $(document.createElement("p"))
      .html(this.quantity)
      .addClass("fw-b");
    console.log("vh");
    var content = $("#modalContent");
    content.append(image);
    content.append(name);
    content.append(quantity);
  }

  addReviewDish() {
    $("#ratingContent").attr("data-dish", this.id);
    var dish = $(document.createElement("div")).addClass(
      "flex-space-between cy"
    );

    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("icon-5");
    var name = $(document.createElement("p")).html(this.name).addClass("fw-b");

    var stars = $(document.createElement("div"));
    for (let index = 0; index < 5; index++) {
      stars.append(
        $(document.createElement("span"))
          .addClass("material-icons-outlined")
          .html("star_outline")
          .attr("data-number", index)
      );
    }

    var childrenArray = $(stars).children().toArray();

    for (var i = 0, len = childrenArray.length; i < len; i++) {
      childrenArray[i].onclick = function () {
        var rating = $(this).attr("data-number");
        for (let index = 0; index <= 4; index++) {
          $(childrenArray[index]).html("star_outline");
        }
        for (let index = 0; index <= rating; index++) {
          $(childrenArray[index]).html("star");
        }
        $("#ratingContent").attr("data-rating", parseInt(rating) + 1);
      };
    }

    var rate = $(document.createElement("p"))
      .html("RATE")
      .addClass("fw-b")
      .attr("id", "rate");
    var skip = $(document.createElement("p")).html("SKIP").addClass("fw-b");

    // var label = $(document.createElement("label"))
    //   .attr("for", "quantity")
    //   .html("Add to Today's Intake");
    // var intake = $(document.createElement("input"))
    //   .attr({
    //     min: 0,
    //     max: this.quantity,

    //     type: "number",
    //   })
    //   .addClass("width-1");
    // var add = $(document.createElement("div"))
    //   .addClass("btn fw-b")
    //   .attr("id", "completeReview")
    //   .html("Add");
    // var quantity = $(document.createElement("p"))
    //   .html(this.quantity)
    //   .addClass("fw-b");
    // console.log("vh");
    var content = $("#ratingContent");

    dish.append(image);
    dish.append(name);

    // dish.append(add);

    dish.append(stars);
    dish.append(rate);
    dish.append(skip);
    content.append(dish);
    $(rate).click(function (e) {
      e.preventDefault();
      // console.log("kbjdb");
    });
  }

  addDishDetails() {
    $("#name").html(this.name);
    $("#image").attr("src", this.image);
    $("#description").html(this.description);
    $("#time").html(this.time);
    $("#price").html("Rs. " + this.price);
    $("#custimizeButton").attr(
      "href",
      "customer-customizedish.html?id=" + this.id
    );
  }

  addDishToCart() {
    var cartItems = $("#cart-items");
    var item = $(document.createElement("div")).addClass(
      "flex-space-between mr-12 mb-3 item"
    );
    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("round-1 icon-4 flex-1 mt-3");
    var specs = $(document.createElement("div"));
    var name = $(document.createElement("p")).html(this.name).addClass("fw-b");
    var quantity = $(document.createElement("p"))
      .html(this.name)
      .addClass("fw-b");

    specs.append(name);
    specs.append(quantity);

    var buttons = $(document.createElement("p"))
      .html(this.name)
      .addClass("fw-b");

    var view = $(document.createElement("span"))
      .addClass("material-icons-outlined icon bg-success round-1 p-1")
      .html("remove");
    var edit = $(document.createElement("span"))
      .addClass("material-icons-outlined icon bg-success round-1 p-1")
      .html("remove");
    var remove = $(document.createElement("span"))
      .addClass("material-icons-outlined icon bg-success round-1 p-1")
      .html("remove");

    buttons.append(view);
    buttons.append(edit);
    buttons.append(remove);

    var price = $(document.createElement("p"))
      .html(this.price)
      .addClass("fw-b");

    var line = $(document.createElement("hr")).addClass("m-4");

    item.append(image);
    item.append(specs);
    item.append(buttons);
    item.append(price);

    cartItems.append(item);
    cartItems.append(line);
  }

  addDish() {
    var dishes = $("#dishes");
    var card = $(document.createElement("div")).addClass("card");

    var name = $(document.createElement("h1"))
      .html(this.name)
      .addClass("m-1 text-upper");

    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("round-2");
    var button = $(document.createElement("a"))
      .addClass("btn fw-b")
      .attr("href", "customer-dish.html?id=" + this.id)
      .html("+ Order Now");

    card.append(image);
    card.append(name);

    dishes.prepend(card);
  }

  addCartItem() {
    console.log(this.cid);
    var cartItems = $("#cart-items");

    var item = $(document.createElement("div"))
      .addClass("flex-space-between mr-12 mb-3 item")
      .attr("data-id", this.cid);

    $(".total").html(parseFloat($(".total").html()) + this.price);

    var name = $(document.createElement("p"))
      .html(this.name)
      .addClass("text-2");

    var quantity = $(document.createElement("p"))
      .html("Quantity: " + this.quantity)
      .addClass("text-upper");

    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("icon-4 mt-3");

    item.append(image);
    var details = $(document.createElement("div")).addClass("details flex-5");

    details.append(name);
    details.append(quantity);

    item.append(details);

    var buttons = $(document.createElement("p")).addClass("mt-3");
    var view = $(document.createElement("a"))
      .addClass("material-icons-outlined")
      .html("remove_red_eye")
      .attr("href", "customer-dish.html?id=" + this.id);
    var edit = $(document.createElement("a"))
      .addClass("material-icons-outlined ml-2")
      .attr(
        "href",
        "customer-customizedish.html?edit=true&id=" +
          this.id +
          "&customID=" +
          this.cid
      )
      .html("mode_edit");

    var remove = $(document.createElement("span"))
      .addClass("material-icons-outlined ml-2 text-red remove")
      .html("delete")
      .attr("data-id", this.cid);

    buttons.append(view);
    buttons.append(edit);
    buttons.append(remove);

    item.append(buttons);

    var price = $(document.createElement("p"))
      .html("Rs. " + this.price)
      .addClass("fw-b text-2");

    item.append(price);

    cartItems.append(item);

    var line = $(document.createElement("hr")).addClass("m-4");

    cartItems.append(line);
  }
}
