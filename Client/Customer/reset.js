$(document).ready(function () {
  const key1 = urlParams.get("key1");
  const key2 = urlParams.get("key2");
  $("#signIn").click(function () {
    var password = document.getElementById("password");
    var rePassword = document.getElementById("repassword");
    var lengthFlag = validatePassword(password);
    var equalFlag = validateEqual(password, rePassword);

    if (lengthFlag && equalFlag) {
      resetPassword();
    }
  });

  function validatePassword(input) {
    if (input.length >= 8) {
      return true;
    } else {
      $("#passworderror").removeClass("hidden");
      $("#passworderror").html("Password must be at least 8 characters");
      return false;
    }
  }

  function validateEqual(first, second) {
    if (first == second) {
      return true;
    } else {
      $("#passworderror").removeClass("hidden");
      $("#passworderror").html("Passwords do not match");
      return false;
    }
  }

  function resetPassword() {
    $.ajax({
      type: "PUT",
      url:
        "http://localhost:8080/Server_war_exploded/reset?key1=" +
        key1 +
        "&key2=" +
        key2,
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify({
        username: key1,
        password: password,
      }),

      success: function (response) {
        console.log("success");
        window.location = "customer-login.html";
      },
      failure: function (response) {
        // console.log(response.);
      },
      error: function (request, status, error) {
        console.log(error);
        console.log(request);
        console.log(status);
      },
    });
  }
});
