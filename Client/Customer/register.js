$(document).ready(function () {
  $("#registerBtn").click(function () {
    var name = $("#newname").val();
    var email = $("#newemail").val();
    console.log(email);
    var contact = $("#newcontact").val();
    var password = $("#newpassword").val();
    console.log(password);

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
      dataType: "json",

      success: function (response) {
        window.location = "customer-landing.html";
      },
      failure: function (response) {
        alert("fail");
      },
    });
    // validateEmail(email);
    // validateContact(contact);

    // document.getElementById("password").value = "";

    // // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8080/Server_war_exploded/register",
    //     data: JSON.stringify({ "username": name, "password" : password}),
    //         contentType: "application/json; charset=utf-8",
    //         dataType: "json",

    //         success: function (response) {
    //            if (response.d == true) {
    //                 alert("You will now be redirected.");
    //                 window.location = "//www.aspsnippets.com/";
    //             }
    //         },
    //         failure: function (response) {
    //             alert(response.d);
    //         }
    //     });
  });

  //   function timer() {
  //     $("#newname").val($("#newname").val() + "a");
  //   }

  //   window.setInterval(timer, 1000);

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
    var phoneno = /^\d{10}$/;
    if (inputtxt.match(phoneno)) {
      return true;
    } else {
      $("#contacterror").removeClass("hidden");
      $("#contacterror").html("Invalid email format");
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
});
