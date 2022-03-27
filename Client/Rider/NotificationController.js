$(window).on("load", function () {
  var notificationCount = 0;

  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var rider = parseInt(window.localStorage.getItem("rider"));

  var badge = $(document.createElement("span"))
    .addClass("icon-button__badge notificationCount")
    .html(2);
  var bell = $(document.createElement("span"))
    .addClass("material-icons")
    .html("notifications");

  var notification = $(document.createElement("button")).addClass(
    "icon-button ml-50"
  );

  notification.append(bell);

  $(bell).click(function (e) {
    e.preventDefault();
    notification.children(".notificationCount").remove();
    $(".dropdown").toggleClass("active");
    window.localStorage.setItem("notifications", parseInt(badge.html()));
  });

  var container = $("#links");

  container.append(notification);

  function getNotifications() {
    var notifCount = window.localStorage.getItem("notifications");
    $.ajax({
      url:
        "http://localhost:8080/Server_war_exploded/notification?role=rider&id=" +
        rider,
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
      newarray.length > 0 && newarray.length > notifCount
        ? notification.append(badge)
        : null;
    });
  }

  getNotifications();
});
