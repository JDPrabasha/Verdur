console.log(window.localStorage.getItem("jwt"));
console.log(window.localStorage.getItem("avatar"));
console.log(window.localStorage.getItem("name"));

var ingredientArray = [];
var preferenceArray = [];
var allergyArray = [];
var lifestyleArray = [];
var typeArray = [];

$(document).ready(function () {
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

  window.setInterval(function () {
    var today = new Date();
    var lunch = "12:00:00";
    var dinner = "18:00:00";

    var time =
      today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

    // console.log(time > st);

    if (time > dinner) {
      $("#current").html("Dinner Specials");
    } else if (time > lunch) {
      $("#current").html("Lunch Specials");
    }
  }, 1000);

  $("#current").click(function (e) {
    e.preventDefault();
    console.log($(this).html());
    $("#dishes").empty();
    tag = $(this).html().split(" ")[0];
    console.log(tag);
    $.ajax({
      url:
        "http://localhost:8080/Server_war_exploded/menu/specials?meal=" + tag,
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      var array = $.parseJSON(data);
      console.log(array);
      const deSerializedData = array.map(MenuSerializer.deSerialize);
      // console.log(deSerializedData);
      deSerializedData.map((params) => new Dish(params).addDish());
    });
  });

  var authHeader = "Bearer " + window.localStorage.getItem("jwt");

  $.ajax({
    url: "http://localhost:8080/Server_war_exploded/menu",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("authorization", authHeader);
    },
  }).then(function (data) {
    var array = $.parseJSON(data);
    console.log(array);
    const deSerializedData = array.map(MenuSerializer.deSerialize);
    // console.log(deSerializedData);
    deSerializedData.map((params) => new Dish(params).addDish());
  });

  $("#recent").click(function (e) {
    e.preventDefault();
    $(this).addClass("display-2");
    $("#trending").removeClass("display-2");
    $("#current").removeClass("display-2");
    $("#dishes").empty();
    $.ajax({
      url: "http://localhost:8080/Server_war_exploded/menu/new",
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      var array = $.parseJSON(data);
      console.log(array);
      const deSerializedData = array.map(MenuSerializer.deSerialize);
      // console.log(deSerializedData);
      deSerializedData.map((params) => new Dish(params).addDish());
    });
  });
  $("#current").click(function (e) {
    e.preventDefault();
    $(this).addClass("display-2");
    $("#trending").removeClass("display-2");
    $("#recent").removeClass("display-2");
  });
  $("#trending").click(function (e) {
    e.preventDefault();
    $(this).addClass("display-2");
    $("#recent").removeClass("display-2");
    $("#current").removeClass("display-2");
    $("#dishes").empty();
    $.ajax({
      url: "http://localhost:8080/Server_war_exploded/menu",
      beforeSend: function (xhr) {
        xhr.setRequestHeader("authorization", authHeader);
      },
    }).then(function (data) {
      var array = $.parseJSON(data);
      console.log(array);
      const deSerializedData = array.map(MenuSerializer.deSerialize);
      // console.log(deSerializedData);
      deSerializedData.map((params) => new Dish(params).addDish());
    });
  });

  $("#search").click(function (e) {
    e.preventDefault();
    $("#search-text").val();
  });

  const searchBtn = document.querySelector(".search-btn");
  const cancelBtn = document.querySelector(".cancel-btn");
  const searchBox = document.querySelector(".search-box");

  var timesClicked = 0;
  searchBtn.onclick = () => {
    timesClicked++;
    if (timesClicked > 1) {
      timesClicked = 0;
      $("#dishes").empty();
      var query = $("#search-text").val();
      $.ajax({
        type: "GET",
        url:
          "http://localhost:8080/Server_war_exploded/menu/search?name=" + query,
        headers: {
          authorization: authHeader,
        },
      }).then(function (data) {
        console.log(data);
        var array = $.parseJSON(data);
        $("#breakfast").empty();
        $("#new").empty();
        $("#trending").html(
          array.length > 1
            ? array.length + " results"
            : array.length + " result"
        );
        console.log(array);
        const deSerializedData = array.map(MenuSerializer.deSerialize);
        // console.log(deSerializedData);
        deSerializedData.map((params) => new Dish(params).addDish());
        // var information = $.parseJSON(data);
        // console.log(information);
        // const deSerializedData = IngredientSerializer.deSerialize(data);
        // console.log(deSerializedData.description);
        // new Ingredient(deSerializedData).addIngredientInformation();

        // var ingredient = $.parseJSON(data);
        // console.log(ingredient);
        // const deSerializedData = DishSerializer.deSerialize(ingredient);
        // new Ingredient(deSerializedData).addIngredientInformation();
      });
    } else {
      searchBox.classList.add("active");
    }
  };

  cancelBtn.onclick = () => {
    searchBox.classList.remove("active");
  };

  $(".ingredientFilter").click(function (e) {
    var img = $(this).find("img").attr("src");
    if (img == "images/img_79905.png") {
      $("#vtf").toggleClass("modal-active ");
      $(".modal").show();
      return;
    }
    console.log(img);

    var item = $('#vtf .flex-column img[src*="' + img + '"]');

    console.log($(item).attr("src"));
    $(item).toggleClass("greyout");
    var name = $(item).siblings().html();
    if (ingredientArray.includes(name)) {
      const index = ingredientArray.indexOf(name);
      ingredientArray.splice(index, 1);
    } else {
      ingredientArray.push(name);
    }

    console.log(ingredientArray);
    var image = $(document.createElement("img"))
      .attr("src", $(item).attr("src"))
      .addClass("icon-2 mr-2");
    if ($(item).hasClass("greyout")) {
      $(".top").append(image);
    } else {
      $('.top img[src*="' + $(item).attr("src") + '"]').hide();
    }
    e.preventDefault();
    $("#vtf").toggleClass("modal-active ");
    $(".modal").show();
  });

  $(".filterFilter").click(function (e) {
    e.preventDefault();
    $("#filterModal").addClass("modal-active");

    $(".filter-modal").show();
  });

  $(".dietFilter").click(function (e) {
    console.log("???? ~ file: MenuController.js ~ line 56 ~ e", e);
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

  $(".modal-ingredient").click(function (e) {
    $(this).toggleClass("greyout");
    var name = $(this).siblings().html();
    if (ingredientArray.includes(name)) {
      const index = ingredientArray.indexOf(name);
      ingredientArray.splice(index, 1);
    } else {
      ingredientArray.push(name);
    }

    console.log(ingredientArray);
    var image = $(document.createElement("img"))
      .attr("src", $(this).attr("src"))
      .addClass("icon-2 mr-2");
    if ($(this).hasClass("greyout")) {
      $(".top").append(image);
    } else {
      $('.top img[src*="' + $(this).attr("src") + '"]').hide();
    }
  });

  $(".allergy").click(function (e) {
    // $(this).toggleClass("greyout");
    var name = $(this).children().html();
    if (allergyArray.includes(name)) {
      const index = allergyArray.indexOf(name);
      allergyArray.splice(index, 1);
      $(this).removeClass("bg-success");
    } else {
      allergyArray.push(name);
      $(this).addClass("bg-success");
    }

    console.log(allergyArray);
  });
  $(".type").click(function (e) {
    // $(this).toggleClass("greyout");
    var name = $(this).children().html();
    if (typeArray.includes(name)) {
      const index = typeArray.indexOf(name);
      typeArray.splice(index, 1);
      $(this).removeClass("bg-success");
    } else {
      typeArray.push(name);
      $(this).addClass("bg-success");
    }

    console.log(typeArray);
  });

  $("#ingredientFilter").click(function (e) {
    var params = "";
    ingredientArray.forEach((i) => {
      params = params + i + ",";
    });
    params = params.slice(0, -1);
    console.log(params);

    console.log("hnn");
    $.ajax({
      type: "GET",
      url:
        "http://localhost:8080/Server_war_exploded/menu/filter?ingredient=" +
        params,
      headers: {
        authorization: authHeader,
      },
    }).then(function (data) {
      console.log(data);

      var array = $.parseJSON(data);
      $("#dishes").empty();
      $("#breakfast").empty();
      $("#new").empty();
      $("#trending").html(
        array.length > 1 ? array.length + " results" : array.length + " result"
      );
      console.log(array);
      const deSerializedData = array.map(MenuSerializer.deSerialize);
      // console.log(deSerializedData);
      deSerializedData.map((params) => new Dish(params).addDish());

      console.log(array);
      $("#vtf").removeClass("modal-active ");
    });
  });

  $("#dietFilterBtn").click(function (e) {
    var prefparams = "";
    preferenceArray.forEach((i) => {
      prefparams = prefparams + i + ",";
    });
    prefparams = prefparams.slice(0, -1);
    console.log(prefparams);
    var lifestyleparams = "";
    lifestyleArray.forEach((i) => {
      lifestyleparams = lifestyleparams + i + ",";
    });
    lifestyleparams = lifestyleparams.slice(0, -1);
    console.log(lifestyleparams);
    var allergyparams = "";
    allergyArray.forEach((i) => {
      allergyparams = allergyparams + i + ",";
    });
    allergyparams = allergyparams.slice(0, -1);
    allergyparams.log(allergyparams);

    console.log("hnn");
    $.ajax({
      type: "GET",
      url:
        "http://localhost:8080/Server_war_exploded/menu/filter?preference=" +
        prefparams +
        "&lifestyle=" +
        lifestyleparams +
        "&allergy=" +
        allergyparams,
      headers: {
        authorization: authHeader,
      },
    }).then(function (data) {
      console.log(data);

      var array = $.parseJSON(data);
      // $("#dishes").empty();
      // $("#breakfast").empty();
      // $("#new").empty();
      // $("#trending").html(
      //   array.length > 1 ? array.length + " results" : array.length + " result"
      // );
      // console.log(array);
      // const deSerializedData = array.map(MenuSerializer.deSerialize);
      // // console.log(deSerializedData);
      // deSerializedData.map((params) => new Dish(params).addDish());

      // console.log(array);
      $("#vtf").removeClass("modal-active ");
    });
  });

  $("#filterFilterBtn").click(function (e) {
    var params = "";
    typeArray.forEach((i) => {
      params = params + i + ",";
    });
    params = params.slice(0, -1);
    console.log(params);

    console.log("hnn");
    $.ajax({
      type: "GET",
      url:
        "http://localhost:8080/Server_war_exploded/menu/filter?type=" +
        params +
        "&budget=" +
        budget +
        "&time=" +
        time,
      headers: {
        authorization: authHeader,
      },
    }).then(function (data) {
      console.log(data);

      var array = $.parseJSON(data);
      // $("#dishes").empty();
      // $("#breakfast").empty();
      // $("#new").empty();
      // $("#trending").html(
      //   array.length > 1 ? array.length + " results" : array.length + " result"
      // );
      // console.log(array);
      // const deSerializedData = array.map(MenuSerializer.deSerialize);
      // // console.log(deSerializedData);
      // deSerializedData.map((params) => new Dish(params).addDish());

      // // console.log(array);
      // $("#vtf").removeClass("modal-active ");
    });
  });

  $(".lifestyle").click(function (e) {
    // $(this).toggleClass("greyout");
    var name = $(this).children().html();
    if (lifestyleArray.includes(name)) {
      $(this).removeClass("bg-success");
      const index = lifestyleArray.indexOf(name);
      lifestyleArray.splice(index, 1);
    } else {
      $(this).addClass("bg-success");
      lifestyleArray.push(name);
    }

    console.log(lifestyleArray);
  });

  $("#priceRange").on("propertychange input", function (e) {
    $("#maxPrice").html(this.value);
    console.log(this.value);
  });
  $("#timeRange").on("propertychange input", function (e) {
    $("#maxTime").html(this.value);
    console.log(this.value);
  });

  $(".preference").click(function (e) {
    // $(this).toggleClass("greyout");
    var name = $(this).children().html();
    if (preferenceArray.includes(name)) {
      $(this).removeClass("bg-success");
      const index = preferenceArray.indexOf(name);
      preferenceArray.splice(index, 1);
    } else {
      $(this).addClass("bg-success");
      preferenceArray.push(name);
    }

    console.log(preferenceArray);
  });

  //     var ajaxRequest = new XMLHttpRequest();
  //     ajaxRequest.onreadystatechange = function(){
  //         if(ajaxRequest.readyState == 4){
  //             if(ajaxRequest.status == 200){
  //                 resp = ajaxRequest.responseText;
  //                 console.log(resp);
  //                 // window.location = '/Client/index.html';
  //             }
  //         }
  //     };

  //     ajaxRequest.open('POST', 'http://localhost:8080/Server_war_exploded/menu');
  //     ajaxRequest.setRequestHeader("authorization", authHeader);
  //     ajaxRequest.send();
  // // }
});
