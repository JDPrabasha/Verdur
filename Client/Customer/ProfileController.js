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

    getNotifications();
    getInformation();

    link.append(avatar);
    avatarContainer.append(link);
    $("#nav-items").append(notification);
    $("#nav-items").append(avatarContainer);
    $("#nav-items").append(name);
  }

  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  $.ajax({
    type: "GET",
    url:
      "http://localhost:8080/Server_war_exploded/dish/recents?customer=" +
      customer,

    headers: {
      authorization: authHeader,
    },

    dataType: "json",

    success: function (data) {
      console.log("pass");
      console.log(data);
      const deSerializedData = data.map(DishSerializer.deSerialize);
      // console.log(deSerializedData);
      deSerializedData.map((params) => new Dish(params).addDish());
    },
    failure: function () {
      alert("fail");
    },
  });

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/Server_war_exploded/mealplan/" + customer,

    headers: {
      authorization: authHeader,
    },

    dataType: "json",

    success: function (data) {
      console.log("pass");
      console.log(data);
      const deSerializedData = MealPlanSerializer.deSerialize(data);
      new MealPlan(deSerializedData).setMeals();
    },
    failure: function () {
      alert("fail");
    },
  });
  // $.ajax({
  //   url:
  //     "http://localhost:8080/Server_war_exploded/dish/recents?customer=" +
  //     customer,

  //   beforeSend: function (xhr) {
  //     xhr.setRequestHeader("authorization", authHeader);
  //   },
  // }).then(function (data) {
  //   var array = $.parseJSON(data);
  //   console.log(array);
  //   const deSerializedData = array.map(DishSerializer.deSerialize);
  //   // console.log(deSerializedData);
  //   deSerializedData.map((params) => new Dish(params).addDish());
  // });

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
      badge.html(newarray.length);
    });
  }

  $("#avatar").attr("src", window.localStorage.getItem("avatar"));
  $("#customer").html(window.localStorage.getItem("name"));
  $("#name").html(window.localStorage.getItem("name"));
  $("#firstName").html(window.localStorage.getItem("name").split(" ")[0]);
  $("#picture").attr("src", window.localStorage.getItem("avatar"));

  $("#addMeal").click(function (e) {
    e.preventDefault();
    console.log("length");
    $("#mealModal").addClass("modal-active");

    $("#mealModal").show();
  });
  $("#editProfile").click(function (e) {
    e.preventDefault();
    console.log("length");
    $("#profileModal").addClass("modal-active");

    $("#mealModal").show();
  });

  $("#confirmProfileChange").click(function (e) {
    e.preventDefault();
    var avatar = $(".avatar, .selected").attr("data-id");
    var address = $("#newaddress").val();
    var contact = $("#newcontact").val();

    updateInformation(avatar, contact, address);
  });

  $("#confirmPlanChange").click(function (e) {
    console.log("bdjbvc");
    e.preventDefault();
    var calorieGoal = $("#newcaloriegoal").val();
    var proteinGoal = $("#newproteingoal").val();
    var carbGoal = $("#newcarbgoal").val();
    var fatGoal = $("#newfatgoal").val();
    editMealPlan(calorieGoal, proteinGoal, fatGoal, carbGoal);
  });

  $(".avatar").click(function () {
    $.each($(".avatar"), function (index, value) {
      $(this).removeClass("selected");
    });
    $(this).addClass("selected");
    console.log($(this).attr("data-id"));
  });

  $("#addNewMeal").click(function (e) {
    var calories = $("#calorieintake").val();
    var protein = $("#proteinintake").val();
    var fats = console.log($("#fatintake").val());
    var carbs = $("#carbintake").val();

    addMeal(calories, protein, fats, carbs);
    e.preventDefault();
  });

  $("#editPlan").click(function (e) {
    e.preventDefault();
    $("#planModal").addClass("modal-active");

    $("#planModal").show();
  });

  $(".modal-close").click(function (e) {
    e.preventDefault();

    $("#planModal").removeClass("modal-active ");
    $("#mealModal").removeClass("modal-active ");
    $("#profileModal").removeClass("modal-active ");
    $(".modal").hide();
  });

  $(".filterFilter").click(function (e) {
    e.preventDefault();
    $(".plan-modal").addClass("modal-active");

    $(".plan-modal").show();
  });

  $(".dietFilter").click(function (e) {
    console.log("🚀 ~ file: MenuController.js ~ line 56 ~ e", e);
    e.preventDefault();
    $("#dietModal").addClass("modal-active");

    $(".diet-modal").show();
  });

  $(".modal-close").click(function (e) {
    e.preventDefault();
    $("#vtf").removeClass("modal-active ");
    $("#dietModal").removeClass("modal-active ");
    $("#filterModal").removeClass("modal-active ");
    $(".modal").hide();
    $(".diet-modal").hide();
    $(".filter-modal").hide();
  });

  function editMealPlan(caloriesgoal, proteingoal, fatgoal, carbsgoal) {
    $.ajax({
      type: "PUT",
      url:
        "http://localhost:8080/Server_war_exploded/mealplan/edit?customer=" +
        customer,
      headers: {
        authorization: authHeader,
      },

      data: JSON.stringify({
        fatgoal: fatgoal,
        proteingoal: proteingoal,
        caloriesgoal: caloriesgoal,
        carbsgoal: carbsgoal,
      }),
      contentType: "application/json; charset=utf-8",

      success: function () {
        console.log("pass");
      },
      failure: function () {
        alert("fail");
      },
    });
  }

  // function editProfile() {
  //   $.ajax({
  //     type: "PUT",
  //     url:
  //       "http://localhost:8080/Server_war_exploded/profile/edit?customer=" +
  //       cusomer,
  //     headers: {
  //       authorization: authHeader,
  //     },

  //     data: JSON.stringify({
  //       avatar: avatar,
  //       contact: contact,
  //       address: address,
  //       gender: gender,
  //     }),
  //     contentType: "application/json; charset=utf-8",

  //     success: function () {
  //       console.log("pass");
  //     },
  //     failure: function () {
  //       alert("fail");
  //     },
  //   });
  // }

  function getInformation() {
    $.ajax({
      url:
        "http://localhost:8080/Server_war_exploded/profile/customer?id=" +
        customer,
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      newarray = $.parseJSON(data);

      $("#contact").html(newarray.contact);
      $("#newcontact").attr("placeholder", newarray.contact);
      $("#address").html(newarray.address);
      $("#newaddress").attr("placeholder", newarray.address);
      var img = window.localStorage.getItem("avatar");
      var ra = $('#profileModal img[src*="' + img + '"]');
      $(ra).addClass("selected");
      $("#mail").html(window.localStorage.getItem("email"));
    });
  }

  function updateInformation(avatar, contact, address) {
    $.ajax({
      type: "PUT",
      url: "http://localhost:8080/Server_war_exploded/customer?id=" + customer,
      headers: {
        authorization: authHeader,
      },

      data: JSON.stringify({
        avatar: avatar,
        contact: contact,
        address: address,
      }),
      contentType: "application/json; charset=utf-8",

      success: function () {
        console.log("pass");
      },
      failure: function () {
        alert("fail");
      },
    });
  }

  function addMeal(calories, protein, fats, carbs) {
    $.ajax({
      type: "PUT",
      url:
        "http://localhost:8080/Server_war_exploded/mealplan/add?customer=" +
        customer,
      headers: {
        authorization: authHeader,
      },

      data: JSON.stringify({
        fat: fats,
        protein: protein,
        calories: calories,
        carbs: carbs,
      }),
      contentType: "application/json; charset=utf-8",

      success: function () {
        console.log("pass");
      },
      failure: function () {
        alert("fail");
      },
    });
  }

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
});
