$(window).on("load", function () {
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var customer = parseInt(window.localStorage.getItem("customer"));
  $("#avatar").attr("src", window.localStorage.getItem("avatar"));
  $("#customer").html(window.localStorage.getItem("name"));
  var fees = [];
  var payment = "";
  var array = [];
  var names = [];
  var order = {};
  var deserializedOrder = {};

  $.ajax({
    url: "http://localhost:8080/Server_war_exploded/order/" + customer,
    beforeSend: function (xhr) {
      xhr.setRequestHeader("authorization", authHeader);
    },
  }).then(function (data) {
    order = $.parseJSON(data);
    console.log(order);

    deserializedOrder = OrderSerializer.deSerialize(order);
    new Order(deserializedOrder).addOrder();

    if (order) {
      $("#orders").removeClass("transparent");
    }
  });

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/delivery/fees",
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    array = $.parseJSON(data);
    console.log(array);
    fees = array;
  });

  $("#orders").click(function (e) {
    e.preventDefault();
    console.log("jbjb");
    $("#orderInfo").toggleClass("modal-active ");
    $(".modal").show();
    console.log($("#active-status").html());
    var status = $("#active-status").html();
    var content = $("#modalContent");
    // $("#modalContent").html("");

    switch (status) {
      case "Pending":
        updateProgressBar(document.getElementById("myProgressBar"), 0);
        $("#orderInfo").html("PLease wait while we process your order");
        break;

      case "Accepted":
        updateProgressBar(document.getElementById("myProgressBar"), 33);
        getTotalTime();
        break;

      case "Rejected":
        new Order(deserializedOrder).showRejectedOrder();
        break;

      case "Delivering":
        updateProgressBar(document.getElementById("myProgressBar"), 66);
        getRiderDetails();

        break;

      case "Delivered":
        updateProgressBar(document.getElementById("myProgressBar"), 100);
        getPaymentDetails();
        break;

      default:
        break;
    }
  });

  function getTotalTime() {}

  function getRiderDetails() {}

  function getPaymentDetails() {}

  function checkDistance(distance) {
    for (i = 0; i < fees.length; i++) {
      if (distance == fees[i].distance) {
        return fees[i].fee;
      }
    }
  }

  function getDeliveryData(distance) {
    for (i = 0; i < fees.length; i++) {
      if (distance == fees[i].distance) {
        return fees[i].fee;
      }
    }
  }

  function getOrderSummary() {
    for (i = 0; i < fees.length; i++) {
      if (distance == fees[i].distance) {
        return fees[i].fee;
      }
    }
  }

  function reviewOrder() {
    const deSerializedData = OrderSerializer.deSerialize(order);
    new Order(deSerializedData).showDelivering();
  }

  function confirmReview() {
    for (i = 0; i < fees.length; i++) {
      if (distance == fees[i].distance) {
        return fees[i].fee;
      }
    }
  }

  function validatePayment() {
    if (payment != "") {
      console.log("issokay meth");
      return true;
    } else {
      $("#methoderror").removeClass("hidden");
      $("#methoderror").html("Please choose a payment method");
    }
  }

  function validateLocation() {
    var flag = $("#distance").attr("data-flag");
    if (flag == "-1") {
      $("#locationerror").removeClass("hidden");
      $("#locationerror").html("Please choose a location");
      return false;
    } else if (flag == 0) {
      $("#locationerror").removeClass("hidden");
      $("#locationerror").html("Location out of range");
      return false;
    } else {
      console.log("issokay loc");
      return true;
    }
  }

  function validateCart(input) {
    if (input > 0) {
      console.log("issokay count");
      return true;
    } else {
      $("#priceerror").removeClass("hidden");
      $("#priceerror").html("Please add desired items to cart");
    }
  }

  function updateProgressBar(progressBar, value) {
    const progressFill = progressBar.querySelector(".progress-fill");
    const progressText = progressBar.querySelector(".progress-text");
    const stages = progressText.dataset.stages
      .split(",")
      .map((stage) => stage.split(":"));

    stages.sort((stageA, stageB) => {
      return stageB[0] - stageA[0];
    });

    console.log(stages);

    const activeStage = stages.find((stage) => {
      return value >= stage[0];
    });

    progressFill.style.width = `${value}%`;
    progressText.textContent = `${activeStage[1]}`;
  }

  updateProgressBar(document.getElementById("myProgressBar"), 100);

  function updateOrders() {
    $.ajax({
      url: "http://localhost:8080/Server_war_exploded/order/" + customer,
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      newarray = $.parseJSON(data);
      console.log(array);
      if (newarray !== array) {
        array = newarray;
        length = array.length;
        counter = length;
        const deSerializedData = array.map(OrderSerializer.deSerialize);
        // console.log(deSerializedData);
        deSerializedData.map((params) => new Order(params).addOrder());
      }
    });
  }

  window.setInterval(function () {
    updateOrders;
  }, 1000);

  function getOrderData(status) {
    $.ajax({
      url:
        "http://localhost:8080/Server_war_exploded/order/details/status=" +
        status +
        "&customer=" +
        customer,
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      newarray = $.parseJSON(data);
      console.log(array);
      switch (status) {
        case "Accepted":
          console.log("Oranges are $0.59 a pound.");
          break;
        case "Delivering":
          console.log("Oranges are $0.59 a pound.");
          break;
        case "Completed":
          console.log("Mangoes and papayas are $2.79 a pound.");

          break;
        default:
          console.log(`Sorry, we are out of ${expr}.`);
          break;
      }
    });
  }

  $(".modal-close").click(function (e) {
    e.preventDefault();
    $("#vtf").removeClass("modal-active ");
    $(".modal").hide();
    var distance = $("#distance").html().split(" ")[0];
    console.log(distance);

    $("#delivery").html("Rs. " + checkDistance(Math.ceil(distance)));
    $("#price").html(
      "Rs. " +
        (parseInt($(".total").html()) +
          parseInt($("#delivery").html().split(" ")[1]))
    );
  });

  $("#cash").click(function (e) {
    e.preventDefault();
    $("#cash").toggleClass("selected");
    $("#card").removeClass("selected");
    payment = "cash";
  });
  $("#card").click(function (e) {
    e.preventDefault();
    $("#card").toggleClass("selected");
    $("#cash").removeClass("selected");
    payment = "card";
  });

  $("#location").click(function (e) {
    e.preventDefault();
    $("#map").load("maps.html");
    $("#vtf").toggleClass("modal-active ");
    $(".modal").show();
  });

  $("#ratingContent").on("click", "#complain", function () {
    var complaint = $(this).parent().siblings("#complaint").val();

    var orderID = order.id;

    $.ajax({
      type: "POST",
      url: "http://localhost:8080/Server_war_exploded/order/complaint",
      headers: {
        authorization: authHeader,
      },

      data: JSON.stringify({
        customer: customer,
        complaint: complaint,
        id: orderID,
      }),
      contentType: "application/json; charset=utf-8",

      success: function (response) {
        console.log("pass");
      },
      failure: function () {
        console.log("fail");
      },
    });
  });

  $("#ratingContent").on("click", "#rate", function () {
    var rating = $("#ratingContent").attr("data-rating");
    var dish = $("#ratingContent").attr("data-dish");

    $.ajax({
      type: "PUT",
      url: "http://localhost:8080/Server_war_exploded/dish/rate/" + customer,
      headers: {
        authorization: authHeader,
      },

      data: JSON.stringify({
        rating: rating,
        id: dish,
      }),
      contentType: "application/json; charset=utf-8",

      success: function (response) {
        console.log("pass");
        const deSerializedData = OrderSerializer.deSerialize(order);
        var orderIndex = parseInt($("#ratingContent").attr("data-index"));
        console.log(orderIndex);
        console.log(orderIndex);
        new Order(deSerializedData).reviewOrder(orderIndex);
      },
      failure: function () {
        console.log("fail");
      },
    });
  });

  $("#checkout").click(function (e) {
    if ($("#active-status").html() == "Delivered") {
      $("#rateOrder").toggleClass("modal-active ");
      $(".modal").show();
      const deSerializedData = OrderSerializer.deSerialize(order);
      var orderIndex = parseInt($("#ratingContent").attr("data-index"));
      console.log(orderIndex);
      new Order(deSerializedData).reviewOrder(orderIndex);
      console.log(order.dishes.length);

      return;
    }

    var distanceFlag = validateLocation();
    var cartFlag = validateCart(parseInt($("#cartPrice").html()));
    var methodFlag = validatePayment();

    if (cartFlag && methodFlag) {
      console.log("bdjbvc");
      e.preventDefault();
      if ($("#card").hasClass("selected")) {
        $("#target").submit(function (event) {
          event.preventDefault();
          console.log("hu");
        });
      }

      var dishArray = [];
      $.each($(".item"), function (index, value) {
        var id = parseInt($(this).attr("data-id"));

        var quantity = parseFloat(
          $(this)
            .children(".details")
            .children(".text-upper")
            .text()
            .split(" ")[1]
        );

        var name = $(this).children(".details").children(".text-2").text();

        names.push(name);

        //   console.log(quantity);
        dishArray.push({ id: id, quantity: quantity });
        console.log(dishArray);
      });

      var longitude = parseFloat($("#longitude").val());
      var latitude = parseFloat($("#latitude").val());

      var distance = parseInt($("#distance").html().split(" ")[0]);
      var price = parseInt($("#price").html().split(" ")[1]);
      var timestamp = new Date().toISOString().slice(0, 19).replace("T", " ");

      console.log({
        latitude: latitude,
        longitude: longitude,
        customer: customer,
        distance: distance,
        payment: payment,
        dishArray: dishArray,
        price: price,
        timestamp: timestamp,
      });

      $.ajax({
        type: "POST",
        url: "http://localhost:8080/Server_war_exploded/order",
        headers: {
          authorization: authHeader,
        },

        data: JSON.stringify({
          customer: customer,
          dishes: dishArray,
          payment: payment,
          timestamp: timestamp,
          longitude: longitude,
          latitude: latitude,
          distance: distance,
          price: price,
        }),
        contentType: "application/json; charset=utf-8",

        success: function (response) {
          console.log("successfull order");
          console.log(response);
          if (payment == "card") {
            $("input[name='order_id']").html(response);

            $("input[name='items']").val(names.toString());
            var price = parseInt($("#price").html().split(" ")[1]);
            console.log(price);
            $("input[name='amount']").val(price);
            $("#targetz").submit();
          }

          console.log(payment);
        },
        failure: function () {
          alert("fail");
        },
      });
    }
  });
});
