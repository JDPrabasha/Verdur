class Order {
  constructor(params) {
    this.id = params.id;
    this.status = params.status;
    this.quantity = params.quantity;
    this.price = params.price;
    this.timestamp = params.timestamp;
    this.dishes = params.dishes;
    this.carbs = params.carbs;
    this.protein = params.protein;
    this.calories = params.calories;
    this.fats = params.fats;
    this.lat = params.lat;
    this.lang = params.lang;
    this.method = params.method;
  }

  addOrder() {
    console.log(this.id);
    $("#active-order").html(this.id);
    var suffix = this.dishes.length > 1 ? " ITEMS" : " ITEM";
    $("#active-quantity").html(this.dishes.length + suffix);
    $("#active-image").attr("src", this.dishes[0].image);

    $("#active-status").html(
      this.status.charAt(0).toUpperCase() + this.status.slice(1)
    );
  }

  showPendingOrder() {
    const deSerializedData = this.dishes.map(DishSerializer.deSerialize);
    console.log(deSerializedData);

    deSerializedData.map((params) => new Dish(params).addPendingDish());
  }

  showDeliveredOrder() {
    var title = $(document.createElement("h2")).html("Review Order");
    $("#modalContent").append(title);
    var complaint = $(document.createElement("textarea")).attr(
      "placeholder",
      "Please let us know about any issues you had with your order "
    );
    $("#modalContent").append(title);
    console.log(this.dishes);
    const deSerializedData = this.dishes.map(DishSerializer.deSerialize);
    console.log(deSerializedData);

    deSerializedData.map((params) => new Dish(params).addReviewDish());
    var button = $(document.createElement("div"))
      .addClass("btn fw-b text-center")
      .attr("id", "completeReview")
      .html("Complete Review");
    $("#modalContent").append(complaint);
    $("#modalContent").append(button);
  }

  addRiderOrder() {
    $("#active-order").html(this.id);
    $("#price").html(this.price);
    $("#method").html(
      this.method.charAt(0).toUpperCase() + this.method.slice(1)
    );
    console.log(this.dishes);
    const deSerializedData = this.dishes.map(MenuSerializer.deSerialize);
    console.log(deSerializedData);
    // console.log(this.lat);
    // console.log(this.lang);
    // initMap(this.lat, this.lang);

    // new Dish(deSerializedData[0]).addOrderDish();
    deSerializedData.map((params) => new Dish(params).addOrderDish());
    console.log("dyf");
    $("#totalItems").html(this.dishes.length);
    // deSerializedData.map((params) => new Dish(params).addDish());
  }

  addPastOrder() {
    var orders = $("#orders");
    var order = $(document.createElement("tr"));
    var content = $(document.createElement("div")).addClass("avatars");
    var dishes = $(document.createElement("td"));
    console.log(this.id);

    var len = this.dishes.length >= 3 ? 3 : this.dishes.length;

    for (let i = 0; i < len; i++) {
      const element = this.dishes[i];

      var image = $(document.createElement("img"))
        .attr("src", element.image)
        .addClass("avatar");
      console.log(this.id);
      console.log(element.image);
      content.append(image);
    }

    var extra = this.dishes.length - 3;
    extra > 1 ? content.append("+ " + extra) : "";
    dishes.append(content);

    var date = $(document.createElement("td")).html(
      [
        this.timestamp.split(" ")[0].split("-")[2],
        this.timestamp.split(" ")[0].split("-")[1],
        this.timestamp.split(" ")[0].split("-")[0].slice(-2),
      ].join(".")
    );
    var id = $(document.createElement("td")).html("ORDER " + this.id);
    var time = $(document.createElement("td")).html(
      this.timestamp.split(" ")[1].split(":")[0] +
        "." +
        this.timestamp.split(" ")[1].split(":")[1] +
        " AM"
    );
    var calories = $(document.createElement("td")).html(this.calories + " cal");
    var protein = $(document.createElement("td")).html(this.protein + " g");
    var carbs = $(document.createElement("td")).html(this.carbs + " g");
    var fat = $(document.createElement("td")).html(this.fats + " g");
    var price = $(document.createElement("td")).html(this.price);
    var button = $(document.createElement("td"));
    var buttonElement = $(document.createElement("div"))
      .addClass("btn cart")
      .attr("data-id", this.id);
    var cart = $(document.createElement("span"))
      .addClass("material-icons-outlined")
      .html("shopping_bag");

    buttonElement.append(cart);
    button.append(buttonElement);
    order.append(id);
    order.append(dishes);
    order.append(date);
    order.append(time);

    order.append(calories);
    order.append(protein);
    order.append(carbs);
    order.append(fat);
    order.append(price);
    order.append(button);
    orders.append(order);
    // var content = $(document.createElement("div")).addClass(
    //   "flex flex-space-evenly"
    // );
    // var price = $(document.createElement("h1"))
    //   .html("Rs. " + this.price)
    //   .addClass("text-2");
    // var name = $(document.createElement("h1"))
    //   .html(this.name)
    //   .addClass("m-1 text-upper");
    // var line = $(document.createElement("hr")).addClass("mb-2 mt-2");
    //   <tr>
    //   <td>ORDER 2206</td>
    //   <td>
    //     <div class="avatars">
    //       <span class="avatar">
    //         <img
    //           src="https://media.healthyfood.com/wp-content/uploads/2021/06/Baked-Salmon-Cauliflower-Rice.gif"
    //         />
    //       </span>
    //       <span class="avatar">
    //         <img
    //           src="https://static.onecms.io/wp-content/uploads/sites/9/2019/09/Hot-Cranberry-Juice-Fall-Cocktail-FT-Blog0919.jpg"
    //         />
    //       </span>
    //       <span class="avatar">
    //         <img
    //           src="https://images.unsplash.com/photo-1574672280600-4accfa5b6f98?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"
    //         />
    //       </span>
    //     </div>
    //   </td>
    //   <td>12.09.21</td>
    //   <td>05.20 PM</td>
    //   <td>100 kcal</td>
    //   <td>10 g</td>
    //   <td>10 g</td>
    //   <td>10 g</td>
    //   <td>1000</td>
    //   <td>
    //     <div class="btn">
    //       <span class="material-icons-outlined"> shopping_bag </span>
    //     </div>
    //   </td>
    // </tr>
  }
}
