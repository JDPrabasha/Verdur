$(window).on("load", function () {
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var rider = parseInt(window.localStorage.getItem("rider"));
  var photo = window.localStorage.getItem("photo");
  var name = window.localStorage.getItem("name");
  var deliveryCount = 0;
  var current = 0;
  var delarray = [];

  $("#photo").attr("src", photo);
  $("#name").html(name);
  $("#firstName").html(name.split(" ")[0]);
  $("#riderId").html(rider);
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/delivery/" + rider,
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    var array = $.parseJSON(data);
    delarray = array;
    console.log(array);
    deliveryCount = array.length;

    if (deliveryCount < 1) {
      $("#content").html("No deliveries");
    } else {
      const deSerializedData = array.map(DeliverySerializer.deSerialize);
      console.log(deSerializedData);
      curobj = deSerializedData;
      const deserializedCustomer = ProfileSerializer.deSerialize(
        deSerializedData[current].customer
      );
      const deserializedOrder = OrderSerializer.deSerialize(
        deSerializedData[current].order
      );

      new Order(deserializedOrder).addRiderOrder();

      getReverseGeocodingData(deserializedOrder.lat, deserializedOrder.lang);

      console.log(deserializedCustomer);

      new Profile(deserializedCustomer).addCustomerDetails();

      var myArray = $(".dish");
      $(myArray[0]).addClass("selected");
      $("#main").attr("src", $(myArray[0]).attr("src"));
    }

    // console.log(myArray);
    $(".dish").click(function () {
      $("#main").attr("src", $(this).attr("src"));
      $.each($(".dish"), function (index, value) {
        $(this).removeClass("selected");
      });
      $(this).addClass("selected");
    });

    $("#next").click(function () {
      $("#dishes").empty();
      var deSerializedData = delarray;
      current = current + 1 == deliveryCount ? 0 : current + 1;
      const deserializedCustomer = ProfileSerializer.deSerialize(
        deSerializedData[current].customer
      );
      const deserializedOrder = OrderSerializer.deSerialize(
        deSerializedData[current].order
      );

      new Order(deserializedOrder).addRiderOrder();

      getReverseGeocodingData(deserializedOrder.lat, deserializedOrder.lang);

      console.log(deserializedCustomer);

      new Profile(deserializedCustomer).addCustomerDetails();

      var myArray = $(".dish");
      $(myArray[0]).addClass("selected");
      $("#main").attr("src", $(myArray[0]).attr("src"));
      console.log("hyhv");
    });

    $("#confirm").click(function () {
      var order = $("#active-order").html();
      var method = $("#method").html();
      $.ajax({
        type: "PUT",
        url:
          "http://localhost:8080/Server_war_exploded/delivery/confirm?order=" +
          order +
          "&method=" +
          method,
        headers: {
          authorization: authHeader,
        },

        contentType: "application/json; charset=utf-8",

        success: function () {
          console.log("pass");
        },
        failure: function () {
          alert("fail");
        },
      });
    });

    function getReverseGeocodingData(lat, lng) {
      var latlng = new google.maps.LatLng(lat, lng);
      // This is making the Geocode request
      var geocoder = new google.maps.Geocoder();
      geocoder.geocode({ latLng: latlng }, function (results, status) {
        if (status !== google.maps.GeocoderStatus.OK) {
          alert(status);
        }
        // This is checking to see if the Geoeode Status is OK before proceeding
        if (status == google.maps.GeocoderStatus.OK) {
          var address = results[0].formatted_address;
          console.log(address);
          $("#location").html(address);
        }
        return 0;
      });
    }

    // const deSerializedData = DishSerializer.deSerialize(dish);
    // new Dish(deSerializedData).addDishDetails();
    // var array = deSerializedData.ingredients;
    // console.log(array);
    // const deSerializedIngredients = array.map(IngredientSerializer.deSerialize);
    // deSerializedIngredients.map((params) =>
    //   new Ingredient(params).addAllIngredients()
    // );
    // deSerializedData.map((params) => new Dish(params).addDish());

    // const deSerializedData = array.map(CartSerializer.deSerialize);
    // console.log(deSerializedData);
    // console.log("hyu");
    // deSerializedData.map((params) => new Dish(params).addCartItem());
  });
});