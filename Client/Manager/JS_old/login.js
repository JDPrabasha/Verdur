function login(){
    name = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    document.getElementById('password').value = '';
    
    var authString = name + ':' + password;
    var encodedAuth = btoa(authString);
    var authHeader = 'Basic ' + encodedAuth;

    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.onreadystatechange = function(){
        if(ajaxRequest.readyState == 4){
            if(ajaxRequest.status == 200){
                token = ajaxRequest.responseText;
                console.log(token);
                window.localStorage.setItem("jwt", token);
                window.location = '/Client/Manager/Manager-Home.html';
            }
        }			
    };
    console.log("hy");
    ajaxRequest.open('POST', 'http://localhost:8080/Server_war_exploded/jauth');
    ajaxRequest.setRequestHeader("authorization", authHeader);
    ajaxRequest.send();
}

// $(document).ready(function() {
//     $("#loginBtn").click(function () {
//         var name = $("#username").val();
//         var password = $("#password").val();
//         $.ajax
//         ({
//           type: "POST",
//           url: "http://localhost:8080/Server_war_exploded/auth",
//           headers: {
//             "authorization": "Basic " + btoa(name + ":" + password)
//           },

//           success: function (){
//               token=ajaxRequest.responseText;
//             console.log(token)
//             window.location.href = '/Client/index.html';

//           }
//         });
//     });
// });