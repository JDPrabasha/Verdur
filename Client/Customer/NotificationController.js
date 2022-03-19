$(window).on("load", function () {
  var notificationCount = 0;

  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var customer = window.localStorage.getItem("customer");

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

    $(bell).click(function (e) {
      e.preventDefault();
      notification.children(".notificationCount").remove();
      $(".dropdown").toggleClass("active");
    });
    // getNotifications();
    // getInformation();

    link.append(avatar);
    avatarContainer.append(link);
    $("#nav-items").append(notification);
    $("#nav-items").append(avatarContainer);
    $("#nav-items").append(name);
  }

  function getNotifications() {
    $.ajax({
      url:
        "http://localhost:8080/Server_war_exploded/notification?role=customer&id=" +
        customer,
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      newarray = $.parseJSON(data);
      console.log(newarray);
      const deSerializedData = newarray.map(NotificationSerializer.deSerialize);
      // console.log(deSerializedData);
      deSerializedData.map((params) =>
        new Notification(params).addNotification()
      );
      badge.html(newarray.length);
      newarray.length > 0 ? notification.append(badge) : null;
    });
  }

  getNotifications();
});
