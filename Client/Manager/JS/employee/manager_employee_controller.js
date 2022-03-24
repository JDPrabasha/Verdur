import Employee from "./manager_employee.js";
import Manager_employee_serialize from "./manager_employee_serializer.js";
import { nextbuttons } from "../functionalities/next_Buttons.js";

var chunk = 7;

$.getScript("/Client/Manager/JS/functionalities/expand.js");
$.getScript("/Client/Manager/JS/functionalities/popup.js");
$.getScript("/Client/Manager/JS/functionalities/image_upload.js");
// $.getScript("/Client/Manager/JS/functionalities/next_Buttons.js");

let empData;

$.getScript("/Client/Manager/JS/side_menu.js",
    // importing other scripts to the head
    $(document).ready(function reload() {

        // var imported = $(document.createElement('script'));
        // imported.attr("src", "/Client/Manager/JS/employee/other_scripts.js")
        // $(document.head).append(imported);
        $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification")
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
        // $.getScript("/Client/Manager/JS/functionalities/notification/Manager_Notification_Controller.js");

        var authHeader = "Bearer " + window.localStorage.getItem("jwt");
        // console.log(authHeader);
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/employee",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            },
            error:function(){
                reload();
            }
        }).then(function (data) {
            updateView(data);
            empData = data;
            $("#loading").trigger("loaded")
        }),
            $("#searchQuerySubmit").click(function () {
                // console.log($("#searchQueryInput").val());
                let searchString = $("#searchQueryInput").val()
                updateView(filterData(empData,searchString))

                // $.ajax({
                //     url: `http://localhost:8080/Server_war_exploded/Manager/employee?search=${$("#searchQueryInput").val()}`,
                //     beforeSend: function (xhr) {
                //         xhr.setRequestHeader("authorization", authHeader);
                //     },
                // }).then(function (data) {
                //     updateView(data);
                // })

            })

    })
);
var array;
function updateView(data) {
    $("#results").html('');
    // var array = $.parseJSON(JSON.stringify(data));
    array = $.parseJSON(JSON.stringify(data));
    // console.log(array.length);

    // ----------- Create Next Buttons & initiate them ----------------------- //

    nextbuttons(array.length, chunk, array);
    initiateNextButtons();

    // ----------------------------- Done ----------------------------------- //


    const deSerializedData = array.slice(0, chunk).map(i => Manager_employee_serialize.doSerialize(i));
    // const deSerializedData = array.map(i=>i);
    // console.log(deSerializedData);
    deSerializedData.map(params => new Employee(params).addemployeenew());
    updateExpandButtons();
    updateEditButtons();
}






//update the edit buttons
function updateEditButtons() {
    doneEditButtons();
    deleteEmpButton();
    $('[id^=edit-').each(function () {
        $(this).on("click", function () {
            // console.log(this.id.split('-')[1])
            // console.log(this.parentElement.parentElement)
            var childele = (this.parentElement.parentElement)
            // console.log($(childele).find('#name'))
            $(this).attr("style", "display:none")
            $(`#delete-emp-${this.id.split('-')[1]}`).attr("style", "display:none")
            $(`#done-edit-${this.id.split('-')[1]}`).attr("style", "display:block");
            $(`#cancel-edit-${this.id.split('-')[1]}`).attr("style", "display:block");

            $(childele).find('#name').prop("disabled", false)
            // $(childele).find('#idno').prop("disabled", false)
            // $(childele).find('#dob').prop("disabled", false)
            $(childele).find('#contact_no').prop("disabled", false)
            $(childele).find('#address').prop("disabled", false)

            $(childele).find('#name')[0].classList.add("enabled-input")
            $(childele).find('#contact_no')[0].classList.add("enabled-input")
            $(childele).find('#address')[0].classList.add("enabled-input")

            let name = $(childele).find('#name').val(),
                contact = $(childele).find('#contact_no').val(),
                address = $(childele).find('#address').val();

            $(`#cancel-edit-${this.id.split('-')[1]}`).click(function () {
                $(childele).find('#name').val(name);
                $(childele).find('#contact_no').val(contact);
                $(childele).find('#address').val(address);

                $(childele).find('#name').prop("disabled", true)
                $(childele).find('#contact_no').prop("disabled", true)
                $(childele).find('#address').prop("disabled", true)

                $(childele).find('#name')[0].classList.remove("enabled-input")
                $(childele).find('#contact_no')[0].classList.remove("enabled-input")
                $(childele).find('#address')[0].classList.remove("enabled-input")

                $(this).attr("style", "display:none")
                $(`#done-edit-${this.id.split('-')[2]}`).attr("style", "display:none");
                $(`#edit-${this.id.split('-')[2]}`).attr("style", "display:inline-block");
                $(`#delete-emp-${this.id.split('-')[2]}`).attr("style", "display:inline-block")
            })
        })
    })
}

function doneEditButtons() {
    $('[id^=done-edit-').each(function () {
        $(this).on("click", function () {
            var childele = (this.parentElement.parentElement)
            $(this).attr("style", "display:none")
            $(`#cancel-edit-${this.id.split('-')[2]}`).attr("style", "display:none");
            $(`#edit-${this.id.split('-')[2]}`).attr("style", "display:inline-block");
            $(`#delete-emp-${this.id.split('-')[1]}`).attr("style", "display:inline-block")

            $(childele).find('#name').prop("disabled", true)
            $(childele).find('#contact_no').prop("disabled", true)
            $(childele).find('#address').prop("disabled", true)

            $(childele).find('#name')[0].classList.remove("enabled-input")
            $(childele).find('#contact_no')[0].classList.remove("enabled-input")
            $(childele).find('#address')[0].classList.remove("enabled-input")

            let name = $(childele).find('#name').val(),
                contact = $(childele).find('#contact_no').val(),
                address = $(childele).find('#address').val(),
                jsonlist = {
                    "employeeID": this.id.split('-')[2],
                    "name": name,
                    "contact_no": contact,
                    "Address": address
                }

            let mainrow = $("#rec-" + this.id.split('-')[2])[0];
            $(mainrow).find(".emp_table_name").html(name);
            $(mainrow).find(".emp_table_address").html(address);
            $(mainrow).find(".emp_table_contact").html(contact);



            var jwt = "Bearer " + window.localStorage.getItem('jwt');

            $.ajax({
                type: 'PUT',
                url: 'http://localhost:8080/Server_war_exploded/Manager/employee',
                headers: {
                    "authorization": jwt
                },
                data: JSON.stringify(jsonlist)
            }).done(function () {
                console.log("done");
            })
            console.log(jsonlist)
        })
    })
}

function deleteEmpButton() {
    $("[id^=delete-emp-").click(function () {
        let id = this.id.split("-")[2]
        var jwt = "Bearer " + window.localStorage.getItem('jwt');
        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/Server_war_exploded/Manager/employee/' + id,
            headers: {
                "authorization": jwt
            },
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                $("#rec-e-" + id).attr("style", "display:none")
                console.log(data)
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        })
    })
}




$("#createEmployee").on("click", function createEmployee() {
    let
        // image = document.document.getElementById("file-input"),
        name = document.getElementById("form_name").value,
        dob = document.getElementById("form_dob").value,
        idno = document.getElementById("form_idno").value,
        contactno = document.getElementById("form_Contact-no").value,
        address = document.getElementById("form_address").value,
        hiring_date = document.getElementById("form_hiring-date").value,
        email = document.getElementById("form_email").value,
        roles = document.getElementById("form_role").value,
        image = $("#label-image").attr("src");
    // used to stop popping the alert multiple times
    var alertx;
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/Server_war_exploded/Manager/employee";
    xhr.open("POST", url, true);
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    xhr.setRequestHeader("authorization", authHeader);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 201) {
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
        // "image": "/Client/images/employee-icon.jpg",
        "image": image,
        "password": "pass"
    });
    // console.log(data)
    xhr.send(data);

})

function initiateNextButtons() {
    //initiate the buttons
    $(`[id^=next-button]*`).each(function () {
        $(this).on("click", function () {
            // console.log($(this).html())
            Employee.changepage(parseInt($(this).html()), array, chunk)
            // changepage(parseInt($(this).html()))
        })
    })
}


function filterData(array,string){
    return array.filter(i => filterRecord(i,string))
}

function filterRecord(array,string){
    let i ,x = new Array;
    for (i in array){
        if(i=="image"){
            continue;
        }
        if (array[i].toString().toLowerCase().includes(string.toLowerCase())){
            // console.log("true")
            // console.log(array["name"] + " " + array[i])
            return true
        }
    }
    console.log("false")
    return false
}