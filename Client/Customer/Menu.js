class Dish {
  constructor(params) {
    this.id = params.id;
    this.name = params.name;
    this.image = params.image;
    this.price = params.price;
    this.gy = params.getd;
  }
  addDish() {
    var dishes = $("#dishes");
    var card = $(document.createElement("div")).addClass("card");
    var content = $(document.createElement("div")).addClass(
      "flex flex-space-evenly"
    );
    var price = $(document.createElement("h1"))
      .html("Rs. " + this.price)
      .addClass("text-2");
    var name = $(document.createElement("h1"))
      .html(this.name)
      .addClass("m-1 text-upper");
    var line = $(document.createElement("hr")).addClass("mb-2 mt-2");

    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("round-2");
    var button = $(document.createElement("a"))
      .addClass("btn fw-b")
      .attr("href", "customer-dish.html?id=" + this.id)
      .html("+ Order Now");

    card.append(image);
    card.append(name);

    content.append(price);
    content.append(button);
    card.append(line);
    card.append(content);
    dishes.prepend(card);
  }
}
