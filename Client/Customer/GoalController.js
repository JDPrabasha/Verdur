$(window).on("load", function () {
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var rider = parseInt(window.localStorage.getItem("rider"));
  var photo = window.localStorage.getItem("photo");
  var name = window.localStorage.getItem("name");
  var goals = [];
  var current = 0;
  var mgoal = {};
  $("#photo").attr("src", photo);
  $("#name").html(name);
  $("#firstName").html(name.split(" ")[0]);
  $("#riderId").html(rider);
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/goals",
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    var array = $.parseJSON(data);
    console.log(array);
    goals = array;
    array.forEach(function (arrayItem) {
      if (arrayItem.orders > current) {
        mgoal = arrayItem;
      }
      // var x = arrayItem.orders + 2;
      // console.log(x);
    });

    console.log(mgoal);
    $("#cp").html(mgoal.checkpoint);
    $("#rem").html(mgoal.orders - current);

    var ctx = document.getElementById("myChart").getContext("2d");
    var myChart = new Chart(ctx, {
      type: "doughnut",
      data: {
        labels: ["Completed", "Remaining"],
        datasets: [
          {
            label: "My First Dataset",
            data: [current, mgoal.orders - current],
            backgroundColor: ["rgb(255, 99, 132)", "rgb(54, 162, 235)"],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        responsize: true,
      },
    });

    // const deSerializedData = array.map(CartSerializer.deSerialize);
    // console.log(deSerializedData);
    // console.log("hyu");
    // deSerializedData.map((params) => new Dish(params).addCartItem());
  });

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/rider/orders?id=" + rider,
    headers: {
      authorization: authHeader,
    },
  }).then(function (data) {
    console.log(data);
    var orders = data;
    current = data;
    $("#totalOrders").html(orders);

    console.log(mgoal);

    // const deSerializedData = array.map(CartSerializer.deSerialize);
    // console.log(deSerializedData);
    // console.log("hyu");
    // deSerializedData.map((params) => new Dish(params).addCartItem());
  });

  //   $.ajax({
  //     type: "GET",
  //     url: "http://localhost:8080/Server_war_exploded/rider/orders?id=" + rider,
  //     headers: {
  //       authorization: authHeader,
  //     },
  //   }).then(function (data) {
  //     var array = $.parseJSON(data);
  //     console.log(array);

  //   });
});
