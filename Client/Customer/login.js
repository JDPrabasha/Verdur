$(document).ready(function () {
  var loginSection = document.getElementById("login");
  var signupSection = document.getElementById("register");

  $("#createAccount").click(function () {
    $(loginSection).addClass("hidden");
    $(signupSection).removeClass("hidden");
  });

  $("#signIn").click(function () {
    console.log("sign in");
    $(signupSection).addClass("hidden");
    $(loginSection).removeClass("hidden");
  });
});

function login() {
  name = document.getElementById("username").value;
  var password = document.getElementById("password").value;
  document.getElementById("password").value = "";
  window.localStorage.setItem("email", name);
  var authString = name + ":" + password;
  var encodedAuth = btoa(authString);
  var authHeader = "Basic " + encodedAuth;

  console.log("helli");

  var ajaxRequest = new XMLHttpRequest();
  ajaxRequest.onreadystatechange = function () {
    if (ajaxRequest.readyState == 4) {
      if (ajaxRequest.status == 200) {
        data = ajaxRequest.responseText;

        var login = $.parseJSON(data);

        window.localStorage.setItem("jwt", login.token);
        window.localStorage.setItem("id", login.id);
        // if (name === "charithfdo@gmail.com") {
        //   window.location = "/Client/rider-home.html";
        // } else {
        setDetails(login.role);
        // }
      } else {
        console.log("error");
        $("#loginerror").removeClass("hidden");
        $("#loginerror").html("Incorrect username or password");
      }
    }
  };

  console.log("hy");
  ajaxRequest.open("POST", "http://localhost:8080/Server_war_exploded/jauth");
  ajaxRequest.setRequestHeader("authorization", authHeader);
  ajaxRequest.send();

  function setDetails(role) {
    authHeader = "Bearer " + window.localStorage.getItem("jwt");
    if (role == "Rider") {
      $.ajax({
        type: "GET",
        url:
          "http://localhost:8080/Server_war_exploded/profile?role=rider&id=" +
          window.localStorage.getItem("id"),
        headers: {
          authorization: authHeader,
        },
      }).then(function (data) {
        var user = $.parseJSON(data);
        console.log(user);
        window.localStorage.setItem("photo", user.avatar);
        window.localStorage.setItem("rider", user.id);
        window.localStorage.setItem("name", user.name);
        console.log(window.localStorage.getItem("name"));

        window.location = "/Client/Rider/rider-home.html";
      });
    } else {
      $.ajax({
        type: "GET",
        url:
          "http://localhost:8080/Server_war_exploded/profile?role=customer&id=" +
          window.localStorage.getItem("id"),
        headers: {
          authorization: authHeader,
        },
      }).then(function (data) {
        var user = $.parseJSON(data);
        console.log(user);
        window.localStorage.setItem("avatar", user.avatar);
        window.localStorage.setItem("customer", user.id);
        window.localStorage.setItem("name", user.name);
        console.log(window.localStorage.getItem("name"));

        window.location = "/Client/Customer/customer-menu.html";
      });
    }
  }
}
