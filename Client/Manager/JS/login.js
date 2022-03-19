function login() {
    name = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    document.getElementById('password').value = '';

    var authString = name + ':' + password;
    var encodedAuth = btoa(authString);
    var authHeader = 'Basic ' + encodedAuth;

    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.onreadystatechange = function () {
        if (ajaxRequest.readyState == 4) {
            if (ajaxRequest.status == 200) {
                token = JSON.parse(ajaxRequest.responseText);
                // console.log(token);
                window.localStorage.setItem("jwt", token.token);
                window.localStorage.setItem("id", token.id);
                setDetails(token.role);
                // window.location = '/Client/Manager/Manager-Home.html';
            }
        }
    };
    // console.log("hy");
    // console.log(authHeader);
    ajaxRequest.open('POST', 'http://localhost:8080/Server_war_exploded/jauth');
    ajaxRequest.setRequestHeader("authorization", authHeader);
    ajaxRequest.send();
}

function setDetails(role) {
    authHeader = "Bearer " + window.localStorage.getItem("jwt");
    if (role == "Customer") {
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
            // console.log(user);
            window.localStorage.setItem("avatar", user.avatar);
            window.localStorage.setItem("customer", user.id);
            window.localStorage.setItem("name", user.name);
            console.log(window.localStorage.getItem("name"));

            window.location = "/Client/Customer/customer-menu.html";
        });
    } else {
        $.ajax({
            type: "GET",
            url:
                `http://localhost:8080/Server_war_exploded/profile?role=${role}&id=` +
                window.localStorage.getItem("id"),
            headers: {
                authorization: authHeader,
            },
        }).then(function (data) {
            // console.log(role)
            var user = $.parseJSON(data);
            // console.log(user);
            window.localStorage.setItem("photo", user.avatar);
            window.localStorage.setItem("name", user.name);
            console.log(window.localStorage.getItem("name"));
            if (role == "Rider") {
                window.localStorage.setItem("rider", user.id);
                window.location = "/Client/rider-home.html";
            } else if (role == "Manager") {
                // window.localStorage.setItem("userID", user.id);
                window.location = "/Client/Manager/Manager-Home.html";
            } else if (role == "Kitchen Manager") {
                // window.localStorage.setItem("userID", user.id);
                window.location = "/Client/kitchenmanager/home.html"
                //Km location
            } else if (role == "Supplier") {
                //supplier location
                window.location = "/Client/Supplier/SupplierOrders.html"
            } else if (role == "Cashier") {
                //cashier
                window.location = "/Client/Cashier/CashierHome.html"
            } else if (role == "Chef"){
                //chef location
                window.location = "/Client/chef/home.html"
            }
        });
    }

}