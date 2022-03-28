class Ingredient {
  constructor(params) {
    this.id = params.id;
    this.name = params.name;
    this.image = params.image;
    this.quantity = params.quantity;
    this.unit = params.unit;
    this.calphg = params.caloriesphg;
    this.carbsphg = params.carbsphg;
    this.type = params.type;
    this.proteinphg = params.proteinphg;
    this.fatsphg = params.fatsphg;
    this.unitWeight = params.weightperunit;
    this.priceperunit = params.priceperunit;
    this.expandable = params.expandable;
    this.minimum = params.minimum;
    this.maximum = params.maximum;
    this.benefits = params.benefits;
    this.suppliers = params.suppliers;
    this.description = params.description;
  }

  addAllIngredients() {
    var ingredients = $("#ingredients");

    var ingredient = $(document.createElement("div")).addClass(
      "ingredient m-1 flex-space-between mb-4"
    );

    console.log(this.id);

    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("round-1 icon-3");

    var name = $(document.createElement("p"))
      .html(this.name)
      .addClass("flex-grow-2 ml-45");

    var quantity = $(document.createElement("p"))
      .html(
        this.unit > 1
          ? this.quantity + " " + this.unit
          : this.quantity + " " + this.unit + "s"
      )
      .addClass("ml-auto")
      .attr("align", "left");

    ingredient.append(image);
    ingredient.append(name);
    ingredient.append(quantity);
    if (this.expandable) {
      var link = $(document.createElement("a")).attr(
        "href",
        "customer-ingredient.html?id=" + this.id
      );
      link.append(ingredient);
      ingredients.append(link);
    } else {
      ingredients.append(ingredient);
    }
  }

  calculateTotals() {}

  addIngredientInformation() {
    $("#name").html(this.name);
    $("#image").attr("src", this.image);
    $("#description").html(this.description);

    $("#benefit1").html(this.benefits[0]);
    $("#benefit1").html(this.benefits[1]);
    $("#benefit1").html(this.benefits[2]);
  }

  addClickers() {
    $.each($(".plus"), function (index, value) {
      // console.log($(this).siblings(".quantity").html());
    });
  }

  addCustomizableIngredients() {
    console.log("this.protein " + this.name);
    console.log(this.proteinphg);
    console.log(this.unitWeight);
    console.log(this.quantity);

    $("#protein").html(
      parseFloat($("#protein").html()) +
        (this.proteinphg * this.unitWeight * this.quantity) / 100
    );

    console.log("this.carbs " + this.name);
    console.log(this.carbsphg);
    console.log(this.unitWeight);
    console.log(this.quantity);

    $("#carbs").html(
      (
        parseFloat($("#carbs").html()) +
        (this.carbsphg * this.unitWeight * this.quantity) / 100
      ).toFixed(2)
    );

    $("#fats").html(
      (
        parseFloat($("#fats").html()) +
        (this.fatsphg * this.unitWeight * this.quantity) / 100
      ).toFixed(2)
    );

    $("#calories").html(
      (
        parseFloat($("#calories").html()) +
        (this.calphg * this.unitWeight * this.quantity) / 100
      ).toFixed(2)
    );

    // console.log(this.calphg);

    var ingredient = $(document.createElement("div"))
      .addClass("ingredient mr-3 flex-space-between mb-4")
      .attr("data-id", this.id);

    var name = $(document.createElement("p"))
      .html(this.name)
      .addClass("fw-b name");

    var unit = $(document.createElement("p")).html(this.unit);

    var details = $(document.createElement("div")).addClass(
      "flex-column text-center flex-2 details"
    );

    details.append(name);
    details.append(unit);

    var image = $(document.createElement("img"))
      .attr("src", this.image)
      .addClass("round-1 icon-4 flex-1");

    console.log(this.image);

    console.log(this.type);

    if (this.type == "toggle") {
      var toggle = $(document.createElement("div")).addClass(
        "flex-space-around  flex-2 container"
      );
      var minus = $(document.createElement("span"))
        .addClass("material-icons-outlined icon bg-success round-1 p-1")
        .html("remove");

      minus.attr("data-protein", this.proteinphg);
      minus.attr("data-carbs", this.carbsphg);
      minus.attr("data-fats", this.fatsphg);
      minus.attr("data-calories", this.calphg);
      minus.attr("data-minimum", this.minimum);
      minus.attr("data-price", this.priceperunit);
      minus.attr("data-default", this.quantity);
      minus.attr("data-weight", this.unitWeight);

      $(minus).click(function (e) {
        console.log($(this).attr("data-default"));
        e.preventDefault();

        var initialQuantity = parseInt($(this).siblings(".quantity").html());
        var defaultQuantity = parseInt($(this).attr("data-default"));
        var quantity =
          initialQuantity == $(this).attr("data-minimum")
            ? initialQuantity
            : initialQuantity - 1;
        if (initialQuantity != quantity) {
          $(this)
            .siblings(".quantity")
            .html(quantity > 9 ? quantity : "0" + quantity);
          var preQuantity = quantity;
          quantity -= defaultQuantity;

          if (quantity >= 0) {
            $(this)
              .parent()
              .siblings(".price")
              .html("+Rs. " + quantity * $(this).attr("data-price"));

            $("#increase").html(
              parseInt($("#increase").html()) -
                parseInt($(this).attr("data-price"))
            );

            $("#price").html(
              "Rs. " +
                parseInt(
                  parseInt($("#price").html().split(" ")[1]) -
                    parseInt($(this).attr("data-price"))
                )
            );

            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".protein")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-protein") *
                    $(this).attr("data-weight")) /
                    100 +
                  "g"
              );
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".carbs")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-carbs") *
                    $(this).attr("data-weight")) /
                    100 +
                  "g"
              );
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".fats")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-fats") *
                    $(this).attr("data-weight")) /
                    100 +
                  "g"
              );
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".calories")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-calories") *
                    $(this).attr("data-weight")) /
                    100 +
                  "kcal"
              );
          }

          $("#protein").html(
            (
              parseFloat($("#protein").html()) -
              parseFloat(
                (1 *
                  $(this).attr("data-protein") *
                  $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );

          $("#carbs").html(
            (
              parseFloat($("#carbs").html()) -
              parseFloat(
                (1 * $(this).attr("data-carbs") * $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
          $("#fats").html(
            (
              parseFloat($("#fats").html()) -
              parseFloat(
                (1 * $(this).attr("data-fats") * $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
          $("#calories").html(
            (
              parseFloat($("#fats").html()) -
              parseFloat(
                (1 * $(this).attr("data-fats") * $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
        }
      });

      var defaultQuantity = $(document.createElement("p"))
        .addClass("text-1 fw-b pl-5 pr-5 quantity")
        .html(this.quantity > 9 ? this.quantity : "0" + this.quantity);

      var plus = $(document.createElement("span"))
        .addClass("material-icons-outlined icon bg-success round-1 p-1 plus")
        .html("add");
      plus.attr("data-protein", this.proteinphg);
      plus.attr("data-carbs", this.carbsphg);
      plus.attr("data-fats", this.fatsphg);
      plus.attr("data-calories", this.calphg);
      plus.attr("data-maximum", this.maximum);
      plus.attr("data-price", this.priceperunit);
      plus.attr("data-default", this.quantity);
      plus.attr("data-weight", this.unitWeight);

      $(plus).click(function (e) {
        e.preventDefault();
        var initialQuantity = parseInt($(this).siblings(".quantity").html());
        var defaultQuantity = parseInt($(this).attr("data-default"));
        var quantity =
          initialQuantity == $(this).attr("data-maximum")
            ? initialQuantity
            : initialQuantity + 1;

        if (initialQuantity != quantity) {
          $(this)
            .siblings(".quantity")
            .html(quantity > 9 ? quantity : "0" + quantity);

          console.log(quantity);
          console.log(defaultQuantity);
          var preQuantity = quantity;
          quantity -= defaultQuantity;
          console.log(quantity);

          if (quantity > 0) {
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".protein")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-protein") *
                    $(this).attr("data-weight")) /
                    100 +
                  "g"
              );
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".carbs")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-carbs") *
                    $(this).attr("data-weight")) /
                    100 +
                  "g"
              );
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".fats")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-fats") *
                    $(this).attr("data-weight")) /
                    100 +
                  "g"
              );
            $(this)
              .parent()
              .siblings(".nutrients")
              .children(".calories")
              .html(
                "+" +
                  (quantity *
                    $(this).attr("data-calories") *
                    $(this).attr("data-weight")) /
                    100 +
                  "kcal"
              );

            $(this)
              .parent()
              .siblings(".price")
              .html("+Rs. " + quantity * $(this).attr("data-price"));

            $("#increase").html(
              parseInt($("#increase").html()) +
                parseInt($(this).attr("data-price"))
            );

            $("#price").html(
              "Rs. " +
                parseInt(
                  parseInt($("#price").html().split(" ")[1]) +
                    parseInt($(this).attr("data-price"))
                )
            );
          }

          $("#protein").html(
            (
              parseFloat($("#protein").html()) +
              parseFloat(
                (1 *
                  $(this).attr("data-protein") *
                  $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
          $("#carbs").html(
            (
              parseFloat($("#carbs").html()) +
              parseFloat(
                (1 * $(this).attr("data-carbs") * $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
          $("#fats").html(
            (
              parseFloat($("#fats").html()) +
              parseFloat(
                (1 * $(this).attr("data-fats") * $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
          $("#calories").html(
            (
              parseFloat($("#calories").html()) +
              parseFloat(
                (1 *
                  $(this).attr("data-calories") *
                  $(this).attr("data-weight")) /
                  100
              )
            ).toFixed(2)
          );
        }
      });

      toggle.append(minus);
      toggle.append(defaultQuantity);
      toggle.append(plus);

      ingredient.append(toggle);
    } else {
      var container = $(document.createElement("div")).addClass(
        "flex-column flex-2 text-center fw-b container"
      );

      var min = $(document.createElement("p")).html(
        this.minimum + " " + this.unit + "s"
      );
      var max = $(document.createElement("p")).html(
        this.maximum + " " + this.unit + "s"
      );
      var current = $(document.createElement("p"))
        .html(this.quantity + " " + this.unit + "s")
        .addClass("ml-6 quantity");
      var minmax = $(document.createElement("div")).addClass(
        "flex-space-between"
      );

      var slider = $(document.createElement("input"))
        .attr({
          min: this.minimum,
          max: this.maximum,
          value: this.quantity,
          unit: this.unit,
          type: "range",
          step: 0.5,
        })
        .attr("id", "oned");

      $(slider).on("propertychange input", function (e) {
        console.log(this.value);
        current.html(this.value + " " + $(this).attr("unit") + "s");
      });

      minmax.append(min);
      minmax.append(max);
      container.append(minmax);

      container.append(slider);
      container.append(current);

      ingredient.append(container);
    }
    var ingredients = $("#ingredients");

    var nutrients = $(document.createElement("div")).addClass(
      "flex-1 flex-column nutrients"
    );

    var zero = $(document.createElement("p"))
      .html("+ 0kcal")
      .addClass("fw-b calories");
    var one = $(document.createElement("p"))
      .html("+ 0g")
      .addClass("fw-b protein");
    var two = $(document.createElement("p"))
      .html("+ 0g")
      .addClass("fw-b carbs");
    var three = $(document.createElement("p"))
      .html("+ 0g")
      .addClass("fw-b fats");
    var defaultPrice = $(document.createElement("p"))
      .html("+ Rs. 0")
      .addClass("fw-b text-info flex-1 price");
    var line = $(document.createElement("hr")).addClass("m-4");

    nutrients.append(zero);

    nutrients.append(one);
    nutrients.append(two);
    nutrients.append(three);

    ingredient.prepend(image);
    ingredient.prepend(details);
    ingredient.append(nutrients);
    ingredient.append(defaultPrice);

    ingredients.append(ingredient);
    ingredients.append(line);
  }
}
