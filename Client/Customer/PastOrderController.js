$(window).on("load", function () {
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var customer = parseInt(window.localStorage.getItem("customer"));
  if (!window.localStorage.getItem("customer")) {
    console.log("hello");
    var signUp = $(document.createElement("li")).addClass("ml-5 btn");

    $("#nav-items").append(signUp);
    var upLink = $(document.createElement("a"))
      .attr("href", "customer-login.html")
      .html("Sign Up");
    signUp.append(upLink);

    var signIn = $(document.createElement("li")).addClass("ml-5 btn bg-black");
    var inLink = $(document.createElement("a"))
      .attr("href", "customer-login.html")
      .html("Sign In");
    signIn.append(inLink);
    $("#nav-items").append(signUp);
    $("#nav-items").append(signIn);
  } else {
    var name = $(document.createElement("li"))
      .addClass("fw-b pl-5")
      .html(window.localStorage.getItem("name"));

    var avatarContainer = $(document.createElement("li")).addClass("pl-5");
    var link = $(document.createElement("a")).attr(
      "href",
      "customer-profile.html"
    );
    var avatar = $(document.createElement("img"))
      .addClass("icon-2")
      .attr("src", window.localStorage.getItem("avatar"));
    var badge = $(document.createElement("span"))
      .addClass("icon-button__badge notificationCount")
      .html(2);
    var bell = $(document.createElement("span"))
      .addClass("material-icons")
      .html("notifications");

    var notification = $(document.createElement("button")).addClass(
      "icon-button "
    );

    notification.append(bell);
    notification.append(badge);

    // getNotifications();
    // getInformation();

    link.append(avatar);
    avatarContainer.append(link);
    $("#nav-items").append(notification);
    $("#nav-items").append(avatarContainer);
    $("#nav-items").append(name);
  }

  $("#orders").on("click", ".cart", function () {
    // e.preventDefault();
    console.log("clicky");
    var id = $(this).attr("data-id");
    console.log(id);
    $.ajax({
      type: "PUT",
      url: "http://localhost:8080/Server_war_exploded/cart?id=" + id,

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

  // $(".cart").click(function (e) {
  //   e.preventDefault();
  //   console.log("clicky");
  //   var id = $(this.attr("data-id"));
  //   console.log(id);
  //   $.ajax({
  //     type: "PUT",
  //     url: "http://localhost:8080/Server_war_exploded/cart?id=" + id,

  //     headers: {
  //       authorization: authHeader,
  //     },

  //     contentType: "application/json; charset=utf-8",

  //     success: function () {
  //       console.log("pass");
  //     },
  //     failure: function () {
  //       alert("fail");
  //     },
  //   });
  // });

  var payment = "";
  var array = [];
  var length = 0;
  var counter = length;
  $.ajax({
    url:
      "http://localhost:8080/Server_war_exploded/order/recents?customer=" +
      customer,
    beforeSend: function (xhr) {
      xhr.setRequestHeader("authorization", authHeader);
    },
  }).then(function (data) {
    array = $.parseJSON(data);
    console.log(array);
    const deSerializedData = array.map(OrderSerializer.deSerialize);
    console.log(deSerializedData);
    deSerializedData.map((params) => new Order(params).addPastOrder());
    // length = array.length;
    // counter = length;
    // const deSerializedData = array.map(OrderSerializer.deSerialize);
    // // console.log(deSerializedData);
    // deSerializedData.map((params) => new Order(params).addOrder());
  });
});
