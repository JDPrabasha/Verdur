$(window).on("load", function () {
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var rider = parseInt(window.localStorage.getItem("rider"));
  var photo = window.localStorage.getItem("photo");
  var name = window.localStorage.getItem("name");
  $("#photo").attr("src", photo);
  $("#name").html(name);
  $("#firstName").html(name.split(" ")[0]);
  $("#riderId").html(rider);

  $("#statusToggle").click(function () {
    if ($("#status").html() == "ASSIGNED") {
      return;
    }
    if ($("#status").html() == "AVAILIBLE") {
      $("#status").html("UNAVAILIBLE");
      $("#statusToggle").html(" toggle_off");
      $("#statusToggle").css("color", "red");
      toggleAvailibility();
    } else if ($("#status").html() == "UNAVAILIBLE") {
      $("#status").html("AVAILIBLE");
      $("#statusToggle").html(" toggle_on");
      $("#statusToggle").css("color", "blue");
      toggleAvailibility();
    }
  });

  function toggleAvailibility() {
    $.ajax({
      type: "PUT",
      url:
        "http://localhost:8080/Server_war_exploded/rider/toggle?rider=" + rider,
      headers: {
        authorization: authHeader,
      },

      success: function () {
        console.log("pass");

        console.log("payment");
      },
      failure: function () {
        alert("fail");
      },
    });
  }

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/rider/" + rider,
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    var data = $.parseJSON(data);

    $("#dueAmount").html(data.due);
    if (data.availible == "available") {
      $("#status").html("AVAILIBLE");
      $("#statusToggle").html(" toggle_on");
    } else if (data.availible == "unavailable") {
      $("#status").html("UNAVAILIBLE");
      $("#statusToggle").html(" toggle_off");
      $("#statusToggle").css("color", "red");
    } else {
      $("#status").html("ASSIGNED");
      $("#statusToggle").css("color", "mediumspringgreen");
    }
    console.log(data);

    // const deSerializedData = array.map(CartSerializer.deSerialize);
    // console.log(deSerializedData);
    // console.log("hyu");
    // deSerializedData.map((params) => new Dish(params).addCartItem());
  });
});
