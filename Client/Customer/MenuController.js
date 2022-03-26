console.log(window.localStorage.getItem("jwt"));
console.log(window.localStorage.getItem("avatar"));
console.log(window.localStorage.getItem("name"));

var ingredientArray = [];
var preferenceArray = [];
var allergyArray = [];
var lifestyleArray = [];
var time = 5000;
var budget = 5000;

var tagArray = [];
var type = "";
var preference = "";

$(document).ready(function () {
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
    $("div").remove(".skeleton-card");
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
    $(this).siblings().removeClass("bg-success");
    if (type == name) {
      type = "";
      $(this).removeClass("bg-success");
    } else {
      type = name;
      $(this).addClass("bg-success");
    }

    console.log(type);
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

  function runFilters() {
    var params = "";
    allergyArray.length ? tagArray.push(...allergyArray) : null;
    lifestyleArray.length ? tagArray.push(...lifestyleArray) : null;
    type != "" ? tagArray.push(type) : null;
    preference != "" ? tagArray.push(preference) : null;
    tagArray.forEach((i) => {
      params = params + i + ",";
    });
    params = params.slice(0, -1);
    console.log(params);

    console.log(tagArray);
    tagArray = [];
    $.ajax({
      type: "GET",
      url:
        "http://localhost:8080/Server_war_exploded/menu/filter?tags=" +
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

      // console.log(array);
      $("#vtf").removeClass("modal-active ");
      $("#current").addClass("hidden");
      $("#recent").addClass("hidden");
    });
  }

  $("#dietFilterBtn").click(function (e) {
    e.preventDefault();
    runFilters();
  });

  $("#filterFilterBtn").click(function (e) {
    e.preventDefault();
    runFilters();
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
    budget = this.value;
  });
  $("#timeRange").on("propertychange input", function (e) {
    $("#maxTime").html(this.value);
    console.log(this.value);
    time = this.value;
  });

  $(".preference").click(function (e) {
    // $(this).toggleClass("greyout");
    var name = $(this).children().html();
    $(this).siblings().removeClass("bg-success");
    if (preference == name) {
      $(this).removeClass("bg-success");
      preference = "";
    } else {
      $(this).addClass("bg-success");
      preference = name;
    }

    console.log(preference);
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
