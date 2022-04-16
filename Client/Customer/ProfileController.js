$(window).on("load", function () {
  var authHeader = "Bearer " + window.localStorage.getItem("jwt");
  var customer = parseInt(window.localStorage.getItem("customer"));

  var avatarID = 0;
  var address = "";
  var contact = "";
  var avatarImage = "";

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

  function getMealPlan() {
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
  }

  getMealPlan();

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

    var address = $("#newaddress").val()
      ? $("#newaddress").val()
      : $("#address").html();
    var contact = $("#newcontact").val()
      ? $("#newcontact").val()
      : $("#contact").html();
    avatarImage = avatarImage
      ? avatarImage
      : window.localStorage.getItem("avatar");
    console.log(avatarImage);
    console.log(address);
    console.log(contact);
    updateInformation(avatarImage, contact, address);
  });

  $("#confirmPlanChange").click(function (e) {
    console.log("bdjbvc");
    e.preventDefault();
    var calorieGoal = $("#newcaloriegoal").val();
    var proteinGoal = $("#newproteingoal").val();
    var carbGoal = $("#newcarbgoal").val();
    var fatGoal = $("#newfatgoal").val();
    console.log(carbGoal);
    editMealPlan(calorieGoal, proteinGoal, fatGoal, carbGoal);
  });

  $(".avatar").click(function () {
    $.each($(".avatar"), function (index, value) {
      $(this).removeClass("selected");
    });
    $(this).addClass("selected");
    console.log($(this).attr("data-id"));
    avatarImage = $(this).attr("src");
  });

  $("#addNewMeal").click(function (e) {
    var calories = $("#calorieintake").val();
    var protein = $("#proteinintake").val();
    var fats = $("#fatintake").val();
    var carbs = $("#carbintake").val();
    console.log(fats);
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
    console.log(carbsgoal);
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
        carbgoal: carbsgoal,
      }),
      contentType: "application/json; charset=utf-8",

      success: function () {
        console.log("pass");
        getMealPlan();
      },
      failure: function () {
        alert("fail");
      },
    });
  }

  $("#resetMeals").click(function (e) {
    e.preventDefault();
    $.ajax({
      type: "PUT",
      url:
        "http://localhost:8080/Server_war_exploded/mealplan/reset?customer=" +
        customer,
      headers: {
        authorization: authHeader,
      },

      contentType: "application/json; charset=utf-8",

      success: function () {
        console.log("pass");
        getMealPlan();
      },
      failure: function () {
        alert("fail");
      },
    });
  });

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
      // $(ra).addClass("selected");
      $("#mail").html(window.localStorage.getItem("email"));
    });
  }

  function updateInformation(avatar, contact, address) {
    console.log(avatar);
    console.log(contact);
    console.log(address);
    $.ajax({
      type: "PUT",
      url: "http://localhost:8080/Server_war_exploded/profile?id=" + customer,
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
    console.log(fats);
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

        getMealPlan();
        $("#mealModal").hide();
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
