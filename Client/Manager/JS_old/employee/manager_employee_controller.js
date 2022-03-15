import Employee from "./manager_employee.js";
import Manager_employee_serialize from "./manager_employee_serializer.js";

$.getScript("/Client/Manager/JS/functionalities/expand.js");
$.getScript("/Client/Manager/JS/functionalities/popup.js");

$.getScript("/Client/Manager/JS/side_menu.js",
    // importing other scripts to the head
    $(document).ready(function doSearch() {

        // var imported = $(document.createElement('script'));
        // imported.attr("src", "/Client/Manager/JS/employee/other_scripts.js")
        // $(document.head).append(imported);
        $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification")
        var authHeader = "Bearer " + window.localStorage.getItem("jwt");
        // console.log(authHeader);
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/employee",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            },
        }).then(function (data) {
            updateView(data);
        }),
            $("#searchQuerySubmit").click(function () {
                // console.log($("#searchQueryInput").val());
                $.ajax({
                    url: `http://localhost:8080/Server_war_exploded/Manager/employee?search=${$("#searchQueryInput").val()}`,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("authorization", authHeader);
                    },
                }).then(function (data) {
                    updateView(data);
                })

            })
    }));

function updateView(data) {
    $("#results").html('');
    var array = $.parseJSON(JSON.stringify(data));
    console.log(array);
    const deSerializedData = array.map(i => Manager_employee_serialize.doSerialize(i));
    // const deSerializedData = array.map(i=>i);
    console.log(deSerializedData);
    deSerializedData.map(params => new Employee(params).addemployeenew());
    updateExpandButtons();
}



$("#createEmployee").on("click",function createEmployee() {
    let
        // image = document.document.getElementById("file-input"),
        name = document.getElementById("form_name").value,
        dob = document.getElementById("form_dob").value,
        idno = document.getElementById("form_idno").value,
        contactno = document.getElementById("form_Contact-no").value,
        address = document.getElementById("form_address").value,
        hiring_date = document.getElementById("form_hiring-date").value,
        email = document.getElementById("form_email").value,
        roles = document.getElementById("form_role").value;
    // used to stop popping the alert multiple times
    var alertx;
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/Server_war_exploded/Manager/employee";
    xhr.open("POST", url, true);
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    xhr.setRequestHeader("authorization", authHeader);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // var json = JSON.parse(xhr.responseText);
            // alert("Employee Added Successfully!");
            if (!alert("Employee Added Successfully!")) { window.location.reload(); }
        } else if (xhr.readyState === 4 && xhr.status == 406 && alertx != 0) {
            // alert("Employee Creation Error!");
            if (!alert("Employee Added Creation Error!")) { alertx = 0; }
        } else if (xhr.status == 500 && alertx != 0) {
            if (!alert("Invalid Data !")) { alertx = 0; }
        }
    };
    var data = JSON.stringify({
        "name": name,
        "email": email,
        "contact_no": contactno,
        "Address": address,
        "idno": idno,
        "hiring_date": hiring_date,
        "dob": dob,
        "role": roles,
        "image": "/Client/images/employee-icon.jpg",
        "password": "pass"
    });
    console.log(data)
    xhr.send(data);

})
