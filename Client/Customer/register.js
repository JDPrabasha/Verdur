$(document).ready(function () {
  $("#registerBtn").click(function () {
    $(".error").addClass("hidden");
    var name = $("#newname").val();
    var email = $("#newemail").val();
    console.log(email);
    var contact = $("#newcontact").val();
    var password = $("#newpassword").val();
    console.log(password);

    var nameFlag = validateName(name);
    var contactFlag = validateContact(contact);
    var emailFlag = validateEmail(email);
    var passwordFlag = validatePassword(password);

    if (nameFlag && contactFlag && emailFlag && passwordFlag) {
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/Server_war_exploded/register",

        data: JSON.stringify({
          name: name,
          contact: contact,
          username: email,
          password: password,
        }),
        contentType: "application/json; charset=utf-8",

        success: function (response) {
          window.location = "customer-landing.html";
          // console.log(response);
        },
        failure: function (response) {
          // console.log(response.);
        },
        error: function (request, status, error) {
          console.log(error);
          console.log(request);
          console.log(status);
          $("#emailerror").removeClass("hidden");
          $("#emailerror").html(
            "Email is already in use. Please try again from a different email"
          );
        },
      });
    }
  });

  function validateName(input) {
    var regName = /^[a-zA-Z]+ [a-zA-Z]+$/;

    if (!regName.test(input)) {
      $("#nameerror").removeClass("hidden");
      $("#nameerror").html("Please enter your full name (first & last name)");

      //   document.getElementById("name").focus();
      return false;
    } else {
      // alert('Valid name given.');
      return true;
    }
  }

  function validateContact(inputtxt) {
    var phoneno = /^07\d{8}$/;
    if (inputtxt.match(phoneno)) {
      return true;
    } else {
      $("#contacterror").removeClass("hidden");
      $("#contacterror").html("Invalid contact");
      return false;
    }
  }
  function validateEmail(input) {
    var validRegex =
      /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    if (input.match(validRegex)) {
      return true;
    } else {
      $("#emailerror").removeClass("hidden");
      $("#emailerror").html("Invalid email format");
      return false;
    }
  }

  function validatePassword(input) {
    if (input.length >= 8) {
      return true;
    } else {
      $("#passworderror").removeClass("hidden");
      $("#passworderror").html("Password must be at least 8 characters");
      return false;
    }
  }
});
