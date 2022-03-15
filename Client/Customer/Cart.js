class Dish {
  constructor(params) {
    this.id = params.id;
    this.name = params.name;
    this.image = params.image;
    this.quantity = params.quantity;
    this.price = params.price;
  }

  //   <div class="flex-space-between mr-12 mb-3">
  //   <img src="https://www.thedailymeal.com/sites/default/files/recipe/2021/Sweet_sticky_chicken_thigh-min.jpg" class="icon-4 mt-3" alt="" />
  //   <div class="order-specs">
  //     <p class="text-2">Sesame Chicken</p>
  //     <p class="text-upper">Quantity : 1</p>
  //   </div>

  //   <p class="mt-3">
  //     <span class="material-icons-outlined"> remove_red_eye </span>
  //     <a class="material-icons-outlined ml-2" href="customer-customizedish.html"> mode_edit </a>
  //     <span class="material-icons-outlined ml-2 text-red"> delete </span>
  //   </p>
  //   <p class="fw-b text-2">Rs. 1,230</p>
  // </div>
  // <hr />

  addCartItem() {
    console.log(this.id);
    var cartItems = $("#cart-items");

    var item = $(document.createElement("div"))
      .addClass("flex-space-between mr-12 mb-3 item")
      .attr("data-id", this.id);

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
    var view = $(document.createElement("span"))
      .addClass("material-icons-outlined")
      .html("remove_red_eye");
    var edit = $(document.createElement("a"))
      .addClass("material-icons-outlined ml-2")
      .attr("href", "customer-customizedish.html?edit=true&id=" + this.id)
      .html("mode_edit");

    var remove = $(document.createElement("span"))
      .addClass("material-icons-outlined ml-2 text-red remove")
      .html("delete")
      .attr("data-id", this.id);

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
